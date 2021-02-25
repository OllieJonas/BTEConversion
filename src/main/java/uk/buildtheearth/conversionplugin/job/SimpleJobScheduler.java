package uk.buildtheearth.conversionplugin.job;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import uk.buildtheearth.conversionplugin.job.excep.JobFailedException;
import uk.buildtheearth.conversionplugin.job.excep.JobSchedulerException;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleJobScheduler implements JobScheduler {

    private final Queue<Job> jobQueue;

    private Job currJob;

    private final JavaPlugin plugin;

    private final BukkitScheduler bukkitScheduler = Bukkit.getScheduler();

    public SimpleJobScheduler(JavaPlugin plugin, int jobCapacity) {
        this.plugin = plugin;
        this.jobQueue = new ArrayBlockingQueue<>(jobCapacity);
    }

    @SuppressWarnings("FunctionalExpressionCanBeFolded")
    @Override
    public void scheduleConcurrentJob(Job job) throws JobFailedException {
        job.init();
        bukkitScheduler.runTaskAsynchronously(plugin, job::run);
    }

    @SuppressWarnings("FunctionalExpressionCanBeFolded")
    public void scheduleBlockingJob(Job job) throws JobSchedulerException, JobFailedException {
        job.init();

        if (currJob == null) {
            this.currJob = job;
        }

        bukkitScheduler.runTaskAsynchronously(plugin, job::run);
        // blockingJobs.remove(name);
    }
}
