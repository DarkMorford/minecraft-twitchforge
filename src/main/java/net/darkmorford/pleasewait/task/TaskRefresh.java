package net.darkmorford.pleasewait.task;

import com.google.gson.*;
import net.darkmorford.pleasewait.PacketHandler;
import net.darkmorford.pleasewait.PleaseWait;
import net.darkmorford.pleasewait.message.MessageStreamStatus;
import net.darkmorford.pleasewait.twitch.Stream;
import net.darkmorford.pleasewait.twitch.TwitchState;
import net.darkmorford.pleasewait.utils.InstantDeserializer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.logging.log4j.Level;

import java.io.IOException;
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
            PleaseWait.logger.log(Level.ERROR, "Refresh called before User ID initialized");
            return;
        }

        String twitchEndpoint = String.format("https://api.twitch.tv/kraken/streams/%d", channelId);

        // Create a request object and set necessary headers
        Request request = new Request.Builder()
                .url(twitchEndpoint)
                .header("Accept", "application/vnd.twitchtv.v5+json")
                .header("Client-ID", TwitchState.twitchApiKey)
                .build();

        // Lock the stream data while the request is in progress
        TwitchState.streamLock.writeLock().lock();

        // Create an HTTP client for the transaction
        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute())
        {
            // Convert the JSON response into a data object
            Gson gson = new GsonBuilder().registerTypeAdapter(Instant.class, new InstantDeserializer()).create();
            JsonParser jParser = new JsonParser();
            JsonObject rootObj = jParser.parse(response.body().charStream()).getAsJsonObject();

            Stream streamStatus = gson.fromJson(rootObj.get("stream"), Stream.class);

            if (streamStatus == null)
            {
                TwitchState.isStreamOnline = false;

                FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList()
                        .sendChatMsg(new TextComponentTranslation("stream.offline", channelName));

                PacketHandler.INSTANCE.sendToAll(new MessageStreamStatus(false));
            }
            else
            {
                if (!TwitchState.isStreamOnline)
                {
                    // TODO: Signal when the stream transitions to online
                    PleaseWait.logger.log(Level.INFO, "Streamer has just gone online!");
                }

                TwitchState.isStreamOnline = true;

                TwitchState.streamGame = streamStatus.game;
                TwitchState.streamStartTime = streamStatus.created_at;
                TwitchState.streamTitle = streamStatus.channel.status;
                TwitchState.streamUri = streamStatus.channel.url;

                FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList()
                        .sendChatMsg(new TextComponentTranslation("stream.online", channelName, streamStatus.channel.status));

                PacketHandler.INSTANCE.sendToAll(new MessageStreamStatus(true, TwitchState.streamTitle));
            }
        }
        catch (IOException e)
        {
            PleaseWait.logger.log(Level.ERROR, e.getMessage());
        }
        catch (JsonSyntaxException e)
        {
            PleaseWait.logger.log(Level.ERROR, e.getMessage());
        }
        finally
        {
            TwitchState.streamLock.writeLock().unlock();
        }
    }
}
