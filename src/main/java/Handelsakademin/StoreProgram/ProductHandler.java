package Handelsakademin.StoreProgram;

import java.util.ArrayList;

public class ProductHandler {
    int id = 0;
    private ArrayList<Product> products = new ArrayList<>();

    public ProductHandler() {
    }

    public void addProduct(String name, int price, int quantity) {
        id++;
        Product product = new Product(id, name, price, quantity);
        products.add(product);
    }
}
