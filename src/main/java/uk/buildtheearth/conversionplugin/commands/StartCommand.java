package uk.buildtheearth.conversionplugin.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import uk.buildtheearth.conversionplugin.ConversionPlugin;
import uk.buildtheearth.conversionplugin.commands.meta.CommandInfo;
import uk.buildtheearth.conversionplugin.commands.meta.SubCommand;
import uk.buildtheearth.conversionplugin.convert.WorldConversionJob;
import uk.buildtheearth.conversionplugin.job.excep.JobSchedulerException;
import uk.buildtheearth.conversionplugin.job.excep.JobFailedException;
import uk.buildtheearth.conversionplugin.util.ClassUtils;


public class StartCommand extends SubCommand<ConvertCommand> {

    public StartCommand(ConvertCommand root) {
        super(root);
    }

    @Override
    public CommandInfo supplyInfo() {
        return CommandInfo.builder()
                .name("start")
                .description("Start conversion")
                .permission("conversion.convert.start")
                .usage("start <x> <z>")
                .range(CommandInfo.ArgRange.of(2, 2))
                .build();
    }

    @Override
    public void executePlayer(Player player, String[] args) {
        Integer x = ClassUtils.stringToClass(args[0], Integer.class);
        Integer z = ClassUtils.stringToClass(args[1], Integer.class);

        if (x != null && z != null) {
            try {
                ConversionPlugin.getJobScheduler().scheduleBlockingJob(new WorldConversionJob(player, x, z));
            } catch (JobSchedulerException e) {
                player.sendMessage(ChatColor.RED + "Error: A conversion job is already scheduled! Please try again later...");
            } catch (JobFailedException e) {
                player.sendMessage(ChatColor.RED + "Error: The current job failed! Please contact an admin!");
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unused")
    @AllArgsConstructor @Getter
    private static class SimpleRange {
        private final int x;
        private final int z;
    }
}
