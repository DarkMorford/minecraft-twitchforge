package net.darkmorford.pleasewait.proxy;

import net.darkmorford.twitchforge.block.ModBlocks;
import net.darkmorford.twitchforge.item.ModItems;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);

        ModBlocks.initModels();
        ModItems.initModels();
    }
}
