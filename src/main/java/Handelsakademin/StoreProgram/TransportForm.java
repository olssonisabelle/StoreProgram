package Handelsakademin.StoreProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TransportForm {
    private JButton goBackButton;
    private JList transportOrderList;
    private JTextField driverField;
    private JTextField registrationNumberField;
    private JList ordersReadyToShipList;
    private JButton addOrderToTransportButton;
    private JButton sendTransportButton;
    private JPanel transportPanel;
    private JLabel employeeNameLabel;
    private JLabel messageLabel;
    private JButton removeOrderFromTransportButton;
    private DefaultListModel orderListModel = new DefaultListModel<>();
    private DefaultListModel transportListModel = new DefaultListModel<>();
    private OrderHandler orderHandler = new OrderHandler();
    private TransportHandler transportHandler = new TransportHandler();
    private ArrayList<Order> orders;
    private ArrayList<Order> packedOrders = new ArrayList<>();
    private ArrayList<Order> transportArrayList = new ArrayList<>();
    private InventoryForm inventoryForm;
    JFrame jFrame;

    public TransportForm(){
        jFrame = new JFrame();
        jFrame.pack();
        jFrame.setSize(700, 700);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(transportPanel);
        ordersReadyToShipList.setModel(orderListModel);
        transportOrderList.setModel(transportListModel);

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //If you go back to inventoryForm without creating a transport, change added orders status to packed
                for (int i = 0; i < orders.size(); i++) {
                    for(Order order: transportArrayList){
                        if(order.getId() == orders.get(i).getId()){
                            orders.get(i).setStatus("PACKED");
                        }
                    }
                }
                //Save updates in orderFile
                orderHandler.saveOrderList();
                inventoryForm.setVisibility(true);
                jFrame.dispose();
            }
        });
        sendTransportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String driver = driverField.getText();
                String registrationNumber = registrationNumberField.getText();
                ArrayList<Order> ordersToTransport = transportArrayList;
                if(!driver.isEmpty() && !registrationNumber.isEmpty() && !ordersToTransport.isEmpty()){
                    Transport transport = new Transport(driver,registrationNumber,ordersToTransport);
                    if(transport.isTransported()){
                        //Transport successfully shipped, add to list
                        transportHandler.addNewTransport(transport);
                        //Save only successful transports to file
                        transportHandler.saveTransportList();
                        //Remove all orders in transportArrayList
                        transportArrayList.clear();
                        //Refresh list when order is added
                        refreshTransportList();
                        //Reset driverField
                        driverField.setText("");
                        //Reset registrationNumberField
                        registrationNumberField.setText("");
                        //Update user
                        messageLabel.setText("Transport successfully sent");
                    }
                    else {
                        //If transport object fails to create reset orders status in list to packed
                        for (int i = 0; i < orders.size(); i++) {
                            for(Order order: transportArrayList){
                                if(order.getId() == orders.get(i).getId()){
                                    orders.get(i).setStatus("PACKED");
                                }
                            }
                        }
                        //Save updates in orderFile
                        orderHandler.saveOrderList();
                        transportArrayList.clear();
                        //Refresh ordersReadyToShip
                        refreshOrderList();
                        //Refresh list when order is added
                        refreshTransportList();
                        driverField.setText("");
                        registrationNumberField.setText("");
                        //Update user
                        messageLabel.setText("Transport failed, try again");
                    }
                }
                else {
                    //Update user
                    messageLabel.setText("Missing information to create transport");
                }
            }
        });
        addOrderToTransportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ordersReadyToShipList.getSelectedIndex() != -1) {
                    int index = ordersReadyToShipList.getSelectedIndex();
                    //Add selected order from ordersReadyToShipList to transportOrderList
                    for (int i = 0; i < orders.size(); i++) {
                        if (packedOrders.get(index).getId() == orders.get(i).getId()) {
                            //Add order to transport list
                            transportArrayList.add(orders.get(i));
                            //Remove order from packedOrdersList
                            packedOrders.remove(packedOrders.get(index));
                            //Change status in order to ship
                            orders.get(i).setStatus("SHIPPED");
                            //Update file
                            orderHandler.saveOrderList();
                            break;
                        }
                    }
                    //Refresh ordersReadyToShip
                    refreshOrderList();
                    //Refresh list when order is added
                    refreshTransportList();
                }
            }
        });
        removeOrderFromTransportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (transportOrderList.getSelectedIndex() != -1) {
                    //Remove order from transport
                    int index = transportOrderList.getSelectedIndex();
                    //Add selected order from ordersReadyToShipList to transportOrderList
                    for (int i = 0; i < orders.size(); i++) {
                        if (transportArrayList.get(index).getId() == orders.get(i).getId()) {
                            //Remove order from transport list
                            transportArrayList.remove(index);
                            //Change status in order to packed
                            orders.get(i).setStatus("PACKED");
                            //Update file
                            orderHandler.saveOrderList();
                            break;
                        }
                    }
                    //Refresh ordersReadyToShip
                    refreshOrderList();
                    //Refresh list when order is added
                    refreshTransportList();
                }
            }
        });
    }

    public void setInventoryForm(InventoryForm inventoryForm){
        this.inventoryForm = inventoryForm;
        //Set userNameLabel
        setUserNameLabel();
        //Refresh list
        refreshOrderList();
    }
    private void refreshTransportList(){
        //First, remove all elements from the list.
        transportListModel.removeAllElements();
        //Adds orders from transportArrayList
        for(Order order: transportArrayList){
            transportListModel.addElement("Order ID: " + order.getId() + ", Address: " + order.getCustomerAddress());
        }
    }
    private void refreshOrderList() {
        //First, remove all elements from the list.
        orderListModel.removeAllElements();
        //Get orderList from orderHandler class
        orderHandler.readOrderList();
        orders = orderHandler.getOrderList();
        //Add orders with status packed
        for (Order order : orders) {
            if(order.getStatus().equals("PACKED")) {
                orderListModel.addElement("Order ID: " + order.getId() + ", Address: " + order.getCustomerAddress());
                packedOrders.add(order);
            }
        }
    }
    public void setUserNameLabel(){
        employeeNameLabel.setText(inventoryForm.getEmployeeForm().getLoginForm().getLogedInUser().getFirstName() + " " + inventoryForm.getEmployeeForm().getLoginForm().getLogedInUser().getLastName());
    }
}
