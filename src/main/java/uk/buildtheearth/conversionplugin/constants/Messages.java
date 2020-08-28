package uk.buildtheearth.conversionplugin.constants;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@UtilityClass
public class Messages {

    public void invalidArgument(Player player, String arg) {
        player.sendMessage(ChatColor.RED + "Invalid Argument " + arg + "!");
    }
}
