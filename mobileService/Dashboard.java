package mobileService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Dashboard extends JFrame implements ActionListener {

    JButton addMobile;
    JButton searchButton;
    JButton clearButton;

    JButton allButton;
    JButton processButton;
    JButton repairedButton;
    JButton notRepairableButton;

    JTextField searchField;

    JTable table;
    JScrollPane scrollPane;

    JLabel totalLabel;
    JLabel processLabel;
    JLabel repairedLabel;
    JLabel notRepairableLabel;

    JLabel totalAmountLabel;
    JLabel paidAmountLabel;
    JLabel pendingAmountLabel;

    // Current selected status filter
    String currentStatus = "";


    Dashboard() {

        setTitle("Mobile Service Center");
        setSize(1000, 700);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // =========================
        // TITLE
        // =========================

        JLabel title =
                new JLabel("MOBILE SERVICE CENTER");

        title.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        title.setBounds(
                350, 20, 350, 40
        );

        add(title);


        // =========================
        // STATUS SUMMARY
        // =========================

        totalLabel =
                new JLabel("Total: 0");

        totalLabel.setBounds(
                50, 75, 150, 30
        );


        processLabel =
                new JLabel("Under Process: 0");

        processLabel.setBounds(
                200, 75, 170, 30
        );


        repairedLabel =
                new JLabel("Repaired: 0");

        repairedLabel.setBounds(
                380, 75, 150, 30
        );


        notRepairableLabel =
                new JLabel("Not Repairable: 0");

        notRepairableLabel.setBounds(
                530, 75, 180, 30
        );


        add(totalLabel);
        add(processLabel);
        add(repairedLabel);
        add(notRepairableLabel);


        // =========================
        // SEARCH
        // =========================

        JLabel searchLabel =
                new JLabel("Search:");

        searchLabel.setBounds(
                50, 115, 60, 30
        );


        searchField =
                new JTextField();

        searchField.setBounds(
                110, 115, 300, 30
        );


        searchButton =
                new JButton("SEARCH");

        searchButton.setBounds(
                420, 115, 100, 30
        );


        clearButton =
                new JButton("CLEAR");

        clearButton.setBounds(
                530, 115, 100, 30
        );


        searchButton.addActionListener(this);
        clearButton.addActionListener(this);


        add(searchLabel);
        add(searchField);
        add(searchButton);
        add(clearButton);


        // =========================
        // ADD MOBILE
        // =========================

        addMobile =
                new JButton("ADD MOBILE");

        addMobile.setBounds(
                700, 110, 150, 40
        );

        addMobile.addActionListener(this);

        add(addMobile);


        // =========================
        // STATUS FILTER BUTTONS
        // =========================

        allButton =
                new JButton("ALL");

        allButton.setBounds(
                50, 155, 100, 30
        );


        processButton =
                new JButton("UNDER PROCESS");

        processButton.setBounds(
                160, 155, 140, 30
        );


        repairedButton =
                new JButton("REPAIRED");

        repairedButton.setBounds(
                310, 155, 110, 30
        );


        notRepairableButton =
                new JButton("NOT REPAIRABLE");

        notRepairableButton.setBounds(
                430, 155, 150, 30
        );


        allButton.addActionListener(this);
        processButton.addActionListener(this);
        repairedButton.addActionListener(this);
        notRepairableButton.addActionListener(this);


        add(allButton);
        add(processButton);
        add(repairedButton);
        add(notRepairableButton);


        // =========================
        // MONEY SUMMARY
        // IMPORTANT:
        // Create BEFORE loadMobiles()
        // =========================

        totalAmountLabel =
                new JLabel("Total Amount: ₹0");

        totalAmountLabel.setBounds(
                50, 590, 200, 30
        );


        paidAmountLabel =
                new JLabel("Paid: ₹0");

        paidAmountLabel.setBounds(
                300, 590, 150, 30
        );


        pendingAmountLabel =
                new JLabel("Pending: ₹0");

        pendingAmountLabel.setBounds(
                500, 590, 180, 30
        );


        add(totalAmountLabel);
        add(paidAmountLabel);
        add(pendingAmountLabel);


        // =========================
        // LOAD TABLE
        // =========================

        loadMobiles("");


        setVisible(true);
    }


    // ==================================================
    // LOAD MOBILES
    // ==================================================

    void loadMobiles(String search) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql;

            PreparedStatement ps;


            // =========================
            // NORMAL LOAD
            // =========================

            if (search.equals("")) {

                if (currentStatus.equals("")) {

                    sql =
                            "SELECT id, customer_name, customer_phone, " +
                            "mobile_no, status, amount, payment " +
                            "FROM mobile_service";

                    ps =
                            con.prepareStatement(sql);

                } else {

                    sql =
                            "SELECT id, customer_name, customer_phone, " +
                            "mobile_no, status, amount, payment " +
                            "FROM mobile_service " +
                            "WHERE status=?";

                    ps =
                            con.prepareStatement(sql);

                    ps.setString(1, currentStatus);
                }

            }


            // =========================
            // SEARCH
            // =========================

            else {

                if (currentStatus.equals("")) {

                    sql =
                            "SELECT id, customer_name, customer_phone, " +
                            "mobile_no, status, amount, payment " +
                            "FROM mobile_service " +
                            "WHERE customer_name LIKE ? " +
                            "OR customer_phone LIKE ? " +
                            "OR mobile_no LIKE ?";

                    ps =
                            con.prepareStatement(sql);

                    String value =
                            "%" + search + "%";

                    ps.setString(1, value);
                    ps.setString(2, value);
                    ps.setString(3, value);

                } else {

                    sql =
                            "SELECT id, customer_name, customer_phone, " +
                            "mobile_no, status, amount, payment " +
                            "FROM mobile_service " +
                            "WHERE status=? " +
                            "AND (customer_name LIKE ? " +
                            "OR customer_phone LIKE ? " +
                            "OR mobile_no LIKE ?)";

                    ps =
                            con.prepareStatement(sql);

                    String value =
                            "%" + search + "%";

                    ps.setString(1, currentStatus);
                    ps.setString(2, value);
                    ps.setString(3, value);
                    ps.setString(4, value);
                }
            }


            ResultSet rs =
                    ps.executeQuery();


            // =========================
            // TABLE COLUMNS
            // =========================

            String[] columns = {

                    "ID",
                    "Customer",
                    "Customer Phone",
                    "Mobile No",
                    "Status",
                    "Amount",
                    "Payment"
            };


            // =========================
            // NON-EDITABLE TABLE
            // =========================

            DefaultTableModel model =
                    new DefaultTableModel(columns, 0) {

                        @Override
                        public boolean isCellEditable(
                                int row,
                                int column) {

                            return false;
                        }
                    };


            // =========================
            // ADD DATABASE ROWS
            // =========================

            while (rs.next()) {

                Object[] row = {

                        rs.getInt("id"),

                        rs.getString(
                                "customer_name"
                        ),

                        rs.getString(
                                "customer_phone"
                        ),

                        rs.getString(
                                "mobile_no"
                        ),

                        rs.getString(
                                "status"
                        ),

                        rs.getDouble(
                                "amount"
                        ),

                        rs.getString(
                                "payment"
                        )
                };

                model.addRow(row);
            }


            // =========================
            // CREATE TABLE FIRST TIME
            // =========================

            if (table == null) {

                table =
                        new JTable(model);

                scrollPane =
                        new JScrollPane(table);

                scrollPane.setBounds(
                        50, 195, 880, 380
                );

                add(scrollPane);


                // =========================
                // DOUBLE CLICK
                // =========================

                table.addMouseListener(
                        new MouseAdapter() {

                            @Override
                            public void mouseClicked(
                                    MouseEvent e) {

                                if (
                                        e.getClickCount() == 2
                                                &&
                                        SwingUtilities
                                                .isLeftMouseButton(e)
                                ) {

                                    int row =
                                            table.rowAtPoint(
                                                    e.getPoint()
                                            );

                                    if (row >= 0) {

                                        int id =
                                                Integer.parseInt(
                                                        table
                                                                .getValueAt(
                                                                        row,
                                                                        0
                                                                )
                                                                .toString()
                                                );


                                        System.out.println(
                                                "Selected Mobile ID: "
                                                        + id
                                        );


                                        new ServiceDetails(id);

                                        dispose();
                                    }
                                }
                            }
                        }
                );

            }


            // =========================
            // UPDATE EXISTING TABLE
            // =========================

            else {

                table.setModel(model);
            }


            // =========================
            // CLOSE DATABASE OBJECTS
            // =========================

            rs.close();
            ps.close();
            con.close();


            // =========================
            // UPDATE SUMMARY
            // =========================

            loadSummary();


        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading mobiles:\n"
                            + e.getMessage()
            );
        }
    }


    // ==================================================
    // LOAD SUMMARY
    // ==================================================

    void loadSummary() {

        try {

            Connection con =
                    DBConnection.getConnection();


            String sql =
                    "SELECT " +

                    "COUNT(*) AS total, " +

                    "SUM(CASE " +
                    "WHEN status='UNDER PROCESS' " +
                    "THEN 1 ELSE 0 END) AS process, " +

                    "SUM(CASE " +
                    "WHEN status='REPAIRED' " +
                    "THEN 1 ELSE 0 END) AS repaired, " +

                    "SUM(CASE " +
                    "WHEN status='NOT REPAIRABLE' " +
                    "THEN 1 ELSE 0 END) AS notrepair, " +

                    "COALESCE(SUM(amount),0) " +
                    "AS totalAmount, " +

                    "COALESCE(" +
                    "SUM(CASE " +
                    "WHEN payment='PAID' " +
                    "THEN amount ELSE 0 END),0" +
                    ") AS paidAmount " +

                    "FROM mobile_service";


            PreparedStatement ps =
                    con.prepareStatement(sql);


            ResultSet rs =
                    ps.executeQuery();


            if (rs.next()) {

                int total =
                        rs.getInt("total");

                int process =
                        rs.getInt("process");

                int repaired =
                        rs.getInt("repaired");

                int notRepair =
                        rs.getInt("notrepair");


                double totalAmount =
                        rs.getDouble(
                                "totalAmount"
                        );

                double paidAmount =
                        rs.getDouble(
                                "paidAmount"
                        );

                double pendingAmount =
                        totalAmount - paidAmount;


                // =========================
                // STATUS
                // =========================

                totalLabel.setText(
                        "Total: " + total
                );

                processLabel.setText(
                        "Under Process: " + process
                );

                repairedLabel.setText(
                        "Repaired: " + repaired
                );

                notRepairableLabel.setText(
                        "Not Repairable: " + notRepair
                );


                // =========================
                // MONEY
                // =========================

                totalAmountLabel.setText(
                        "Total Amount: ₹"
                                + totalAmount
                );

                paidAmountLabel.setText(
                        "Paid: ₹"
                                + paidAmount
                );

                pendingAmountLabel.setText(
                        "Pending: ₹"
                                + pendingAmount
                );
            }


            rs.close();
            ps.close();
            con.close();


        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // ==================================================
    // BUTTON ACTIONS
    // ==================================================

    @Override
    public void actionPerformed(
            ActionEvent e) {


        // =========================
        // ADD MOBILE
        // =========================

        if (e.getSource() == addMobile) {

            new AddMobile();

            dispose();
        }


        // =========================
        // SEARCH
        // =========================

        if (e.getSource() == searchButton) {

            String search =
                    searchField
                            .getText()
                            .trim();

            loadMobiles(search);
        }


        // =========================
        // CLEAR
        // =========================

        if (e.getSource() == clearButton) {

            searchField.setText("");

            currentStatus = "";

            loadMobiles("");
        }


        // =========================
        // ALL
        // =========================

        if (e.getSource() == allButton) {

            currentStatus = "";

            loadMobiles(
                    searchField.getText().trim()
            );
        }


        // =========================
        // UNDER PROCESS
        // =========================

        if (e.getSource() == processButton) {

            currentStatus = "UNDER PROCESS";

            loadMobiles(
                    searchField.getText().trim()
            );
        }


        // =========================
        // REPAIRED
        // =========================

        if (e.getSource() == repairedButton) {

            currentStatus = "REPAIRED";

            loadMobiles(
                    searchField.getText().trim()
            );
        }


        // =========================
        // NOT REPAIRABLE
        // =========================

        if (e.getSource() == notRepairableButton) {

            currentStatus = "NOT REPAIRABLE";

            loadMobiles(
                    searchField.getText().trim()
            );
        }
    }
}