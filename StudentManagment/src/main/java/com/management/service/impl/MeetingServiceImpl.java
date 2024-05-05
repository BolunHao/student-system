package com.management.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.management.Parametric.Meeting;
import com.management.mapper.Mapper.MeetingMapper;
import com.management.utils.StringUtils;
import com.management.vo.ModuleVO;

import java.util.List;
/**
 * @author Wenqi Wang , Meng Wang
 * @version 1.0
 * @since 2024-04-22
 */
public class MeetingServiceImpl extends ServiceImpl<MeetingMapper, Meeting> {

    public List<ModuleVO> selectCourseByStudent(Meeting meeting) {
        return baseMapper.selectCourseByStudent(meeting);
    }

    public Integer save(Meeting meeting) {
        meeting.setId(String.valueOf(IdUtil.getSnowflakeNextId()));
        return baseMapper.insert(meeting);
    }

    public List<Meeting> selectMeetingByTeacher(Meeting meeting) {
        // Create query parameters
        EntityWrapper<Meeting> wrapper = new EntityWrapper<>();
        wrapper.eq("staff_id", meeting.getStaffId());
        wrapper.like(StringUtils.isNotEmpty(meeting.getMeetingName()), "meeting_name", meeting.getMeetingName());
        return baseMapper.selectList(wrapper);

    }
}
