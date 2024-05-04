package com.management.Parametric;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;


@Data
@TableName("timetable")
public class Timetable implements Serializable{
    private String id;
    @TableField("timetable_id")
    private String timetableId;   //timetable_ID
    @TableField("module_id")
    private String moduleId;    //module_ID
    @TableField(exist = false)
    private String studentId;   //student_ID
    @TableField("Teacher_ID")
    private String teacherId;   //Teacher_ID
    @TableField("day_of_week")
    private Enum dayOfWeek;   //What day of the week is the class?
    @TableField("module_start_day")
    private Date moduleStartDay;    //module_start_day
    @TableField("module_end_day")
    private Date moduleEndDay;      //module_end_day
    @TableField("module_start_time")
    private Time moduleStartTime;   //module_start_time
    @TableField("module_end_time")
    private Time moduleEndTime;     //module_end_time
}