package uk.buildtheearth.conversionplugin.convert;

import uk.buildtheearth.conversionplugin.job.step.ObservableStep;
import uk.buildtheearth.conversionplugin.job.step.StepContext;
import uk.buildtheearth.conversionplugin.job.excep.StepFailedException;

public class CoordsToRegionFileStep extends ObservableStep<WorldConversionJob> {

    public CoordsToRegionFileStep(WorldConversionJob job) {
        super(job, "Coordinates to Region File", "Converting Coordinates to Region File");
    }

    @Override
    public StepContext run(StepContext context) throws StepFailedException {
        int x = toRegionCoords(context.getValue("x"));
        int z = toRegionCoords(context.getValue("z"));
        context.updateValue("translated_x", x);
        context.updateValue("translated_z", z);
        context.updateValue("region_file", toRegionFile(x, z));
        return context;
    }

    private int toRegionCoords(int value) {
         return value >> 5;
    }

    private String toRegionFile(int translated_x, int translated_z) {
        return String.format("");
    }
}
