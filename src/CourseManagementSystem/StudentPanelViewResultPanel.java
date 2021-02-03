package CourseManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentPanelViewResultPanel extends JPanel implements AppLayout {
    private JTextField studentName,studentEmail,studentLevel,courseName,collegeName;
    private JLabel title,course,name,email,clzName;
    private GridBagConstraints layout,details;
    UserTable userTable;

    public StudentPanelViewResultPanel(){
        studentName = new JTextField(20);
        studentName.setPreferredSize(new Dimension(40,30));
        studentName.setEnabled(false);
        studentName.setDisabledTextColor(new Color(0,0,0));

        studentEmail = new JTextField(20);
        studentEmail.setPreferredSize(new Dimension(40,30));
        studentEmail.setEnabled(false);
        studentEmail.setDisabledTextColor(new Color(0,0,0));

        studentLevel = new JTextField(20);
        studentLevel.setPreferredSize(new Dimension(40,30));
        studentLevel.setEnabled(false);
        studentLevel.setDisabledTextColor(new Color(0,0,0));

        courseName = new JTextField(20);
        courseName.setPreferredSize(new Dimension(40,30));
        courseName.setEnabled(false);
        courseName.setDisabledTextColor(new Color(0,0,0));

        collegeName = new JTextField(20);
        collegeName.setPreferredSize(new Dimension(40,30));
        collegeName.setEnabled(false);
        collegeName.setDisabledTextColor(new Color(0,0,0));

        title = new JLabel("<html><h1>Student Progress Report Card</h1></html>");
        course = new JLabel("Enrolled Course");
        name = new JLabel("Student Name");
        email = new JLabel("Student Email");
        clzName = new JLabel("College Name");


        userTable = new UserTable();
    }

    public void loggedInStudentData(String Email){
        try {
            ResultSet resultSet = userTable.getParticularUserData(Email);
            while (resultSet.next()) {
                studentName.setText(resultSet.getString("firstName") + " " + resultSet.getString("lastName"));
                studentEmail.setText(resultSet.getString("email"));
                studentLevel.setText(resultSet.getString("level"));
                courseName.setText(resultSet.getString("courseName"));
                collegeName.setText("Herald College");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel StudentPanelCoursesStudentDetailsPanel(){
        JPanel studentPanelCoursesStudentDetails = new JPanel();
        studentPanelCoursesStudentDetails.setLayout(new GridBagLayout());
        studentPanelCoursesStudentDetails.setBackground(Color.decode("#D6D9DF"));

        details = new GridBagConstraints();
        details.fill = GridBagConstraints.HORIZONTAL;
        details.insets = new Insets(5,5,5,5);

        details.gridx = 0;
        details.gridy = 0;
        studentPanelCoursesStudentDetails.add(name,details);

        details.gridx = 1;
        details.gridy = 0;
        details.weightx = 1;
        details.gridwidth = 3;
        studentPanelCoursesStudentDetails.add(studentName,details);

        details.gridx = 0;
        details.gridy = 1;
        studentPanelCoursesStudentDetails.add(email,details);

        details.gridx = 1;
        details.gridy = 1;
        details.weightx = 1;
        details.gridwidth = 3;
        studentPanelCoursesStudentDetails.add(studentEmail,details);

        details.gridx=0;
        details.gridy=2;
        details.gridwidth = 1;
        studentPanelCoursesStudentDetails.add(course,details);

        details.gridx=1;
        details.gridy=2;
        details.gridwidth = 3;
        studentPanelCoursesStudentDetails.add(courseName,details);

        details.gridx=0;
        details.gridy=3;
        details.gridwidth = 1;
        studentPanelCoursesStudentDetails.add(clzName,details);

        details.gridx=1;
        details.gridy=3;
        details.gridwidth = 3;
        studentPanelCoursesStudentDetails.add(collegeName,details);

        return studentPanelCoursesStudentDetails;
    }

    @Override
    public JPanel panelUI() {
        setLayout(new GridBagLayout());
        layout = new GridBagConstraints();
        layout.fill = GridBagConstraints.BOTH;
        layout.insets = new Insets(5,0,5,0);
        this.setBackground(Color.decode("#D6D9DF"));

        layout.gridx = 0;
        layout.gridy = 0;
//        layout.insets = new Insets(0,100,0,0);
        add(title,layout);

        layout.gridx = 0;
        layout.gridy = 1;
        add(StudentPanelCoursesStudentDetailsPanel(),layout);

        return this;
    }
}
