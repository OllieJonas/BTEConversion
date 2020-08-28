package uk.buildtheearth.conversionplugin.job.step;

import uk.buildtheearth.conversionplugin.job.ObservableJob;

public abstract class ObservableStep<T extends ObservableJob> extends Step<T> {

    public ObservableStep(T job, String name, String message) {
        super(job, name, message);
    }
}
