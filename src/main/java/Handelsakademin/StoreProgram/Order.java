package Handelsakademin.StoreProgram;

import java.util.ArrayList;

public class Order {
    private int id;
    private ArrayList <Product> productList = new ArrayList<>();

    private Customer customer;

    private static int nextId = 1;

    public Order(ArrayList<Product> productList, Customer customer) {
        id = nextId;
        nextId++;
        this.productList = productList;
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
}
