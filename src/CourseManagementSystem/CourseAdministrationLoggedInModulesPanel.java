package CourseManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseAdministrationLoggedInModulesPanel extends JPanel implements AppLayout, ActionListener {
    private DefaultTableModel courseAdministratorModuleModel;
    private JTable courseAdministratorModuleTable;
    private JButton addModule, deleteModule, updateModule, back;
    private JTextField moduleName;
    private JComboBox course, level,semester;
    private JCheckBox isElective;
    private JLabel elective;
    private GridBagConstraints layout, formLayout, buttonLayout;
    courseTable coursetable;

    public CourseAdministrationLoggedInModulesPanel() {
        setBorder(BorderFactory.createTitledBorder("Register Module"));
        moduleName = new JTextField(20);
        moduleName.setPreferredSize(new Dimension(40,30));
        String[] TableNames = {"Module Id","Module Name","Course Name","level","Course Type","Semester"};
        String ll[]={"Select Level","4","5","6"};
        String st[] = {"Select Semester","1","2"};
        course = new JComboBox();
        semester =new JComboBox(st);
        level =new JComboBox(ll);
        level.setBounds(50, 50,90,20);
        course.setBounds(50, 50,90,20);
        semester.setBounds(50, 50,90,20);
        DefaultTableModel courseAdministratorCourseModel = new DefaultTableModel();
        courseAdministratorCourseModel.setColumnIdentifiers(TableNames);

        isElective = new JCheckBox();

        back = new JButton("Back");
        addModule = new JButton("Add Module");
        deleteModule = new JButton("Delete Module");
        updateModule = new JButton("Update Module");

        courseAdministratorModuleTable = new JTable(courseAdministratorCourseModel);

        coursetable = new courseTable();
        courseName();
        isElective.setVisible(false);


    }

    private void courseName(){
        try {
            ResultSet resultSet = coursetable.getCourseDetails();
            course.removeAllItems();
            course.addItem("Select Course Names");
            while (resultSet.next()) {
                course.addItem(resultSet.getString("courseName"));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel courseAdministratorCourseTablePanel() {

        JScrollPane scrollPane =new JScrollPane(courseAdministratorModuleTable);
        courseAdministratorModuleTable.setDefaultEditor(Object.class, null);
        courseAdministratorModuleTable.setSelectionBackground(Color.BLACK);
        courseAdministratorModuleTable.setSelectionForeground(Color.WHITE);
        JTableHeader header = courseAdministratorModuleTable.getTableHeader();
        header.setBackground(Color.decode("#D6D9DF"));

        JPanel courseAdministratorCoursePanel = new JPanel();
        courseAdministratorCoursePanel.setBackground(Color.decode("#D6D9DF"));
        courseAdministratorCoursePanel.add(scrollPane);

        return courseAdministratorCoursePanel;
    }

    private JPanel courseAdministratorModuleFormPanel(){
        JPanel courseAdministratorModuleForm = new JPanel();
        courseAdministratorModuleForm.setLayout(new GridBagLayout());
        courseAdministratorModuleForm.setBackground(Color.decode("#D6D9DF"));

        formLayout = new GridBagConstraints();
        formLayout.fill = GridBagConstraints.HORIZONTAL;
        formLayout.insets = new Insets(5,5,5,5);

        formLayout.gridx = 0;
        formLayout.gridy = 0;
        formLayout.gridwidth = 1;
        courseAdministratorModuleForm.add(new JLabel("Course"), formLayout);

        formLayout.weightx = 1;
        formLayout.gridwidth = 3;
        formLayout.gridx = 1;
        formLayout.gridy = 0;
        courseAdministratorModuleForm.add(course,formLayout);

        formLayout.gridx=0;
        formLayout.gridy=1;
        formLayout.gridwidth = 1;
        courseAdministratorModuleForm.add(new JLabel("Module Name"),formLayout);

        formLayout.gridx=1;
        formLayout.gridy=1;
        formLayout.gridwidth = 3;
        courseAdministratorModuleForm.add(moduleName,formLayout);

        formLayout.gridx = 0;
        formLayout.gridy = 2;
        formLayout.gridwidth = 1;
        courseAdministratorModuleForm.add(new JLabel("Level"), formLayout);

        formLayout.gridx = 1;
        formLayout.gridy = 2;
        formLayout.gridwidth = 4;
        courseAdministratorModuleForm.add(level, formLayout);

        formLayout.gridx = 0;
        formLayout.gridy = 3;
        formLayout.gridwidth = 1;
        courseAdministratorModuleForm.add(new JLabel("Semester"), formLayout);

        formLayout.gridx = 1;
        formLayout.gridy = 3;
        formLayout.gridwidth = 4;
        courseAdministratorModuleForm.add(semester, formLayout);

        formLayout.gridx = 0;
        formLayout.gridy = 4;
        formLayout.gridwidth = 1;
        elective = new JLabel("Elective");
        courseAdministratorModuleForm.add(elective, formLayout);

        formLayout.gridx = 1;
        formLayout.gridy = 4;
        courseAdministratorModuleForm.add(isElective, formLayout);



        elective.setVisible(false);

        return courseAdministratorModuleForm;
    }

    private JPanel courseAdministratorModuleButtonPanel() {
        JPanel courseAdministratorModuleButton = new JPanel();
        courseAdministratorModuleButton.setLayout(new GridBagLayout());
        courseAdministratorModuleButton.setBackground(Color.decode("#D6D9DF"));
        buttonLayout = new GridBagConstraints();
        buttonLayout.fill = GridBagConstraints.HORIZONTAL;
        buttonLayout.insets = new Insets(20,10,5,10);
        buttonLayout.ipadx = 20;
        buttonLayout.ipady = 20;

        buttonLayout.gridx = 0;
        buttonLayout.gridy = 0;
        courseAdministratorModuleButton.add(addModule, buttonLayout);

        buttonLayout.gridx = 1;
        buttonLayout.gridy = 0;
        courseAdministratorModuleButton.add(deleteModule, buttonLayout);

        buttonLayout.gridx = 0;
        buttonLayout.gridy = 1;
        buttonLayout.gridwidth = 4;
        courseAdministratorModuleButton.add(updateModule, buttonLayout);

        buttonLayout.gridx = 0;
        buttonLayout.gridy = 2;
        buttonLayout.gridwidth = 4;
        courseAdministratorModuleButton.add(back, buttonLayout);

        return courseAdministratorModuleButton;
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
        add(courseAdministratorModuleFormPanel(),layout);

        layout.gridx = 1;
        layout.gridy = 1;
        add(courseAdministratorModuleButtonPanel(),layout);

        layout.weighty = 0.5;
        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridheight=3;
        add(courseAdministratorCourseTablePanel(),layout);


        level.addActionListener(this);

        return this;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String item = level.getSelectedItem().toString();

        if (item == "6") {
            isElective.setVisible(true);
            elective.setVisible(true);
        }  else {
            isElective.setVisible(false);
            elective.setVisible(false);
            isElective.setSelected(false);
        }

    }

    public DefaultTableModel getCourseAdministratorModuleModel(){ return (DefaultTableModel) getTable().getModel();}
    public  JTable getTable(){
        return courseAdministratorModuleTable;
    }
    public JComboBox getCourse() { return course; }
    public JComboBox getLevel() { return level; }
    public JComboBox getSemester() { return semester; }
    public JCheckBox getIsElective() { return isElective; }
    public JTextField getModuleName() { return moduleName; }
    public JButton getAddModule() {
        return addModule;
    }
    public JButton getDeleteModule() {
        return deleteModule;
    }
    public JButton getUpdateModule() {
        return updateModule;
    }
    public JButton getBack() {
        return back;
    }
    public void getCourseName(){
        courseName();
    }
}

