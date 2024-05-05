package com.management.Parametric;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("meeting")
public class Meeting extends BaseEntity implements Serializable{
    private String id;
    private String meetingId;
    private String meetingName;
    private String studentId;
    private String staffId;
    private String meetingStatus;
    private String meetingStartTime;
    private String meetingEndTime;
}