package Handelsakademin.StoreProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginForm {
    private JPanel loginPanel;
    private JTextField emailField;
    private JTextField passwordField;
    private JButton createNewUserButton;
    private JButton loginButton;
    private JLabel messageLabel;
    private JFrame jFrame;

    private ArrayList<User> userList = createUsers();

    public LoginForm() {
        jFrame = new JFrame();
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(loginPanel);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean foundUser = false;
                User tempUser = new User();
                for(User user: userList){
                    if(emailField.getText().equals(user.getEmail())){
                        if(passwordField.getText().equals(user.getPassword())){
                            foundUser = true;
                            tempUser = user;
                            break;
                        }
                    }
                }

                if(foundUser && tempUser.isWorking()){
                    EmployeeForm employeeForm = new EmployeeForm();
                    jFrame.setVisible(false);
                }
                else if (foundUser && !tempUser.isWorking()) {
                    ShoppingForm shoppingForm = new ShoppingForm();
                    jFrame.setVisible(false);
                }
                else{
                   messageLabel.setText("Incorrect username or password");
                }

            }
        });
    }

    private ArrayList<User> createUsers(){
        ArrayList<User> users = new ArrayList<>();
        users.add(new Employee("Ulf", "Bo", "ulf.bo@mail.com", 400, "123"));
        users.add(new Customer("Bo", "Ek", "BoEk", "Gatan", 302, "Halmstad", "1234"));
        return users;
    }
}
