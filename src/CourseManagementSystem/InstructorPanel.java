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
    private JTextField instructorName,email,studentName,obtainedMarks,fullMarks;
    private JComboBox courseName,level,moduleName,studentEmail;
    private JButton addMarks,updateMarks, logout;
    private GridBagConstraints layout, studentLayout,electiveLayout, buttonLayout;
    ModuleTable moduleTable;
    UserTable userTable;
    loginPanel login;
    StudentCourseTable studentCourseTable;
    InstructorPanelTable instructorPanelTable;
    JPanel studentPanelCoursesStudentElectiveSubjects;


    public InstructorPanel() {
        setBorder(BorderFactory.createTitledBorder("Register Instructor To Module"));
        String[] TableNames = {"ID","Student Email","Course Name","Level","Module Name","Obtained Marks","Pass Marks","Full Marks","Grade","Status"};
        String ll[]={"Select Level","4","5","6"};

        instructorName = new JTextField(20);
        instructorName.setPreferredSize(new Dimension(40,30));
        instructorName.setEnabled(false);
        instructorName.setDisabledTextColor(new Color(0,0,0));

        email = new JTextField(20);
        email.setPreferredSize(new Dimension(40,30));
        email.setEnabled(false);
        email.setDisabledTextColor(new Color(0,0,0));

        studentName = new JTextField(20);
        studentName.setPreferredSize(new Dimension(40,30));
        studentName.setEnabled(false);
        studentName.setDisabledTextColor(new Color(0,0,0));

        obtainedMarks = new JTextField(20);
        obtainedMarks.setPreferredSize(new Dimension(40,30));

        fullMarks = new JTextField(20);
        fullMarks.setPreferredSize(new Dimension(40,30));
        fullMarks.setEnabled(false);
        fullMarks.setDisabledTextColor(new Color(0,0,0));
        fullMarks.setText("100");


        courseName = new JComboBox();
        level = new JComboBox(ll);
        moduleName = new JComboBox();
        studentEmail = new JComboBox();

        DefaultTableModel courseAdministratorInstructorModel = new DefaultTableModel();
        courseAdministratorInstructorModel.setColumnIdentifiers(TableNames);

        addMarks = new JButton("Add Elective");
        updateMarks = new JButton("Update Marks");
        logout = new JButton("Logout");

        instructorTable = new JTable(courseAdministratorInstructorModel);

        moduleTable = new ModuleTable();
        userTable = new UserTable();
        login = new loginPanel();
        studentCourseTable = new StudentCourseTable();
        instructorPanelTable = new InstructorPanelTable();

        refreshStudentEmail();
        refreshStudentName();
    }

    public void loggedInInstructorData(String Email){
        try {
                ResultSet resultSet = userTable.getParticularUserData(Email);
                while (resultSet.next()) {
                    instructorName.setText(resultSet.getString("firstName") + " " + resultSet.getString("lastName"));
                    email.setText(resultSet.getString("email"));
                }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void instructorTeachingCourses(String Email){
        try {
            ResultSet resultSet = instructorPanelTable.getInstructorTeachingCourses(Email);
            courseName.addItem("Select Course");
            while (resultSet.next()) {
                courseName.addItem(resultSet.getString("modules.courseName"));
            }
            if (courseName.getItemCount() <= 2){
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

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void studentEmail() {
        try {
            String course = courseName.getSelectedItem().toString();
            String lvl = level.getSelectedItem().toString();
            ResultSet resultSet = instructorPanelTable.getStudentsDetailsOnInstructorPanel(course,lvl);
            studentEmail.removeAllItems();
            studentEmail.addItem("Select Student");
            while (resultSet.next()) {
                studentEmail.addItem(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void studentName() {
        if (studentEmail.getItemCount() == 0){
        }else {
            try {
                if (studentEmail.getSelectedItem().toString() == "Select Student"){
                    studentName.setText("");
                } else {
                    String course = courseName.getSelectedItem().toString();
                    String lvl = level.getSelectedItem().toString();
                    ResultSet resultSet = instructorPanelTable.getStudentsDetailsOnInstructorPanel(course, lvl);
                    while (resultSet.next()) {
                        studentName.setText(resultSet.getString("firstName") + " " + resultSet.getString("lastName"));
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void instructorTeachingModules() {
        try {
            String Email = email.getText().trim();
            String cName = courseName.getSelectedItem().toString().trim();
            String lvl = level.getSelectedItem().toString().trim();
            ResultSet resultSet = instructorPanelTable.getInstructorTeachingModules(Email,cName,lvl);
            moduleName.removeAllItems();
            moduleName.addItem("Select Modules");
            while (resultSet.next()) {
                moduleName.addItem(resultSet.getString("modules.moduleName"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshStudentEmail(){
        courseName.addActionListener(e -> {
            studentEmail();
            instructorTeachingModules();
        });

        level.addActionListener(e -> {
            studentEmail();
            instructorTeachingModules();
        });
    }

    private void refreshStudentName(){
        studentEmail.addActionListener(e -> studentName());
    }

    private JPanel instructorMarksAddTablePanel() {

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

    private JPanel InstructorPanelInstructorDetailsPanel(){
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

    private JPanel addMarksToStudentPanel() {
        studentPanelCoursesStudentElectiveSubjects = new JPanel();
        studentPanelCoursesStudentElectiveSubjects.setLayout(new GridBagLayout());
        studentPanelCoursesStudentElectiveSubjects.setBackground(Color.decode("#D6D9DF"));
        studentPanelCoursesStudentElectiveSubjects.setBorder(BorderFactory.createTitledBorder("Add Student Marks"));

        electiveLayout = new GridBagConstraints();
        electiveLayout.fill = GridBagConstraints.HORIZONTAL;
        electiveLayout.insets = new Insets(5,5,5,5);

        electiveLayout.gridx = 0;
        electiveLayout.gridy = 0;
        electiveLayout.gridwidth = 1;
        studentPanelCoursesStudentElectiveSubjects.add(new JLabel("Course Name"), electiveLayout);

        electiveLayout.weightx = 1;
        electiveLayout.gridwidth = 3;
        electiveLayout.gridx = 1;
        electiveLayout.gridy = 0;
        studentPanelCoursesStudentElectiveSubjects.add(courseName,electiveLayout);

        electiveLayout.gridx=0;
        electiveLayout.gridy=1;
        electiveLayout.gridwidth = 1;
        studentPanelCoursesStudentElectiveSubjects.add(new JLabel("Level"),electiveLayout);

        electiveLayout.gridx=1;
        electiveLayout.gridy=1;
        electiveLayout.gridwidth = 3;
        studentPanelCoursesStudentElectiveSubjects.add(level,electiveLayout);

        electiveLayout.gridx=0;
        electiveLayout.gridy=2;
        electiveLayout.gridwidth = 1;
        studentPanelCoursesStudentElectiveSubjects.add(new JLabel("Module Name"),electiveLayout);

        electiveLayout.gridx=1;
        electiveLayout.gridy=2;
        electiveLayout.gridwidth = 3;
        studentPanelCoursesStudentElectiveSubjects.add(moduleName,electiveLayout);

        electiveLayout.gridx=0;
        electiveLayout.gridy=3;
        electiveLayout.gridwidth = 1;
        studentPanelCoursesStudentElectiveSubjects.add(new JLabel("Student Email"),electiveLayout);

        electiveLayout.gridx=1;
        electiveLayout.gridy=3;
        electiveLayout.gridwidth = 3;
        studentPanelCoursesStudentElectiveSubjects.add(studentEmail,electiveLayout);

        electiveLayout.gridx=0;
        electiveLayout.gridy=4;
        electiveLayout.gridwidth = 1;
        studentPanelCoursesStudentElectiveSubjects.add(new JLabel("Student Name"),electiveLayout);

        electiveLayout.gridx=1;
        electiveLayout.gridy=4;
        electiveLayout.gridwidth = 3;
        studentPanelCoursesStudentElectiveSubjects.add(studentName,electiveLayout);

        electiveLayout.gridx=0;
        electiveLayout.gridy=5;
        electiveLayout.gridwidth = 1;
        studentPanelCoursesStudentElectiveSubjects.add(new JLabel("Obtained Marks"),electiveLayout);

        electiveLayout.gridx=1;
        electiveLayout.gridy=5;
        electiveLayout.gridwidth = 3;
        studentPanelCoursesStudentElectiveSubjects.add(obtainedMarks,electiveLayout);

        electiveLayout.gridx=0;
        electiveLayout.gridy=6;
        electiveLayout.gridwidth = 1;
        studentPanelCoursesStudentElectiveSubjects.add(new JLabel("Full Marks"),electiveLayout);

        electiveLayout.gridx=1;
        electiveLayout.gridy=6;
        electiveLayout.gridwidth = 3;
        studentPanelCoursesStudentElectiveSubjects.add(fullMarks,electiveLayout);


        return studentPanelCoursesStudentElectiveSubjects;
    }

    private JPanel StudentPanelCoursesButtonPanel() {
        JPanel studentPanelCoursesButtons = new JPanel();
        studentPanelCoursesButtons.setLayout(new GridBagLayout());
        studentPanelCoursesButtons.setBackground(Color.decode("#D6D9DF"));

        buttonLayout = new GridBagConstraints();
        buttonLayout.fill = GridBagConstraints.HORIZONTAL;
        buttonLayout.insets = new Insets(5,10,5,10);
        buttonLayout.ipadx = 25;
        buttonLayout.ipady = 25;
        buttonLayout.weightx = 1;

        buttonLayout.ipady = 5;
        buttonLayout.gridx=0;
        buttonLayout.gridy=0;
        studentPanelCoursesButtons.add(addMarks,buttonLayout);

        buttonLayout.ipady = 5;
        buttonLayout.gridx=1;
        buttonLayout.gridy=0;
        studentPanelCoursesButtons.add(updateMarks, buttonLayout);

        buttonLayout.ipady = 5;
        buttonLayout.gridx=2;
        buttonLayout.gridy=0;
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
        add(InstructorPanelInstructorDetailsPanel(),layout);

        layout.gridx = 1;
        layout.gridy = 1;
        add(addMarksToStudentPanel(),layout);

        layout.gridx  = 1;
        layout.gridy = 2;
        add(StudentPanelCoursesButtonPanel(), layout);

        layout.weighty = 0.5;
        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridheight=3;
        add(instructorMarksAddTablePanel(),layout);

        return this;
    }

    public DefaultTableModel getInstructorModel(){ return (DefaultTableModel) getTable().getModel();}
    public  JTable getTable(){
        return instructorTable;
    }
    public JTextField getEmail() {
        return email;
    }
    public JTextField getObtainedMarks() {
        return obtainedMarks;
    }
    public JTextField getFullMarks() {
        return fullMarks;
    }
    public JComboBox getCourseName(){
        return courseName;
    }
    public JComboBox getModuleName(){
        return moduleName;
    }
    public JComboBox getLevel(){
        return level;
    }
    public JComboBox getStudentEmail(){ return studentEmail; }
    public JButton getAddMarks(){ return addMarks; }
    public JButton getUpdateMarks(){ return updateMarks; }
    public JButton getLogout() {
        return logout;
    }
}

