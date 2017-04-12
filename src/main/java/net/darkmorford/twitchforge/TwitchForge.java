package net.darkmorford.twitchforge;

import net.darkmorford.twitchforge.command.CommandMain;
import net.darkmorford.twitchforge.proxy.CommonProxy;
import net.darkmorford.twitchforge.task.TaskGetUserId;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

@Mod(modid = TwitchForge.MODID, name = TwitchForge.MODNAME, version = TwitchForge.VERSION, useMetadata = true, acceptableRemoteVersions = "*")
public class TwitchForge
{
    public static final String MODID = "twitchforge";
    public static final String MODNAME = "TwitchForge";
    public static final String VERSION = "1.10.2-0.0.1.0";

    @SidedProxy(clientSide = "net.darkmorford.twitchforge.proxy.ClientProxy", serverSide = "net.darkmorford.twitchforge.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Instance
    public static TwitchForge instance;

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // Sample things to do in this event handler:
        // Register blocks and items, register tileEntities, register entities, assign oreDict names

        logger = event.getModLog();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Sample things to do in this event handler:
        // Register world generators, register recipes, register event handlers, send IMC messages

        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        // Sample things to do in this event handler:
        // Mod compatibility, etc.

        proxy.postInit(event);
    }

    @EventHandler
    public void serverAboutToStart(FMLServerAboutToStartEvent event)
    {
        logger.log(Level.INFO, "Fetching Twitch channel ID");
        Thread t = new Thread(new TaskGetUserId());
        t.start();
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        // Sample things to do in this event handler:
        // Register commands

        event.registerServerCommand(new CommandMain());
    }
}
