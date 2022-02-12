package CourseManagementSystem;

import javax.swing.*;
import java.sql.*;

public class UserTable {
    private Connection connection;

    public UserTable() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/CourseManagementSystem?useTimezone=true&serverTimeZone=true&serverTimezone = UTC",
                    "root",
                    "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void registerUser(String fName, String lName, String email, String password, String userRole,
            String courseName,
            String level) {
        try {
            String insert = "INSERT INTO users(firstName,lastName,email,password,userType,courseName,level)"
                    + "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement myStmt = connection.prepareStatement(insert);

            myStmt.setString(1, fName);
            myStmt.setString(2, lName);
            myStmt.setString(3, email);
            myStmt.setString(4, password);
            myStmt.setString(5, userRole);
            myStmt.setString(6, courseName);
            myStmt.setString(7, level);

            myStmt.executeUpdate();
            myStmt.close();
            JOptionPane.showMessageDialog(null, "User Successfully Registered");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    ResultSet getUser(String email) {
        try {
            String select = "SELECT * FROM users WHERE email = ?";

            PreparedStatement myStmt = connection.prepareStatement(select);
            myStmt.setString(1, email);
            return myStmt.executeQuery();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet getInstructorEmails() {
        try {
            String select = "Select email FROM users WHERE userType = 'Instructor'";

            PreparedStatement myStmt = connection.prepareStatement(select);
            return myStmt.executeQuery();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet getInstructorName(String email) {
        try {
            String select = "Select firstName,lastName  from users where email =?";
            PreparedStatement myStmt = connection.prepareStatement(select);
            myStmt.setString(1, email);
            return myStmt.executeQuery();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
