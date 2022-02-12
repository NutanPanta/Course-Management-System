package CourseManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

public class MyApp extends JFrame {

    userLoginPanel UserLoginPanel;
    userRegistrationPanel UserRegistrationPanel;
    StudentLoggedInCoursePanel studentPanel;
    StudentPanelViewResultPanel studentResultPanel;
    AdminMainPanel adminMainPanel;
    AdminCoursePanel adminCoursePanel;
    AdminModulePanel adminModulePanel;
    AdminInstructorPanel adminInstructorPanel;
    AdminMarksGeneratePanel adminMarksGeneratePanel;
    InstructorPanel instructorPanel;
    courseTable coursetable;
    ModuleTable moduleTable;
    UserTable userTable;
    InstructorPanelTable instructorPanelTable;
    StudentCourseTable studentCourseTable;
    InstructorToModuleTable instructorToModuleTable;
    File registrationFile = new File("Files");
    int fileLineCount, moduleCount, electiveModulesCount;

    MyApp self = this;

    public MyApp() {
        setVisible(true);
        setResizable(false);
        setTitle("Course Management System");
        setMinimumSize(new Dimension(800, 520));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);

        UserRegistrationPanel = new userRegistrationPanel();
        UserLoginPanel = new userLoginPanel();
        adminMainPanel = new AdminMainPanel();
        studentPanel = new StudentLoggedInCoursePanel();
        studentResultPanel = new StudentPanelViewResultPanel();
        coursetable = new courseTable();
        adminModulePanel = new AdminModulePanel();
        adminCoursePanel = new AdminCoursePanel();
        moduleTable = new ModuleTable();
        studentCourseTable = new StudentCourseTable();
        instructorPanelTable = new InstructorPanelTable();
        userTable = new UserTable();
        instructorToModuleTable = new InstructorToModuleTable();
        instructorPanel = new InstructorPanel();
        adminInstructorPanel = new AdminInstructorPanel();
        adminMarksGeneratePanel = new AdminMarksGeneratePanel();

        UserRegistrationPanel.setVisible(false);
        UserLoginPanel.setVisible(true);
        adminMainPanel.setVisible(false);
        studentPanel.setVisible(false);
        instructorPanel.setVisible(false);
        studentResultPanel.setVisible(false);
        adminCoursePanel.setVisible(false);
        adminInstructorPanel.setVisible(false);
        adminModulePanel.setVisible(false);
        adminMarksGeneratePanel.setVisible(false);

        add(appLayout());

        addModules();
        addCourses();
        giveMarksToStudent();
        chooseOptionalModules();
        assignInstructor();

        loginUser();
        registerUsers();

        refreshInstructorMarksTable();
        refreshModuleTable();
        refreshStudentPanelCourseTable();
        refreshCourseTable();
        refreshAdminnstructorTable();
        refreshElectiveModulesCount();
        refreshModuleCount();

        deleteInstructorFromModule();
        deleteModule();
        deleteCourse();

        updateCourseName();
        updateCourseStatusToCancel();
        updateModule();
        updateCourseStatusToOpen();
        updateObtainedMarks();
        updateInstructorFromModel();

