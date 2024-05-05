package com.management.utils;

import com.luoran.common.core.utils.ServletUtils;

/**
 * Tabular data processing
 */
public class TableSupport {
    /**
     * Start index of the current record
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * Displays the number of records per page
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * Sort column
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * Sort by direction "desc" or "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * Encapsulated page object
     */
    public static PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(IS_ASC));
        return pageDomain;
    }

    public static PageDomain buildPageRequest() {
        return getPageDomain();
    }
}
