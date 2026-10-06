package mobileService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JLabel l1, l2;
    JTextField username;
    JPasswordField password;
    JButton login;

    Login() {

        setTitle("Mobile Service Center - Login");

        setSize(400, 250);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l1 = new JLabel("Username");
        l1.setBounds(50, 50, 100, 30);

        l2 = new JLabel("Password");
        l2.setBounds(50, 100, 100, 30);

        username = new JTextField();
        username.setBounds(150, 50, 180, 30);

        password = new JPasswordField();
        password.setBounds(150, 100, 180, 30);

        login = new JButton("LOGIN");
        login.setBounds(150, 150, 100, 30);

        login.addActionListener(this);

        add(l1);
        add(l2);
        add(username);
        add(password);
        add(login);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String user = username.getText();
        String pass = new String(password.getPassword());

        String sql = "SELECT * FROM admin WHERE username=? AND password=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                JOptionPane.showMessageDialog(
                    this,
                    "Login Successful"
                );

                dispose();

                new Dashboard();

            } else {

                JOptionPane.showMessageDialog(
                    this,
                    "Invalid Username or Password"
                );
            }

            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}