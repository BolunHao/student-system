package com.management.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.management.Parametric.Absence;
import com.management.Parametric.Timetable;
import com.management.mapper.Mapper.AbsenceMapper;
import com.management.mapper.Mapper.TimetableMapper;
import com.management.vo.AbsenceVO;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author Wenqi Wang
 * @version 1.0
 * @since 2024-04-22
 */
public class AbsenceServiceImpl extends ServiceImpl<AbsenceMapper, Absence> {

    @Autowired
    private TimetableMapper timetableMapper;

    @Autowired
    private AbsenceMapper absenceMapper;

    public String applyAbsence(AbsenceVO vo) {
        // Query timetable information
        Timetable query = new Timetable();
        query.setModuleId(vo.getModuleId());
        Timetable timetable = timetableMapper.selectOne(query);

        // Add absence information
        Absence absence = new Absence();
        absence.setId(String.valueOf(IdUtil.getSnowflakeNextId()));
        absence.setAbsenceId(String.valueOf(IdUtil.getSnowflakeNextId()));
        absence.setAbsenceReason(vo.getAbsenceReason());
        absence.setTimetableId(timetable.getTimetableId());
        absence.setAbsenceStatus(0);
        absence.setStudentId(vo.getStudentId());
        absenceMapper.insert(absence);

        return "add success";
    }
}
