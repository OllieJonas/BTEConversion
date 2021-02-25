package uk.buildtheearth.conversionplugin.commands.meta;

import lombok.Getter;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public abstract class SubCommand<T extends RootCommand<?>> implements ICommand {

    protected T root;

    @Getter
    protected CommandInfo info;

    public SubCommand(T root) {
        this.root = root;
        this.info = supplyInfo();
    }

    public abstract void executePlayer(Player player, String[] args);

    public void executeConsole(ConsoleCommandSender sender, String[] args) {

    }

    public String buildPermission(String plugin, String command) {
        return String.format("%s.%s.%s", plugin, command, getInfo().getName());
    }
}
