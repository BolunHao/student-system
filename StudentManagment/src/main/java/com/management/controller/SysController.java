package com.management.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.management.Parametric.LoginLog;
import com.management.Parametric.OperateRecord;
import com.management.service.services.LoginLogService;
import com.management.service.services.OperateRecordService;
import com.management.utils.EnumCode;
import com.management.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("sys")
public class SysController {
    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private OperateRecordService operateRecordService;

    //Login log list
    @GetMapping("/loginLog/list")
    public Object getLoginLogByPage(Integer startPage,Integer pageSize,String searchKeyWord){
        Page<LoginLog> page = new Page<LoginLog>(startPage,pageSize);
        List<LoginLog> list = loginLogService.findUserLoginLogByPage(page,searchKeyWord);
        return ResultUtil.result(EnumCode.OK.getValue(), EnumCode.OK.getText(), list, page.getTotal());
    }
    //Login log list
    @GetMapping("/operateRecord/list")
    public Object getOperateRecordByPage(Integer startPage,Integer pageSize,String searchKeyWord){
        Page<OperateRecord> page = new Page<OperateRecord>(startPage,pageSize);
        List<OperateRecord> list = operateRecordService.findOperatingRecordByPage(page,searchKeyWord);
        return ResultUtil.result(EnumCode.OK.getValue(), EnumCode.OK.getText(), list, page.getTotal());
    }

    //User Login Statistics
    @GetMapping(value = "/findUserLoginTotal")
    public Object findUserLoginTotal() {
        return loginLogService.findUserLoginTotal();
    }

    //User request statistics
    @GetMapping(value = "/findUserReqTotal")
    public Object findUserReqTotal() {
        return operateRecordService.findUserReqTotal();
    }
}