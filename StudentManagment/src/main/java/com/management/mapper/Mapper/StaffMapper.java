package com.management.mapper.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.management.Parametric.Staff;
import com.management.vo.ApprovalVO;

import java.util.List;
/**
 * @author Meng Wang
 * @version 1.0
 * @since 2024-04-21
 */
public interface StaffMapper extends BaseMapper<Staff> {
    List<ApprovalVO> selectApplyByStaffId(ApprovalVO approvalVO);
}
