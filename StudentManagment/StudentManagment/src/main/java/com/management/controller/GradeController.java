package com.management.controller;

import com.management.Parametric.Grade;
import com.management.service.impl.GradeServiceImpl;
import com.management.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author Wenqi Wang
 * @version 1.0
 * @since 2024-04-22
 */
@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeServiceImpl gradeService;

    /**
     * 学生界面显示所有成绩以及考试课程以及课程负责老师的相关信息
     */
    @GetMapping("/selectGradeByStudentId")
    public AjaxResult selectGradeByStudentId(Grade grade) {
        return AjaxResult.success(gradeService.selectGradeByStudentId(grade));
    }
}
