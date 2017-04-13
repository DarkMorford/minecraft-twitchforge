package net.darkmorford.twitchforge.task;

import com.google.gson.*;
import net.darkmorford.twitchforge.Config;
import net.darkmorford.twitchforge.TwitchForge;
import net.darkmorford.twitchforge.twitch.TwitchState;
import net.darkmorford.twitchforge.twitch.User;
import net.darkmorford.twitchforge.utils.InstantDeserializer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.logging.log4j.Level;

import java.io.IOException;
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
        Request request = new Request.Builder()
                .url(twitchEndpoint)
                .header("Accept", "application/vnd.twitchtv.v5+json")
                .header("Client-ID", TwitchState.twitchApiKey)
                .build();

        // Create an HTTP client for the transaction
        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute())
        {
            // Convert the JSON response into data objects
            Gson gson = new GsonBuilder().registerTypeAdapter(Instant.class, new InstantDeserializer()).create();
            JsonParser jParser = new JsonParser();
            JsonObject rootObj = jParser.parse(response.body().charStream()).getAsJsonObject();

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
            TwitchForge.logger.log(Level.ERROR, e.getMessage());
        }
        finally
        {
            // Make sure this gets unlocked so other functions can use it
            TwitchState.channelLock.writeLock().unlock();
        }
    }
}
