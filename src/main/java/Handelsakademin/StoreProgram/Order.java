package Handelsakademin.StoreProgram;

import java.util.ArrayList;

public class Order {
    private int id;
    private ArrayList <Product> productList = new ArrayList<>();

    private Customer customer;

    public static int nextId = 1;

    public Order(ArrayList<Product> productList, Customer customer) {
        id = nextId;
        nextId++;
        this.productList = productList;
        this.customer = customer;
    }

    public Order(int id, ArrayList<Product> productList, Customer customer) {
        this.id = id;
        if(this.id >= nextId) {
            nextId = id + 1;
        }
        this.productList = productList;
        this.customer = customer;
    }

    public Order(Customer customer){

        id = nextId;
        nextId++;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }


    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }
    private String getProductListCSV(String delimiter){
        String temp = "";
        for(int i = 0; i < productList.size(); i++){
            temp += productList.get(i).getCSV("&");
            if(i < productList.size() - 1){
                temp += delimiter;
            }
        }
        return temp;
    }

    public void addProductToOrder(Product product){
        productList.add(product);
    }

    public String getCustomerName(){
        return customer.getFirstName() + " " + customer.getLastName();
    }

    public String getCSV(){
        String str = getProductListCSV("/");
        return id + "," + str + "," + customer.getId();
    }
}
