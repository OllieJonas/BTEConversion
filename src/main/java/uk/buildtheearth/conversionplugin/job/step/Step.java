package uk.buildtheearth.conversionplugin.job.step;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import uk.buildtheearth.conversionplugin.job.Job;
import uk.buildtheearth.conversionplugin.job.excep.StepFailedException;

@RequiredArgsConstructor
@Getter
public abstract class Step<T extends Job> {

    @NonNull protected T job;

    @NonNull protected final String name;
    @NonNull protected final String message;

    public abstract StepContext run(StepContext prevContext) throws StepFailedException;
}
