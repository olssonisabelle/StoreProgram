package Handelsakademin.StoreProgram;

import javax.swing.*;

public class ShoppingForm {
    private JPanel shoppingPanel;
    private JList productList;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField quantityField;
    private JButton addToCartButton;
    private JButton logOutButton;

    JFrame jFrame;

    public ShoppingForm() {
        jFrame = new JFrame();
        jFrame.setSize(700, 700);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(shoppingPanel);
    }
}
