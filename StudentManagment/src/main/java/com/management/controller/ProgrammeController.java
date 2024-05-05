package com.management.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.management.Parametric.Programme;
import com.management.service.impl.ProGrammeServiceImpl;
import com.management.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author Wenqi Wang
 * @version 1.0
 * @since 2024-04-22
 */
@RestController
@RequestMapping("/programme")
public class ProgrammeController {

    @Autowired
    private ProGrammeServiceImpl programmeService;

    /**
     * Students click on the programme module to display the major name, ID, and major description
     */
    @RequestMapping("/list")
    public AjaxResult list(Programme programme) {
        // Create query parameters
        EntityWrapper<Programme> wrapper = new EntityWrapper<>();
        wrapper.like("programme_id", programme.getProgrammeId());
        wrapper.like("programme_name", programme.getProgrammeName());
        wrapper.like("programme_desc", programme.getProgrammeDesc());
        return AjaxResult.success(programmeService.selectList(wrapper));
    }

}
