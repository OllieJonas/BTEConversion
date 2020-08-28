package uk.buildtheearth.conversionplugin.convert;

import org.bukkit.entity.Player;
import uk.buildtheearth.conversionplugin.job.ObservableJob;
import uk.buildtheearth.conversionplugin.job.step.StepContext;

public class WorldConversionJob extends ObservableJob {

    private final int x;
    private final int z;

    public WorldConversionJob(Player player, int x, int z) {
        super(player, "World Conversion");
        this.x = x;
        this.z = z;
    }

    @Override
    protected void registerSteps() {
        addStep(new CoordsToRegionFileStep(this));
        addStep(new PrintCoordsStep(this));
    }

    @Override
    protected StepContext supplyContext() {
        return new StepContext()
                .entry("x", Integer.class, x)
                .entry("z", Integer.class, z)
                .entry("translated_x", Double.class)
                .entry("translated_z", Double.class);
    }
}
