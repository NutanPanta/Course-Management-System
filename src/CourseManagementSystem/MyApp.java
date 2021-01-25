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
    CourseAdministrationLoggedInModulesPanel courseAdministrationLoggedInModulesPanel;
    CourseAdministrationLoggedInInstructorAddToModulePanel courseAdministrationLoggedInInstructorAddToModulePanel;
    courseTable coursetable;
    ModuleTable moduleTable;
    UserTable userTable;
    InstructorToModuleTable instructorToModuleTable;
    File file = new File("Files");
    int rln;

    MyApp self = this;

    public MyApp() {
        setVisible(true);
        setResizable(true);
        setTitle("Course Management System");
        setMinimumSize(new Dimension(900,550));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);

        LoginPanel = new loginPanel();
        RegisterPanel = new registerPanel();
        studentLoggedInMainPanel = new StudentLoggedInMainPanel();
        studentLoggedInCoursePanel = new StudentLoggedInCoursePanel();
        courseAdministrationLoggedInMainPanel = new CourseAdministrationLoggedInMainPanel();
        courseAdministrationLoggedInCoursesPanel = new CourseAdministrationLoggedInCoursesPanel();
        courseAdministrationLoggedInModulesPanel = new CourseAdministrationLoggedInModulesPanel();
        courseAdministrationLoggedInInstructorAddToModulePanel = new CourseAdministrationLoggedInInstructorAddToModulePanel();
        coursetable = new courseTable();
        moduleTable = new ModuleTable();
        userTable = new UserTable();
        instructorToModuleTable = new InstructorToModuleTable();

        LoginPanel.setVisible(false);
        RegisterPanel.setVisible(false);
        studentLoggedInMainPanel.setVisible(false);
        studentLoggedInCoursePanel.setVisible(false);
        courseAdministrationLoggedInMainPanel.setVisible(true);
        courseAdministrationLoggedInCoursesPanel.setVisible(false);
        courseAdministrationLoggedInModulesPanel.setVisible(false);
        courseAdministrationLoggedInInstructorAddToModulePanel.setVisible(false);

        add(appLayout());
        registerUsers();
        loginUser();

        implementCourses();
        implementModules();
        implementInstructorToModule();

        updateCourseName();
        updateCourseStatusToCancel();
        updateCourseStatusToOpen();
        updateModule();
        updateInstructorFromModel();

        deleteCourse();
        deleteModule();
        deleteInstructorFromModule();

        refreshCourseTable();
        refreshModuleTable();
        refreshCourseAdministratorInstructorTable();

        panelChange();
        pack();
        setLocationRelativeTo(null);
    }

//    All JPanel adding

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
        mainPanel.add(courseAdministrationLoggedInModulesPanel.panelUI(),layout);
        mainPanel.add(courseAdministrationLoggedInInstructorAddToModulePanel.panelUI(),layout);

        return mainPanel;
    }

//    Creating files folder in not exist

    private void createFolder() {
        if (!file.exists()) {
            file.mkdirs();
        }
    }

