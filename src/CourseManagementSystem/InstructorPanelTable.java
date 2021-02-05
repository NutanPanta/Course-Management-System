package CourseManagementSystem;

import javax.swing.*;
import java.sql.*;

public class InstructorPanelTable {
    private Connection con;

    public InstructorPanelTable(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CourseManagementSystem?useTimezone=true&serverTimeZone=true&serverTimezone = UTC","root","");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void insert(String studentEmail,String moduleName,String instructorEmail,int obtainedMarks,int passMarks,int fullMarks,char grade,String status){
        try {
            String insert = "INSERT INTO instructorMarksTable(studentEmail,moduleName,instructorEmail,obtainedMarks,passMarks,fullMarks,grade,status)" + "VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setString(1,studentEmail);
            statement.setString(2,moduleName);
            statement.setString(3,instructorEmail);
            statement.setInt(4,obtainedMarks);
            statement.setInt(5,passMarks);
            statement.setInt(6,fullMarks);
            statement.setString(7,String.valueOf(grade));
            statement.setString(8,status);

            statement.executeUpdate();
            statement.close();
            JOptionPane.showMessageDialog(null,obtainedMarks + " marks has been added of " + moduleName + " to " + studentEmail + " by " + instructorEmail);
        }catch (SQLException e){
            if (e instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(null,"Marks has already been added for this student in this module.");
            }else {
                JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    ResultSet getAddedMarks(String email){
        try {
            String select = "Select * from instructormarkstable inner join modules on modules.moduleName = instructormarkstable.moduleName inner join users on instructormarkstable.instructorEmail = users.email where email = ?";
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1,email);
            return statement.executeQuery();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet getStudentsDetails(String email,String courseName, String level){
        try {
            String select = "Select * from users WHERE userType = 'Student' and  email = ? and courseName = ? and level = ?";
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1,email);
            statement.setString(2,courseName);
            statement.setString(3,level);
            return statement.executeQuery();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet getStudents(String courseName, String level){
        try {
            String select = "Select * from users WHERE userType = 'Student' and courseName = ? and level = ?";
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1,courseName);
            statement.setString(2,level);
            return statement.executeQuery();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet getStudentsCompulsoryDetailsOnInstructorPanel(String courseName, String level){
        try {
            String select = "Select DISTINCT users.email,modules.moduleType from users inner join courses on users.courseName = courses.courseName inner join modules on modules.courseName = courses.courseName where users.courseName = ? and users.level = ? and modules.moduleType = 'Compulsory'";
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1,courseName);
            statement.setString(2,level);
            return statement.executeQuery();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet getStudentsElectiveDetailsOnInstructorPanel(String email){
        try {
            String select = "select DISTINCT studentelectivesubjects.email from studentelectivesubjects inner join instructorteachingmodules on studentelectivesubjects.moduleName = instructorteachingmodules.moduleName where instructorteachingmodules.instructorEmail = ?";
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1,email);
            return statement.executeQuery();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet getInstructorTeachingCourses(String email){
        try {
            String select = "Select DISTINCT modules.courseName FROM instructorteachingmodules INNER JOIN modules ON instructorteachingmodules.moduleName = modules.moduleName INNER JOIN users ON instructorteachingmodules.instructorEmail = users.email WHERE email=?";
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1,email);
            return statement.executeQuery();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet getInstructorTeachingModules(String email,String courseName,String level){
        try {
            String select = "Select DISTINCT modules.moduleName,modules.moduleType FROM instructorteachingmodules INNER JOIN modules ON instructorteachingmodules.moduleName = modules.moduleName INNER JOIN users ON instructorteachingmodules.instructorEmail = users.email WHERE email = ? AND modules.courseName=? AND modules.level = ?";
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1,email);
            statement.setString(2,courseName);
            statement.setString(3,level);
            return statement.executeQuery();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet getInstructorTeachingModuleName(String email,String courseName,String moduleName){
        try {
            String select = "Select DISTINCT modules.moduleName,modules.moduleType FROM instructorteachingmodules INNER JOIN modules ON instructorteachingmodules.moduleName = modules.moduleName INNER JOIN users ON instructorteachingmodules.instructorEmail = users.email WHERE email = ? AND modules.courseName=? AND modules.moduleName = ?";
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1,email);
            statement.setString(2,courseName);
            statement.setString(3,moduleName);
            return statement.executeQuery();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    void updateStudentMarks(int marksId, int obtainedMarks,char grade,String status) {
        try {
            String update = "UPDATE instructormarkstable SET obtainedMarks = ?, grade = ?, status = ? WHERE  marksId = ?";
            PreparedStatement statement = con.prepareStatement(update);
            statement.setInt(1, obtainedMarks);
            statement.setString(2, String.valueOf(grade));
            statement.setString(3, status);
            statement.setInt(4, marksId);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
