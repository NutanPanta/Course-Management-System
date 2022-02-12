package CourseManagementSystem;

import javax.swing.*;
import java.sql.*;

public class StudentCourseTable {

    private Connection connection;

    public StudentCourseTable() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/CourseManagementSystem?useTimezone=true&serverTimeZone=true&serverTimezone = UTC",
                    "root", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void insert(String email, String moduleName) {
        try {
            String insert = "INSERT INTO studentElectiveModules(email,moduleName)" + "VALUES(?,?)";
            PreparedStatement myStmt = connection.prepareStatement(insert);

            myStmt.setString(1, email);
            myStmt.setString(2, moduleName);

            myStmt.executeUpdate();
            myStmt.close();
            JOptionPane.showMessageDialog(null,
                    moduleName + " has been successfully added has your elective subjects. Thank You!!!");

        } catch (SQLException error) {
            if (error instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(null,
                        "This module has already been added as your elective modules. Choose Another");
            } else {
                JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    ResultSet getElectiveSubjectCount(String email, String semester) {
        try {
            String select = "SELECT COUNT(studentelectivemodules.moduleName) AS total FROM studentelectivemodules inner join modules on studentelectivemodules.moduleName = modules.moduleName where studentelectivemodules.email = ? AND modules.semester = ?";

            PreparedStatement myStmt = connection.prepareStatement(select);
            myStmt.setString(1, email);
            myStmt.setString(2, semester);

            return myStmt.executeQuery();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
            error.printStackTrace();
        }
        return null;
    }

    ResultSet getModuleDetails(String courseName, String level) {
        try {
            String select = "Select * from instructorteachingmodules inner join modules on  modules.moduleName = instructorteachingmodules.moduleName and courseName = ? and level = ? inner join courses where modules.courseName = courses.courseName and modules.moduleType ='Compulsory' order by cast(modules.semester as unsigned)";
            PreparedStatement myStmt = connection.prepareStatement(select);
            myStmt.setString(1, courseName);
            myStmt.setString(2, level);
            return myStmt.executeQuery();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet getCompulsoryMarksDetailsForResult(String email) {
        try {
            String select = "Select instructormarkstable.moduleName,modules.semester,instructormarkstable.obtainedMarks,instructormarkstable.fullMarks,instructormarkstable.passMarks,instructormarkstable.grade,instructormarkstable.status from instructormarkstable inner join modules on modules.moduleName = instructormarkstable.moduleName inner join users on instructormarkstable.studentEmail = users.email where email = ? and moduleType = 'Compulsory'";
            PreparedStatement myStmt = connection.prepareStatement(select);
            myStmt.setString(1, email);
            return myStmt.executeQuery();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet getElectiveMarksDetailsForResult(String email) {
        try {
            String select = "Select instructormarkstable.moduleName,modules.semester,instructormarkstable.obtainedMarks,instructormarkstable.fullMarks,instructormarkstable.passMarks,instructormarkstable.grade,instructormarkstable.status from instructormarkstable inner join modules on modules.moduleName = instructormarkstable.moduleName inner join users on instructormarkstable.studentEmail = users.email where email = ? and moduleType = 'Elective'";
            PreparedStatement myStmt = connection.prepareStatement(select);
            myStmt.setString(1, email);
            return myStmt.executeQuery();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet getModuleNameAtStudentPanel(String semester) {
        try {
            String select = "Select * from instructorteachingmodules inner join modules on  modules.moduleName = instructorteachingmodules.moduleName and modules.moduleType = 'Elective' and modules.semester = ?  inner join courses on courses.courseStatus = 'Open' and modules.courseName = courses.courseName";
            PreparedStatement myStmt = connection.prepareStatement(select);
            myStmt.setString(1, semester);
            return myStmt.executeQuery();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet getElectiveSubjectData(String email) {
        try {
            String select = "Select * from studentElectiveModules inner join modules on studentElectiveModules.moduleName = modules.moduleName inner join instructorteachingmodules on studentElectiveModules.moduleName = instructorteachingmodules.moduleName  where email=? order by cast(modules.semester as unsigned)";
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
