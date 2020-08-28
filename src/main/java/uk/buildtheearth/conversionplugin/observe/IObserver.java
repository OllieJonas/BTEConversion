package uk.buildtheearth.conversionplugin.observe;

import uk.buildtheearth.conversionplugin.job.step.ObservableStep;

public interface IObserver {
    <T extends ObservableStep<?>> void update(Observable observable, T step, Object... args);
}
