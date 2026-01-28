package com.AKTU.University_Enrollment_System;

import com.AKTU.University_Enrollment_System.dao.StudentDAO;
import com.AKTU.University_Enrollment_System.model.Student;
import com.AKTU.University_Enrollment_System.exception.InvalidAgeException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentDAOTest {

    StudentDAO dao = new StudentDAO();

    @Test
    void testAddStudent() throws SQLException, InvalidAgeException {
        Student s = new Student("Pooja", 22, 201);
        dao.addStudent(s);

        List<String> students = dao.getAllStudentsWithCourses();
        boolean found = false;
        for (String row : students) {
            if (row.contains("201")) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    void testDeleteStudent() throws SQLException, InvalidAgeException {
        Student s = new Student("Rahul", 23, 202);
        dao.addStudent(s);

        boolean deleted = dao.deleteStudent(202);
        assertTrue(deleted);
    }
}
