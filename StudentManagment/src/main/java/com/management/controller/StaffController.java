package com.management.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.management.Parametric.Staff;
import com.management.service.impl.StaffServiceImpl;
import com.management.utils.AjaxResult;
import com.management.utils.StringUtils;
import com.management.utils.TableDataInfo;
import com.management.vo.ApprovalVO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Teacher information management controller
 * @author Meng Wang
 * @version 1.0
 * @since 2024-04-23
 */
@RestController
@RequestMapping("/staff")
@AllArgsConstructor
public class StaffController extends BaseController{

    @Autowired
    private StaffServiceImpl staffService;

    /**
     * Query the teacher information list
     * @param staff
     * @return
     */
    @GetMapping("/list")
    public AjaxResult list(Staff staff) {
        // Create query parameters
        EntityWrapper<Staff> wrapper = new EntityWrapper<>();
        wrapper.like(StringUtils.isNotEmpty(staff.getStaffName()), "staff_name", staff.getStaffName());
        wrapper.like(StringUtils.isNotEmpty(staff.getStaffId()), "staff_id", staff.getStaffId());
        wrapper.like(StringUtils.isNotEmpty(staff.getTitleName()), "title", staff.getTitle());
        wrapper.like(StringUtils.isNotEmpty(staff.getPhone()), "phone", staff.getPhone());
        wrapper.like(StringUtils.isNotEmpty(staff.getPhotoUrl()), "photo_url", staff.getPhotoUrl());
        wrapper.like(staff.getGender() != null, "gender", String.valueOf(staff.getGender()));
        wrapper.like(StringUtils.isNotEmpty(staff.getEmail()), "email", staff.getEmail());
        List<Staff> list = staffService.selectList(wrapper);
        return AjaxResult.success(list);
    }

    /**
     * Get teacher details
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(staffService.selectById(id));
    }

    /**
     * Add teacher
     */
    @PostMapping
    public AjaxResult add(@RequestBody Staff staff) {
        return AjaxResult.success(staffService.insert(staff));
    }

    /**
     * Modify teacher
     */
    @PutMapping
    public AjaxResult edit(@RequestBody Staff staff) {
        return AjaxResult.success(staffService.updateById(staff));
    }

    /**
     * 删除教师
     *
     */
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return AjaxResult.success(staffService.deleteBatchIds(Arrays.asList(ids)));
    }

    /**
     * 所有学生交互申请统一显示在教师的任务管理界面由教师同意进行确认和拒绝，不对信息类型作区分，全部分页展示；
     * pageNum 当前页码
     * pageSize 每页显示条数
     */
    @GetMapping("/selectApplyByStaffId")
    public TableDataInfo selectApplyByStaffId(ApprovalVO approvalVO) {
        startPage();
        List<ApprovalVO> list = staffService.selectApplyByStaffId(approvalVO);
        return getDataTable(list);
    }

}
