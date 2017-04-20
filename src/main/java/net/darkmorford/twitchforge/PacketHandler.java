package net.darkmorford.twitchforge;

import net.darkmorford.twitchforge.message.MessageStreamStatus;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(TwitchForge.MODID);

    public static void registerMessages()
    {
        // Packets sent from the server to the client
        INSTANCE.registerMessage(MessageStreamStatus.Handler.class, MessageStreamStatus.class, 0, Side.CLIENT);

        // Packets sent from the client to the server
    }
}
