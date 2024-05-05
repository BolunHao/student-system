package com.management.vo;

import com.management.Parametric.BaseEntity;
import lombok.Data;
/**
 * @author Meng Wang
 * @version 1.0
 * @since 2024-04-22
 */
@Data
public class ApprovalVO extends BaseEntity {

    private String id;
    private String approvalId;
    private String moduleId;
    private String approvalContent;
    private String studentId;
    private String staffId;
    private String approvalStatus;
}
