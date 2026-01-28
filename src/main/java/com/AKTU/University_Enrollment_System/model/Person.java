package com.AKTU.University_Enrollment_System.model;
import java.util.*;

abstract public class Person {
    private String name;
    private int age;
    protected Person(String name, int age){
        this.name=name;
        this.age=age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    public abstract String getDetails();
}