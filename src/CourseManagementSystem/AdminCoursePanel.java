package CourseManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class AdminCoursePanel extends JPanel implements AppLayout {
    private GridBagConstraints gridLayout, buttonLayout, formLayout;
    private DefaultTableModel courseAdministratorCourseModel;
    private JTable courseAdministratorCourseTable;
    private JTextField name;
    private JButton cancelCourse, openCourse, addCourse, back, updateCourse, deleteCourse;

    public AdminCoursePanel() {
        courseAdministratorCourseModel = new DefaultTableModel();
        String[] courses = { "Id", "Name", "Status" };
        courseAdministratorCourseModel.setColumnIdentifiers(courses);
        name = new JTextField(20);
        name.setPreferredSize(new Dimension(40, 30));
        cancelCourse = new JButton("Cancel Course");
        openCourse = new JButton("Open Course");
        addCourse = new JButton("Add Course");
        back = new JButton("Back");
        updateCourse = new JButton("Update Course");
        deleteCourse = new JButton("Delete Course");

        courseAdministratorCourseTable = new JTable(courseAdministratorCourseModel);

    }

    private JPanel courseAdministratorCourseButtonPanel() {
        JPanel courseAdministratorCourseButton = new JPanel();
        courseAdministratorCourseButton.setBackground(Color.decode("#D6D9DE"));
        courseAdministratorCourseButton.setLayout(new GridBagLayout());
        buttonLayout = new GridBagConstraints();
        buttonLayout.fill = GridBagConstraints.HORIZONTAL;
        buttonLayout.insets = new Insets(20, 10, 5, 10);
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

    private JPanel courseAdministratorCourseTablePanel() {

        courseAdministratorCourseTable.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(courseAdministratorCourseTable);
        courseAdministratorCourseTable.setSelectionBackground(Color.BLACK);
        courseAdministratorCourseTable.setSelectionForeground(Color.WHITE);
        JTableHeader header = courseAdministratorCourseTable.getTableHeader();
        header.setBackground(Color.decode("#D6D9DE"));

        JPanel courseAdministratorCoursePanel = new JPanel();
        courseAdministratorCoursePanel.setBackground(Color.decode("#D6D9DE"));
        courseAdministratorCoursePanel.add(scrollPane);

        return courseAdministratorCoursePanel;
    }

    private JPanel courseAdministratorCourseFormPanel() {
        JPanel courseAdministratorCourseForm = new JPanel();
        courseAdministratorCourseForm.setBackground(Color.decode("#D6D9DE"));
        courseAdministratorCourseForm.setLayout(new GridBagLayout());
        formLayout = new GridBagConstraints();
        formLayout.insets = new Insets(80, 0, 0, 0);

        formLayout.gridx = 0;
        formLayout.gridy = 0;
        courseAdministratorCourseForm.add(new JLabel("Course Name"), formLayout);

        formLayout.gridx = 1;
        formLayout.gridy = 0;
        courseAdministratorCourseForm.add(name, formLayout);

        return courseAdministratorCourseForm;
    }

    @Override
    public JPanel panelUI() {
        this.setBackground(Color.decode("#D6D9DE"));
        setLayout(new GridBagLayout());
        gridLayout = new GridBagConstraints();
        gridLayout.fill = GridBagConstraints.BOTH;
        gridLayout.insets = new Insets(4, 0, 4, 0);

        gridLayout.gridx = 3;
        gridLayout.gridy = 3;

        gridLayout.gridx = 1;
        gridLayout.gridy = 0;
        add(courseAdministratorCourseFormPanel(), gridLayout);

        gridLayout.gridx = 1;
        gridLayout.gridy = 1;
        add(courseAdministratorCourseButtonPanel(), gridLayout);

        gridLayout.weighty = 0.5;
        gridLayout.gridx = 0;
        gridLayout.gridy = 0;
        gridLayout.gridheight = 3;
        add(courseAdministratorCourseTablePanel(), gridLayout);

        return this;
    }

    public DefaultTableModel getCourseModel() {
        return (DefaultTableModel) getTable().getModel();
    }

    public JButton getBack() {
        return back;
    }

    public JTextField getCourseName() {
        return name;
    }

    public JButton getOpenCourse() {
        return openCourse;
    }

    public JTable getTable() {
        return courseAdministratorCourseTable;
    }

    public JButton getCancelCourse() {
        return cancelCourse;
    }

    public JButton getAddCourse() {
        return addCourse;
    }

    public JButton getUpdateCourse() {
        return updateCourse;
    }

    public JButton getDeleteCourse() {
        return deleteCourse;
    }
}
