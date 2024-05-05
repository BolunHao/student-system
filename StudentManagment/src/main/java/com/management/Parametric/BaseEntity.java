package com.management.Parametric;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Entity基类
 * 
 * @author Meng Wang
 */
@Data
@ToString
public class BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** search value */
    private String searchValue;

    /** creator */
    private String createBy;

    /** creation time */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** regenerator */
    private String updateBy;

    /** UpdateTime */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** remark */
    private String remark;

    /** request parameter  */
    private Map<String, Object> params;


    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params)
    {
        this.params = params;
    }
}
