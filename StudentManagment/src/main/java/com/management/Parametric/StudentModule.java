package com.management.Parametric;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("student_module")
public class StudentModule implements Serializable{
    private String id;
    @TableField("student_id")
    private String studentId;   //StudentID
    @TableField("module_id")
    private String moduleId;    //ModuleID
    private String studentModuleStatus;

}
