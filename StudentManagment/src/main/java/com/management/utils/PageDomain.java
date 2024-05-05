package com.management.utils;

/**
 * 分页数据
 * @author Meng Wang
 * @version 1.0
 * @since 2024-04-21
 */
public class PageDomain {
    /**
     * Start index of the current record
     */
    private Integer pageNum;

    /**
     * Displays the number of records per page
     */
    private Integer pageSize;

    /**
     * Sorting
     */
    private String orderByColumn;

    /**
     * Sort in the direction desc or asc
     */
    private String isAsc = "asc";

    public String getOrderBy() {
        if (StringUtils.isEmpty(orderByColumn)) {
            return "";
        }
        return StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(String isAsc) {
        this.isAsc = isAsc;
    }
}
