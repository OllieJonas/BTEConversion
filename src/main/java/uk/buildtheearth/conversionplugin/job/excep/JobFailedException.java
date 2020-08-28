package uk.buildtheearth.conversionplugin.job.excep;

import uk.buildtheearth.conversionplugin.job.step.Step;

public class JobFailedException extends RuntimeException {

    public JobFailedException(Step<?> step) {

    }
}
