
package mobileService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddMobile extends JFrame implements ActionListener {

    JTextField customerName;
    JTextField customerPhone;
    JTextField mobileNo;
    JTextField problem;

    JButton save;
    JButton back;

    AddMobile() {

        setTitle("Add Mobile");

        setSize(500, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("ADD NEW MOBILE");

        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(160, 20, 250, 30);

        JLabel l1 = new JLabel("Customer Name");
        l1.setBounds(50, 80, 120, 30);

        JLabel l2 = new JLabel("Customer Phone");
        l2.setBounds(50, 125, 120, 30);

        JLabel l3 = new JLabel("Mobile Number");
        l3.setBounds(50, 170, 120, 30);

        JLabel l4 = new JLabel("Problem");
        l4.setBounds(50, 215, 120, 30);

        customerName = new JTextField();
        customerName.setBounds(180, 80, 220, 30);

        customerPhone = new JTextField();
        customerPhone.setBounds(180, 125, 220, 30);

        mobileNo = new JTextField();
        mobileNo.setBounds(180, 170, 220, 30);

        problem = new JTextField();
        problem.setBounds(180, 215, 220, 30);

        save = new JButton("SAVE");
        save.setBounds(150, 280, 100, 35);

        back = new JButton("BACK");
        back.setBounds(270, 280, 100, 35);

        save.addActionListener(this);
        back.addActionListener(this);

        add(title);

        add(l1);
        add(l2);
        add(l3);
        add(l4);

        add(customerName);
        add(customerPhone);
        add(mobileNo);
        add(problem);

        add(save);
        add(back);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        // =========================================
        // SAVE
        // =========================================

        if (e.getSource() == save) {

            String name =
                    customerName.getText().trim();

            String phone =
                    customerPhone.getText().trim();

            String mobile =
                    mobileNo.getText().trim();

            String issue =
                    problem.getText().trim();


            // =========================================
            // VALIDATION
            // =========================================

            if (name.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter customer name"
                );

                customerName.requestFocus();

                return;
            }


            if (phone.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter customer phone"
                );

                customerPhone.requestFocus();

                return;
            }


            if (mobile.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter mobile number"
                );

                mobileNo.requestFocus();

                return;
            }


            if (issue.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter the problem"
                );

                problem.requestFocus();

                return;
            }


            // =========================================
            // INSERT INTO DATABASE
            // =========================================

            String sql =
                    "INSERT INTO mobile_service " +
                    "(customer_name, customer_phone, mobile_no, problem) " +
                    "VALUES (?, ?, ?, ?)";


            try {

                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);


                ps.setString(1, name);
                ps.setString(2, phone);
                ps.setString(3, mobile);
                ps.setString(4, issue);


                ps.executeUpdate();


                JOptionPane.showMessageDialog(
                        this,
                        "Mobile Added Successfully"
                );


                ps.close();
                con.close();


                new Dashboard();

                dispose();


            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Error adding mobile:\n"
                                + ex.getMessage()
                );
            }
        }


        // =========================================
        // BACK
        // =========================================

        if (e.getSource() == back) {

            new Dashboard();

            dispose();
        }
    }
}