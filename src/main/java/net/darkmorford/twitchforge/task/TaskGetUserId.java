package net.darkmorford.twitchforge.task;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.darkmorford.twitchforge.Config;
import net.darkmorford.twitchforge.TwitchForge;
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

        // Build the URI for the data we want
        String twitchEndpoint = String.format("https://api.twitch.tv/kraken/users?login=%s", Config.twitchChannel);

        // Create a request object and set necessary headers
        HttpGet request = new HttpGet(twitchEndpoint);
        request.addHeader("Accept", "application/vnd.twitchtv.v5+json");
        request.addHeader("Client-ID", Config.twitchApiKey);

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

            int totalUsers = gson.fromJson(rootObj.get("_total"), int.class);
            User[] users = gson.fromJson(rootObj.get("users"), User[].class);
        }
        catch (IOException e)
        {
            TwitchForge.logger.log(Level.ERROR, e.getMessage());
        }
    }
}
