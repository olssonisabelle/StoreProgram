package Handelsakademin.StoreProgram;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditProductForm {

    private EmployeeForm employeeForm;

    private JFrame jFrame;
    private JPanel editProductPanel;
    private JLabel employeeNameLabel;
    private JButton goBackButton;
    private JTextField nameField;
    private JTextField quantityField;
    private JTextField priceField;
    private JButton saveButton;
    private JList existingProductJList;
    private JLabel messageLabel;
    private JLabel editProductLabel;
    private JButton removeProductButton;
    private DefaultListModel defaultListModel = new DefaultListModel<>();
    private ArrayList<Product> productList;
    private ProductHandler productHandler = new ProductHandler();


    public EditProductForm() {
        jFrame = new JFrame();
        jFrame.pack();
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(editProductPanel);
        existingProductJList.setModel(defaultListModel);

        productHandler.readProductFile();
        productList = productHandler.getProductList();

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeeForm.setVisibility(true);
                jFrame.dispose();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Checks if an item from the JList is selected
                if(existingProductJList.getSelectedIndex()!= -1) {
                    // Gets which item is selected
                    int index = existingProductJList.getSelectedIndex();
                    Product changingProduct = productList.get(index);
                    // Gets the new information from the user
                    String name = nameField.getText();
                    int price = 0;
                    int quantity = 0;
                    try {
                        price = Integer.parseInt(priceField.getText());
                        quantity = Integer.parseInt(quantityField.getText());
                        // Checks to see if the information is valid
                        if (!name.isEmpty() && price > 0 && quantity > 0) {
                            // Saves the changes to the product
                            productHandler.saveProductChanges(changingProduct.getId(), name, price, quantity);
                            // Saves the new productList to the ProductFile
                            productHandler.saveProductFile();
                            messageLabel.setText("Product information has been changed");
                            // Updates JList
                            refreshJList();
                        } else {
                            messageLabel.setText("Missing information to save changes");
                        }
                    } catch (Exception ex) {
                        messageLabel.setText("Price and quantity must be a number");
                    }
                }
                else{
                    messageLabel.setText("Please choose a product");
                }
            }
        });
        existingProductJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Checks if an item from the JList is selected
                if(existingProductJList.getSelectedIndex()!= -1){
                    // Gets the selected item
                    int index = existingProductJList.getSelectedIndex();
                    // Writes the information about the products in the fields
                    nameField.setText(productList.get(index).getName());
                    priceField.setText(productList.get(index).getPrice() + "");
                    quantityField.setText(productList.get(index).getQuantity() + "");
                }
            }
        });
        removeProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Checks if an item from the JList is selected
                if(existingProductJList.getSelectedIndex()!= -1) {
                    // Gets which product is selected
                    int index = existingProductJList.getSelectedIndex();
                    // Deletes the product from the list
                    productList.remove(index);
                    // Empties all the fields
                    nameField.setText("");
                    priceField.setText("");
                    quantityField.setText("");
                    // Update user
                    messageLabel.setText("Product is removed");
                    // Saves the changes to the ProductFile
                    productHandler.saveProductFile();
                    // Updates the JList
                    refreshJList();
                }
            }
        });
    }

    public void refreshJList(){
        // Removes all the existing elements from JList
        defaultListModel.removeAllElements();
        // Updates the productList
        productHandler.readProductFile();
        // Adds all the products to the JList
        for (Product product : productList) {
            defaultListModel.addElement("Id: " + product.getId() + ", " + product.getName());
        }
    }

    public void setEmployeeNameLabel() {
        employeeNameLabel.setText(employeeForm.getLoginForm().getLogedInUser().getFirstName() + " " + employeeForm.getLoginForm().getLogedInUser().getLastName());
    }

    public void setEmployeeForm(EmployeeForm employeeForm) {
        this.employeeForm = employeeForm;
        setEmployeeNameLabel();
        refreshJList();
    }

}