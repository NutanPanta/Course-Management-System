package CourseManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class StudentLoggedInCoursePanel extends JPanel implements AppLayout {
    private DefaultTableModel StudentLoggedInCourseModel;
    private JTable StudentLoggedInCourseTable;
    private JTextField studentName, courseName, level, moduleType;
    private JComboBox semester, electiveSubjectOne, electiveSubjectTwo;
    private JButton addElectiveSubject, back;
    private GridBagConstraints layout, studentLayout, buttonLayout,electiveLayout;
    ModuleTable moduleTable;
    UserTable userTable;


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
        moduleType = new JTextField(20);
        moduleType.setPreferredSize(new Dimension(40,30));
        moduleType.setEnabled(false);
        moduleType.setDisabledTextColor(new Color(0,0,0));

        String sem[] = {"Select Semester","1","2"};
        semester = new JComboBox(sem);
        electiveSubjectOne = new JComboBox();
        electiveSubjectOne.addItem("Select Elective Subject One");
        electiveSubjectTwo = new JComboBox();
        electiveSubjectTwo.addItem("Select Elective Subject Two");

        DefaultTableModel courseAdministratorInstructorModel = new DefaultTableModel();
        courseAdministratorInstructorModel.setColumnIdentifiers(TableNames);

        addElectiveSubject = new JButton("Add Elective");
        back = new JButton("Back");

        StudentLoggedInCourseTable = new JTable(courseAdministratorInstructorModel);

        moduleTable = new ModuleTable();
        userTable = new UserTable();

    }

//    private void moduleDetailsAtTextField(){
//        courseName.setText("");
//        level.setText("");
//        moduleType.setText("");
//        semester.setText("");
//        try {
//            String name = moduleName.getSelectedItem().toString();
//
//            if (name == "Select Module Names"){
//                courseName.setText("");
//                level.setText("");
//                moduleType.setText("");
//                semester.setText("");
//            } else {
//                ResultSet resultSet1 = moduleTable.getModulesDataIntoInstructorPanel(name);
//                while (resultSet1.next()) {
//                    courseName.setText(resultSet1.getString("courseName"));
//                    level.setText(resultSet1.getString("level"));
//                    moduleType.setText(resultSet1.getString("moduleType"));
//                    semester.setText(resultSet1.getString("semester"));
//                }
//            }
//
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }

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
        studentLayout.gridwidth = 1;
        studentPanelCoursesStudentDetails.add(new JLabel("Student Name"), studentLayout);

        studentLayout.weightx = 1;
        studentLayout.gridwidth = 3;
        studentLayout.gridx = 1;
        studentLayout.gridy = 0;
        studentPanelCoursesStudentDetails.add(studentName, studentLayout);

        studentLayout.gridx = 0;
        studentLayout.gridy = 1;
        studentLayout.gridwidth = 1;
        studentPanelCoursesStudentDetails.add(new JLabel("Course Name"), studentLayout);

        studentLayout.gridx=1;
        studentLayout.gridy=1;
        studentLayout.gridwidth = 1;
        studentPanelCoursesStudentDetails.add(courseName, studentLayout);

        studentLayout.gridx = 0;
        studentLayout.gridy = 2;
        studentLayout.gridwidth = 1;
        studentPanelCoursesStudentDetails.add(new JLabel("Level"), studentLayout);

        studentLayout.gridx=1;
        studentLayout.gridy=2;
        studentLayout.gridwidth = 1;
        studentPanelCoursesStudentDetails.add(level, studentLayout);

        studentLayout.gridx = 0;
        studentLayout.gridy = 3;
        studentLayout.gridwidth = 1;
        studentPanelCoursesStudentDetails.add(new JLabel("Module Type"), studentLayout);

        studentLayout.gridx=1;
        studentLayout.gridy=3;
        studentLayout.gridwidth = 1;
        studentPanelCoursesStudentDetails.add(moduleType, studentLayout);

        return studentPanelCoursesStudentDetails;
    }

    private JPanel StudentPanelLevelSixOptionalCourses() {
        JPanel studentPanelCoursesStudentElectiveSubjects = new JPanel();
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

    private JPanel courseAdministratorInstructorButtonPanel() {
        JPanel courseAdministratorInstructorButton = new JPanel();
        courseAdministratorInstructorButton.setLayout(new GridBagLayout());
        courseAdministratorInstructorButton.setBackground(Color.decode("#D6D9DF"));
        buttonLayout = new GridBagConstraints();
        buttonLayout.fill = GridBagConstraints.HORIZONTAL;
        buttonLayout.insets = new Insets(5,10,5,10);
        buttonLayout.ipadx = 60;
        buttonLayout.ipady = 20;

        buttonLayout.gridx = 0;
        buttonLayout.gridy = 0;
        courseAdministratorInstructorButton.add(addElectiveSubject, buttonLayout);

        buttonLayout.gridx = 1;
        buttonLayout.gridy = 0;
        courseAdministratorInstructorButton.add(back, buttonLayout);

        return courseAdministratorInstructorButton;
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

        layout.gridx = 1;
        layout.gridy = 2;
        add(courseAdministratorInstructorButtonPanel(),layout);

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
    public JTextField getCourseName() {
        return courseName;
    }
    public JTextField getLevel() {
        return level;
    }
    public JTextField getModuleType() {
        return moduleType;
    }
    public JButton getBack() {
        return back;
    }
}

