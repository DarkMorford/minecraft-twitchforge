package net.darkmorford.twitchforge.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

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
        return "/twitchforge help";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        sender.addChatMessage(new TextComponentString(TextFormatting.RED + "This command not yet implemented!"));
    }
}
