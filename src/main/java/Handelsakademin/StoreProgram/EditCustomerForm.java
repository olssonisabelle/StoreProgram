package Handelsakademin.StoreProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditCustomerForm {
    private JPanel editCustomerPanel;
    private JButton goBackButton;
    private JButton saveChangesButton;
    private JLabel customerNameLabel;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField passwordField;
    private JTextField streetNameField;
    private JTextField zipCodeField;
    private JTextField cityField;
    private JFrame jFrame;

    ShoppingForm shoppingForm;

    public EditCustomerForm() {
        jFrame = new JFrame();
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(editCustomerPanel);
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
