package com.management.service.services;

import com.baomidou.mybatisplus.service.IService;
import com.management.Parametric.StudentModule;

import java.util.List;

/**
 * Interface for student module related operations.
 */
public interface StudentModuleService extends IService<StudentModule> {
    List<StudentModule> getModuleByStudentId(String studentId);
}

