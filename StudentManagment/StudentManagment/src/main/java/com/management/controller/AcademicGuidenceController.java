package com.management.controller;

import com.management.Parametric.AcademicGuidence;
import com.management.Parametric.Staff;
import com.management.service.impl.AcademicGuidenceServiceImpl;
import com.management.service.impl.StaffServiceImpl;
import com.management.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
* @author Wenqi Wang
 * @version 1.0
 * @since 2024-04-22
 */
@RestController
@RequestMapping("/academicGuidence")
public class AcademicGuidenceController {

    @Autowired
    private AcademicGuidenceServiceImpl academicGuidenceService;

    @Autowired
    private StaffServiceImpl staffService;

    /**
     * 学生界面显示所有成绩以及考试课程以及课程负责老师的相关信息
     */
    @GetMapping("/staffCount")
    public AjaxResult staffCount() {
        // 查询所以老师信息
        Map<String, String> staffMap = new HashMap<>();
        List<Staff> staffList = staffService.selectList(null);
        staffList.forEach(it -> {
            staffMap.put(it.getStaffId(), it.getStaffName());
        });
        // 查询所有数据，并
        List<AcademicGuidence> list = academicGuidenceService.selectList(null);
        // list中根据staffId进行统计
        Map<String, Integer> staffIdCountMap = new HashMap<>();
        for(AcademicGuidence academicGuidence : list){
            String staffId = academicGuidence.getStaffId();
            staffIdCountMap.put(staffMap.get(staffId), staffIdCountMap.getOrDefault(staffId, 0) + 1);
        }

        return AjaxResult.success(staffIdCountMap);

    }
}
