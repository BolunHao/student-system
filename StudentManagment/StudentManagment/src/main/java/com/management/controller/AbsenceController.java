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
 * 学生信息管理控制器
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
     * 申请缺席
     * 同时单独的每节课在查看详情页面可以选择是否absence以及是否申请作业延迟提交，
     * 每次强制为一周，最多可申请两次，也就是总延迟时间最多两周（需要和数据库组对齐插入一条提交作业时间的数据在grade表格里）。
     * 在单独的每节课详情页，学生也可以点击提交feedback。
     * 界面提供选课按钮，只提供可以匹配上时间的课程即可。
     */
    @PostMapping("/applyAbsence")
    public AjaxResult applyAbsence(@RequestBody AbsenceVO vo) {
        return AjaxResult.success(absenceService.applyAbsence(vo));
    }

}
