package com.management.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.management.Parametric.Meeting;
import com.management.mapper.Mapper.MeetingMapper;
import com.management.vo.ModuleVO;

import java.util.List;
/**
 * @author Wenqi Wang
 * @version 1.0
 * @since 2024-04-22
 */
public class MeetingServiceImpl extends ServiceImpl<MeetingMapper, Meeting> {

    public List<ModuleVO> selectCourseByTeacher(Meeting meeting) {
        return baseMapper.selectCourseByTeacher(meeting);
    }

    public Integer save(Meeting meeting) {
        meeting.setId(String.valueOf(IdUtil.getSnowflakeNextId()));
        return baseMapper.insert(meeting);
    }
}
