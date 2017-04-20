package net.darkmorford.twitchforge.command;

import com.google.common.collect.Lists;
import net.darkmorford.twitchforge.TwitchForge;
import net.darkmorford.twitchforge.task.TaskRefresh;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import org.apache.logging.log4j.Level;

import java.util.Arrays;
import java.util.List;

public class CommandMain extends CommandBase
{
    private final List<String> aliases;

    public CommandMain()
    {
        aliases = Lists.newArrayList(TwitchForge.MODID, "tf");
    }

    @Override
    public String getCommandName()
    {
        return "twitchforge";
    }

    @Override
    public List<String> getCommandAliases()
    {
        return aliases;
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "/twitchforge refresh";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        // Check to make sure we got a subcommand
        if (args.length == 0)
            throw new WrongUsageException("commands.generic.usage", getCommandUsage(sender));

        String subcommand = args[0];
        List<String> subArgs = Arrays.asList(args).subList(1, args.length);

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
