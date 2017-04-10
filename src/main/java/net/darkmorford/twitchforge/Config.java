package net.darkmorford.twitchforge;

import net.darkmorford.twitchforge.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

public class Config
{
    // Configuration categories
    private static final String CATEGORY_GENERAL = "general";

    // General configuration values
    public static String twitchApiKey = "tuf4r166cwig76d4dxwfo6lm41sfl6";
    public static String twitchChannel = "minecraft";
    public static int updateInterval = 5;

    public static void readConfig()
    {
        Configuration cfg = CommonProxy.config;

        try
        {
            cfg.load();
            initGeneralConfig(cfg);
        }
        catch (Exception e)
        {
            TwitchForge.logger.log(Level.ERROR, "Problem loading config file!", e);
        }
        finally
        {
            if (cfg.hasChanged())
                cfg.save();
        }
    }

    private static void initGeneralConfig(Configuration cfg)
    {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
        twitchApiKey = cfg.getString("twitchApiKey", CATEGORY_GENERAL, twitchApiKey, "Set the Twitch.tv API key");
        twitchChannel = cfg.getString("twitchChannel", CATEGORY_GENERAL, twitchChannel, "Set the twitch channel to watch");
        updateInterval = cfg.getInt("updateInterval", CATEGORY_GENERAL, updateInterval, 1, 60, "Set the update interval in minutes");
    }
}
