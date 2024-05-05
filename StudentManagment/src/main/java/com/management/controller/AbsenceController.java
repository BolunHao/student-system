package com.management.controller;

import com.management.service.impl.AbsenceServiceImpl;
import com.management.utils.AjaxResult;
import com.management.vo.AbsenceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Student information management controller
 * @author Wenqi Wang
 * @version 1.0
 * @since 2024-04-22
 */
@RestController
@RequestMapping("/absence")
public class AbsenceController {

    @Autowired
    private AbsenceServiceImpl absenceService;

    /**
     * Application for absence
     * At the same time, for each class, you can select whether the assignment is absent or delayed on the viewing details page.
     * Each application is mandatory for one week, with a maximum of two applications, which means a total delay of up to two weeks (need to insert a submission date in the grade form aligned with the database group).
     * On the separate details page for each lesson, students can also click to submit feedback.
     * The interface provides a course selection button, and only courses that can match the time can be provided.
     */
    @PostMapping("/applyAbsence")
    public AjaxResult applyAbsence(@RequestBody AbsenceVO vo) {
        return AjaxResult.success(absenceService.applyAbsence(vo));
    }

}
