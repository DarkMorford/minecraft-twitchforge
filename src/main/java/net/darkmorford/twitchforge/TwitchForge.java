package net.darkmorford.twitchforge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = TwitchForge.MODID, name = TwitchForge.MODNAME, version = TwitchForge.VERSION, useMetadata = true)
public class TwitchForge
{
    public static final String MODID = "twitchforge";
    public static final String MODNAME = "TwitchForge";
    public static final String VERSION = "1.10.2-0.0.1.0";

    @Instance
    public static TwitchForge instance;

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // Sample things to do in this event handler:
        // Register blocks and items, register tileEntities, register entities, assign oreDict names

        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Sample things to do in this event handler:
        // Register world generators, register recipes, register event handlers, send IMC messages
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        // Sample things to do in this event handler:
        // Mod compatibility, etc.
    }

    @EventHandler
    public void serverStart(FMLServerStartingEvent event)
    {
        // Sample things to do in this event handler:
        // Register commands
    }
}
