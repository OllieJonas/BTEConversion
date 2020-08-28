package uk.buildtheearth.conversionplugin.commands.meta;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public interface ICommand {

    CommandContext context();

    boolean executePlayer(Player player, String[] args);

    default boolean executeConsole(ConsoleCommandSender sender, String[] args) {
        return false;
    }

    default String buildPermission(String plugin, String command) {
        return String.format("%s.%s.%s", plugin, command, context().getName());
    }
}
