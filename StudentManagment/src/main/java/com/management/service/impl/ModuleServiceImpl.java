package com.management.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.management.Parametric.Module;
import com.management.mapper.Mapper.ModuleMapper;
import com.management.vo.ModuleVO;

import java.util.List;
/**
 * @author Wenqi Wang , Meng Wang
 * @version 1.0
 * @since 2024-04-22
 */
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, Module> {
    public List<Module> selectHomeworkAndExam(ModuleVO moduleVO) {
        return baseMapper.selectHomeworkAndExam(moduleVO);
    }

    public List<ModuleVO> selectScoreByStudentId(ModuleVO vo) {
        return baseMapper.selectScoreByStudentId(vo);
    }

    public List<ModuleVO> selectScoreByStaffId(ModuleVO vo) {
        return baseMapper.selectScoreByStaffId(vo);
    }
}
