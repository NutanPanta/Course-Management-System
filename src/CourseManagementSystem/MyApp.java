package CourseManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

public class MyApp extends JFrame {

    loginPanel LoginPanel;
    registerPanel RegisterPanel;
    StudentLoggedInCoursePanel studentLoggedInCoursePanel;
    StudentPanelViewResultPanel studentPanelViewResultPanel;
    CourseAdministrationLoggedInMainPanel courseAdministrationLoggedInMainPanel;
    CourseAdministrationLoggedInCoursesPanel courseAdministrationLoggedInCoursesPanel;
    CourseAdministrationLoggedInModulesPanel courseAdministrationLoggedInModulesPanel;
    CourseAdministrationLoggedInInstructorAddToModulePanel courseAdministrationLoggedInInstructorAddToModulePanel;
    CourseAdministratorGenerateMarksPanel courseAdministratorGenerateMarksPanel;
    InstructorPanel instructorPanel;
    courseTable coursetable;
    ModuleTable moduleTable;
    UserTable userTable;
    InstructorPanelTable instructorPanelTable;
    StudentCourseTable studentCourseTable;
    InstructorToModuleTable instructorToModuleTable;
    File file = new File("Files");
    int rln;

    MyApp self = this;

    public MyApp() {
        setVisible(true);
        setResizable(true);
        setTitle("Course Management System");
        setMinimumSize(new Dimension(800,520));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);

        LoginPanel = new loginPanel();
        RegisterPanel = new registerPanel();
        studentLoggedInCoursePanel = new StudentLoggedInCoursePanel();
        studentPanelViewResultPanel = new StudentPanelViewResultPanel();
        courseAdministrationLoggedInMainPanel = new CourseAdministrationLoggedInMainPanel();
        courseAdministrationLoggedInCoursesPanel = new CourseAdministrationLoggedInCoursesPanel();
        courseAdministrationLoggedInModulesPanel = new CourseAdministrationLoggedInModulesPanel();
        courseAdministrationLoggedInInstructorAddToModulePanel = new CourseAdministrationLoggedInInstructorAddToModulePanel();
        courseAdministratorGenerateMarksPanel = new CourseAdministratorGenerateMarksPanel();
        instructorPanel = new InstructorPanel();

        coursetable = new courseTable();
        moduleTable = new ModuleTable();
        userTable = new UserTable();
        studentCourseTable = new StudentCourseTable();
        instructorToModuleTable = new InstructorToModuleTable();
        instructorPanelTable = new InstructorPanelTable();

        LoginPanel.setVisible(true);
        RegisterPanel.setVisible(false);
        studentLoggedInCoursePanel.setVisible(false);
        studentPanelViewResultPanel.setVisible(false);
        instructorPanel.setVisible(false);
        courseAdministrationLoggedInMainPanel.setVisible(false);
        courseAdministrationLoggedInCoursesPanel.setVisible(false);
        courseAdministrationLoggedInModulesPanel.setVisible(false);
        courseAdministrationLoggedInInstructorAddToModulePanel.setVisible(false);
        courseAdministratorGenerateMarksPanel.setVisible(false);

        add(appLayout());
        registerUsers();
        loginUser();

        implementCourses();
        implementModules();
        implementInstructorToModule();
        implementElectiveSubjects();
        implementInstructorAddMarks();

        updateCourseName();
        updateCourseStatusToCancel();
        updateCourseStatusToOpen();
        updateModule();
        updateInstructorFromModel();
        updateObtainedMarks();

        deleteCourse();
        deleteModule();
        deleteInstructorFromModule();

        refreshCourseTable();
        refreshModuleTable();
        refreshCourseAdministratorInstructorTable();
        refreshStudentPanelCourseTable();
        refreshInstructorMarksTable();

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
        mainPanel.setBackground(Color.decode("#D6D9DF"));

