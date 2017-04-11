package net.darkmorford.twitchforge.twitch;

import java.net.URI;
import java.time.Instant;

public class TwitchState
{
    public static final String twitchApiKey = "tuf4r166cwig76d4dxwfo6lm41sfl6";

    public static Boolean isStreamOnline = false;

    public static Long    channelId;
    public static String  channelDisplayName;

    public static String  streamGame;
    public static Instant streamStartTime;
    public static String  streamTitle;
    public static URI     streamUri;
}
