package uk.buildtheearth.conversionplugin.job;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import uk.buildtheearth.conversionplugin.observe.Observable;
import uk.buildtheearth.conversionplugin.observe.SimpleObservable;

public abstract class ObservableJob extends Job {

    private final Observable observable;

    public ObservableJob(Player player, String name) {
        super(player, name);
        this.observable = new SimpleObservable();
    }

    @Override
    public void run0() {
        Bukkit.broadcastMessage("called ObservableJob.doRun");
        super.run0();
//        while (!steps.isEmpty()) {
//            if (!(steps.peek() instanceof ObservableStep))
//                throw new IllegalStateException("Trying to observe a non-observable step!");
//
//            ObservableStep<> curr = (ObservableStep) steps.poll();
//            observable.notifyAllObservers(curr);
//            curr.run();
//        }
    }

}
