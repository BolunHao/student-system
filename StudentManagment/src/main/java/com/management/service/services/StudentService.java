package com.management.service.services;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.management.Parametric.Module;
import com.management.Parametric.Student;
import com.management.mapper.Mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {
    List<Module> getCoursesByStudent(String studentId);
    void registerCourse(String studentId, String courseId);

}

