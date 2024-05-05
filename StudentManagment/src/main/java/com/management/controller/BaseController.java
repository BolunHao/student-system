package com.management.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.management.utils.*;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * web layer general data processing
 *@author Meng Wang
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Converts a string in the Date format passed from the foreground to the date type automatically
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * Set the request paging data
     */
    protected void startPage(){
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = null;
            try {
                orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * Paging data in response to request
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setRows(list);
        rspData.setMsg("查询成功");
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * Response return result
     *
     * @param rows Affect the number of rows
     * @return result of operation
     */
    protected AjaxResult toAjax(int rows) {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * Response return result
     *
     * @param rows Affect the number of rows
     * @return result of operation
     */
    protected AjaxResult toAjax(Long rows) {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * Response return result
     *
     * @param result
     * @return result of operation
     */
    protected AjaxResult toAjax(boolean result) {
        return result ? success() : error();
    }

    /**
     * Return success message
     */
    public AjaxResult success() {
        return AjaxResult.success();
    }

    /**
     * Return failure message
     */
    public AjaxResult error() {
        return AjaxResult.error();
    }

    /**
     * Return success message
     */
    public AjaxResult success(String message) {
        return AjaxResult.success(message);
    }

    /**
     * Return success message
     */
    public AjaxResult success(Object data) {
        return AjaxResult.success(data);
    }

    /**
     * Return failure message
     */
    public AjaxResult error(String message) {
        return AjaxResult.error(message);
    }

}
