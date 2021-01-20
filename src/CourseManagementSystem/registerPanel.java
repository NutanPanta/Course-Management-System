package CourseManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class registerPanel extends JPanel implements AppLayout, ActionListener {
    private JTextField firstName, lastName, registerEmail, registerPassword;
    private JLabel course,level;
    private JComboBox registerUserType,registerLevel,registerCourse;
    private JButton registerButton,loginLabel;
    private GridBagConstraints layout;

    public registerPanel() {
        setBorder(BorderFactory.createTitledBorder("Register"));
        firstName = new JTextField(20);
        firstName.setPreferredSize(new Dimension(40,30));
        lastName = new JTextField(20);
        lastName.setPreferredSize(new Dimension(40,30));
        registerEmail = new JTextField(20);
        registerEmail.setPreferredSize(new Dimension(40,30));
        registerPassword = new JPasswordField(20);
        registerPassword.setPreferredSize(new Dimension(40,30));
        String users[]={"Student","Instructor","Course Administrator"};
        registerUserType =new JComboBox(users);
        registerUserType.setBounds(50, 50,90,20);
        String ll[]={"4","5","6"};
        registerLevel =new JComboBox(ll);
        registerLevel.setBounds(50, 50,90,20);
        String ce[]={"BIT","BIM"};
        registerCourse =new JComboBox(ce);
        registerCourse.setBounds(50, 50,90,20);
        registerButton = new JButton("Register");
        registerUserType.setSelectedItem(users[0]);
        loginLabel = new JButton("<HTML><U style='color: blue;'>Already Registered?Login</U></HTML>");
        loginLabel.setFocusPainted(false);
        loginLabel.setMargin(new Insets(0, 0, 0, 0));
        loginLabel.setContentAreaFilled(false);
        loginLabel.setBorderPainted(false);

    }

    @Override
    public JPanel panelUI() {
        setLayout(new GridBagLayout());
        layout = new GridBagConstraints();
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.insets = new Insets(5,5,5,5);

        layout.gridx = 0;
        layout.gridy = 0;
        add(new JLabel("First Name"),layout);

        layout.weightx = 1;
        layout.gridwidth = 3;
        layout.gridx = 1;
        layout.gridy = 0;
        add(firstName,layout);

        layout.gridx=0;
        layout.gridy=1;
        layout.gridwidth = 1;
        add(new JLabel("Last Name"),layout);

        layout.gridx=1;
        layout.gridy=1;
        layout.gridwidth = 3;
        add(lastName,layout);

        layout.gridx=0;
        layout.gridy=2;
        layout.gridwidth = 1;
        add(new JLabel("Email"),layout);

        layout.gridx=1;
        layout.gridy=2;
        layout.gridwidth = 3;
        add(registerEmail,layout);

        layout.gridx=0;
        layout.gridy=3;
        layout.gridwidth = 1;
        add(new JLabel("Password"),layout);

        layout.gridx=1;
        layout.gridy=3;
        layout.gridwidth = 3;
        add(registerPassword,layout);

        layout.gridx=0;
        layout.gridy=4;
        layout.gridwidth = 1;
        add(new JLabel("User Type"),layout);

        layout.gridx=1;
        layout.gridy=4;
        layout.gridwidth = 4;
        add(registerUserType,layout);

        layout.gridx = 0;
        layout.gridy = 5;
        layout.gridwidth = 1;
        course = new JLabel("Course");
        add(course, layout);

        layout.gridx = 1;
        layout.gridy = 5;
        layout.gridwidth = 4;
        add(registerCourse, layout);

        layout.gridx = 0;
        layout.gridy = 6;
        layout.gridwidth = 1;
        level = new JLabel("Level");
        add(level, layout);

        layout.gridx = 1;
        layout.gridy = 6;
        layout.gridwidth = 4;
        add(registerLevel, layout);

        layout.ipady = 5;
        layout.gridx = 0;
        layout.gridy = 8;
        layout.gridwidth = 5;
        add(registerButton, layout);

        layout.gridx = 3;
        layout.gridy = 9;
        layout.insets = new Insets(3, 50, 3, 3);
        add(loginLabel, layout);

        registerUserType.addActionListener(this);
        return this;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String item = registerUserType.getSelectedItem().toString();

        if (item == "Student") {
            registerLevel.setVisible(true);
            registerCourse.setVisible(true);
            course.setVisible(true);
            level.setVisible(true);
        }  else {
            registerLevel.setVisible(false);
            registerCourse.setVisible(false);
            level.setVisible(false);
            course.setVisible(false);
        }

    }

    public JTextField getFirstName() {
        return  firstName;
    }
    public JTextField getLastName() {
        return  lastName;
    }
    public JTextField getRegistrationEmail() {
        return  registerEmail;
    }
    public JTextField getRegistrationPassword() {
        return registerPassword;
    }
    public JComboBox getRegistrationUserType() {
        return  registerUserType;
    }
    public JComboBox getRegistrationCourse() {
        return  registerCourse;
    }
    public JComboBox getRegistrationLevel() {
        return  registerLevel;
    }
    public JButton getRegisterButton() {
        return  registerButton;
    }
    public JButton getLoginLabel() { return loginLabel; }
}
