package CourseManagementSystem;

import javax.swing.*;
import java.sql.*;

public class UserTable {
    private Connection con;

    public UserTable(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CourseManagementSystem?useTimezone=true&serverTimeZone=true&serverTimezone = UTC","root","");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void insert(String firstName,String lastName,String email,String password,String userType,String courseType,String level){
        try {
            String insert = "INSERT INTO users(firstName,lastName,email,password,userType,courseType,level)" + "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(insert);

            statement.setString(1,firstName);
            statement.setString(2,lastName);
            statement.setString(3,email);
            statement.setString(4,password);
            statement.setString(5,userType);
            statement.setString(6,courseType);
            statement.setString(7,level);

            statement.executeUpdate();
            statement.close();
            JOptionPane.showMessageDialog(null, "User has been successfully added. Thank You!!!");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    ResultSet getParticularStudentData(String Email) {
        try {
            String select = "SELECT * FROM users WHERE email = ?";

            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1,Email);
            return statement.executeQuery();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    ResultSet getInstructorNames(){
        try {
            String select = "Select firstName,lastName FROM users WHERE users.userType = 'Instructor'";

            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
