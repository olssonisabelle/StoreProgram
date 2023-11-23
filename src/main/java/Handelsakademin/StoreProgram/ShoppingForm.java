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
    private JLabel userNameLable;
    private JButton editCustomerButton;
    private JButton checkOutButton;
    private JFrame jFrame;
    private LoginForm loginForm;
    private ShoppingForm shoppingForm = this;

    public ShoppingForm() {
        jFrame = new JFrame();
        jFrame.setSize(700, 700);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(shoppingPanel);
        logOutButton.addActionListener(new ActionListener() {
            //logOutButton to get back to loginForm
            @Override
            public void actionPerformed(ActionEvent e) {
                loginForm.setVisibility(true);
                jFrame.dispose();
            }
        });
        editCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCustomerForm editCustomerForm = new EditCustomerForm();
                editCustomerForm.setCustomerInformation(shoppingForm);
                jFrame.setVisible(false);
            }
        });
        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderForm orderForm = new OrderForm();
                orderForm.setShoppingForm(shoppingForm);
                jFrame.setVisible(false);
            }
        });
    }

    public void setLoginForm(LoginForm loginForm){
        this.loginForm = loginForm;
    }

    public void setVisibility(boolean isVisible){
        jFrame.setVisible(isVisible);
    }
    public LoginForm getLoginForm(){
        return loginForm;
    }

    //Fill in userNameLabel with the logged-in users full name
    public void setUserNameLable(){
        userNameLable.setText(loginForm.getLogedInUser().getFirstName() + " " + loginForm.getLogedInUser().getLastName());
    }
}
