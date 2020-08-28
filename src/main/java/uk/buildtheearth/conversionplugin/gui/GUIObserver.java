package uk.buildtheearth.conversionplugin.gui;

import org.bukkit.Bukkit;
import uk.buildtheearth.conversionplugin.job.ObservableJob;
import uk.buildtheearth.conversionplugin.job.step.ObservableStep;
import uk.buildtheearth.conversionplugin.observe.IObserver;
import uk.buildtheearth.conversionplugin.observe.Observable;

public class GUIObserver implements IObserver {

    private final ObservableJob job;

    public GUIObserver(ObservableJob job) {
        this.job = job;
    }

    @Override
    public <T extends ObservableStep<?>> void update(Observable observable, T step, Object... args) {

    }
}