//    Different JPanel visible during different button clicks

    private void panelChange() {
        JButton registerLabel = LoginPanel.getRegisterLabel();
        JButton loginLabel = RegisterPanel.getLoginLabel();
//        Admin Main Panel Buttons
        JButton studentViewCoursesButton = studentLoggedInMainPanel.getViewCourses();
        JButton courseAdministratorCourseButton = courseAdministrationLoggedInMainPanel.getCourses();
        JButton courseAdministratorCourseModuleButton = courseAdministrationLoggedInMainPanel.getModules();
        JButton courseAdministratorInstructorAddToModuleButton = courseAdministrationLoggedInMainPanel.getAddInstructor();
//        Back Buttons
        JButton courseAdministratorCourseBackButton = courseAdministrationLoggedInCoursesPanel.getBack();
        JButton courseAdministratorModuleBackButton = courseAdministrationLoggedInModulesPanel.getBack();
        JButton courseAdministratorInstructorAddToModuleBackButton = courseAdministrationLoggedInInstructorAddToModulePanel.getBack();

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
//        Admin Main Panel Action Listeners
        courseAdministratorCourseButton.addActionListener(e -> {
            try {
                courseAdministrationLoggedInMainPanel.setVisible(false);
                courseAdministrationLoggedInCoursesPanel.setVisible(true);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);

            }
        });

        courseAdministratorCourseModuleButton.addActionListener(e -> {
            try {
                courseAdministrationLoggedInMainPanel.setVisible(false);
                courseAdministrationLoggedInModulesPanel.setVisible(true);
                courseAdministrationLoggedInModulesPanel.getCourseName();
                refreshModuleTable();
            } catch (Exception ex){
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);

            }
        });

        courseAdministratorInstructorAddToModuleButton.addActionListener(e -> {
            try {
                courseAdministrationLoggedInMainPanel.setVisible(false);
                courseAdministrationLoggedInInstructorAddToModulePanel.setVisible(true);
                courseAdministrationLoggedInInstructorAddToModulePanel.getRefreshModuleName();
                courseAdministrationLoggedInInstructorAddToModulePanel.getRefreshInstructorName();
                refreshCourseAdministratorInstructorTable();
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

        courseAdministratorModuleBackButton.addActionListener(e -> {
            try {
                courseAdministrationLoggedInModulesPanel.getModuleName().setText("");
                courseAdministrationLoggedInMainPanel.setVisible(true);
                courseAdministrationLoggedInModulesPanel.setVisible(false);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);

            }
        });

        courseAdministratorInstructorAddToModuleBackButton.addActionListener(e -> {
            try {
                courseAdministrationLoggedInMainPanel.setVisible(true);
                courseAdministrationLoggedInInstructorAddToModulePanel.setVisible(false);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);

            }
        });
    }

//    Read register.txt file

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

//    Add Registration Data to register.txt file

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

//    Validate Email During Registration

    private boolean emailValidation(String Email) {
        boolean valid = false;
        if (Email.contains("@")) {
            if (Email.contains(".com")) {
                valid = true;
            }
        }
        return valid;
    }

//    Check Duplicate Email During Registration

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

//    Read and validate login data

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

//    Validate register data

    private void checkRegistrationData(String firstName, String lastName, String Email, String Password, String userType,String course, String level){
        String registrationFirstName = RegisterPanel.getFirstName().getText().trim();
        String registrationLastName = RegisterPanel.getLastName().getText().trim();
        String registrationEmail = RegisterPanel.getRegistrationEmail().getText().trim();
        String registrationPassword = RegisterPanel.getRegistrationPassword().getText();
        String registrationUserType = Objects.requireNonNull(RegisterPanel.getRegistrationUserType().getSelectedItem()).toString();
        String registrationCourse = Objects.requireNonNull(RegisterPanel.getRegistrationCourse().getSelectedItem()).toString();
        String registrationLevel = Objects.requireNonNull(RegisterPanel.getRegistrationLevel().getSelectedItem()).toString();

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
                    userTable.insert(registrationFirstName,registrationLastName,registrationEmail,registrationPassword,registrationUserType,registrationCourse,registrationLevel);
                } else  {
                    userTable.insert(registrationFirstName,registrationLastName,registrationEmail,registrationPassword,registrationUserType,null,null);
                    addRegistrationData(registrationFirstName, registrationLastName, registrationEmail, registrationPassword, registrationUserType, null, null);
                }
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

//    Validate Login Data

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
            JOptionPane.showMessageDialog(this,"User Type or email is incorrect!!!");
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
            JOptionPane.showMessageDialog(this,"Email or Password or userType Did Not Match!!!");
        }
    }

//    Validate Course Data From Course Administrator

    private void checkCourseData(String courseName,String courseStatus) {
        String name = courseAdministrationLoggedInCoursesPanel.getCourseName().getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Complete all fields!!!");
        }  else {
            coursetable.insert(name,courseStatus);
            courseAdministrationLoggedInCoursesPanel.getCourseName().setText("");
        }
    }

