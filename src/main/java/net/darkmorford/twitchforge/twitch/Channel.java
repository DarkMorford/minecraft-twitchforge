package net.darkmorford.twitchforge.twitch;

import java.net.URI;
import java.time.Instant;

public class Channel
{
    public Long    _id;
    public String  broadcaster_language;
    public Instant created_at;
    public String  display_name;
    public Long    followers;
    public String  game;
    public String  language;
    public URI     logo;
    public Boolean mature;
    public String  name;
    public Boolean partner;
    public String  profile_banner;
    public String  profile_banner_background_color;
    public String  status;
    public Instant updated_at;
    public URI     url;
    public String  video_banner;
    public Long    views;

    Channel()
    {
    }
}
