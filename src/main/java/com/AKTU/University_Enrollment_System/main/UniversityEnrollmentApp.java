package com.AKTU.University_Enrollment_System.main;

import com.AKTU.University_Enrollment_System.dao.EnrollmentDAO;
import com.AKTU.University_Enrollment_System.dao.StudentDAO;
import com.AKTU.University_Enrollment_System.exception.CourseFullException;
import com.AKTU.University_Enrollment_System.exception.InvalidAgeException;
import com.AKTU.University_Enrollment_System.model.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class UniversityEnrollmentApp {

    private static final Logger logger =
            Logger.getLogger(UniversityEnrollmentApp.class.getName());

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentDAO studentDAO = new StudentDAO();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String input = sc.nextLine();
            String[] parts = input.split(" ");

            try {
                switch (parts[0]) {

                    case "ADD":
                        String name = parts[1];
                        int age = Integer.parseInt(parts[2]);
                        int id = Integer.parseInt(parts[3]);

                        Student student = new Student(name, age, id);
                        studentDAO.addStudent(student);
                        logger.info("Student added successfully");
                        break;

                    case "ENROLL":
                        int studentId = Integer.parseInt(parts[1]);
                        String courseName = parts[2];

                        enrollmentDAO.enrollStudent(studentId, courseName);
                        logger.info("Enrolled successfully");
                        break;

                    case "VIEW_ALL":
                        List<String> students = studentDAO.getAllStudentsWithCourses();
                        for (String s : students) {
                            logger.info(s);
                        }
                        break;

                    case "DELETE":
                        int deleteId = Integer.parseInt(parts[1]);
                        boolean deleted = studentDAO.deleteStudent(deleteId);

                        if (deleted)
                            logger.info("Student deleted successfully");
                        else
                            logger.warning("Student not found");
                        break;

                    default:
                        logger.warning("Invalid command: " + parts[0]);
                }

            } catch (InvalidAgeException e) {
                logger.severe("Invalid age");
            } catch (CourseFullException e) {
                logger.severe("Course is full");
            } catch (SQLException e) {
                logger.severe(e.getMessage());
            }
        }
    }
}
