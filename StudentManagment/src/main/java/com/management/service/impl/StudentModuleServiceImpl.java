package com.management.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.management.Parametric.StudentModule;
import com.management.mapper.Mapper.StudentModuleMapper;
import com.management.service.services.StudentModuleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for the student module.
 * Handles business logic and data access for student module operations.
 */
@Service
public class StudentModuleServiceImpl extends ServiceImpl<StudentModuleMapper, StudentModule> implements StudentModuleService {

    @Override
    public List<StudentModule> getModuleByStudentId(String studentId) {
        return null;
    }
}
