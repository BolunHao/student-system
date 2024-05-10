package com.management.controller;

import com.management.Parametric.Timetable;
import com.management.service.services.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.List;

@RestController
@RequestMapping("/api/timetable")
public class TimetableController {

    @Autowired
    private TimetableService timetableService;

    /**
     * Endpoint to create a new timetable entry.
     * @param moduleId The module ID for the course.
     * @param teacherId The teacher's ID.
     * @param startTime The start time of the class.
     * @param endTime The end time of the class.
     * @param dayOfWeek The day of the week the class occurs.
     * @return A ResponseEntity indicating success or failure.
     */
    @PostMapping("/create")
    public ResponseEntity<?> createTimetableEntry(
            @RequestParam String moduleId,
            @RequestParam String teacherId,
            @RequestParam Time startTime,
            @RequestParam Time endTime,
            @RequestParam String dayOfWeek) {
        try {
            timetableService.createTimetableEntry(moduleId, teacherId, startTime, endTime, Enum.valueOf(DayOfWeek.class, dayOfWeek));
            return ResponseEntity.ok("Timetable entry created successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating timetable entry: " + e.getMessage());
        }
    }

    /**
     * Endpoint to fetch timetables for a specific module.
     * @param moduleId The module ID.
     * @param month The month for which to fetch timetables.
     * @param year The year for which to fetch timetables.
     * @return A list of Timetable objects.
     */
    @GetMapping("/fetch")
    public ResponseEntity<List<Timetable>> fetchTimetables(@RequestParam String moduleId, @RequestParam int month, @RequestParam int year) {
        try {
            List<Timetable> timetables = timetableService.fetchTimetablesForModule(moduleId, month, year);
            return ResponseEntity.ok(timetables);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Endpoint for a student to enroll in a module.
     * @param timetableId The ID of the timetable entry.
     * @param studentId The ID of the student.
     * @return A ResponseEntity indicating success or failure.
     */
    @PostMapping("/enroll")
    public ResponseEntity<?> enrollStudent(@RequestParam String timetableId, @RequestParam String studentId) {
        try {
            timetableService.enrollStudentInModule(timetableId, studentId);
            return ResponseEntity.ok("Student enrolled successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error enrolling student: " + e.getMessage());
        }
    }
}