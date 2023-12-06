package Handelsakademin.StoreProgram;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class OrderHandler {
    private ArrayList<Order> orderList = new ArrayList<>();
    private File orderFile = new File("orders.txt");

    public OrderHandler(){

    }
    public void addNewOrder(Order order){
        orderList.add(order);
    }
    public ArrayList<Order> getOrderList(){
        return orderList;
    }

    public ArrayList<Order> getOrderListByCustomer(int id){
        ArrayList <Order> specificOrderList = new ArrayList<>();
        //Looks through the orderList and adds all the orders with a specific customer's id
        for(Order order: orderList){
            if(order.getCustomerId() == id){
                specificOrderList.add(order);
            }
        }
        return specificOrderList;
    }

    public void readOrderList(){
        orderList.clear();
        try {
            FileReader fr = new FileReader(orderFile);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String[] tempArr;
            String[] tempArr1;
            String[] tempArr2;
            String delimiterOrder = ",";
            String delimiterProductList = "/";
            String delimiterProduct = "&";
            String tempString = "";
            //Variable for order number
            int idOrder;
            //Variables for Customer
            int idCustomer;
            Customer customer = null;
            //Variables for product
            int idProduct;
            String name;
            int price;
            int quantity;
            ArrayList<Product> productList = new ArrayList<>();
            String status;
            while (line != null) {
                //Divide into order variables
                tempArr = line.split(delimiterOrder);
                //Placeholder for order id
                idOrder = Integer.parseInt(tempArr[0]);
                // Checks if there are multiple product in order or not
                if(tempArr[1].contains(delimiterProductList)) {
                    tempArr1 = tempArr[1].split(delimiterProductList);
                    // tempString to be able to separate productList containing more than 1 product
                    tempString = "";
                    // Adds all the product together with only one kind of delimiter
                    for(int i = 0; i < tempArr1.length; i++) {
                        tempString += tempArr1[i];
                        if(i < tempArr1.length-1){
                            tempString += delimiterProduct;
                        }
                    }
                    //Divide individual product into product variables
                    tempArr2 = tempString.split(delimiterProduct);
                }
                else{
                    //Divide individual product into product variables
                    tempArr2 = tempArr[1].split(delimiterProduct);
                }
                //Create products from product variables
                for (int i = 0; i < tempArr2.length; i += 4) {
                    idProduct = Integer.parseInt(tempArr2[i]);
                    name = tempArr2[i + 1];
                    price = Integer.parseInt(tempArr2[i + 2]);
                    quantity = Integer.parseInt(tempArr2[i + 3]);
                    //Create product
                    Product product = new Product(idProduct, name, price, quantity);
                    //Add product to list
                    productList.add(product);
                }

                idCustomer = Integer.parseInt(tempArr[2]);
                UserHandler userHandler = new UserHandler();
                userHandler.readUserFile();
                ArrayList <User> customerList = userHandler.getCustomers();
                for(User user: customerList){
                    if(user.getId() == idCustomer){
                        customer = (Customer) user;
                        break;
                    }
                }
                if(customer !=null) {
                    //Create order from file
                    ArrayList <Product> tempProductList = new ArrayList<>();
                    for(Product product: productList){
                        tempProductList.add(product);
                    }
                    status = tempArr[3];
                    Order order = new Order(idOrder, tempProductList, customer, status);
                    //Add to list
                    orderList.add(order);
                }
                line = br.readLine();
                productList.clear();
            }
            br.close();
        }
        catch (Exception e){
            System.out.println("Error, something went wrong!");
        }
    }

    public void saveOrderList(){
        try{
            FileWriter fw = new FileWriter(orderFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i = 0; i < orderList.size(); i++){
                //Write order to file
                bw.write(orderList.get(i).getCSV());
                //So the file don't end with new line
                if(i < orderList.size() - 1){
                    bw.newLine();
                }
            }
            bw.close();
        }
        catch (Exception e){
            System.out.println("Error, something went wrong.");
        }
    }
}