//    Validate Module Data from Course Administrator

    private void checkModuleData(String courseName,String moduleName,String level,String elective, String semester) {
        String cname =  courseAdministrationLoggedInModulesPanel.getCourse().getSelectedItem().toString();
        String mname = courseAdministrationLoggedInModulesPanel.getModuleName().getText().trim();
        String lvl = courseAdministrationLoggedInModulesPanel.getLevel().getSelectedItem().toString();
        String sem = courseAdministrationLoggedInModulesPanel.getSemester().getSelectedItem().toString();
        String moduleType = courseAdministrationLoggedInModulesPanel.getIsElective().isSelected() ? "Elective" : "Compulsory";

        if (mname.isEmpty() || mname.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Complete all fields!!!");
        }  else {
                moduleTable.insert(mname,cname, lvl, moduleType, sem);
            courseAdministrationLoggedInModulesPanel.getModuleName().setText("");
        }
    }

    //    Validate Add Instructor TO Module Data From Course Administrator

    private void checkInstructorData(String moduleName,String instructorName) {
        String caModuleName = courseAdministrationLoggedInInstructorAddToModulePanel.getModuleName().getSelectedItem().toString();
        String caInstructorName = courseAdministrationLoggedInInstructorAddToModulePanel.getInstructorName().getSelectedItem().toString();

        if(caModuleName == "Select Module Names" &&  caInstructorName == "Select Instructor Names"){
            JOptionPane.showMessageDialog(self,"Please select module and instructor names and try again.");
        }else if (caModuleName == "Select Module Names"){
            JOptionPane.showMessageDialog(self,"No module is selected.");
        } else if(caInstructorName == "Select Instructor Names"){
            JOptionPane.showMessageDialog(self,"No Instructor is selected.");
        } else {
            instructorToModuleTable.insert(caModuleName,caInstructorName);
        }
    }

//    Count register.txt lines

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

