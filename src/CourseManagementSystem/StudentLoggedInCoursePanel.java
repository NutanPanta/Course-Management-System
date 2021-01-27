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
    private JComboBox moduleName, instructorName;
    private JTextField courseName,level,moduleType,semester;
    private JButton back;
    private GridBagConstraints layout, formLayout, buttonLayout;
    ModuleTable moduleTable;
    UserTable userTable;


    public StudentLoggedInCoursePanel() {
        setBorder(BorderFactory.createTitledBorder("Register Instructor To Module"));
        String[] TableNames = {"Id","Course Name","Module Name","level","moduleType","semester","Instructor Name"};

        moduleName = new JComboBox();
        instructorName =new JComboBox();
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
        semester = new JTextField(20);
        semester.setPreferredSize(new Dimension(40,30));
        semester.setEnabled(false);
        semester.setDisabledTextColor(new Color(0,0,0));

        moduleName.setBounds(50, 50,90,20);

        instructorName.setBounds(50, 50,90,20);

        DefaultTableModel courseAdministratorInstructorModel = new DefaultTableModel();
        courseAdministratorInstructorModel.setColumnIdentifiers(TableNames);

        back = new JButton("Back");

        StudentLoggedInCourseTable = new JTable(courseAdministratorInstructorModel);

        moduleTable = new ModuleTable();
        userTable = new UserTable();
        instructorName();
        refreshModuleName();

    }

    private void refreshModuleName(){
        int a = 1,size = 0;
        try {
            ResultSet resultSet = moduleTable.getModuleName();
            moduleName.removeAllItems();
            moduleName.addItem("Select Module Names");
            while (resultSet.next()) {
                size = moduleName.getItemCount();
                a++;
                if (size < a){
                    moduleName.addItem(resultSet.getString("moduleName"));
                }

            }
            moduleDetailsAtTextField();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void instructorName(){
        instructorName.removeAllItems();
        try {
            ResultSet resultSet = userTable.getInstructorNames();
            instructorName.addItem("Select Instructor Names");
            while (resultSet.next()) {

                instructorName.addItem(resultSet.getString("firstName") + " " +  resultSet.getString( "lastName"));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void moduleDetailsAtTextField(){
        courseName.setText("");
        level.setText("");
        moduleType.setText("");
        semester.setText("");
        try {
            String name = moduleName.getSelectedItem().toString();

            if (name == "Select Module Names"){
                courseName.setText("");
                level.setText("");
                moduleType.setText("");
                semester.setText("");
            } else {
                ResultSet resultSet1 = moduleTable.getModulesDataIntoInstructorPanel(name);
                while (resultSet1.next()) {
                    courseName.setText(resultSet1.getString("courseName"));
                    level.setText(resultSet1.getString("level"));
                    moduleType.setText(resultSet1.getString("moduleType"));
                    semester.setText(resultSet1.getString("semester"));
                }
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

    private JPanel courseAdministratorInstructorFormPanel(){
        JPanel courseAdministratorInstructorForm = new JPanel();
        courseAdministratorInstructorForm.setLayout(new GridBagLayout());
        courseAdministratorInstructorForm.setBackground(Color.decode("#D6D9DF"));

        formLayout = new GridBagConstraints();
        formLayout.fill = GridBagConstraints.HORIZONTAL;
        formLayout.insets = new Insets(5,5,5,5);

        formLayout.gridx = 0;
        formLayout.gridy = 0;
        formLayout.gridwidth = 1;
        courseAdministratorInstructorForm.add(new JLabel("Module Name"), formLayout);

        formLayout.weightx = 1;
        formLayout.gridwidth = 3;
        formLayout.gridx = 1;
        formLayout.gridy = 0;
        courseAdministratorInstructorForm.add(moduleName,formLayout);

        formLayout.gridx = 0;
        formLayout.gridy = 1;
        formLayout.gridwidth = 1;
        courseAdministratorInstructorForm.add(new JLabel("Instructor Name"), formLayout);

        formLayout.weightx = 1;
        formLayout.gridwidth = 3;
        formLayout.gridx = 1;
        formLayout.gridy = 1;
        courseAdministratorInstructorForm.add(instructorName,formLayout);

        formLayout.gridx = 0;
        formLayout.gridy = 2;
        formLayout.gridwidth = 1;
        courseAdministratorInstructorForm.add(new JLabel("Course Name"), formLayout);

        formLayout.gridx=1;
        formLayout.gridy=2;
        formLayout.gridwidth = 1;
        courseAdministratorInstructorForm.add(courseName,formLayout);

        formLayout.gridx = 0;
        formLayout.gridy = 3;
        formLayout.gridwidth = 1;
        courseAdministratorInstructorForm.add(new JLabel("Level"), formLayout);

        formLayout.gridx=1;
        formLayout.gridy=3;
        formLayout.gridwidth = 1;
        courseAdministratorInstructorForm.add(level,formLayout);

        formLayout.gridx = 0;
        formLayout.gridy = 4;
        formLayout.gridwidth = 1;
        courseAdministratorInstructorForm.add(new JLabel("Module Type"), formLayout);

        formLayout.gridx=1;
        formLayout.gridy=4;
        formLayout.gridwidth = 1;
        courseAdministratorInstructorForm.add(moduleType,formLayout);

        formLayout.gridx = 0;
        formLayout.gridy = 5;
        formLayout.gridwidth = 1;
        courseAdministratorInstructorForm.add(new JLabel("Semester"), formLayout);

        formLayout.gridx=1;
        formLayout.gridy=5;
        formLayout.gridwidth = 1;
        courseAdministratorInstructorForm.add(semester,formLayout);



        return courseAdministratorInstructorForm;
    }

    private JPanel courseAdministratorInstructorButtonPanel() {
        JPanel courseAdministratorInstructorButton = new JPanel();
        courseAdministratorInstructorButton.setLayout(new GridBagLayout());
        courseAdministratorInstructorButton.setBackground(Color.decode("#D6D9DF"));
        buttonLayout = new GridBagConstraints();
        buttonLayout.fill = GridBagConstraints.HORIZONTAL;
        buttonLayout.insets = new Insets(13,10,5,10);
        buttonLayout.ipadx = 20;
        buttonLayout.ipady = 20;

        buttonLayout.gridx = 0;
        buttonLayout.gridy = 1;
        buttonLayout.gridwidth = 4;
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
        add(courseAdministratorInstructorFormPanel(),layout);

        layout.gridx = 1;
        layout.gridy = 1;
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
    public JComboBox getModuleName() { return moduleName; }
    public JComboBox getInstructorName() { return instructorName; }
    public JTextField getCourseName() {
        return courseName;
    }
    public JTextField getLevel() {
        return level;
    }
    public JTextField getModuleType() {
        return moduleType;
    }
    public JTextField getSemester() {
        return semester;
    }
    public JButton getBack() {
        return back;
    }
    public void getRefreshModuleName(){
        refreshModuleName();
    }
    public void getRefreshInstructorName(){
        instructorName();
    }
}

