package net.darkmorford.twitchforge;

import net.darkmorford.twitchforge.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber
public class EventHandler
{
    @SubscribeEvent
    public static void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        // Find out who just logged in
        EntityPlayer player = event.player;
        String playerName = player.getDisplayNameString();

        // Send them a welcome message
        player.addChatComponentMessage(new TextComponentTranslation("misc.motd", playerName));
    }

    @SubscribeEvent
    public static void configChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(TwitchForge.MODID))
        {
            if (CommonProxy.config.hasChanged())
                CommonProxy.config.save();
        }
    }
}
