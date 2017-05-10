package net.darkmorford.pleasewait;

import net.darkmorford.pleasewait.proxy.CommonProxy;
import net.darkmorford.pleasewait.twitch.TwitchState;
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

        // Inform them of the channel status
        if (TwitchState.isStreamOnline)
        {
            player.addChatComponentMessage(new TextComponentTranslation("stream.online", TwitchState.channelDisplayName, TwitchState.streamTitle));
        }
        else
        {
            player.addChatComponentMessage(new TextComponentTranslation("stream.offline", TwitchState.channelDisplayName));
        }
    }

    @SubscribeEvent
    public static void configChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(PleaseWait.MODID))
        {
            if (CommonProxy.config.hasChanged())
                CommonProxy.config.save();
        }
    }
}
