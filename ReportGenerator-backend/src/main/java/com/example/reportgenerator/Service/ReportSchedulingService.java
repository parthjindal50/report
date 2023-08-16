package com.example.reportgenerator.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

/**
 * Service class responsible for scheduling and managing report generation tasks.
 */
@Service
public class ReportSchedulingService {

    @Autowired
    private TaskScheduler taskScheduler;

    // Map to store scheduled tasks using their job IDs.
    private Map<Long, ScheduledFuture<?>> jobsMap = new HashMap<>();

    /**
     * Schedule a task to run periodically based on a cron expression.
     *
     * @param jobId           The ID of the job/task being scheduled.
     * @param tasklet         The task to be executed.
     * @param cronExpression  The cron expression defining the scheduling pattern.
     */
    public void scheduleATask(Long jobId, Runnable tasklet, String cronExpression) {
        System.out.println("Scheduling task with job id: " + jobId + " and cron expression: " + cronExpression);

        // Schedule the task using the provided cron expression and time zone.
        ScheduledFuture<?> scheduledTask = taskScheduler.schedule(
                tasklet,
                new CronTrigger(cronExpression, TimeZone.getTimeZone(TimeZone.getDefault().getID()))
        );

        // Store the scheduled task in the jobsMap using its job ID.
        jobsMap.put(jobId, scheduledTask);
    }

    /**
     * Remove a scheduled task based on its job ID.
     *
     * @param jobId The ID of the job/task to be removed.
     */
    public void removeScheduledTask(Long jobId) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(jobId);

        if (scheduledTask != null) {
            // Cancel the scheduled task and remove it from the jobsMap.
            scheduledTask.cancel(true);
            jobsMap.put(jobId, null);
        }
    }
}
