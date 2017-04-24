package net.darkmorford.twitchforge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber
public class EventHandler
{
    public static void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        // Find out who just logged in
        EntityPlayer player = event.player;

        // Send them a welcome message
        player.addChatComponentMessage(new TextComponentTranslation("misc.motd", player.getDisplayNameString()));
    }
}
