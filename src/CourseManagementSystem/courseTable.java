package CourseManagementSystem;

import javax.swing.*;
import java.sql.*;

public class courseTable {
    private Connection connection;

    public courseTable() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/CourseManagementSystem?useTimezone=true&serverTimeZone=true&serverTimezone = UTC",
                    "root", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    ResultSet getAllCourses() {
        try {
            String select = "SELECT * FROM courses";

            PreparedStatement myStmt = connection.prepareStatement(select);
            return myStmt.executeQuery();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet openCourses() {
        try {
            String select = "SELECT courseName FROM courses WHERE courseStatus = 'Open'";

            PreparedStatement myStmt = connection.prepareStatement(select);
            return myStmt.executeQuery();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
            error.printStackTrace();
        }
        return null;
    }

    void addCourse(String name, String status) {
        try {
            String insert = "INSERT INTO courses(courseName,courseStatus)" + "VALUES(?,?)";
            PreparedStatement myStmt = connection.prepareStatement(insert);

            myStmt.setString(1, name);
            myStmt.setString(2, status);

            myStmt.executeUpdate();
            myStmt.close();
            JOptionPane.showMessageDialog(null, "Course Added!!!");

        } catch (SQLException error) {
            if (error instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(null, "Coure with this name has already been added");
            } else {
                JOptionPane.showMessageDialog(null, "Internal Server Error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void updateCourseName(int id, String name) {
        try {
            String update = "UPDATE courses SET courseName = ? WHERE  courseId = ?";
            PreparedStatement myStmt = connection.prepareStatement(update);
            myStmt.setString(1, name);
            myStmt.setInt(2, id);

            myStmt.executeUpdate();
            myStmt.close();
            JOptionPane.showMessageDialog(null, "Course Name Has Been Successfully Updated To " + name + ".",
                    "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException error) {
            if (error instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(null, "Duplicate Course Name");
                error.printStackTrace();
            } else {
                JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void updateCourseStatus(int id, String status) {
        try {
            String update = "UPDATE courses SET courseStatus = ? WHERE  courseId = ?";
            PreparedStatement myStmt = connection.prepareStatement(update);
            myStmt.setString(1, status);
            myStmt.setInt(2, id);

            myStmt.executeUpdate();
            myStmt.close();

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    void deleteCourse(int id) {
        String delete = "DELETE FROM courses WHERE courseId=?";
        try {
            PreparedStatement myStmt = connection.prepareStatement(delete);
            myStmt.setInt(1, id);
            myStmt.execute();
            myStmt.close();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
            error.printStackTrace();
        }
    }
}
