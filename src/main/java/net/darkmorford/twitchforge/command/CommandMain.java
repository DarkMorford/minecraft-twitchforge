package net.darkmorford.twitchforge.command;

import net.darkmorford.twitchforge.TwitchForge;
import net.darkmorford.twitchforge.task.TaskRefresh;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import org.apache.logging.log4j.Level;

public class CommandMain extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "twitchforge";
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "/twitchforge";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (!server.getEntityWorld().isRemote)
        {
            TwitchForge.logger.log(Level.INFO, "Starting refresh thread");
            Thread t = new Thread(new TaskRefresh());
            t.start();
        }
        else
        {
            sender.addChatMessage(new TextComponentString(TextFormatting.RED + "That command can only be performed from the server!"));
        }
    }
}
