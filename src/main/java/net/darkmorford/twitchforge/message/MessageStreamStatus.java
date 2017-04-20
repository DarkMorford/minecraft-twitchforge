package net.darkmorford.twitchforge.message;

import io.netty.buffer.ByteBuf;
import net.darkmorford.twitchforge.twitch.TwitchState;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageStreamStatus implements IMessage
{
    private boolean streamOnline;

    public MessageStreamStatus()
    {
        this.streamOnline = false;
    }

    public MessageStreamStatus(boolean streamOnline)
    {
        this.streamOnline = streamOnline;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        streamOnline = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(streamOnline);
    }

    public static class Handler implements IMessageHandler<MessageStreamStatus, IMessage>
    {
        @Override
        public IMessage onMessage(MessageStreamStatus message, MessageContext ctx)
        {

            // Do the actual message processing on the main Minecraft thread
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));

            return null;
        }

        private void handle(MessageStreamStatus message, MessageContext ctx)
        {
            TwitchState.isStreamOnline = message.streamOnline;
        }
    }
}
