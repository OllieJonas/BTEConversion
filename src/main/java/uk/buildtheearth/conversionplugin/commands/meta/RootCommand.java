package uk.buildtheearth.conversionplugin.commands.meta;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.experimental.Delegate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static uk.buildtheearth.conversionplugin.commands.meta.CommandUtil.*;

public abstract class RootCommand<T extends JavaPlugin> implements CommandExecutor, ICommand {

    protected final T plugin;

    @Delegate @Getter
    protected final CommandInfo info;

    @Getter
    private final Map<String, SubCommand<?>> commands;

    public RootCommand(T plugin) {
        this.plugin = plugin;
        this.info = supplyInfo();
        this.commands = new HashMap<>();

        Preconditions.checkNotNull(info);

        addCommands();
    }

    protected abstract void addCommands();

    protected abstract void executeNoArgs(Player player);

    protected void executeNoArgs(ConsoleCommandSender sender) {

    }

    protected void addCommand(SubCommand<?> command) {
        commands.put(command.getInfo().getName(), command);
        command.getInfo().getAliases().forEach(e -> commands.put(e, command));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!hasPermission(sender, info.getPermission()))
            return noPermission(sender);

        if (args.length == 0)
            return executeNoArgs(sender);

        Optional<SubCommand<?>> cmdOptional = findCommand(args[0]);

        if (!cmdOptional.isPresent())
            return invalidSubCommand(sender, args[0]);

        SubCommand<?> cmd = cmdOptional.get();

        if (!hasPermission(sender, cmd.getInfo().getPermission()))
            return noPermission(sender);

        String[] newArgs = Arrays.copyOfRange(args, 1, args.length);

        if (!cmd.getInfo().inRange(newArgs.length))
            return invalidUsage(sender, cmd.getInfo().getUsage());

        return execute(sender, cmd, newArgs);
    }

    private Optional<SubCommand<?>> findCommand(String name) {
        return commands.values()
                .stream()
                .filter(c -> c.getInfo().getName().equalsIgnoreCase(name) ||
                        c.getInfo().getAliases().stream()
                                .anyMatch(s -> s.equalsIgnoreCase(name)))
                .findFirst();
    }

    private boolean executeNoArgs(CommandSender sender) {
        if (sender instanceof Player)
            executeNoArgs((Player) sender);
        else
            executeNoArgs((ConsoleCommandSender) sender);
        return true;
    }

    private boolean execute(CommandSender sender, SubCommand<?> command, String[] args) {
        if (sender instanceof Player)
            command.executePlayer((Player) sender, args);
        else if (sender instanceof ConsoleCommandSender)
            command.executeConsole((ConsoleCommandSender) sender, args);
        else
            throw new IllegalArgumentException("i have no idea how someone managed to hit this");

        return true;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean hasPermission(CommandSender sender, String permission) {
        return permission == null || permission.equals("") || sender.hasPermission(permission);
    }
}
