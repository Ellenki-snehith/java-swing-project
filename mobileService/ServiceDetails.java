package mobileService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ServiceDetails extends JFrame
        implements ActionListener {

    int id;

    JTextField customerName;
    JTextField customerPhone;
    JTextField mobileNo;
    JTextField problem;

    JTextField repair1;
    JTextField repair2;
    JTextField repair3;

    JTextField newMobileNo;
    JTextField parts;
    JTextField amount;

    JComboBox<String> status;
    JComboBox<String> payment;

    JButton update;
    JButton remove;
    JButton back;
    JButton bill;


    ServiceDetails(int id) {

        this.id = id;

        setTitle("Service Details");

        setSize(600, 700);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // =========================
        // TITLE
        // =========================

        JLabel title =
                new JLabel("SERVICE DETAILS");

        title.setFont(
                new Font("Arial", Font.BOLD, 22)
        );

        title.setBounds(
                200, 20, 250, 30
        );


        // =========================
        // CUSTOMER NAME
        // =========================

        JLabel l1 =
                new JLabel("Customer Name");

        l1.setBounds(40, 70, 130, 30);

        customerName =
                new JTextField();

        customerName.setBounds(
                180, 70, 300, 30
        );


        // =========================
        // CUSTOMER PHONE
        // =========================

        JLabel l2 =
                new JLabel("Customer Phone");

        l2.setBounds(40, 110, 130, 30);

        customerPhone =
                new JTextField();

        customerPhone.setBounds(
                180, 110, 300, 30
        );


        // =========================
        // MOBILE NUMBER
        // =========================

        JLabel l3 =
                new JLabel("Mobile Number");

        l3.setBounds(40, 150, 130, 30);

        mobileNo =
                new JTextField();

        mobileNo.setBounds(
                180, 150, 300, 30
        );


        // =========================
        // PROBLEM
        // =========================

        JLabel l4 =
                new JLabel("Problem");

        l4.setBounds(40, 190, 130, 30);

        problem =
                new JTextField();

        problem.setBounds(
                180, 190, 300, 30
        );


        // =========================
        // REPAIR 1
        // =========================

        JLabel l5 =
                new JLabel("Repair 1");

        l5.setBounds(40, 230, 130, 30);

        repair1 =
                new JTextField();

        repair1.setBounds(
                180, 230, 300, 30
        );


        // =========================
        // REPAIR 2
        // =========================

        JLabel l6 =
                new JLabel("Repair 2");

        l6.setBounds(40, 270, 130, 30);

        repair2 =
                new JTextField();

        repair2.setBounds(
                180, 270, 300, 30
        );


        // =========================
        // REPAIR 3
        // =========================

        JLabel l7 =
                new JLabel("Repair 3");

        l7.setBounds(40, 310, 130, 30);

        repair3 =
                new JTextField();

        repair3.setBounds(
                180, 310, 300, 30
        );


        // =========================
        // NEW MOBILE NUMBER
        // =========================

        JLabel l8 =
                new JLabel("New Mobile No");

        l8.setBounds(40, 350, 130, 30);

        newMobileNo =
                new JTextField();

        newMobileNo.setBounds(
                180, 350, 300, 30
        );


        // =========================
        // PARTS
        // =========================

        JLabel l9 =
                new JLabel("Parts");

        l9.setBounds(40, 390, 130, 30);

        parts =
                new JTextField();

        parts.setBounds(
                180, 390, 300, 30
        );


        // =========================
        // AMOUNT
        // =========================

        JLabel l10 =
                new JLabel("Amount");

        l10.setBounds(40, 430, 130, 30);

        amount =
                new JTextField();

        amount.setBounds(
                180, 430, 300, 30
        );


        // =========================
        // STATUS
        // =========================

        JLabel l11 =
                new JLabel("Status");

        l11.setBounds(40, 470, 130, 30);

        status =
                new JComboBox<>(
                        new String[] {
                                "UNDER PROCESS",
                                "REPAIRED",
                                "NOT REPAIRABLE"
                        }
                );

        status.setBounds(
                180, 470, 300, 30
        );


        // =========================
        // PAYMENT
        // =========================

        JLabel l12 =
                new JLabel("Payment");

        l12.setBounds(40, 510, 130, 30);

        payment =
                new JComboBox<>(
                        new String[] {
                                "PAID",
                                "NOT PAID"
                        }
                );

        payment.setBounds(
                180, 510, 300, 30
        );


        // =========================
        // BUTTONS
        // =========================

        update =
                new JButton("UPDATE");

        update.setBounds(
                50, 570, 100, 35
        );


        remove =
                new JButton("REMOVE");

        remove.setBounds(
                160, 570, 100, 35
        );


        bill =
                new JButton("BILL");

        bill.setBounds(
                270, 570, 100, 35
        );


        back =
                new JButton("BACK");

        back.setBounds(
                380, 570, 100, 35
        );


        update.addActionListener(this);
        remove.addActionListener(this);
        bill.addActionListener(this);
        back.addActionListener(this);


        // =========================
        // ADD COMPONENTS
        // =========================

        add(title);

        add(l1);
        add(customerName);

        add(l2);
        add(customerPhone);

        add(l3);
        add(mobileNo);

        add(l4);
        add(problem);

        add(l5);
        add(repair1);

        add(l6);
        add(repair2);

        add(l7);
        add(repair3);

        add(l8);
        add(newMobileNo);

        add(l9);
        add(parts);

        add(l10);
        add(amount);

        add(l11);
        add(status);

        add(l12);
        add(payment);

        add(update);
        add(remove);
        add(bill);
        add(back);


        // =========================
        // LOAD DATABASE DATA
        // =========================

        loadData();

        setVisible(true);
    }


    // ==================================================
    // READ
    // ==================================================

    void loadData() {

        String sql =
                "SELECT * FROM mobile_service WHERE id=?";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs =
                    ps.executeQuery();


            if (rs.next()) {

                customerName.setText(
                        rs.getString("customer_name")
                );

                customerPhone.setText(
                        rs.getString("customer_phone")
                );

                mobileNo.setText(
                        rs.getString("mobile_no")
                );

                problem.setText(
                        rs.getString("problem")
                );

                repair1.setText(
                        rs.getString("repair1")
                );

                repair2.setText(
                        rs.getString("repair2")
                );

                repair3.setText(
                        rs.getString("repair3")
                );

                newMobileNo.setText(
                        rs.getString("new_mobile_no")
                );

                parts.setText(
                        rs.getString("parts")
                );

                amount.setText(
                        rs.getString("amount")
                );

                status.setSelectedItem(
                        rs.getString("status")
                );

                payment.setSelectedItem(
                        rs.getString("payment")
                );
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading details:\n"
                            + e.getMessage()
            );
        }
    }


    // ==================================================
    // BUTTON ACTIONS
    // ==================================================

    @Override
    public void actionPerformed(ActionEvent e) {


        // ==================================================
        // UPDATE
        // ==================================================

        if (e.getSource() == update) {

            String name =
                    customerName.getText().trim();

            String phone =
                    customerPhone.getText().trim();

            String mobile =
                    mobileNo.getText().trim();

            String issue =
                    problem.getText().trim();

            String amountText =
                    amount.getText().trim();


            // =========================
            // VALIDATION
            // =========================

            if (name.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Customer name cannot be empty"
                );

                customerName.requestFocus();

                return;
            }


            if (phone.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Customer phone cannot be empty"
                );

                customerPhone.requestFocus();

                return;
            }


            if (!phone.matches("\\d{10}")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Customer phone must contain exactly 10 digits"
                );

                customerPhone.requestFocus();

                return;
            }


            if (mobile.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Mobile number cannot be empty"
                );

                mobileNo.requestFocus();

                return;
            }


            if (issue.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Problem cannot be empty"
                );

                problem.requestFocus();

                return;
            }


            double amountValue;

            try {

                amountValue =
                        Double.parseDouble(amountText);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Amount must be a valid number"
                );

                amount.requestFocus();

                return;
            }


            if (amountValue < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Amount cannot be negative"
                );

                amount.requestFocus();

                return;
            }


            // =========================
            // UPDATE QUERY
            // =========================

            String sql =
                    "UPDATE mobile_service SET " +
                    "customer_name=?, " +
                    "customer_phone=?, " +
                    "mobile_no=?, " +
                    "problem=?, " +
                    "repair1=?, " +
                    "repair2=?, " +
                    "repair3=?, " +
                    "new_mobile_no=?, " +
                    "parts=?, " +
                    "amount=?, " +
                    "status=?, " +
                    "payment=? " +
                    "WHERE id=?";


            try {

                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ps.setString(1, name);
                ps.setString(2, phone);
                ps.setString(3, mobile);
                ps.setString(4, issue);
                ps.setString(5, repair1.getText().trim());
                ps.setString(6, repair2.getText().trim());
                ps.setString(7, repair3.getText().trim());
                ps.setString(8, newMobileNo.getText().trim());
                ps.setString(9, parts.getText().trim());
                ps.setDouble(10, amountValue);

                ps.setString(
                        11,
                        status.getSelectedItem().toString()
                );

                ps.setString(
                        12,
                        payment.getSelectedItem().toString()
                );

                ps.setInt(13, id);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                        this,
                        "Updated Successfully"
                );

                ps.close();
                con.close();

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Update Error:\n"
                                + ex.getMessage()
                );
            }
        }


        // ==================================================
        // REMOVE → MOVE TO RESTORE TABLE
        // ==================================================

        if (e.getSource() == remove) {

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Move this mobile to restore database?",
                            "Confirm Remove",
                            JOptionPane.YES_NO_OPTION
                    );


            if (choice == JOptionPane.YES_OPTION) {

                Connection con = null;

                try {

                    con = DBConnection.getConnection();

                    // =====================================
                    // COPY TO DELETED TABLE
                    // =====================================

                    String copySql =
                            "INSERT INTO deleted_mobile_service " +
                            "SELECT * FROM mobile_service " +
                            "WHERE id=?";

                    PreparedStatement copyPs =
                            con.prepareStatement(copySql);

                    copyPs.setInt(1, id);

                    int copied =
                            copyPs.executeUpdate();

                    copyPs.close();


                    if (copied == 0) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Mobile record not found"
                        );

                        con.close();

                        return;
                    }


                    // =====================================
                    // DELETE FROM ACTIVE TABLE
                    // =====================================

                    String deleteSql =
                            "DELETE FROM mobile_service " +
                            "WHERE id=?";

                    PreparedStatement deletePs =
                            con.prepareStatement(deleteSql);

                    deletePs.setInt(1, id);

                    deletePs.executeUpdate();

                    deletePs.close();

                    con.close();


                    JOptionPane.showMessageDialog(
                            this,
                            "Mobile moved to restore database"
                    );


                    new Dashboard();

                    dispose();


                } catch (Exception ex) {

                    ex.printStackTrace();

                    try {
                        if (con != null) {
                            con.close();
                        }
                    } catch (Exception ignored) {
                    }

                    JOptionPane.showMessageDialog(
                            this,
                            "Remove Error:\n"
                                    + ex.getMessage()
                    );
                }
            }
        }


        // ==================================================
        // BILL
        // ==================================================

        if (e.getSource() == bill) {

            showBill();
        }


        // ==================================================
        // BACK
        // ==================================================

        if (e.getSource() == back) {

            new Dashboard();

            dispose();
        }
    }


    // ==================================================
    // BILL / RECEIPT
    // ==================================================

    void showBill() {

        String billText =
                "================================\n" +
                "       MOBILE SERVICE CENTER\n" +
                "================================\n\n" +

                "Service ID   : " + id + "\n" +

                "Customer     : "
                + customerName.getText() + "\n" +

                "Phone        : "
                + customerPhone.getText() + "\n" +

                "Mobile No    : "
                + mobileNo.getText() + "\n\n" +

                "Problem      : "
                + problem.getText() + "\n\n" +

                "Repair 1     : "
                + repair1.getText() + "\n" +

                "Repair 2     : "
                + repair2.getText() + "\n" +

                "Repair 3     : "
                + repair3.getText() + "\n\n" +

                "New Mobile   : "
                + newMobileNo.getText() + "\n" +

                "Parts        : "
                + parts.getText() + "\n\n" +

                "Amount       : ₹"
                + amount.getText() + "\n" +

                "Payment      : "
                + payment.getSelectedItem() + "\n" +

                "Status       : "
                + status.getSelectedItem() + "\n\n" +

                "================================\n" +
                "          THANK YOU\n" +
                "================================";


        JTextArea textArea =
                new JTextArea();

        textArea.setText(billText);

        textArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        textArea.setEditable(false);


        JScrollPane scrollPane =
                new JScrollPane(textArea);


        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "Service Bill",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}