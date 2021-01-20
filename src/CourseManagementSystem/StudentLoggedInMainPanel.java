package CourseManagementSystem;

import javax.swing.*;
import java.awt.*;

public class StudentLoggedInMainPanel extends JPanel implements AppLayout {
    private JButton viewCourses;
    private JButton viewResult;
    private GridBagConstraints layout;

    public StudentLoggedInMainPanel() {
        viewCourses = new JButton("View Courses");
        viewResult = new JButton("View Result");
    }

    @Override
    public JPanel panelUI() {
        setLayout(new GridBagLayout());
        layout = new GridBagConstraints();
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.insets = new Insets(5,5,5,5);

        layout.gridx = 0;
        layout.gridy = 0;
        add(viewCourses,layout);

        layout.gridx = 0;
        layout.gridy = 1;
        add(viewResult,layout);

        return this;
    }

    public JButton getViewCourses() {
        return viewCourses;
    }
    public JButton getViewResult() {
        return viewResult;
    }
}
