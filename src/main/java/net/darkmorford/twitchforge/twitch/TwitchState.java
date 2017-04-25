package net.darkmorford.twitchforge.twitch;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import java.net.URI;
import java.time.Instant;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TwitchState
{
    public static final String twitchApiKey = "tuf4r166cwig76d4dxwfo6lm41sfl6";

    public static Boolean isStreamOnline = false;

    public static ReadWriteLock channelLock = new ReentrantReadWriteLock();
    public static Long          channelId = 0L;
    public static String        channelDisplayName;

    public static ReadWriteLock streamLock = new ReentrantReadWriteLock();
    public static String        streamGame;
    public static Instant       streamStartTime;
    public static String        streamTitle;
    public static URI           streamUri;

    public static ITextComponent getStatusString()
    {
        // Make a copy of the channel name for display
        channelLock.readLock().lock();
        String channelName = channelDisplayName;
        channelLock.readLock().unlock();

        if (isStreamOnline)
        {
            // Copy the stream title
            streamLock.readLock().lock();
            String streamName = streamTitle;
            streamLock.readLock().unlock();

            // Send the "online" message
            return new TextComponentTranslation("stream.online", channelName, streamName);
        }
        else
        {
            // Send the "offline" message
            return new TextComponentTranslation("stream.offline", channelName);
        }
    }
}
