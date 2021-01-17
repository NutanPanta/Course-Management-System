package CourseManagementSystem;

import javax.swing.*;
import java.awt.*;

public class loginPanel extends JPanel implements AppLayout {
    private JTextField emailTextField;
    private JPasswordField passwordField;
    private JComboBox userType;
    private JButton loginButton;
    private GridBagConstraints layout;

    public loginPanel() {
        setBorder(BorderFactory.createTitledBorder("Login"));
        emailTextField = new JTextField(20);
        emailTextField.setPreferredSize(new Dimension(40,30));
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(40,30));
        String users[]={"Student","Instructor","Course Administrator"};
        userType =new JComboBox(users);
        userType.setBounds(50, 50,90,20);
        loginButton = new JButton("Login");
    }
    @Override
    public JPanel panelUI() {
        setLayout(new GridBagLayout());
        layout = new GridBagConstraints();
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.insets = new Insets(5,5,5,5);

        layout.gridx = 0;
        layout.gridy = 0;
        add(new JLabel("Email"),layout);

        layout.weightx = 1;
        layout.gridwidth = 3;
        layout.gridx = 1;
        layout.gridy = 0;
        add(emailTextField,layout);

        layout.gridx=0;
        layout.gridy=1;
        layout.gridwidth = 1;
        add(new JLabel("Password"),layout);

        layout.gridx=1;
        layout.gridy=1;
        layout.gridwidth = 3;
        add(passwordField,layout);

        layout.gridx=0;
        layout.gridy=2;
        layout.gridwidth = 1;
        add(new JLabel("User Type"),layout);

        layout.gridx=1;
        layout.gridy=2;
        layout.gridwidth = 3;
        add(userType,layout);

        layout.ipady = 5;
        layout.gridx=0;
        layout.gridy=3;
        layout.gridwidth = 5;
        add(loginButton,layout);

        return this;
    }

    public JTextField getEmail() {
        return  emailTextField;
    }
    public JTextField getPassword() {
        return  passwordField;
    }
    public JComboBox getUserType() {
        return  userType;
    }
    public JButton getLoginButton() {
        return  loginButton;
    }
}
