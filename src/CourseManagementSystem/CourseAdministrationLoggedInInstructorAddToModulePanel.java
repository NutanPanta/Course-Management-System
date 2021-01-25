package CourseManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseAdministrationLoggedInInstructorAddToModulePanel extends JPanel implements AppLayout {
    private DefaultTableModel courseAdministratorInstructorModel;
    private JTable courseAdministratorInstructorTable;
    private JComboBox moduleName, instructorName;
    private JButton addInstructor, deleteInstructor, updateInstructor, back;
    private GridBagConstraints layout, formLayout, buttonLayout;
    ModuleTable moduleTable;
    UserTable userTable;

    public CourseAdministrationLoggedInInstructorAddToModulePanel() {
        setBorder(BorderFactory.createTitledBorder("Register Instructor To Module"));
        String[] TableNames = {"Id","Course Name","Module Name","level","moduleType","semester","Instructor Name"};
        moduleName = new JComboBox();
        instructorName =new JComboBox();
        moduleName.setBounds(50, 50,90,20);
        instructorName.setBounds(50, 50,90,20);
        DefaultTableModel courseAdministratorInstructorModel = new DefaultTableModel();
        courseAdministratorInstructorModel.setColumnIdentifiers(TableNames);

        back = new JButton("Back");
        addInstructor = new JButton("Add Instructor");
        deleteInstructor = new JButton("Delete Instructor");
        updateInstructor = new JButton("Update Instructor");

        courseAdministratorInstructorTable = new JTable(courseAdministratorInstructorModel);

        moduleTable = new ModuleTable();
        userTable = new UserTable();
        refreshModuleName();
        instructorName();


    }

    private void refreshModuleName(){
        moduleName.removeAllItems();
        try {
            ResultSet resultSet = moduleTable.getModuleName();
            moduleName.addItem("Select Module Names");
            while (resultSet.next()) {
                moduleName.addItem(resultSet.getString("moduleName"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    private JPanel courseAdministratorCourseTablePanel() {

        JScrollPane scrollPane =new JScrollPane(courseAdministratorInstructorTable);
        courseAdministratorInstructorTable.setDefaultEditor(Object.class, null);
        courseAdministratorInstructorTable.setSelectionBackground(Color.BLACK);
        courseAdministratorInstructorTable.setSelectionForeground(Color.WHITE);
        JTableHeader header = courseAdministratorInstructorTable.getTableHeader();
        header.setBackground(Color.decode("#D6D9DF"));

        JPanel courseAdministratorCoursePanel = new JPanel();
        courseAdministratorCoursePanel.add(scrollPane);

        return courseAdministratorCoursePanel;
    }

    private JPanel courseAdministratorCourseFormPanel(){
        JPanel courseAdministratorCourseForm = new JPanel();
        courseAdministratorCourseForm.setLayout(new GridBagLayout());

        courseAdministratorCourseForm.setLayout(new GridBagLayout());
        formLayout = new GridBagConstraints();
        formLayout.fill = GridBagConstraints.HORIZONTAL;
        formLayout.insets = new Insets(5,5,5,5);

        formLayout.gridx = 0;
        formLayout.gridy = 0;
        formLayout.gridwidth = 1;
        courseAdministratorCourseForm.add(new JLabel("Module Name"), formLayout);

        formLayout.weightx = 1;
        formLayout.gridwidth = 3;
        formLayout.gridx = 1;
        formLayout.gridy = 0;
        courseAdministratorCourseForm.add(moduleName,formLayout);

        formLayout.gridx = 0;
        formLayout.gridy = 1;
        formLayout.gridwidth = 1;
        courseAdministratorCourseForm.add(new JLabel("Instructor Name"), formLayout);

        formLayout.weightx = 1;
        formLayout.gridwidth = 3;
        formLayout.gridx = 1;
        formLayout.gridy = 1;
        courseAdministratorCourseForm.add(instructorName,formLayout);


        return courseAdministratorCourseForm;
    }

    private JPanel courseAdministratorCourseButtonPanel() {
        JPanel courseAdministratorCourseButton = new JPanel();
        courseAdministratorCourseButton.setLayout(new GridBagLayout());
        buttonLayout = new GridBagConstraints();
        buttonLayout.fill = GridBagConstraints.HORIZONTAL;
        buttonLayout.insets = new Insets(20,10,5,10);
        buttonLayout.ipadx = 20;
        buttonLayout.ipady = 20;

        buttonLayout.gridx = 0;
        buttonLayout.gridy = 0;
        courseAdministratorCourseButton.add(addInstructor, buttonLayout);

        buttonLayout.gridx = 1;
        buttonLayout.gridy = 0;
        courseAdministratorCourseButton.add(deleteInstructor, buttonLayout);

        buttonLayout.gridx = 0;
        buttonLayout.gridy = 1;
        buttonLayout.gridwidth = 4;
        courseAdministratorCourseButton.add(updateInstructor, buttonLayout);

        buttonLayout.gridx = 0;
        buttonLayout.gridy = 2;
        buttonLayout.gridwidth = 4;
        courseAdministratorCourseButton.add(back, buttonLayout);

        return courseAdministratorCourseButton;
    }

    @Override
    public JPanel panelUI() {
        setLayout(new GridBagLayout());
        layout = new GridBagConstraints();
        layout.fill = GridBagConstraints.BOTH;
        layout.insets = new Insets(5,0,5,0);

        layout.gridx=3;
        layout.gridy=3;

        layout.gridx = 1;
        layout.gridy = 0;
        add(courseAdministratorCourseFormPanel(),layout);

        layout.gridx = 1;
        layout.gridy = 1;
        add(courseAdministratorCourseButtonPanel(),layout);

        layout.weighty = 0.5;
        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridheight=3;
        add(courseAdministratorCourseTablePanel(),layout);

        return this;
    }


    public DefaultTableModel getCourseAdministratorInstructorModel(){ return (DefaultTableModel) getTable().getModel();}
    public  JTable getTable(){
        return courseAdministratorInstructorTable;
    }
    public JComboBox getModuleName() { return moduleName; }
    public JComboBox getInstructorName() { return instructorName; }
    public JButton getAddInstructor() {
        return addInstructor;
    }
    public JButton getDeleteInstructor() {
        return deleteInstructor;
    }
    public JButton getUpdateInstructor() {
        return updateInstructor;
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

