package net.darkmorford.pleasewait.twitch;

import java.time.Instant;

public class Stream
{
    public Long    _id;
    public String  game;
    public Integer viewers;
    public Integer video_height;
    public Float   average_fps;
    public Float   delay;
    public Instant created_at;
    public Boolean is_playlist;
    public Preview preview;
    public Channel channel;

    Stream()
    {
    }
}
