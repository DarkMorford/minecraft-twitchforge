package net.darkmorford.twitchforge.twitch;

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
}
