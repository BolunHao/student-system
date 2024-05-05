package com.management.Parametric;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("staff")
public class Staff extends BaseEntity implements Serializable{
    private String id;
    private String staffId;             //TeacherName
    private String staffName;             //TeacherName
    private Integer gender;             //TeacherGender
    private String title;            //Teacher title
    private String email;            //email
    private String phone;            //phone_Number

    @TableField("photo_url")
    private String photoUrl;        //phone number

    @TableField(exist = false)
    private String titleName;       //title Name;Selecting Filters for Mentors

    @TableField(exist = false)
    private String[] ids;            //id collection
}