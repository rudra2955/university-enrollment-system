package com.AKTU.University_Enrollment_System;

import com.AKTU.University_Enrollment_System.model.Student;
import com.AKTU.University_Enrollment_System.exception.InvalidAgeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testValidStudentCreation() throws InvalidAgeException {
        Student s = new Student("Rudra", 20, 101);
        assertEquals("Rudra", s.getName());
        assertEquals(20, s.getAge());
        assertEquals(101, s.getStd_id());
    }

    @Test
    void testInvalidAgeException() {
        assertThrows(InvalidAgeException.class, () -> {
            new Student("Amit", 16, 102);
        });
    }

    @Test
    void testEnrollCourse() throws InvalidAgeException {
        Student s = new Student("Neha", 21, 103);
        s.enrollCourse("Java");
        assertTrue(s.getEnrolledCourses().contains("Java"));
    }
}
