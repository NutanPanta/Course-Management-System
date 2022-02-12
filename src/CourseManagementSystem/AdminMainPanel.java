package CourseManagementSystem;

import javax.swing.*;
import java.awt.*;

public class AdminMainPanel extends JPanel implements AppLayout {
    private JButton Courses, Modules, addInstructor, generateMarkSheet, logOut;
    private GridBagConstraints gridLayout;

    public AdminMainPanel() {
        Courses = new JButton("Courses");
        Modules = new JButton("Modules");
        addInstructor = new JButton("Add Instructor to a module");
        generateMarkSheet = new JButton("Generate Marks");
        logOut = new JButton("Logout");
    }

    @Override
    public JPanel panelUI() {
        setLayout(new GridBagLayout());
        gridLayout = new GridBagConstraints();
        gridLayout.fill = GridBagConstraints.HORIZONTAL;
        gridLayout.insets = new Insets(5, 5, 5, 5);
        this.setBackground(Color.decode("#D6D9DE"));

        gridLayout.gridx = 0;
        gridLayout.gridy = 0;
        add(Courses, gridLayout);

        gridLayout.gridx = 0;
        gridLayout.gridy = 1;
        add(Modules, gridLayout);

        gridLayout.gridx = 0;
        gridLayout.gridy = 2;
        add(addInstructor, gridLayout);

        gridLayout.gridx = 0;
        gridLayout.gridy = 3;
        add(generateMarkSheet, gridLayout);

        gridLayout.gridx = 0;
        gridLayout.gridy = 4;
        add(logOut, gridLayout);

        return this;
    }

    public JButton getCourses() {
        return Courses;
    }

    public JButton getModules() {
        return Modules;
    }

    public JButton getAddInstructor() {
        return addInstructor;
    }

    public JButton getGenerateMarkSheet() {
        return generateMarkSheet;
    }

    public JButton getLogOut() {
        return logOut;
    }
}
