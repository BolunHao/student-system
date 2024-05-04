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
     * 按照student，teacher，course，S_C，T_C的数据表进行数据展示，只展示给学生上过课的老师供给学生选择论文导师。此处设置约束条件，
     * 当一个老师被15个学生选择的时候，页面告知该老师名额已满，不可被选择。实现方式在 academic_guidence表个里根据老师ID的出现次数进行比较即可。
     */
    @GetMapping("/selectScoreByStudentId")
    public AjaxResult list(ModuleVO vo) {
        return AjaxResult.success(moduleService.selectScoreByStudentId(vo));
    }
}
