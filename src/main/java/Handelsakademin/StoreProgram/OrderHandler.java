package Handelsakademin.StoreProgram;

import java.io.*;
import java.util.ArrayList;

public class OrderHandler {
    private ArrayList<Order> orderList = new ArrayList<>();
    private File orderFile = new File("orders.txt");

    public OrderHandler(){

    }
    public void addNewOrder(ArrayList<Product> productList, Customer customer){
        orderList.add(new Order(productList,customer));
    }
    public ArrayList<Order> getOrderList(){
        return orderList;
    }

    public void readOrderList(){
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
            while (line != null) {
                //Divide into order variables
                tempArr = line.split(delimiterOrder);
                //Placeholder for order id
                idOrder = Integer.parseInt(tempArr[0]);
                //Divide productList into individual products
                tempArr1 = tempArr[1].split(delimiterProductList);
                //Divide individual product into product variables
                tempArr2 = tempArr1[1].split(delimiterProduct);
                //Create products from product variables
                for(int i = 0; i < tempArr2.length; i+=4) {
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
                userHandler.getCustomers();
                for(User user: userHandler.getCustomers()){
                    if(user.getId() == idCustomer){
                        customer = (Customer) user;
                    }
                }
                if(customer !=null) {
                    //Create order from file
                    Order order = new Order(idOrder, productList, customer);
                    //Add to list
                    orderList.add(order);
                }
                line = br.readLine();
            }
            br.close();
        }
        catch (Exception e){
            System.out.println("Error, something went wrong!");
        }
    }

    public void saveOrderLst(){
        try{
            FileWriter fw = new FileWriter(orderFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i = 0; i < orderList.size(); i++){
                //Write user to file
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
