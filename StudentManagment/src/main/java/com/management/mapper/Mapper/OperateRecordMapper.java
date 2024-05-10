package com.management.mapper.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.management.Parametric.OperateRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OperateRecordMapper extends BaseMapper<OperateRecord> {

    /**
     * Query operation record
     */
    List<OperateRecord> findOperateRecordByPage(Pagination page, @Param("searchKeyWord") String searchKeyWord);

    /**
     * Count all requests
     */
    List<OperateRecord> findAllRequstCount();
}