//    Register

    private void registerUsers() {
        String registrationLastName = RegisterPanel.getLastName().getText().trim();
        String registrationFirstName = RegisterPanel.getFirstName().getText().trim();
        String registrationPassword = RegisterPanel.getRegistrationPassword().getText();
        String registrationEmail = RegisterPanel.getRegistrationEmail().getText().trim();
        String registrationUserType = Objects.requireNonNull(RegisterPanel.getRegistrationUserType().getSelectedItem()).toString();
        String registrationLevel = Objects.requireNonNull(RegisterPanel.getRegistrationLevel().getSelectedItem()).toString();
        String registrationCourse = Objects.requireNonNull(RegisterPanel.getRegistrationCourse().getSelectedItem()).toString();
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

//    Login

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

//    Add

    private void implementCourses() {
        String name = courseAdministrationLoggedInCoursesPanel.getCourseName().getText().trim();
        JButton addCourseBtn = courseAdministrationLoggedInCoursesPanel.getAddCourse();

        addCourseBtn.addActionListener(e -> {
            try {
                checkCourseData(name,"Open");
                refreshCourseTable();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void implementModules() {
        String cname =  courseAdministrationLoggedInModulesPanel.getCourse().getSelectedItem().toString();
        String mname = courseAdministrationLoggedInModulesPanel.getModuleName().getText().trim();
        String lvl = courseAdministrationLoggedInModulesPanel.getLevel().getSelectedItem().toString();
        String sem = courseAdministrationLoggedInModulesPanel.getLevel().getSelectedItem().toString();
        String courseType = courseAdministrationLoggedInModulesPanel.getIsElective().isSelected() ? "Elective" : "Compulsory";

        JButton addModuleBtn = courseAdministrationLoggedInModulesPanel.getAddModule();

        addModuleBtn.addActionListener(e -> {
            try {
                checkModuleData(cname,mname,lvl,sem,courseType);
                refreshModuleTable();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void implementInstructorToModule() {
        String moduleName = courseAdministrationLoggedInInstructorAddToModulePanel.getModuleName().getSelectedItem().toString();
        String instructorName = courseAdministrationLoggedInInstructorAddToModulePanel.getInstructorName().getSelectedItem().toString();
        JButton addInstructorToModuleBtn = courseAdministrationLoggedInInstructorAddToModulePanel.getAddInstructor();

        addInstructorToModuleBtn.addActionListener(e -> {
            try {
                checkInstructorData(moduleName,instructorName);
                refreshCourseAdministratorInstructorTable();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

//    Refresh Table

    private void refreshCourseTable() {
        courseAdministrationLoggedInCoursesPanel.getCourseModel().setRowCount(0);
        try {
            ResultSet resultSet = coursetable.getCourseData();
            while (resultSet.next()) {
                courseAdministrationLoggedInCoursesPanel.getCourseModel().addRow(new Object[]{
                        resultSet.getInt("courseId"),
                        resultSet.getString("courseName"),
                        resultSet.getString("courseStatus"),
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void refreshModuleTable() {
        courseAdministrationLoggedInModulesPanel.getCourseAdministratorModuleModel().setRowCount(0);
        try {
            ResultSet resultSet = moduleTable.getModuleData();
            while (resultSet.next()) {
                courseAdministrationLoggedInModulesPanel.getCourseAdministratorModuleModel().addRow(new Object[]{
                        resultSet.getInt("moduleId"),
                        resultSet.getString("moduleName"),
                        resultSet.getString("courseName"),
                        resultSet.getString("level"),
                        resultSet.getString("moduleType"),
                        resultSet.getString("semester"),

                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void refreshCourseAdministratorInstructorTable() {
        courseAdministrationLoggedInInstructorAddToModulePanel.getCourseAdministratorInstructorModel().setRowCount(0);
        try {
            ResultSet resultSet = instructorToModuleTable.getModuleDetails();
            while (resultSet.next()) {
                courseAdministrationLoggedInInstructorAddToModulePanel.getCourseAdministratorInstructorModel().addRow(new Object[]{
                        resultSet.getInt("id"),
                        resultSet.getString("courseName"),
                        resultSet.getString("moduleName"),
                        resultSet.getString("level"),
                        resultSet.getString("moduleType"),
                        resultSet.getString("semester"),
                        resultSet.getString("instructorName"),

                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

//    Update

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
                    JOptionPane.showMessageDialog(self, "Coding error", "Error", JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(self, "Coding error", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
    }

    private void updateModule(){
        JButton updateModuleBtn = courseAdministrationLoggedInModulesPanel.getUpdateModule();
        DefaultTableModel moduleModel = courseAdministrationLoggedInModulesPanel.getCourseAdministratorModuleModel();
        JTable dataTable = courseAdministrationLoggedInModulesPanel.getTable();
        dataTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = dataTable.getSelectedRow();
                courseAdministrationLoggedInModulesPanel.getModuleName().setText(moduleModel.getValueAt(selectedRow,1).toString());
                courseAdministrationLoggedInModulesPanel.getCourse().setSelectedItem(moduleModel.getValueAt(selectedRow, 2).toString());
                courseAdministrationLoggedInModulesPanel.getLevel().setSelectedItem(moduleModel.getValueAt(selectedRow, 3).toString());
                courseAdministrationLoggedInModulesPanel.getIsElective().setSelected(moduleModel.getValueAt(selectedRow, 4).toString().equals("Elective"));
                courseAdministrationLoggedInModulesPanel.getSemester().setSelectedItem(moduleModel.getValueAt(selectedRow, 5).toString());
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
        updateModuleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = dataTable.getSelectedRow();
                if (selectedRow==-1){
                    JOptionPane.showMessageDialog(self,"Please select the row from the table","Warning",JOptionPane.WARNING_MESSAGE);
                }
                try {
                    String mname = courseAdministrationLoggedInModulesPanel.getModuleName().getText().trim();
                    String cname =  courseAdministrationLoggedInModulesPanel.getCourse().getSelectedItem().toString();
                    String lvl = courseAdministrationLoggedInModulesPanel.getLevel().getSelectedItem().toString();
                    String sem = courseAdministrationLoggedInModulesPanel.getSemester().getSelectedItem().toString();
                    String courseType = courseAdministrationLoggedInModulesPanel.getIsElective().isSelected() ? "Elective" : "Compulsory";
                    if (mname.isEmpty()) {
                        JOptionPane.showMessageDialog(self, "Module Name is Empty", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    else{
                        int id = Integer.parseInt(moduleModel.getValueAt(selectedRow, 0).toString());
                        moduleTable.updateModule(id,mname,cname,lvl,courseType,sem);
                        refreshModuleTable();

                        courseAdministrationLoggedInModulesPanel.getModuleName().setText("");
                        dataTable.clearSelection();
                    }
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(self, "Coding error", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void updateInstructorFromModel(){
        JButton instructorFromModuleUpdateBtn = courseAdministrationLoggedInInstructorAddToModulePanel.getUpdateInstructor();
        DefaultTableModel courseAdministratorInstructorModel = courseAdministrationLoggedInInstructorAddToModulePanel.getCourseAdministratorInstructorModel();
        JTable dataTable = courseAdministrationLoggedInInstructorAddToModulePanel.getTable();
        dataTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = dataTable.getSelectedRow();
                courseAdministrationLoggedInInstructorAddToModulePanel.getModuleName().setSelectedItem(courseAdministratorInstructorModel.getValueAt(selectedRow, 0).toString());
                courseAdministrationLoggedInInstructorAddToModulePanel.getInstructorName().setSelectedItem(courseAdministratorInstructorModel.getValueAt(selectedRow, 1).toString());
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
        instructorFromModuleUpdateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = dataTable.getSelectedRow();
                if (selectedRow==-1){
                    JOptionPane.showMessageDialog(self,"Please select the row from table","Warning",JOptionPane.WARNING_MESSAGE);
                }
                try {
                    String moduleName = courseAdministrationLoggedInInstructorAddToModulePanel.getModuleName().getSelectedItem().toString();
                    String instructorName = courseAdministrationLoggedInInstructorAddToModulePanel.getInstructorName().getSelectedItem().toString();
                        int id = Integer.parseInt(courseAdministratorInstructorModel.getValueAt(selectedRow, 0).toString());
                        instructorToModuleTable.updateInstructorTeachingModules(id,moduleName,instructorName);
                        refreshCourseAdministratorInstructorTable();
                        dataTable.clearSelection();
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(self, "Coding error", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

//    Delete

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

    private void deleteModule(){
        DefaultTableModel moduleModel = courseAdministrationLoggedInModulesPanel.getCourseAdministratorModuleModel();
        JTable dataTable = courseAdministrationLoggedInModulesPanel.getTable();
        JButton moduleDeleteBtn = courseAdministrationLoggedInModulesPanel.getDeleteModule();

        moduleDeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(self,"Are you sure you want to Delete this data? ", "Confirmation",JOptionPane.YES_NO_OPTION);
                int selectedRow = dataTable.getSelectedRow();
                if (confirm==JOptionPane.YES_OPTION){
                    try {
                        courseAdministrationLoggedInModulesPanel.getModuleName().setText("");
                        int  moduleName=Integer.parseInt(moduleModel.getValueAt(selectedRow,0).toString());
                        moduleTable.deleteModule(moduleName);
                        refreshModuleTable();
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

    private void deleteInstructorFromModule(){
        DefaultTableModel instructorModuleModel = courseAdministrationLoggedInInstructorAddToModulePanel.getCourseAdministratorInstructorModel();
        JTable dataTable = courseAdministrationLoggedInInstructorAddToModulePanel.getTable();
        JButton instructorFromModuleDeleteBtn = courseAdministrationLoggedInInstructorAddToModulePanel.getDeleteInstructor();

        instructorFromModuleDeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(self,"Are you sure you want to Delete this data? ", "Confirmation",JOptionPane.YES_NO_OPTION);
                int selectedRow = dataTable.getSelectedRow();
                if (confirm==JOptionPane.YES_OPTION){
                    try {
                        int  id=Integer.parseInt(instructorModuleModel.getValueAt(selectedRow,0).toString());
                        instructorToModuleTable.deleteInstructorFromModule(id);
                        refreshCourseAdministratorInstructorTable();
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
