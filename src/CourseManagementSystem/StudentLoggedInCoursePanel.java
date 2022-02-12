package CourseManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentLoggedInCoursePanel extends JPanel implements AppLayout {
    private DefaultTableModel courseAdministratorInstructorModel;
    private JTable StudentLoggedInCourseTable;
    private JTextField studentName, email, courseName, level;
    private JComboBox<String> semester, electiveModule;
    private JButton addElectiveSubject, viewResult, logout;
    private GridBagConstraints gridLayout, studentLayout, electiveLayout, buttonLayout;
    ModuleTable moduleTable;
    UserTable userTable;
    userLoginPanel login;
    StudentCourseTable studentCourseTable;
    JPanel studentPanelCoursesStudentElectiveSubjects;
    StudentPanelViewResultPanel studentPanelViewResultPanel;

    public StudentLoggedInCoursePanel() {
        setBorder(BorderFactory.createTitledBorder("Register Instructor To Module"));
        String[] TableNames = { "Course Name", "Module Name", "Semester", "Instructor Name", "Module Type" };

        studentName = new JTextField(20);
        studentName.setPreferredSize(new Dimension(40, 30));
        studentName.setEnabled(false);
        studentName.setDisabledTextColor(new Color(0, 0, 0));
        email = new JTextField(20);
        email.setPreferredSize(new Dimension(40, 30));
        email.setEnabled(false);
        email.setDisabledTextColor(new Color(0, 0, 0));
        courseName = new JTextField(20);
        courseName.setPreferredSize(new Dimension(40, 30));
        courseName.setEnabled(false);
        courseName.setDisabledTextColor(new Color(0, 0, 0));
        level = new JTextField(20);
        level.setPreferredSize(new Dimension(40, 30));
        level.setEnabled(false);
        level.setDisabledTextColor(new Color(0, 0, 0));

        String sem[] = { "Select Semester", "1", "2" };
        semester = new JComboBox<>(sem);
        electiveModule = new JComboBox<>();

        courseAdministratorInstructorModel = new DefaultTableModel();
        courseAdministratorInstructorModel.setColumnIdentifiers(TableNames);

        addElectiveSubject = new JButton("Add Elective");
        viewResult = new JButton("View Result");
        logout = new JButton("Logout");

        StudentLoggedInCourseTable = new JTable(courseAdministratorInstructorModel);

        moduleTable = new ModuleTable();
        userTable = new UserTable();
        login = new userLoginPanel();
        studentCourseTable = new StudentCourseTable();
        studentPanelViewResultPanel = new StudentPanelViewResultPanel();

        electiveSubjects();
        refreshElectiveSubjects();
    }

    private void electiveSubjects() {
        try {
            String sem = semester.getSelectedItem().toString();
            ResultSet resultSet = studentCourseTable.getModuleNameAtStudentPanel(sem);
            electiveModule.removeAllItems();
            electiveModule.addItem("Select Elective Module");
            while (resultSet.next()) {
                electiveModule.addItem(resultSet.getString("moduleName"));
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(this, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshElectiveSubjects() {
        semester.addActionListener(error -> {
            electiveSubjects();
        });
    }

    public void loggedInStudentData(String Email) {
        try {
            ResultSet resultSet = userTable.getUser(Email);
            while (resultSet.next()) {
                studentName.setText(resultSet.getString("firstName") + " " + resultSet.getString("lastName"));
                email.setText(resultSet.getString("email"));
                courseName.setText(resultSet.getString("courseName"));
                level.setText(resultSet.getString("level"));
            }
            String lvl = level.getText().trim();
            if (!lvl.equals("6")) {
                semester.setEnabled(false);
                electiveModule.setEnabled(false);
            } else {
                semester.setEnabled(true);
                electiveModule.setEnabled(true);
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel StudentPanelCoursesTablePanel() {

        JScrollPane scrollPane = new JScrollPane(StudentLoggedInCourseTable);
        StudentLoggedInCourseTable.setDefaultEditor(Object.class, null);
        StudentLoggedInCourseTable.setSelectionBackground(Color.BLACK);
        StudentLoggedInCourseTable.setSelectionForeground(Color.WHITE);
        JTableHeader header = StudentLoggedInCourseTable.getTableHeader();
        header.setBackground(Color.decode("#D6D9DE"));

        JPanel courseAdministratorCoursePanel = new JPanel();
        courseAdministratorCoursePanel.setBackground(Color.decode("#D6D9DE"));
        courseAdministratorCoursePanel.add(scrollPane);

        return courseAdministratorCoursePanel;
    }

    private JPanel StudentPanelCoursesStudentDetailsPanel() {
        JPanel studentPanelCoursesStudentDetails = new JPanel();
        studentPanelCoursesStudentDetails.setLayout(new GridBagLayout());
        studentPanelCoursesStudentDetails.setBackground(Color.decode("#D6D9DE"));
        studentPanelCoursesStudentDetails.setBorder(BorderFactory.createTitledBorder("Student Details"));

        studentLayout = new GridBagConstraints();
        studentLayout.fill = GridBagConstraints.HORIZONTAL;
        studentLayout.insets = new Insets(5, 5, 5, 5);

        studentLayout.gridx = 0;
        studentLayout.gridy = 0;
        studentPanelCoursesStudentDetails.add(new JLabel("Student Name"), studentLayout);

        studentLayout.gridx = 1;
        studentLayout.gridy = 0;
        studentLayout.weightx = 1;
        studentLayout.gridwidth = 3;
        studentPanelCoursesStudentDetails.add(studentName, studentLayout);

        studentLayout.gridx = 0;
        studentLayout.gridy = 1;
        studentPanelCoursesStudentDetails.add(new JLabel("Email"), studentLayout);

        studentLayout.gridx = 1;
        studentLayout.gridy = 1;
        studentLayout.weightx = 1;
        studentLayout.gridwidth = 3;
        studentPanelCoursesStudentDetails.add(email, studentLayout);

        studentLayout.gridx = 0;
        studentLayout.gridy = 2;
        studentLayout.gridwidth = 1;
        studentPanelCoursesStudentDetails.add(new JLabel("Enrolled Course"), studentLayout);

        studentLayout.gridx = 1;
        studentLayout.gridy = 2;
        studentLayout.gridwidth = 3;
        studentPanelCoursesStudentDetails.add(courseName, studentLayout);

        studentLayout.gridx = 0;
        studentLayout.gridy = 3;
        studentLayout.gridwidth = 1;
        studentPanelCoursesStudentDetails.add(new JLabel("Level"), studentLayout);

        studentLayout.gridx = 1;
        studentLayout.gridy = 3;
        studentLayout.gridwidth = 3;
        studentPanelCoursesStudentDetails.add(level, studentLayout);

        return studentPanelCoursesStudentDetails;
    }

    private JPanel StudentPanelLevelSixOptionalCourses() {
        studentPanelCoursesStudentElectiveSubjects = new JPanel();
        studentPanelCoursesStudentElectiveSubjects.setLayout(new GridBagLayout());
        studentPanelCoursesStudentElectiveSubjects.setBackground(Color.decode("#D6D9DE"));
        studentPanelCoursesStudentElectiveSubjects.setBorder(BorderFactory.createTitledBorder("Studying Modules"));

        electiveLayout = new GridBagConstraints();
        electiveLayout.fill = GridBagConstraints.HORIZONTAL;
        electiveLayout.insets = new Insets(5, 5, 5, 5);

        electiveLayout.gridx = 0;
        electiveLayout.gridy = 0;
        electiveLayout.gridwidth = 1;
        studentPanelCoursesStudentElectiveSubjects.add(new JLabel("Semester"), electiveLayout);

        electiveLayout.weightx = 1;
        electiveLayout.gridwidth = 3;
        electiveLayout.gridx = 1;
        electiveLayout.gridy = 0;
        studentPanelCoursesStudentElectiveSubjects.add(semester, electiveLayout);

        electiveLayout.gridx = 0;
        electiveLayout.gridy = 1;
        electiveLayout.gridwidth = 1;
        studentPanelCoursesStudentElectiveSubjects.add(new JLabel("Elective Module"), electiveLayout);

        electiveLayout.gridx = 1;
        electiveLayout.gridy = 1;
        electiveLayout.gridwidth = 3;
        studentPanelCoursesStudentElectiveSubjects.add(electiveModule, electiveLayout);

        return studentPanelCoursesStudentElectiveSubjects;
    }

    private JPanel StudentPanelCoursesButtonPanel() {
        JPanel studentPanelCoursesButtons = new JPanel();
        studentPanelCoursesButtons.setLayout(new GridBagLayout());
        studentPanelCoursesButtons.setBackground(Color.decode("#D6D9DE"));

        buttonLayout = new GridBagConstraints();
        buttonLayout.fill = GridBagConstraints.HORIZONTAL;
        buttonLayout.insets = new Insets(5, 10, 5, 10);
        buttonLayout.ipadx = 20;
        buttonLayout.ipady = 20;
        buttonLayout.weightx = 1;
        buttonLayout.gridwidth = 3;

        buttonLayout.ipady = 5;
        buttonLayout.gridx = 0;
        buttonLayout.gridy = 0;
        buttonLayout.gridwidth = 4;
        studentPanelCoursesButtons.add(addElectiveSubject, buttonLayout);

        buttonLayout.ipady = 5;
        buttonLayout.gridx = 0;
        buttonLayout.gridy = 1;
        buttonLayout.gridwidth = 4;
        studentPanelCoursesButtons.add(viewResult, buttonLayout);

        buttonLayout.ipady = 5;
        buttonLayout.gridx = 0;
        buttonLayout.gridy = 2;
        buttonLayout.gridwidth = 4;
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
        add(StudentPanelCoursesStudentDetailsPanel(), gridLayout);

        gridLayout.gridx = 1;
        gridLayout.gridy = 1;
        add(StudentPanelLevelSixOptionalCourses(), gridLayout);

        gridLayout.gridx = 1;
        gridLayout.gridy = 2;
        add(StudentPanelCoursesButtonPanel(), gridLayout);

        gridLayout.weighty = 0.5;
        gridLayout.gridx = 0;
        gridLayout.gridy = 0;
        gridLayout.gridheight = 3;
        add(StudentPanelCoursesTablePanel(), gridLayout);

        return this;
    }

    public DefaultTableModel getStudentLoggedInCourseModel() {
        return (DefaultTableModel) getTable().getModel();
    }

    public JTable getTable() {
        return StudentLoggedInCourseTable;
    }

    public JTextField getCourseName() {
        return courseName;
    }

    public JTextField getEmail() {
        return email;
    }

    public JTextField getLevel() {
        return level;
    }

    public JComboBox<String> getSemester() {
        return semester;
    }

    public JComboBox<String> getElectiveModule() {
        return electiveModule;
    }

    public JButton getAddElectiveSubject() {
        return addElectiveSubject;
    }

    public JButton getViewResult() {
        return viewResult;
    }

    public JButton getLogout() {
        return logout;
    }
}
