package com.management.Parametric;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("absence")
public class Absence implements Serializable{
    private String id;
    private String absenceId;          //absence_id
    @TableField("absence_reason")
    private String absenceReason;   // Leave of Absence Description
    @TableField("student_id")
    private String studentId;            //student_id
    @TableField("staff_id")
    private String staffId;          //teacher_id
    @TableField("timetable_id")         //A lesson id in a separate timetable
    private String timetableId;
    @TableField("absence_status")
    private int absenceStatus;       //Approval status: 0 pending 1 approved 2 rejected
}
