package uk.buildtheearth.conversionplugin;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import uk.buildtheearth.conversionplugin.commands.ConvertCommand;
import uk.buildtheearth.conversionplugin.gui.ProgressGUIManager;
import uk.buildtheearth.conversionplugin.job.JobScheduler;
import uk.buildtheearth.conversionplugin.job.SimpleJobScheduler;

public final class ConversionPlugin extends JavaPlugin {

    @Getter
    private static JobScheduler jobScheduler;

    @Getter
    private static ProgressGUIManager guiManager;

    @Override
    public void onEnable() {
        jobScheduler = new SimpleJobScheduler(this, 15);
        guiManager = new ProgressGUIManager(this);

        this.getCommand("convert").setExecutor(new ConvertCommand("convert", this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
