package com.management.utils;

import java.util.HashMap;

/**
 * Operation message reminder
 *
 *@author Wenqi Wang
 *@version 1.0
 *@since 2024-04-22
 */
public class AjaxResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /** status code */
    public static final String CODE_TAG = "code";

    /** returned content*/
    public static final String MSG_TAG = "msg";

    /** data object */
    public static final String DATA_TAG = "data";

    /**
     * Initializes a newly created AjaxResult object to represent an empty message.
     */
    public AjaxResult()
    {
    }

    /**
     * Initializes a newly created AjaxResult object
     * 
     * @param code status code
     * @param msg return content
     */
    public AjaxResult(int code, String msg)
    {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * Initializes a newly created AjaxResult object
     * 
     * @param code status code
     * @param msg return content
     * @param data data object
     */
    public AjaxResult(int code, String msg, Object data)
    {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data))
        {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * Return success message
     * 
     * @return success message
     */
    public static AjaxResult success()
    {
        return AjaxResult.success("operate successfully");
    }

    /**
     * Return success data
     * 
     * @return Success message
     */
    public static AjaxResult success(Object data)
    {
        return AjaxResult.success("operate successfully", data);
    }

    /**
     * Return success message
     * 
     * @param msg return content
     * @return Success message
     */
    public static AjaxResult success(String msg)
    {
        return AjaxResult.success(msg, null);
    }

    /**
     * Return success message
     * 
     * @param msg return content
     * @param data data object
     * @return Success message
     */
    public static AjaxResult success(String msg, Object data)
    {
        return new AjaxResult(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * Return error message
     * 
     * @return
     */
    public static AjaxResult error()
    {
        return AjaxResult.error("operation failure");
    }

    /**
     * Return error message
     * 
     * @param msg return content
     * @return Error message
     */
    public static AjaxResult error(String msg)
    {
        return AjaxResult.error(msg, null);
    }

    /**
     * Return error message
     * 
     * @param msg return content
     * @param data data object
     * @return Error message
     */
    public static AjaxResult error(String msg, Object data)
    {
        return new AjaxResult(HttpStatus.ERROR, msg, data);
    }

    /**
     * Return warning message
     * 
     * @param code status code
     * @param msg return content
     * @return Warning message
     */
    public static AjaxResult error(int code, String msg)
    {
        return new AjaxResult(code, msg, null);
    }
}
