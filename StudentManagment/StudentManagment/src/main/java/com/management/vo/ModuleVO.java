package com.management.vo;

import com.management.Parametric.Module;
import lombok.Data;
/**
 * @author Wenqi Wang
 * @version 1.0
 * @since 2024-04-22
 */
@Data
public class ModuleVO extends Module {

    private String studentId;
    private String ModuleID;
    private String staffName;
    private double score;
    private String studentName;
}
