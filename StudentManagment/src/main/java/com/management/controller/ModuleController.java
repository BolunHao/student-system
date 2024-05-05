package com.management.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.management.Parametric.Module;
import com.management.service.impl.ModuleServiceImpl;
import com.management.utils.AjaxResult;
import com.management.vo.ModuleVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @author Wenqi Wang , Meng Wang
 * @version 1.0
 * @since 2024-04-22
 */
@RestController
@RequestMapping("/module")
@AllArgsConstructor
public class ModuleController {

    private ModuleServiceImpl moduleService;


    /**
     * Students search for information about their courses including exam and assignment information
     */
    @GetMapping("/selectHomeworkAndExam")
    public AjaxResult selectHomeworkAndExam(ModuleVO moduleVO) {
        List<Module> list = moduleService.selectHomeworkAndExam(moduleVO);
        return AjaxResult.success(list);
    }

    /**
     * Teachers can view their own students' course information (test scores, leave, assignment submission, etc.);
     */
    @GetMapping("/selectScoreByStaffId")
    public AjaxResult selectScoreByStaffId(ModuleVO vo) {
        List<ModuleVO> list = moduleService.selectScoreByStaffId(vo);
        return AjaxResult.success(list);
    }

    /**
     * Click on the student number to display the courses the student has taken
     * @param studentId
     * @return
     */
    @GetMapping("/selectModuleByStudentId/{studentId}")
    public AjaxResult selectScoreByStudentId(@PathVariable String studentId) {
        EntityWrapper<Module> wrapper = new EntityWrapper<>();
        wrapper.eq("student_id", studentId);
        List<Module> list = moduleService.selectList(wrapper);
        return AjaxResult.success(list);
    }

}
