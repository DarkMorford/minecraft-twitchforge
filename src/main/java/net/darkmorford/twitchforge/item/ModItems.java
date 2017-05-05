package net.darkmorford.twitchforge.item;

import net.darkmorford.twitchforge.TwitchForge;
import net.darkmorford.twitchforge.block.ModBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@ObjectHolder("twitchforge")
@Mod.EventBusSubscriber
public class ModItems
{
    // Regular Items
    public static final Item glitch = null;

    // ItemBlocks
    public static final Item streamDetector = null;

    @SideOnly(Side.CLIENT)
    public static void initModels()
    {
        ((ItemGlitch)glitch).initModel();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        // Regular Items
        event.getRegistry().register(new ItemGlitch());

        // ItemBlocks
        event.getRegistry().register(new ItemBlock(ModBlocks.streamDetector)
                .setRegistryName("twitchforge:streamDetector")
                .setCreativeTab(TwitchForge.tabTwitchForge));
    }
}
