package Handelsakademin.StoreProgram;

import java.util.ArrayList;

public class Order {
    private int id;
    private ArrayList <Product> productList = new ArrayList<>();

    private Customer customer;
    private enum Status{
        ORDERED,
        RECEIVED,
        PACKED,
        CANCELLED,
        FAILED,
        SHIPPED,
        DELIVERED

    };
    private Status status;

    public static int nextId = 1;

    public Order(ArrayList<Product> productList, Customer customer) {
        id = nextId;
        nextId++;
        this.productList = productList;
        this.customer = customer;
        status = Status.ORDERED;
    }

    public Order(int id, ArrayList<Product> productList, Customer customer, String status) {
        this.id = id;
        if(this.id >= nextId) {
            nextId = id + 1;
        }
        this.productList = productList;
        this.customer = customer;
        setStatus(status);
    }

    public Order(Customer customer){

        id = nextId;
        nextId++;
        this.customer = customer;
        status = Status.ORDERED;
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
        return id + "," + str + "," + customer.getId() + "," + status;
    }

    public int getCustomerId(){
        return customer.getId();
    }
    public String getStatus(){
        return status.name();
    }

    public void setStatus(String status) {
        // Changes the status depending on the string that is sent in
        switch (status) {
            case "ORDERED":
                this.status = Status.ORDERED;
                break;
            case "RECEIVED":
                this.status = Status.RECEIVED;
                break;
            case "PACKED":
                this.status = Status.PACKED;
                break;
            case "CANCELLED":
                this.status = Status.CANCELLED;
                break;
            case "FAILED":
                this.status = Status.FAILED;
                break;
            case "SHIPPED":
                this.status = Status.SHIPPED;
                break;
            case "DELIVERED":
                this.status = Status.DELIVERED;
                break;

        }
    }
}
