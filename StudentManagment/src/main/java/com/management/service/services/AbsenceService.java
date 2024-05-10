package com.management.service.services;


import com.management.vo.AbsenceVO;

/**
 * AbsenceService interface for managing student absences.
 * Defines the contract for absence related operations.
 */
public interface AbsenceService {

    /**
     *
     * @param vo the AbsenceVO containing all necessary data to register an absence
     * @return a string message indicating the result of the operation
     */
    String applyAbsence(AbsenceVO vo);
}
