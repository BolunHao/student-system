package com.management.controller;

import com.management.Parametric.Meeting;
import com.management.service.impl.MeetingServiceImpl;
import com.management.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * @author Wenqi Wang
 * @version 1.0
 * @since 2024-04-22
 */
@RestController
@RequestMapping("/meeting")
public class MeetingController {

    @Autowired
    private MeetingServiceImpl meetingService;

    /**
     * 学生界面显示相关老师以及老师负责课程
     */
    @GetMapping("/selectCourseByTeacher")
    public AjaxResult selectCourseByTeacher(Meeting meeting) {
        return AjaxResult.success(meetingService.selectCourseByTeacher(meeting));
    }

    /**
     * 点击预约会议提交预约会议。
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Meeting meeting) {
        return AjaxResult.success(meetingService.save(meeting));
    }
}
