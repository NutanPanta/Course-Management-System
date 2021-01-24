package CourseManagementSystem;

import javax.swing.*;
import java.sql.*;

public class courseTable {
    private Connection con;

    public courseTable(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CourseManagementSystem?useTimezone=true&serverTimeZone=true&serverTimezone = UTC","root","");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void insert(String courseName,String courseStatus){
        try {
            String insert = "INSERT INTO courses(courseName,courseStatus)" + "VALUES(?,?)";
            PreparedStatement statement = con.prepareStatement(insert);


            statement.setString(1,courseName);
            statement.setString(2,courseStatus);

            statement.executeUpdate();
            statement.close();
            JOptionPane.showMessageDialog(null, "Course has been successfully added. Thank You!!!");

        }catch (SQLException e){
            if (e instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(null,"Duplicate Course Name");
            }else {
                e.printStackTrace();
            }
        }
    }

    ResultSet getCourseData(){
        try {
            String select = "SELECT *FROM courses";

            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    ResultSet getCourseDetails(){
        try {
            String select = "SELECT courseName FROM courses WHERE courseStatus = 'Open'";

            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    void updateCourseName(int id, String courseName) {
        try {
            String update = "UPDATE courses SET courseName = ? WHERE  courseId = ?";
            PreparedStatement statement = con.prepareStatement(update);
            statement.setString(1, courseName);
            statement.setInt(2, id);

            statement.executeUpdate();
            statement.close();
            JOptionPane.showMessageDialog(null,"The data has been update successfully", "Success", JOptionPane.INFORMATION_MESSAGE  );

        } catch (SQLException e){
            if (e instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(null,"Duplicate Course Name");
            }else {
                e.printStackTrace();
            }
        }
    }

    void updateCourseStatus(int id, String courseStatus) {
        try {
            String update = "UPDATE courses SET courseStatus = ? WHERE  courseId = ?";
            PreparedStatement statement = con.prepareStatement(update);
            statement.setString(1, courseStatus);
            statement.setInt(2, id);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void deleteCourse(int id){
        String delete = "DELETE FROM courses WHERE courseId=?";
        try {
            PreparedStatement statement = con.prepareStatement(delete);
            statement.setInt(1, id);
            statement.execute();
            statement.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
