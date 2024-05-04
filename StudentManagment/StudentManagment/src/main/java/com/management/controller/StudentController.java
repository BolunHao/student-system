package com.management.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.management.Parametric.Student;
import com.management.service.impl.StudentServiceImpl;
import com.management.utils.AjaxResult;
import com.management.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 学生信息管理控制器
 * @author Wenqi Wang
 * @version 1.0
 * @since 2024-04-22
 */
@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {

    private StudentServiceImpl studentService;

    /**
     * 查询学生信息列表
     */
    @GetMapping("/list")
    public AjaxResult list(Student student) {
        // 创建查询参数
        EntityWrapper<Student> wrapper = new EntityWrapper<>();
        wrapper.like(StringUtils.isNotEmpty(student.getName()), "name", student.getName());
        wrapper.like(StringUtils.isNotEmpty(student.getStudentNumber()), "student_number", student.getStudentNumber());
        wrapper.like(StringUtils.isNotEmpty(student.getMajorId()), "major_id", student.getMajorId());
        wrapper.like(StringUtils.isNotEmpty(student.getPhone()), "phone", student.getPhone());
        wrapper.like(StringUtils.isNotEmpty(student.getEmail()), "email", student.getEmail());
        wrapper.like(StringUtils.isNotEmpty(student.getMajorName()), "major_name", student.getMajorName());
        List<Student> list = studentService.selectList(wrapper);
        return AjaxResult.success(list);
    }

    /**
     * 获取学生详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(studentService.selectById(id));
    }

    /**
     * 新增学生
     */
    @PostMapping
    public AjaxResult add(@RequestBody Student student) {
        return AjaxResult.success(studentService.insert(student));
    }

    /**
     * 修改学生
     */
    @PutMapping
    public AjaxResult edit(@RequestBody Student student) {
        return AjaxResult.success(studentService.updateById(student));
    }

    /**
     * 删除学生
     *
     */
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return AjaxResult.success(studentService.deleteBatchIds(Arrays.asList(ids)));
    }

}
