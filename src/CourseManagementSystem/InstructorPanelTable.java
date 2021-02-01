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

    void insert(String studentEmail,String moduleName,String instructorEmail,String obtainedMarks,String passMarks,String fullMarks){
        try {
            String insert = "INSERT INTO instructorMarksTable(studentEmail,moduleName,instructorEmail,obtainedMarks,passMarks,fullMarks)" + "VALUES(?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(insert);

            statement.setString(1,studentEmail);
            statement.setString(2,moduleName);
            statement.setString(3,instructorEmail);
            statement.setString(4,obtainedMarks);
            statement.setString(5,passMarks);
            statement.setString(6,fullMarks);

            statement.executeUpdate();
            statement.close();
            JOptionPane.showMessageDialog(null, moduleName  + "marks has been successfully added to. " + studentEmail +  " Thank You!!!");

        }catch (SQLException e){
            if (e instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(null,"Marks has already been added for this student in this module.");
            }else {
                JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    ResultSet getStudentsDetailsOnInstructorPanel(String courseName,String level){
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
            String select = "Select DISTINCT modules.moduleName FROM instructorteachingmodules INNER JOIN modules ON instructorteachingmodules.moduleName = modules.moduleName INNER JOIN users ON instructorteachingmodules.instructorEmail = users.email WHERE email = ? AND modules.courseName=? AND modules.level = ?";
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
}
