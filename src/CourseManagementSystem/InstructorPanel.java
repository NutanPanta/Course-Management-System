package CourseManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InstructorPanel extends JPanel implements AppLayout {
    private DefaultTableModel instructorModel;
    private JTable instructorTable;
    private JTextField instructorName,email;
    private JComboBox semester, electiveModule;
    private JButton addElectiveSubject,viewResult, logout;
    private GridBagConstraints layout, studentLayout,electiveLayout, buttonLayout;
    ModuleTable moduleTable;
    UserTable userTable;
    loginPanel login;
    StudentCourseTable studentCourseTable;
    JPanel studentPanelCoursesStudentElectiveSubjects;


    public InstructorPanel() {
        setBorder(BorderFactory.createTitledBorder("Register Instructor To Module"));
        String[] TableNames = {"Module Name","moduleType","semester","Instructor Name"};

        instructorName = new JTextField(20);
        instructorName.setPreferredSize(new Dimension(40,30));
        instructorName.setEnabled(false);
        instructorName.setDisabledTextColor(new Color(0,0,0));
        email = new JTextField(20);
        email.setPreferredSize(new Dimension(40,30));
        email.setEnabled(false);
        email.setDisabledTextColor(new Color(0,0,0));

        String sem[] = {"Select Semester","1","2"};
        semester = new JComboBox(sem);
        electiveModule = new JComboBox();

        DefaultTableModel courseAdministratorInstructorModel = new DefaultTableModel();
        courseAdministratorInstructorModel.setColumnIdentifiers(TableNames);

        addElectiveSubject = new JButton("Add Elective");
        viewResult = new JButton("View Result");
        logout = new JButton("Logout");

        instructorTable = new JTable(courseAdministratorInstructorModel);

        moduleTable = new ModuleTable();
        userTable = new UserTable();
        login = new loginPanel();
        studentCourseTable = new StudentCourseTable();
    }

    public void loggedInInstructorData(String Email){
        try {
                ResultSet resultSet = userTable.getParticularStudentData(Email);
                while (resultSet.next()) {
                    instructorName.setText(resultSet.getString("firstName") + " " + resultSet.getString("lastName"));
                    email.setText(resultSet.getString("email"));
                }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel courseAdministratorCourseTablePanel() {

        JScrollPane scrollPane =new JScrollPane(instructorTable);
        instructorTable.setDefaultEditor(Object.class, null);
        instructorTable.setSelectionBackground(Color.BLACK);
        instructorTable.setSelectionForeground(Color.WHITE);
        JTableHeader header = instructorTable.getTableHeader();
        header.setBackground(Color.decode("#D6D9DF"));

        JPanel courseAdministratorCoursePanel = new JPanel();
        courseAdministratorCoursePanel.setBackground(Color.decode("#D6D9DF"));
        courseAdministratorCoursePanel.add(scrollPane);

        return courseAdministratorCoursePanel;
    }

    private JPanel StudentPanelCoursesStudentDetailsPanel(){
        JPanel studentPanelCoursesStudentDetails = new JPanel();
        studentPanelCoursesStudentDetails.setLayout(new GridBagLayout());
        studentPanelCoursesStudentDetails.setBackground(Color.decode("#D6D9DF"));
        studentPanelCoursesStudentDetails.setBorder(BorderFactory.createTitledBorder("Instructor Details"));

        studentLayout = new GridBagConstraints();
        studentLayout.fill = GridBagConstraints.HORIZONTAL;
        studentLayout.insets = new Insets(5,5,5,5);

        studentLayout.gridx = 0;
        studentLayout.gridy = 0;
        studentPanelCoursesStudentDetails.add(new JLabel("Instructor Name"),studentLayout);

        studentLayout.weightx = 1;
        studentLayout.gridwidth = 3;
        studentLayout.gridx = 1;
        studentLayout.gridy = 0;
        studentPanelCoursesStudentDetails.add(instructorName,studentLayout);

        studentLayout.gridx = 0;
        studentLayout.gridy = 1;
        studentPanelCoursesStudentDetails.add(new JLabel("Email"),studentLayout);

        studentLayout.gridx = 1;
        studentLayout.gridy = 1;
        studentLayout.weightx = 1;
        studentLayout.gridwidth = 3;
        studentPanelCoursesStudentDetails.add(email,studentLayout);

        return studentPanelCoursesStudentDetails;
    }

    private JPanel StudentPanelLevelSixOptionalCourses() {
        studentPanelCoursesStudentElectiveSubjects = new JPanel();
        studentPanelCoursesStudentElectiveSubjects.setLayout(new GridBagLayout());
        studentPanelCoursesStudentElectiveSubjects.setBackground(Color.decode("#D6D9DF"));
        studentPanelCoursesStudentElectiveSubjects.setBorder(BorderFactory.createTitledBorder("Student Elective Module Panel"));

        electiveLayout = new GridBagConstraints();
        electiveLayout.fill = GridBagConstraints.HORIZONTAL;
        electiveLayout.insets = new Insets(5,5,5,5);

        electiveLayout.gridx = 0;
        electiveLayout.gridy = 0;
        electiveLayout.gridwidth = 1;
        studentPanelCoursesStudentElectiveSubjects.add(new JLabel("Semester"), electiveLayout);

        electiveLayout.weightx = 1;
        electiveLayout.gridwidth = 3;
        electiveLayout.gridx = 1;
        electiveLayout.gridy = 0;
        studentPanelCoursesStudentElectiveSubjects.add(semester,electiveLayout);

        electiveLayout.gridx=0;
        electiveLayout.gridy=1;
        electiveLayout.gridwidth = 1;
        studentPanelCoursesStudentElectiveSubjects.add(new JLabel("Elective Module"),electiveLayout);

        electiveLayout.gridx=1;
        electiveLayout.gridy=1;
        electiveLayout.gridwidth = 3;
        studentPanelCoursesStudentElectiveSubjects.add(electiveModule,electiveLayout);

        return studentPanelCoursesStudentElectiveSubjects;
    }

    private JPanel StudentPanelCoursesButtonPanel() {
        JPanel studentPanelCoursesButtons = new JPanel();
        studentPanelCoursesButtons.setLayout(new GridBagLayout());
        studentPanelCoursesButtons.setBackground(Color.decode("#D6D9DF"));

        buttonLayout = new GridBagConstraints();
        buttonLayout.fill = GridBagConstraints.HORIZONTAL;
        buttonLayout.insets = new Insets(5,10,5,10);
        buttonLayout.ipadx = 20;
        buttonLayout.ipady = 20;
        buttonLayout.weightx = 1;
        buttonLayout.gridwidth = 3;

        buttonLayout.ipady = 5;
        buttonLayout.gridx=0;
        buttonLayout.gridy=0;
        buttonLayout.gridwidth = 4;
        studentPanelCoursesButtons.add(addElectiveSubject,buttonLayout);

        buttonLayout.ipady = 5;
        buttonLayout.gridx=0;
        buttonLayout.gridy=1;
        buttonLayout.gridwidth = 4;
        studentPanelCoursesButtons.add(viewResult, buttonLayout);

        buttonLayout.ipady = 5;
        buttonLayout.gridx=0;
        buttonLayout.gridy=2;
        buttonLayout.gridwidth = 4;
        studentPanelCoursesButtons.add(logout, buttonLayout);

        return studentPanelCoursesButtons;


    }

    @Override
    public JPanel panelUI() {
        setLayout(new GridBagLayout());
        layout = new GridBagConstraints();
        layout.fill = GridBagConstraints.BOTH;
        layout.insets = new Insets(5,0,5,0);
        this.setBackground(Color.decode("#D6D9DF"));

        layout.gridx=3;
        layout.gridy=3;

        layout.gridx = 1;
        layout.gridy = 0;
        add(StudentPanelCoursesStudentDetailsPanel(),layout);

        layout.gridx = 1;
        layout.gridy = 1;
        add(StudentPanelLevelSixOptionalCourses(),layout);

        layout.gridx  = 1;
        layout.gridy = 2;
        add(StudentPanelCoursesButtonPanel(), layout);

        layout.weighty = 0.5;
        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridheight=3;
        add(courseAdministratorCourseTablePanel(),layout);

        return this;
    }

    public DefaultTableModel getInstructorModel(){ return (DefaultTableModel) getTable().getModel();}
    public  JTable getTable(){
        return instructorTable;
    }
    public JTextField getEmail() {
        return email;
    }
    public JComboBox getSemester(){
        return semester;
    }
    public JComboBox getElectiveModule(){ return electiveModule; }
    public JButton getAddElectiveSubject(){ return addElectiveSubject; }
    public JButton getLogout() {
        return logout;
    }
}

