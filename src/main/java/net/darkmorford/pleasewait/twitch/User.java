package net.darkmorford.pleasewait.twitch;

import java.net.URI;
import java.time.Instant;

public class User
{
    public Long    _id;
    public String  bio;
    public Instant created_at;
    public String  display_name;
    public URI     logo;
    public String  name;
    public String  type;
    public Instant updated_at;

    User()
    {
    }
}
