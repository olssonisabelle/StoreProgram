package Handelsakademin.StoreProgram;

public class Product {
    private int id;
    private String name;
    private int price;
    private int quantity;

    public Product(int id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getCSV(String delimiter){
        return id + delimiter + name + delimiter + price + delimiter + quantity;
    }
}
