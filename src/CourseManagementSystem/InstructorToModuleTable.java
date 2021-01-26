package CourseManagementSystem;

import javax.swing.*;
import java.sql.*;

public class InstructorToModuleTable {
    private Connection con;

    public InstructorToModuleTable(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CourseManagementSystem?useTimezone=true&serverTimeZone=true&serverTimezone = UTC","root","");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void insert(String moduleName,String instructorName){
        try {
            String insert = "INSERT INTO instructorteachingmodule(moduleName,instructorName)" + "VALUES(?,?)";
            PreparedStatement statement = con.prepareStatement(insert);

            statement.setString(1,moduleName);
            statement.setString(2,instructorName);

            statement.executeUpdate();
            statement.close();
            JOptionPane.showMessageDialog(null, instructorName + " has been successfully added to " + moduleName + ". Thank You!!!");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    ResultSet getModuleDetails(){
        try {
            String select = "Select * from instructorteachingmodule inner join modules on  modules.moduleName = instructorteachingmodule.moduleName inner join courses on courses.courseStatus = 'Open' and modules.courseName = courses.courseName";

            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    void updateInstructorTeachingModules(int id,String moduleName,String instructorName) {
        try {
            String update = "UPDATE instructorteachingmodule SET moduleName = ?,instructorName = ? WHERE  id = ?";
            PreparedStatement statement = con.prepareStatement(update);

            statement.setString(1,moduleName);
            statement.setString(2,instructorName);
            statement.setInt(3, id);

            statement.executeUpdate();
            statement.close();
            JOptionPane.showMessageDialog(null, "Instructor Details has been successfully updated. Thank You!!!");

        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(null,"Duplicate Module Name");
            }else {
                JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    void deleteInstructorFromModule(int id){
        String delete = "DELETE FROM instructorteachingmodule WHERE id=?";
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
