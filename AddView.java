package com.view;

import com.dao.MemberDao;
import com.entity.Member;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class AddView extends JFrame {


    private JPanel contentPane;

    private JTextField FirstName;

    private JTextField SecondName;
    private JTextField State;

    private JTextField City;

    private JTextField Street;

    private JTextField PhoneNumber;

    private JTextField ZipCode;

    private JTextField Salary;

    private JTextField WorkSchedule;


    private MemberDao memberDao = new MemberDao();


    public AddView() {
        setTitle("Member List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 600);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("First Name");
        lblNewLabel.setBounds(112, 100, 80, 15);
        contentPane.add(lblNewLabel);

        FirstName = new JTextField();
        FirstName.setBounds(200, 98, 160, 21);
        contentPane.add(FirstName);
        FirstName.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Last Name");
        lblNewLabel_1.setBounds(112, 140, 80, 15);
        contentPane.add(lblNewLabel_1);

        SecondName = new JTextField();
        SecondName.setBounds(200, 138, 160, 21);
        contentPane.add(SecondName);
        SecondName.setColumns(10);
//
        JLabel lblNewLabel_2 = new JLabel("State");
        lblNewLabel_2.setBounds(111, 185, 80, 15);
        contentPane.add(lblNewLabel_2);

        State = new JTextField();
        State.setBounds(200, 183, 160, 21);
        contentPane.add(State);
        State.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("City");
        lblNewLabel_3.setBounds(112, 222, 80, 15);
        contentPane.add(lblNewLabel_3);
        City = new JTextField();
        City.setBounds(200, 220, 160, 21);
        contentPane.add(City);
        City.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("Street");
        lblNewLabel_4.setBounds(112, 264, 43, 15);
        contentPane.add(lblNewLabel_4);

        Street = new JTextField();
        Street.setBounds(200, 262, 160, 21);
        contentPane.add(Street);
        Street.setColumns(10);
//
        JLabel lblNewLabel_5 = new JLabel("PhoneNumber");
        lblNewLabel_5.setBounds(112, 304, 90, 15);
        contentPane.add(lblNewLabel_5);

        PhoneNumber = new JTextField();
        PhoneNumber.setBounds(210, 302, 160, 21);
        contentPane.add(PhoneNumber);
        PhoneNumber.setColumns(10);
//
        JLabel lblNewLabel_6 = new JLabel("ZipCode");
        lblNewLabel_6.setBounds(112, 344, 80, 15);
        contentPane.add(lblNewLabel_6);

        ZipCode = new JTextField();
        ZipCode.setBounds(200, 342, 160, 21);
        contentPane.add(ZipCode);
        ZipCode.setColumns(10);
//
        JLabel lblNewLabel_7 = new JLabel("Salary");
        lblNewLabel_7.setBounds(112, 384, 80, 15);
        contentPane.add(lblNewLabel_7);

        Salary = new JTextField();
        Salary.setBounds(200, 382, 160, 21);
        contentPane.add(Salary);
        Salary.setColumns(10);

        JLabel lblNewLabel_8 = new JLabel("WorkSchedule");
        lblNewLabel_8.setBounds(112, 424, 80, 15);
        contentPane.add(lblNewLabel_8);

        WorkSchedule = new JTextField();
        WorkSchedule.setBounds(200, 420, 160, 21);
        contentPane.add(WorkSchedule);
        WorkSchedule.setColumns(10);





        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String firstNameText = FirstName.getText();
                String secondNameText = SecondName.getText();
                String stateText = State.getText();
                String city = City.getText();
                String street = Street.getText();
                String phoneNumber = PhoneNumber.getText();
                String zipCode = ZipCode.getText();
                String salary = Salary.getText();
                String workSchedule = WorkSchedule.getText();

                if(firstNameText == null || "".equals(secondNameText)){
                    JOptionPane.showMessageDialog(contentPane, "Please enter your last name", "System prompt", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(secondNameText == null || "".equals(secondNameText)){
                    JOptionPane.showMessageDialog(contentPane, "Please enter your first name", "System prompt", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(stateText == null || "".equals(stateText)){
                    JOptionPane.showMessageDialog(contentPane, "Please enter State", "System prompt", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(city == null || "".equals(city)){
                    JOptionPane.showMessageDialog(contentPane, "Please enter City", "System prompt", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(street == null || "".equals(street)){
                    JOptionPane.showMessageDialog(contentPane, "Please enter Street", "System prompt", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(phoneNumber == null || "".equals(phoneNumber) || phoneNumber.length() != 11 ){
                    JOptionPane.showMessageDialog(contentPane, "Incorrect Phone Number", "System prompt", JOptionPane.WARNING_MESSAGE);
                    return;
                }
               // int i = 0;
                try{
                   Long.parseLong(phoneNumber);
                }catch (NumberFormatException e1){
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(contentPane, "The format of the mobile phone number is incorrect", "System prompt", JOptionPane.WARNING_MESSAGE);

                }
                if(zipCode == null || "".equals(zipCode) || zipCode.length() != 7 ){
                    JOptionPane.showMessageDialog(contentPane, "Incorrect Zip Code", "System prompt", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try{
                    Integer.parseInt(zipCode);
                }catch (NumberFormatException e1){
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(contentPane, "Incorrect Zip Code", "System prompt", JOptionPane.WARNING_MESSAGE);

                }
                if(salary == null || "".equals(salary) ){
                    JOptionPane.showMessageDialog(contentPane, "Please enter your Salary", "System prompt", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Double s = 0.0;
                try{
                   s = Double.parseDouble(salary);
                }catch (NumberFormatException e1){
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(contentPane, "The salary format is incorrect", "System prompt", JOptionPane.WARNING_MESSAGE);

                }finally {

                    if(s == 0.0)
                    s = 1000.0;
                }
                if(workSchedule == null || "".equals(workSchedule)){
                    JOptionPane.showMessageDialog(contentPane, "Please enter work information", "System prompt", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Member member1 = new Member();
                member1.setFirstName(firstNameText);
                member1.setSecondName(secondNameText);
                member1.setName(member1.getFirstName()+member1.getSecondName());
                member1.setSalary(s);
                member1.setCity(city);
                member1.setZipCode(zipCode);
                member1.setPhoneNumber(phoneNumber);
                member1.setWorkSchedule(workSchedule);
                member1.setState(stateText);
                member1.setStreet(street);
                boolean flag = memberDao.save(member1);
                if(flag){
                    dispose();
                    JOptionPane.showMessageDialog(contentPane, "Add success, refresh can view!");
                }else{
                    JOptionPane.showMessageDialog(contentPane, "Operation failure", "System prompt", JOptionPane.WARNING_MESSAGE);
                }
                return;

            }
        });
        saveBtn.setBounds(151, 500, 74, 23);
        contentPane.add(saveBtn);


        JButton cancleBtn = new JButton("Cancel");
        cancleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancleBtn.setBounds(237, 500, 74, 23);
        contentPane.add(cancleBtn);
    }

}
