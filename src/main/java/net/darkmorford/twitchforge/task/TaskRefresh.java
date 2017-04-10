package net.darkmorford.twitchforge.task;

import com.google.gson.Gson;
import net.darkmorford.twitchforge.Config;
import net.darkmorford.twitchforge.TwitchForge;
import net.darkmorford.twitchforge.twitch.Stream;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.Level;

import java.io.IOException;

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

            // Convert the JSON response into an object
            Gson gson = new Gson();
            Stream streamStatus = gson.fromJson(responseBody.toString(), Stream.class);

            // Done with the web response, go ahead and close it
            response.close();
        }
        catch (IOException e)
        {
            TwitchForge.logger.log(Level.ERROR, e.getMessage());
        }
    }
}
