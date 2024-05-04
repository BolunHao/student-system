package com.management.mapper.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.management.Parametric.Grade;
import com.management.vo.GradeVO;

import java.util.List;
/**
 * @author Wenqi Wang
 * @version 1.0
 * @since 2024-04-22
 */
public interface GradeMapper extends BaseMapper<Grade> {
    List<GradeVO> selectGradeByStudentId(Grade grade);
}
