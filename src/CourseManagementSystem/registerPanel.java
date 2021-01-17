package CourseManagementSystem;

import javax.swing.*;
import java.awt.*;

public class registerPanel extends JPanel implements AppLayout  {
    private JTextField firstName, lastName, registerEmail;
    private JPasswordField registerPassowrd;
    private JComboBox registerUserType;
    private JButton registerButton;
    private GridBagConstraints layout;

    public registerPanel() {
        setBorder(BorderFactory.createTitledBorder("Register"));
        firstName = new JTextField(20);
        firstName.setPreferredSize(new Dimension(40,30));
        lastName = new JTextField(20);
        lastName.setPreferredSize(new Dimension(40,30));
        registerEmail = new JTextField(20);
        registerEmail.setPreferredSize(new Dimension(40,30));
        registerPassowrd = new JPasswordField(20);
        registerPassowrd.setPreferredSize(new Dimension(40,30));
        String users[]={"Student","Instructor","Course Administrator"};
        registerUserType =new JComboBox(users);
        registerUserType.setBounds(50, 50,90,20);
        registerButton = new JButton("Register");
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
        add(registerPassowrd,layout);

        layout.gridx=0;
        layout.gridy=4;
        layout.gridwidth = 1;
        add(new JLabel("User Type"),layout);

        layout.gridx=1;
        layout.gridy=4;
        layout.gridwidth = 4;
        add(registerUserType,layout);

        layout.ipady = 5;
        layout.gridx=0;
        layout.gridy=5;
        layout.gridwidth = 5;
        add(registerButton,layout);

        return this;
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
    public JPasswordField getRegistrationPassword() {
        return  registerPassowrd;
    }
    public JComboBox getRegistrationUserType() {
        return  registerUserType;
    }
    public JButton getRegisterButton() {
        return  registerButton;
    }

}
