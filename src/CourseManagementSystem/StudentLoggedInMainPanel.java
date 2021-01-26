package CourseManagementSystem;

import javax.swing.*;
import java.awt.*;

public class StudentLoggedInMainPanel extends JPanel implements AppLayout {
    private JButton viewCourses,viewResult,logOut;
    private GridBagConstraints layout;

    public StudentLoggedInMainPanel() {
        viewCourses = new JButton("View Courses");
        viewResult = new JButton("View Result");
        logOut = new JButton("Logout");
    }

    @Override
    public JPanel panelUI() {
        setLayout(new GridBagLayout());
        layout = new GridBagConstraints();
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.insets = new Insets(5,5,5,5);
        this.setBackground(Color.decode("#D6D9DF"));

        layout.gridx = 0;
        layout.gridy = 0;
        add(viewCourses,layout);

        layout.gridx = 0;
        layout.gridy = 1;
        add(viewResult,layout);

        layout.gridx = 0;
        layout.gridy = 2;
        add(logOut,layout);

        return this;
    }

    public JButton getViewCourses() {
        return viewCourses;
    }
    public JButton getViewResult() {
        return viewResult;
    }
    public JButton getLogOut() {
        return logOut;
    }
}
