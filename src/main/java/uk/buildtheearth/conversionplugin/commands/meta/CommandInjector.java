package uk.buildtheearth.conversionplugin.commands.meta;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

public class CommandInjector {

    public void injectCommand(RootCommand<?> command) {
        CommandMap map = Bukkit.getServer().getCommandMap();

    }
}
