package net.darkmorford.twitchforge.proxy;

import net.darkmorford.twitchforge.Config;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class CommonProxy
{
    public static Configuration config;

    public void preInit(FMLPreInitializationEvent event)
    {
        File configDir = event.getModConfigurationDirectory();
        config = new Configuration(new File(configDir.getPath(), "twitchforge.cfg"));
        Config.readConfig();
    }

    public void init(FMLInitializationEvent event)
    {
    }

    public void postInit(FMLPostInitializationEvent event)
    {
        if (config.hasChanged())
            config.save();
    }
}