        mainPanel.add(LoginPanel.panelUI(),layout);
        mainPanel.add(RegisterPanel.panelUI(),layout);
        mainPanel.add(studentLoggedInCoursePanel.panelUI(),layout);
        mainPanel.add(studentPanelViewResultPanel.panelUI(),layout);
        mainPanel.add(instructorPanel.panelUI(),layout);
        mainPanel.add(courseAdministrationLoggedInMainPanel.panelUI(),layout);
        mainPanel.add(courseAdministrationLoggedInCoursesPanel.panelUI(),layout);
        mainPanel.add(courseAdministrationLoggedInModulesPanel.panelUI(),layout);
        mainPanel.add(courseAdministrationLoggedInInstructorAddToModulePanel.panelUI(),layout);
        mainPanel.add(courseAdministratorGenerateMarksPanel.panelUI(),layout);

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
        JButton courseAdministratorCourseButton = courseAdministrationLoggedInMainPanel.getCourses();
        JButton courseAdministratorCourseModuleButton = courseAdministrationLoggedInMainPanel.getModules();
        JButton courseAdministratorInstructorAddToModuleButton = courseAdministrationLoggedInMainPanel.getAddInstructor();
        JButton courseAdministratorInstructorGenerateMarksButton = courseAdministrationLoggedInMainPanel.getGenerateMarkSheet();
//        Student Panel View Result Button
        JButton studentPanelViewResultButton = studentLoggedInCoursePanel.getViewResult();
//        Back Buttons
        JButton studentPanelResultBackButton = studentPanelViewResultPanel.getBack();
        JButton courseAdministratorCourseBackButton = courseAdministrationLoggedInCoursesPanel.getBack();
        JButton courseAdministratorModuleBackButton = courseAdministrationLoggedInModulesPanel.getBack();
        JButton courseAdministratorInstructorAddToModuleBackButton = courseAdministrationLoggedInInstructorAddToModulePanel.getBack();
        JButton courseAdministratorGenerateMarksBackButton = courseAdministratorGenerateMarksPanel.getBack();
//        Logout Buttons
        JButton studentLogoutButton = studentLoggedInCoursePanel.getLogout();
        JButton courseAdministratorLogoutButton = courseAdministrationLoggedInMainPanel.getLogOut();
        JButton instructorPanelLogoutButton = instructorPanel.getLogout();

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

//        Student Panel Action Listeners
        studentPanelViewResultButton.addActionListener(e -> {
            try {
                studentLoggedInCoursePanel.setVisible(false);
                studentPanelViewResultPanel.setVisible(true);
                this.setMinimumSize(new Dimension(800,800));
                pack();
                setLocationRelativeTo(null);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);

            }
        });

        studentPanelResultBackButton.addActionListener(e -> {
            try {
                studentLoggedInCoursePanel.setVisible(true);
                studentPanelViewResultPanel.setVisible(false);
                this.setMinimumSize(new Dimension(820,520));
                pack();
                setLocationRelativeTo(null);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);

            }
        });

//        Admin Main Panel Action Listeners
        courseAdministratorCourseButton.addActionListener(e -> {
            try {
                courseAdministrationLoggedInMainPanel.setVisible(false);
                courseAdministrationLoggedInCoursesPanel.setVisible(true);
                this.setMinimumSize(new Dimension(780,480));
                pack();
                setLocationRelativeTo(null);
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
                this.setMinimumSize(new Dimension(800,520));
                pack();
                setLocationRelativeTo(null);
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
                this.setMinimumSize(new Dimension(820,520));
                pack();
                setLocationRelativeTo(null);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);

            }
        });

        courseAdministratorInstructorGenerateMarksButton.addActionListener(e -> {
            try {
                courseAdministrationLoggedInMainPanel.setVisible(false);
                courseAdministratorGenerateMarksPanel.setVisible(true);
                courseAdministratorGenerateMarksPanel.getCourseName();
                this.setMinimumSize(new Dimension(800,800));
                pack();
                setLocationRelativeTo(null);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();

            }
        });

//        Admin Inside Panels Back Buttons Action Listeners

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

        courseAdministratorGenerateMarksBackButton.addActionListener(e -> {
            try {
                courseAdministrationLoggedInMainPanel.setVisible(true);
                courseAdministratorGenerateMarksPanel.setVisible(false);
                setMinimumSize(new Dimension(800,520));
                pack();
                setLocationRelativeTo(null);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);

            }
        });

