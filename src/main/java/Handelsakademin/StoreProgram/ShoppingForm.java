package Handelsakademin.StoreProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingForm {
    private JPanel shoppingPanel;
    private JList productList;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField quantityField;
    private JButton addToCartButton;
    private JButton logOutButton;

    JFrame jFrame;

    public ShoppingForm(UserHandler userHandler) {
        jFrame = new JFrame();
        jFrame.setSize(700, 700);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(shoppingPanel);
        logOutButton.addActionListener(new ActionListener() {
            // logOutButton to get back to loginForm
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm loginForm = new LoginForm(userHandler);
                loginForm.getLoginForm();
                loginForm.setVisibility(true);
                jFrame.dispose();
            }
        });
    }
}
