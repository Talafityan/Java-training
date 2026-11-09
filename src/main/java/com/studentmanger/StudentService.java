
package com.studentmanger;

import java.sql.*;

public class StudentService {

    public void showAllStudents() throws SQLException {
        String sql = "SELECT * FROM students";

        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("email") + " | " +
                                rs.getInt("age")
                );
            }
        }
    }

    public void addStudent(String name, String email, int age) throws SQLException {
        String sql = "INSERT INTO students (name, email, age) VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, age);
            ps.executeUpdate();
        }
    }

    public void deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void updateStudent(int id, String name, String email, int age) throws SQLException {
        String sql = "UPDATE students SET name = ?, email = ?, age = ? WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, age);
            ps.setInt(4, id);
            ps.executeUpdate();
        }
    }

    public void showStudent(int id) throws SQLException {
        String sql = "SELECT * FROM students WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println(
                            rs.getInt("id") + " | " +
                                    rs.getString("name") + " | " +
                                    rs.getString("email") + " | " +
                                    rs.getInt("age")
                    );
                } else {
                    System.out.println("Student not found");
                }
            }
        }
    }
}
