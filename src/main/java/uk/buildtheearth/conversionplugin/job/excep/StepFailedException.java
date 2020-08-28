package uk.buildtheearth.conversionplugin.job.excep;

import uk.buildtheearth.conversionplugin.job.step.Step;

public class StepFailedException extends RuntimeException {
    public StepFailedException(Step<?> step) {
        throw new JobFailedException(step);
    }
}
