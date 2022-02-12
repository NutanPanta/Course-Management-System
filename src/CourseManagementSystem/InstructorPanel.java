package CourseManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InstructorPanel extends JPanel implements AppLayout {
    private DefaultTableModel courseAdministratorInstructorModel;
    private JTable instructorTable;
    private JTextField instructorName, email, studentName, obtainedMarks, fullMarks, moduleType;
    private JComboBox<String> courseName, level, moduleName, studentEmail;
    private JButton addMarks, updateMarks, logout;
    private GridBagConstraints gridLayout, studentLayout, electiveLayout, buttonLayout;
    ModuleTable moduleTable;
    UserTable userTable;
    userLoginPanel login;
    StudentCourseTable studentCourseTable;
    InstructorPanelTable instructorPanelTable;
    JPanel studentPanelCoursesStudentElectiveSubjects;
    String MT;

    public InstructorPanel() {
        setBorder(BorderFactory.createTitledBorder("Register Instructor To Module"));
        String[] TableNames = { "ID", "Student Email", "Course Name", "Level", "Module Name", "Obtained Marks",
                "Pass Marks", "Full Marks", "Grade", "Status" };
        String ll[] = { "Select Level", "4", "5", "6" };

        instructorName = new JTextField(20);
        instructorName.setPreferredSize(new Dimension(40, 30));
        instructorName.setEnabled(false);
        instructorName.setDisabledTextColor(new Color(0, 0, 0));

        email = new JTextField(20);
        email.setPreferredSize(new Dimension(40, 30));
        email.setEnabled(false);
        email.setDisabledTextColor(new Color(0, 0, 0));

        studentName = new JTextField(20);
        studentName.setPreferredSize(new Dimension(40, 30));
        studentName.setEnabled(false);
        studentName.setDisabledTextColor(new Color(0, 0, 0));

        obtainedMarks = new JTextField(20);
        obtainedMarks.setPreferredSize(new Dimension(40, 30));

        fullMarks = new JTextField(20);
        fullMarks.setPreferredSize(new Dimension(40, 30));
        fullMarks.setEnabled(false);
        fullMarks.setDisabledTextColor(new Color(0, 0, 0));
        fullMarks.setText("100");

        moduleType = new JTextField(20);
        moduleType.setPreferredSize(new Dimension(40, 30));
        moduleType.setEnabled(false);
        moduleType.setDisabledTextColor(new Color(0, 0, 0));

        courseName = new JComboBox<>();
        level = new JComboBox<>(ll);
        moduleName = new JComboBox<>();
        studentEmail = new JComboBox<>();
        studentEmail.addItem("Select Student");

        courseAdministratorInstructorModel = new DefaultTableModel();
        courseAdministratorInstructorModel.setColumnIdentifiers(TableNames);

        addMarks = new JButton("Add Marks");
        updateMarks = new JButton("Update Marks");
        logout = new JButton("Logout");

        instructorTable = new JTable(courseAdministratorInstructorModel);

        moduleTable = new ModuleTable();
        userTable = new UserTable();
        login = new userLoginPanel();
        studentCourseTable = new StudentCourseTable();
        instructorPanelTable = new InstructorPanelTable();

        refreshStudentEmail();
        refreshStudentName();
    }

    public void loggedInInstructorData(String Email) {
        try {
            ResultSet resultSet = userTable.getUser(Email);
            while (resultSet.next()) {
                instructorName.setText(resultSet.getString("firstName") + " " + resultSet.getString("lastName"));
                email.setText(resultSet.getString("email"));
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void instructorTeachingCourses(String Email) {
        try {
            ResultSet resultSet = instructorPanelTable.getInstructorCourses(Email);
            courseName.addItem("Select Course");
            courseName.removeAllItems();
            while (resultSet.next()) {
                courseName.addItem(resultSet.getString("modules.courseName"));
            }
            if (courseName.getItemCount() <= 2) {
                courseName.setSelectedIndex(1);
                courseName.setEnabled(false);
                courseName.setRenderer(new DefaultListCellRenderer() {
                    @Override
                    public void paint(Graphics g) {
                        setForeground(Color.BLACK);
                        super.paint(g);
                    }
                });
            } else {
                courseName.setSelectedIndex(0);
                courseName.setEnabled(true);
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void studentEmail() {
        if (courseName.getItemCount() == 0) {
            courseName.addItem("Select Course");
        } else {
            try {
                String course = courseName.getSelectedItem().toString();
                String lvl = level.getSelectedItem().toString();
                ResultSet resultSet = instructorPanelTable.getStudentCompDetails(course, lvl);
                studentEmail.removeAllItems();
                while (resultSet.next()) {
                    if (resultSet.getString("moduleType").equals("Compulsory")) {
                        studentEmail.addItem(resultSet.getString("email"));
                    }
                }
            } catch (SQLException error) {
                JOptionPane.showMessageDialog(this, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);
                error.printStackTrace();
            }
        }
    }

    private void studentEmail1() {
        try {
            String Email = email.getText().trim();
            ResultSet resultSet1 = instructorPanelTable.getStudentOptDetails(Email);
            studentEmail.removeAllItems();
            while (resultSet1.next()) {
                studentEmail.addItem(resultSet1.getString("email"));
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(this, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
            error.printStackTrace();
        }
    }

    private void studentName() {
        if (studentEmail.getItemCount() == 0) {
            studentEmail.addItem("Select Student");
        } else {
            try {
                if (studentEmail.getSelectedItem().toString() == "Select Student") {
                    studentName.setText("");
                } else {
                    String course = courseName.getSelectedItem().toString();
                    String lvl = level.getSelectedItem().toString();
                    String mail = studentEmail.getSelectedItem().toString().trim();
                    ResultSet resultSet = instructorPanelTable.getStudentsDetails(mail, course, lvl);
                    while (resultSet.next()) {
                        studentName.setText(resultSet.getString("firstName") + " " + resultSet.getString("lastName"));
                    }
                }
            } catch (SQLException error) {
                JOptionPane.showMessageDialog(this, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void moduleType() {
        if (moduleName.getItemCount() == 0) {
        } else {
            try {
                if (moduleName.getSelectedItem().toString() == "Select Modules") {
                    moduleType.setText("");
                } else {
                    String Email = email.getText().trim();
                    String module = moduleName.getSelectedItem().toString().trim();
                    String cName = courseName.getSelectedItem().toString().trim();
                    ResultSet resultSet = instructorPanelTable.getInstructorModuleName(Email, cName, module);
                    while (resultSet.next()) {
                        moduleType.setText(resultSet.getString("modules.moduleType"));
                    }
                }
            } catch (SQLException error) {
                JOptionPane.showMessageDialog(this, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);
                error.printStackTrace();
            }
        }
    }

    public void instructorTeachingModules() {
        try {
            String Email = email.getText().trim();
            String cName = courseName.getSelectedItem().toString().trim();
            String lvl = level.getSelectedItem().toString().trim();
            ResultSet resultSet = instructorPanelTable.getInstructorModules(Email, cName, lvl);
            moduleName.removeAllItems();
            moduleName.addItem("Select Modules");
            while (resultSet.next()) {
                moduleName.addItem(resultSet.getString("modules.moduleName"));
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
            error.printStackTrace();
        }
    }

    private void refreshStudentEmail() {
        courseName.addActionListener(error -> {
            studentEmail();
            instructorTeachingModules();
        });

        level.addActionListener(error -> {
            studentEmail();
            instructorTeachingModules();
        });

        moduleName.addActionListener(error -> {
            moduleType();
            if (moduleType.getText().trim().equals("Elective")) {
                studentEmail1();
            } else {
                studentEmail();
            }
        });

    }

    private void refreshStudentName() {
        studentEmail.addActionListener(error -> studentName());
    }

    private JPanel instructorMarksAddTablePanel() {

        JScrollPane scrollPane = new JScrollPane(instructorTable);
        instructorTable.setDefaultEditor(Object.class, null);
        instructorTable.setSelectionBackground(Color.BLACK);
        instructorTable.setSelectionForeground(Color.WHITE);
        JTableHeader header = instructorTable.getTableHeader();
        header.setBackground(Color.decode("#D6D9DE"));

        JPanel courseAdministratorCoursePanel = new JPanel();
        courseAdministratorCoursePanel.setBackground(Color.decode("#D6D9DE"));
        courseAdministratorCoursePanel.add(scrollPane);

        return courseAdministratorCoursePanel;
    }

    private JPanel InstructorPanelInstructorDetailsPanel() {
        JPanel studentPanelCoursesStudentDetails = new JPanel();
        studentPanelCoursesStudentDetails.setLayout(new GridBagLayout());
        studentPanelCoursesStudentDetails.setBackground(Color.decode("#D6D9DE"));
        studentPanelCoursesStudentDetails.setBorder(BorderFactory.createTitledBorder("Instructor Details"));

        studentLayout = new GridBagConstraints();
        studentLayout.fill = GridBagConstraints.HORIZONTAL;
        studentLayout.insets = new Insets(5, 5, 5, 5);

        studentLayout.gridx = 0;
        studentLayout.gridy = 0;
        studentPanelCoursesStudentDetails.add(new JLabel("Instructor Name"), studentLayout);

        studentLayout.weightx = 1;
        studentLayout.gridwidth = 3;
        studentLayout.gridx = 1;
        studentLayout.gridy = 0;
        studentPanelCoursesStudentDetails.add(instructorName, studentLayout);

        studentLayout.gridx = 0;
        studentLayout.gridy = 1;
        studentPanelCoursesStudentDetails.add(new JLabel("Email"), studentLayout);

        studentLayout.gridx = 1;
        studentLayout.gridy = 1;
        studentLayout.weightx = 1;
        studentLayout.gridwidth = 3;
        studentPanelCoursesStudentDetails.add(email, studentLayout);

        return studentPanelCoursesStudentDetails;
    }

    private JPanel addMarksToStudentPanel() {
        studentPanelCoursesStudentElectiveSubjects = new JPanel();
        studentPanelCoursesStudentElectiveSubjects.setLayout(new GridBagLayout());
        studentPanelCoursesStudentElectiveSubjects.setBackground(Color.decode("#D6D9DE"));
        studentPanelCoursesStudentElectiveSubjects.setBorder(BorderFactory.createTitledBorder("Add Student Marks"));

        electiveLayout = new GridBagConstraints();
        electiveLayout.fill = GridBagConstraints.HORIZONTAL;
        electiveLayout.insets = new Insets(5, 5, 5, 5);

        electiveLayout.gridx = 0;
        electiveLayout.gridy = 0;
        electiveLayout.gridwidth = 1;
        studentPanelCoursesStudentElectiveSubjects.add(new JLabel("Course Name"), electiveLayout);

        electiveLayout.weightx = 1;
        electiveLayout.gridwidth = 3;
        electiveLayout.gridx = 1;
        electiveLayout.gridy = 0;
        studentPanelCoursesStudentElectiveSubjects.add(courseName, electiveLayout);

        electiveLayout.gridx = 0;
        electiveLayout.gridy = 1;
        electiveLayout.gridwidth = 1;
        studentPanelCoursesStudentElectiveSubjects.add(new JLabel("Level"), electiveLayout);

        electiveLayout.gridx = 1;
        electiveLayout.gridy = 1;
        electiveLayout.gridwidth = 3;
        studentPanelCoursesStudentElectiveSubjects.add(level, electiveLayout);

        electiveLayout.gridx = 0;
        electiveLayout.gridy = 2;
        electiveLayout.gridwidth = 1;
        studentPanelCoursesStudentElectiveSubjects.add(new JLabel("Module Name"), electiveLayout);

        electiveLayout.gridx = 1;
        electiveLayout.gridy = 2;
        electiveLayout.gridwidth = 3;
        studentPanelCoursesStudentElectiveSubjects.add(moduleName, electiveLayout);

        electiveLayout.gridx = 0;
        electiveLayout.gridy = 3;
        electiveLayout.gridwidth = 1;
        studentPanelCoursesStudentElectiveSubjects.add(new JLabel("Student Email"), electiveLayout);

        electiveLayout.gridx = 1;
        electiveLayout.gridy = 3;
        electiveLayout.gridwidth = 3;
        studentPanelCoursesStudentElectiveSubjects.add(studentEmail, electiveLayout);

        electiveLayout.gridx = 0;
        electiveLayout.gridy = 4;
        electiveLayout.gridwidth = 1;
        studentPanelCoursesStudentElectiveSubjects.add(new JLabel("Student Name"), electiveLayout);

        electiveLayout.gridx = 1;
        electiveLayout.gridy = 4;
        electiveLayout.gridwidth = 3;
        studentPanelCoursesStudentElectiveSubjects.add(studentName, electiveLayout);

        electiveLayout.gridx = 0;
        electiveLayout.gridy = 5;
        electiveLayout.gridwidth = 1;
        studentPanelCoursesStudentElectiveSubjects.add(new JLabel("Obtained Marks"), electiveLayout);

        electiveLayout.gridx = 1;
        electiveLayout.gridy = 5;
        electiveLayout.gridwidth = 3;
        studentPanelCoursesStudentElectiveSubjects.add(obtainedMarks, electiveLayout);

        electiveLayout.gridx = 0;
        electiveLayout.gridy = 6;
        electiveLayout.gridwidth = 1;
        studentPanelCoursesStudentElectiveSubjects.add(new JLabel("Full Marks"), electiveLayout);

        electiveLayout.gridx = 1;
        electiveLayout.gridy = 6;
        electiveLayout.gridwidth = 3;
        studentPanelCoursesStudentElectiveSubjects.add(fullMarks, electiveLayout);

        return studentPanelCoursesStudentElectiveSubjects;
    }

    private JPanel StudentPanelCoursesButtonPanel() {
        JPanel studentPanelCoursesButtons = new JPanel();
        studentPanelCoursesButtons.setLayout(new GridBagLayout());
        studentPanelCoursesButtons.setBackground(Color.decode("#D6D9DE"));

        buttonLayout = new GridBagConstraints();
        buttonLayout.fill = GridBagConstraints.HORIZONTAL;
        buttonLayout.insets = new Insets(5, 10, 5, 10);
        buttonLayout.ipadx = 25;
        buttonLayout.ipady = 25;
        buttonLayout.weightx = 1;

        buttonLayout.ipady = 5;
        buttonLayout.gridx = 0;
        buttonLayout.gridy = 0;
        studentPanelCoursesButtons.add(addMarks, buttonLayout);

        buttonLayout.ipady = 5;
        buttonLayout.gridx = 1;
        buttonLayout.gridy = 0;
        studentPanelCoursesButtons.add(updateMarks, buttonLayout);

        buttonLayout.ipady = 5;
        buttonLayout.gridx = 2;
        buttonLayout.gridy = 0;
        studentPanelCoursesButtons.add(logout, buttonLayout);

        return studentPanelCoursesButtons;

    }

    @Override
    public JPanel panelUI() {
        setLayout(new GridBagLayout());
        gridLayout = new GridBagConstraints();
        gridLayout.fill = GridBagConstraints.BOTH;
        gridLayout.insets = new Insets(5, 0, 5, 0);
        this.setBackground(Color.decode("#D6D9DE"));

        gridLayout.gridx = 3;
        gridLayout.gridy = 3;

        gridLayout.gridx = 1;
        gridLayout.gridy = 0;
        add(InstructorPanelInstructorDetailsPanel(), gridLayout);

        gridLayout.gridx = 1;
        gridLayout.gridy = 1;
        add(addMarksToStudentPanel(), gridLayout);

        gridLayout.gridx = 1;
        gridLayout.gridy = 2;
        add(StudentPanelCoursesButtonPanel(), gridLayout);

        gridLayout.weighty = 0.5;
        gridLayout.gridx = 0;
        gridLayout.gridy = 0;
        gridLayout.gridheight = 3;
        add(instructorMarksAddTablePanel(), gridLayout);

        return this;
    }

    public JTable getTable() {
        return instructorTable;
    }

    public JTextField getEmail() {
        return email;
    }

    public JTextField getFullMarks() {
        return fullMarks;
    }

    public JButton getUpdateMarks() {
        return updateMarks;
    }

    public JComboBox<String> getStudentEmail() {
        return studentEmail;
    }

    public JButton getAddMarks() {
        return addMarks;
    }

    public DefaultTableModel getInstructorModel() {
        return (DefaultTableModel) getTable().getModel();
    }

    public JTextField getObtainedMarks() {
        return obtainedMarks;
    }

    public JComboBox<String> getLevel() {
        return level;
    }

    public JComboBox<String> getModuleName() {
        return moduleName;
    }

    public JComboBox<String> getCourseName() {
        return courseName;
    }

    public JButton getLogout() {
        return logout;
    }
}
