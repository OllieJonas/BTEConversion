package uk.buildtheearth.conversionplugin.convert;

import uk.buildtheearth.conversionplugin.job.step.ObservableStep;
import uk.buildtheearth.conversionplugin.job.step.StepContext;
import uk.buildtheearth.conversionplugin.job.excep.StepFailedException;

public class PrintCoordsStep extends ObservableStep<WorldConversionJob> {

    public PrintCoordsStep(WorldConversionJob job) {
        super(job, "Print coords", "Printing coordinates...");
    }

    @Override
    public StepContext run(StepContext context) throws StepFailedException {
        String test = context.getValue("test");
        job.getPlayer().sendMessage(test);
        return context;
    }
}
