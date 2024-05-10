package com.management.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.management.Parametric.Student;
import com.management.Parametric.StudentModule;
import com.management.service.impl.StudentModuleServiceImpl;
import com.management.service.impl.StudentServiceImpl;
import com.management.utils.AjaxResult;
import com.management.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Student information management controller
 * @author Wenqi Wang, Meng Wang
 * @version 1.0
 * @since 2024-04-22
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private StudentModuleServiceImpl studentModuleService;
    /**
     * Query student information list
     */
    @GetMapping("/list")
    public AjaxResult list(Student student) {
        // Creating Query Parameters
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
     * Get student details
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(studentService.selectById(id));
    }

    /**
     * Add student
     */
    @PostMapping
    public AjaxResult add(@RequestBody Student student) {
        return AjaxResult.success(studentService.insert(student));
    }

    /**
     * Modify student
     */
    @PutMapping
    public AjaxResult edit(@RequestBody Student student) {
        return AjaxResult.success(studentService.updateById(student));
    }

    /**
     * Delete student
     *
     */
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return AjaxResult.success(studentService.deleteBatchIds(Arrays.asList(ids)));
    }

    /**
     * Modify course status
     */
    @PutMapping("/updateStatus")
    public AjaxResult changeStatus(@RequestBody StudentModule studentModule) {
        return AjaxResult.success(studentModuleService.updateById(studentModule));
    }

}
