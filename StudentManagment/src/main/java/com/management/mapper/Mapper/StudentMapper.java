package com.management.mapper.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.management.Parametric.Module;
import com.management.Parametric.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Wenqi Wang
 * @version 1.0
 * @since 2024-04-23
 */
public interface StudentMapper extends BaseMapper<Student>{
    List<Student> getListByPage(Page<Student> page, @Param("name") String name);
    Integer getStudentCount(@Param("programmeId") String majorId);
    List<Module> listCoursesByStudentId(@Param("studentId") String studentId);//Search course list
    void registerCourse(@Param("studentId") String studentId, @Param("courseId") String courseId);//Register new course
}

