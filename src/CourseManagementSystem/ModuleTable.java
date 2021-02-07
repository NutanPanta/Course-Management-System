package CourseManagementSystem;

import javax.swing.*;
import java.sql.*;

public class ModuleTable {
    private Connection con;

    public ModuleTable(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CourseManagementSystem?useTimezone=true&serverTimeZone=true&serverTimezone = UTC","root","");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void insert(String moduleName,String courseName,String level,String moduleType,String semester){
        try {
            String insert = "INSERT INTO modules(moduleName,courseName,level,moduleType,semester)" + "VALUES(?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(insert);

            statement.setString(1,moduleName);
            statement.setString(2,courseName);
            statement.setString(3,level);
            statement.setString(4,moduleType);
            statement.setString(5,semester);

            statement.executeUpdate();
            statement.close();
            JOptionPane.showMessageDialog(null, "Module has been successfully added. Thank You!!!");

        }catch (SQLException e){
            if (e instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(null,"Duplicate Course Name");
            }else {
                JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    ResultSet getModuleName(){
        try {
            String select = "SELECT moduleName FROM modules, courses WHERE courses.courseName=modules.courseName and courses.courseStatus = 'Open'";

            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet getModuleCount(String courseName,String level,String semester,String moduleType){
        try {
            String select = "SELECT COUNT(moduleName) AS total  FROM modules WHERE courseName = ? AND level = ? AND semester = ? AND moduleType = ?";

            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1,courseName);
            statement.setString(2,level);
            statement.setString(3,semester);
            statement.setString(4,moduleType);

            return statement.executeQuery();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return null;
    }

    ResultSet getModuleData(){
        try {
            String select = "Select * FROM modules, courses WHERE courses.courseName=modules.courseName and courses.courseStatus = 'Open'";
            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet getModulesDataIntoInstructorPanel(String moduleName){
        try {
            String select = "select courseName,level,moduleType,semester from modules where moduleName = ?";
            PreparedStatement statement = con.prepareStatement(select);

            statement.setString(1,moduleName);

            return statement.executeQuery();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    void updateModule(int moduleId,String moduleName,String courseName,String level,String moduleType,String semester) {
        try {
            String update = "UPDATE modules SET moduleName = ?,courseName = ?, level = ?,moduleType = ?, semester = ?  WHERE  moduleId = ?";
            PreparedStatement statement = con.prepareStatement(update);

            statement.setString(1,moduleName);
            statement.setString(2,courseName);
            statement.setString(3,level);
            statement.setString(4,moduleType);
            statement.setString(5,semester);
            statement.setInt(6, moduleId);

            statement.executeUpdate();
            statement.close();
            JOptionPane.showMessageDialog(null, "Module has been successfully updated. Thank You!!!");

        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(null,"Duplicate Module Name");
            }else {
                JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void deleteModule(int id){
        String delete = "DELETE FROM modules WHERE moduleId=?";
        try {
            PreparedStatement statement = con.prepareStatement(delete);
            statement.setInt(1, id);
            statement.execute();
            statement.close();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
