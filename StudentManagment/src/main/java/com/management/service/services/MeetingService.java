package com.management.service.services;


import com.management.Parametric.Meeting;
import java.util.List;

/**
 * Interface for handling operations related to meetings.
 */
public interface MeetingService {
    /**
     * Finds meetings based on the student's criteria.
     * @param meeting Meeting filter criteria.
     * @return List of meetings matching the criteria.
     */
    List<Meeting> selectCourseByStudent(Meeting meeting);

    /**
     * Saves a new meeting.
     * @param meeting Meeting data to be saved.
     * @return boolean indicating if the save was successful.
     */
    boolean save(Meeting meeting);

    /**
     * Finds meetings for a teacher based on provided criteria.
     * @param meeting Meeting filter criteria.
     * @return List of meetings related to the teacher.
     */
    List<Meeting> selectMeetingByTeacher(Meeting meeting);

    /**
     * Updates a meeting's status.
     * @param meeting Meeting data to be updated.
     * @return boolean indicating if the update was successful.
     */
    boolean updateById(Meeting meeting);
}

