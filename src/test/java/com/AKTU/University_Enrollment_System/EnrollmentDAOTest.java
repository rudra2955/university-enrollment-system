package com.AKTU.University_Enrollment_System;

import com.AKTU.University_Enrollment_System.dao.EnrollmentDAO;
import com.AKTU.University_Enrollment_System.dao.StudentDAO;
import com.AKTU.University_Enrollment_System.exception.CourseFullException;
import com.AKTU.University_Enrollment_System.model.Student;
import com.AKTU.University_Enrollment_System.exception.InvalidAgeException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class EnrollmentDAOTest {

    EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
    StudentDAO studentDAO = new StudentDAO();

    @Test
    void testSuccessfulEnrollment() throws SQLException, InvalidAgeException, CourseFullException {
        Student s = new Student("Kiran", 21, 301);
        studentDAO.addStudent(s);

        enrollmentDAO.enrollStudent(301, "Mathematics");

        assertTrue(true); // If no exception, enrollment is successful
    }

    @Test
    void testCourseFullException() throws SQLException, InvalidAgeException {
        Student s1 = new Student("A", 21, 401);
        Student s2 = new Student("B", 22, 402);

        studentDAO.addStudent(s1);
        studentDAO.addStudent(s2);

        try {
            enrollmentDAO.enrollStudent(401, "Physics"); // capacity = 1
            assertThrows(CourseFullException.class, () -> {
                enrollmentDAO.enrollStudent(402, "Physics"); // should fail
            });
        } catch (CourseFullException e) {
            fail("First enrollment should not fail");
        }
    }
}
