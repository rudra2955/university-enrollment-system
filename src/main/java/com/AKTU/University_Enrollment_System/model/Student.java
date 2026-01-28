package com.AKTU.University_Enrollment_System.model;
import com.AKTU.University_Enrollment_System.exception.InvalidAgeException;

import java.util.*;

public class Student extends Person {
    private int std_id;
    private List<String>enrolledCourses;
    static int studentcount=0;
    public Student(String name, int age,int std_id) throws InvalidAgeException {
         super(name,age);
         if(age<18){
             throw new InvalidAgeException("Age must be 18 or above");
         }
         this.std_id=std_id;
         this.enrolledCourses=new ArrayList<>();
         studentcount++;
    }
    public void enrollCourse(String courseName){
        enrolledCourses.add(courseName);

    }
    public int getStd_id() {
        return std_id;
    }
    public List<String> getEnrolledCourses() {
        return enrolledCourses;
    }
    public static int getStudentcount(){
        return studentcount;
    }
     @Override
     public String getDetails(){
         return "Student ID: " + std_id +
                 ", Name: " + getName()+
                 ", Age: " + getAge() +
                 ", Courses: " + enrolledCourses;
    }

    public class Courses{
        private String courseName;
        private int no_of_seats;
         public Courses(String courseName, int no_of_seats){
             this.courseName=courseName;
             this.no_of_seats=no_of_seats;

         }

        public String getCourseName() {
            return courseName;
        }

        public int getNo_of_seats() {
            return no_of_seats;
        }

    }
}