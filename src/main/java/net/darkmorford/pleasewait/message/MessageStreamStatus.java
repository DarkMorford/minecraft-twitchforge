package net.darkmorford.pleasewait.message;

import io.netty.buffer.ByteBuf;
import net.darkmorford.pleasewait.twitch.TwitchState;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageStreamStatus implements IMessage
{
    private boolean streamOnline;
    private String  streamTitle;

    public MessageStreamStatus()
    {
        this(false, "");
    }

    public MessageStreamStatus(boolean streamOnline)
    {
        this(streamOnline, "");
    }

    public MessageStreamStatus(boolean streamOnline, String streamTitle)
    {
        this.streamOnline = streamOnline;
        this.streamTitle  = streamTitle;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        streamOnline = buf.readBoolean();

        int titleLength = buf.readInt();
        streamTitle = buf.readBytes(titleLength).toString();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(streamOnline);

        buf.writeInt(streamTitle.length());
        buf.writeBytes(streamTitle.getBytes());
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
            TwitchState.streamTitle = message.streamTitle;
        }
    }
}
