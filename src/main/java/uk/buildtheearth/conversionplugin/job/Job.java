package uk.buildtheearth.conversionplugin.job;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import uk.buildtheearth.conversionplugin.job.step.Step;
import uk.buildtheearth.conversionplugin.job.step.StepContext;

import java.util.LinkedList;
import java.util.Queue;

@Getter
public abstract class Job extends BukkitRunnable {

    protected final Player player;

    protected final String name;

    protected final Queue<Step<?>> steps;

    protected StepContext context;

    private State state;

    private boolean initialised;

    public Job(Player player, String name) {
        this.player = player;
        this.name = name;
        this.steps = new LinkedList<>();
    }

    protected abstract void registerSteps();

    protected abstract StepContext supplyContext();

    public Job init() {
        if (!initialised) {
            registerSteps();
            this.context = supplyContext();
            this.initialised = true;
        }
        return this;
    }

    protected void addStep(Step<?> step) {
        steps.add(step);
    }

    @Override
    public void run() {
        run0();
    }

    protected void run0() {
        StepContext prevContext = context;
        while (!steps.isEmpty()) {
            Step<?> curr = steps.poll();
            prevContext = curr.run(prevContext);
        }
    }

    public enum State {
        NEW,
        READY,
        RUNNING,
        TERMINATED,
        WAITING,
        INTERRUPTED,
        FAILED;
    }
}
