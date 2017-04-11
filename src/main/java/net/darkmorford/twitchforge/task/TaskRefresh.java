package net.darkmorford.twitchforge.task;

import com.google.gson.Gson;
import net.darkmorford.twitchforge.Config;
import net.darkmorford.twitchforge.TwitchForge;
import net.darkmorford.twitchforge.twitch.Stream;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.Level;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TaskRefresh implements Runnable
{
    @Override
    public void run()
    {
        // Build the URI for the data we want
        String twitchEndpoint = String.format("https://api.twitch.tv/kraken/streams/%s", Config.twitchChannel);

        // Create a request object and set necessary headers
        HttpGet request = new HttpGet(twitchEndpoint);
        request.addHeader("Accept", "application/vnd.twitchtv.v5+json");
        request.addHeader("Client-ID", Config.twitchApiKey);

        // Create an HTTP client for the transaction
        CloseableHttpClient client = HttpClients.createDefault();
        try
        {
            // Perform the network request
            CloseableHttpResponse response = client.execute(request);
            HttpEntity responseBody = response.getEntity();

            // Get the JSON string from the response
            String responseString = IOUtils.toString(responseBody.getContent(), StandardCharsets.UTF_8);

            // Convert the JSON response into an object
            Gson gson = new Gson();
            Stream streamStatus = gson.fromJson(responseString, Stream.class);

            // Done with the web response, go ahead and close it
            response.close();
        }
        catch (IOException e)
        {
            TwitchForge.logger.log(Level.ERROR, e.getMessage());
        }
    }
}
