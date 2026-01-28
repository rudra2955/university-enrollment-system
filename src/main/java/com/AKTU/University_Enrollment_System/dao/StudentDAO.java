package com.AKTU.University_Enrollment_System.dao;

import com.AKTU.University_Enrollment_System.model.Student;
import com.AKTU.University_Enrollment_System.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (student_id, name, age) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql))
        //DBConnection.getConnection() returns a JDBC connection
                //try-with-resources automatically closes connection after use (no memory leak)
                //3. Prepare the
                //PreparedStatement ps = con.prepareStatement(sql)
        //This:
        //
        //Compiles the SQL
        //
        //Keeps it ready to receive values
        {
            //Bind Values to Placeholders
            ps.setInt(1, student.getStd_id());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getAge());

//            | Placeholder | Value      |
//                | ----------- | ---------- |
//                | `?1`        | student_id |
//                | `?2`        | name       |
//                | `?3`        | age        |

                ps.executeUpdate();
                //Executes the SQL
            //
            //Returns number of rows affected (we don’t need it here)




        }
    }
    //fetching all students from the database and which course
    //each student is enrolled in then return everything as a list of
    //formatted string

    public List<String> getAllStudentsWithCourses() throws SQLException {
        List<String> result = new ArrayList<>();

        String sql =
                "SELECT s.student_id, s.name, s.age, GROUP_CONCAT(e.course_name) AS courses " +
                        "FROM students s " +
                        "LEFT JOIN enrollments e ON s.student_id = e.student_id " +
                        "GROUP BY s.student_id, s.name, s.age";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                String row = "Student ID: " + rs.getInt("student_id") +
                        ", Name: " + rs.getString("name") +
                        ", Age: " + rs.getInt("age") +
                        ", Courses: " + rs.getString("courses");

                result.add(row);
            }
        }
        return result;
    }


    // DELETE student
    public boolean deleteStudent(int studentId) throws SQLException {
        String sql = "DELETE FROM students WHERE student_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            return ps.executeUpdate() > 0;

            //executeUpdate() returns how many rows were affected.
            //
            //If more than 0 rows were deleted → return true
            //
            //If no row was found → return false
            //
            //Why cascading delete works here
            //
            //Because your enrollments table has:
            //
            //ON DELETE CASCADE
            //
            //
            //So when a student is deleted:
            //
            //All their course enrollments are automatically deleted too.
        }
    }
}