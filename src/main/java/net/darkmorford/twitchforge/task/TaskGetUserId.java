package net.darkmorford.twitchforge.task;

import com.google.gson.*;
import net.darkmorford.twitchforge.Config;
import net.darkmorford.twitchforge.TwitchForge;
import net.darkmorford.twitchforge.twitch.TwitchState;
import net.darkmorford.twitchforge.twitch.User;
import net.darkmorford.twitchforge.utils.InstantDeserializer;
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

public class TaskGetUserId implements Runnable
{
    @Override
    public void run()
    {
        // https://dev.twitch.tv/docs/v5/guides/using-the-twitch-api/

        // Lock the channel information until we have a chance to update it
        TwitchState.channelLock.writeLock().lock();

        // Build the URI for the data we want
        String twitchEndpoint = String.format("https://api.twitch.tv/kraken/users?login=%s", Config.twitchChannel);

        // Create a request object and set necessary headers
        HttpGet request = new HttpGet(twitchEndpoint);
        request.addHeader("Accept", "application/vnd.twitchtv.v5+json");
        request.addHeader("Client-ID", TwitchState.twitchApiKey);

        // Create an HTTP client for the transaction
        CloseableHttpClient client = HttpClients.createDefault();
        try (CloseableHttpResponse response = client.execute(request))
        {
            // Perform the network request
            HttpEntity responseBody = response.getEntity();

            // Get the JSON string from the response
            String responseString = IOUtils.toString(responseBody.getContent(), StandardCharsets.UTF_8);

            // Convert the JSON into data objects
            Gson gson = new GsonBuilder().registerTypeAdapter(Instant.class, new InstantDeserializer()).create();
            JsonParser jParser = new JsonParser();
            JsonObject rootObj = jParser.parse(responseString).getAsJsonObject();

            User[] users = gson.fromJson(rootObj.get("users"), User[].class);

            User mainUser = users[0];
            TwitchState.channelId = mainUser._id;
            TwitchState.channelDisplayName = mainUser.display_name;
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
            // Make sure this gets unlocked so other functions can use it
            TwitchState.channelLock.writeLock().unlock();
        }
    }
}
