package com.management.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.management.Parametric.Student;
import com.management.mapper.Mapper.StudentMapper;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.management.Parametric.Module;
import com.management.Parametric.Student;
import com.management.mapper.Mapper.StudentMapper;
import com.management.service.services.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Override
    public List<Module> getCoursesByStudent(String studentId) {
        return baseMapper.listCoursesByStudentId(studentId);
    }

    @Override
    public void registerCourse(String studentId, String courseId) {
        baseMapper.registerCourse(studentId, courseId);
    }

}