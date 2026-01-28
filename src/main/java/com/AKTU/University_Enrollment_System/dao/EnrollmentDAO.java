package com.AKTU.University_Enrollment_System.dao;

import com.AKTU.University_Enrollment_System.exception.CourseFullException;
import com.AKTU.University_Enrollment_System.util.DBConnection;

import java.sql.*;

public class EnrollmentDAO {

    public void enrollStudent(int studentId, String courseName)
            throws SQLException, CourseFullException {

        String checkCapacitySql = "SELECT capacity FROM courses WHERE course_name = ?";
        String insertEnrollSql = "INSERT INTO enrollments (student_id, course_name) VALUES (?, ?)";
        String updateCapacitySql = "UPDATE courses SET capacity = capacity - 1 WHERE course_name = ?";

        try (Connection con = DBConnection.getConnection()) {

            // 1. Check capacity
            try (PreparedStatement ps = con.prepareStatement(checkCapacitySql)) {
                ps.setString(1, courseName);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    int capacity = rs.getInt("capacity");
                    if (capacity <= 0) {
                        throw new CourseFullException("Course is full");
                    }
                }
                else {
                    throw new SQLException("Course not found");
                }
            }

            // 2. Enroll student
            try (PreparedStatement ps = con.prepareStatement(insertEnrollSql)) {
                ps.setInt(1, studentId);
                ps.setString(2, courseName);
                ps.executeUpdate();
            }

            // 3. Decrease capacity
            try (PreparedStatement ps = con.prepareStatement(updateCapacitySql)) {
                ps.setString(1, courseName);
                ps.executeUpdate();
            }
        }
    }
}
