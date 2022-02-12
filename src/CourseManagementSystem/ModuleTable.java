package CourseManagementSystem;

import javax.swing.*;
import java.sql.*;

public class ModuleTable {
    private Connection connection;

    public ModuleTable() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/CourseManagementSystem?useTimezone=true&serverTimeZone=true&serverTimezone = UTC",
                    "root", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void addModules(String moduleName, String courseName, String level, String moduleType, String semester) {
        try {
            String insert = "INSERT INTO modules(moduleName,courseName,level,moduleType,semester)"
                    + "VALUES(?,?,?,?,?)";
            PreparedStatement myStmt = connection.prepareStatement(insert);

            myStmt.setString(1, moduleName);
            myStmt.setString(2, courseName);
            myStmt.setString(3, level);
            myStmt.setString(4, moduleType);
            myStmt.setString(5, semester);

            myStmt.executeUpdate();
            myStmt.close();
            JOptionPane.showMessageDialog(null, "Module has been successfully added. Thank You!!!");

        } catch (SQLException error) {
            if (error instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(null, "Duplicate Module Name");
                error.printStackTrace();
            } else {
                JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    ResultSet getModule() {
        try {
            String select = "SELECT moduleName FROM modules, courses WHERE courses.courseName=modules.courseName and courses.courseStatus = 'Open'";

            PreparedStatement myStmt = connection.prepareStatement(select);
            return myStmt.executeQuery();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet getModuleCount(String courseName, String level, String semester, String moduleType) {
        try {
            String select = "SELECT COUNT(moduleName) AS total  FROM modules WHERE courseName = ? AND level = ? AND semester = ? AND moduleType = ?";

            PreparedStatement myStmt = connection.prepareStatement(select);
            myStmt.setString(1, courseName);
            myStmt.setString(2, level);
            myStmt.setString(3, semester);
            myStmt.setString(4, moduleType);

            return myStmt.executeQuery();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
            error.printStackTrace();
        }
        return null;
    }

    ResultSet getModules() {
        try {
            String select = "Select * FROM modules, courses WHERE courses.courseName=modules.courseName and courses.courseStatus = 'Open'";
            PreparedStatement myStmt = connection.prepareStatement(select);
            return myStmt.executeQuery();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet getModulesDataIntoInstructorPanel(String moduleName) {
        try {
            String select = "select courseName,level,moduleType,semester from modules where moduleName = ?";
            PreparedStatement myStmt = connection.prepareStatement(select);

            myStmt.setString(1, moduleName);

            return myStmt.executeQuery();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    void updateModule(int moduleId, String moduleName, String courseName, String level, String moduleType,
            String semester) {
        try {
            String update = "UPDATE modules SET moduleName = ?,courseName = ?, level = ?,moduleType = ?, semester = ?  WHERE  moduleId = ?";
            PreparedStatement myStmt = connection.prepareStatement(update);

            myStmt.setString(1, moduleName);
            myStmt.setString(2, courseName);
            myStmt.setString(3, level);
            myStmt.setString(4, moduleType);
            myStmt.setString(5, semester);
            myStmt.setInt(6, moduleId);

            myStmt.executeUpdate();
            myStmt.close();
            JOptionPane.showMessageDialog(null, "Module has been successfully updated. Thank You!!!");

        } catch (SQLException error) {
            if (error instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(null, "Duplicate Module Name");
            } else {
                JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void deleteModule(int id) {
        String delete = "DELETE FROM modules WHERE moduleId=?";
        try {
            PreparedStatement myStmt = connection.prepareStatement(delete);
            myStmt.setInt(1, id);
            myStmt.execute();
            myStmt.close();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
