package CourseManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class CourseAdministrationLoggedInCoursesPanel  extends JPanel implements AppLayout {
    private DefaultTableModel courseAdministratorCourseModel;
    private JTable courseAdministratorCourseTable;
    private JTextField courseId, courseName;
    private JButton addCourse,cancelCourse,deleteCourse,updateCourse, back;
    private GridBagConstraints layout, formLayout, buttonLayout;

    public CourseAdministrationLoggedInCoursesPanel() {
        DefaultTableModel courseAdministratorCourseModel = new DefaultTableModel();
        String[] courseNames = {"Course Id","Course Name","Course Status"};
        courseAdministratorCourseModel.setColumnIdentifiers(courseNames);
        courseId = new JTextField(20);
        courseId.setPreferredSize(new Dimension(40,30));
        courseName = new JTextField(20);
        courseName.setPreferredSize(new Dimension(40,30));
        addCourse = new JButton("Add Course");
        cancelCourse = new JButton("Cancel Course");
        deleteCourse = new JButton("Delete Course");
        updateCourse = new JButton("Update Course");
        back = new JButton("Back");

        courseAdministratorCourseTable = new JTable(courseAdministratorCourseModel);


    }

    private JPanel courseAdministratorCourseTablePanel() {

        JScrollPane scrollPane =new JScrollPane(courseAdministratorCourseTable);
        courseAdministratorCourseTable.setSelectionBackground(Color.BLACK);
        courseAdministratorCourseTable.setSelectionForeground(Color.WHITE);
        JTableHeader header = courseAdministratorCourseTable.getTableHeader();
        header.setBackground(Color.decode("#D6D9DF"));

        JPanel courseAdministratorCourseTable = new JPanel();
        courseAdministratorCourseTable.setBackground(Color.decode("#D6D9DF"));
        courseAdministratorCourseTable.add(scrollPane);

        return courseAdministratorCourseTable;
    }

    private JPanel courseAdministratorCourseFormPanel(){
        JPanel courseAdministratorCourseForm = new JPanel();
        courseAdministratorCourseForm.setBackground(Color.decode("#D6D9DF"));
        courseAdministratorCourseForm.setLayout(new GridBagLayout());
        formLayout = new GridBagConstraints();
        formLayout.insets = new Insets(80,0,0,0);

        formLayout.gridx = 0;
        formLayout.gridy = 0;
        courseAdministratorCourseForm.add(new JLabel("Course Id"),formLayout);

        formLayout.gridx = 1;
        formLayout.gridy = 0;
        courseAdministratorCourseForm.add(courseId,formLayout);
        formLayout.insets = new Insets(20,5,5,5);

        formLayout.gridx = 0;
        formLayout.gridy = 1;
        courseAdministratorCourseForm.add(new JLabel("Course Name"),formLayout);

        formLayout.gridx = 1;
        formLayout.gridy = 1;
        courseAdministratorCourseForm.add(courseName,formLayout);

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
        courseAdministratorCourseButton.add(deleteCourse, buttonLayout);

        buttonLayout.gridx = 1;
        buttonLayout.gridy = 1;
        courseAdministratorCourseButton.add(updateCourse, buttonLayout);

        buttonLayout.gridx = 0;
        buttonLayout.gridy = 2;
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


    public DefaultTableModel getCourseModel(){
        return courseAdministratorCourseModel;
    }
    public  JTable getTable(){
        return courseAdministratorCourseTable;
    }
    public JTextField getCourseId() { return courseId; }
    public JTextField getCourseName() { return courseName; }
    public JButton getAddCourse() {
        return addCourse;
    }
    public JButton getCancelCourse() {
        return cancelCourse;
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

