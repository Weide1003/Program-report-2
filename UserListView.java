package com.view;

import com.dao.MemberDao;
import com.entity.Member;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



public class UserListView extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JTextField nameText;

    private MemberDao memberDao = new MemberDao();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserListView frame = new UserListView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public UserListView() {

        setTitle("List of members");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 337);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 39, 800, 232);
        contentPane.add(scrollPane);


        Object[] columns = { "ID","FirstName", "LastName", "State", "City", "Street","PhoneNumber", "ZipCode","Salary","WorkSchedule"};

        Object[][] data = null;
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        
        load(null);
        scrollPane.setViewportView(table);

        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setBounds(10, 10, 42, 15);
        contentPane.add(lblNewLabel);

        nameText = new JTextField();
        nameText.setBounds(44, 8, 115, 21);
        contentPane.add(nameText);
        nameText.setColumns(10);

        //查询按钮
        JButton searchBtn = new JButton("Search");
        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                load(nameText.getText());
            }
        });
        searchBtn.setBounds(169, 8, 63, 23);
        contentPane.add(searchBtn);


        JButton addBtn = new JButton("Add");
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddView view = new AddView();
                view.setVisible(true);
            }
        });
        addBtn.setBounds(365, 8, 63, 23);
        contentPane.add(addBtn);


        JButton updateBtn = new JButton("Edit");
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int row = table.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(contentPane, "Please select a record", "System prompt", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int id = Integer.valueOf(table.getValueAt(row, 0).toString());
            UpdateView view = new UpdateView(id);
                view.setVisible(true);

            }
        });
        updateBtn.setBounds(438, 8, 63, 23);


        JButton deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int row = table.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(contentPane, "Please select a record", "System prompt", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int result = JOptionPane.showConfirmDialog(contentPane, "Do you confirm deleting the member?", "System prompt",
                        JOptionPane.YES_NO_OPTION);
                if (result == 0) {
                    int id = Integer.valueOf(table.getValueAt(row, 0).toString());

                    boolean flag = memberDao.delete(id);
                    if(flag){
                        JOptionPane.showMessageDialog(contentPane, "Delete success!");
                        load(null);
                    }else{
                        JOptionPane.showMessageDialog(contentPane, "Operation failure", "System prompt", JOptionPane.WARNING_MESSAGE);

                    }
                }
                return;
            }
        });
        deleteBtn.setBounds(511, 8, 63, 23);
        contentPane.add(deleteBtn);
        contentPane.add(updateBtn);
    }

    public void load(String name){
        List<Member> list = memberDao.query(name);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
       
        if(list == null){
            JOptionPane.showMessageDialog(contentPane, "There are no members", "System prompt", JOptionPane.WARNING_MESSAGE);
            return;
        }
        for (Member item : list) {
            String[] arr = new String[10];

            arr[0] = item.getId().toString();
            arr[1] = item.getFirstName();
            arr[2] = item.getSecondName();
            arr[3] = item.getState();
            arr[4] = item.getCity();
            arr[5] =item.getStreet();
            arr[6] = item.getPhoneNumber();
            arr[7] = item.getZipCode();
            arr[8] = item.getSalary().toString();
            arr[9] = item.getWorkSchedule();
            tableModel.addRow(arr);
        }
    }
}