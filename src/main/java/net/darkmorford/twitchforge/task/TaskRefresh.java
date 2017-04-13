package net.darkmorford.twitchforge.task;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import net.darkmorford.twitchforge.TwitchForge;
import net.darkmorford.twitchforge.twitch.Stream;
import net.darkmorford.twitchforge.twitch.TwitchState;
import net.darkmorford.twitchforge.utils.InstantDeserializer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.Level;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

public class TaskRefresh implements Runnable
{
    @Override
    public void run()
    {
        // https://dev.twitch.tv/docs/v5/reference/streams/

        // Build the URI for the data we want
        TwitchState.channelLock.readLock().lock();
        Long channelId = TwitchState.channelId;
        String channelName = TwitchState.channelDisplayName;
        TwitchState.channelLock.readLock().unlock();

        if (channelId == 0L)
        {
            TwitchForge.logger.log(Level.ERROR, "Refresh called before User ID initialized");
            return;
        }

        String twitchEndpoint = String.format("https://api.twitch.tv/kraken/streams/%d", channelId);

        // Create a request object and set necessary headers
        HttpGet request = new HttpGet(twitchEndpoint);
        request.addHeader("Accept", "application/vnd.twitchtv.v5+json");
        request.addHeader("Client-ID", TwitchState.twitchApiKey);

        // Lock the stream data while the request is in progress
        TwitchState.streamLock.writeLock().lock();

        // Create an HTTP client for the transaction
        CloseableHttpClient client = HttpClients.createDefault();
        try (CloseableHttpResponse response = client.execute(request))
        {
            // Get the response body
            HttpEntity responseBody = response.getEntity();

            // Get the JSON string from the response
            String responseString = IOUtils.toString(responseBody.getContent(), StandardCharsets.UTF_8);

            // Convert the JSON a data object
            Gson gson = new GsonBuilder().registerTypeAdapter(Instant.class, new InstantDeserializer()).create();
            Stream streamStatus = gson.fromJson(responseString, Stream.class);

            if (streamStatus._id == null)
            {
                TwitchState.isStreamOnline = false;

                FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList()
                        .sendChatMsg(new TextComponentTranslation("stream.offline", channelName));
            }
            else
            {
                if (!TwitchState.isStreamOnline)
                {
                    // TODO: Signal when the stream transitions to online
                    TwitchForge.logger.log(Level.INFO, "Streamer has just gone online!");
                }

                TwitchState.isStreamOnline = true;

                TwitchState.streamGame = streamStatus.game;
                TwitchState.streamStartTime = streamStatus.created_at;
                TwitchState.streamTitle = streamStatus.channel.status;
                TwitchState.streamUri = streamStatus.channel.url;

                FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList()
                        .sendChatMsg(new TextComponentTranslation("stream.online", channelName, streamStatus.channel.status));
            }
        }
        catch (IOException e)
        {
            TwitchForge.logger.log(Level.ERROR, e.getMessage());
        }
        catch (JsonSyntaxException e)
        {
        }
        finally
        {
            TwitchState.streamLock.writeLock().unlock();
        }
    }
}
