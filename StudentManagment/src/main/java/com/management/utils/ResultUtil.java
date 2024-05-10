package com.management.utils;

import org.json.JSONObject;


//Encapsulation of return results
public class ResultUtil {
    public static String result(final Integer status, final String msg, final Object data, final Integer total) {
        JSONObject jsonObject = new JSONObject() {
            {
                put("status", status);
                put("msg", msg);
                put("data", data);
                put("total", total);
            }
        };
        return jsonObject.toString();
    }

    public static String result(final Integer status, final String msg, final Object data) {
        JSONObject jsonObject = new JSONObject() {
            {
                put("status", status);
                put("msg", msg);
                put("data", data);
            }
        };
        return jsonObject.toString();
    }

    public static String result(final Integer status, final String msg) {
        JSONObject jsonObject = new JSONObject() {
            {
                put("status", status);
                put("msg", msg);
            }
        };
        return jsonObject.toString();
    }

}
