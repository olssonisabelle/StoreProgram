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
        ProductHandler productHandler = new ProductHandler();

        productHandler.readProductFile();
        productList = productHandler.getProductList();
        // Adds all product to the JList
        for(Product product: productList){
            defaultListModel.addElement(product.getName());
        }

        existingProductsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Clears the messageLabel
                messageLabel.setText("");
                // Gets the chosen index
                int index = existingProductsList.getSelectedIndex();
                // Sets the labels to display the chosen product information
                nameField.setText(productList.get(index).getName());
                priceField.setText(Integer.toString(productList.get(index).getPrice()));
                quantityField.setText(Integer.toString(productList.get(index).getQuantity()));
            }
        });

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
                        messageLabel.setText("Please write a number for price and/or quantity");
                    }
                    productHandler.addProduct(productName, productPrice, productQuantity);
                    messageLabel.setText("Product successfully added");
                    refreshJList();
                    productHandler.saveProductFile();
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
    }
private void refreshJList() {
    defaultListModel.removeAllElements();
    // Adds all product to the JList
    for(Product product: productList) {
        defaultListModel.addElement(product.getName());
    }
    }
}