package uk.buildtheearth.conversionplugin.job;

import uk.buildtheearth.conversionplugin.job.Job;
import uk.buildtheearth.conversionplugin.job.excep.JobSchedulerException;
import uk.buildtheearth.conversionplugin.job.excep.JobFailedException;

public interface JobScheduler {

    void scheduleConcurrentJob(Job job) throws JobFailedException;

    void scheduleBlockingJob(Job job) throws JobSchedulerException, JobFailedException;
}
