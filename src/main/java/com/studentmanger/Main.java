package com.studentmanger;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        StudentService service = new StudentService();

        System.out.println("ALL STUDENT");
        StudentService service1 = service;
        service1.showAllStudents();
    }
}