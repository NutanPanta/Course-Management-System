package CourseManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminInstructorPanel extends JPanel implements AppLayout {
    private DefaultTableModel courseAdministratorInstructorModel;
    private JTable courseAdministratorInstructorTable;
    private JComboBox<String> moduleName, instructorEmail;
    private JTextField courseName, level, moduleType, semester, instructorName;
    private JButton addInstructor, deleteInstructor, updateInstructor, back;
    private GridBagConstraints layout, formLayout, buttonLayout;
    ModuleTable moduleTable;
    UserTable userTable;

    public AdminInstructorPanel() {
        setBorder(BorderFactory.createTitledBorder("Register Instructor To Module"));
        String[] TableNames = { "Id", "Course Name", "Module Name", "level", "moduleType", "semester",
                "Instructor Name" };

        moduleName = new JComboBox<>();
        instructorEmail = new JComboBox<>();

        courseName = new JTextField(20);
        courseName.setPreferredSize(new Dimension(40, 30));
        courseName.setEnabled(false);
        courseName.setDisabledTextColor(new Color(0, 0, 0));

        level = new JTextField(20);
        level.setPreferredSize(new Dimension(40, 30));
        level.setEnabled(false);
        level.setDisabledTextColor(new Color(0, 0, 0));

        moduleType = new JTextField(20);
        moduleType.setPreferredSize(new Dimension(40, 30));
        moduleType.setEnabled(false);
        moduleType.setDisabledTextColor(new Color(0, 0, 0));

        semester = new JTextField(20);
        semester.setPreferredSize(new Dimension(40, 30));
        semester.setEnabled(false);
        semester.setDisabledTextColor(new Color(0, 0, 0));

        instructorName = new JTextField(20);
        instructorName.setPreferredSize(new Dimension(40, 30));
        instructorName.setEnabled(false);
        instructorName.setDisabledTextColor(new Color(0, 0, 0));

        moduleName.addItem("Select Module Names");
        moduleName.setBounds(50, 50, 90, 20);

        instructorEmail.setBounds(50, 50, 90, 20);
        instructorEmail.addItem("Select Instructor");

        courseAdministratorInstructorModel = new DefaultTableModel();
        courseAdministratorInstructorModel.setColumnIdentifiers(TableNames);

        back = new JButton("Back");
        addInstructor = new JButton("Add Instructor");
        deleteInstructor = new JButton("Delete Instructor");
        updateInstructor = new JButton("Update Instructor");

        courseAdministratorInstructorTable = new JTable(courseAdministratorInstructorModel);

        moduleTable = new ModuleTable();
        userTable = new UserTable();
        instructorEmails();
        refreshModuleName();
        moduleDetailsAtTextField();
        refreshModuleData();
        refreshInstructorName();
    }

    private void refreshModuleName() {
        try {
            ResultSet resultSet = moduleTable.getModule();
            moduleName.removeAllItems();
            while (resultSet.next()) {
                moduleName.addItem(resultSet.getString("moduleName"));
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(this, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void instructorEmails() {
        try {
            ResultSet resultSet = userTable.getInstructorEmails();
            instructorEmail.removeAllItems();
            instructorEmail.addItem("Select Instructor");
            while (resultSet.next()) {
                instructorEmail.addItem(resultSet.getString("email"));
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void instructorName() {
        if (instructorEmail.getItemCount() == 0) {
        } else {
            instructorName.setText("");
            try {
                String email = instructorEmail.getSelectedItem().toString();
                if (email == "Select Instructor") {
                    instructorName.setText("");
                } else {
                    ResultSet resultSet = userTable.getInstructorName(email);
                    while (resultSet.next()) {
                        instructorName
                                .setText(resultSet.getString("firstName") + " " + resultSet.getString("lastName"));
                    }
                }
            } catch (SQLException error) {
                JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);
                error.printStackTrace();
            }
        }

    }

    private void moduleDetailsAtTextField() {
        if (moduleName.getItemCount() == 0) {
            moduleName.addItem("Select Module Names");
        }
        courseName.setText("");
        level.setText("");
        moduleType.setText("");
        semester.setText("");
        try {
            String name = moduleName.getSelectedItem().toString();

            if (name == "Select Module Names") {
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

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshModuleData() {
        moduleName.addActionListener(error -> moduleDetailsAtTextField());
    }

    private void refreshInstructorName() {
        instructorEmail.addActionListener(error -> instructorName());
    }

    private JPanel instructorToModuleTablePanel() {

        JScrollPane scrollPane = new JScrollPane(courseAdministratorInstructorTable);
        courseAdministratorInstructorTable.setDefaultEditor(Object.class, null);
        courseAdministratorInstructorTable.setSelectionBackground(Color.BLACK);
        courseAdministratorInstructorTable.setSelectionForeground(Color.WHITE);
        JTableHeader header = courseAdministratorInstructorTable.getTableHeader();
        header.setBackground(Color.decode("#D6D9DE"));

        JPanel courseAdministratorCoursePanel = new JPanel();
        courseAdministratorCoursePanel.setBackground(Color.decode("#D6D9DE"));
        courseAdministratorCoursePanel.add(scrollPane);

        return courseAdministratorCoursePanel;
    }

    private JPanel instructorToModuleFormPanel() {
        JPanel courseAdministratorInstructorForm = new JPanel();
        courseAdministratorInstructorForm.setLayout(new GridBagLayout());
        courseAdministratorInstructorForm.setBackground(Color.decode("#D6D9DE"));

        formLayout = new GridBagConstraints();
        formLayout.fill = GridBagConstraints.HORIZONTAL;
        formLayout.insets = new Insets(5, 5, 5, 5);

        formLayout.gridx = 0;
        formLayout.gridy = 0;
        formLayout.gridwidth = 1;
        courseAdministratorInstructorForm.add(new JLabel("Module Name"), formLayout);

        formLayout.weightx = 1;
        formLayout.gridwidth = 3;
        formLayout.gridx = 1;
        formLayout.gridy = 0;
        courseAdministratorInstructorForm.add(moduleName, formLayout);

        formLayout.gridx = 0;
        formLayout.gridy = 1;
        formLayout.gridwidth = 1;
        courseAdministratorInstructorForm.add(new JLabel("Instructor Email"), formLayout);

        formLayout.gridx = 1;
        formLayout.gridy = 1;
        formLayout.gridwidth = 1;
        courseAdministratorInstructorForm.add(instructorEmail, formLayout);

        formLayout.gridx = 0;
        formLayout.gridy = 2;
        formLayout.gridwidth = 1;
        courseAdministratorInstructorForm.add(new JLabel("Course Name"), formLayout);

        formLayout.gridx = 1;
        formLayout.gridy = 2;
        formLayout.gridwidth = 1;
        courseAdministratorInstructorForm.add(courseName, formLayout);

        formLayout.gridx = 0;
        formLayout.gridy = 3;
        formLayout.gridwidth = 1;
        courseAdministratorInstructorForm.add(new JLabel("Level"), formLayout);

        formLayout.gridx = 1;
        formLayout.gridy = 3;
        formLayout.gridwidth = 1;
        courseAdministratorInstructorForm.add(level, formLayout);

        formLayout.gridx = 0;
        formLayout.gridy = 4;
        formLayout.gridwidth = 1;
        courseAdministratorInstructorForm.add(new JLabel("Module Type"), formLayout);

        formLayout.gridx = 1;
        formLayout.gridy = 4;
        formLayout.gridwidth = 1;
        courseAdministratorInstructorForm.add(moduleType, formLayout);

        formLayout.gridx = 0;
        formLayout.gridy = 5;
        formLayout.gridwidth = 1;
        courseAdministratorInstructorForm.add(new JLabel("Semester"), formLayout);

        formLayout.gridx = 1;
        formLayout.gridy = 5;
        formLayout.gridwidth = 1;
        courseAdministratorInstructorForm.add(semester, formLayout);

        formLayout.gridx = 0;
        formLayout.gridy = 6;
        formLayout.gridwidth = 1;
        courseAdministratorInstructorForm.add(new JLabel("Instructor Name"), formLayout);

        formLayout.gridx = 1;
        formLayout.gridy = 6;
        formLayout.gridwidth = 1;
        courseAdministratorInstructorForm.add(instructorName, formLayout);

        return courseAdministratorInstructorForm;
    }

    private JPanel instructorToModuleButtonPanel() {
        JPanel courseAdministratorInstructorButton = new JPanel();
        courseAdministratorInstructorButton.setLayout(new GridBagLayout());
        courseAdministratorInstructorButton.setBackground(Color.decode("#D6D9DE"));
        buttonLayout = new GridBagConstraints();
        buttonLayout.fill = GridBagConstraints.HORIZONTAL;
        buttonLayout.insets = new Insets(13, 10, 5, 10);
        buttonLayout.ipadx = 20;
        buttonLayout.ipady = 20;

        buttonLayout.gridx = 0;
        buttonLayout.gridy = 0;
        courseAdministratorInstructorButton.add(addInstructor, buttonLayout);

        buttonLayout.gridx = 1;
        buttonLayout.gridy = 0;
        courseAdministratorInstructorButton.add(deleteInstructor, buttonLayout);

        buttonLayout.gridx = 0;
        buttonLayout.gridy = 1;
        buttonLayout.gridwidth = 4;
        courseAdministratorInstructorButton.add(updateInstructor, buttonLayout);

        buttonLayout.gridx = 0;
        buttonLayout.gridy = 2;
        buttonLayout.gridwidth = 4;
        courseAdministratorInstructorButton.add(back, buttonLayout);

        return courseAdministratorInstructorButton;
    }

    @Override
    public JPanel panelUI() {
        setLayout(new GridBagLayout());
        layout = new GridBagConstraints();
        layout.fill = GridBagConstraints.BOTH;
        layout.insets = new Insets(5, 0, 5, 0);
        this.setBackground(Color.decode("#D6D9DE"));

        layout.gridx = 3;
        layout.gridy = 3;

        layout.gridx = 1;
        layout.gridy = 0;
        add(instructorToModuleFormPanel(), layout);

        layout.gridx = 1;
        layout.gridy = 1;
        add(instructorToModuleButtonPanel(), layout);

        layout.weighty = 0.5;
        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridheight = 3;
        add(instructorToModuleTablePanel(), layout);

        return this;
    }

    public DefaultTableModel getCourseAdministratorInstructorModel() {
        return (DefaultTableModel) getTable().getModel();
    }

    public JTable getTable() {
        return courseAdministratorInstructorTable;
    }

    public JButton getBack() {
        return back;
    }

    public JComboBox<String> getInstructorEmail() {
        return instructorEmail;
    }

    public JButton getAddInstructor() {
        return addInstructor;
    }

    public JComboBox<String> getModuleName() {
        return moduleName;
    }

    public JTextField getLevel() {
        return level;
    }

    public JTextField getCourseName() {
        return courseName;
    }

    public JTextField getSemester() {
        return semester;
    }

    public JTextField getModuleType() {
        return moduleType;
    }

    public JButton getDeleteInstructor() {
        return deleteInstructor;
    }

    public void getRefreshInstructorName() {
        instructorEmails();
    }

    public JButton getUpdateInstructor() {
        return updateInstructor;
    }

    public void getRefreshModuleName() {
        refreshModuleName();
    }

}
