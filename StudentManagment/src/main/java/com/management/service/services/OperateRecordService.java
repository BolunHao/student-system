package com.management.service.services;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.management.Parametric.OperateRecord;


import java.util.List;

public interface OperateRecordService extends IService<OperateRecord> {

    /**
     * Query operation record
     */
    List<OperateRecord> findOperatingRecordByPage(Page<OperateRecord> page, String searchKeyWord);

    /**
     * Access statistics
     */
    Object findUserReqTotal();
}
