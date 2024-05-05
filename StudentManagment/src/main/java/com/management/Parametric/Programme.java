package com.management.Parametric;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("programme")
public class Programme implements Serializable{
    private String id;
    private String programmeId;
    private String programmeName;
    private String programmeDesc;
}
