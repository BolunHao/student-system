package com.management.mapper.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.management.Parametric.StudentModule;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Meng Wang
 * @version 1.0
 * @since 2024-04-23
 */
public interface StudentModuleMapper extends BaseMapper<StudentModule> {
        @Select("SELECT m.* FROM module m JOIN student_module sm ON m.id = sm.module_id WHERE sc.student_id = #{studentId}")
        List<StudentModule> findCoursesByStudentId(@Param("studentId") String studentId);

}
