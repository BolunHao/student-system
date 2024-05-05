package com.management.controller;

import cn.hutool.core.util.IdUtil;
import com.management.Parametric.Grade;
import com.management.service.impl.GradeServiceImpl;
import com.management.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * @author Wenqi Wang, Meng Wang
 * @version 1.0
 * @since 2024-04-22
 */
@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeServiceImpl gradeService;

    /**
     * The student interface displays all grades as well as information about the course and the teacher responsible for the course
     */
    @GetMapping("/selectGradeByStudentId")
    public AjaxResult selectGradeByStudentId(Grade grade) {
        return AjaxResult.success(gradeService.selectGradeByStudentId(grade));
    }

    /**
     * add grade
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Grade grade) {
        grade.setId(String.valueOf(IdUtil.getSnowflakeNextId()));
        return AjaxResult.success(gradeService.insert(grade));
    }


}
