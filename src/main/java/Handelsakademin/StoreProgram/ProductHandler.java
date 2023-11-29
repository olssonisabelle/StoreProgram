package Handelsakademin.StoreProgram;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class ProductHandler {

    private ArrayList<Product> productList = new ArrayList<>();

    private File productFile = new File("products.txt");

    public ProductHandler() {
    }

    public void addProduct(String name, int price, int quantity) {

        Product product = new Product(name, price, quantity);
        productList.add(product);
    }

    public ArrayList <Product> getProductList(){
        return productList;
    }

    public void readProductFile(){
        if(productFile.exists()) {
            //Reset list before reading product from file
            productList.clear();
            //Read file to add products to productList
            try {
                FileReader fr = new FileReader(productFile);
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                String[] tempArr;
                String delimiter = ",";
                int id;
                String name;
                int price;
                int quantity;
                while (line != null) {
                    tempArr = line.split(delimiter);
                    id = Integer.parseInt(tempArr[0]);
                    name = tempArr[1];
                    price = Integer.parseInt(tempArr[2]);
                    quantity = Integer.parseInt(tempArr[3]);
                    // Creates a new product
                    Product product = new Product(id, name, price, quantity);
                    // Adds products to list
                    productList.add(product);
                    //Read next line
                    line = br.readLine();
                }
                //Close file when done reading
                br.close();
            } catch (Exception e) {
                System.out.println("Error, something went wrong.");
            }
        }
        else if(!productFile.exists()){
            //Hard coded test products
            productList.add(new Product("T-shirt", 150, 30));
            productList.add(new Product("Pants", 200, 5));
            productList.add(new Product("Jacket", 700, 1));
        }
    }


    public void saveProductFile(){
        try{
            FileWriter fw = new FileWriter(productFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i = 0; i < productList.size(); i++){
                //Write product to file
                bw.write(productList.get(i).getCSV(","));
                //So the file don't end with new line
                if(i < productList.size() - 1){
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
