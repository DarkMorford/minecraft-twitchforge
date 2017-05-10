package net.darkmorford.pleasewait.block;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@ObjectHolder("pleasewait")
@Mod.EventBusSubscriber
public class ModBlocks
{
    public static final Block streamDetector = null;

    @SideOnly(Side.CLIENT)
    public static void initModels()
    {
        ((BlockStreamDetector)streamDetector).initModel();
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().register(new BlockStreamDetector());
    }
}
