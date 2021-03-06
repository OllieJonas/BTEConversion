package uk.buildtheearth.conversionplugin.commands.meta;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@UtilityClass
public class CommandUtil {
    public boolean noPermission(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
        return false;
    }

    public boolean invalidUsage(CommandSender sender, String usage) {
        sender.sendMessage(ChatColor.RED + "Invalid usage! Correct usage: " + usage);
        return false;
    }

    public boolean invalidSubCommand(CommandSender sender, String subCommand) {
        sender.sendMessage(ChatColor.RED + "Invalid subcommand " + subCommand + "!");
        return false;
    }


    public boolean sendHelp(CommandSender sender, RootCommand<?> command) {
        command.getCommands().values().forEach(c -> sender.sendMessage(formattedHelp(c)));
        return false;
    }

    private String formattedHelp(SubCommand command) {
        return String.format(ChatColor.translateAlternateColorCodes('&', "&6%s &8- &e%s"), command.getInfo().getName(), command.getInfo().getDescription());
    }
}
