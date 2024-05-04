package com.management.controller;

import com.management.Parametric.Module;
import com.management.service.impl.ModuleServiceImpl;
import com.management.utils.AjaxResult;
import com.management.vo.ModuleVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @author Wenqi Wang
 * @version 1.0
 * @since 2024-04-22
 */
@RestController
@RequestMapping("/module")
@AllArgsConstructor
public class ModuleController {

    private ModuleServiceImpl moduleService;


    /**
     * 学生查询自己的课程相关信息包括考试和作业信息
     */
    @GetMapping("/selectHomeworkAndExam")
    public AjaxResult selectHomeworkAndExam(ModuleVO moduleVO) {
        List<Module> list = moduleService.selectHomeworkAndExam(moduleVO);
        return AjaxResult.success(list);
    }

}
