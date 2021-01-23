package CourseManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

public class MyApp extends JFrame {

    loginPanel LoginPanel;
    registerPanel RegisterPanel;
    StudentLoggedInMainPanel studentLoggedInMainPanel;
    StudentLoggedInCoursePanel studentLoggedInCoursePanel;
    CourseAdministrationLoggedInMainPanel courseAdministrationLoggedInMainPanel;
    CourseAdministrationLoggedInCoursesPanel courseAdministrationLoggedInCoursesPanel;
    courseTable coursetable;
    File file = new File("Files");
    int rln;

    MyApp self = this;

    public MyApp() {
        setVisible(true);
        setResizable(true);
        setTitle("Course Management System");
        setMinimumSize(new Dimension(800,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);

        LoginPanel = new loginPanel();
        RegisterPanel = new registerPanel();
        studentLoggedInMainPanel = new StudentLoggedInMainPanel();
        studentLoggedInCoursePanel = new StudentLoggedInCoursePanel();
        courseAdministrationLoggedInMainPanel = new CourseAdministrationLoggedInMainPanel();
        courseAdministrationLoggedInCoursesPanel = new CourseAdministrationLoggedInCoursesPanel();
        coursetable = new courseTable();
        LoginPanel.setVisible(false);
        RegisterPanel.setVisible(false);
        studentLoggedInMainPanel.setVisible(false);
        studentLoggedInCoursePanel.setVisible(false);
        courseAdministrationLoggedInMainPanel.setVisible(false);
        courseAdministrationLoggedInCoursesPanel.setVisible(true);

        add(appLayout());
        registerUsers();
        loginUser();
        implementCourses();
        updateCourseName();
        updateCourseStatusToCancel();
        updateCourseStatusToOpen();
        deleteCourse();
        refreshCourseTable();
        panelChange();
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel appLayout() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();
        layout.fill = GridBagConstraints.BOTH;

        mainPanel.add(LoginPanel.panelUI(),layout);
        mainPanel.add(RegisterPanel.panelUI(),layout);
        mainPanel.add(studentLoggedInMainPanel.panelUI(),layout);
        mainPanel.add(studentLoggedInCoursePanel.panelUI(),layout);
        mainPanel.add(courseAdministrationLoggedInMainPanel.panelUI(),layout);
        mainPanel.add(courseAdministrationLoggedInCoursesPanel.panelUI(),layout);

        return mainPanel;
    }

    private void createFolder() {
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private void panelChange() {
        JButton registerLabel = LoginPanel.getRegisterLabel();
        JButton loginLabel = RegisterPanel.getLoginLabel();
        JButton studentViewCoursesButton = studentLoggedInMainPanel.getViewCourses();
        JButton courseAdministratorCourseButton = courseAdministrationLoggedInMainPanel.getCourses();
        JButton courseAdministratorCourseBackButton = courseAdministrationLoggedInCoursesPanel.getBack();

        registerLabel.addActionListener(e -> {
            try {
                LoginPanel.getLoginEmail().setText("");
                LoginPanel.getLoginPassword().setText("");
                RegisterPanel.setVisible(true);
                LoginPanel.setVisible(false);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);

            }
        });

        loginLabel.addActionListener(e -> {
            try {
                RegisterPanel.getFirstName().setText("");
                RegisterPanel.getLastName().setText("");
                RegisterPanel.getRegistrationEmail().setText("");
                RegisterPanel.getRegistrationPassword().setText("");
                RegisterPanel.setVisible(false);
                LoginPanel.setVisible(true);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);

            }
        });

        studentViewCoursesButton.addActionListener(e -> {
            try {
                studentLoggedInMainPanel.setVisible(false);
                studentLoggedInCoursePanel.setVisible(true);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);

            }
        });

