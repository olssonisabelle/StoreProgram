package Handelsakademin.StoreProgram;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.util.ArrayList;

public class CreateProductForm {
    private JFrame jFrame;
    private JPanel createProductPanel;
    private JButton createProductButton;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField quantityField;
    private JButton goBackButton;
    private JList existingProductsList;
    private JLabel messageLabel;
    private ArrayList <Product> productList;
    private EmployeeForm employeeForm;
    private DefaultListModel defaultListModel = new DefaultListModel<>();
    private ProductHandler productHandler;


    public CreateProductForm() {
        jFrame = new JFrame();
        jFrame.pack();
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(createProductPanel);
        existingProductsList.setModel(defaultListModel);

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

                        if(productPrice > 0 && productQuantity > 0) {
                            productHandler.addProduct(productName, productPrice, productQuantity);
                            messageLabel.setText("Product successfully added");
                            productHandler.saveProductFile();
                            refreshJList();
                            //Reset labels
                            nameField.setText("");
                            priceField.setText("");
                            quantityField.setText("");
                        }
                        else {
                            messageLabel.setText("Please have a price and/or quantity above 0 ");
                        }
                    } catch (Exception ex) {
                        messageLabel.setText("Please write a number for price and/or quantity");
                    }

                } else {
                    messageLabel.setText("Please update all fields");
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
        productHandler = new ProductHandler();
        productHandler.readProductFile();
        productList = productHandler.getProductList();
        refreshJList();
    }
    private void refreshJList() {
        defaultListModel.removeAllElements();
        // Adds all product to the JList
        for(Product product: productList) {
            defaultListModel.addElement(product.getName() + ", Price: " + product.getPrice());
        }
    }
}