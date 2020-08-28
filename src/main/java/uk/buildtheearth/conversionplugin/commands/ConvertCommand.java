package uk.buildtheearth.conversionplugin.commands;

import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import uk.buildtheearth.conversionplugin.commands.meta.ICommand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static uk.buildtheearth.conversionplugin.commands.meta.CommandUtil.*;

public class ConvertCommand implements CommandExecutor {

    private final String name;

    private final JavaPlugin plugin;

    @Getter
    private final Map<String, ICommand> commands;

    public ConvertCommand(String name, JavaPlugin plugin) {
        this.name = name;
        this.plugin = plugin;
        this.commands = new HashMap<>();

        addCommands();
    }

    private void addCommands() {
        addCommand(new StartCommand());
    }

    private void addCommand(ICommand command) {
        commands.put(command.context().getName(), command);
        command.context().getAliases().forEach(e -> commands.put(e, command));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("conversion.convert"))
            return noPermission(sender);

        if (args.length == 0)
            return sendHelp(sender, this);

        Optional<ICommand> cmdOptional = findCommand(args[0]);

        if (!cmdOptional.isPresent())
            return invalidSubCommand(sender, args[0]);

        ICommand cmd = cmdOptional.get();

        if (!sender.hasPermission(cmd.context().getPermission())) {
            return noPermission(sender);
        }

        String[] newArgs = Arrays.copyOfRange(args, 1, args.length);

        if (!cmd.context().inRange(newArgs.length))
            return invalidUsage(sender, cmd.context().getUsage());


        return sender instanceof Player ?
                cmd.executePlayer((Player) sender, newArgs) :
                cmd.executeConsole((ConsoleCommandSender) sender, newArgs);
    }

    private Optional<ICommand> findCommand(String name) {
        return commands.values()
                .stream()
                .filter(c -> c.context().getName().equalsIgnoreCase(name) ||
                        c.context().getAliases().stream()
                                .anyMatch(s -> s.equalsIgnoreCase(name)))
                .findFirst();
    }
}
