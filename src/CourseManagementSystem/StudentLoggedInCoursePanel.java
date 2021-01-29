package CourseManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentLoggedInCoursePanel extends JPanel implements AppLayout {
    private DefaultTableModel StudentLoggedInCourseModel;
    private JTable StudentLoggedInCourseTable;
    private JTextField studentName, courseName, level;
    private JComboBox semester, electiveSubjectOne, electiveSubjectTwo;
    private JButton addElectiveSubject,viewResult, back;
    private GridBagConstraints layout, studentLayout,electiveLayout, buttonLayout;
    ModuleTable moduleTable;
    UserTable userTable;
    loginPanel login;
    StudentLoggedInMainPanel studentLoggedInMainPanel;
    JPanel studentPanelCoursesStudentElectiveSubjects;


    public StudentLoggedInCoursePanel() {
        setBorder(BorderFactory.createTitledBorder("Register Instructor To Module"));
        String[] TableNames = {"Id","Course Name","Module Name","level","moduleType","semester","Instructor Name"};

        studentName = new JTextField(20);
        studentName.setPreferredSize(new Dimension(40,30));
        studentName.setEnabled(false);
        studentName.setDisabledTextColor(new Color(0,0,0));
        courseName = new JTextField(20);
        courseName.setPreferredSize(new Dimension(40,30));
        courseName.setEnabled(false);
        courseName.setDisabledTextColor(new Color(0,0,0));
        level = new JTextField(20);
        level.setPreferredSize(new Dimension(40,30));
        level.setEnabled(false);
        level.setDisabledTextColor(new Color(0,0,0));

        String sem[] = {"Select Semester","1","2"};
        semester = new JComboBox(sem);
        electiveSubjectOne = new JComboBox();
        electiveSubjectTwo = new JComboBox();

        DefaultTableModel courseAdministratorInstructorModel = new DefaultTableModel();
        courseAdministratorInstructorModel.setColumnIdentifiers(TableNames);

        addElectiveSubject = new JButton("Add Elective");
        viewResult = new JButton("View Result");
        back = new JButton("Back");

        StudentLoggedInCourseTable = new JTable(courseAdministratorInstructorModel);

        moduleTable = new ModuleTable();
        userTable = new UserTable();
        login = new loginPanel();
        studentLoggedInMainPanel = new StudentLoggedInMainPanel();

        electiveSubjects();
        refreshElectiveSubjects();
    }

    private void electiveSubjects() {
        try {
            String sem = semester.getSelectedItem().toString();
            ResultSet resultSet = moduleTable.getModuleNameAtStudentPanel(sem);
            electiveSubjectOne.removeAllItems();
            electiveSubjectTwo.removeAllItems();
            electiveSubjectOne.addItem("Select Elective Subject One");
            electiveSubjectTwo.addItem("Select Elective Subject Two");
            while (resultSet.next()) {
                electiveSubjectOne.addItem(resultSet.getString("moduleName"));
                electiveSubjectTwo.addItem(resultSet.getString("moduleName"));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshElectiveSubjects(){
        semester.addActionListener(e -> {
            electiveSubjects();
        });
    }

    public void loggedInStudentData(String Email){
        try {
                ResultSet resultSet = userTable.getParticularStudentData(Email);
                while (resultSet.next()) {
                    studentName.setText(resultSet.getString("firstName") + " " + resultSet.getString("lastName"));
                    courseName.setText(resultSet.getString("courseType"));
                    level.setText(resultSet.getString("level"));
                }
            String lvl = level.getText().trim();
            if (!lvl.equals("6")){
                studentPanelCoursesStudentElectiveSubjects.setVisible(false);
                addElectiveSubject.setVisible(false);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel courseAdministratorCourseTablePanel() {

        JScrollPane scrollPane =new JScrollPane(StudentLoggedInCourseTable);
        StudentLoggedInCourseTable.setDefaultEditor(Object.class, null);
        StudentLoggedInCourseTable.setSelectionBackground(Color.BLACK);
        StudentLoggedInCourseTable.setSelectionForeground(Color.WHITE);
        JTableHeader header = StudentLoggedInCourseTable.getTableHeader();
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
        studentPanelCoursesStudentDetails.setBorder(BorderFactory.createTitledBorder("Student Details"));

        studentLayout = new GridBagConstraints();
        studentLayout.fill = GridBagConstraints.HORIZONTAL;
        studentLayout.insets = new Insets(5,5,5,5);

        studentLayout.gridx = 0;
        studentLayout.gridy = 0;
        studentPanelCoursesStudentDetails.add(new JLabel("Student Name"),studentLayout);

        studentLayout.gridx = 1;
        studentLayout.gridy = 0;
        studentLayout.weightx = 1;
        studentLayout.gridwidth = 3;
        studentPanelCoursesStudentDetails.add(studentName,studentLayout);

        studentLayout.gridx=0;
        studentLayout.gridy=1;
        studentLayout.gridwidth = 1;
        studentPanelCoursesStudentDetails.add(new JLabel("Enrolled Course"),studentLayout);

        studentLayout.gridx=1;
        studentLayout.gridy=1;
        studentLayout.gridwidth = 3;
        studentPanelCoursesStudentDetails.add(courseName,studentLayout);

        studentLayout.gridx=0;
        studentLayout.gridy=2;
        studentLayout.gridwidth = 1;
        studentPanelCoursesStudentDetails.add(new JLabel("Level"),studentLayout);

        studentLayout.gridx=1;
        studentLayout.gridy=2;
        studentLayout.gridwidth = 3;
        studentPanelCoursesStudentDetails.add(level,studentLayout);

        return studentPanelCoursesStudentDetails;
    }

    private JPanel StudentPanelLevelSixOptionalCourses() {
        studentPanelCoursesStudentElectiveSubjects = new JPanel();
        studentPanelCoursesStudentElectiveSubjects.setLayout(new GridBagLayout());
        studentPanelCoursesStudentElectiveSubjects.setBackground(Color.decode("#D6D9DF"));
        studentPanelCoursesStudentElectiveSubjects.setBorder(BorderFactory.createTitledBorder("Student Elective Subjects Panel"));

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
        studentPanelCoursesStudentElectiveSubjects.add(new JLabel("Elective Subject One"),electiveLayout);

        electiveLayout.gridx=1;
        electiveLayout.gridy=1;
        electiveLayout.gridwidth = 3;
        studentPanelCoursesStudentElectiveSubjects.add(electiveSubjectOne,electiveLayout);

        electiveLayout.gridx=0;
        electiveLayout.gridy=2;
        electiveLayout.gridwidth = 1;
        studentPanelCoursesStudentElectiveSubjects.add(new JLabel("Elective Subject Two"),electiveLayout);

        electiveLayout.gridx=1;
        electiveLayout.gridy=2;
        electiveLayout.gridwidth = 3;
        studentPanelCoursesStudentElectiveSubjects.add(electiveSubjectTwo,electiveLayout);

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
        studentPanelCoursesButtons.add(back, buttonLayout);

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

    public DefaultTableModel getStudentLoggedInCourseModel(){ return (DefaultTableModel) getTable().getModel();}
    public  JTable getTable(){
        return StudentLoggedInCourseTable;
    }
    public JTextField getStudentName() {
        return studentName;
    }
    public JTextField getCourseName() {
        return courseName;
    }
    public JTextField getLevel() {
        return level;
    }
    public JButton getBack() {
        return back;
    }
}

