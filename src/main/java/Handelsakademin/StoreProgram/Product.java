package Handelsakademin.StoreProgram;

public class Product {
    private int id;
    private String name;
    private int price;
    private int quantity;
    private static int nextId = 1;

    public Product(int id, String name, int price, int quantity) {
        this.id = id;
        if(this.id >= nextId) {
            nextId = id + 1;
        }
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String name, int price, int quantity) {
        id = nextId;
        nextId++;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Is only used when adding product to cart
    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getCSV(String delimiter){
        return id + delimiter + name + delimiter + price + delimiter + quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
