package CourseManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminMarksGeneratePanel extends JPanel implements AppLayout {
    private JTextField marks1, marks2, marks3, marks4, marks5, marks6, marks7, marks8;
    private JTextField pMarks1, pMarks2, pMarks3, pMarks4, pMarks5, pMarks6, pMarks7, pMarks8;
    private JTextField status1, status2, status3, status4, status5, status6, status7, status8;
    private JTextField emailLabel, nameLabel, name, level, course;
    private JTextField fMarks1, fMarks2, fMarks3, fMarks4, fMarks5, fMarks6, fMarks7, fMarks8;
    private JTextField subjectName1, subjectName2, subjectName3, subjectName4, subjectName5, subjectName6, subjectName7,
            subjectName8;
    private JTextField grade1, grade2, grade3, grade4, grade5, grade6, grade7, grade8;
    private JTextField moduleHeader, obtainedMarksHeader, fullMarksHeader, passMarksHeader, gradeHeader, statusHeader,
            totalMarks, obtainerMarks, fullMarks, passMarks, grade, status;
    private JTextPane title, comp, optional;
    private GridBagConstraints gridLayout, details, gradeSheet;
    private JComboBox<String> email, studentLevel, courseName;
    private JButton back;
    UserTable userTable;
    StudentCourseTable studentCourseTable;
    InstructorPanelTable instructorPanelTable;
    courseTable courseTable;
    public int totalObtained, totalFM, totalPM, totalObtainedMarksInPercentage = 0;

    public AdminMarksGeneratePanel() {
        course = new JTextField(25);
        course.setDisabledTextColor(new Color(0, 0, 0));
        course.setPreferredSize(new Dimension(40, 50));
        course.setHorizontalAlignment(JTextField.CENTER);
        course.setFont(course.getFont().deriveFont(Font.BOLD));
        course.setText("Enrolled Course:");
        course.setEnabled(false);

        courseName = new JComboBox<>();
        courseName.setPreferredSize(new Dimension(250, 50));
        courseName.setBackground(new Color(255, 255, 255));

        level = new JTextField(25);
        level.setText("Student Level:");
        level.setPreferredSize(new Dimension(40, 50));
        level.setDisabledTextColor(new Color(0, 0, 0));
        level.setHorizontalAlignment(JTextField.CENTER);
        level.setFont(level.getFont().deriveFont(Font.BOLD));
        level.setEnabled(false);

        String levels[] = { "Select Level", "4", "5", "6" };
        studentLevel = new JComboBox<>(levels);
        studentLevel.setPreferredSize(new Dimension(250, 50));
        studentLevel.setBackground(new Color(255, 255, 255));

        emailLabel = new JTextField(25);
        emailLabel.setHorizontalAlignment(JTextField.CENTER);
        emailLabel.setPreferredSize(new Dimension(40, 50));
        emailLabel.setFont(emailLabel.getFont().deriveFont(Font.BOLD));
        emailLabel.setText("Student Email:");
        emailLabel.setDisabledTextColor(new Color(0, 0, 0));
        emailLabel.setEnabled(false);

        email = new JComboBox<>();
        email.setBackground(new Color(255, 255, 255));
        email.setPreferredSize(new Dimension(260, 50));
        email.addItem("Select Student");

        nameLabel = new JTextField(25);
        nameLabel.setFont(emailLabel.getFont().deriveFont(Font.BOLD));
        nameLabel.setDisabledTextColor(new Color(0, 0, 0));
        nameLabel.setPreferredSize(new Dimension(40, 50));
        nameLabel.setHorizontalAlignment(JTextField.CENTER);
        nameLabel.setText("Student Name:");
        nameLabel.setEnabled(false);

        name = new JTextField(25);
        name.setFont(name.getFont().deriveFont(Font.BOLD));
        name.setDisabledTextColor(new Color(0, 0, 0));
        name.setPreferredSize(new Dimension(40, 50));
        name.setHorizontalAlignment(JTextField.CENTER);

        name.setEnabled(false);
        marks1 = new JTextField(25);
        marks1.setFont(marks1.getFont().deriveFont(Font.BOLD));
        marks1.setDisabledTextColor(new Color(0, 0, 0));
        marks1.setPreferredSize(new Dimension(40, 50));
        marks1.setHorizontalAlignment(JTextField.CENTER);

        marks1.setEnabled(false);
        marks2 = new JTextField(25);
        marks2.setFont(marks2.getFont().deriveFont(Font.BOLD));
        marks2.setDisabledTextColor(new Color(0, 0, 0));
        marks2.setPreferredSize(new Dimension(40, 50));
        marks2.setHorizontalAlignment(JTextField.CENTER);

        marks2.setEnabled(false);
        marks3 = new JTextField(25);
        marks3.setFont(marks3.getFont().deriveFont(Font.BOLD));
        marks3.setDisabledTextColor(new Color(0, 0, 0));
        marks3.setPreferredSize(new Dimension(40, 50));
        marks3.setHorizontalAlignment(JTextField.CENTER);

        marks3.setEnabled(false);
        marks4 = new JTextField(25);
        marks4.setFont(marks4.getFont().deriveFont(Font.BOLD));
        marks4.setDisabledTextColor(new Color(0, 0, 0));
        marks4.setPreferredSize(new Dimension(40, 50));
        marks4.setHorizontalAlignment(JTextField.CENTER);

        marks4.setEnabled(false);
        marks5 = new JTextField(25);
        marks5.setFont(marks5.getFont().deriveFont(Font.BOLD));
        marks5.setDisabledTextColor(new Color(0, 0, 0));
        marks5.setPreferredSize(new Dimension(40, 50));
        marks5.setHorizontalAlignment(JTextField.CENTER);

        marks5.setEnabled(false);
        marks6 = new JTextField(25);
        marks6.setFont(marks6.getFont().deriveFont(Font.BOLD));
        marks6.setDisabledTextColor(new Color(0, 0, 0));
        marks6.setPreferredSize(new Dimension(40, 50));
        marks6.setHorizontalAlignment(JTextField.CENTER);

        marks6.setEnabled(false);
        marks7 = new JTextField(25);
        marks7.setFont(marks7.getFont().deriveFont(Font.BOLD));
        marks7.setDisabledTextColor(new Color(0, 0, 0));
        marks7.setPreferredSize(new Dimension(40, 50));
        marks7.setHorizontalAlignment(JTextField.CENTER);

        marks7.setEnabled(false);
        marks8 = new JTextField(25);
        marks8.setFont(marks8.getFont().deriveFont(Font.BOLD));
        marks8.setDisabledTextColor(new Color(0, 0, 0));
        marks8.setPreferredSize(new Dimension(40, 50));
        marks8.setHorizontalAlignment(JTextField.CENTER);

        marks8.setEnabled(false);
        fMarks1 = new JTextField(25);
        fMarks1.setFont(fMarks1.getFont().deriveFont(Font.BOLD));
        fMarks1.setDisabledTextColor(new Color(0, 0, 0));
        fMarks1.setPreferredSize(new Dimension(40, 50));
        fMarks1.setHorizontalAlignment(JTextField.CENTER);

        fMarks1.setEnabled(false);
        fMarks2 = new JTextField(25);
        fMarks2.setFont(fMarks2.getFont().deriveFont(Font.BOLD));
        fMarks2.setDisabledTextColor(new Color(0, 0, 0));
        fMarks2.setPreferredSize(new Dimension(40, 50));
        fMarks2.setHorizontalAlignment(JTextField.CENTER);

        fMarks2.setEnabled(false);
        fMarks3 = new JTextField(25);
        fMarks3.setFont(fMarks3.getFont().deriveFont(Font.BOLD));
        fMarks3.setDisabledTextColor(new Color(0, 0, 0));
        fMarks3.setPreferredSize(new Dimension(40, 50));
        fMarks3.setHorizontalAlignment(JTextField.CENTER);

        fMarks3.setEnabled(false);
        fMarks4 = new JTextField(25);
        fMarks4.setFont(fMarks4.getFont().deriveFont(Font.BOLD));
        fMarks4.setDisabledTextColor(new Color(0, 0, 0));
        fMarks4.setPreferredSize(new Dimension(40, 50));
        fMarks4.setHorizontalAlignment(JTextField.CENTER);

        fMarks4.setEnabled(false);
        fMarks5 = new JTextField(25);
        fMarks5.setFont(fMarks5.getFont().deriveFont(Font.BOLD));
        fMarks5.setDisabledTextColor(new Color(0, 0, 0));
        fMarks5.setPreferredSize(new Dimension(40, 50));
        fMarks5.setHorizontalAlignment(JTextField.CENTER);

        fMarks5.setEnabled(false);
        fMarks6 = new JTextField(25);
        fMarks6.setFont(fMarks6.getFont().deriveFont(Font.BOLD));
        fMarks6.setDisabledTextColor(new Color(0, 0, 0));
        fMarks6.setPreferredSize(new Dimension(40, 50));
        fMarks6.setHorizontalAlignment(JTextField.CENTER);

        fMarks6.setEnabled(false);
        fMarks7 = new JTextField(25);
        fMarks7.setFont(fMarks7.getFont().deriveFont(Font.BOLD));
        fMarks7.setDisabledTextColor(new Color(0, 0, 0));
        fMarks7.setPreferredSize(new Dimension(40, 50));
        fMarks7.setHorizontalAlignment(JTextField.CENTER);

        fMarks7.setEnabled(false);
        fMarks8 = new JTextField(25);
        fMarks8.setFont(fMarks8.getFont().deriveFont(Font.BOLD));
        fMarks8.setDisabledTextColor(new Color(0, 0, 0));
        fMarks8.setPreferredSize(new Dimension(40, 50));
        fMarks8.setHorizontalAlignment(JTextField.CENTER);

        fMarks8.setEnabled(false);
        pMarks1 = new JTextField(25);
        pMarks1.setFont(pMarks1.getFont().deriveFont(Font.BOLD));
        pMarks1.setDisabledTextColor(new Color(0, 0, 0));
        pMarks1.setPreferredSize(new Dimension(40, 50));
        pMarks1.setHorizontalAlignment(JTextField.CENTER);

        pMarks1.setEnabled(false);
        pMarks2 = new JTextField(25);
        pMarks2.setFont(pMarks2.getFont().deriveFont(Font.BOLD));
        pMarks2.setDisabledTextColor(new Color(0, 0, 0));
        pMarks2.setPreferredSize(new Dimension(40, 50));
        pMarks2.setHorizontalAlignment(JTextField.CENTER);

        pMarks2.setEnabled(false);
        pMarks3 = new JTextField(25);
        pMarks3.setFont(pMarks3.getFont().deriveFont(Font.BOLD));
        pMarks3.setDisabledTextColor(new Color(0, 0, 0));
        pMarks3.setPreferredSize(new Dimension(40, 50));
        pMarks3.setHorizontalAlignment(JTextField.CENTER);

        pMarks3.setEnabled(false);
        pMarks4 = new JTextField(25);
        pMarks4.setFont(pMarks4.getFont().deriveFont(Font.BOLD));
        pMarks4.setDisabledTextColor(new Color(0, 0, 0));
        pMarks4.setPreferredSize(new Dimension(40, 50));
        pMarks4.setHorizontalAlignment(JTextField.CENTER);

        pMarks4.setEnabled(false);
        pMarks5 = new JTextField(25);
        pMarks5.setFont(pMarks5.getFont().deriveFont(Font.BOLD));
        pMarks5.setDisabledTextColor(new Color(0, 0, 0));
        pMarks5.setPreferredSize(new Dimension(40, 50));
        pMarks5.setHorizontalAlignment(JTextField.CENTER);

        pMarks5.setEnabled(false);
        pMarks6 = new JTextField(25);
        pMarks6.setFont(pMarks6.getFont().deriveFont(Font.BOLD));
        pMarks6.setDisabledTextColor(new Color(0, 0, 0));
        pMarks6.setPreferredSize(new Dimension(40, 50));
        pMarks6.setHorizontalAlignment(JTextField.CENTER);

        pMarks6.setEnabled(false);
        pMarks7 = new JTextField(25);
        pMarks7.setFont(pMarks7.getFont().deriveFont(Font.BOLD));
        pMarks7.setDisabledTextColor(new Color(0, 0, 0));
        pMarks7.setPreferredSize(new Dimension(40, 50));
        pMarks7.setHorizontalAlignment(JTextField.CENTER);

        pMarks7.setEnabled(false);
        pMarks8 = new JTextField(25);
        pMarks8.setFont(pMarks8.getFont().deriveFont(Font.BOLD));
        pMarks8.setDisabledTextColor(new Color(0, 0, 0));
        pMarks8.setPreferredSize(new Dimension(40, 50));
        pMarks8.setHorizontalAlignment(JTextField.CENTER);

        pMarks8.setEnabled(false);
        grade1 = new JTextField(25);
        grade1.setFont(grade1.getFont().deriveFont(Font.BOLD));
        grade1.setDisabledTextColor(new Color(0, 0, 0));
        grade1.setPreferredSize(new Dimension(40, 50));
        grade1.setHorizontalAlignment(JTextField.CENTER);

        grade1.setEnabled(false);
        grade2 = new JTextField(25);
        grade2.setFont(grade2.getFont().deriveFont(Font.BOLD));
        grade2.setDisabledTextColor(new Color(0, 0, 0));
        grade2.setPreferredSize(new Dimension(40, 50));
        grade2.setHorizontalAlignment(JTextField.CENTER);

        grade2.setEnabled(false);
        grade3 = new JTextField(25);
        grade3.setFont(grade3.getFont().deriveFont(Font.BOLD));
        grade3.setDisabledTextColor(new Color(0, 0, 0));
        grade3.setPreferredSize(new Dimension(40, 50));
        grade3.setHorizontalAlignment(JTextField.CENTER);

        grade3.setEnabled(false);
        grade4 = new JTextField(25);
        grade4.setFont(grade4.getFont().deriveFont(Font.BOLD));
        grade4.setDisabledTextColor(new Color(0, 0, 0));
        grade4.setPreferredSize(new Dimension(40, 50));
        grade4.setHorizontalAlignment(JTextField.CENTER);

        grade4.setEnabled(false);
        grade5 = new JTextField(25);
        grade5.setFont(grade5.getFont().deriveFont(Font.BOLD));
        grade5.setDisabledTextColor(new Color(0, 0, 0));
        grade5.setPreferredSize(new Dimension(40, 50));
        grade5.setHorizontalAlignment(JTextField.CENTER);

        grade5.setEnabled(false);
        grade6 = new JTextField(25);
        grade6.setFont(grade6.getFont().deriveFont(Font.BOLD));
        grade6.setDisabledTextColor(new Color(0, 0, 0));
        grade6.setPreferredSize(new Dimension(40, 50));
        grade6.setHorizontalAlignment(JTextField.CENTER);

        grade6.setEnabled(false);
        grade7 = new JTextField(25);
        grade7.setFont(grade7.getFont().deriveFont(Font.BOLD));
        grade7.setDisabledTextColor(new Color(0, 0, 0));
        grade7.setPreferredSize(new Dimension(40, 50));
        grade7.setHorizontalAlignment(JTextField.CENTER);

        grade7.setEnabled(false);
        grade8 = new JTextField(25);
        grade8.setFont(grade8.getFont().deriveFont(Font.BOLD));
        grade8.setDisabledTextColor(new Color(0, 0, 0));
        grade8.setPreferredSize(new Dimension(40, 50));
        grade8.setHorizontalAlignment(JTextField.CENTER);

        grade8.setEnabled(false);
        status1 = new JTextField(25);
        status1.setFont(status1.getFont().deriveFont(Font.BOLD));
        status1.setDisabledTextColor(new Color(0, 0, 0));
        status1.setPreferredSize(new Dimension(40, 50));
        status1.setHorizontalAlignment(JTextField.CENTER);

        status1.setEnabled(false);
        status2 = new JTextField(25);
        status2.setFont(status2.getFont().deriveFont(Font.BOLD));
        status2.setDisabledTextColor(new Color(0, 0, 0));
        status2.setPreferredSize(new Dimension(40, 50));
        status2.setHorizontalAlignment(JTextField.CENTER);

        status2.setEnabled(false);
        status3 = new JTextField(25);
        status3.setFont(status3.getFont().deriveFont(Font.BOLD));
        status3.setDisabledTextColor(new Color(0, 0, 0));
        status3.setPreferredSize(new Dimension(40, 50));
        status3.setHorizontalAlignment(JTextField.CENTER);

        status3.setEnabled(false);
        status4 = new JTextField(25);
        status4.setFont(status4.getFont().deriveFont(Font.BOLD));
        status4.setDisabledTextColor(new Color(0, 0, 0));
        status4.setPreferredSize(new Dimension(40, 50));
        status4.setHorizontalAlignment(JTextField.CENTER);

        status4.setEnabled(false);
        status5 = new JTextField(25);
        status5.setFont(status5.getFont().deriveFont(Font.BOLD));
        status5.setDisabledTextColor(new Color(0, 0, 0));
        status5.setPreferredSize(new Dimension(40, 50));
        status5.setHorizontalAlignment(JTextField.CENTER);

        status5.setEnabled(false);
        status6 = new JTextField(25);
        status6.setFont(status6.getFont().deriveFont(Font.BOLD));
        status6.setDisabledTextColor(new Color(0, 0, 0));
        status6.setPreferredSize(new Dimension(40, 50));
        status6.setHorizontalAlignment(JTextField.CENTER);

        status6.setEnabled(false);
        status7 = new JTextField(25);
        status7.setFont(status7.getFont().deriveFont(Font.BOLD));
        status7.setDisabledTextColor(new Color(0, 0, 0));
        status7.setPreferredSize(new Dimension(40, 50));
        status7.setHorizontalAlignment(JTextField.CENTER);

        status7.setEnabled(false);
        status8 = new JTextField(25);
        status8.setFont(status8.getFont().deriveFont(Font.BOLD));
        status8.setDisabledTextColor(new Color(0, 0, 0));
        status8.setPreferredSize(new Dimension(40, 50));
        status8.setHorizontalAlignment(JTextField.CENTER);

        status8.setEnabled(false);
        subjectName1 = new JTextField(25);
        subjectName1.setFont(subjectName1.getFont().deriveFont(Font.BOLD));
        subjectName1.setDisabledTextColor(new Color(0, 0, 0));
        subjectName1.setPreferredSize(new Dimension(40, 50));
        subjectName1.setHorizontalAlignment(JTextField.CENTER);

        subjectName1.setEnabled(false);
        subjectName2 = new JTextField(25);
        subjectName2.setFont(subjectName2.getFont().deriveFont(Font.BOLD));
        subjectName2.setDisabledTextColor(new Color(0, 0, 0));
        subjectName2.setPreferredSize(new Dimension(40, 50));
        subjectName2.setHorizontalAlignment(JTextField.CENTER);

        subjectName2.setEnabled(false);
        subjectName3 = new JTextField(25);
        subjectName3.setFont(subjectName3.getFont().deriveFont(Font.BOLD));
        subjectName3.setDisabledTextColor(new Color(0, 0, 0));
        subjectName3.setPreferredSize(new Dimension(40, 50));
        subjectName3.setHorizontalAlignment(JTextField.CENTER);

        subjectName3.setEnabled(false);
        subjectName4 = new JTextField(25);
        subjectName4.setFont(subjectName4.getFont().deriveFont(Font.BOLD));
        subjectName4.setDisabledTextColor(new Color(0, 0, 0));
        subjectName4.setPreferredSize(new Dimension(40, 50));
        subjectName4.setHorizontalAlignment(JTextField.CENTER);

        subjectName4.setEnabled(false);
        subjectName5 = new JTextField(25);
        subjectName5.setFont(subjectName5.getFont().deriveFont(Font.BOLD));
        subjectName5.setDisabledTextColor(new Color(0, 0, 0));
        subjectName5.setPreferredSize(new Dimension(40, 50));
        subjectName5.setHorizontalAlignment(JTextField.CENTER);

        subjectName5.setEnabled(false);
        subjectName6 = new JTextField(25);
        subjectName6.setFont(subjectName6.getFont().deriveFont(Font.BOLD));
        subjectName6.setDisabledTextColor(new Color(0, 0, 0));
        subjectName6.setPreferredSize(new Dimension(40, 50));
        subjectName6.setHorizontalAlignment(JTextField.CENTER);

        subjectName6.setEnabled(false);
        subjectName7 = new JTextField(25);
        subjectName7.setFont(subjectName7.getFont().deriveFont(Font.BOLD));
        subjectName7.setDisabledTextColor(new Color(0, 0, 0));
        subjectName7.setPreferredSize(new Dimension(40, 50));
        subjectName7.setHorizontalAlignment(JTextField.CENTER);

        subjectName7.setEnabled(false);
        subjectName8 = new JTextField(25);
        subjectName8.setFont(subjectName8.getFont().deriveFont(Font.BOLD));
        subjectName8.setDisabledTextColor(new Color(0, 0, 0));
        subjectName8.setPreferredSize(new Dimension(40, 50));
        subjectName8.setHorizontalAlignment(JTextField.CENTER);

        subjectName8.setEnabled(false);
        moduleHeader = new JTextField(25);
        moduleHeader.setFont(moduleHeader.getFont().deriveFont(Font.BOLD));
        moduleHeader.setDisabledTextColor(new Color(0, 0, 0));
        moduleHeader.setPreferredSize(new Dimension(40, 50));
        moduleHeader.setHorizontalAlignment(JTextField.CENTER);
        moduleHeader.setText("Module Name(Semester)");
        moduleHeader.setEnabled(false);

        obtainedMarksHeader = new JTextField(25);
        obtainedMarksHeader.setFont(obtainedMarksHeader.getFont().deriveFont(Font.BOLD));
        obtainedMarksHeader.setDisabledTextColor(new Color(0, 0, 0));
        obtainedMarksHeader.setPreferredSize(new Dimension(40, 50));
        obtainedMarksHeader.setHorizontalAlignment(JTextField.CENTER);
        obtainedMarksHeader.setText("Obtained Marks");
        obtainedMarksHeader.setEnabled(false);

        fullMarksHeader = new JTextField(25);
        fullMarksHeader.setFont(fullMarksHeader.getFont().deriveFont(Font.BOLD));
        fullMarksHeader.setDisabledTextColor(new Color(0, 0, 0));
        fullMarksHeader.setPreferredSize(new Dimension(40, 50));
        fullMarksHeader.setHorizontalAlignment(JTextField.CENTER);
        fullMarksHeader.setText("Full Marks");
        fullMarksHeader.setEnabled(false);

        passMarksHeader = new JTextField(25);
        passMarksHeader.setFont(passMarksHeader.getFont().deriveFont(Font.BOLD));
        passMarksHeader.setDisabledTextColor(new Color(0, 0, 0));
        passMarksHeader.setPreferredSize(new Dimension(40, 50));
        passMarksHeader.setHorizontalAlignment(JTextField.CENTER);
        passMarksHeader.setText("Pass Marks");
        passMarksHeader.setEnabled(false);

        gradeHeader = new JTextField(25);
        gradeHeader.setFont(gradeHeader.getFont().deriveFont(Font.BOLD));
        gradeHeader.setDisabledTextColor(new Color(0, 0, 0));
        gradeHeader.setPreferredSize(new Dimension(40, 50));
        gradeHeader.setHorizontalAlignment(JTextField.CENTER);
        gradeHeader.setText("Grade");
        gradeHeader.setEnabled(false);

        statusHeader = new JTextField(25);
        statusHeader.setFont(statusHeader.getFont().deriveFont(Font.BOLD));
        statusHeader.setDisabledTextColor(new Color(0, 0, 0));
        statusHeader.setPreferredSize(new Dimension(40, 50));
        statusHeader.setHorizontalAlignment(JTextField.CENTER);
        statusHeader.setText("Remarks");
        statusHeader.setEnabled(false);

        totalMarks = new JTextField(25);
        totalMarks.setFont(totalMarks.getFont().deriveFont(Font.BOLD));
        totalMarks.setDisabledTextColor(new Color(0, 0, 0));
        totalMarks.setPreferredSize(new Dimension(40, 50));
        totalMarks.setHorizontalAlignment(JTextField.CENTER);
        totalMarks.setText("Total");
        totalMarks.setEnabled(false);

        obtainerMarks = new JTextField(25);
        obtainerMarks.setFont(obtainerMarks.getFont().deriveFont(Font.BOLD));
        obtainerMarks.setDisabledTextColor(new Color(0, 0, 0));
        obtainerMarks.setPreferredSize(new Dimension(40, 50));
        obtainerMarks.setHorizontalAlignment(JTextField.CENTER);

        obtainerMarks.setEnabled(false);
        fullMarks = new JTextField(25);
        fullMarks.setFont(fullMarks.getFont().deriveFont(Font.BOLD));
        fullMarks.setDisabledTextColor(new Color(0, 0, 0));
        fullMarks.setPreferredSize(new Dimension(40, 50));
        fullMarks.setHorizontalAlignment(JTextField.CENTER);

        fullMarks.setEnabled(false);
        passMarks = new JTextField(25);
        passMarks.setFont(passMarks.getFont().deriveFont(Font.BOLD));
        passMarks.setDisabledTextColor(new Color(0, 0, 0));
        passMarks.setPreferredSize(new Dimension(40, 50));
        passMarks.setHorizontalAlignment(JTextField.CENTER);

        passMarks.setEnabled(false);
        grade = new JTextField(25);
        grade.setFont(grade.getFont().deriveFont(Font.BOLD));
        grade.setDisabledTextColor(new Color(0, 0, 0));
        grade.setPreferredSize(new Dimension(40, 50));
        grade.setHorizontalAlignment(JTextField.CENTER);

        grade.setEnabled(false);
        status = new JTextField(25);
        status.setFont(status.getFont().deriveFont(Font.BOLD));
        status.setDisabledTextColor(new Color(0, 0, 0));
        status.setPreferredSize(new Dimension(40, 50));
        status.setHorizontalAlignment(JTextField.CENTER);

        status.setEnabled(false);
        title = new JTextPane();
        title.setFont(title.getFont().deriveFont(Font.BOLD));
        title.setDisabledTextColor(new Color(0, 0, 0));
        title.setContentType("text/html");
        title.setText(
                "<HTML><CENTER><H1>Student Progress Report Card<BR>Herald College Kathmandu</H1></CENTER></HTML>");

        title.setEnabled(false);

        comp = new JTextPane();
        comp.setFont(comp.getFont().deriveFont(Font.BOLD));
        comp.setPreferredSize(new Dimension(40, 50));
        comp.setContentType("text/html");
        comp.setText("<HTML><BODY><CENTER><H1>Compulsory Modules</H1></BODY></CENTER><HTML>");
        comp.setDisabledTextColor(new Color(0, 0, 0));

        comp.setEnabled(false);
        optional = new JTextPane();
        optional.setFont(optional.getFont().deriveFont(Font.BOLD));
        optional.setPreferredSize(new Dimension(40, 50));
        optional.setContentType("text/html");
        optional.setText("<HTML><BODY><CENTER><H1>Elective Modules</H1></CENTER></BODY><HTML>");
        optional.setDisabledTextColor(new Color(0, 0, 0));

        optional.setEnabled(false);
        back = new JButton("Back");
        back.setPreferredSize(new Dimension(40, 35));

        userTable = new UserTable();
        studentCourseTable = new StudentCourseTable();
        instructorPanelTable = new InstructorPanelTable();
        courseTable = new courseTable();

        courseName();
        refreshStudentEmail();
        refreshStudentName();
        refreshMarkSheet();
    }

    private void courseName() {
        try {
            ResultSet resultSet = courseTable.openCourses();
            courseName.removeAllItems();
            courseName.addItem("Select Course Names");
            while (resultSet.next()) {
                courseName.addItem(resultSet.getString("courseName"));

            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(this, "Internal Server Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
            error.printStackTrace();
        }
    }

    private void email() {
        if (courseName.getItemCount() == 0) {
        } else {
            try {
                String level = studentLevel.getSelectedItem().toString();
                String course = courseName.getSelectedItem().toString();
                ResultSet resultSet = instructorPanelTable.getAllStudents(course, level);
                email.removeAllItems();
                email.addItem("Select Student");
                while (resultSet.next()) {
                    email.addItem(resultSet.getString("email"));
                }
            } catch (SQLException error) {
                JOptionPane.showMessageDialog(this, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void name() {
        if (email.getItemCount() == 0) {
        } else {
            try {
                if (email.getSelectedItem().toString() == "Select Student") {
                    name.setText("");
                } else {
                    String level = studentLevel.getSelectedItem().toString();
                    String mail = email.getSelectedItem().toString().trim();
                    String course = courseName.getSelectedItem().toString();
                    ResultSet resultSet = instructorPanelTable.getStudentsDetails(mail, course, level);
                    while (resultSet.next()) {
                        name.setText(resultSet.getString("firstName") + " " + resultSet.getString("lastName"));
                    }
                }
            } catch (SQLException error) {
                JOptionPane.showMessageDialog(this, "Internal Server Error", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void refreshStudentEmail() {
        courseName.addActionListener(error -> {
            email();
        });

        studentLevel.addActionListener(error -> {
            email();
            if (studentLevel.getSelectedItem().toString().equals("6")) {
                optional.setText("<HTML><BODY><CENTER><H1>Elective Modules</H1></CENTER></BODY><HTML>");
            } else {
                optional.setText("<HTML><BODY><CENTER><H1></H1></CENTER></BODY><HTML>");
            }
        });
    }

    private void refreshStudentName() {
        email.addActionListener(error -> {
            name();
        });
    }

    private void clear() {
        clearCompulsory(subjectName1, subjectName2, subjectName3, subjectName4,
                marks1, marks2, marks3, marks4,
                pMarks1, pMarks2, pMarks3, pMarks4);

        clearCompulsory(fMarks1, fMarks2, fMarks3, fMarks4,
                grade1, grade2, grade3, grade4, status1,
                status2, status3, status4);
    }

    private void clearCompulsory(JTextField subjectName1, JTextField subjectName2,
            JTextField subjectName3, JTextField subjectName4, JTextField marks1,
            JTextField marks2, JTextField marks3,
            JTextField marks4, JTextField pMarks1, JTextField pMarks2,
            JTextField pMarks3, JTextField pMarks4) {
        clearElective(subjectName1, subjectName2, subjectName3, subjectName4,
                marks1, marks2, marks3, marks4,
                pMarks1, pMarks2, pMarks3, pMarks4);
    }

    private void clear1() {
        clearElective(subjectName5, subjectName6, subjectName7, subjectName8,
                marks5, marks6, marks7, marks8,
                pMarks5, pMarks6, pMarks7, pMarks8);

        clearElective(fMarks5, fMarks6, fMarks7, fMarks8,
                grade5, grade6, grade7, grade8, status5,
                status6, status7, status8);

    }

    private void clearElective(JTextField subjectName5, JTextField subjectName6,
            JTextField subjectName7, JTextField subjectName8, JTextField marks5,
            JTextField marks6, JTextField marks7,
            JTextField marks8, JTextField pMarks5, JTextField pMarks6,
            JTextField pMarks7, JTextField pMarks8) {
        subjectName5.setText("");
        subjectName6.setText("");
        subjectName7.setText("");
        subjectName8.setText("");

        marks5.setText("");
        marks6.setText("");
        marks7.setText("");
        marks8.setText("");

        pMarks5.setText("");
        pMarks6.setText("");
        pMarks7.setText("");
        pMarks8.setText("");
    }

    public void studentLevelCompulsoryModuleNames() {
        if (email.getItemCount() == 0) {
        } else {
            clear();
            clear1();
            try {
                String emailAddress = email.getSelectedItem().toString().trim();
                ResultSet resultSet = studentCourseTable.getCompulsoryMarksDetailsForResult(emailAddress);
                while (resultSet.next()) {
                    subjectName1
                            .setText(resultSet.getString("moduleName") + "(" + resultSet.getString("semester") + ")");
                    marks1.setText(resultSet.getString("obtainedMarks"));
                    pMarks1.setText(resultSet.getString("passMarks"));
                    fMarks1.setText(resultSet.getString("fullMarks"));
                    grade1.setText(resultSet.getString("grade"));
                    status1.setText(resultSet.getString("status"));
                    totalObtained += Integer.parseInt(marks1.getText().trim());
                    totalFM += Integer.parseInt(fMarks1.getText().trim());
                    totalPM += Integer.parseInt(pMarks1.getText().trim());
                    if (resultSet.next()) {
                        subjectName2.setText(
                                resultSet.getString("moduleName") + "(" + resultSet.getString("semester") + ")");
                        marks2.setText(resultSet.getString("obtainedMarks"));
                        pMarks2.setText(resultSet.getString("passMarks"));
                        fMarks2.setText(resultSet.getString("fullMarks"));
                        grade2.setText(resultSet.getString("grade"));
                        status2.setText(resultSet.getString("status"));
                        totalObtained += Integer.parseInt(marks2.getText().trim());
                        totalFM += Integer.parseInt(fMarks2.getText().trim());
                        totalPM += Integer.parseInt(pMarks2.getText().trim());
                    }
                    if (resultSet.next()) {
                        subjectName3.setText(
                                resultSet.getString("moduleName") + "(" + resultSet.getString("semester") + ")");
                        marks3.setText(resultSet.getString("obtainedMarks"));
                        pMarks3.setText(resultSet.getString("passMarks"));
                        fMarks3.setText(resultSet.getString("fullMarks"));
                        grade3.setText(resultSet.getString("grade"));
                        status3.setText(resultSet.getString("status"));
                        totalObtained += Integer.parseInt(marks3.getText().trim());
                        totalFM += Integer.parseInt(fMarks3.getText().trim());
                        totalPM += Integer.parseInt(pMarks3.getText().trim());
                    }
                    if (resultSet.next()) {
                        subjectName4.setText(
                                resultSet.getString("moduleName") + "(" + resultSet.getString("semester") + ")");
                        marks4.setText(resultSet.getString("obtainedMarks"));
                        pMarks4.setText(resultSet.getString("passMarks"));
                        fMarks4.setText(resultSet.getString("fullMarks"));
                        grade4.setText(resultSet.getString("grade"));
                        status4.setText(resultSet.getString("status"));
                        totalObtained += Integer.parseInt(marks4.getText().trim());
                        totalFM += Integer.parseInt(fMarks4.getText().trim());
                        totalPM += Integer.parseInt(pMarks4.getText().trim());
                    }
                    if (resultSet.next()) {
                        subjectName5.setText(
                                resultSet.getString("moduleName") + "(" + resultSet.getString("semester") + ")");
                        marks5.setText(resultSet.getString("obtainedMarks"));
                        pMarks5.setText(resultSet.getString("passMarks"));
                        fMarks5.setText(resultSet.getString("fullMarks"));
                        grade5.setText(resultSet.getString("grade"));
                        status5.setText(resultSet.getString("status"));
                        totalObtained += Integer.parseInt(marks4.getText().trim());
                        totalFM += Integer.parseInt(fMarks4.getText().trim());
                        totalPM += Integer.parseInt(pMarks4.getText().trim());
                    }
                    if (resultSet.next()) {
                        subjectName6.setText(
                                resultSet.getString("moduleName") + "(" + resultSet.getString("semester") + ")");
                        marks6.setText(resultSet.getString("obtainedMarks"));
                        pMarks6.setText(resultSet.getString("passMarks"));
                        fMarks6.setText(resultSet.getString("fullMarks"));
                        grade6.setText(resultSet.getString("grade"));
                        status6.setText(resultSet.getString("status"));
                        totalObtained += Integer.parseInt(marks6.getText().trim());
                        totalFM += Integer.parseInt(fMarks6.getText().trim());
                        totalPM += Integer.parseInt(pMarks6.getText().trim());
                    }
                    if (resultSet.next()) {
                        subjectName7.setText(
                                resultSet.getString("moduleName") + "(" + resultSet.getString("semester") + ")");
                        marks7.setText(resultSet.getString("obtainedMarks"));
                        pMarks7.setText(resultSet.getString("passMarks"));
                        fMarks7.setText(resultSet.getString("fullMarks"));
                        grade7.setText(resultSet.getString("grade"));
                        status7.setText(resultSet.getString("status"));
                        totalObtained += Integer.parseInt(marks7.getText().trim());
                        totalFM += Integer.parseInt(fMarks7.getText().trim());
                        totalPM += Integer.parseInt(pMarks7.getText().trim());
                    }
                    if (resultSet.next()) {
                        subjectName8.setText(
                                resultSet.getString("moduleName") + "(" + resultSet.getString("semester") + ")");
                        marks8.setText(resultSet.getString("obtainedMarks"));
                        pMarks8.setText(resultSet.getString("passMarks"));
                        fMarks8.setText(resultSet.getString("fullMarks"));
                        grade8.setText(resultSet.getString("grade"));
                        status8.setText(resultSet.getString("status"));
                        totalObtained += Integer.parseInt(marks8.getText().trim());
                        totalFM += Integer.parseInt(fMarks8.getText().trim());
                        totalPM += Integer.parseInt(pMarks8.getText().trim());
                    }
                }
                String level = studentLevel.getSelectedItem().toString().trim();
                if (!level.equals("6")) {
                    obtainerMarks.setText(String.valueOf(totalObtained));
                    fullMarks.setText(String.valueOf(totalFM));
                    passMarks.setText(String.valueOf(totalPM));
                    if (totalObtained == 0 || totalFM == 0 || totalPM == 0) {

                    } else {
                        totalObtainedMarksInPercentage = (totalObtained * 100) / totalFM;
                    }
                    if (!fullMarks.getText().trim().equals("800")) {
                        grade.setText("Marks of all 8 modules must be given");
                        status.setText("Marks of all 8 modules must be given");
                    } else if (totalObtainedMarksInPercentage > 70) {
                        grade.setText("A");
                        status.setText("Pass");
                    } else if (totalObtainedMarksInPercentage <= 70 && totalObtainedMarksInPercentage >= 60) {
                        grade.setText("B");
                        status.setText("Pass");
                    } else if (totalObtainedMarksInPercentage < 60 && totalObtainedMarksInPercentage >= 50) {
                        grade.setText("C");
                        status.setText("Pass");
                    } else if (totalObtainedMarksInPercentage < 50 && totalObtainedMarksInPercentage >= 43) {
                        grade.setText("D");
                        status.setText("Pass");
                    } else if (totalObtainedMarksInPercentage < 43 && totalObtainedMarksInPercentage >= 40) {
                        grade.setText("E");
                        status.setText("Pass");
                    } else {
                        grade.setText("F");
                        status.setText("Fail");
                    }
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void studentLevelElectiveModuleNames() {
        if (email.getItemCount() == 0) {
        } else {
            String level = studentLevel.getSelectedItem().toString().trim();
            if (level.equals("6")) {
                clear1();
                optional.setText("<HTML><BODY><CENTER><H1>Elective Modules</H1></CENTER></BODY><HTML>");

                try {
                    String emailAddress = email.getSelectedItem().toString().trim();
                    ResultSet resultSet = studentCourseTable.getElectiveMarksDetailsForResult(emailAddress);
                    while (resultSet.next()) {
                        subjectName5.setText(
                                resultSet.getString("moduleName") + "(" + resultSet.getString("semester") + ")");
                        marks5.setText(resultSet.getString("obtainedMarks"));
                        pMarks5.setText(resultSet.getString("passMarks"));
                        fMarks5.setText(resultSet.getString("fullMarks"));
                        grade5.setText(resultSet.getString("grade"));
                        status5.setText(resultSet.getString("status"));
                        totalObtained += Integer.parseInt(marks5.getText().trim());
                        totalFM += Integer.parseInt(fMarks5.getText().trim());
                        totalPM += Integer.parseInt(pMarks5.getText().trim());

                        if (resultSet.next()) {
                            subjectName6.setText(
                                    resultSet.getString("moduleName") + "(" + resultSet.getString("semester") + ")");
                            marks6.setText(resultSet.getString("obtainedMarks"));
                            pMarks6.setText(resultSet.getString("passMarks"));
                            fMarks6.setText(resultSet.getString("fullMarks"));
                            grade6.setText(resultSet.getString("grade"));
                            status6.setText(resultSet.getString("status"));
                            totalObtained += Integer.parseInt(marks6.getText().trim());
                            totalFM += Integer.parseInt(fMarks6.getText().trim());
                            totalPM += Integer.parseInt(pMarks6.getText().trim());
                        }
                        if (resultSet.next()) {
                            subjectName7.setText(
                                    resultSet.getString("moduleName") + "(" + resultSet.getString("semester") + ")");
                            marks7.setText(resultSet.getString("obtainedMarks"));
                            pMarks7.setText(resultSet.getString("passMarks"));
                            fMarks7.setText(resultSet.getString("fullMarks"));
                            grade7.setText(resultSet.getString("grade"));
                            status7.setText(resultSet.getString("status"));
                            totalObtained += Integer.parseInt(marks7.getText().trim());
                            totalFM += Integer.parseInt(fMarks7.getText().trim());
                            totalPM += Integer.parseInt(pMarks7.getText().trim());
                        }
                        if (resultSet.next()) {
                            subjectName8.setText(
                                    resultSet.getString("moduleName") + "(" + resultSet.getString("semester") + ")");
                            marks8.setText(resultSet.getString("obtainedMarks"));
                            pMarks8.setText(resultSet.getString("passMarks"));
                            fMarks8.setText(resultSet.getString("fullMarks"));
                            grade8.setText(resultSet.getString("grade"));
                            status8.setText(resultSet.getString("status"));
                            totalObtained += Integer.parseInt(marks8.getText().trim());
                            totalFM += Integer.parseInt(fMarks8.getText().trim());
                            totalPM += Integer.parseInt(pMarks8.getText().trim());
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                obtainerMarks.setText(String.valueOf(totalObtained));
                fullMarks.setText(String.valueOf(totalFM));
                passMarks.setText(String.valueOf(totalPM));
                if (totalObtained == 0 || totalFM == 0 || totalPM == 0) {

                } else {
                    totalObtainedMarksInPercentage = (totalObtained * 100) / totalFM;
                }
                if (totalFM != 800) {
                    grade.setText("Marks of all 8 modules must be given");
                    status.setText("Marks of all 8 modules must be given");
                } else if (totalObtainedMarksInPercentage > 70) {
                    grade.setText("A");
                    status.setText("Pass");
                } else if (totalObtainedMarksInPercentage <= 70 && totalObtainedMarksInPercentage >= 60) {
                    grade.setText("B");
                    status.setText("Pass");
                } else if (totalObtainedMarksInPercentage < 60 && totalObtainedMarksInPercentage >= 50) {
                    grade.setText("C");
                    status.setText("Pass");
                } else if (totalObtainedMarksInPercentage < 50 && totalObtainedMarksInPercentage >= 43) {
                    grade.setText("D");
                    status.setText("Pass");
                } else if (totalObtainedMarksInPercentage < 43 && totalObtainedMarksInPercentage >= 40) {
                    grade.setText("E");
                    status.setText("Pass");
                } else {
                    grade.setText("F");
                    status.setText("Fail");
                }
            } else {
                optional.setText("<HTML><BODY><CENTER><H1></H1></CENTER></BODY><HTML>");
            }
        }
    }

    public void refreshMarkSheet() {
        email.addActionListener(error -> {
            studentLevelCompulsoryModuleNames();
            studentLevelElectiveModuleNames();
            totalObtained = 0;
            totalFM = 0;
            totalPM = 0;
        });
    }

    private JPanel studentDetailsPanel() {
        JPanel studentPanelCoursesStudentDetails = new JPanel();
        studentPanelCoursesStudentDetails.setLayout(new GridBagLayout());
        studentPanelCoursesStudentDetails.setBackground(Color.decode("#D6D9DE"));

        details = new GridBagConstraints();
        details.fill = GridBagConstraints.BOTH;
        gridLayout.insets = new Insets(0, 0, 0, 0);

        details.gridx = 0;
        details.gridy = 0;
        details.gridwidth = 6;
        add(title, details);

        details.gridx = 0;
        details.gridy = 1;
        details.gridwidth = 1;
        studentPanelCoursesStudentDetails.add(course, details);

        details.gridx = 1;
        details.gridy = 1;
        studentPanelCoursesStudentDetails.add(courseName, details);

        details.gridx = 2;
        details.gridy = 1;
        studentPanelCoursesStudentDetails.add(level, details);

        details.gridx = 3;
        details.gridy = 1;
        studentPanelCoursesStudentDetails.add(studentLevel, details);

        details.gridx = 4;
        details.gridy = 1;
        studentPanelCoursesStudentDetails.add(emailLabel, details);

        details.gridx = 5;
        details.gridy = 1;
        studentPanelCoursesStudentDetails.add(email, details);

        details.gridx = 0;
        details.gridy = 2;
        details.gridwidth = 4;
        studentPanelCoursesStudentDetails.add(back, details);

        details.gridx = 4;
        details.gridy = 2;
        details.gridwidth = 1;
        studentPanelCoursesStudentDetails.add(nameLabel, details);

        details.gridx = 5;
        details.gridy = 2;
        studentPanelCoursesStudentDetails.add(name, details);

        return studentPanelCoursesStudentDetails;
    }

    private JPanel studentGradePanel() {
        JPanel studentPanelReportCard = new JPanel();
        studentPanelReportCard.setLayout(new GridBagLayout());
        studentPanelReportCard.setBackground(Color.decode("#D6D9DE"));

        gradeSheet = new GridBagConstraints();
        gradeSheet.fill = GridBagConstraints.HORIZONTAL;

        gradeSheet.gridx = 0;
        gradeSheet.gridy = 0;
        studentPanelReportCard.add(moduleHeader, gradeSheet);

        gradeSheet.gridx = 1;
        gradeSheet.gridy = 0;
        studentPanelReportCard.add(obtainedMarksHeader, gradeSheet);

        gradeSheet.gridx = 2;
        gradeSheet.gridy = 0;
        studentPanelReportCard.add(fullMarksHeader, gradeSheet);

        gradeSheet.gridx = 3;
        gradeSheet.gridy = 0;
        studentPanelReportCard.add(passMarksHeader, gradeSheet);

        gradeSheet.gridx = 4;
        gradeSheet.gridy = 0;
        studentPanelReportCard.add(gradeHeader, gradeSheet);

        gradeSheet.gridx = 5;
        gradeSheet.gridy = 0;
        studentPanelReportCard.add(statusHeader, gradeSheet);

        gradeSheet.gridx = 0;
        gradeSheet.gridy = 1;
        gradeSheet.gridwidth = 6;
        studentPanelReportCard.add(comp, gradeSheet);

        gradeSheet.gridx = 0;
        gradeSheet.gridy = 2;
        gradeSheet.gridwidth = 1;
        studentPanelReportCard.add(subjectName1, gradeSheet);

        gradeSheet.gridx = 1;
        gradeSheet.gridy = 2;
        studentPanelReportCard.add(marks1, gradeSheet);

        gradeSheet.gridx = 2;
        gradeSheet.gridy = 2;
        studentPanelReportCard.add(fMarks1, gradeSheet);

        gradeSheet.gridx = 3;
        gradeSheet.gridy = 2;
        studentPanelReportCard.add(pMarks1, gradeSheet);

        gradeSheet.gridx = 4;
        gradeSheet.gridy = 2;
        studentPanelReportCard.add(grade1, gradeSheet);

        gradeSheet.gridx = 5;
        gradeSheet.gridy = 2;
        studentPanelReportCard.add(status1, gradeSheet);

        gradeSheet.gridx = 0;
        gradeSheet.gridy = 3;
        studentPanelReportCard.add(subjectName2, gradeSheet);

        gradeSheet.gridx = 1;
        gradeSheet.gridy = 3;
        studentPanelReportCard.add(marks2, gradeSheet);

        gradeSheet.gridx = 2;
        gradeSheet.gridy = 3;
        studentPanelReportCard.add(fMarks2, gradeSheet);

        gradeSheet.gridx = 3;
        gradeSheet.gridy = 3;
        studentPanelReportCard.add(pMarks2, gradeSheet);

        gradeSheet.gridx = 4;
        gradeSheet.gridy = 3;
        studentPanelReportCard.add(grade2, gradeSheet);

        gradeSheet.gridx = 5;
        gradeSheet.gridy = 3;
        studentPanelReportCard.add(status2, gradeSheet);

        gradeSheet.gridx = 0;
        gradeSheet.gridy = 4;
        studentPanelReportCard.add(subjectName3, gradeSheet);

        gradeSheet.gridx = 1;
        gradeSheet.gridy = 4;
        studentPanelReportCard.add(marks3, gradeSheet);

        gradeSheet.gridx = 2;
        gradeSheet.gridy = 4;
        studentPanelReportCard.add(fMarks3, gradeSheet);

        gradeSheet.gridx = 3;
        gradeSheet.gridy = 4;
        studentPanelReportCard.add(pMarks3, gradeSheet);

        gradeSheet.gridx = 4;
        gradeSheet.gridy = 4;
        studentPanelReportCard.add(grade3, gradeSheet);

        gradeSheet.gridx = 5;
        gradeSheet.gridy = 4;
        studentPanelReportCard.add(status3, gradeSheet);

        gradeSheet.gridx = 0;
        gradeSheet.gridy = 5;
        studentPanelReportCard.add(subjectName4, gradeSheet);

        gradeSheet.gridx = 1;
        gradeSheet.gridy = 5;
        studentPanelReportCard.add(marks4, gradeSheet);

        gradeSheet.gridx = 2;
        gradeSheet.gridy = 5;
        studentPanelReportCard.add(fMarks4, gradeSheet);

        gradeSheet.gridx = 3;
        gradeSheet.gridy = 5;
        studentPanelReportCard.add(pMarks4, gradeSheet);

        gradeSheet.gridx = 4;
        gradeSheet.gridy = 5;
        studentPanelReportCard.add(grade4, gradeSheet);

        gradeSheet.gridx = 5;
        gradeSheet.gridy = 5;
        studentPanelReportCard.add(status4, gradeSheet);

        gradeSheet.gridx = 0;
        gradeSheet.gridy = 6;
        gradeSheet.gridwidth = 6;
        studentPanelReportCard.add(optional, gradeSheet);

        gradeSheet.gridx = 0;
        gradeSheet.gridy = 7;
        gradeSheet.gridwidth = 1;
        studentPanelReportCard.add(subjectName5, gradeSheet);

        gradeSheet.gridx = 1;
        gradeSheet.gridy = 7;
        studentPanelReportCard.add(marks5, gradeSheet);

        gradeSheet.gridx = 2;
        gradeSheet.gridy = 7;
        studentPanelReportCard.add(fMarks5, gradeSheet);

        gradeSheet.gridx = 3;
        gradeSheet.gridy = 7;
        studentPanelReportCard.add(pMarks5, gradeSheet);

        gradeSheet.gridx = 4;
        gradeSheet.gridy = 7;
        studentPanelReportCard.add(grade5, gradeSheet);

        gradeSheet.gridx = 5;
        gradeSheet.gridy = 7;
        studentPanelReportCard.add(status5, gradeSheet);

        gradeSheet.gridx = 0;
        gradeSheet.gridy = 8;
        studentPanelReportCard.add(subjectName6, gradeSheet);

        gradeSheet.gridx = 1;
        gradeSheet.gridy = 8;
        studentPanelReportCard.add(marks6, gradeSheet);

        gradeSheet.gridx = 2;
        gradeSheet.gridy = 8;
        studentPanelReportCard.add(fMarks6, gradeSheet);

        gradeSheet.gridx = 3;
        gradeSheet.gridy = 8;
        studentPanelReportCard.add(pMarks6, gradeSheet);

        gradeSheet.gridx = 4;
        gradeSheet.gridy = 8;
        studentPanelReportCard.add(grade6, gradeSheet);

        gradeSheet.gridx = 5;
        gradeSheet.gridy = 8;
        studentPanelReportCard.add(status6, gradeSheet);

        gradeSheet.gridx = 0;
        gradeSheet.gridy = 9;
        studentPanelReportCard.add(subjectName7, gradeSheet);

        gradeSheet.gridx = 1;
        gradeSheet.gridy = 9;
        studentPanelReportCard.add(marks7, gradeSheet);

        gradeSheet.gridx = 2;
        gradeSheet.gridy = 9;
        studentPanelReportCard.add(fMarks7, gradeSheet);

        gradeSheet.gridx = 3;
        gradeSheet.gridy = 9;
        studentPanelReportCard.add(pMarks7, gradeSheet);

        gradeSheet.gridx = 4;
        gradeSheet.gridy = 9;
        studentPanelReportCard.add(grade7, gradeSheet);

        gradeSheet.gridx = 5;
        gradeSheet.gridy = 9;
        studentPanelReportCard.add(status7, gradeSheet);

        gradeSheet.gridx = 0;
        gradeSheet.gridy = 10;
        studentPanelReportCard.add(subjectName8, gradeSheet);

        gradeSheet.gridx = 1;
        gradeSheet.gridy = 10;
        studentPanelReportCard.add(marks8, gradeSheet);

        gradeSheet.gridx = 2;
        gradeSheet.gridy = 10;
        studentPanelReportCard.add(fMarks8, gradeSheet);

        gradeSheet.gridx = 3;
        gradeSheet.gridy = 10;
        studentPanelReportCard.add(pMarks8, gradeSheet);

        gradeSheet.gridx = 4;
        gradeSheet.gridy = 10;
        studentPanelReportCard.add(grade8, gradeSheet);

        gradeSheet.gridx = 5;
        gradeSheet.gridy = 10;
        studentPanelReportCard.add(status8, gradeSheet);

        gradeSheet.gridx = 0;
        gradeSheet.gridy = 11;
        studentPanelReportCard.add(totalMarks, gradeSheet);

        gradeSheet.gridx = 1;
        gradeSheet.gridy = 11;
        studentPanelReportCard.add(obtainerMarks, gradeSheet);

        gradeSheet.gridx = 2;
        gradeSheet.gridy = 11;
        studentPanelReportCard.add(fullMarks, gradeSheet);

        gradeSheet.gridx = 3;
        gradeSheet.gridy = 11;
        studentPanelReportCard.add(passMarks, gradeSheet);

        gradeSheet.gridx = 4;
        gradeSheet.gridy = 11;
        studentPanelReportCard.add(grade, gradeSheet);

        gradeSheet.gridx = 5;
        gradeSheet.gridy = 11;
        studentPanelReportCard.add(status, gradeSheet);

        return studentPanelReportCard;
    }

    @Override
    public JPanel panelUI() {
        setLayout(new GridBagLayout());
        gridLayout = new GridBagConstraints();
        gridLayout.fill = GridBagConstraints.BOTH;
        this.setBackground(Color.decode("#D6D9DE"));

        gridLayout.gridx = 0;
        gridLayout.gridy = 1;
        add(studentDetailsPanel(), gridLayout);

        gridLayout.gridx = 0;
        gridLayout.gridy = 2;
        add(studentGradePanel(), gridLayout);

        return this;
    }

    public void getCourseName() {
        courseName();
    }

    public JButton getBack() {
        return back;
    }

}
