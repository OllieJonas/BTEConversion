package uk.buildtheearth.conversionplugin.commands.meta;

import org.bukkit.entity.Player;

public class HelpCommand<T extends RootCommand<?>> extends SubCommand<T> {

    public HelpCommand(T root) {
        super(root);
    }

    @Override
    public void executePlayer(Player player, String[] args) {

    }

    @Override
    public CommandInfo supplyInfo() {
        return null;
    }
}
