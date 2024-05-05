package com.management.Parametric;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.joda.time.DateTime;

import java.io.Serializable;


@Data
@TableName("grade")
public class Grade implements Serializable{
    private String id;
    @TableField("student_id")
    private String studentId;   //studentID
    @TableField("module_id")
    private String moduleId;    //courseID
    @TableField("score")
    private Double score;       //Score
    private String remark;     //teacher assessment
    @TableField(exist = false)
    private String[] ids;            //id
    @TableField(exist = false)
    private DateTime gradeTime;     //Paper submission time

}
