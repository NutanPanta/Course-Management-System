package CourseManagementSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyApp extends JFrame {

    loginPanel LoginPanel;
    registerPanel RegisterPanel;
    File file = new File("Files");
    int ln;

    MyApp self = this;

    public MyApp() {
        setVisible(true);
        setResizable(false);
        setTitle("Course Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LoginPanel = new loginPanel();
        RegisterPanel = new registerPanel();


        add(appLayout());
        registerUsers();
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel appLayout() {
        JPanel mainPanel = new JPanel();

        mainPanel.add(LoginPanel.panelUI());
        mainPanel.add(RegisterPanel.panelUI());

        return mainPanel;
    }

    private void createFolder() {
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private void readRegistrationFile() {
            try{
                FileReader fr = new FileReader(file + "\\register.txt");
            } catch (FileNotFoundException ex) {
                try {
                    FileWriter fw = new FileWriter(file + "\\register.txt");
                } catch (IOException ex1) {
                    ex1.printStackTrace();
                }
            }
    }

    private void addRegistrationData(String firstName, String lastName, String Email, char[] Password, String userType) {
        try{
            RandomAccessFile raf = new RandomAccessFile(file + "\\register.txt","rw");
            for(int i=0; i<ln; i++) {
                raf.readLine();
            }
            if(ln>0) {
                raf.writeBytes("\r\n");
            }
            raf.writeBytes("First Name: " + firstName + "\r\n");
            raf.writeBytes("Last Name: " + lastName + "\r\n");
            raf.writeBytes("Email: " + Email + "\r\n");
            raf.writeBytes("Password: " + Password + "\r\n");
            raf.writeBytes("User Type: " + userType + "\r\n");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean emailValidation(String Email) {
        boolean valid = false;
        if (Email.contains("@")) {
            if (Email.contains(".com")) {
                valid = true;
            }
        } else {
            valid = false;

        }
        return valid;
    }

    private boolean emailDuplication(String Email) {
        boolean isDuplicate = false;
        try {
            RandomAccessFile raf = new RandomAccessFile(file + "\\register.txt", "rw");
            for(int i=0;i<ln;i+=6) {
                for(int k = 1; k<=2; k++) {
                    raf.readLine();
                }
                String mail = raf.readLine().substring(7);

                if (Email.equals(mail)) {
                    isDuplicate = true;
                    break;
                } else {
                    isDuplicate = false;
                }
                for(int k = 1; k<=3; k++) {
                    raf.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isDuplicate;
    }

    private void checkRegistrationData(String firstName, String lastName, String Email, char[] Password, String userType){
        String registrationFirstName = RegisterPanel.getFirstName().getText().trim();
        String registrationLastName = RegisterPanel.getLastName().getText().trim();
        String registrationEmail = RegisterPanel.getRegistrationEmail().getText().trim();
        char[] registrationPassword = RegisterPanel.getRegistrationPassword().getPassword();
        String registrationUserType = RegisterPanel.getRegistrationUserType().getSelectedItem().toString().trim();

        boolean emailValidation = emailValidation(registrationEmail);
        boolean isDuplicate = emailDuplication(registrationEmail);
        if (registrationFirstName.isEmpty() && registrationLastName.isEmpty() && registrationEmail.isEmpty() && registrationPassword.length == 0 && registrationUserType.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are empty. Fill the form and try again.");
        } else if (registrationFirstName.isEmpty() || registrationLastName.isEmpty() || registrationEmail.isEmpty() || registrationPassword.length == 0 || registrationUserType.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete all fields and try again.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (emailValidation == false) {
            JOptionPane.showMessageDialog(this, "Enter a valid email");
        } else if (isDuplicate == true) {
            JOptionPane.showMessageDialog(this, "This user with this email has already been Registered. Please Login!!!");
        } else {
            addRegistrationData(registrationFirstName, registrationLastName, registrationEmail, registrationPassword, registrationUserType);
            JOptionPane.showMessageDialog(this, "You have been successfully registered. Thank You!!!");
        }
    }

    private void countRegistrationLines(){
        try {
            ln=0;
            RandomAccessFile raf = new RandomAccessFile(file + "\\register.txt","rw");
            for(int i=0;raf.readLine()!=null;i++){
                ln++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void registerUsers() {
        var registrationFirstName = RegisterPanel.getFirstName().getText().trim();
        var registrationLastName = RegisterPanel.getLastName().getText().trim();
        var registrationEmail = RegisterPanel.getRegistrationEmail().getText().trim();
        var registrationPassword = RegisterPanel.getRegistrationPassword().getPassword();
        var registrationUserType = RegisterPanel.getRegistrationUserType().getSelectedItem().toString().trim();
        JButton registerBtn = RegisterPanel.getRegisterButton();

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createFolder();
                    readRegistrationFile();
                    countRegistrationLines();
                    checkRegistrationData(registrationFirstName,registrationLastName,registrationEmail,registrationPassword,registrationUserType);
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(self, "Coding error", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void loginUser() {
        JButton loginBtn = LoginPanel.getLoginButton();

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(self, "Coding error", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }


    public static void main(String[] args) {
        new MyApp();
    }
}