//        Logout from main panels from different users Action Listeners

        studentLogoutButton.addActionListener(e -> {
            try {
                studentLoggedInCoursePanel.setVisible(false);
                LoginPanel.setVisible(true);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);

            }
        });

        courseAdministratorLogoutButton.addActionListener(e -> {
            try {
                courseAdministrationLoggedInMainPanel.setVisible(false);
                LoginPanel.setVisible(true);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);

            }
        });

        instructorPanelLogoutButton.addActionListener(e -> {
            try {
                instructorPanel.setVisible(false);
                LoginPanel.setVisible(true);
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

            hash_map.put(mail, password);

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

            String value = read.readLine().substring(11);
            hash_map1.put(mail, value);

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

    private void checkLoginData(String Email, String pwd,String userType) throws IOException, SQLException {
        String loginEmail = LoginPanel.getLoginEmail().getText().trim();
        String loginPassword = LoginPanel.getLoginPassword().getText().trim();
        String loginUserType = Objects.requireNonNull(LoginPanel.getLoginUserType().getSelectedItem()).toString();
        boolean matchLoginData = readAndValidateLoginData(loginEmail, loginPassword);
        boolean  matchUserType = readAndValidateLoginUserType(loginEmail,loginUserType);


        if(loginEmail.isEmpty() && loginPassword.isEmpty()){
            JOptionPane.showMessageDialog(this,"Complete all fields!!!");
        } else if(loginEmail.isEmpty()){
            JOptionPane.showMessageDialog(this,"Email field is empty!!!");
        } else if(loginPassword.isEmpty()){
            JOptionPane.showMessageDialog(this,"Password field is empty!!!");
        } else if(!matchUserType){
            JOptionPane.showMessageDialog(this,"User Type or email is incorrect!!!");
        } else if (matchLoginData && loginUserType.equals("Student")) {
            JOptionPane.showMessageDialog(this,"You are logged in as Student!!!");
            studentLoggedInCoursePanel.loggedInStudentData(loginEmail);
            studentPanelViewResultPanel.loggedInStudentData(loginEmail);
            studentPanelViewResultPanel.studentLevelCompulsoryModuleNames(loginEmail);
            studentPanelViewResultPanel.studentLevelElectiveModuleNames(loginEmail);
            refreshStudentPanelCourseTable();
            LoginPanel.getLoginEmail().setText("");
            LoginPanel.getLoginPassword().setText("");
            studentLoggedInCoursePanel.setVisible(true);
            RegisterPanel.setVisible(false);
            LoginPanel.setVisible(false);
            this.setMinimumSize(new Dimension(820,520));
            pack();
            setLocationRelativeTo(null);
        } else if (matchLoginData && loginUserType.equals("Instructor")) {
            JOptionPane.showMessageDialog(this,"You are logged in as Instructor!!!");
            instructorPanel.loggedInInstructorData(loginEmail);
            instructorPanel.instructorTeachingCourses(loginEmail);
            refreshInstructorMarksTable();
            LoginPanel.getLoginEmail().setText("");
            LoginPanel.getLoginPassword().setText("");
            instructorPanel.setVisible(true);
            RegisterPanel.setVisible(false);
            LoginPanel.setVisible(false);
            this.setMinimumSize(new Dimension(900,520));
            pack();
            setLocationRelativeTo(null);
        } else if(matchLoginData && loginUserType.equals("Course Administrator")) {
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
        String cname =  Objects.requireNonNull(courseAdministrationLoggedInModulesPanel.getCourse().getSelectedItem()).toString();
        String mName = courseAdministrationLoggedInModulesPanel.getModuleName().getText().trim();
        String lvl = Objects.requireNonNull(courseAdministrationLoggedInModulesPanel.getLevel().getSelectedItem()).toString();
        String sem = Objects.requireNonNull(courseAdministrationLoggedInModulesPanel.getSemester().getSelectedItem()).toString();
        String moduleType = courseAdministrationLoggedInModulesPanel.getIsElective().isSelected() ? "Elective" : "Compulsory";

        if (cname.equals("Select Course Names")) {
            JOptionPane.showMessageDialog(this,"Select course name!!!");
        } else if(mName.isEmpty()){
            JOptionPane.showMessageDialog(this,"Module Name Field is empty!!!");
        } else if (lvl.equals("Select Level")) {
            JOptionPane.showMessageDialog(this,"Select Level!!!");
        } else if (sem.equals("Select Semester")) {
            JOptionPane.showMessageDialog(this,"Select Semester!!!");
        } else {
                moduleTable.insert(mName,cname, lvl, moduleType, sem);
            courseAdministrationLoggedInModulesPanel.getModuleName().setText("");
        }
    }


    //    Validate Add Instructor TO Module Data From Course Administrator

    private void checkInstructorData(String moduleName,String instructorName) {
        String caModuleName = Objects.requireNonNull(courseAdministrationLoggedInInstructorAddToModulePanel.getModuleName().getSelectedItem()).toString();
        String caInstructorName = Objects.requireNonNull(courseAdministrationLoggedInInstructorAddToModulePanel.getInstructorEmail().getSelectedItem()).toString();

        if(caModuleName.equals("Select Module Names") && caInstructorName.equals("Select Instructor Names")){
            JOptionPane.showMessageDialog(self,"Please select module and instructor names and try again.");
        }else if (caModuleName.equals("Select Module Names")){
            JOptionPane.showMessageDialog(self,"No module is selected.");
        } else if(caInstructorName.equals("Select Instructor Names")){
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
        String cname =  Objects.requireNonNull(courseAdministrationLoggedInModulesPanel.getCourse().getSelectedItem()).toString();
        String mName = courseAdministrationLoggedInModulesPanel.getModuleName().getText().trim();
        String lvl = Objects.requireNonNull(courseAdministrationLoggedInModulesPanel.getLevel().getSelectedItem()).toString();
        String sem = courseAdministrationLoggedInModulesPanel.getLevel().getSelectedItem().toString();
        String courseType = courseAdministrationLoggedInModulesPanel.getIsElective().isSelected() ? "Elective" : "Compulsory";

        JButton addModuleBtn = courseAdministrationLoggedInModulesPanel.getAddModule();

        addModuleBtn.addActionListener(e -> {
            try {
                checkModuleData(cname,mName,lvl,sem,courseType);
                refreshModuleTable();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void implementInstructorToModule() {
        String moduleName = Objects.requireNonNull(courseAdministrationLoggedInInstructorAddToModulePanel.getModuleName().getSelectedItem()).toString();
        String instructorEmail = Objects.requireNonNull(courseAdministrationLoggedInInstructorAddToModulePanel.getInstructorEmail().getSelectedItem()).toString();
        JButton addInstructorToModuleBtn = courseAdministrationLoggedInInstructorAddToModulePanel.getAddInstructor();

        addInstructorToModuleBtn.addActionListener(e -> {
            try {
                checkInstructorData(moduleName,instructorEmail);
                refreshCourseAdministratorInstructorTable();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void implementElectiveSubjects(){
        JButton addElectiveBtn = studentLoggedInCoursePanel.getAddElectiveSubject();

        addElectiveBtn.addActionListener(e -> {
            try {
                String email =  studentLoggedInCoursePanel.getEmail().getText().trim();
                String electiveModule =  studentLoggedInCoursePanel.getElectiveModule().getSelectedItem().toString();
                String semester = studentLoggedInCoursePanel.getSemester().getSelectedItem().toString();
                String level = studentLoggedInCoursePanel.getLevel().getText().trim();
                if (!level.equals("6")){
                    JOptionPane.showMessageDialog(null, "You must be level 6 to choose elective subjects");
                } else if (electiveModule == "Select Elective Module" && semester == "Select Semester"){
                    JOptionPane.showMessageDialog(this,"Complete All Fields");
                } else if(electiveModule == "Select Elective Module"){
                    JOptionPane.showMessageDialog(this,"Elective Subject is not selected");
                } else if(semester == "Select Semester"){
                    JOptionPane.showMessageDialog(this,"Semester is not selected");
                } else {
                    studentCourseTable.insert(email,electiveModule);
                    refreshStudentPanelCourseTable();
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void implementInstructorAddMarks(){
        JButton addMarks = instructorPanel.getAddMarks();

        addMarks.addActionListener(e -> {
            try {
                String courseName = instructorPanel.getCourseName().getSelectedItem().toString();
                String studentEmail =  instructorPanel.getStudentEmail().getSelectedItem().toString();
                String moduleName =  instructorPanel.getModuleName().getSelectedItem().toString();
                String instructorEmail = instructorPanel.getEmail().getText().trim();
                String level = instructorPanel.getLevel().getSelectedItem().toString();
                String obtainedMarksEmpty = instructorPanel.getObtainedMarks().getText().trim();
                String pass = "Pass";
                String fail = "Fail";
                if(courseName == "Select Course" && level == "Select Level" && moduleName == "Select Modules" &&  studentEmail == "Select Student" && obtainedMarksEmpty.isEmpty()){
                    JOptionPane.showMessageDialog(this,"Complete all fields");
                } else if(level == "Select Level"){
                    JOptionPane.showMessageDialog(this,"Select Level");
                } else if(moduleName == "Select Modules"){
                    JOptionPane.showMessageDialog(this,"No Module is selected");
                } else if(studentEmail == "Select Student"){
                    JOptionPane.showMessageDialog(this,"No student is selected");
                }else if(obtainedMarksEmpty.isEmpty()){
                    JOptionPane.showMessageDialog(this,"Enter Obtained Marks");
                } else {
                    int obtainedMarks = Integer.parseInt(instructorPanel.getObtainedMarks().getText().trim());
                    int fullMarks = Integer.parseInt(instructorPanel.getFullMarks().getText().trim());
                    int passMarks = (40*fullMarks)/100;
                    if (obtainedMarks > fullMarks) {
                        JOptionPane.showMessageDialog(self, "Obtained Marks cannot be grater than full marks.", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        if (obtainedMarks > 70) {
                            instructorPanelTable.insert(studentEmail, moduleName, instructorEmail, obtainedMarks, passMarks, fullMarks, 'A', pass);
                        } else if (obtainedMarks <= 70 && obtainedMarks >= 60) {
                            instructorPanelTable.insert(studentEmail, moduleName, instructorEmail, obtainedMarks, passMarks, fullMarks, 'B', pass);
                        } else if (obtainedMarks < 60 && obtainedMarks >= 50) {
                            instructorPanelTable.insert(studentEmail, moduleName, instructorEmail, obtainedMarks, passMarks, fullMarks, 'C', pass);
                        } else if (obtainedMarks < 50 && obtainedMarks >= 43) {
                            instructorPanelTable.insert(studentEmail, moduleName, instructorEmail, obtainedMarks, passMarks, fullMarks, 'D', pass);
                        } else if (obtainedMarks < 43 && obtainedMarks >= 40) {
                            instructorPanelTable.insert(studentEmail, moduleName, instructorEmail, obtainedMarks, passMarks, fullMarks, 'E', pass);
                        } else {
                            instructorPanelTable.insert(studentEmail, moduleName, instructorEmail, obtainedMarks, passMarks, fullMarks, 'F', fail);
                        }
                        refreshInstructorMarksTable();
                        instructorPanel.getLevel().setSelectedIndex(0);
                        instructorPanel.getModuleName().setSelectedIndex(0);
                        instructorPanel.getStudentEmail().setSelectedIndex(0);
                        instructorPanel.getObtainedMarks().setText("");
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
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
            JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
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
                        resultSet.getString("instructorEmail"),

                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void refreshStudentPanelCourseTable() {
        studentLoggedInCoursePanel.getStudentLoggedInCourseModel().setRowCount(0);
        try {
            String lvl = studentLoggedInCoursePanel.getLevel().getText().trim();
            String courseName = studentLoggedInCoursePanel.getCourseName().getText().trim();
            String email = studentLoggedInCoursePanel.getEmail().getText().trim();
            ResultSet resultSet = studentCourseTable.getModuleDetails(courseName,lvl);
            ResultSet resultSet1 = studentCourseTable.getElectiveSubjectData(email);
            while (resultSet.next()) {
                studentLoggedInCoursePanel.getStudentLoggedInCourseModel().addRow(new Object[]{
                        resultSet.getString("courseName"),
                        resultSet.getString("moduleName"),
                        resultSet.getString("semester"),
                        resultSet.getString("instructorEmail"),
                        resultSet.getString("moduleType"),
                });
            }
            while (resultSet1.next()) {
                studentLoggedInCoursePanel.getStudentLoggedInCourseModel().addRow(new Object[]{
                        resultSet1.getString("courseName"),
                        resultSet1.getString("moduleName"),
                        resultSet1.getString("semester"),
                        resultSet1.getString("instructorEmail"),
                        resultSet1.getString("moduleType"),
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshInstructorMarksTable() {
        instructorPanel.getInstructorModel().setRowCount(0);
        try {
            String email = instructorPanel.getEmail().getText().trim();
            System.out.println(email);
            ResultSet resultSet = instructorPanelTable.getAddedMarks(email);
            while (resultSet.next()) {
                instructorPanel.getInstructorModel().addRow(new Object[]{
                        resultSet.getString("marksId"),
                        resultSet.getString("studentEmail"),
                        resultSet.getString("courseName"),
                        resultSet.getString("level"),
                        resultSet.getString("moduleName"),
                        resultSet.getString("obtainedMarks"),
                        resultSet.getString("passMarks"),
                        resultSet.getString("fullMarks"),
                        resultSet.getString("grade"),
                        resultSet.getString("status"),
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

//    Update

    private void updateCourseName(){
        DefaultTableModel courseModel = courseAdministrationLoggedInCoursesPanel.getCourseModel();
        JButton updateCourseBtn = courseAdministrationLoggedInCoursesPanel.getUpdateCourse();
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
        updateCourseBtn.addActionListener(e -> {
            try {
                int selectedRow = dataTable.getSelectedRow();
                if (selectedRow==-1){
                    JOptionPane.showMessageDialog(self,"Please select the row from table","Warning",JOptionPane.WARNING_MESSAGE);
                } else {
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
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(self, "Coding error", "Error", JOptionPane.ERROR_MESSAGE);
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
        cancelCourseBtn.addActionListener(e -> {
                try {
                    int selectedRow = dataTable.getSelectedRow();
                    if (selectedRow == -1){
                        JOptionPane.showMessageDialog(self,"Please select the row from table to cancel","Warning",JOptionPane.WARNING_MESSAGE);
                    }else {
                        String status = courseModel.getValueAt(selectedRow, 2).toString();
                        if (status.equals("Cancel")) {
                            JOptionPane.showMessageDialog(self, "THe data you have selected is already canceled.");
                        } else {
                            int id = Integer.parseInt(courseModel.getValueAt(selectedRow, 0).toString());
                            coursetable.updateCourseStatus(id, "Cancel");
                            refreshCourseTable();
                            JOptionPane.showMessageDialog(self, "The data has been updated to cancel successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                            courseAdministrationLoggedInCoursesPanel.getCourseName().setText("");
                            dataTable.clearSelection();
                        }
                    }
                }
                catch (Exception ex){
                JOptionPane.showMessageDialog(self, "Coding error", "Error", JOptionPane.ERROR_MESSAGE);
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
        openCourseBtn.addActionListener(e -> {
                try {
                    int selectedRow = dataTable.getSelectedRow();
                    if (selectedRow == -1){
                        JOptionPane.showMessageDialog(self,"Please select the row from table to open","Warning",JOptionPane.WARNING_MESSAGE);
                    }else {
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
                }
                catch (Exception ex){
                JOptionPane.showMessageDialog(self, "Coding error", "Error", JOptionPane.ERROR_MESSAGE);
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
        updateModuleBtn.addActionListener(e -> {
            try {
                int selectedRow = dataTable.getSelectedRow();
                if (selectedRow==-1){
                    JOptionPane.showMessageDialog(self,"Please select the row from the table","Warning",JOptionPane.WARNING_MESSAGE);
                } else {
                    String mName = courseAdministrationLoggedInModulesPanel.getModuleName().getText().trim();
                    String cname =  Objects.requireNonNull(courseAdministrationLoggedInModulesPanel.getCourse().getSelectedItem()).toString();
                    String lvl = Objects.requireNonNull(courseAdministrationLoggedInModulesPanel.getLevel().getSelectedItem()).toString();
                    String sem = Objects.requireNonNull(courseAdministrationLoggedInModulesPanel.getSemester().getSelectedItem()).toString();
                    String courseType = courseAdministrationLoggedInModulesPanel.getIsElective().isSelected() ? "Elective" : "Compulsory";
                    if (cname.equals("Select Course Names")) {
                        JOptionPane.showMessageDialog(self,"Select course name!!!");
                    } else if(mName.isEmpty()){
                        JOptionPane.showMessageDialog(self,"Module Name Field is empty!!!");
                    } else if (lvl.equals("Select Level")) {
                        JOptionPane.showMessageDialog(self,"Select Level!!!");
                    } else if (sem.equals("Select Semester")) {
                        JOptionPane.showMessageDialog(self,"Select Semester!!!");
                    } else {
                        int id = Integer.parseInt(moduleModel.getValueAt(selectedRow, 0).toString());
                        moduleTable.updateModule(id,mName,cname,lvl,courseType,sem);
                        refreshModuleTable();

                        courseAdministrationLoggedInModulesPanel.getModuleName().setText("");
                        dataTable.clearSelection();
                    }
                }
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(self, "Coding error", "Error", JOptionPane.ERROR_MESSAGE);
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
                courseAdministrationLoggedInInstructorAddToModulePanel.getCourseName().setText(courseAdministratorInstructorModel.getValueAt(selectedRow,1).toString());
                courseAdministrationLoggedInInstructorAddToModulePanel.getModuleName().setSelectedItem(courseAdministratorInstructorModel.getValueAt(selectedRow, 2).toString());
                courseAdministrationLoggedInInstructorAddToModulePanel.getLevel().setText(courseAdministratorInstructorModel.getValueAt(selectedRow,3).toString());
                courseAdministrationLoggedInInstructorAddToModulePanel.getModuleType().setText(courseAdministratorInstructorModel.getValueAt(selectedRow,4).toString());
                courseAdministrationLoggedInInstructorAddToModulePanel.getSemester().setText(courseAdministratorInstructorModel.getValueAt(selectedRow,5).toString());
                courseAdministrationLoggedInInstructorAddToModulePanel.getInstructorEmail().setSelectedItem(courseAdministratorInstructorModel.getValueAt(selectedRow, 6).toString());
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
        instructorFromModuleUpdateBtn.addActionListener(e -> {
            try {
                int selectedRow = dataTable.getSelectedRow();
                if (selectedRow==-1){
                    JOptionPane.showMessageDialog(self,"Please select the row from table","Warning",JOptionPane.WARNING_MESSAGE);
                }else{
                    String moduleName = Objects.requireNonNull(courseAdministrationLoggedInInstructorAddToModulePanel.getModuleName().getSelectedItem()).toString();
                    String instructorEmail = Objects.requireNonNull(courseAdministrationLoggedInInstructorAddToModulePanel.getInstructorEmail().getSelectedItem()).toString();
                    int id = Integer.parseInt(courseAdministratorInstructorModel.getValueAt(selectedRow, 0).toString());
                    instructorToModuleTable.updateInstructorTeachingModules(id,moduleName,instructorEmail);
                    refreshCourseAdministratorInstructorTable();
                    dataTable.clearSelection();
                }
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(self, "Coding error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void updateObtainedMarks(){
        JButton updateMarksBtn = instructorPanel.getUpdateMarks();
        DefaultTableModel marksModel = instructorPanel.getInstructorModel();
        JTable dataTable = instructorPanel.getTable();
        dataTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = dataTable.getSelectedRow();
                instructorPanel.getCourseName().setSelectedItem(marksModel.getValueAt(selectedRow,2).toString());
                instructorPanel.getLevel().setSelectedItem(marksModel.getValueAt(selectedRow,3).toString());
                instructorPanel.getModuleName().setSelectedItem(marksModel.getValueAt(selectedRow,4).toString());
                instructorPanel.getStudentEmail().setSelectedItem(marksModel.getValueAt(selectedRow,1).toString());
                instructorPanel.getObtainedMarks().setText(marksModel.getValueAt(selectedRow,5).toString());
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
        updateMarksBtn.addActionListener(e -> {
            try {
                int selectedRow = dataTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(self, "Please select the row from table to update marks", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    int obtainedMarks = Integer.parseInt(instructorPanel.getObtainedMarks().getText().trim());
                    String studentEmail = instructorPanel.getStudentEmail().getSelectedItem().toString();
                    int marks = Integer.parseInt(marksModel.getValueAt(selectedRow, 5).toString());
                    int marksId = Integer.parseInt(marksModel.getValueAt(selectedRow, 0).toString());
                    int fullMarks = Integer.parseInt(instructorPanel.getFullMarks().getText().trim());
                    if (marks == obtainedMarks) {
                        JOptionPane.showMessageDialog(self, "<HTML><CENTER>Nothing to update.<br>Or you may have tried to change other data as they can't be changed.Only marks can be updated.</CENTER></HTML>");
                    } else {
                        String pass = "Pass";
                        String fail = "Fail";
                        if (obtainedMarks > fullMarks) {
                            JOptionPane.showMessageDialog(self, "Obtained Marks cannot be grater than full marks.", "Warning", JOptionPane.WARNING_MESSAGE);
                        } else {
                            if (obtainedMarks > 70) {
                                instructorPanelTable.updateStudentMarks(marksId, obtainedMarks, 'A', pass);
                            } else if (obtainedMarks <= 70 && obtainedMarks >= 60) {
                                instructorPanelTable.updateStudentMarks(marksId, obtainedMarks, 'B', pass);
                            } else if (obtainedMarks < 60 && obtainedMarks >= 50) {
                                instructorPanelTable.updateStudentMarks(marksId, obtainedMarks, 'C', pass);
                            } else if (obtainedMarks < 50 && obtainedMarks >= 43) {
                                instructorPanelTable.updateStudentMarks(marksId, obtainedMarks, 'D', pass);
                            } else if (obtainedMarks < 43 && obtainedMarks >= 40) {
                                instructorPanelTable.updateStudentMarks(marksId, obtainedMarks, 'E', pass);
                            } else {
                                instructorPanelTable.updateStudentMarks(marksId, obtainedMarks, 'F', fail);
                            }
                            refreshInstructorMarksTable();
                            JOptionPane.showMessageDialog(self, "Marks has been updated to open successfully of " + studentEmail + ".", "Success", JOptionPane.INFORMATION_MESSAGE);
                            instructorPanel.getLevel().setSelectedIndex(0);
                            instructorPanel.getModuleName().setSelectedIndex(0);
                            instructorPanel.getStudentEmail().setSelectedIndex(0);
                            instructorPanel.getObtainedMarks().setText("");
                            dataTable.clearSelection();
                        }
                    }
                }
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(self, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });
    }









//    Delete

    private void deleteCourse(){
        JButton courseDeleteBtn = courseAdministrationLoggedInCoursesPanel.getDeleteCourse();
        DefaultTableModel courseModel = courseAdministrationLoggedInCoursesPanel.getCourseModel();
        JTable dataTable = courseAdministrationLoggedInCoursesPanel.getTable();
        courseDeleteBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(self,"Are you sure you want to Delete this data? ", "Confirmation",JOptionPane.YES_NO_OPTION);
            int selectedRow = dataTable.getSelectedRow();
            if (confirm==JOptionPane.YES_OPTION){
                try {
                    courseAdministrationLoggedInCoursesPanel.getCourseName().setText("");
                    int  courseId=Integer.parseInt(courseModel.getValueAt(selectedRow,0).toString());
                    coursetable.deleteCourse(courseId);
                    refreshCourseTable();
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(self, "Please select a row", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void deleteModule(){
        DefaultTableModel moduleModel = courseAdministrationLoggedInModulesPanel.getCourseAdministratorModuleModel();
        JTable dataTable = courseAdministrationLoggedInModulesPanel.getTable();
        JButton moduleDeleteBtn = courseAdministrationLoggedInModulesPanel.getDeleteModule();

        moduleDeleteBtn.addActionListener(e -> {
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
        });
    }

    private void deleteInstructorFromModule(){
        DefaultTableModel instructorModuleModel = courseAdministrationLoggedInInstructorAddToModulePanel.getCourseAdministratorInstructorModel();
        JTable dataTable = courseAdministrationLoggedInInstructorAddToModulePanel.getTable();
        JButton instructorFromModuleDeleteBtn = courseAdministrationLoggedInInstructorAddToModulePanel.getDeleteInstructor();

        instructorFromModuleDeleteBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(self,"Are you sure you want to Delete this data? ", "Confirmation",JOptionPane.YES_NO_OPTION);
            int selectedRow = dataTable.getSelectedRow();
            if (confirm==JOptionPane.YES_OPTION){
                try {
                    int  id=Integer.parseInt(instructorModuleModel.getValueAt(selectedRow,0).toString());
                    instructorToModuleTable.deleteInstructorFromModule(id);
                    refreshCourseAdministratorInstructorTable();
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(self, "Please select a row", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }


    public static void main(String[] args) { new MyApp(); }
}
