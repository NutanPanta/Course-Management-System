package CourseManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class StudentLoggedInCoursePanel extends JPanel implements AppLayout {
    private DefaultTableModel model;
    private JTable table;

    public StudentLoggedInCoursePanel() {
        DefaultTableModel model = new DefaultTableModel();
        String[] headers = {"First Name","Last Name","Id Number"};
        model.setColumnIdentifiers(headers);

        table = new JTable(model);

    }

    @Override
    public JPanel panelUI() {
        this.setBackground(Color.decode("#D6D9DF"));
        table.setSelectionBackground(Color.BLACK);
        table.setSelectionForeground(Color.WHITE);
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.decode("#D6D9DF"));

        JScrollPane scrollPane =new JScrollPane(table);
        add(scrollPane);

        return this;
    }


    public DefaultTableModel getModel(){
        return (DefaultTableModel) getTable().getModel();
    }

    public  JTable getTable(){
        return table;
    }
}
