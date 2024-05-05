package com.management.Parametric;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.joda.time.DateTime;

import java.io.Serializable;


@Data
@TableName("academic_guidence")
public class AcademicGuidence implements Serializable{
    private int id;
    private String academicId;          //academic_id
    @TableField("student_id")
    private String studentId;   //student_id
    @TableField("staff_id")
    private String staffId;            //teacher_id
    @TableField("application_deadline")
    private DateTime applicationDeadline;  //application_deadline
    @TableField("tutor_status")
    private int tutorStatus;    //Approval or not
    @TableField("content")
    private String content;        //content
}
