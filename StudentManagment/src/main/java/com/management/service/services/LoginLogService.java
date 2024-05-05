package com.management.service.services;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.management.Parametric.LoginLog;

import java.util.List;

public interface LoginLogService extends IService<LoginLog> {

    /**
     * Query login counts
     */
    Integer findMaxLoginTotalByUserId(String userId);


    /**
     * User login log
     */
    List<LoginLog> findUserLoginLogByPage(Page<LoginLog> page, String searchKeyWord);

    /**
     * Statistical landing
     */
    Object findUserLoginTotal();
}
