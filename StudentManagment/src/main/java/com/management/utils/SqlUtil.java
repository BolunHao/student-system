package com.management.utils;



/**
 * sql operation tools
 * @author Meng Wang
 * @version 1.0
 * @since 2024-04-21
 */
public class SqlUtil {
    /**
     * Supports only letters, numbers, underscores, Spaces, commas, and decimal points (supports multiple field sorting)
     */
    public static String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,\\.]+";

    /**
     * Check characters to prevent injection bypassing
     */
    public static String escapeOrderBySql(String value) throws Exception {
        if (StringUtils.isNotEmpty(value) && !isValidOrderBySql(value)) {
            throw new Exception("The parameter does not meet specifications and cannot be queried");
        }
        return value;
    }

    /**
     * Verify that the order by syntax conforms to the specification
     */
    public static boolean isValidOrderBySql(String value) {
        return value.matches(SQL_PATTERN);
    }
}
