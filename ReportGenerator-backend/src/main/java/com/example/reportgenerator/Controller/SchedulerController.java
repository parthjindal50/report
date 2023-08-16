package com.example.reportgenerator.Controller;

import com.example.reportgenerator.Model.Schedule;
import com.example.reportgenerator.Service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class responsible for handling HTTP requests related to schedules.
 */
@RestController
@RequestMapping("/schedule")
public class SchedulerController {

    private final SchedulerService schedulerService;

    /**
     * Constructor to inject the SchedulerService dependency.
     *
     * @param schedulerService The service responsible for managing schedules.
     */
    @Autowired
    public SchedulerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    /**
     * Endpoint to retrieve all schedules.
     *
     * @return ResponseEntity containing a list of schedules and a status code.
     */
    @GetMapping
    public ResponseEntity<List> getAllSchedules() {
        return schedulerService.getAllSchedules();
    }

    /**
     * Endpoint to retrieve a specific schedule by its ID.
     *
     * @param id The ID of the schedule to retrieve.
     * @return ResponseEntity containing the retrieved schedule and a status code.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable Long id) {
        return schedulerService.getSchedule(id);
    }

    /**
     * Endpoint to create a new schedule.
     *
     * @param schedule The schedule data provided in the request body.
     * @return ResponseEntity containing the created schedule and a status code.
     */
    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule) {
        return schedulerService.createSchedule(schedule);
    }

    /**
     * Endpoint to update an existing schedule by its ID.
     *
     * @param id The ID of the schedule to update.
     * @param schedule The updated schedule data provided in the request body.
     * @return ResponseEntity containing the updated schedule and a status code.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long id, @RequestBody Schedule schedule) {
        return schedulerService.updateSchedule(id, schedule);
    }

    /**
     * Endpoint to delete a schedule by its ID.
     *
     * @param id The ID of the schedule to delete.
     * @return ResponseEntity with a status code indicating the success of deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        return schedulerService.deleteSchedule(id);
    }
}
