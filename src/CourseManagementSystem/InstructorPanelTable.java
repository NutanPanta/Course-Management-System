package CourseManagementSystem;

import javax.swing.*;
import java.sql.*;

public class InstructorPanelTable {
    private Connection connection;

    public InstructorPanelTable() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/CourseManagementSystem?useTimezone=true&serverTimeZone=true&serverTimezone = UTC",
                    "root", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    ResultSet getStudentsDetails(String email, String courseName, String level) {
        try {
            String select = "Select * from users WHERE userType = 'Student' and  email = ? and courseName = ? and level = ?";
            PreparedStatement myStmt = connection.prepareStatement(select);
            myStmt.setString(1, email);
            myStmt.setString(2, courseName);
            myStmt.setString(3, level);
            return myStmt.executeQuery();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet getMarks(String email) {
        try {
            String select = "Select * from instructormarkstable inner join modules on modules.moduleName = instructormarkstable.moduleName inner join users on instructormarkstable.instructorEmail = users.email where email = ?";
            PreparedStatement myStmt = connection.prepareStatement(select);
            myStmt.setString(1, email);
            return myStmt.executeQuery();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    void addMarks(String studentEmail, String moduleName, String instructorEmail, int obtainedMarks, int passMarks,
            int fullMarks, char grade, String status) {
        try {
            String addMarks = "INSERT INTO instructorMarksTable(studentEmail,moduleName,instructorEmail,obtainedMarks,passMarks,fullMarks,grade,status)"
                    + "VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement myStmt = connection.prepareStatement(addMarks);
            myStmt.setString(1, studentEmail);
            myStmt.setString(2, moduleName);
            myStmt.setString(3, instructorEmail);
            myStmt.setInt(4, obtainedMarks);
            myStmt.setInt(5, passMarks);
            myStmt.setInt(6, fullMarks);
            myStmt.setString(7, String.valueOf(grade));
            myStmt.setString(8, status);

            myStmt.executeUpdate();
            myStmt.close();
            JOptionPane.showMessageDialog(null, obtainedMarks + " marks has been added of " + moduleName + " to "
                    + studentEmail + " by " + instructorEmail);
        } catch (SQLException error) {
            if (error instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(null, "Marks has already been added for this student in this module.");
            } else {
                JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    ResultSet getInstructorCourses(String email) {
        try {
            String select = "Select DISTINCT modules.courseName FROM instructorteachingmodules INNER JOIN modules ON instructorteachingmodules.moduleName = modules.moduleName INNER JOIN users ON instructorteachingmodules.instructorEmail = users.email WHERE email=?";
            PreparedStatement myStmt = connection.prepareStatement(select);
            myStmt.setString(1, email);
            return myStmt.executeQuery();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet getStudentOptDetails(String email) {
        try {
            String select = "select DISTINCT studentElectiveModules.email from studentElectiveModules inner join instructorteachingmodules on studentElectiveModules.moduleName = instructorteachingmodules.moduleName where instructorteachingmodules.instructorEmail = ?";
            PreparedStatement myStmt = connection.prepareStatement(select);
            myStmt.setString(1, email);
            return myStmt.executeQuery();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet getAllStudents(String courseName, String level) {
        try {
            String select = "Select * from users WHERE userType = 'Student' and courseName = ? and level = ?";
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

    void updateMarks(int marksId, int obtainedMarks, char grade, String status) {
        try {
            String update = "UPDATE instructormarkstable SET obtainedMarks = ?, grade = ?, status = ? WHERE  marksId = ?";
            PreparedStatement myStmt = connection.prepareStatement(update);
            myStmt.setInt(1, obtainedMarks);
            myStmt.setString(2, String.valueOf(grade));
            myStmt.setString(3, status);
            myStmt.setInt(4, marksId);

            myStmt.executeUpdate();
            myStmt.close();

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    ResultSet getInstructorModules(String email, String courseName, String level) {
        try {
            String select = "Select DISTINCT modules.moduleName,modules.moduleType FROM instructorteachingmodules INNER JOIN modules ON instructorteachingmodules.moduleName = modules.moduleName INNER JOIN users ON instructorteachingmodules.instructorEmail = users.email WHERE email = ? AND modules.courseName=? AND modules.level = ?";
            PreparedStatement myStmt = connection.prepareStatement(select);
            myStmt.setString(1, email);
            myStmt.setString(2, courseName);
            myStmt.setString(3, level);
            return myStmt.executeQuery();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet getStudentCompDetails(String courseName, String level) {
        try {
            String select = "Select DISTINCT users.email,modules.moduleType from users inner join courses on users.courseName = courses.courseName inner join modules on modules.courseName = courses.courseName where users.courseName = ? and users.level = ? and modules.moduleType = 'Compulsory'";
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

    ResultSet getInstructorModuleName(String email, String courseName, String moduleName) {
        try {
            String select = "Select DISTINCT modules.moduleName,modules.moduleType FROM instructorteachingmodules INNER JOIN modules ON instructorteachingmodules.moduleName = modules.moduleName INNER JOIN users ON instructorteachingmodules.instructorEmail = users.email WHERE email = ? AND modules.courseName=? AND modules.moduleName = ?";
            PreparedStatement myStmt = connection.prepareStatement(select);
            myStmt.setString(1, email);
            myStmt.setString(2, courseName);
            myStmt.setString(3, moduleName);
            return myStmt.executeQuery();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}