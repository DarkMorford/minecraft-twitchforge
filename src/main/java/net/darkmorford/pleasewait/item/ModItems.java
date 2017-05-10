package net.darkmorford.pleasewait.item;

import net.darkmorford.pleasewait.PleaseWait;
import net.darkmorford.pleasewait.block.ModBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@ObjectHolder("pleasewait")
@Mod.EventBusSubscriber
public class ModItems
{
    // Regular Items
    public static final Item buffer = null;

    // ItemBlocks
    public static final Item streamDetector = null;

    @SideOnly(Side.CLIENT)
    public static void initModels()
    {
        ((ItemBuffer) buffer).initModel();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        // Regular Items
        event.getRegistry().register(new ItemBuffer());

        // ItemBlocks
        event.getRegistry().register(new ItemBlock(ModBlocks.streamDetector)
                .setRegistryName(PleaseWait.MODID + ":streamDetector")
                .setCreativeTab(PleaseWait.tabPleaseWait));
    }
}
