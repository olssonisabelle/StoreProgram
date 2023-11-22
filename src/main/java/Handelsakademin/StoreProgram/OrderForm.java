package Handelsakademin.StoreProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderForm {
    private JPanel orderPanel;
    private JLabel customerNameLabel;
    private JList productCartJList;
    private JButton placeOrderButton;
    private JButton goBackButton;
    private JLabel messageLabel;
    private JLabel productNameLabel;
    private JTextField quantityField;
    private JLabel priceLabel;
    private JFrame jFrame;
    private ShoppingForm shoppingForm;

    public OrderForm() {
        jFrame = new JFrame();
        jFrame.setSize(700, 700);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(orderPanel);


        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shoppingForm.setVisibility(true);
                jFrame.dispose();
            }
        });
    }

    public void setShoppingForm(ShoppingForm shoppingForm){
        this.shoppingForm = shoppingForm;
    }
}
