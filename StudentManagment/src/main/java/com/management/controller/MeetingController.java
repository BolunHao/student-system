package com.management.controller;

import com.management.Parametric.Meeting;
import com.management.service.impl.MeetingServiceImpl;
import com.management.utils.AjaxResult;
import com.management.utils.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @author Wenqi Wang, Meng wang
 * @version 1.0
 * @since 2024-04-22
 */
@RestController
@RequestMapping("/meeting")
public class MeetingController extends BaseController{

    @Autowired
    private MeetingServiceImpl meetingService;

    /**
     * The student interface shows the relevant teacher and the teacher is responsible for the lesson
     */
    @GetMapping("/selectCourseByStudent")
    public AjaxResult selectCourseByStudent(Meeting meeting) {
        return AjaxResult.success(meetingService.selectCourseByStudent(meeting));
    }

    /**
     * Click Schedule Meeting to schedule a meeting
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Meeting meeting) {
        return AjaxResult.success(meetingService.save(meeting));
    }

    /**
     * Display the specific information of all teachers' meetings in a page display.
     */

    /**
     * The student interface shows the relevant teacher and the teacher is responsible for the lesson
     */
    @GetMapping("/selectMeetingByTeacher")
    public TableDataInfo selectMeetingByTeacher(Meeting meeting) {
        startPage();
        List<Meeting> list = meetingService.selectMeetingByTeacher(meeting);
        return getDataTable(list);
    }

    /**
     * Modifying Meeting status
     */
    @PutMapping("/updateStatus")
    public AjaxResult changeStatus(@RequestBody Meeting meeting) {
        return AjaxResult.success(meetingService.updateById(meeting));
    }
}
