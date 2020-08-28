package uk.buildtheearth.conversionplugin.job.excep;

public class JobSchedulerException extends Exception {

    public JobSchedulerException(String job, int queuePos) {
        super(String.format("A %s job already scheduled! You are currently position %d in the queue", job, queuePos));
    }
    public JobSchedulerException() {
        super("Job already scheduled!");
    }
}
