package com.management.controller;

import com.management.Parametric.AcademicGuidence;
import com.management.Parametric.Staff;
import com.management.service.impl.AcademicGuidenceServiceImpl;
import com.management.service.impl.StaffServiceImpl;
import com.management.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author Wenqi Wang，Meng Wamg
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
     * The student interface displays all grades as well as information about the course and the teacher responsible for the course
     */
    @GetMapping("/staffCount")
    public AjaxResult staffCount() {
        // Query all teacher information
        Map<String, String> staffMap = new HashMap<>();
        List<Staff> staffList = staffService.selectList(null);
        staffList.forEach(it -> {
            staffMap.put(it.getStaffId(), it.getStaffName());
        });
        // Query all data
        List<AcademicGuidence> list = academicGuidenceService.selectList(null);
        // list is counted according to staffId
        Map<String, Integer> staffIdCountMap = new HashMap<>();
        for(AcademicGuidence academicGuidence : list){
            String staffId = academicGuidence.getStaffId();
            staffIdCountMap.put(staffMap.get(staffId), staffIdCountMap.getOrDefault(staffId, 0) + 1);
        }

        return AjaxResult.success(staffIdCountMap);
    }

    /**
     * Modify the academic guidance status
     */
    @PutMapping("/updateStatus")
    public AjaxResult changeStatus(@RequestBody AcademicGuidence academicGuidence) {
        return AjaxResult.success(academicGuidenceService.updateById(academicGuidence));
    }
}
