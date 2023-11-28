package Handelsakademin.StoreProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeForm {

    private JPanel employeePanel;
    private JLabel employeeNameLabel;
    private JButton createEmployeeButton;
    private JButton createNewProductButton;
    private JButton logOutButton;
    private JButton goToChangeProfileButton;
    private JButton goToInventoryButton;
    private JFrame jFrame;
    private LoginForm loginForm;
    private EmployeeForm employeeForm = this;

    private ProductHandler productHandler;

    public EmployeeForm() {
        jFrame = new JFrame();
        jFrame.setSize(700, 700);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(employeePanel);
        jFrame.pack();
        createEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateEmployeeForm createEmployeeForm = new CreateEmployeeForm();
                //To send employeeForm information to another form
                createEmployeeForm.setEmployeeForm(employeeForm);
                jFrame.setVisible(false);
            }
        });

        createNewProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateProductForm createProductForm = new CreateProductForm();
                createProductForm.setEmployeeForm(employeeForm);
                //To send employeeForm information to another form

                jFrame.setVisible(false);
            }
        });

        logOutButton.addActionListener(new ActionListener() {
            // logOutButton to get back to loginForm
            @Override
            public void actionPerformed(ActionEvent e) {
                loginForm.setVisibility(true);
                jFrame.dispose();
            }
        });

        goToChangeProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditEmployeeForm editEmployeeForm = new EditEmployeeForm();
                editEmployeeForm.setEmployeeInformation(employeeForm);
                jFrame.setVisible(false);
            }
        });
        goToInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InventoryForm inventoryForm = new InventoryForm();
                inventoryForm.setEmployeeForm(employeeForm);
                jFrame.setVisible(false);
            }
        });
    }


    public EmployeeForm getEmployeeForm(){
        return this;
    }
    public void setVisibility(boolean isVisible){
        jFrame.setVisible(isVisible);
    }

    public void setLoginForm(LoginForm loginForm){
        this.loginForm = loginForm;
    }

    public LoginForm getLoginForm(){
        return loginForm;
    }

    //Fill in userNameLabel with the logged-in users full name
    public void setUserNameLable(){
        employeeNameLabel.setText(loginForm.getLogedInUser().getFirstName() + " " + loginForm.getLogedInUser().getLastName());
    }

    public void setProductHandler(ProductHandler productHandler) {
        this.productHandler = productHandler;
    }
}
