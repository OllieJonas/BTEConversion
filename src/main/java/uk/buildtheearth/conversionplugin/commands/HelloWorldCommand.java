package uk.buildtheearth.conversionplugin.commands;

import org.bukkit.entity.Player;
import uk.buildtheearth.conversionplugin.commands.meta.CommandInfo;
import uk.buildtheearth.conversionplugin.commands.meta.SubCommand;

public class HelloWorldCommand extends SubCommand<ConvertCommand> {

    public HelloWorldCommand(ConvertCommand root) {
        super(root);
    }

    @Override
    public void executePlayer(Player player, String[] args) {
        player.sendMessage("hello world!");
    }

    @Override
    public CommandInfo supplyInfo() {
        return CommandInfo.builder().name("helloworld").build();
    }
}
