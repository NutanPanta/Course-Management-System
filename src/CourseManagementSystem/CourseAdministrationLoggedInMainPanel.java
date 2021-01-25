package CourseManagementSystem;

import javax.swing.*;
import java.awt.*;

public class CourseAdministrationLoggedInMainPanel extends JPanel implements AppLayout {
    private JButton Courses;
    private JButton Modules;
    private JButton addInstructor;
    private GridBagConstraints layout;

    public CourseAdministrationLoggedInMainPanel() {
        Courses = new JButton("Courses");
        Modules = new JButton("Modules");
        addInstructor = new JButton("Add Instructor to a module");
    }

    @Override
    public JPanel panelUI() {
        setLayout(new GridBagLayout());
        layout = new GridBagConstraints();
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.insets = new Insets(5,5,5,5);

        layout.gridx = 0;
        layout.gridy = 0;
        add(Courses,layout);

        layout.gridx = 0;
        layout.gridy = 1;
        add(Modules,layout);

        layout.gridx = 0;
        layout.gridy = 2;
        add(addInstructor,layout);

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
}