        courseAdministratorCourseButton.addActionListener(e -> {
            try {
                courseAdministrationLoggedInMainPanel.setVisible(false);
                courseAdministrationLoggedInCoursesPanel.setVisible(true);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);

            }
        });

        courseAdministratorCourseBackButton.addActionListener(e -> {
            try {
                courseAdministrationLoggedInCoursesPanel.getCourseName().setText("");
                courseAdministrationLoggedInMainPanel.setVisible(true);
                courseAdministrationLoggedInCoursesPanel.setVisible(false);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);

            }
        });
    }

    private void readRegistrationFile() {
            try{
                FileReader fr = new FileReader(file + "\\register.txt");
            } catch (FileNotFoundException ex) {
                try {
                    FileWriter fw = new FileWriter(file + "\\register.txt");
                } catch (IOException ex1) {
                    JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
    }

    private void addRegistrationData(String firstName, String lastName, String Email, String Password, String userType, String course, String level) {
        try{
            RandomAccessFile raf = new RandomAccessFile(file + "\\register.txt","rw");
            for(int i = 0; i< rln; i++) {
                raf.readLine();
            }
            if(rln >0) {
                raf.writeBytes("\r\n");
            }
            raf.writeBytes("First Name: " + firstName + "\r\n");
            raf.writeBytes("Last Name: " + lastName + "\r\n");
            raf.writeBytes("Email: " + Email + "\r\n");
            raf.writeBytes("Password: " + Password + "\r\n");
            raf.writeBytes("User Type: " + userType + "\r\n");
            raf.writeBytes("Course: " + course + "\r\n");
            raf.writeBytes("Level: " + level + "\r\n");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean emailValidation(String Email) {
        boolean valid = false;
        if (Email.contains("@")) {
            if (Email.contains(".com")) {
                valid = true;
            }
        }
        return valid;
    }

    private boolean emailDuplication(String Email) {
        boolean isEmailDuplicate = false;
        try {
            RandomAccessFile raf = new RandomAccessFile(file + "\\register.txt", "rw");
            for(int i = 0; i< rln; i+=8) {
                for(int k = 1; k<=2; k++) {
                    raf.readLine();
                }
                String mail = raf.readLine().substring(7);

                if (Email.equals(mail)) {
                    isEmailDuplicate = true;
                    break;
                }
                for(int k = 1; k<=5; k++) {
                    raf.readLine();
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return isEmailDuplicate;
    }

    private boolean administrationDuplication(String userType) {
        boolean isUserDuplicate = false;
        try {
            RandomAccessFile raf = new RandomAccessFile(file + "\\register.txt", "rw");
            for(int i = 0; i< rln; i+=8) {
                for(int k = 1; k<=4; k++) {
                    raf.readLine();
                }
                String user = raf.readLine().substring(11);

                if (userType.equals("Course Administrator")) {
                    isUserDuplicate = true;
                    break;
                }
                for(int k = 1; k<=3; k++) {
                    raf.readLine();
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return isUserDuplicate;
    }

    private boolean readAndValidateLoginData(String Email, String pwd) throws IOException {
        String loginEmail = LoginPanel.getLoginEmail().getText().trim();
        String loginPassword = LoginPanel.getLoginPassword().getText();

        HashMap<String, String> hash_map = new HashMap<>();
        BufferedReader read = new BufferedReader(new FileReader(file + "\\register.txt"));

        for(int i = 0; i< rln; i+=8) {
            for(int k = 1; k<=2; k++) {
                read.readLine();
            }
            String mail = read.readLine().substring(7);
            String password = read.readLine().substring(10);

            String key = mail;
            String value = password;
            hash_map.put(key, value);

            for(int k = 1; k<=4; k++) {
                read.readLine();
            }
        }
        read.close();

        boolean matchLoginData = false;
        if (hash_map.containsKey(loginEmail)) {
            String passwordForEmail = hash_map.get(loginEmail); // get the hash_map value that is associated to the key loginEmail -> this should be the password that belongs to the email
            matchLoginData = loginPassword.equals(passwordForEmail);
        }
        return matchLoginData;
    }

    private boolean readAndValidateLoginUserType(String Email, String userType) throws IOException {
        String loginEmail = LoginPanel.getLoginEmail().getText().trim();
        String loginUserType = Objects.requireNonNull(LoginPanel.getLoginUserType().getSelectedItem()).toString();

        HashMap<String, String> hash_map1 = new HashMap<>();
        BufferedReader read = new BufferedReader(new FileReader(file + "\\register.txt"));

        for(int i = 0; i< rln; i+=8) {
            for(int k = 1; k<=2; k++) {
                read.readLine();
            }
            String mail = read.readLine().substring(7);
            read.readLine();
            String type = read.readLine().substring(11);

            String key = mail;
            String value = type;
            hash_map1.put(key, value);

            for(int k = 1; k<=3; k++) {
                read.readLine();
            }
        }
        read.close();
        boolean matchUserType = false;
        if (hash_map1.containsKey(loginEmail)) {
            String userTypeForEmail = hash_map1.get(loginEmail); // get the hash_map value that is associated to the key loginEmail -> this should be the userType that belongs to the email
            matchUserType = loginUserType.equals(userTypeForEmail);
        }
        return matchUserType;
    }

    private void checkRegistrationData(String firstName, String lastName, String Email, String Password, String userType,String course, String level){
        String registrationFirstName = RegisterPanel.getFirstName().getText().trim();
        String registrationLastName = RegisterPanel.getLastName().getText().trim();
        String registrationEmail = RegisterPanel.getRegistrationEmail().getText().trim();
        String registrationPassword = RegisterPanel.getRegistrationPassword().getText();
        String registrationUserType = Objects.requireNonNull(RegisterPanel.getRegistrationUserType().getSelectedItem()).toString();
        String registrationCourse = Objects.requireNonNull(RegisterPanel.getRegistrationCourse().getSelectedItem()).toString();
        String registrationLevel = Objects.requireNonNull(RegisterPanel.getRegistrationLevel().getSelectedItem()).toString();

        System.out.println(registrationFirstName);
        boolean emailValidation = emailValidation(registrationEmail);
        boolean isEmailDuplicate = emailDuplication(registrationEmail);
        boolean isUserDuplicate = administrationDuplication(registrationUserType);
            if (registrationFirstName.isEmpty() && registrationLastName.isEmpty() && registrationEmail.isEmpty() && registrationPassword.isEmpty() && registrationUserType.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are empty. Fill the form and try again.");
            } else if (registrationFirstName.isEmpty() || registrationLastName.isEmpty() || registrationEmail.isEmpty() || registrationPassword.isEmpty() || registrationUserType.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete all fields and try again.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!emailValidation) {
                JOptionPane.showMessageDialog(this, "Enter a valid email");
            } else if (isEmailDuplicate) {
                JOptionPane.showMessageDialog(this, "This user with this email has already been Registered. Please Login!!!");
            } else if (isUserDuplicate) {
                JOptionPane.showMessageDialog(this, "There is already one course administrator. Please Login!!!");
            } else {
                if(RegisterPanel.getRegistrationCourse().isVisible()){
                addRegistrationData(registrationFirstName, registrationLastName, registrationEmail, registrationPassword, registrationUserType, registrationCourse, registrationLevel);
                } else  {
                    addRegistrationData(registrationFirstName, registrationLastName, registrationEmail, registrationPassword, registrationUserType, "Empty", "Empty");
                }
                JOptionPane.showMessageDialog(this, "You have been successfully registered. Thank You!!!");
                RegisterPanel.getFirstName().setText("");
                RegisterPanel.getLastName().setText("");
                RegisterPanel.getRegistrationEmail().setText("");
                RegisterPanel.getRegistrationPassword().setText("");
                RegisterPanel.getLastName().setText("");
                RegisterPanel.getRegistrationEmail().setText("");
                RegisterPanel.setVisible(false);
                LoginPanel.setVisible(true);
            }
    }

    private void checkLoginData(String Email, String pwd,String userType) throws IOException {
        String loginEmail = LoginPanel.getLoginEmail().getText().trim();
        String loginPassword = LoginPanel.getLoginPassword().getText().trim();
        String loginUserType = Objects.requireNonNull(LoginPanel.getLoginUserType().getSelectedItem()).toString();
        boolean matchLoginData = readAndValidateLoginData(loginEmail, loginPassword);
        boolean  matchUserType = readAndValidateLoginUserType(loginEmail,loginUserType);


        if(loginEmail.isEmpty() &&
                loginPassword.isEmpty()){
            JOptionPane.showMessageDialog(this,"Complete all fields!!!");
        } else if(loginEmail.isEmpty()){
            JOptionPane.showMessageDialog(this,"Email field is empty!!!");
        } else if(loginPassword.isEmpty()){
            JOptionPane.showMessageDialog(this,"Password field is empty!!!");
        } else if(!matchUserType){
            JOptionPane.showMessageDialog(this,"User Type is incorrect!!!");
        } else if (matchLoginData == true && loginUserType == "Student" && matchUserType) {
            JOptionPane.showMessageDialog(this,"You are logged in as Student!!!");
            LoginPanel.getLoginEmail().setText("");
            LoginPanel.getLoginPassword().setText("");
            studentLoggedInMainPanel.setVisible(true);
            RegisterPanel.setVisible(false);
            LoginPanel.setVisible(false);
        } else if(matchLoginData == true && loginUserType == "Course Administrator"  && matchUserType) {
            JOptionPane.showMessageDialog(this,"You are logged in as Course Administrator!!!");
            LoginPanel.getLoginEmail().setText("");
            LoginPanel.getLoginPassword().setText("");
            courseAdministrationLoggedInMainPanel.setVisible(true);
            RegisterPanel.setVisible(false);
            LoginPanel.setVisible(false);
        }
        else {
            JOptionPane.showMessageDialog(this,"Email or Password Did Not Match!!!");
        }
    }

    private void checkModuleData(String courseName,String courseStatus) {
        String name = courseAdministrationLoggedInCoursesPanel.getCourseName().getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Complete all fields!!!");
        }  else {
            coursetable.insert(name,courseStatus);
            JOptionPane.showMessageDialog(this, "You have been successfully registered. Thank You!!!");
            courseAdministrationLoggedInCoursesPanel.getCourseName().setText("");
        }
    }

    private void countRegistrationLines(){
        try {
            rln =0;
            RandomAccessFile raf = new RandomAccessFile(file + "\\register.txt","rw");
            for(int i=0;raf.readLine()!=null;i++){
                rln++;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void registerUsers() {
        String registrationFirstName = RegisterPanel.getFirstName().getText().trim();
        String registrationLastName = RegisterPanel.getLastName().getText().trim();
        String registrationEmail = RegisterPanel.getRegistrationEmail().getText().trim();
        String registrationPassword = RegisterPanel.getRegistrationPassword().getText();
        String registrationUserType = Objects.requireNonNull(RegisterPanel.getRegistrationUserType().getSelectedItem()).toString();
        String registrationCourse = Objects.requireNonNull(RegisterPanel.getRegistrationCourse().getSelectedItem()).toString();
        String registrationLevel = Objects.requireNonNull(RegisterPanel.getRegistrationLevel().getSelectedItem()).toString();
        JButton registerBtn = RegisterPanel.getRegisterButton();

        registerBtn.addActionListener(e -> {
            try {

                createFolder();
                readRegistrationFile();
                countRegistrationLines();
                checkRegistrationData(registrationFirstName,registrationLastName,registrationEmail,registrationPassword,registrationUserType,registrationCourse,registrationLevel);

            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void loginUser() {
        String loginEmail = LoginPanel.getLoginEmail().getText().trim();
        String loginPassword = LoginPanel.getLoginPassword().getText().trim();
        String loginUserType = Objects.requireNonNull(LoginPanel.getLoginUserType().getSelectedItem()).toString();
        JButton loginBtn = LoginPanel.getLoginButton();

        loginBtn.addActionListener(e -> {
            try {
                countRegistrationLines();
                checkLoginData(loginEmail, loginPassword, loginUserType);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void implementCourses() {
        String name = courseAdministrationLoggedInCoursesPanel.getCourseName().getText().trim();
        JButton addCourseBtn = courseAdministrationLoggedInCoursesPanel.getAddCourse();

        addCourseBtn.addActionListener(e -> {
            try {
                checkModuleData(name,"Open");
                refreshCourseTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void refreshCourseTable() {
        courseAdministrationLoggedInCoursesPanel.getCourseModel().setRowCount(0);
        try {
            ResultSet resultSet = coursetable.getCourseData();
            while (resultSet.next()) {
                courseAdministrationLoggedInCoursesPanel.getCourseModel().addRow(new Object[]{
                        resultSet.getInt("courseid"),
                        resultSet.getString("courseName"),
                        resultSet.getString("courseStatus"),

                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void updateCourseName(){
        JButton updateCourseBtn = courseAdministrationLoggedInCoursesPanel.getUpdateCourse();
        DefaultTableModel courseModel = courseAdministrationLoggedInCoursesPanel.getCourseModel();
        JTable dataTable = courseAdministrationLoggedInCoursesPanel.getTable();
        dataTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = dataTable.getSelectedRow();
                courseAdministrationLoggedInCoursesPanel.getCourseName().setText(courseModel.getValueAt(selectedRow,1).toString());
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        updateCourseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = dataTable.getSelectedRow();
                if (selectedRow==-1){
                    JOptionPane.showMessageDialog(self,"Please select the row from table","Warning",JOptionPane.WARNING_MESSAGE);
                }
                try {
                    String courseName = courseAdministrationLoggedInCoursesPanel.getCourseName().getText().trim();
                    if (courseName.isEmpty()) {
                        JOptionPane.showMessageDialog(self, "Please enter the valid Data", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    else{
                        int id = Integer.parseInt(courseModel.getValueAt(selectedRow, 0).toString());
                        coursetable.updateCourseName(id,courseName);
                        refreshCourseTable();
                        JOptionPane.showMessageDialog(self,"The data has been update successfully", "Success", JOptionPane.INFORMATION_MESSAGE  );
                        courseAdministrationLoggedInCoursesPanel.getCourseName().setText("");
                        dataTable.clearSelection();
                    }
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(self, "Coding error", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void updateCourseStatusToCancel(){
        JButton cancelCourseBtn = courseAdministrationLoggedInCoursesPanel.getCancelCourse();
        DefaultTableModel courseModel = courseAdministrationLoggedInCoursesPanel.getCourseModel();
        JTable dataTable = courseAdministrationLoggedInCoursesPanel.getTable();
        dataTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = dataTable.getSelectedRow();
                courseAdministrationLoggedInCoursesPanel.getCourseName().setText(courseModel.getValueAt(selectedRow,1).toString());
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        cancelCourseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = dataTable.getSelectedRow();
                if (selectedRow == -1){
                    JOptionPane.showMessageDialog(self,"Please select the row from table to cancel","Warning",JOptionPane.WARNING_MESSAGE);
                }else {
                    try {
                        String status = courseModel.getValueAt(selectedRow, 2).toString();
                        if(status.equals("Cancel")){
                            JOptionPane.showMessageDialog(self,"THe data you have selected is already canceled.");
                        } else {
                            int id = Integer.parseInt(courseModel.getValueAt(selectedRow, 0).toString());
                            coursetable.updateCourseStatus(id,"Cancel");
                            refreshCourseTable();
                            JOptionPane.showMessageDialog(self,"The data has been updated to cancel successfully", "Success", JOptionPane.INFORMATION_MESSAGE  );
                            courseAdministrationLoggedInCoursesPanel.getCourseName().setText("");
                            dataTable.clearSelection();
                        }
                    }
                    catch (Exception ex){
//                    JOptionPane.showMessageDialog(self, "Coding error", "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }

            }
        });
    }

    private void updateCourseStatusToOpen(){
        JButton openCourseBtn = courseAdministrationLoggedInCoursesPanel.getOpenCourse();
        DefaultTableModel courseModel = courseAdministrationLoggedInCoursesPanel.getCourseModel();
        JTable dataTable = courseAdministrationLoggedInCoursesPanel.getTable();
        dataTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = dataTable.getSelectedRow();
                courseAdministrationLoggedInCoursesPanel.getCourseName().setText(courseModel.getValueAt(selectedRow,1).toString());
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        openCourseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = dataTable.getSelectedRow();
                if (selectedRow == -1){
                    JOptionPane.showMessageDialog(self,"Please select the row from table to open","Warning",JOptionPane.WARNING_MESSAGE);
                }else {
                    try {
                        String status = courseModel.getValueAt(selectedRow, 2).toString();
                        if(status.equals("Open")){
                            JOptionPane.showMessageDialog(self,"THe data you have selected is already open.");
                        } else {
                            int id = Integer.parseInt(courseModel.getValueAt(selectedRow, 0).toString());
                            coursetable.updateCourseStatus(id,"Open");
                            refreshCourseTable();
                            JOptionPane.showMessageDialog(self,"The data has been updated to open successfully", "Success", JOptionPane.INFORMATION_MESSAGE  );
                            courseAdministrationLoggedInCoursesPanel.getCourseName().setText("");
                            dataTable.clearSelection();
                        }
                    }
                    catch (Exception ex){
//                    JOptionPane.showMessageDialog(self, "Coding error", "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }

            }
        });
    }

    private void deleteCourse(){
        JButton courseDeleteBtn = courseAdministrationLoggedInCoursesPanel.getDeleteCourse();
        DefaultTableModel courseModel = courseAdministrationLoggedInCoursesPanel.getCourseModel();
        JTable dataTable = courseAdministrationLoggedInCoursesPanel.getTable();
        courseDeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(self,"Are you sure you want to Delete this data? ", "Confirmation",JOptionPane.YES_NO_OPTION);
                int selectedRow = dataTable.getSelectedRow();
                if (confirm==JOptionPane.YES_OPTION){
                    try {
                        courseAdministrationLoggedInCoursesPanel.getCourseName().setText("");
                        int  courseName=Integer.parseInt(courseModel.getValueAt(selectedRow,0).toString());
                        coursetable.deleteCourse(courseName);
                        refreshCourseTable();
                    }
                    catch (Exception ex){
                        JOptionPane.showMessageDialog(self, "Please select a row", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    return;
                }
            }
        });
    }

    public static void main(String[] args) {
        new MyApp();
    }
}
