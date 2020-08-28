package uk.buildtheearth.conversionplugin.gui;

import org.bukkit.plugin.java.JavaPlugin;

public class ProgressGUIManager {

    private final JavaPlugin plugin;

    private static ProgressGUI activeGUI;

    public ProgressGUIManager(JavaPlugin plugin) {
        this.plugin = plugin;

        registerEvents();
    }

    private void registerEvents() {
        plugin.getServer().getPluginManager().registerEvents(new ClickEvent(), plugin);
    }
}
