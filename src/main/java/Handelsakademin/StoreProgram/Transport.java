package Handelsakademin.StoreProgram;

import java.util.ArrayList;

public class Transport {
    private int id;
    private String driver;
    private String registrationNumber;
    private ArrayList<Order> transportOrders;
    private boolean isTransported;
    public static int nextId = 1;

    //Constructor to use when a user create transport in application
    public Transport(String driver, String registrationNumber, ArrayList<Order> transportOrders) {
        id = nextId;
        nextId++;
        this.driver = driver;
        this.registrationNumber = registrationNumber;
        this.transportOrders = transportOrders;
        this.isTransported = sendTransport();
    }
    //Constructor to use when reading from file to create transport
    public Transport(int id, String driver, String registrationNumber, ArrayList<Order> ordersToTransport, boolean isTransported) {
        this.id = id;
        if(this.id >= nextId) {
            nextId = id + 1;
        }
        this.driver = driver;
        this.registrationNumber = registrationNumber;
        transportOrders = new ArrayList<>();
        for(Order order: ordersToTransport) {
            transportOrders.add(order);
        }
        this.isTransported = isTransported;
    }
    //There is a 10% chance that transport will fail to ship
    public boolean sendTransport(){
        int send = (int)(Math.random()*100);
        return false;//send >= 10;
    }
    //To create a CSV file to save data
    public String getCSV(){
        return id + "," + driver + "," + registrationNumber + "," + getTransportOrderListCSV("@") + "," + isTransported;
    }
    //A CSV for the orderList in transport
    private String getTransportOrderListCSV(String delimiter){
        String temp = "";
        for(int i = 0; i < transportOrders.size(); i++){
            temp += transportOrders.get(i).getId();
            if(i < transportOrders.size() - 1){
                temp += delimiter;
            }
        }
        return temp;
    }
    public int getID() {
        return id;
    }
    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public ArrayList<Order> getTransportOrders() {
        return transportOrders;
    }
    public String getTransportOrdersText() {
        String text = "";
        for(Order order: transportOrders){
            text += ", Order ID: " + order.getId() + ", Customer: " + order.getCustomerName();
        }
        return text;
    }

    public void setTransportOrders(ArrayList<Order> transportOrders) {
        this.transportOrders = transportOrders;
    }

    public boolean isTransported() {
        return isTransported;
    }

    public void setTransported(boolean transported) {
        isTransported = transported;
    }
}
