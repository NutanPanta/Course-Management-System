package CourseManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userRegistrationPanel extends JPanel implements AppLayout, ActionListener {
    private GridBagConstraints gridLayout;
    private JLabel course, level;
    private JTextField email, password, fName, lName;
    private JComboBox<String> userRole, selectLevel, selectCourse;
    private JButton register, login;
    courseTable courseTable;

    public userRegistrationPanel() {
        fName = new JTextField(20);
        fName.setPreferredSize(new Dimension(40, 30));
        lName = new JTextField(20);
        lName.setPreferredSize(new Dimension(40, 30));
        email = new JTextField(20);
        email.setPreferredSize(new Dimension(40, 30));
        password = new JPasswordField(20);
        password.setPreferredSize(new Dimension(40, 30));
        String users[] = { "Select User", "Student", "Instructor", "Course Administrator" };
        userRole = new JComboBox<>(users);
        userRole.setBounds(49, 51, 89, 21);
        String levels[] = { "Select Level", "4", "5", "6" };
        selectLevel = new JComboBox<>(levels);
        userRole.setBounds(49, 51, 89, 21);
        selectCourse = new JComboBox<>();
        userRole.setBounds(49, 51, 89, 21);
        register = new JButton("Register");
        userRole.setSelectedItem(users[0]);
        login = new JButton("<HTML><U style='color: red;'>Already have a account?Login</U></HTML>");
        login.setBorderPainted(false);
        login.setContentAreaFilled(false);
        login.setMargin(new Insets(0, 0, 0, 0));
        login.setFocusPainted(false);
        courseTable = new courseTable();
        setBorder(BorderFactory.createTitledBorder("Register"));

        courseList();

    }

    private void courseList() {
        try {
            ResultSet resultSet = courseTable.openCourses();
            selectCourse.removeAllItems();
            selectCourse.addItem("Select Course Names");
            while (resultSet.next()) {
                selectCourse.addItem(resultSet.getString("courseName"));

            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(this, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
            error.printStackTrace();
        }
    }

    @Override
    public JPanel panelUI() {
        setLayout(new GridBagLayout());
        gridLayout = new GridBagConstraints();
        gridLayout.fill = GridBagConstraints.HORIZONTAL;
        gridLayout.insets = new Insets(5, 5, 5, 5);
        this.setBackground(Color.decode("#D6D9DE"));

        gridLayout.gridx = 0;
        gridLayout.gridy = 0;
        add(new JLabel("First Name"), gridLayout);

        gridLayout.weightx = 1;
        gridLayout.gridwidth = 3;
        gridLayout.gridx = 1;
        gridLayout.gridy = 0;
        add(fName, gridLayout);

        gridLayout.gridx = 0;
        gridLayout.gridy = 1;
        gridLayout.gridwidth = 1;
        add(new JLabel("Last Name"), gridLayout);

        gridLayout.gridx = 1;
        gridLayout.gridy = 1;
        gridLayout.gridwidth = 3;
        add(lName, gridLayout);

        gridLayout.gridx = 0;
        gridLayout.gridy = 2;
        gridLayout.gridwidth = 1;
        add(new JLabel("Email"), gridLayout);

        gridLayout.gridx = 1;
        gridLayout.gridy = 2;
        gridLayout.gridwidth = 3;
        add(email, gridLayout);

        gridLayout.gridx = 0;
        gridLayout.gridy = 3;
        gridLayout.gridwidth = 1;
        add(new JLabel("Password"), gridLayout);

        gridLayout.gridx = 1;
        gridLayout.gridy = 3;
        gridLayout.gridwidth = 3;
        add(password, gridLayout);

        gridLayout.gridx = 0;
        gridLayout.gridy = 4;
        gridLayout.gridwidth = 1;
        add(new JLabel("User Role"), gridLayout);

        gridLayout.gridx = 1;
        gridLayout.gridy = 4;
        gridLayout.gridwidth = 4;
        add(userRole, gridLayout);

        gridLayout.gridx = 0;
        gridLayout.gridy = 5;
        gridLayout.gridwidth = 1;
        course = new JLabel("Course");
        add(course, gridLayout);

        gridLayout.gridx = 1;
        gridLayout.gridy = 5;
        gridLayout.gridwidth = 4;
        add(selectCourse, gridLayout);

        gridLayout.gridx = 0;
        gridLayout.gridy = 6;
        gridLayout.gridwidth = 1;
        level = new JLabel("Level");
        add(level, gridLayout);

        gridLayout.gridx = 1;
        gridLayout.gridy = 6;
        gridLayout.gridwidth = 4;
        add(selectLevel, gridLayout);

        gridLayout.ipady = 5;
        gridLayout.gridx = 0;
        gridLayout.gridy = 8;
        gridLayout.gridwidth = 5;
        add(register, gridLayout);

        gridLayout.gridx = 0;
        gridLayout.gridy = 9;
        add(login, gridLayout);

        userRole.addActionListener(this);
        return this;
    }

    @Override
    public void actionPerformed(ActionEvent error) {
        String role = userRole.getSelectedItem().toString();

        if (role == "Student") {
            level.setVisible(true);
            selectCourse.setVisible(true);
            course.setVisible(true);
            selectLevel.setVisible(true);
        } else {
            course.setVisible(false);
            selectCourse.setVisible(false);
            selectLevel.setVisible(false);
            level.setVisible(false);
        }

    }

    public JTextField getEmail() {
        return email;
    }

    public JTextField getPassword() {
        return password;
    }

    public JTextField getUserFirstName() {
        return fName;
    }

    public JTextField getUserLastName() {
        return lName;
    }

    public JComboBox<String> getCourse() {
        return selectCourse;
    }

    public JComboBox<String> getLevel() {
        return selectLevel;
    }

    public JComboBox<String> getUserRole() {
        return userRole;
    }

    public JButton getRegisterBtn() {
        return register;
    }

    public JButton getLoginLabel() {
        return login;
    }
}