        panelChange();
        pack();
        setLocationRelativeTo(null);
    }

    // All JPanel adding

    private JPanel appLayout() {
        JPanel appPanel = new JPanel();
        appPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridLayout = new GridBagConstraints();
        gridLayout.fill = GridBagConstraints.BOTH;
        appPanel.setBackground(Color.decode("#D6D9DE"));

        appPanel.add(studentPanel.panelUI(), gridLayout);
        appPanel.add(UserRegistrationPanel.panelUI(), gridLayout);
        appPanel.add(adminCoursePanel.panelUI(), gridLayout);
        appPanel.add(instructorPanel.panelUI(), gridLayout);
        appPanel.add(adminMarksGeneratePanel.panelUI(), gridLayout);
        appPanel.add(studentResultPanel.panelUI(), gridLayout);
        appPanel.add(adminMainPanel.panelUI(), gridLayout);
        appPanel.add(adminInstructorPanel.panelUI(), gridLayout);
        appPanel.add(UserLoginPanel.panelUI(), gridLayout);
        appPanel.add(adminModulePanel.panelUI(), gridLayout);

        return appPanel;
    }

    // Creating files folder in not exist

    private void createFolder() {
        if (!registrationFile.exists()) {
            registrationFile.mkdirs();
        }
    }

    // Different JPanel visible during different button clicks

    private void panelChange() {
        // Back Buttons
        JButton adminModuleBackButton = adminModulePanel.getBack();
        JButton adminCourseBackButton = adminCoursePanel.getBack();
        JButton adminMarksBackButton = adminMarksGeneratePanel.getBack();
        JButton studentPanelResultBackButton = studentResultPanel.getBack();
        JButton adminInstructorBackButton = adminInstructorPanel.getBack();
        // Logout Buttons
        JButton instructorLogoutBtn = instructorPanel.getLogout();
        JButton studentLogoutBtn = studentPanel.getLogout();
        JButton adminLogoutBtn = adminMainPanel.getLogOut();
        // Auth Panel
        JButton register = UserLoginPanel.getRegistrationButton();
        JButton login = UserRegistrationPanel.getLoginLabel();

        // Admin Main Panel Buttons
        JButton adminCourseButton = adminMainPanel.getCourses();
        JButton adminInstructorButton = adminMainPanel
                .getAddInstructor();
        JButton adminModuleButton = adminMainPanel.getModules();
        JButton adminMarksGenerateButton = adminMainPanel
                .getGenerateMarkSheet();
        // Student Panel View Result Button
        JButton studentPanelViewResultButton = studentPanel.getViewResult();

        register.addActionListener(error -> {
            try {
                UserLoginPanel.getPassword().setText("");
                UserLoginPanel.getEmail().setText("");
                UserLoginPanel.setVisible(false);
                UserRegistrationPanel.setVisible(true);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);

            }
        });
        login.addActionListener(error -> {
            try {
                UserRegistrationPanel.getUserLastName().setText("");
                UserRegistrationPanel.getUserFirstName().setText("");
                UserRegistrationPanel.getPassword().setText("");
                UserRegistrationPanel.getEmail().setText("");
                UserLoginPanel.setVisible(true);
                UserRegistrationPanel.setVisible(false);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);

            }
        });

        studentPanelViewResultButton.addActionListener(error -> {
            try {
                studentPanel.setVisible(false);
                studentResultPanel.setVisible(true);
                this.setMinimumSize(new Dimension(800, 800));
                pack();
                setLocationRelativeTo(null);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);

            }
        });

        adminInstructorButton.addActionListener(error -> {
            try {
                adminMainPanel.setVisible(false);
                adminInstructorPanel.setVisible(true);
                adminInstructorPanel.getRefreshInstructorName();
                refreshAdminnstructorTable();
                adminInstructorPanel.getRefreshModuleName();
                this.setMinimumSize(new Dimension(820, 520));
                pack();
                setLocationRelativeTo(null);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);

            }
        });

        adminModuleButton.addActionListener(error -> {
            try {
                adminModulePanel.setVisible(true);
                adminModulePanel.getCourseName();
                adminMainPanel.setVisible(false);
                refreshModuleTable();
                this.setMinimumSize(new Dimension(800, 520));
                pack();
                setLocationRelativeTo(null);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);

            }
        });

        studentPanelResultBackButton.addActionListener(error -> {
            try {
                studentResultPanel.setVisible(false);
                studentPanel.setVisible(true);
                this.setMinimumSize(new Dimension(820, 520));
                pack();
                setLocationRelativeTo(null);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);

            }
        });

        adminCourseButton.addActionListener(error -> {
            try {
                adminCoursePanel.setVisible(true);
                this.setMinimumSize(new Dimension(780, 480));
                adminMainPanel.setVisible(false);
                pack();
                setLocationRelativeTo(null);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);

            }
        });

        adminCourseBackButton.addActionListener(error -> {
            try {
                adminCoursePanel.setVisible(false);
                adminCoursePanel.getCourseName().setText("");
                adminMainPanel.setVisible(true);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);

            }
        });

        adminMarksGenerateButton.addActionListener(error -> {
            try {
                adminMainPanel.setVisible(false);
                adminMarksGeneratePanel.setVisible(true);
                adminMarksGeneratePanel.getCourseName();
                this.setMinimumSize(new Dimension(800, 800));
                pack();
                setLocationRelativeTo(null);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);
                exception.printStackTrace();

            }
        });

        adminModuleBackButton.addActionListener(error -> {
            try {
                adminModulePanel.getModuleName().setText("");
                adminModulePanel.setVisible(false);
                adminMainPanel.setVisible(true);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);

            }
        });

        adminMarksBackButton.addActionListener(error -> {
            try {
                adminMarksGeneratePanel.setVisible(false);
                adminMainPanel.setVisible(true);
                setMinimumSize(new Dimension(800, 520));
                pack();
                setLocationRelativeTo(null);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);

            }
        });

        adminInstructorBackButton.addActionListener(error -> {
            try {
                adminInstructorPanel.setVisible(false);
                adminMainPanel.setVisible(true);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);

            }
        });

        // Logout from main panels from different users Action Listeners

        studentLogoutBtn.addActionListener(error -> {
            try {
                studentPanel.setVisible(false);
                UserLoginPanel.setVisible(true);
                studentResultPanel.totalObtained = 0;
                studentResultPanel.totalFM = 0;
                studentResultPanel.totalPM = 0;
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);

            }
        });

        instructorLogoutBtn.addActionListener(error -> {
            try {
                instructorPanel.setVisible(false);
                UserLoginPanel.setVisible(true);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);

            }
        });

        adminLogoutBtn.addActionListener(error -> {
            try {
                adminMainPanel.setVisible(false);
                UserLoginPanel.setVisible(true);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);

            }
        });

    }

    // Read register.txt registrationFile

    private void readRegistrationFile() throws IOException {
        try {
            FileReader readFile = new FileReader(registrationFile + "\\register.txt");
            readFile.close();
        } catch (FileNotFoundException exception) {
            try {
                FileWriter writeToFile = new FileWriter(registrationFile + "\\register.txt");
                writeToFile.close();
            } catch (IOException ex1) {
                JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Add Registration Data to register.txt registrationFile

    private void addRegistrationData(String firstName, String lastName, String Email, String Password, String userType,
            String course, String level) {
        try {
            RandomAccessFile appendFile = new RandomAccessFile(registrationFile + "\\register.txt", "rw");
            for (int i = 0; i < fileLineCount; i++) {
                appendFile.readLine();
            }
            if (fileLineCount > 0) {
                appendFile.writeBytes("\r\n");
            }
            appendFile.writeBytes("First Name: " + firstName + "\r\n");
            appendFile.writeBytes("Last Name: " + lastName + "\r\n");
            appendFile.writeBytes("Email: " + Email + "\r\n");
            appendFile.writeBytes("Password: " + Password + "\r\n");
            appendFile.writeBytes("User Type: " + userType + "\r\n");
            appendFile.writeBytes("Course: " + course + "\r\n");
            appendFile.writeBytes("Level: " + level + "\r\n");
            appendFile.close();

        } catch (IOException error) {
            JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Check If the input email is already registered
    private boolean emailDuplication(String Email) {
        boolean isEmailDuplicate = false;
        try {
            RandomAccessFile appendFile = new RandomAccessFile(registrationFile + "\\register.txt", "rw");
            for (int i = 0; i < fileLineCount; i += 8) {
                for (int k = 1; k <= 2; k++) {
                    appendFile.readLine();
                }
                String mail = appendFile.readLine().substring(7);

                if (Email.equals(mail)) {
                    isEmailDuplicate = true;
                    break;
                }
                for (int k = 1; k <= 5; k++) {
                    appendFile.readLine();
                }
            }
            appendFile.close();
        } catch (IOException error) {
            JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return isEmailDuplicate;
    }

    // check if the input email is a valid one
    private boolean emailValidation(String Email) {
        boolean valid = false;
        if (Email.contains("@") && Email.contains(".com")) {
            valid = true;
        }
        return valid;
    }

    // allowing only one admin
    private boolean administrationDuplication(String userType) {
        boolean isUserDuplicate = false;
        try {
            RandomAccessFile appendFile = new RandomAccessFile(registrationFile + "\\register.txt", "rw");
            for (int i = 0; i < fileLineCount; i += 8) {
                for (int k = 1; k <= 4; k++) {
                    appendFile.readLine();
                }
                if (userType.equals("Course Administrator")) {
                    isUserDuplicate = true;
                    break;
                }
                for (int k = 1; k <= 3; k++) {
                    appendFile.readLine();
                }
            }
            appendFile.close();
        } catch (IOException error) {
            JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return isUserDuplicate;
    }

    // login validation
    private boolean readAndValidateLoginData(String Email, String pwd) throws IOException {
        String loginEmail = UserLoginPanel.getEmail().getText().trim();
        String loginPassword = UserLoginPanel.getPassword().getText();

        HashMap<String, String> hash_map = new HashMap<>();
        BufferedReader fileReader = new BufferedReader(new FileReader(registrationFile + "\\register.txt"));

        for (int i = 0; i < fileLineCount; i += 8) {
            for (int k = 1; k <= 2; k++) {
                fileReader.readLine();
            }
            String mail = fileReader.readLine().substring(7);
            String password = fileReader.readLine().substring(10);

            hash_map.put(mail, password);

            for (int k = 1; k <= 4; k++) {
                fileReader.readLine();
            }
        }
        fileReader.close();

        boolean matchLoginData = false;
        if (hash_map.containsKey(loginEmail)) {
            String passwordForEmail = hash_map.get(loginEmail); // get the hash_map value that is associated to the key
                                                                // loginEmail -> this should be the password that
                                                                // belongs to the email
            matchLoginData = loginPassword.equals(passwordForEmail);
        }
        return matchLoginData;
    }

    // validation user role and if it matches to data in database user is logged in
    // to their respective panel
    private boolean validateUserRole(String Email, String userType) throws IOException {
        String loginEmail = UserLoginPanel.getEmail().getText().trim();
        String loginUserType = Objects.requireNonNull(UserLoginPanel.getUserRole().getSelectedItem()).toString();

        HashMap<String, String> map = new HashMap<>();
        BufferedReader fileReader = new BufferedReader(new FileReader(registrationFile + "\\register.txt"));

        for (int i = 0; i < fileLineCount; i += 8) {
            for (int k = 1; k <= 2; k++) {
                fileReader.readLine();
            }
            String mail = fileReader.readLine().substring(7);
            fileReader.readLine();

            String value = fileReader.readLine().substring(11);
            map.put(mail, value);

            for (int k = 1; k <= 3; k++) {
                fileReader.readLine();
            }
        }
        fileReader.close();
        boolean matchUserType = false;
        if (map.containsKey(loginEmail)) {
            String userTypeForEmail = map.get(loginEmail); // get the hash_map value that is associated to the key
                                                           // loginEmail -> this should be the userType that
                                                           // belongs to the email
            matchUserType = loginUserType.equals(userTypeForEmail);
        }
        return matchUserType;
    }

    // Validate register data and if the datta is valid register user
    private void checkRegistrationData(String firstName, String lastName, String Email, String Password,
            String userType, String course, String level) {
        String formFirstName = UserRegistrationPanel.getUserFirstName().getText().trim();
        String formLastName = UserRegistrationPanel.getUserLastName().getText().trim();
        String email = UserRegistrationPanel.getEmail().getText().trim();
        String password = UserRegistrationPanel.getPassword().getText();
        String userRole = Objects
                .requireNonNull(UserRegistrationPanel.getUserRole().getSelectedItem())
                .toString();
        String registrationCourse = Objects
                .requireNonNull(UserRegistrationPanel.getCourse().getSelectedItem())
                .toString();
        String registrationLevel = Objects
                .requireNonNull(UserRegistrationPanel.getLevel().getSelectedItem())
                .toString();
        boolean emailValidation = emailValidation(email);
        boolean isEmailDuplicate = emailDuplication(email);
        boolean isUserDuplicate = administrationDuplication(userRole);
        if (formFirstName.isEmpty() && formLastName.isEmpty() && email.isEmpty()
                && password.isEmpty() && userRole.isEmpty()
                && userRole.equals("Select User") && registrationLevel.equals("Select Level")) {
            JOptionPane.showMessageDialog(this, "All fields are empty. Fill the form and try again.");
        } else if (formFirstName.isEmpty() || formLastName.isEmpty() || email.isEmpty()
                || password.isEmpty() || userRole.isEmpty()
                || userRole.equals("Select User")
                || userRole.equals("Student") && registrationLevel.equals("Select Level")) {
            JOptionPane.showMessageDialog(this, "Complete all fields and try again.", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        } else if (userRole.equals("Select User")) {
            JOptionPane.showMessageDialog(this, "Select a user type");
        } else if (userRole.equals("Student") && registrationLevel.equals("Select Level")) {
            JOptionPane.showMessageDialog(this, "No Level Is Selected");
        } else if (!emailValidation) {
            JOptionPane.showMessageDialog(this, "Enter a valid email");
        } else if (isEmailDuplicate) {
            JOptionPane.showMessageDialog(this, "This user with this email has already been Registered. Please Login");
        } else if (isUserDuplicate) {
            JOptionPane.showMessageDialog(this, "There is already one course administrator. Please Login");
        } else {
            if (UserRegistrationPanel.getCourse().isVisible()) {
                addRegistrationData(formFirstName, formLastName, email,
                        password, userRole, registrationCourse, registrationLevel);
                userTable.registerUser(formFirstName, formLastName, email, password,
                        userRole, registrationCourse, registrationLevel);
            } else {
                userTable.registerUser(formFirstName, formLastName, email, password,
                        userRole, null, null);
                addRegistrationData(formFirstName, formLastName, email,
                        password, userRole, null, null);
            }
            UserRegistrationPanel.getUserFirstName().setText("");
            UserRegistrationPanel.getUserLastName().setText("");
            UserRegistrationPanel.getEmail().setText("");
            UserRegistrationPanel.getPassword().setText("");
            UserRegistrationPanel.setVisible(false);
            UserLoginPanel.setVisible(true);
        }
    }

    // Validate Login Data and log user in if valid otherwise show appropriate
    // message
    private void checkLoginData(String Email, String pwd, String userType) throws IOException {
        String loginEmail = UserLoginPanel.getEmail().getText().trim();
        String loginPassword = UserLoginPanel.getPassword().getText().trim();
        String loginUserType = Objects.requireNonNull(UserLoginPanel.getUserRole().getSelectedItem()).toString();
        boolean matchLoginData = readAndValidateLoginData(loginEmail, loginPassword);
        boolean matchUserType = validateUserRole(loginEmail, loginUserType);

        if (loginEmail.isEmpty() && loginPassword.isEmpty() && loginUserType.equals("Select User")) {
            JOptionPane.showMessageDialog(this, "Complete all fields");
        } else if (loginEmail.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email field is empty");
        } else if (loginPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password field is empty");
        } else if (loginUserType.equals("Select User")) {
            JOptionPane.showMessageDialog(this, "Select a user type!!!");
        } else if (!matchUserType) {
            JOptionPane.showMessageDialog(this, "Incorrect User type or email");
        } else if (matchLoginData && loginUserType.equals("Student")) {
            JOptionPane.showMessageDialog(this, "You are logged in as Student");
            studentPanel.loggedInStudentData(loginEmail);
            studentResultPanel.loggedInStudentData(loginEmail);
            studentResultPanel.studentLevelCompulsoryModuleNames(loginEmail);
            studentResultPanel.studentLevelElectiveModuleNames(loginEmail);
            refreshStudentPanelCourseTable();
            UserLoginPanel.getEmail().setText("");
            UserLoginPanel.getPassword().setText("");
            studentPanel.setVisible(true);
            UserRegistrationPanel.setVisible(false);
            UserLoginPanel.setVisible(false);
            this.setMinimumSize(new Dimension(820, 520));
            pack();
            setLocationRelativeTo(null);
        } else if (matchLoginData && loginUserType.equals("Instructor")) {
            JOptionPane.showMessageDialog(this, "You are logged in as Instructor!!!");
            instructorPanel.loggedInInstructorData(loginEmail);
            instructorPanel.instructorTeachingCourses(loginEmail);
            refreshInstructorMarksTable();
            UserLoginPanel.getEmail().setText("");
            UserLoginPanel.getPassword().setText("");
            instructorPanel.setVisible(true);
            UserRegistrationPanel.setVisible(false);
            UserLoginPanel.setVisible(false);
            this.setMinimumSize(new Dimension(900, 520));
            pack();
            setLocationRelativeTo(null);
        } else if (matchLoginData && loginUserType.equals("Course Administrator")) {
            JOptionPane.showMessageDialog(this, "You are logged in as Course Administrator!!!");
            UserLoginPanel.getEmail().setText("");
            UserLoginPanel.getPassword().setText("");
            adminMainPanel.setVisible(true);
            UserRegistrationPanel.setVisible(false);
            UserLoginPanel.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Email or Password or userType Did Not Match!!!");
        }
    }

    // Validate Course Data From Course Administrator

    private void checkCourseData(String name, String status) {
        String courseName = adminCoursePanel.getCourseName().getText().trim();
        if (courseName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete all fields!!!");
        } else {
            coursetable.addCourse(courseName, status);
            adminCoursePanel.getCourseName().setText("");
        }
    }

    // Count register.txt lines

    private void countRegistrationLines() {
        try {
            fileLineCount = 0;
            RandomAccessFile appendFile = new RandomAccessFile(registrationFile + "\\register.txt", "rw");
            for (int i = 0; appendFile.readLine() != null; i = i + 1) {
                fileLineCount++;
            }
            appendFile.close();
        } catch (IOException error) {
            JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
    // Validate Module Data from Course Administrator

    private void checkModuleData(String courseName, String moduleName, String level, String elective, String semester) {

        String mn = adminModulePanel.getModuleName().getText().trim();
        String ll = Objects.requireNonNull(adminModulePanel.getLevel().getSelectedItem())
                .toString();
        String mt = adminModulePanel.getModuleType().getSelectedItem().toString()
                .trim();
        String sm = Objects.requireNonNull(adminModulePanel.getSemester().getSelectedItem())
                .toString();
        String cn = Objects.requireNonNull(adminModulePanel.getCourse().getSelectedItem())
                .toString();
        if (cn.equals("Select Course Names")) {
            JOptionPane.showMessageDialog(this, "Select course name!!!");
        } else if (mn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Module Name Field is empty!!!");
        } else if (ll.equals("Select Level")) {
            JOptionPane.showMessageDialog(this, "Select Level!!!");
        } else if (sm.equals("Select Semester")) {
            JOptionPane.showMessageDialog(this, "Select Semester!!!");
        } else if (mt.equals("Select ModuleType")) {
            JOptionPane.showMessageDialog(self, "Select ModuleType!!!");
        } else {
            if (ll.equals("6")) {
                if (mt.equals("Compulsory") && moduleCount < 2) {
                    moduleTable.addModules(mn, cn, ll, mt, sm);
                } else if (mt.equals("Elective") && moduleCount < 4) {
                    moduleTable.addModules(mn, cn, ll, mt, sm);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Compulsory subject for level 6 for each sem cannot be more than 2 and not more than 4 for elective Modules.");
                }
            } else {
                if (moduleCount < 4) {
                    moduleTable.addModules(mn, cn, ll, mt, sm);
                } else {
                    JOptionPane.showMessageDialog(this, "Each Semester cannot have more than 4 modules");
                }
            }
            adminModulePanel.getModuleName().setText("");
        }
    }
    // Validate Add Instructor TO Module Data From Course Administrator

    private void checkInstructorData() {
        String module = Objects
                .requireNonNull(
                        adminInstructorPanel.getModuleName().getSelectedItem())
                .toString();
        String instructor = Objects
                .requireNonNull(
                        adminInstructorPanel.getInstructorEmail().getSelectedItem())
                .toString();

        if (module.equals("Select Module Names") && instructor.equals("Select Instructor Names")) {
            JOptionPane.showMessageDialog(self, "Please select module and instructor names and try again.");
        } else if (module.equals("Select Module Names")) {
            JOptionPane.showMessageDialog(self, "No module is selected.");
        } else if (instructor.equals("Select Instructor Names")) {
            JOptionPane.showMessageDialog(self, "No Instructor is selected.");
        } else {
            instructorToModuleTable.addInstructorToModule(module, instructor);
        }
    }

    private void loginUser() {
        String loginEmail = UserLoginPanel.getEmail().getText().trim();
        String loginPassword = UserLoginPanel.getPassword().getText().trim();
        String loginUserType = Objects.requireNonNull(UserLoginPanel.getUserRole().getSelectedItem()).toString();
        JButton loginBtn = UserLoginPanel.getLoginButton();

        loginBtn.addActionListener(error -> {
            try {
                countRegistrationLines();
                checkLoginData(loginEmail, loginPassword, loginUserType);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);
                exception.printStackTrace();
            }
        });
    }

    private void registerUsers() {
        String formFirstName = UserRegistrationPanel.getUserFirstName().getText().trim();
        String formLastName = UserRegistrationPanel.getUserLastName().getText().trim();
        String email = UserRegistrationPanel.getEmail().getText().trim();
        String password = UserRegistrationPanel.getPassword().getText();
        String userRole = Objects.requireNonNull(UserRegistrationPanel.getUserRole().getSelectedItem()).toString();
        String registrationLevel = Objects.requireNonNull(UserRegistrationPanel.getLevel().getSelectedItem())
                .toString();
        String registrationCourse = Objects.requireNonNull(UserRegistrationPanel.getCourse().getSelectedItem())
                .toString();
        JButton registerBtn = UserRegistrationPanel.getRegisterBtn();

        registerBtn.addActionListener(error -> {
            try {

                createFolder();
                readRegistrationFile();
                countRegistrationLines();
                checkRegistrationData(formFirstName, formLastName, email,
                        password, userRole, registrationCourse, registrationLevel);

            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Add

    private void addCourses() {
        String name = adminCoursePanel.getCourseName().getText().trim();
        JButton addCourseBtn = adminCoursePanel.getAddCourse();

        addCourseBtn.addActionListener(error -> {
            try {
                checkCourseData(name, "Open");
                refreshCourseTable();

            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void addModules() {
        String cname = Objects.requireNonNull(adminModulePanel.getCourse().getSelectedItem())
                .toString();
        String mName = adminModulePanel.getModuleName().getText().trim();
        String lvl = Objects.requireNonNull(adminModulePanel.getLevel().getSelectedItem())
                .toString();
        String sem = adminModulePanel.getLevel().getSelectedItem().toString();
        String moduleType = adminModulePanel.getModuleType().getSelectedItem().toString()
                .trim();

        JButton addModuleBtn = adminModulePanel.getAddModule();

        addModuleBtn.addActionListener(error -> {
            try {
                refreshModuleCount();
                checkModuleData(cname, mName, lvl, sem, moduleType);
                refreshModuleTable();

            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);
                exception.printStackTrace();
            }
        });
    }

    private void assignInstructor() {
        JButton addInstructorToModuleBtn = adminInstructorPanel.getAddInstructor();

        addInstructorToModuleBtn.addActionListener(error -> {
            try {
                checkInstructorData();
                refreshAdminnstructorTable();

            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void chooseOptionalModules() {
        JButton addElectiveBtn = studentPanel.getAddElectiveSubject();

        addElectiveBtn.addActionListener(error -> {
            try {
                String email = studentPanel.getEmail().getText().trim();
                String electiveModule = studentPanel.getElectiveModule().getSelectedItem().toString();
                String semester = studentPanel.getSemester().getSelectedItem().toString();
                String level = studentPanel.getLevel().getText().trim();
                if (!level.equals("6")) {
                    JOptionPane.showMessageDialog(null, "You must be level 6 to choose elective subjects");
                } else if (electiveModule == "Select Elective Module" && semester == "Select Semester") {
                    JOptionPane.showMessageDialog(this, "Complete All Fields");
                } else if (electiveModule == "Select Elective Module") {
                    JOptionPane.showMessageDialog(this, "Elective Subject is not selected");
                } else if (semester == "Select Semester") {
                    JOptionPane.showMessageDialog(this, "Semester is not selected");
                } else {
                    if (electiveModulesCount < 2) {
                        studentCourseTable.insert(email, electiveModule);
                        refreshStudentPanelCourseTable();
                        refreshElectiveModulesCount();
                    } else {
                        JOptionPane.showMessageDialog(this, "Each Semester cannot have more than 2 Elective Modules");
                    }
                }

            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void giveMarksToStudent() {
        JButton addMarks = instructorPanel.getAddMarks();

        addMarks.addActionListener(error -> {
            try {
                String courseName = instructorPanel.getCourseName().getSelectedItem().toString();
                String email = instructorPanel.getStudentEmail().getSelectedItem().toString();
                String moduleName = instructorPanel.getModuleName().getSelectedItem().toString();
                String instructorEmail = instructorPanel.getEmail().getText().trim();
                String level = instructorPanel.getLevel().getSelectedItem().toString();
                String obtainedMarksEmpty = instructorPanel.getObtainedMarks().getText().trim();
                String pass = "Pass";
                String fail = "Fail";
                if (courseName == "Select Course" && level == "Select Level" && moduleName == "Select Modules"
                        && email == "Select Student" && obtainedMarksEmpty.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Complete all fields");
                } else if (level == "Select Level") {
                    JOptionPane.showMessageDialog(this, "Select Level");
                } else if (moduleName == "Select Modules") {
                    JOptionPane.showMessageDialog(this, "No Module is selected");
                } else if (email == "Select Student") {
                    JOptionPane.showMessageDialog(this, "No student is selected");
                } else if (obtainedMarksEmpty.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter Obtained Marks");
                } else {
                    int obtainedMarks = Integer.parseInt(instructorPanel.getObtainedMarks().getText().trim());
                    int fullMarks = Integer.parseInt(instructorPanel.getFullMarks().getText().trim());
                    int passMarks = (40 * fullMarks) / 100;
                    if (obtainedMarks > fullMarks) {
                        JOptionPane.showMessageDialog(self, "Obtained Marks cannot be grater than" + fullMarks,
                                "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        if (obtainedMarks > 70) {
                            instructorPanelTable.addMarks(email, moduleName, instructorEmail, obtainedMarks,
                                    passMarks, fullMarks, 'A', pass);
                        } else if (obtainedMarks <= 70 && obtainedMarks >= 60) {
                            instructorPanelTable.addMarks(email, moduleName, instructorEmail, obtainedMarks,
                                    passMarks, fullMarks, 'B', pass);
                        } else if (obtainedMarks < 60 && obtainedMarks >= 50) {
                            instructorPanelTable.addMarks(email, moduleName, instructorEmail, obtainedMarks,
                                    passMarks, fullMarks, 'C', pass);
                        } else if (obtainedMarks < 50 && obtainedMarks >= 43) {
                            instructorPanelTable.addMarks(email, moduleName, instructorEmail, obtainedMarks,
                                    passMarks, fullMarks, 'D', pass);
                        } else if (obtainedMarks < 43 && obtainedMarks >= 40) {
                            instructorPanelTable.addMarks(email, moduleName, instructorEmail, obtainedMarks,
                                    passMarks, fullMarks, 'E', pass);
                        } else {
                            instructorPanelTable.addMarks(email, moduleName, instructorEmail, obtainedMarks,
                                    passMarks, fullMarks, 'F', fail);
                        }
                        refreshInstructorMarksTable();
                        instructorPanel.getLevel().setSelectedIndex(0);
                        instructorPanel.getModuleName().setSelectedIndex(0);
                        instructorPanel.getStudentEmail().setSelectedIndex(0);
                        instructorPanel.getObtainedMarks().setText("");
                    }
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);
                exception.printStackTrace();
            }
        });
    }

    // Refresh Table

    private void refreshCourseTable() {
        adminCoursePanel.getCourseModel().setRowCount(0);
        try {
            ResultSet resultSet = coursetable.getAllCourses();
            while (resultSet.next()) {
                adminCoursePanel.getCourseModel().addRow(new Object[] {
                        resultSet.getInt("courseId"),
                        resultSet.getString("courseName"),
                        resultSet.getString("courseStatus"),
                });
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void refreshModuleTable() {
        adminModulePanel.getCourseAdministratorModuleModel().setRowCount(0);
        try {
            ResultSet resultSet = moduleTable.getModules();
            while (resultSet.next()) {
                adminModulePanel.getCourseAdministratorModuleModel().addRow(new Object[] {
                        resultSet.getInt("moduleId"),
                        resultSet.getString("moduleName"),
                        resultSet.getString("courseName"),
                        resultSet.getString("level"),
                        resultSet.getString("moduleType"),
                        resultSet.getString("semester"),

                });
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
            error.printStackTrace();
        }

    }

    private void getModuleCount() {
        try {
            if (adminModulePanel.getCourse().getItemCount() == 0) {

            } else {
                String cname = Objects
                        .requireNonNull(adminModulePanel.getCourse().getSelectedItem())
                        .toString();
                String lvl = Objects
                        .requireNonNull(adminModulePanel.getLevel().getSelectedItem())
                        .toString();
                String sem = Objects
                        .requireNonNull(adminModulePanel.getSemester().getSelectedItem())
                        .toString();
                String moduleType = adminModulePanel.getModuleType().getSelectedItem()
                        .toString().trim();
                ResultSet resultSet = moduleTable.getModuleCount(cname, lvl, sem, moduleType);
                while (resultSet.next()) {
                    moduleCount = resultSet.getInt("total");
                }
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void getElectiveModulesCount() {
        try {
            if (studentPanel.getElectiveModule().getItemCount() == 0) {

            } else {
                String email = studentPanel.getEmail().getText().trim();
                String sem = Objects.requireNonNull(studentPanel.getSemester().getSelectedItem())
                        .toString();
                ResultSet resultSet = studentCourseTable.getElectiveSubjectCount(email, sem);
                while (resultSet.next()) {
                    electiveModulesCount = resultSet.getInt("total");
                }
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshModuleCount() {
        adminModulePanel.getCourse().addActionListener(error -> getModuleCount());
        adminModulePanel.getLevel().addActionListener(error -> getModuleCount());
        adminModulePanel.getSemester().addActionListener(error -> getModuleCount());
        adminModulePanel.getModuleType().addActionListener(error -> getModuleCount());
    }

    private void refreshElectiveModulesCount() {
        studentPanel.getSemester().addActionListener(error -> {
            getElectiveModulesCount();
        });
        studentPanel.getElectiveModule().addActionListener(error -> {
            getElectiveModulesCount();
        });
    }

    private void refreshAdminnstructorTable() {
        adminInstructorPanel.getCourseAdministratorInstructorModel().setRowCount(0);
        try {
            ResultSet resultSet = instructorToModuleTable.getModuleDetails();
            while (resultSet.next()) {
                adminInstructorPanel.getCourseAdministratorInstructorModel()
                        .addRow(new Object[] {
                                resultSet.getInt("id"),
                                resultSet.getString("courseName"),
                                resultSet.getString("moduleName"),
                                resultSet.getString("level"),
                                resultSet.getString("moduleType"),
                                resultSet.getString("semester"),
                                resultSet.getString("instructorEmail"),

                        });
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void refreshStudentPanelCourseTable() {
        studentPanel.getStudentLoggedInCourseModel().setRowCount(0);
        try {
            String lvl = studentPanel.getLevel().getText().trim();
            String courseName = studentPanel.getCourseName().getText().trim();
            String email = studentPanel.getEmail().getText().trim();
            ResultSet resultSet = studentCourseTable.getModuleDetails(courseName, lvl);
            ResultSet resultSet1 = studentCourseTable.getElectiveSubjectData(email);
            while (resultSet.next()) {
                studentPanel.getStudentLoggedInCourseModel().addRow(new Object[] {
                        resultSet.getString("courseName"),
                        resultSet.getString("moduleName"),
                        resultSet.getString("semester"),
                        resultSet.getString("instructorEmail"),
                        resultSet.getString("moduleType"),
                });
            }
            while (resultSet1.next()) {
                studentPanel.getStudentLoggedInCourseModel().addRow(new Object[] {
                        resultSet1.getString("courseName"),
                        resultSet1.getString("moduleName"),
                        resultSet1.getString("semester"),
                        resultSet1.getString("instructorEmail"),
                        resultSet1.getString("moduleType"),
                });
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshInstructorMarksTable() {
        instructorPanel.getInstructorModel().setRowCount(0);
        try {
            String email = instructorPanel.getEmail().getText().trim();
            ResultSet resultSet = instructorPanelTable.getMarks(email);
            while (resultSet.next()) {
                instructorPanel.getInstructorModel().addRow(new Object[] {
                        resultSet.getString("marksId"),
                        resultSet.getString("email"),
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
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Update

    private void updateCourseName() {
        DefaultTableModel courseModel = adminCoursePanel.getCourseModel();
        JButton updateCourseBtn = adminCoursePanel.getUpdateCourse();
        JTable dataTable = adminCoursePanel.getTable();
        dataTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent error) {
                int selectedRow = dataTable.getSelectedRow();
                adminCoursePanel.getCourseName()
                        .setText(courseModel.getValueAt(selectedRow, 1).toString());
            }

            @Override
            public void mousePressed(MouseEvent error) {
            }

            @Override
            public void mouseReleased(MouseEvent error) {
            }

            @Override
            public void mouseEntered(MouseEvent error) {
            }

            @Override
            public void mouseExited(MouseEvent error) {
            }
        });
        updateCourseBtn.addActionListener(error -> {
            try {
                int selectedRow = dataTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(self, "Please select the row from table", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    String courseName = adminCoursePanel.getCourseName().getText().trim();
                    if (courseName.isEmpty()) {
                        JOptionPane.showMessageDialog(self, "Please enter the valid Data", "Warning",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        int id = Integer.parseInt(courseModel.getValueAt(selectedRow, 0).toString());
                        coursetable.updateCourseName(id, courseName);
                        refreshCourseTable();
                        refreshModuleTable();
                        refreshInstructorMarksTable();
                        refreshElectiveModulesCount();
                        refreshStudentPanelCourseTable();
                        refreshAdminnstructorTable();

                        adminCoursePanel.getCourseName().setText("");
                        dataTable.clearSelection();
                    }
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Coding error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void updateCourseStatusToCancel() {
        JButton cancelCourseBtn = adminCoursePanel.getCancelCourse();
        DefaultTableModel courseModel = adminCoursePanel.getCourseModel();
        JTable dataTable = adminCoursePanel.getTable();
        dataTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent error) {
                int selectedRow = dataTable.getSelectedRow();
                adminCoursePanel.getCourseName()
                        .setText(courseModel.getValueAt(selectedRow, 1).toString());
            }

            @Override
            public void mousePressed(MouseEvent error) {
            }

            @Override
            public void mouseReleased(MouseEvent error) {
            }

            @Override
            public void mouseEntered(MouseEvent error) {
            }

            @Override
            public void mouseExited(MouseEvent error) {
            }
        });
        cancelCourseBtn.addActionListener(error -> {
            try {
                int selectedRow = dataTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(self, "Please select the row from table to cancel", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    String status = courseModel.getValueAt(selectedRow, 2).toString();
                    if (status.equals("Cancel")) {
                        JOptionPane.showMessageDialog(self, "THe data you have selected is already canceled.");
                    } else {
                        int id = Integer.parseInt(courseModel.getValueAt(selectedRow, 0).toString());
                        coursetable.updateCourseStatus(id, "Cancel");
                        refreshCourseTable();
                        JOptionPane.showMessageDialog(self, "The data has been updated to cancel successfully",
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                        adminCoursePanel.getCourseName().setText("");
                        dataTable.clearSelection();
                    }
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Coding error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void updateCourseStatusToOpen() {
        JButton openCourseBtn = adminCoursePanel.getOpenCourse();
        DefaultTableModel courseModel = adminCoursePanel.getCourseModel();
        JTable dataTable = adminCoursePanel.getTable();
        dataTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent error) {
                int selectedRow = dataTable.getSelectedRow();
                adminCoursePanel.getCourseName()
                        .setText(courseModel.getValueAt(selectedRow, 1).toString());
            }

            @Override
            public void mousePressed(MouseEvent error) {
            }

            @Override
            public void mouseReleased(MouseEvent error) {
            }

            @Override
            public void mouseEntered(MouseEvent error) {
            }

            @Override
            public void mouseExited(MouseEvent error) {
            }
        });
        openCourseBtn.addActionListener(error -> {
            try {
                int selectedRow = dataTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(self, "Please select the row from table to open", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    String status = courseModel.getValueAt(selectedRow, 2).toString();
                    if (status.equals("Open")) {
                        JOptionPane.showMessageDialog(self, "THe data you have selected is already open.");
                    } else {
                        int id = Integer.parseInt(courseModel.getValueAt(selectedRow, 0).toString());
                        coursetable.updateCourseStatus(id, "Open");
                        refreshCourseTable();
                        JOptionPane.showMessageDialog(self, "The data has been updated to open successfully", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        adminCoursePanel.getCourseName().setText("");
                        dataTable.clearSelection();
                    }
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Coding error", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });
    }

    private void updateModule() {
        JButton updateModuleBtn = adminModulePanel.getUpdateModule();
        DefaultTableModel moduleModel = adminModulePanel.getCourseAdministratorModuleModel();
        JTable dataTable = adminModulePanel.getTable();
        dataTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent error) {
                int selectedRow = dataTable.getSelectedRow();
                adminModulePanel.getModuleName()
                        .setText(moduleModel.getValueAt(selectedRow, 1).toString());
                adminModulePanel.getCourse()
                        .setSelectedItem(moduleModel.getValueAt(selectedRow, 2).toString());
                adminModulePanel.getLevel()
                        .setSelectedItem(moduleModel.getValueAt(selectedRow, 3).toString());
                adminModulePanel.getModuleType()
                        .setSelectedItem(moduleModel.getValueAt(selectedRow, 4).toString().equals("Elective"));
                adminModulePanel.getSemester()
                        .setSelectedItem(moduleModel.getValueAt(selectedRow, 5).toString());
            }

            @Override
            public void mousePressed(MouseEvent error) {
            }

            @Override
            public void mouseReleased(MouseEvent error) {
            }

            @Override
            public void mouseEntered(MouseEvent error) {
            }

            @Override
            public void mouseExited(MouseEvent error) {
            }
        });
        updateModuleBtn.addActionListener(error -> {
            try {
                int selectedRow = dataTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(self, "Please select the row from the table", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    String mName = adminModulePanel.getModuleName().getText().trim();
                    String cname = Objects
                            .requireNonNull(adminModulePanel.getCourse().getSelectedItem())
                            .toString();
                    String lvl = Objects
                            .requireNonNull(adminModulePanel.getLevel().getSelectedItem())
                            .toString();
                    String sem = Objects
                            .requireNonNull(adminModulePanel.getSemester().getSelectedItem())
                            .toString();
                    String moduleType = adminModulePanel.getModuleType().getSelectedItem()
                            .toString().trim();
                    if (cname.equals("Select Course Names")) {
                        JOptionPane.showMessageDialog(self, "Select course name!!!");
                    } else if (mName.isEmpty()) {
                        JOptionPane.showMessageDialog(self, "Module Name Field is empty!!!");
                    } else if (lvl.equals("Select Level")) {
                        JOptionPane.showMessageDialog(self, "Select Level!!!");
                    } else if (sem.equals("Select Semester")) {
                        JOptionPane.showMessageDialog(self, "Select Semester!!!");
                    } else if (moduleType.equals("Select ModuleType")) {
                        JOptionPane.showMessageDialog(self, "Select ModuleType!!!");
                    } else {
                        int id = Integer.parseInt(moduleModel.getValueAt(selectedRow, 0).toString());
                        moduleTable.updateModule(id, mName, cname, lvl, moduleType, sem);
                        refreshModuleTable();

                        adminModulePanel.getModuleName().setText("");
                        dataTable.clearSelection();
                    }
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Coding error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void updateInstructorFromModel() {
        JButton instructorFromModuleUpdateBtn = adminInstructorPanel
                .getUpdateInstructor();
        DefaultTableModel courseAdministratorInstructorModel = adminInstructorPanel
                .getCourseAdministratorInstructorModel();
        JTable dataTable = adminInstructorPanel.getTable();
        dataTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent error) {
                int selectedRow = dataTable.getSelectedRow();
                adminInstructorPanel.getCourseName()
                        .setText(courseAdministratorInstructorModel.getValueAt(selectedRow, 1).toString());
                adminInstructorPanel.getModuleName()
                        .setSelectedItem(courseAdministratorInstructorModel.getValueAt(selectedRow, 2).toString());
                adminInstructorPanel.getLevel()
                        .setText(courseAdministratorInstructorModel.getValueAt(selectedRow, 3).toString());
                adminInstructorPanel.getModuleType()
                        .setText(courseAdministratorInstructorModel.getValueAt(selectedRow, 4).toString());
                adminInstructorPanel.getSemester()
                        .setText(courseAdministratorInstructorModel.getValueAt(selectedRow, 5).toString());
                adminInstructorPanel.getInstructorEmail()
                        .setSelectedItem(courseAdministratorInstructorModel.getValueAt(selectedRow, 6).toString());
            }

            @Override
            public void mousePressed(MouseEvent error) {
            }

            @Override
            public void mouseReleased(MouseEvent error) {
            }

            @Override
            public void mouseEntered(MouseEvent error) {
            }

            @Override
            public void mouseExited(MouseEvent error) {
            }
        });
        instructorFromModuleUpdateBtn.addActionListener(error -> {
            try {
                int selectedRow = dataTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(self, "Please select the row from table", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    String moduleName = Objects.requireNonNull(
                            adminInstructorPanel.getModuleName().getSelectedItem())
                            .toString();
                    String instructorEmail = Objects
                            .requireNonNull(adminInstructorPanel.getInstructorEmail()
                                    .getSelectedItem())
                            .toString();
                    int id = Integer.parseInt(courseAdministratorInstructorModel.getValueAt(selectedRow, 0).toString());
                    instructorToModuleTable.updateInstructorModules(id, moduleName, instructorEmail);
                    refreshAdminnstructorTable();
                    dataTable.clearSelection();
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Coding error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void updateObtainedMarks() {
        JButton updateMarksBtn = instructorPanel.getUpdateMarks();
        DefaultTableModel marksModel = instructorPanel.getInstructorModel();
        JTable dataTable = instructorPanel.getTable();
        dataTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent error) {
                int selectedRow = dataTable.getSelectedRow();
                instructorPanel.getCourseName().setSelectedItem(marksModel.getValueAt(selectedRow, 2).toString());
                instructorPanel.getLevel().setSelectedItem(marksModel.getValueAt(selectedRow, 3).toString());
                instructorPanel.getModuleName().setSelectedItem(marksModel.getValueAt(selectedRow, 4).toString());
                instructorPanel.getStudentEmail().setSelectedItem(marksModel.getValueAt(selectedRow, 1).toString());
                instructorPanel.getObtainedMarks().setText(marksModel.getValueAt(selectedRow, 5).toString());
            }

            @Override
            public void mousePressed(MouseEvent error) {
            }

            @Override
            public void mouseReleased(MouseEvent error) {
            }

            @Override
            public void mouseEntered(MouseEvent error) {
            }

            @Override
            public void mouseExited(MouseEvent error) {
            }
        });
        updateMarksBtn.addActionListener(error -> {
            try {
                int selectedRow = dataTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(self, "Please select the row from table to update marks", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    int obtainedMarks = Integer.parseInt(instructorPanel.getObtainedMarks().getText().trim());
                    String email = instructorPanel.getStudentEmail().getSelectedItem().toString();
                    int marks = Integer.parseInt(marksModel.getValueAt(selectedRow, 5).toString());
                    int marksId = Integer.parseInt(marksModel.getValueAt(selectedRow, 0).toString());
                    int fullMarks = Integer.parseInt(instructorPanel.getFullMarks().getText().trim());
                    if (marks == obtainedMarks) {
                        JOptionPane.showMessageDialog(self,
                                "<HTML><CENTER>Nothing to update.<br>Or you may have tried to change other data as they can't be changed.Only marks can be updated.</CENTER></HTML>");
                    } else {
                        String pass = "Pass";
                        String fail = "Fail";
                        if (obtainedMarks > fullMarks) {
                            JOptionPane.showMessageDialog(self, "Obtained Marks cannot be grater than full marks.",
                                    "Warning", JOptionPane.WARNING_MESSAGE);
                        } else {
                            if (obtainedMarks > 70) {
                                instructorPanelTable.updateMarks(marksId, obtainedMarks, 'A', pass);
                            } else if (obtainedMarks <= 70 && obtainedMarks >= 60) {
                                instructorPanelTable.updateMarks(marksId, obtainedMarks, 'B', pass);
                            } else if (obtainedMarks < 60 && obtainedMarks >= 50) {
                                instructorPanelTable.updateMarks(marksId, obtainedMarks, 'C', pass);
                            } else if (obtainedMarks < 50 && obtainedMarks >= 43) {
                                instructorPanelTable.updateMarks(marksId, obtainedMarks, 'D', pass);
                            } else if (obtainedMarks < 43 && obtainedMarks >= 40) {
                                instructorPanelTable.updateMarks(marksId, obtainedMarks, 'E', pass);
                            } else {
                                instructorPanelTable.updateMarks(marksId, obtainedMarks, 'F', fail);
                            }
                            refreshInstructorMarksTable();
                            JOptionPane.showMessageDialog(self,
                                    "Marks has been updated to open successfully of " + email + ".", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                            instructorPanel.getLevel().setSelectedIndex(0);
                            instructorPanel.getModuleName().setSelectedIndex(0);
                            instructorPanel.getStudentEmail().setSelectedIndex(0);
                            instructorPanel.getObtainedMarks().setText("");
                            dataTable.clearSelection();
                        }
                    }
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(self, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        });
    }

    // Delete

    private void deleteCourse() {
        JButton courseDeleteBtn = adminCoursePanel.getDeleteCourse();
        DefaultTableModel courseModel = adminCoursePanel.getCourseModel();
        JTable dataTable = adminCoursePanel.getTable();
        courseDeleteBtn.addActionListener(error -> {
            int isConfirmed = JOptionPane.showConfirmDialog(self, "Are you sure you want to Delete this data? ",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            int selectedRow = dataTable.getSelectedRow();
            if (isConfirmed == JOptionPane.YES_OPTION) {
                try {
                    adminCoursePanel.getCourseName().setText("");
                    int courseId = Integer.parseInt(courseModel.getValueAt(selectedRow, 0).toString());
                    coursetable.deleteCourse(courseId);
                    refreshCourseTable();
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(self, "No Row Selected", "Error", JOptionPane.ERROR_MESSAGE);
                    exception.printStackTrace();
                }
            }
        });
    }

    private void deleteModule() {
        JTable dataTable = adminModulePanel.getTable();
        JButton deleteBtn = adminModulePanel.getDeleteModule();
        DefaultTableModel model = adminModulePanel.getCourseAdministratorModuleModel();

        deleteBtn.addActionListener(error -> {
            int isConfirmed = JOptionPane.showConfirmDialog(self, "Are you sure you want to Delete this data? ",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            int selectedRow = dataTable.getSelectedRow();
            if (isConfirmed == JOptionPane.YES_OPTION) {
                try {
                    int moduleName = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
                    adminModulePanel.getModuleName().setText("");
                    refreshModuleCount();
                    moduleTable.deleteModule(moduleName);
                    refreshModuleTable();
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(self, "No Row Selected", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void deleteInstructorFromModule() {
        JTable dataTable = adminInstructorPanel.getTable();
        JButton deleteBtn = adminInstructorPanel
                .getDeleteInstructor();
        DefaultTableModel model = adminInstructorPanel
                .getCourseAdministratorInstructorModel();

        deleteBtn.addActionListener(error -> {
            int selectedRow = dataTable.getSelectedRow();
            int isConfirmed = JOptionPane.showConfirmDialog(self, "Are you sure you want to Delete this data? ",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (isConfirmed == JOptionPane.YES_OPTION) {
                try {
                    int id = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
                    instructorToModuleTable.deleteInstructorFromModule(id);
                    refreshAdminnstructorTable();
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(self, "No Row Selected", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        new MyApp();
    }
}
