package net.darkmorford.twitchforge.proxy;

import net.darkmorford.twitchforge.item.ModItems;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);

        ModItems.initModels();
    }
}
