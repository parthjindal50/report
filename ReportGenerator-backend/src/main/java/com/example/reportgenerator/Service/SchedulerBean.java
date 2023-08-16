package com.example.reportgenerator.Service;

import com.example.reportgenerator.Model.ReportTemplate;
import com.example.reportgenerator.Model.Schedule;
import com.example.reportgenerator.Repository.TemplateRepository;
import com.example.reportgenerator.Utils.EmailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class responsible for executing scheduled tasks.
 */
@Service
public class SchedulerBean implements Runnable {

    private Schedule schedule;
    private final EmailSender emailSender = new EmailSender();

    private final TemplateRepository templateRepository;

    /**
     * Constructor to inject the TemplateRepository dependency.
     *
     * @param templateRepository The repository responsible for accessing report templates.
     */
    public SchedulerBean(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    /**
     * Get the schedule associated with this scheduler bean.
     *
     * @return The associated schedule.
     */
    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * Set the schedule for this scheduler bean.
     *
     * @param schedule The schedule to be set.
     */
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    /**
     * Execute the scheduled task.
     */
    @Override
    public void run() {
        // Retrieve the report template associated with the schedule.
        Optional<ReportTemplate> template = templateRepository.findById(schedule.getTemplateId());

        // TODO: Generate the report based on the template and schedule.

        // Send an email using the EmailSender utility.
        emailSender.sendEmail(schedule.getEmail());
    }
}
