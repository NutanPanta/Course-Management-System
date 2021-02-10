package CourseManagementSystem;

import javax.swing.*;
import java.sql.*;

public class StudentCourseTable {

    private Connection con;

    public StudentCourseTable(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CourseManagementSystem?useTimezone=true&serverTimeZone=true&serverTimezone = UTC","root","");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void insert(String email,String moduleName){
        try {
            String insert = "INSERT INTO studentElectiveSubjects(email,moduleName)" + "VALUES(?,?)";
            PreparedStatement statement = con.prepareStatement(insert);

            statement.setString(1,email);
            statement.setString(2,moduleName);

            statement.executeUpdate();
            statement.close();
            JOptionPane.showMessageDialog(null, moduleName  + " has been successfully added has your elective subjects. Thank You!!!");

        }catch (SQLException e){
            if (e instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(null,"Duplicate Module Name");
            }else {
                JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    ResultSet getModuleDetails(String courseName, String level){
        try {
            String select = "Select * from instructorteachingmodules inner join modules on  modules.moduleName = instructorteachingmodules.moduleName and courseName = ? and level = ? inner join courses where modules.courseName = courses.courseName and modules.moduleType ='Compulsory' order by cast(modules.semester as unsigned)";
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

        ResultSet getCompulsoryMarksDetailsForResult(String email){
            try {
                String select = "Select instructormarkstable.moduleName,modules.semester,instructormarkstable.obtainedMarks,instructormarkstable.fullMarks,instructormarkstable.passMarks,instructormarkstable.grade,instructormarkstable.status from instructormarkstable inner join modules on modules.moduleName = instructormarkstable.moduleName inner join users on instructormarkstable.studentEmail = users.email where email = ? and moduleType = 'Compulsory'";
                PreparedStatement statement = con.prepareStatement(select);
                statement.setString(1,email);
                return statement.executeQuery();
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            return null;
        }

    ResultSet getElectiveMarksDetailsForResult(String email){
        try {
            String select = "Select instructormarkstable.moduleName,modules.semester,instructormarkstable.obtainedMarks,instructormarkstable.fullMarks,instructormarkstable.passMarks,instructormarkstable.grade,instructormarkstable.status from instructormarkstable inner join modules on modules.moduleName = instructormarkstable.moduleName inner join users on instructormarkstable.studentEmail = users.email where email = ? and moduleType = 'Elective'";
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1,email);
            return statement.executeQuery();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet getModuleNameAtStudentPanel(String semester){
        try {
            String select = "Select * from instructorteachingmodules inner join modules on  modules.moduleName = instructorteachingmodules.moduleName and modules.moduleType = 'Elective' and modules.semester = ?  inner join courses on courses.courseStatus = 'Open' and modules.courseName = courses.courseName";
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1,semester);
            return statement.executeQuery();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet getElectiveSubjectData(String email){
        try {
            String select = "Select * from studentelectivesubjects inner join modules on studentelectivesubjects.moduleName = modules.moduleName inner join instructorteachingmodules on studentelectivesubjects.moduleName = instructorteachingmodules.moduleName  where email=? order by cast(modules.semester as unsigned)";
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1,email);
            return statement.executeQuery();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
