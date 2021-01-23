package CourseManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class CourseAdministrationLoggedInModulesPanel extends JPanel implements AppLayout {
    private DefaultTableModel courseAdministratorCourseModel;
    private JTable courseAdministratorModuleTable;
    private JTextField moduleName;
    private JButton addCourse,cancelCourse,openCourse,deleteCourse,updateCourse, back;
    private GridBagConstraints layout, formLayout, buttonLayout;

    public CourseAdministrationLoggedInModulesPanel() {
        DefaultTableModel courseAdministratorCourseModel = new DefaultTableModel();
        String[] courseNames = {"Course Id","Course Name","Course Status"};
        courseAdministratorCourseModel.setColumnIdentifiers(courseNames);
        moduleName = new JTextField(20);
        moduleName.setPreferredSize(new Dimension(40,30));
        addCourse = new JButton("Add Course");
        cancelCourse = new JButton("Cancel Course");
        openCourse = new JButton("Open Course");
        deleteCourse = new JButton("Delete Course");
        updateCourse = new JButton("Update Course");
        back = new JButton("Back");

        courseAdministratorModuleTable = new JTable(courseAdministratorCourseModel);


    }

    private JPanel courseAdministratorCourseTablePanel() {

        courseAdministratorModuleTable.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane =new JScrollPane(courseAdministratorModuleTable);
        courseAdministratorModuleTable.setSelectionBackground(Color.BLACK);
        courseAdministratorModuleTable.setSelectionForeground(Color.WHITE);
        JTableHeader header = courseAdministratorModuleTable.getTableHeader();
        header.setBackground(Color.decode("#D6D9DF"));

        JPanel courseAdministratorCoursePanel = new JPanel();
        courseAdministratorCoursePanel.setBackground(Color.decode("#D6D9DF"));
        courseAdministratorCoursePanel.add(scrollPane);

        return courseAdministratorCoursePanel;
    }

    private JPanel courseAdministratorCourseFormPanel(){
        JPanel courseAdministratorCourseForm = new JPanel();
        courseAdministratorCourseForm.setBackground(Color.decode("#D6D9DF"));
        courseAdministratorCourseForm.setLayout(new GridBagLayout());
        formLayout = new GridBagConstraints();
        formLayout.insets = new Insets(80,0,0,0);

        formLayout.gridx = 0;
        formLayout.gridy = 0;
        courseAdministratorCourseForm.add(new JLabel("Course Name   "),formLayout);

        formLayout.gridx = 1;
        formLayout.gridy = 0;
        courseAdministratorCourseForm.add(moduleName,formLayout);

        return courseAdministratorCourseForm;
    }

    private JPanel courseAdministratorCourseButtonPanel() {
        JPanel courseAdministratorCourseButton = new JPanel();
        courseAdministratorCourseButton.setBackground(Color.decode("#D6D9DF"));
        courseAdministratorCourseButton.setLayout(new GridBagLayout());
        buttonLayout = new GridBagConstraints();
        buttonLayout.fill = GridBagConstraints.HORIZONTAL;
        buttonLayout.insets = new Insets(20,10,5,10);
        buttonLayout.ipadx = 20;
        buttonLayout.ipady = 20;

        buttonLayout.gridx = 0;
        buttonLayout.gridy = 0;
        courseAdministratorCourseButton.add(addCourse, buttonLayout);

        buttonLayout.gridx = 1;
        buttonLayout.gridy = 0;
        courseAdministratorCourseButton.add(cancelCourse, buttonLayout);

        buttonLayout.gridx = 0;
        buttonLayout.gridy = 1;
        courseAdministratorCourseButton.add(openCourse, buttonLayout);

        buttonLayout.gridx = 1;
        buttonLayout.gridy = 1;
        courseAdministratorCourseButton.add(deleteCourse, buttonLayout);

        buttonLayout.gridx = 0;
        buttonLayout.gridy = 2;
        buttonLayout.gridwidth = 4;
        courseAdministratorCourseButton.add(updateCourse, buttonLayout);

        buttonLayout.gridx = 0;
        buttonLayout.gridy = 3;
        buttonLayout.gridwidth = 4;
        courseAdministratorCourseButton.add(back, buttonLayout);

        return courseAdministratorCourseButton;
    }

    @Override
    public JPanel panelUI() {
        this.setBackground(Color.decode("#D6D9DF"));
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


    public DefaultTableModel getCourseModel(){ return (DefaultTableModel) getTable().getModel();}
    public  JTable getTable(){
        return courseAdministratorModuleTable;
    }
    public JTextField getCourseName() { return moduleName; }
    public JButton getAddCourse() {
        return addCourse;
    }
    public JButton getCancelCourse() {
        return cancelCourse;
    }
    public JButton getOpenCourse() {
        return openCourse;
    }
    public JButton getDeleteCourse() {
        return deleteCourse;
    }
    public JButton getUpdateCourse() {
        return updateCourse;
    }
    public JButton getBack() {
        return back;
    }
}

