package com.management.utils;

import java.io.Serializable;
import java.util.List;

/**
 * Table paging data object
 * @author Meng Wang
 * @version 1.0
 * @since 2024-04-21
 */
public class TableDataInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Total number of records
     */
    private long total;

    /**
     * table data
     */
    private List<?> rows;

    /**
     * Message status code
     */
    private int code;

    /**
     * Message Content
     */
    private String msg;

    /**
     * Form data object
     */
    public TableDataInfo() {
    }

    /**
     * Pagination
     *
     * @param list  Table data
     * @param total Total number of records
     */
    public TableDataInfo(List<?> list, int total) {
        this.rows = list;
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}