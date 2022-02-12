package CourseManagementSystem;

import javax.swing.*;
import java.sql.*;

public class InstructorToModuleTable {
    private Connection connection;

    public InstructorToModuleTable() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/CourseManagementSystem?useTimezone=true&serverTimeZone=true&serverTimezone = UTC",
                    "root", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void deleteInstructorFromModule(int id) {
        String delete = "DELETE FROM instructorteachingmodules WHERE id=?";
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

    ResultSet getModuleDetails() {
        try {
            String select = "Select * from instructorteachingmodules inner join modules on  modules.moduleName = instructorteachingmodules.moduleName inner join courses on courses.courseStatus = 'Open' and modules.courseName = courses.courseName";

            PreparedStatement myStmt = connection.prepareStatement(select);
            return myStmt.executeQuery();
        } catch (SQLException error) {
            // JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is
            // being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
            error.printStackTrace();
        }
        return null;
    }

    void updateInstructorModules(int id, String moduleName, String instructorEmail) {
        try {
            String update = "UPDATE instructorteachingmodules SET moduleName = ?,instructorEmail = ? WHERE  id = ?";
            PreparedStatement myStmt = connection.prepareStatement(update);

            myStmt.setString(1, moduleName);
            myStmt.setString(2, instructorEmail);
            myStmt.setInt(3, id);

            myStmt.executeUpdate();
            myStmt.close();
            JOptionPane.showMessageDialog(null, "Instructor Details has been successfully updated. Thank You!!!");

        } catch (SQLException error) {
            if (error instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(null, "Duplicate Module Name");
            } else {
                JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void addInstructorToModule(String moduleName, String instructorEmail) {
        try {
            String insert = "INSERT INTO instructorteachingmodules(moduleName,instructorEmail)" + "VALUES(?,?)";
            PreparedStatement myStmt = connection.prepareStatement(insert);

            myStmt.setString(1, moduleName);
            myStmt.setString(2, instructorEmail);

            myStmt.executeUpdate();
            myStmt.close();
            JOptionPane.showMessageDialog(null,
                    instructorEmail + " has been successfully added to " + moduleName + ". Thank You!!!");

        } catch (SQLException error) {
            if (error instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(null, "This module has already been assigned to a instructor");
            } else {
                JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);

            }
        }
    }
}
