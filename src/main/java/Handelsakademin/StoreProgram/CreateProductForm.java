package Handelsakademin.StoreProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateProductForm {
    private JFrame jFrame;
    private JPanel createProductPanel;
    private JButton createProductButton;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField quantityField;
    private JButton goBackButton;
    private JList list1;

    private EmployeeForm employeeForm;

    private ProductHandler productHandler;

    public CreateProductForm() {
        jFrame = new JFrame();
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(createProductPanel);
        jFrame.pack();

        createProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!nameField.getText().isEmpty() && !priceField.getText().isEmpty() && !quantityField.getText().isEmpty()) {
                    String productName = nameField.getText();
                    int productPrice = 0;
                    int productQuantity = 0;
                    try {
                        productPrice = Integer.parseInt(priceField.getText());
                        productQuantity = Integer.parseInt(quantityField.getText());
                    } catch (Exception ex) {
                        //TODO uppdatera message field får bara finnas siffror i price och quantity
                    }
                    productHandler.addProduct(productName, productPrice, productQuantity);
                    //TODO uppdatera message field med success

                } else {
                   //TODO uppdatera message field fyll i alla fields
                }
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeeForm.setVisibility(true);
                jFrame.dispose();
            }
        });
    }

    public void setEmployeeForm(EmployeeForm employeeForm) {
        this.employeeForm = employeeForm;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}