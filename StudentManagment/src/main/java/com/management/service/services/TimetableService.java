package com.management.service.services;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.management.Parametric.Timetable;
import com.management.mapper.Mapper.TimetableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TimetableService {

    @Autowired
    private TimetableMapper timetableMapper;

    /**
     * Creates a new timetable entry.
     * @param moduleId The ID of the course module.
     * @param teacherId The ID of the teacher.
     * @param startTime The start time of the class.
     * @param endTime The end time of the class.
     * @param dayOfWeek The day of the week on which the class is held.
     */
    public void createTimetableEntry(String moduleId, String teacherId, Time startTime, Time endTime, Enum dayOfWeek) {
        Timetable timetable = new Timetable();
        timetable.setModuleId(moduleId);
        timetable.setTeacherId(teacherId);
        timetable.setModuleStartTime(startTime);
        timetable.setModuleEndTime(endTime);
        timetable.setDayOfWeek(dayOfWeek);

        // Set the start and end dates for the next month.
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date startDay = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDay = cal.getTime();

        timetable.setModuleStartDay(startDay);
        timetable.setModuleEndDay(endDay);

        timetableMapper.insert(timetable);
    }

    /**
     * Fetches timetables for a specific module and month.
     * @param moduleId The ID of the module.
     * @param month The month for the timetable.
     * @param year The year for the timetable.
     * @return A list of Timetable entries.
     */
    public List<Timetable> fetchTimetablesForModule(String moduleId, int month, int year) {
        Calendar startCal = Calendar.getInstance();
        startCal.set(Calendar.YEAR, year);
        startCal.set(Calendar.MONTH, month - 1);
        startCal.set(Calendar.DAY_OF_MONTH, 1);

        Calendar endCal = Calendar.getInstance();
        endCal.set(Calendar.YEAR, year);
        endCal.set(Calendar.MONTH, month - 1);
        endCal.set(Calendar.DAY_OF_MONTH, endCal.getActualMaximum(Calendar.DAY_OF_MONTH));

        Date startDate = startCal.getTime();
        Date endDate = endCal.getTime();

        EntityWrapper<Timetable> wrapper = new EntityWrapper<>();
        wrapper.eq("module_id", moduleId)
                .between("module_start_day", startDate, endDate);
        return timetableMapper.selectList(wrapper);
    }

    /**
     * Enrolls a student in a specific timetable.
     * @param timetableId The ID of the timetable.
     * @param studentId The ID of the student.
     */
    public void enrollStudentInModule(String timetableId, String studentId) {
        Timetable timetable = timetableMapper.selectById(timetableId);
        if (timetable != null) {
            timetable.setStudentId(studentId);
            timetableMapper.updateById(timetable);
        }
    }
}
