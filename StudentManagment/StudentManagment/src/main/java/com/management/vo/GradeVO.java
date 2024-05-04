package com.management.vo;

import com.management.Parametric.Grade;
import lombok.Data;
/**
 * @author Wenqi Wang
 * @version 1.0
 * @since 2024-04-22
 */
@Data
public class GradeVO extends Grade {

    private String studentName;
    private String moduleName;
    private String staffName;
    private String gender;
    private String phone;
    private String title;
    private String email;
}
