package com.AKTU.University_Enrollment_System.util;
import java.sql.*;
public class DBConnection {
    private static final String url="jdbc:mysql://localhost:3306/university_enrollment";
    private static final String USER = "root";
    private static final String PASSWORD = "Rudra@2954";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, USER, PASSWORD);
    }
}

//connection is the return type of function  , it is a interface of sql package that represent the session with database
//4. getConnection()
//Meaning: This is the method name.
// It's a custom method (not built-in) that, when called, attempts to connect to a database.

//DriverManager.getConnection(url, USER, PASSWORD)
//Meaning: This is a call to a static method from the DriverManager class (in java.sql). It establishes a connection using the provided URL, username, and password.
//        url: A string like "jdbc:mysql://localhost:3306/mydb" specifying the database type, location, and name.
//        USER: A string for the database username (e.g., "root").
//PASSWORD: A string for the password (e.g., "password123").
//Why here?: DriverManager manages JDBC drivers and handles the actual connection setup. This is the core JDBC way to connect.
//
