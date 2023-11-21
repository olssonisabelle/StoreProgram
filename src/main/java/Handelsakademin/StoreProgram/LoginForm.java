package Handelsakademin.StoreProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginForm {
    private JPanel loginPanel;
    private JTextField emailField;
    private JTextField passwordField;
    private JButton createNewUserButton;
    private JButton loginButton;
    private JFrame jFrame;

    private ArrayList<User> userList = new ArrayList<>();

    public LoginForm() {
        jFrame = new JFrame();
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(loginPanel);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
    }
}
