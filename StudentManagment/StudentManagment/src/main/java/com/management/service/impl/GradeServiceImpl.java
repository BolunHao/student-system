package com.management.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.management.Parametric.Grade;
import com.management.mapper.Mapper.GradeMapper;
import com.management.vo.GradeVO;

import java.util.List;
/**
 * @author Wenqi Wang
 * @version 1.0
 * @since 2024-04-22
 */
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> {


    public List<GradeVO> selectGradeByStudentId(Grade grade) {
        return baseMapper.selectGradeByStudentId(grade);
    }
}
