package com.management.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.management.Parametric.Staff;
import com.management.mapper.Mapper.StaffMapper;
import com.management.vo.ApprovalVO;

import java.util.List;
/**
 * @author Meng Wang
 * @version 1.0
 * @since 2024-04-23
 */
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> {
    public List<ApprovalVO> selectApplyByStaffId(ApprovalVO approvalVO) {
        return baseMapper.selectApplyByStaffId(approvalVO);
    }
}
