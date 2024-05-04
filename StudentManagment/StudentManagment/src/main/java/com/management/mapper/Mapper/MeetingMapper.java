package com.management.mapper.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.management.Parametric.Meeting;
import com.management.vo.ModuleVO;

import java.util.List;
/**
 * @author Wenqi Wang
 * @version 1.0
 * @since 2024-04-22
 */
public interface MeetingMapper extends BaseMapper<Meeting> {
    List<ModuleVO> selectCourseByTeacher(Meeting meeting);
}
