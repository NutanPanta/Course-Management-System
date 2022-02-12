package CourseManagementSystem;

import javax.swing.*;
import java.awt.*;

public class userLoginPanel extends JPanel implements AppLayout {
    private GridBagConstraints gridLayout;
    private JComboBox<String> userRole;
    private JTextField email, password;
    private JButton register;
    private JButton login;

    public userLoginPanel() {
        email = new JTextField(21);
        email.setPreferredSize(new Dimension(41, 31));
        password = new JTextField(21);
        password.setPreferredSize(new Dimension(43, 31));
        String users[] = { "Select User", "Student", "Instructor", "Course Administrator" };
        userRole = new JComboBox<>(users);
        userRole.setBounds(49, 51, 89, 21);
        login = new JButton("Login");
        register = new JButton("<HTML><U style='color: red;'>New User?Register</U></HTML>");
        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        register.setMargin(new Insets(0, 0, 0, 0));
        register.setFocusPainted(false);
        setOpaque(false);
        setBorder(BorderFactory.createTitledBorder("Login"));
    }

    @Override
    public JPanel panelUI() {
        setLayout(new GridBagLayout());
        gridLayout = new GridBagConstraints();
        gridLayout.insets = new Insets(5, 4, 5, 4);
        gridLayout.fill = GridBagConstraints.HORIZONTAL;

        gridLayout.gridx = 0;
        gridLayout.gridy = 0;
        add(new JLabel("Email"), gridLayout);

        gridLayout.gridx = 1;
        gridLayout.gridy = 0;
        gridLayout.weightx = 1;
        gridLayout.gridwidth = 4;
        add(email, gridLayout);

        gridLayout.gridx = 0;
        gridLayout.gridy = 1;
        gridLayout.gridwidth = 1;
        add(new JLabel("Password"), gridLayout);

        gridLayout.gridx = 1;
        gridLayout.gridy = 1;
        gridLayout.gridwidth = 4;
        add(password, gridLayout);

        gridLayout.gridx = 0;
        gridLayout.gridy = 2;
        gridLayout.gridwidth = 1;
        add(new JLabel("User Type"), gridLayout);

        gridLayout.gridx = 1;
        gridLayout.gridy = 2;
        gridLayout.gridwidth = 4;
        add(userRole, gridLayout);

        gridLayout.ipady = 5;
        gridLayout.gridx = 0;
        gridLayout.gridy = 3;
        gridLayout.gridwidth = 12;
        add(login, gridLayout);

        gridLayout.gridx = 0;
        gridLayout.gridy = 4;
        add(register, gridLayout);

        return this;
    }

    public JTextField getPassword() {
        return password;
    }

    public JTextField getEmail() {
        return email;
    }

    public JButton getLoginButton() {
        return login;
    }

    public JComboBox<String> getUserRole() {
        return userRole;
    }

    public JButton getRegistrationButton() {
        return register;
    }
}
