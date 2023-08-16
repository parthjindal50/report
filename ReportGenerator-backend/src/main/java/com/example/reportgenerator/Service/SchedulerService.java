package com.example.reportgenerator.Service;

import com.example.reportgenerator.Model.Schedule;
import com.example.reportgenerator.Repository.ScheduleRepository;
import com.example.reportgenerator.Repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class responsible for managing scheduling-related operations.
 */
@Service
public class SchedulerService {

    private final ScheduleRepository scheduleRepository;
    private final ReportSchedulingService reportSchedulingService;
    private final TemplateRepository templateRepository;

    /**
     * Constructor to inject dependencies.
     *
     * @param scheduleRepository The repository for accessing schedules.
     * @param reportSchedulingService The service for scheduling tasks.
     * @param templateRepository The repository for accessing templates.
     */
    @Autowired
    public SchedulerService(ScheduleRepository scheduleRepository, ReportSchedulingService reportSchedulingService,
                            TemplateRepository templateRepository) {
        this.reportSchedulingService = reportSchedulingService;
        this.scheduleRepository = scheduleRepository;
        this.templateRepository = templateRepository;
    }

    /**
     * Retrieve all schedules.
     *
     * @return ResponseEntity containing a list of schedules and a status code.
     */
    public ResponseEntity<List> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return ResponseEntity.ok(schedules);
    }

    /**
     * Retrieve a specific schedule by its ID.
     *
     * @param id The ID of the schedule to retrieve.
     * @return ResponseEntity containing the retrieved schedule or a not found response.
     */
    public ResponseEntity<Schedule> getSchedule(Long id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        return schedule.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Create a new schedule.
     *
     * @param schedule The schedule to be created.
     * @return ResponseEntity containing the created schedule and a status code.
     */
    public ResponseEntity<Schedule> createSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);

        SchedulerBean bean = new SchedulerBean(templateRepository);
        bean.setSchedule(schedule);

        reportSchedulingService.scheduleATask(schedule.getId(), bean, schedule.getCronExpression());

        return ResponseEntity.ok(schedule);
    }

    /**
     * Update an existing schedule.
     *
     * @param id The ID of the schedule to be updated.
     * @param updatedSchedule The updated schedule data.
     * @return ResponseEntity containing the updated schedule or a not found response.
     */
    public ResponseEntity<Schedule> updateSchedule(Long id, Schedule updatedSchedule) {
        Optional<Schedule> existingSchedule = scheduleRepository.findById(id);
        if (existingSchedule.isPresent()) {
            Schedule schedule = existingSchedule.get();
            schedule.setName(updatedSchedule.getName());
            schedule.setEmail(updatedSchedule.getEmail());
            schedule.setCronExpression(updatedSchedule.getCronExpression());
            schedule.setTemplateId(updatedSchedule.getTemplateId());
            return ResponseEntity.ok(scheduleRepository.save(schedule));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete a schedule by its ID.
     *
     * @param id The ID of the schedule to be deleted.
     * @return ResponseEntity indicating the success of deletion or a not found response.
     */
    public ResponseEntity<Void> deleteSchedule(Long id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isPresent()) {
            scheduleRepository.delete(schedule.get());
            reportSchedulingService.removeScheduledTask(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
