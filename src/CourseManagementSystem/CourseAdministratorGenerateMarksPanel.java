package CourseManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseAdministratorGenerateMarksPanel extends JPanel implements AppLayout {
    private JTextField studentEmailLabel,studentLabel,studentName,studentLevelLabel,enrolledCourseLabel,
            subjectOneObtainedMarks,subjectTwoObtainedMarks,subjectThreeObtainedMarks,subjectFourObtainedMarks,subjectFiveObtainedMarks,subjectSixObtainedMarks,subjectSevenObtainedMarks,subjectEightObtainedMarks,
            subjectOneFullMarks,subjectTwoFullMarks,subjectThreeFullMarks,subjectFourFullMarks,subjectFiveFullMarks,subjectSixFullMarks,subjectSevenFullMarks,subjectEightFullMarks,
            subjectOnePassMarks,subjectTwoPassMarks,subjectThreePassMarks,subjectFourPassMarks,subjectFivePassMarks,subjectSixPassMarks,subjectSevenPassMarks,subjectEightPassMarks,
            subjectOneGrade,subjectTwoGrade,subjectThreeGrade,subjectFourGrade,subjectFiveGrade,subjectSixGrade,subjectSevenGrade,subjectEightGrade,
            subjectOneStatus,subjectTwoStatus,subjectThreeStatus,subjectFourStatus,subjectFiveStatus,subjectSixStatus,subjectSevenStatus,subjectEightStatus,
            subjectOneModuleName,subjectTwoModuleName,subjectThreeModuleName,subjectFourModuleName,subjectFiveModuleName,subjectSixModuleName,subjectSevenModuleName,subjectEightModuleName,
            moduleHeader,obtainedMarksHeader,fullMarksHeader,passMarksHeader,gradeHeader,statusHeader,
            total,totalObtainedMarks,totalFullMarks,totalPassMarks,totalGrade,totalStatus;
    private JTextPane title,compulsory,elective;
    private GridBagConstraints layout,details,markSheet;
    private JComboBox studentEmail,studentLevel,courseName;
    private JButton back;
    UserTable userTable;
    StudentCourseTable studentCourseTable;
    InstructorPanelTable instructorPanelTable;
    courseTable courseTable;
    public int totalObtained,totalFM,totalPM,totalObtainedMarksInPercentage = 0;

    public CourseAdministratorGenerateMarksPanel(){
        enrolledCourseLabel = new JTextField(25);
        enrolledCourseLabel.setPreferredSize(new Dimension(40,50));
        enrolledCourseLabel.setEnabled(false);
        enrolledCourseLabel.setDisabledTextColor(new Color(0,0,0));
        enrolledCourseLabel.setFont(enrolledCourseLabel.getFont().deriveFont(Font.BOLD));
        enrolledCourseLabel.setHorizontalAlignment(JTextField.CENTER);
        enrolledCourseLabel.setText("Enrolled Course Name:");

        courseName = new JComboBox();
        courseName.setBackground(new Color(255,255,255));
        courseName.setPreferredSize(new Dimension(250,50));

        studentLevelLabel = new JTextField(25);
        studentLevelLabel.setPreferredSize(new Dimension(40,50));
        studentLevelLabel.setEnabled(false);
        studentLevelLabel.setDisabledTextColor(new Color(0,0,0));
        studentLevelLabel.setFont(studentLevelLabel.getFont().deriveFont(Font.BOLD));
        studentLevelLabel.setText("Student Level:");
        studentLevelLabel.setHorizontalAlignment(JTextField.CENTER);

        String levels[] = {"Select Level","4","5","6"};
        studentLevel = new JComboBox(levels);
        studentLevel.setBackground(new Color(255,255,255));
        studentLevel.setPreferredSize(new Dimension(250,50));

        studentEmailLabel = new JTextField(25);
        studentEmailLabel.setPreferredSize(new Dimension(40,50));
        studentEmailLabel.setEnabled(false);
        studentEmailLabel.setDisabledTextColor(new Color(0,0,0));
        studentEmailLabel.setFont(studentEmailLabel.getFont().deriveFont(Font.BOLD));
        studentEmailLabel.setHorizontalAlignment(JTextField.CENTER);
        studentEmailLabel.setText("Student Email:");

        studentEmail = new JComboBox();
        studentEmail.setBackground(new Color(255,255,255));
        studentEmail.setPreferredSize(new Dimension(260,50));
        studentEmail.addItem("Select Student");

        studentLabel = new JTextField(25);
        studentLabel.setPreferredSize(new Dimension(40,50));
        studentLabel.setEnabled(false);
        studentLabel.setDisabledTextColor(new Color(0,0,0));
        studentLabel.setFont(studentEmailLabel.getFont().deriveFont(Font.BOLD));
        studentLabel.setHorizontalAlignment(JTextField.CENTER);
        studentLabel.setText("Student Name:");

        studentName = new JTextField(25);
        studentName.setPreferredSize(new Dimension(40,50));
        studentName.setEnabled(false);
        studentName.setDisabledTextColor(new Color(0,0,0));
        studentName.setFont(studentName.getFont().deriveFont(Font.BOLD));
        studentName.setHorizontalAlignment(JTextField.CENTER);

        subjectOneObtainedMarks = new JTextField(25);
        subjectOneObtainedMarks.setPreferredSize(new Dimension(40,50));
        subjectOneObtainedMarks.setEnabled(false);
        subjectOneObtainedMarks.setDisabledTextColor(new Color(0,0,0));
        subjectOneObtainedMarks.setFont(subjectOneObtainedMarks.getFont().deriveFont(Font.BOLD));
        subjectOneObtainedMarks.setHorizontalAlignment(JTextField.CENTER);

        subjectTwoObtainedMarks = new JTextField(25);
        subjectTwoObtainedMarks.setPreferredSize(new Dimension(40,50));
        subjectTwoObtainedMarks.setEnabled(false);
        subjectTwoObtainedMarks.setDisabledTextColor(new Color(0,0,0));
        subjectTwoObtainedMarks.setFont(subjectTwoObtainedMarks.getFont().deriveFont(Font.BOLD));
        subjectTwoObtainedMarks.setHorizontalAlignment(JTextField.CENTER);

        subjectThreeObtainedMarks = new JTextField(25);
        subjectThreeObtainedMarks.setPreferredSize(new Dimension(40,50));
        subjectThreeObtainedMarks.setEnabled(false);
        subjectThreeObtainedMarks.setDisabledTextColor(new Color(0,0,0));
        subjectThreeObtainedMarks.setFont(subjectThreeObtainedMarks.getFont().deriveFont(Font.BOLD));
        subjectThreeObtainedMarks.setHorizontalAlignment(JTextField.CENTER);

        subjectFourObtainedMarks = new JTextField(25);
        subjectFourObtainedMarks.setPreferredSize(new Dimension(40,50));
        subjectFourObtainedMarks.setEnabled(false);
        subjectFourObtainedMarks.setDisabledTextColor(new Color(0,0,0));
        subjectFourObtainedMarks.setFont(subjectFourObtainedMarks.getFont().deriveFont(Font.BOLD));
        subjectFourObtainedMarks.setHorizontalAlignment(JTextField.CENTER);

        subjectFiveObtainedMarks = new JTextField(25);
        subjectFiveObtainedMarks.setPreferredSize(new Dimension(40,50));
        subjectFiveObtainedMarks.setEnabled(false);
        subjectFiveObtainedMarks.setDisabledTextColor(new Color(0,0,0));
        subjectFiveObtainedMarks.setFont(subjectFiveObtainedMarks.getFont().deriveFont(Font.BOLD));
        subjectFiveObtainedMarks.setHorizontalAlignment(JTextField.CENTER);

        subjectSixObtainedMarks = new JTextField(25);
        subjectSixObtainedMarks.setPreferredSize(new Dimension(40,50));
        subjectSixObtainedMarks.setEnabled(false);
        subjectSixObtainedMarks.setDisabledTextColor(new Color(0,0,0));
        subjectSixObtainedMarks.setFont(subjectSixObtainedMarks.getFont().deriveFont(Font.BOLD));
        subjectSixObtainedMarks.setHorizontalAlignment(JTextField.CENTER);

        subjectSevenObtainedMarks = new JTextField(25);
        subjectSevenObtainedMarks.setPreferredSize(new Dimension(40,50));
        subjectSevenObtainedMarks.setEnabled(false);
        subjectSevenObtainedMarks.setDisabledTextColor(new Color(0,0,0));
        subjectSevenObtainedMarks.setFont(subjectSevenObtainedMarks.getFont().deriveFont(Font.BOLD));
        subjectSevenObtainedMarks.setHorizontalAlignment(JTextField.CENTER);

        subjectEightObtainedMarks = new JTextField(25);
        subjectEightObtainedMarks.setPreferredSize(new Dimension(40,50));
        subjectEightObtainedMarks.setEnabled(false);
        subjectEightObtainedMarks.setDisabledTextColor(new Color(0,0,0));
        subjectEightObtainedMarks.setFont(subjectEightObtainedMarks.getFont().deriveFont(Font.BOLD));
        subjectEightObtainedMarks.setHorizontalAlignment(JTextField.CENTER);

        subjectOneFullMarks = new JTextField(25);
        subjectOneFullMarks.setPreferredSize(new Dimension(40,50));
        subjectOneFullMarks.setEnabled(false);
        subjectOneFullMarks.setDisabledTextColor(new Color(0,0,0));
        subjectOneFullMarks.setFont(subjectOneFullMarks.getFont().deriveFont(Font.BOLD));
        subjectOneFullMarks.setHorizontalAlignment(JTextField.CENTER);

        subjectTwoFullMarks = new JTextField(25);
        subjectTwoFullMarks.setPreferredSize(new Dimension(40,50));
        subjectTwoFullMarks.setEnabled(false);
        subjectTwoFullMarks.setDisabledTextColor(new Color(0,0,0));
        subjectTwoFullMarks.setFont(subjectTwoFullMarks.getFont().deriveFont(Font.BOLD));
        subjectTwoFullMarks.setHorizontalAlignment(JTextField.CENTER);

        subjectThreeFullMarks = new JTextField(25);
        subjectThreeFullMarks.setPreferredSize(new Dimension(40,50));
        subjectThreeFullMarks.setEnabled(false);
        subjectThreeFullMarks.setDisabledTextColor(new Color(0,0,0));
        subjectThreeFullMarks.setFont(subjectThreeFullMarks.getFont().deriveFont(Font.BOLD));
        subjectThreeFullMarks.setHorizontalAlignment(JTextField.CENTER);

        subjectFourFullMarks = new JTextField(25);
        subjectFourFullMarks.setPreferredSize(new Dimension(40,50));
        subjectFourFullMarks.setEnabled(false);
        subjectFourFullMarks.setDisabledTextColor(new Color(0,0,0));
        subjectFourFullMarks.setFont(subjectFourFullMarks.getFont().deriveFont(Font.BOLD));
        subjectFourFullMarks.setHorizontalAlignment(JTextField.CENTER);

        subjectFiveFullMarks = new JTextField(25);
        subjectFiveFullMarks.setPreferredSize(new Dimension(40,50));
        subjectFiveFullMarks.setEnabled(false);
        subjectFiveFullMarks.setDisabledTextColor(new Color(0,0,0));
        subjectFiveFullMarks.setFont(subjectFiveFullMarks.getFont().deriveFont(Font.BOLD));
        subjectFiveFullMarks.setHorizontalAlignment(JTextField.CENTER);

        subjectSixFullMarks = new JTextField(25);
        subjectSixFullMarks.setPreferredSize(new Dimension(40,50));
        subjectSixFullMarks.setEnabled(false);
        subjectSixFullMarks.setDisabledTextColor(new Color(0,0,0));
        subjectSixFullMarks.setFont(subjectSixFullMarks.getFont().deriveFont(Font.BOLD));
        subjectSixFullMarks.setHorizontalAlignment(JTextField.CENTER);

        subjectSevenFullMarks = new JTextField(25);
        subjectSevenFullMarks.setPreferredSize(new Dimension(40,50));
        subjectSevenFullMarks.setEnabled(false);
        subjectSevenFullMarks.setDisabledTextColor(new Color(0,0,0));
        subjectSevenFullMarks.setFont(subjectSevenFullMarks.getFont().deriveFont(Font.BOLD));
        subjectSevenFullMarks.setHorizontalAlignment(JTextField.CENTER);

        subjectEightFullMarks = new JTextField(25);
        subjectEightFullMarks.setPreferredSize(new Dimension(40,50));
        subjectEightFullMarks.setEnabled(false);
        subjectEightFullMarks.setDisabledTextColor(new Color(0,0,0));
        subjectEightFullMarks.setFont(subjectEightFullMarks.getFont().deriveFont(Font.BOLD));
        subjectEightFullMarks.setHorizontalAlignment(JTextField.CENTER);

        subjectOnePassMarks = new JTextField(25);
        subjectOnePassMarks.setPreferredSize(new Dimension(40,50));
        subjectOnePassMarks.setEnabled(false);
        subjectOnePassMarks.setDisabledTextColor(new Color(0,0,0));
        subjectOnePassMarks.setFont(subjectOnePassMarks.getFont().deriveFont(Font.BOLD));
        subjectOnePassMarks.setHorizontalAlignment(JTextField.CENTER);

        subjectTwoPassMarks = new JTextField(25);
        subjectTwoPassMarks.setPreferredSize(new Dimension(40,50));
        subjectTwoPassMarks.setEnabled(false);
        subjectTwoPassMarks.setDisabledTextColor(new Color(0,0,0));
        subjectTwoPassMarks.setFont(subjectTwoPassMarks.getFont().deriveFont(Font.BOLD));
        subjectTwoPassMarks.setHorizontalAlignment(JTextField.CENTER);

        subjectThreePassMarks = new JTextField(25);
        subjectThreePassMarks.setPreferredSize(new Dimension(40,50));
        subjectThreePassMarks.setEnabled(false);
        subjectThreePassMarks.setDisabledTextColor(new Color(0,0,0));
        subjectThreePassMarks.setFont(subjectThreePassMarks.getFont().deriveFont(Font.BOLD));
        subjectThreePassMarks.setHorizontalAlignment(JTextField.CENTER);

        subjectFourPassMarks = new JTextField(25);
        subjectFourPassMarks.setPreferredSize(new Dimension(40,50));
        subjectFourPassMarks.setEnabled(false);
        subjectFourPassMarks.setDisabledTextColor(new Color(0,0,0));
        subjectFourPassMarks.setFont(subjectFourPassMarks.getFont().deriveFont(Font.BOLD));
        subjectFourPassMarks.setHorizontalAlignment(JTextField.CENTER);

        subjectFivePassMarks = new JTextField(25);
        subjectFivePassMarks.setPreferredSize(new Dimension(40,50));
        subjectFivePassMarks.setEnabled(false);
        subjectFivePassMarks.setDisabledTextColor(new Color(0,0,0));
        subjectFivePassMarks.setFont(subjectFivePassMarks.getFont().deriveFont(Font.BOLD));
        subjectFivePassMarks.setHorizontalAlignment(JTextField.CENTER);

        subjectSixPassMarks = new JTextField(25);
        subjectSixPassMarks.setPreferredSize(new Dimension(40,50));
        subjectSixPassMarks.setEnabled(false);
        subjectSixPassMarks.setDisabledTextColor(new Color(0,0,0));
        subjectSixPassMarks.setFont(subjectSixPassMarks.getFont().deriveFont(Font.BOLD));
        subjectSixPassMarks.setHorizontalAlignment(JTextField.CENTER);

        subjectSevenPassMarks = new JTextField(25);
        subjectSevenPassMarks.setPreferredSize(new Dimension(40,50));
        subjectSevenPassMarks.setEnabled(false);
        subjectSevenPassMarks.setDisabledTextColor(new Color(0,0,0));
        subjectSevenPassMarks.setFont(subjectSevenPassMarks.getFont().deriveFont(Font.BOLD));
        subjectSevenPassMarks.setHorizontalAlignment(JTextField.CENTER);

        subjectEightPassMarks = new JTextField(25);
        subjectEightPassMarks.setPreferredSize(new Dimension(40,50));
        subjectEightPassMarks.setEnabled(false);
        subjectEightPassMarks.setDisabledTextColor(new Color(0,0,0));
        subjectEightPassMarks.setFont(subjectEightPassMarks.getFont().deriveFont(Font.BOLD));
        subjectEightPassMarks.setHorizontalAlignment(JTextField.CENTER);

        subjectOneGrade = new JTextField(25);
        subjectOneGrade.setPreferredSize(new Dimension(40,50));
        subjectOneGrade.setEnabled(false);
        subjectOneGrade.setDisabledTextColor(new Color(0,0,0));
        subjectOneGrade.setFont(subjectOneGrade.getFont().deriveFont(Font.BOLD));
        subjectOneGrade.setHorizontalAlignment(JTextField.CENTER);

        subjectTwoGrade = new JTextField(25);
        subjectTwoGrade.setPreferredSize(new Dimension(40,50));
        subjectTwoGrade.setEnabled(false);
        subjectTwoGrade.setDisabledTextColor(new Color(0,0,0));
        subjectTwoGrade.setFont(subjectTwoGrade.getFont().deriveFont(Font.BOLD));
        subjectTwoGrade.setHorizontalAlignment(JTextField.CENTER);

        subjectThreeGrade = new JTextField(25);
        subjectThreeGrade.setPreferredSize(new Dimension(40,50));
        subjectThreeGrade.setEnabled(false);
        subjectThreeGrade.setDisabledTextColor(new Color(0,0,0));
        subjectThreeGrade.setFont(subjectThreeGrade.getFont().deriveFont(Font.BOLD));
        subjectThreeGrade.setHorizontalAlignment(JTextField.CENTER);

        subjectFourGrade = new JTextField(25);
        subjectFourGrade.setPreferredSize(new Dimension(40,50));
        subjectFourGrade.setEnabled(false);
        subjectFourGrade.setDisabledTextColor(new Color(0,0,0));
        subjectFourGrade.setFont(subjectFourGrade.getFont().deriveFont(Font.BOLD));
        subjectFourGrade.setHorizontalAlignment(JTextField.CENTER);

        subjectFiveGrade = new JTextField(25);
        subjectFiveGrade.setPreferredSize(new Dimension(40,50));
        subjectFiveGrade.setEnabled(false);
        subjectFiveGrade.setDisabledTextColor(new Color(0,0,0));
        subjectFiveGrade.setFont(subjectFiveGrade.getFont().deriveFont(Font.BOLD));
        subjectFiveGrade.setHorizontalAlignment(JTextField.CENTER);

        subjectSixGrade = new JTextField(25);
        subjectSixGrade.setPreferredSize(new Dimension(40,50));
        subjectSixGrade.setEnabled(false);
        subjectSixGrade.setDisabledTextColor(new Color(0,0,0));
        subjectSixGrade.setFont(subjectSixGrade.getFont().deriveFont(Font.BOLD));
        subjectSixGrade.setHorizontalAlignment(JTextField.CENTER);

        subjectSevenGrade = new JTextField(25);
        subjectSevenGrade.setPreferredSize(new Dimension(40,50));
        subjectSevenGrade.setEnabled(false);
        subjectSevenGrade.setDisabledTextColor(new Color(0,0,0));
        subjectSevenGrade.setFont(subjectSevenGrade.getFont().deriveFont(Font.BOLD));
        subjectSevenGrade.setHorizontalAlignment(JTextField.CENTER);

        subjectEightGrade = new JTextField(25);
        subjectEightGrade.setPreferredSize(new Dimension(40,50));
        subjectEightGrade.setEnabled(false);
        subjectEightGrade.setDisabledTextColor(new Color(0,0,0));
        subjectEightGrade.setFont(subjectEightGrade.getFont().deriveFont(Font.BOLD));
        subjectEightGrade.setHorizontalAlignment(JTextField.CENTER);

        subjectOneStatus = new JTextField(25);
        subjectOneStatus.setPreferredSize(new Dimension(40,50));
        subjectOneStatus.setEnabled(false);
        subjectOneStatus.setDisabledTextColor(new Color(0,0,0));
        subjectOneStatus.setFont(subjectOneStatus.getFont().deriveFont(Font.BOLD));
        subjectOneStatus.setHorizontalAlignment(JTextField.CENTER);

        subjectTwoStatus = new JTextField(25);
        subjectTwoStatus.setPreferredSize(new Dimension(40,50));
        subjectTwoStatus.setEnabled(false);
        subjectTwoStatus.setDisabledTextColor(new Color(0,0,0));
        subjectTwoStatus.setFont(subjectTwoStatus.getFont().deriveFont(Font.BOLD));
        subjectTwoStatus.setHorizontalAlignment(JTextField.CENTER);

        subjectThreeStatus = new JTextField(25);
        subjectThreeStatus.setPreferredSize(new Dimension(40,50));
        subjectThreeStatus.setEnabled(false);
        subjectThreeStatus.setDisabledTextColor(new Color(0,0,0));
        subjectThreeStatus.setFont(subjectThreeStatus.getFont().deriveFont(Font.BOLD));
        subjectThreeStatus.setHorizontalAlignment(JTextField.CENTER);

        subjectFourStatus = new JTextField(25);
        subjectFourStatus.setPreferredSize(new Dimension(40,50));
        subjectFourStatus.setEnabled(false);
        subjectFourStatus.setDisabledTextColor(new Color(0,0,0));
        subjectFourStatus.setFont(subjectFourStatus.getFont().deriveFont(Font.BOLD));
        subjectFourStatus.setHorizontalAlignment(JTextField.CENTER);

        subjectFiveStatus = new JTextField(25);
        subjectFiveStatus.setPreferredSize(new Dimension(40,50));
        subjectFiveStatus.setEnabled(false);
        subjectFiveStatus.setDisabledTextColor(new Color(0,0,0));
        subjectFiveStatus.setFont(subjectFiveStatus.getFont().deriveFont(Font.BOLD));
        subjectFiveStatus.setHorizontalAlignment(JTextField.CENTER);

        subjectSixStatus = new JTextField(25);
        subjectSixStatus.setPreferredSize(new Dimension(40,50));
        subjectSixStatus.setEnabled(false);
        subjectSixStatus.setDisabledTextColor(new Color(0,0,0));
        subjectSixStatus.setFont(subjectSixStatus.getFont().deriveFont(Font.BOLD));
        subjectSixStatus.setHorizontalAlignment(JTextField.CENTER);

        subjectSevenStatus = new JTextField(25);
        subjectSevenStatus.setPreferredSize(new Dimension(40,50));
        subjectSevenStatus.setEnabled(false);
        subjectSevenStatus.setDisabledTextColor(new Color(0,0,0));
        subjectSevenStatus.setFont(subjectSevenStatus.getFont().deriveFont(Font.BOLD));
        subjectSevenStatus.setHorizontalAlignment(JTextField.CENTER);

        subjectEightStatus = new JTextField(25);
        subjectEightStatus.setPreferredSize(new Dimension(40,50));
        subjectEightStatus.setEnabled(false);
        subjectEightStatus.setDisabledTextColor(new Color(0,0,0));
        subjectEightStatus.setFont(subjectEightStatus.getFont().deriveFont(Font.BOLD));
        subjectEightStatus.setHorizontalAlignment(JTextField.CENTER);

        subjectOneModuleName = new JTextField(25);
        subjectOneModuleName.setPreferredSize(new Dimension(40,50));
        subjectOneModuleName.setEnabled(false);
        subjectOneModuleName.setDisabledTextColor(new Color(0,0,0));
        subjectOneModuleName.setFont(subjectOneModuleName.getFont().deriveFont(Font.BOLD));
        subjectOneModuleName.setHorizontalAlignment(JTextField.CENTER);

        subjectTwoModuleName = new JTextField(25);
        subjectTwoModuleName.setPreferredSize(new Dimension(40,50));
        subjectTwoModuleName.setEnabled(false);
        subjectTwoModuleName.setDisabledTextColor(new Color(0,0,0));
        subjectTwoModuleName.setFont(subjectTwoModuleName.getFont().deriveFont(Font.BOLD));
        subjectTwoModuleName.setHorizontalAlignment(JTextField.CENTER);

        subjectThreeModuleName = new JTextField(25);
        subjectThreeModuleName.setPreferredSize(new Dimension(40,50));
        subjectThreeModuleName.setEnabled(false);
        subjectThreeModuleName.setDisabledTextColor(new Color(0,0,0));
        subjectThreeModuleName.setFont(subjectThreeModuleName.getFont().deriveFont(Font.BOLD));
        subjectThreeModuleName.setHorizontalAlignment(JTextField.CENTER);

        subjectFourModuleName = new JTextField(25);
        subjectFourModuleName.setPreferredSize(new Dimension(40,50));
        subjectFourModuleName.setEnabled(false);
        subjectFourModuleName.setDisabledTextColor(new Color(0,0,0));
        subjectFourModuleName.setFont(subjectFourModuleName.getFont().deriveFont(Font.BOLD));
        subjectFourModuleName.setHorizontalAlignment(JTextField.CENTER);

        subjectFiveModuleName = new JTextField(25);
        subjectFiveModuleName.setPreferredSize(new Dimension(40,50));
        subjectFiveModuleName.setEnabled(false);
        subjectFiveModuleName.setDisabledTextColor(new Color(0,0,0));
        subjectFiveModuleName.setFont(subjectFiveModuleName.getFont().deriveFont(Font.BOLD));
        subjectFiveModuleName.setHorizontalAlignment(JTextField.CENTER);

        subjectSixModuleName = new JTextField(25);
        subjectSixModuleName.setPreferredSize(new Dimension(40,50));
        subjectSixModuleName.setEnabled(false);
        subjectSixModuleName.setDisabledTextColor(new Color(0,0,0));
        subjectSixModuleName.setFont(subjectSixModuleName.getFont().deriveFont(Font.BOLD));
        subjectSixModuleName.setHorizontalAlignment(JTextField.CENTER);

        subjectSevenModuleName = new JTextField(25);
        subjectSevenModuleName.setPreferredSize(new Dimension(40,50));
        subjectSevenModuleName.setEnabled(false);
        subjectSevenModuleName.setDisabledTextColor(new Color(0,0,0));
        subjectSevenModuleName.setFont(subjectSevenModuleName.getFont().deriveFont(Font.BOLD));
        subjectSevenModuleName.setHorizontalAlignment(JTextField.CENTER);

        subjectEightModuleName = new JTextField(25);
        subjectEightModuleName.setPreferredSize(new Dimension(40,50));
        subjectEightModuleName.setEnabled(false);
        subjectEightModuleName.setDisabledTextColor(new Color(0,0,0));
        subjectEightModuleName.setFont(subjectEightModuleName.getFont().deriveFont(Font.BOLD));
        subjectEightModuleName.setHorizontalAlignment(JTextField.CENTER);

        moduleHeader = new JTextField(25);
        moduleHeader.setPreferredSize(new Dimension(40,50));
        moduleHeader.setEnabled(false);
        moduleHeader.setDisabledTextColor(new Color(0,0,0));
        moduleHeader.setFont(moduleHeader.getFont().deriveFont(Font.BOLD));
        moduleHeader.setHorizontalAlignment(JTextField.CENTER);
        moduleHeader.setText("Module Name(Semester)");

        obtainedMarksHeader = new JTextField(25);
        obtainedMarksHeader.setPreferredSize(new Dimension(40,50));
        obtainedMarksHeader.setEnabled(false);
        obtainedMarksHeader.setDisabledTextColor(new Color(0,0,0));
        obtainedMarksHeader.setFont(obtainedMarksHeader.getFont().deriveFont(Font.BOLD));
        obtainedMarksHeader.setHorizontalAlignment(JTextField.CENTER);
        obtainedMarksHeader.setText("Obtained Marks");

        fullMarksHeader = new JTextField(25);
        fullMarksHeader.setPreferredSize(new Dimension(40,50));
        fullMarksHeader.setEnabled(false);
        fullMarksHeader.setDisabledTextColor(new Color(0,0,0));
        fullMarksHeader.setFont(fullMarksHeader.getFont().deriveFont(Font.BOLD));
        fullMarksHeader.setHorizontalAlignment(JTextField.CENTER);
        fullMarksHeader.setText("Full Marks");

        passMarksHeader = new JTextField(25);
        passMarksHeader.setPreferredSize(new Dimension(40,50));
        passMarksHeader.setEnabled(false);
        passMarksHeader.setDisabledTextColor(new Color(0,0,0));
        passMarksHeader.setFont(passMarksHeader.getFont().deriveFont(Font.BOLD));
        passMarksHeader.setHorizontalAlignment(JTextField.CENTER);
        passMarksHeader.setText("Pass Marks");

        gradeHeader = new JTextField(25);
        gradeHeader.setPreferredSize(new Dimension(40,50));
        gradeHeader.setEnabled(false);
        gradeHeader.setDisabledTextColor(new Color(0,0,0));
        gradeHeader.setFont(gradeHeader.getFont().deriveFont(Font.BOLD));
        gradeHeader.setHorizontalAlignment(JTextField.CENTER);
        gradeHeader.setText("Grade");

        statusHeader = new JTextField(25);
        statusHeader.setPreferredSize(new Dimension(40,50));
        statusHeader.setEnabled(false);
        statusHeader.setDisabledTextColor(new Color(0,0,0));
        statusHeader.setFont(statusHeader.getFont().deriveFont(Font.BOLD));
        statusHeader.setHorizontalAlignment(JTextField.CENTER);
        statusHeader.setText("Remarks");

        total = new JTextField(25);
        total.setPreferredSize(new Dimension(40,50));
        total.setEnabled(false);
        total.setDisabledTextColor(new Color(0,0,0));
        total.setFont(total.getFont().deriveFont(Font.BOLD));
        total.setHorizontalAlignment(JTextField.CENTER);
        total.setText("Total");

        totalObtainedMarks = new JTextField(25);
        totalObtainedMarks.setPreferredSize(new Dimension(40,50));
        totalObtainedMarks.setEnabled(false);
        totalObtainedMarks.setDisabledTextColor(new Color(0,0,0));
        totalObtainedMarks.setFont(totalObtainedMarks.getFont().deriveFont(Font.BOLD));
        totalObtainedMarks.setHorizontalAlignment(JTextField.CENTER);

        totalFullMarks = new JTextField(25);
        totalFullMarks.setPreferredSize(new Dimension(40,50));
        totalFullMarks.setEnabled(false);
        totalFullMarks.setDisabledTextColor(new Color(0,0,0));
        totalFullMarks.setFont(totalFullMarks.getFont().deriveFont(Font.BOLD));
        totalFullMarks.setHorizontalAlignment(JTextField.CENTER);

        totalPassMarks = new JTextField(25);
        totalPassMarks.setPreferredSize(new Dimension(40,50));
        totalPassMarks.setEnabled(false);
        totalPassMarks.setDisabledTextColor(new Color(0,0,0));
        totalPassMarks.setFont(totalPassMarks.getFont().deriveFont(Font.BOLD));
        totalPassMarks.setHorizontalAlignment(JTextField.CENTER);

        totalGrade = new JTextField(25);
        totalGrade.setPreferredSize(new Dimension(40,50));
        totalGrade.setEnabled(false);
        totalGrade.setDisabledTextColor(new Color(0,0,0));
        totalGrade.setFont(totalGrade.getFont().deriveFont(Font.BOLD));
        totalGrade.setHorizontalAlignment(JTextField.CENTER);

        totalStatus = new JTextField(25);
        totalStatus.setPreferredSize(new Dimension(40,50));
        totalStatus.setEnabled(false);
        totalStatus.setDisabledTextColor(new Color(0,0,0));
        totalStatus.setFont(totalStatus.getFont().deriveFont(Font.BOLD));
        totalStatus.setHorizontalAlignment(JTextField.CENTER);

        title = new JTextPane();
        title.setContentType("text/html");
        title.setEnabled(false);
        title.setDisabledTextColor(new Color(0,0,0));
        title.setFont(title.getFont().deriveFont(Font.BOLD));
        title.setText("<HTML><CENTER><H1>Student Progress Report Card<BR>Herald College Kathmandu</H1></CENTER></HTML>");

        compulsory = new JTextPane();
        compulsory.setContentType("text/html");
        compulsory.setPreferredSize(new Dimension(40,50));
        compulsory.setEnabled(false);
        compulsory.setDisabledTextColor(new Color(0,0,0));
        compulsory.setFont(compulsory.getFont().deriveFont(Font.BOLD));
        compulsory.setText("<HTML><BODY><CENTER><H1>Compulsory Modules</H1></BODY></CENTER><HTML>");

        elective = new JTextPane();
        elective.setContentType("text/html");
        elective.setPreferredSize(new Dimension(40,50));
        elective.setEnabled(false);
        elective.setDisabledTextColor(new Color(0,0,0));
        elective.setFont(elective.getFont().deriveFont(Font.BOLD));
        elective.setText("<HTML><BODY><CENTER><H1>Elective Modules</H1></CENTER></BODY><HTML>");

        back = new JButton("Back");
        back.setPreferredSize(new Dimension(40,35));

        userTable = new UserTable();
        studentCourseTable = new StudentCourseTable();
        instructorPanelTable = new InstructorPanelTable();
        courseTable = new courseTable();

        courseName();
        refreshStudentEmail();
        refreshStudentName();
        refreshMarkSheet();
    }

    private void courseName(){
        try {
            ResultSet resultSet = courseTable.getCourseDetails();
            courseName.removeAllItems();
            courseName.addItem("Select Course Names");
            while (resultSet.next()) {
                courseName.addItem(resultSet.getString("courseName"));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void studentEmail() {
        if (courseName.getItemCount() == 0){
        } else {
            try {
                String course = courseName.getSelectedItem().toString();
                String lvl = studentLevel.getSelectedItem().toString();
                ResultSet resultSet = instructorPanelTable.getStudents(course,lvl);
                studentEmail.removeAllItems();
                studentEmail.addItem("Select Student");
                while (resultSet.next()) {
                    studentEmail.addItem(resultSet.getString("email"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void studentName() {
        if (studentEmail.getItemCount() == 0){
        }else {
            try {
                if (studentEmail.getSelectedItem().toString() == "Select Student"){
                    studentName.setText("");
                } else {
                    String course = courseName.getSelectedItem().toString();
                    String lvl = studentLevel.getSelectedItem().toString();
                    String mail = studentEmail.getSelectedItem().toString().trim();
                    ResultSet resultSet = instructorPanelTable.getStudentsDetails(mail,course, lvl);
                    while (resultSet.next()) {
                        studentName.setText(resultSet.getString("firstName") + " " + resultSet.getString("lastName"));
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Coding error.Please wait while it is being fixed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void refreshStudentEmail(){
        courseName.addActionListener(e -> {
            studentEmail();
        });

        studentLevel.addActionListener(e -> {
            studentEmail();
            if (studentLevel.getSelectedItem().toString().equals("6")) {
                elective.setText("<HTML><BODY><CENTER><H1>Elective Modules</H1></CENTER></BODY><HTML>");
            } else {
                elective.setText("<HTML><BODY><CENTER><H1></H1></CENTER></BODY><HTML>");
            }
        });
    }

    private void refreshStudentName(){
        studentEmail.addActionListener(e -> {
            studentName();
        });
    }
    
    private void clear(){
        clearCompulsory(subjectOneModuleName, subjectTwoModuleName, subjectThreeModuleName, subjectFourModuleName, subjectOneObtainedMarks, subjectTwoObtainedMarks, subjectThreeObtainedMarks, subjectFourObtainedMarks, subjectOnePassMarks, subjectTwoPassMarks, subjectThreePassMarks, subjectFourPassMarks);

        clearCompulsory(subjectOneFullMarks, subjectTwoFullMarks, subjectThreeFullMarks, subjectFourFullMarks, subjectOneGrade, subjectTwoGrade, subjectThreeGrade, subjectFourGrade, subjectOneStatus, subjectTwoStatus, subjectThreeStatus, subjectFourStatus);
    }

    private void clearCompulsory(JTextField subjectOneModuleName, JTextField subjectTwoModuleName, JTextField subjectThreeModuleName, JTextField subjectFourModuleName, JTextField subjectOneObtainedMarks, JTextField subjectTwoObtainedMarks, JTextField subjectThreeObtainedMarks, JTextField subjectFourObtainedMarks, JTextField subjectOnePassMarks, JTextField subjectTwoPassMarks, JTextField subjectThreePassMarks, JTextField subjectFourPassMarks) {
        clearElective(subjectOneModuleName, subjectTwoModuleName, subjectThreeModuleName, subjectFourModuleName, subjectOneObtainedMarks, subjectTwoObtainedMarks, subjectThreeObtainedMarks, subjectFourObtainedMarks, subjectOnePassMarks, subjectTwoPassMarks, subjectThreePassMarks, subjectFourPassMarks);
    }

    private void clear1(){
        clearElective(subjectFiveModuleName, subjectSixModuleName, subjectSevenModuleName, subjectEightModuleName, subjectFiveObtainedMarks, subjectSixObtainedMarks, subjectSevenObtainedMarks, subjectEightObtainedMarks, subjectFivePassMarks, subjectSixPassMarks, subjectSevenPassMarks, subjectEightPassMarks);

        clearElective(subjectFiveFullMarks, subjectSixFullMarks, subjectSevenFullMarks, subjectEightFullMarks, subjectFiveGrade, subjectSixGrade, subjectSevenGrade, subjectEightGrade, subjectFiveStatus, subjectSixStatus, subjectSevenStatus, subjectEightStatus);


    }

    private void clearElective(JTextField subjectFiveModuleName, JTextField subjectSixModuleName, JTextField subjectSevenModuleName, JTextField subjectEightModuleName, JTextField subjectFiveObtainedMarks, JTextField subjectSixObtainedMarks, JTextField subjectSevenObtainedMarks, JTextField subjectEightObtainedMarks, JTextField subjectFivePassMarks, JTextField subjectSixPassMarks, JTextField subjectSevenPassMarks, JTextField subjectEightPassMarks) {
        subjectFiveModuleName.setText("");
        subjectSixModuleName.setText("");
        subjectSevenModuleName.setText("");
        subjectEightModuleName.setText("");

        subjectFiveObtainedMarks.setText("");
        subjectSixObtainedMarks.setText("");
        subjectSevenObtainedMarks.setText("");
        subjectEightObtainedMarks.setText("");

        subjectFivePassMarks.setText("");
        subjectSixPassMarks.setText("");
        subjectSevenPassMarks.setText("");
        subjectEightPassMarks.setText("");
    }

    public void studentLevelCompulsoryModuleNames() {
        if (studentEmail.getItemCount() == 0){
        } else {
            clear();
            clear1();
            try {
                String email = studentEmail.getSelectedItem().toString().trim();
                ResultSet resultSet = studentCourseTable.getCompulsoryMarksDetailsForResult(email);
                while (resultSet.next()) {
                    subjectOneModuleName.setText(resultSet.getString("moduleName") + "(" + resultSet.getString("semester") + ")");
                    subjectOneObtainedMarks.setText(resultSet.getString("obtainedMarks"));
                    subjectOnePassMarks.setText(resultSet.getString("passMarks"));
                    subjectOneFullMarks.setText(resultSet.getString("fullMarks"));
                    subjectOneGrade.setText(resultSet.getString("grade"));
                    subjectOneStatus.setText(resultSet.getString("status"));
                    totalObtained += Integer.parseInt(subjectOneObtainedMarks.getText().trim());
                    totalFM += Integer.parseInt(subjectOneFullMarks.getText().trim());
                    totalPM += Integer.parseInt(subjectOnePassMarks.getText().trim());
                    if (resultSet.next()) {
                        subjectTwoModuleName.setText(resultSet.getString("moduleName") + "(" + resultSet.getString("semester") + ")");
                        subjectTwoObtainedMarks.setText(resultSet.getString("obtainedMarks"));
                        subjectTwoPassMarks.setText(resultSet.getString("passMarks"));
                        subjectTwoFullMarks.setText(resultSet.getString("fullMarks"));
                        subjectTwoGrade.setText(resultSet.getString("grade"));
                        subjectTwoStatus.setText(resultSet.getString("status"));
                        totalObtained += Integer.parseInt(subjectTwoObtainedMarks.getText().trim());
                        totalFM += Integer.parseInt(subjectTwoFullMarks.getText().trim());
                        totalPM += Integer.parseInt(subjectTwoPassMarks.getText().trim());
                    }
                    if (resultSet.next()) {
                        subjectThreeModuleName.setText(resultSet.getString("moduleName") + "(" + resultSet.getString("semester") + ")");
                        subjectThreeObtainedMarks.setText(resultSet.getString("obtainedMarks"));
                        subjectThreePassMarks.setText(resultSet.getString("passMarks"));
                        subjectThreeFullMarks.setText(resultSet.getString("fullMarks"));
                        subjectThreeGrade.setText(resultSet.getString("grade"));
                        subjectThreeStatus.setText(resultSet.getString("status"));
                        totalObtained += Integer.parseInt(subjectThreeObtainedMarks.getText().trim());
                        totalFM += Integer.parseInt(subjectThreeFullMarks.getText().trim());
                        totalPM += Integer.parseInt(subjectThreePassMarks.getText().trim());
                    }
                    if (resultSet.next()) {
                        subjectFourModuleName.setText(resultSet.getString("moduleName") + "(" + resultSet.getString("semester") + ")");
                        subjectFourObtainedMarks.setText(resultSet.getString("obtainedMarks"));
                        subjectFourPassMarks.setText(resultSet.getString("passMarks"));
                        subjectFourFullMarks.setText(resultSet.getString("fullMarks"));
                        subjectFourGrade.setText(resultSet.getString("grade"));
                        subjectFourStatus.setText(resultSet.getString("status"));
                        totalObtained += Integer.parseInt(subjectFourObtainedMarks.getText().trim());
                        totalFM += Integer.parseInt(subjectFourFullMarks.getText().trim());
                        totalPM += Integer.parseInt(subjectFourPassMarks.getText().trim());
                    }
                    if (resultSet.next()) {
                        subjectFiveModuleName.setText(resultSet.getString("moduleName") + "(" + resultSet.getString("semester") + ")");
                        subjectFiveObtainedMarks.setText(resultSet.getString("obtainedMarks"));
                        subjectFivePassMarks.setText(resultSet.getString("passMarks"));
                        subjectFiveFullMarks.setText(resultSet.getString("fullMarks"));
                        subjectFiveGrade.setText(resultSet.getString("grade"));
                        subjectFiveStatus.setText(resultSet.getString("status"));
                        totalObtained += Integer.parseInt(subjectFourObtainedMarks.getText().trim());
                        totalFM += Integer.parseInt(subjectFourFullMarks.getText().trim());
                        totalPM += Integer.parseInt(subjectFourPassMarks.getText().trim());
                    }
                    if (resultSet.next()) {
                        subjectSixModuleName.setText(resultSet.getString("moduleName") + "(" + resultSet.getString("semester") + ")");
                        subjectSixObtainedMarks.setText(resultSet.getString("obtainedMarks"));
                        subjectSixPassMarks.setText(resultSet.getString("passMarks"));
                        subjectSixFullMarks.setText(resultSet.getString("fullMarks"));
                        subjectSixGrade.setText(resultSet.getString("grade"));
                        subjectSixStatus.setText(resultSet.getString("status"));
                        totalObtained += Integer.parseInt(subjectSixObtainedMarks.getText().trim());
                        totalFM += Integer.parseInt(subjectSixFullMarks.getText().trim());
                        totalPM += Integer.parseInt(subjectSixPassMarks.getText().trim());
                    }
                    if (resultSet.next()) {
                        subjectSevenModuleName.setText(resultSet.getString("moduleName") + "(" + resultSet.getString("semester") + ")");
                        subjectSevenObtainedMarks.setText(resultSet.getString("obtainedMarks"));
                        subjectSevenPassMarks.setText(resultSet.getString("passMarks"));
                        subjectSevenFullMarks.setText(resultSet.getString("fullMarks"));
                        subjectSevenGrade.setText(resultSet.getString("grade"));
                        subjectSevenStatus.setText(resultSet.getString("status"));
                        totalObtained += Integer.parseInt(subjectSevenObtainedMarks.getText().trim());
                        totalFM += Integer.parseInt(subjectSevenFullMarks.getText().trim());
                        totalPM += Integer.parseInt(subjectSevenPassMarks.getText().trim());
                    }
                    if (resultSet.next()) {
                        subjectEightModuleName.setText(resultSet.getString("moduleName") + "(" + resultSet.getString("semester") + ")");
                        subjectEightObtainedMarks.setText(resultSet.getString("obtainedMarks"));
                        subjectEightPassMarks.setText(resultSet.getString("passMarks"));
                        subjectEightFullMarks.setText(resultSet.getString("fullMarks"));
                        subjectEightGrade.setText(resultSet.getString("grade"));
                        subjectEightStatus.setText(resultSet.getString("status"));
                        totalObtained += Integer.parseInt(subjectEightObtainedMarks.getText().trim());
                        totalFM += Integer.parseInt(subjectEightFullMarks.getText().trim());
                        totalPM += Integer.parseInt(subjectEightPassMarks.getText().trim());
                    }
                }
                String level = studentLevel.getSelectedItem().toString().trim();
                if (!level.equals("6")) {
                    totalObtainedMarks.setText(String.valueOf(totalObtained));
                    totalFullMarks.setText(String.valueOf(totalFM));
                    totalPassMarks.setText(String.valueOf(totalPM));
                    if (totalObtained == 0 || totalFM == 0 || totalPM == 0) {

                    } else {
                        totalObtainedMarksInPercentage = (totalObtained * 100) / totalFM;
                    }
                    if (!totalFullMarks.getText().trim().equals("800")) {
                        totalGrade.setText("Marks of all 8 modules must be given");
                        totalStatus.setText("Marks of all 8 modules must be given");
                    } else if (totalObtainedMarksInPercentage > 70) {
                        totalGrade.setText("A");
                        totalStatus.setText("Pass");
                    } else if (totalObtainedMarksInPercentage <= 70 && totalObtainedMarksInPercentage >= 60) {
                        totalGrade.setText("B");
                        totalStatus.setText("Pass");
                    } else if (totalObtainedMarksInPercentage < 60 && totalObtainedMarksInPercentage >= 50) {
                        totalGrade.setText("C");
                        totalStatus.setText("Pass");
                    } else if (totalObtainedMarksInPercentage < 50 && totalObtainedMarksInPercentage >= 43) {
                        totalGrade.setText("D");
                        totalStatus.setText("Pass");
                    } else if (totalObtainedMarksInPercentage < 43 && totalObtainedMarksInPercentage >= 40) {
                        totalGrade.setText("E");
                        totalStatus.setText("Pass");
                    } else {
                        totalGrade.setText("F");
                        totalStatus.setText("Fail");
                    }
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void studentLevelElectiveModuleNames() {
        if (studentEmail.getItemCount() == 0){
        } else {
            String level = studentLevel.getSelectedItem().toString().trim();
            if (level.equals("6")) {
                clear1();
                elective.setText("<HTML><BODY><CENTER><H1>Elective Modules</H1></CENTER></BODY><HTML>");

                try {
                    String email = studentEmail.getSelectedItem().toString().trim();
                    ResultSet resultSet = studentCourseTable.getElectiveMarksDetailsForResult(email);
                    while (resultSet.next()){
                        subjectFiveModuleName.setText(resultSet.getString("moduleName") + "(" + resultSet.getString("semester") + ")");
                        subjectFiveObtainedMarks.setText(resultSet.getString("obtainedMarks"));
                        subjectFivePassMarks.setText(resultSet.getString("passMarks"));
                        subjectFiveFullMarks.setText(resultSet.getString("fullMarks"));
                        subjectFiveGrade.setText(resultSet.getString("grade"));
                        subjectFiveStatus.setText(resultSet.getString("status"));
                        totalObtained += Integer.parseInt(subjectFiveObtainedMarks.getText().trim());
                        totalFM +=  Integer.parseInt(subjectFiveFullMarks.getText().trim());
                        totalPM +=  Integer.parseInt(subjectFivePassMarks.getText().trim());

                        if (resultSet.next()) {
                            subjectSixModuleName.setText(resultSet.getString("moduleName") + "(" + resultSet.getString("semester") + ")");
                            subjectSixObtainedMarks.setText(resultSet.getString("obtainedMarks"));
                            subjectSixPassMarks.setText(resultSet.getString("passMarks"));
                            subjectSixFullMarks.setText(resultSet.getString("fullMarks"));
                            subjectSixGrade.setText(resultSet.getString("grade"));
                            subjectSixStatus.setText(resultSet.getString("status"));
                            totalObtained += Integer.parseInt(subjectSixObtainedMarks.getText().trim());
                            totalFM +=  Integer.parseInt(subjectSixFullMarks.getText().trim());
                            totalPM +=  Integer.parseInt(subjectSixPassMarks.getText().trim());
                        }
                        if (resultSet.next()) {
                            subjectSevenModuleName.setText(resultSet.getString("moduleName") + "(" + resultSet.getString("semester") + ")");
                            subjectSevenObtainedMarks.setText(resultSet.getString("obtainedMarks"));
                            subjectSevenPassMarks.setText(resultSet.getString("passMarks"));
                            subjectSevenFullMarks.setText(resultSet.getString("fullMarks"));
                            subjectSevenGrade.setText(resultSet.getString("grade"));
                            subjectSevenStatus.setText(resultSet.getString("status"));
                            totalObtained += Integer.parseInt(subjectSevenObtainedMarks.getText().trim());
                            totalFM +=  Integer.parseInt(subjectSevenFullMarks.getText().trim());
                            totalPM +=  Integer.parseInt(subjectSevenPassMarks.getText().trim());
                        }
                        if (resultSet.next()) {
                            subjectEightModuleName.setText(resultSet.getString("moduleName") + "(" + resultSet.getString("semester") + ")");
                            subjectEightObtainedMarks.setText(resultSet.getString("obtainedMarks"));
                            subjectEightPassMarks.setText(resultSet.getString("passMarks"));
                            subjectEightFullMarks.setText(resultSet.getString("fullMarks"));
                            subjectEightGrade.setText(resultSet.getString("grade"));
                            subjectEightStatus.setText(resultSet.getString("status"));
                            totalObtained += Integer.parseInt(subjectEightObtainedMarks.getText().trim());
                            totalFM +=  Integer.parseInt(subjectEightFullMarks.getText().trim());
                            totalPM +=  Integer.parseInt(subjectEightPassMarks.getText().trim());
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                totalObtainedMarks.setText(String.valueOf(totalObtained));
                totalFullMarks.setText(String.valueOf(totalFM));
                totalPassMarks.setText(String.valueOf(totalPM));
                if (totalObtained == 0 || totalFM == 0 || totalPM == 0) {

                } else {
                    totalObtainedMarksInPercentage = (totalObtained * 100) / totalFM;
                }
                if (totalFM != 800) {
                    totalGrade.setText("Marks of all 8 modules must be given");
                    totalStatus.setText("Marks of all 8 modules must be given");
                } else if (totalObtainedMarksInPercentage > 70) {
                    totalGrade.setText("A");
                    totalStatus.setText("Pass");
                } else if (totalObtainedMarksInPercentage <= 70 && totalObtainedMarksInPercentage >= 60) {
                    totalGrade.setText("B");
                    totalStatus.setText("Pass");
                } else if (totalObtainedMarksInPercentage < 60 && totalObtainedMarksInPercentage >= 50) {
                    totalGrade.setText("C");
                    totalStatus.setText("Pass");
                } else if (totalObtainedMarksInPercentage < 50 && totalObtainedMarksInPercentage >= 43) {
                    totalGrade.setText("D");
                    totalStatus.setText("Pass");
                } else if (totalObtainedMarksInPercentage < 43 && totalObtainedMarksInPercentage >= 40) {
                    totalGrade.setText("E");
                    totalStatus.setText("Pass");
                } else {
                    totalGrade.setText("F");
                    totalStatus.setText("Fail");
                }
            } else {
                elective.setText("<HTML><BODY><CENTER><H1></H1></CENTER></BODY><HTML>");
            }
        }
    }

    public void refreshMarkSheet(){
        studentEmail.addActionListener(e -> {
            studentLevelCompulsoryModuleNames();
            studentLevelElectiveModuleNames();
            totalObtained = 0;
            totalFM = 0;
            totalPM = 0;
        });
    }

    private JPanel StudentPanelCoursesStudentDetailsPanel(){
        JPanel studentPanelCoursesStudentDetails = new JPanel();
        studentPanelCoursesStudentDetails.setLayout(new GridBagLayout());
        studentPanelCoursesStudentDetails.setBackground(Color.decode("#D6D9DF"));

        details = new GridBagConstraints();
        details.fill = GridBagConstraints.BOTH;
        layout.insets = new Insets(0,0,0,0);

        details.gridx = 0;
        details.gridy = 0;
        details.gridwidth = 6;
        add(title,details);

        details.gridx = 0;
        details.gridy = 1;
        details.gridwidth = 1;
        studentPanelCoursesStudentDetails.add(enrolledCourseLabel,details);

        details.gridx = 1;
        details.gridy = 1;
        studentPanelCoursesStudentDetails.add(courseName,details);

        details.gridx= 2;
        details.gridy= 1;
        studentPanelCoursesStudentDetails.add(studentLevelLabel,details);

        details.gridx=3;
        details.gridy=1;
        studentPanelCoursesStudentDetails.add(studentLevel,details);

        details.gridx= 4;
        details.gridy= 1;
        studentPanelCoursesStudentDetails.add(studentEmailLabel,details);

        details.gridx= 5;
        details.gridy= 1;
        studentPanelCoursesStudentDetails.add(studentEmail,details);


        details.gridx = 0;
        details.gridy = 2;
        details.gridwidth = 4;
        studentPanelCoursesStudentDetails.add(back,details);

        details.gridx= 4;
        details.gridy= 2;
        details.gridwidth = 1;
        studentPanelCoursesStudentDetails.add(studentLabel,details);

        details.gridx= 5;
        details.gridy= 2;
        studentPanelCoursesStudentDetails.add(studentName,details);


        return studentPanelCoursesStudentDetails;
    }

    private JPanel studentPanelStudentReportCard(){
        JPanel studentPanelReportCard = new JPanel();
        studentPanelReportCard.setLayout(new GridBagLayout());
        studentPanelReportCard.setBackground(Color.decode("#D6D9DF"));

        markSheet = new GridBagConstraints();
        markSheet.fill = GridBagConstraints.HORIZONTAL;
        
        markSheet.gridx = 0;
        markSheet.gridy = 0;
        studentPanelReportCard.add(moduleHeader,markSheet);

        markSheet.gridx = 1;
        markSheet.gridy = 0;
        studentPanelReportCard.add(obtainedMarksHeader,markSheet);

        markSheet.gridx = 2;
        markSheet.gridy = 0;
        studentPanelReportCard.add(fullMarksHeader,markSheet);

        markSheet.gridx = 3;
        markSheet.gridy = 0;
        studentPanelReportCard.add(passMarksHeader,markSheet);

        markSheet.gridx = 4;
        markSheet.gridy = 0;
        studentPanelReportCard.add(gradeHeader,markSheet);

        markSheet.gridx = 5;
        markSheet.gridy = 0;
        studentPanelReportCard.add(statusHeader,markSheet);

        markSheet.gridx = 0;
        markSheet.gridy = 1;
        markSheet.gridwidth = 6;
        studentPanelReportCard.add(compulsory,markSheet);

        markSheet.gridx = 0;
        markSheet.gridy = 2;
        markSheet.gridwidth = 1;
        studentPanelReportCard.add(subjectOneModuleName,markSheet);

        markSheet.gridx = 1;
        markSheet.gridy = 2;
        studentPanelReportCard.add(subjectOneObtainedMarks,markSheet);

        markSheet.gridx = 2;
        markSheet.gridy = 2;
        studentPanelReportCard.add(subjectOneFullMarks,markSheet);

        markSheet.gridx = 3;
        markSheet.gridy = 2;
        studentPanelReportCard.add(subjectOnePassMarks,markSheet);

        markSheet.gridx = 4;
        markSheet.gridy = 2;
        studentPanelReportCard.add(subjectOneGrade,markSheet);

        markSheet.gridx = 5;
        markSheet.gridy = 2;
        studentPanelReportCard.add(subjectOneStatus,markSheet);

        markSheet.gridx = 0;
        markSheet.gridy = 3;
        studentPanelReportCard.add(subjectTwoModuleName,markSheet);

        markSheet.gridx = 1;
        markSheet.gridy = 3;
        studentPanelReportCard.add(subjectTwoObtainedMarks,markSheet);

        markSheet.gridx = 2;
        markSheet.gridy = 3;
        studentPanelReportCard.add(subjectTwoFullMarks,markSheet);

        markSheet.gridx = 3;
        markSheet.gridy = 3;
        studentPanelReportCard.add(subjectTwoPassMarks,markSheet);

        markSheet.gridx = 4;
        markSheet.gridy = 3;
        studentPanelReportCard.add(subjectTwoGrade,markSheet);

        markSheet.gridx = 5;
        markSheet.gridy = 3;
        studentPanelReportCard.add(subjectTwoStatus,markSheet);

        markSheet.gridx = 0;
        markSheet.gridy = 4;
        studentPanelReportCard.add(subjectThreeModuleName,markSheet);

        markSheet.gridx = 1;
        markSheet.gridy = 4;
        studentPanelReportCard.add(subjectThreeObtainedMarks,markSheet);

        markSheet.gridx = 2;
        markSheet.gridy = 4;
        studentPanelReportCard.add(subjectThreeFullMarks,markSheet);

        markSheet.gridx = 3;
        markSheet.gridy = 4;
        studentPanelReportCard.add(subjectThreePassMarks,markSheet);

        markSheet.gridx = 4;
        markSheet.gridy = 4;
        studentPanelReportCard.add(subjectThreeGrade,markSheet);

        markSheet.gridx = 5;
        markSheet.gridy = 4;
        studentPanelReportCard.add(subjectThreeStatus,markSheet);

        markSheet.gridx = 0;
        markSheet.gridy = 5;
        studentPanelReportCard.add(subjectFourModuleName,markSheet);

        markSheet.gridx = 1;
        markSheet.gridy = 5;
        studentPanelReportCard.add(subjectFourObtainedMarks,markSheet);

        markSheet.gridx = 2;
        markSheet.gridy = 5;
        studentPanelReportCard.add(subjectFourFullMarks,markSheet);

        markSheet.gridx = 3;
        markSheet.gridy = 5;
        studentPanelReportCard.add(subjectFourPassMarks,markSheet);

        markSheet.gridx = 4;
        markSheet.gridy = 5;
        studentPanelReportCard.add(subjectFourGrade,markSheet);

        markSheet.gridx = 5;
        markSheet.gridy = 5;
        studentPanelReportCard.add(subjectFourStatus,markSheet);

        markSheet.gridx = 0;
        markSheet.gridy = 6;
        markSheet.gridwidth = 6;
        studentPanelReportCard.add(elective,markSheet);

        markSheet.gridx = 0;
        markSheet.gridy = 7;
        markSheet.gridwidth = 1;
        studentPanelReportCard.add(subjectFiveModuleName,markSheet);

        markSheet.gridx = 1;
        markSheet.gridy = 7;
        studentPanelReportCard.add(subjectFiveObtainedMarks,markSheet);

        markSheet.gridx = 2;
        markSheet.gridy = 7;
        studentPanelReportCard.add(subjectFiveFullMarks,markSheet);

        markSheet.gridx = 3;
        markSheet.gridy = 7;
        studentPanelReportCard.add(subjectFivePassMarks,markSheet);

        markSheet.gridx = 4;
        markSheet.gridy = 7;
        studentPanelReportCard.add(subjectFiveGrade,markSheet);

        markSheet.gridx = 5;
        markSheet.gridy = 7;
        studentPanelReportCard.add(subjectFiveStatus,markSheet);

        markSheet.gridx = 0;
        markSheet.gridy = 8;
        studentPanelReportCard.add(subjectSixModuleName,markSheet);

        markSheet.gridx = 1;
        markSheet.gridy = 8;
        studentPanelReportCard.add(subjectSixObtainedMarks,markSheet);

        markSheet.gridx = 2;
        markSheet.gridy = 8;
        studentPanelReportCard.add(subjectSixFullMarks,markSheet);

        markSheet.gridx = 3;
        markSheet.gridy = 8;
        studentPanelReportCard.add(subjectSixPassMarks,markSheet);

        markSheet.gridx = 4;
        markSheet.gridy = 8;
        studentPanelReportCard.add(subjectSixGrade,markSheet);

        markSheet.gridx = 5;
        markSheet.gridy = 8;
        studentPanelReportCard.add(subjectSixStatus,markSheet);

        markSheet.gridx = 0;
        markSheet.gridy = 9;
        studentPanelReportCard.add(subjectSevenModuleName,markSheet);

        markSheet.gridx = 1;
        markSheet.gridy = 9;
        studentPanelReportCard.add(subjectSevenObtainedMarks,markSheet);

        markSheet.gridx = 2;
        markSheet.gridy = 9;
        studentPanelReportCard.add(subjectSevenFullMarks,markSheet);

        markSheet.gridx = 3;
        markSheet.gridy = 9;
        studentPanelReportCard.add(subjectSevenPassMarks,markSheet);

        markSheet.gridx = 4;
        markSheet.gridy = 9;
        studentPanelReportCard.add(subjectSevenGrade,markSheet);

        markSheet.gridx = 5;
        markSheet.gridy = 9;
        studentPanelReportCard.add(subjectSevenStatus,markSheet);

        markSheet.gridx = 0;
        markSheet.gridy = 10;
        studentPanelReportCard.add(subjectEightModuleName,markSheet);

        markSheet.gridx = 1;
        markSheet.gridy = 10;
        studentPanelReportCard.add(subjectEightObtainedMarks,markSheet);

        markSheet.gridx = 2;
        markSheet.gridy = 10;
        studentPanelReportCard.add(subjectEightFullMarks,markSheet);

        markSheet.gridx = 3;
        markSheet.gridy = 10;
        studentPanelReportCard.add(subjectEightPassMarks,markSheet);

        markSheet.gridx = 4;
        markSheet.gridy = 10;
        studentPanelReportCard.add(subjectEightGrade,markSheet);

        markSheet.gridx = 5;
        markSheet.gridy = 10;
        studentPanelReportCard.add(subjectEightStatus,markSheet);

        markSheet.gridx = 0;
        markSheet.gridy = 11;
        studentPanelReportCard.add(total,markSheet);

        markSheet.gridx = 1;
        markSheet.gridy = 11;
        studentPanelReportCard.add(totalObtainedMarks,markSheet);

        markSheet.gridx = 2;
        markSheet.gridy = 11;
        studentPanelReportCard.add(totalFullMarks,markSheet);

        markSheet.gridx = 3;
        markSheet.gridy = 11;
        studentPanelReportCard.add(totalPassMarks,markSheet);

        markSheet.gridx = 4;
        markSheet.gridy = 11;
        studentPanelReportCard.add(totalGrade,markSheet);

        markSheet.gridx = 5;
        markSheet.gridy = 11;
        studentPanelReportCard.add(totalStatus,markSheet);



        return studentPanelReportCard;
    }

    @Override
    public JPanel panelUI() {
        setLayout(new GridBagLayout());
        layout = new GridBagConstraints();
        layout.fill = GridBagConstraints.BOTH;
        this.setBackground(Color.decode("#D6D9DF"));

        layout.gridx = 0;
        layout.gridy = 1;
        add(StudentPanelCoursesStudentDetailsPanel(),layout);

        layout.gridx = 0;
        layout.gridy = 2;
        add(studentPanelStudentReportCard(),layout);

        return this;
    }
    public JButton getBack(){
        return back;
    }
    public void getCourseName(){
        courseName();
    }
}
