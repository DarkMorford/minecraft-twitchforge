package net.darkmorford.twitchforge.item;

import net.darkmorford.twitchforge.TwitchForge;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;

@Mod.EventBusSubscriber
public class ModItems
{
    public static ItemGlitch glitch;

    public static void init()
    {
        glitch = new ItemGlitch();
    }

    @SideOnly(Side.CLIENT)
    public static void initModels()
    {
        glitch.initModel();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        TwitchForge.logger.log(Level.INFO, "Registering items");

        event.getRegistry().register(glitch);
    }
}
