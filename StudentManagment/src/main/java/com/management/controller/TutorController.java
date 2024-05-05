package com.management.controller;

import com.management.service.impl.ModuleServiceImpl;
import com.management.utils.AjaxResult;
import com.management.vo.ModuleVO;
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
@RequestMapping("/tutor")
public class TutorController {

    @Autowired
    private ModuleServiceImpl moduleService;

    /**
     * According to the data table of student, teacher, course, S_C, T_C, the data is displayed, and only the teachers who have given classes to students can provide students with the choice of thesis supervisor. Set the constraint here,
     * When a teacher is selected by 15 students, the page informs that the teacher is full and cannot be selected. Implementation methods can be compared according to the number of appearances of the teacher ID in the academic_guidence table.
     */
    @GetMapping("/selectScoreByStudentId")
    public AjaxResult list(ModuleVO vo) {
        return AjaxResult.success(moduleService.selectScoreByStudentId(vo));
    }
}
