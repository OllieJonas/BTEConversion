package uk.buildtheearth.conversionplugin.commands;

import org.bukkit.entity.Player;
import uk.buildtheearth.conversionplugin.ConversionPlugin;
import uk.buildtheearth.conversionplugin.commands.meta.CommandInfo;
import uk.buildtheearth.conversionplugin.commands.meta.RootCommand;

public class ConvertCommand extends RootCommand<ConversionPlugin> {

    public ConvertCommand(ConversionPlugin plugin) {
        super(plugin);
    }

    @Override
    public CommandInfo supplyInfo() {
        return CommandInfo.builder().name("convert").build();
    }

    @Override
    protected void addCommands() {
        addCommand(new StartCommand(this));
    }

    @Override
    protected void executeNoArgs(Player player) {
        player.sendMessage("no args!");
    }
}
