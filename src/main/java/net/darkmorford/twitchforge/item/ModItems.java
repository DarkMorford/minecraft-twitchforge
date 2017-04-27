package net.darkmorford.twitchforge.item;

import net.darkmorford.twitchforge.TwitchForge;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;

@ObjectHolder("twitchforge")
@Mod.EventBusSubscriber
public class ModItems
{
    public static final Item glitch = null;

    @SideOnly(Side.CLIENT)
    public static void initModels()
    {
        ((ItemGlitch)glitch).initModel();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().register(new ItemGlitch());
    }
}
