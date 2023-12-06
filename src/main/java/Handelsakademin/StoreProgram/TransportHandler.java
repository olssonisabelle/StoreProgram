package Handelsakademin.StoreProgram;

import java.io.*;
import java.util.ArrayList;

public class TransportHandler {
    private ArrayList<Transport> transportList = new ArrayList<>();
    private File transportFile = new File("transports.txt");
    private ArrayList<Order> allOrders;
    private OrderHandler orderHandler;
    public TransportHandler(){
        //Create a orderHandler
        orderHandler = new OrderHandler();
        //Read order file
        orderHandler.readOrderList();
        //Get a list of all order from orderHandler
        allOrders = orderHandler.getOrderList();
    }
    public void addNewTransport(Transport transport){
        transportList.add(transport);
    }
    public ArrayList<Transport> getTransportList(){
        return transportList;
    }
    public void saveTransportList(){
        try{
            FileWriter fw = new FileWriter(transportFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i = 0; i < transportList.size(); i++){
                //Write transport to file
                bw.write(transportList.get(i).getCSV());
                //So the file don't end with new line
                if(i < transportList.size() - 1){
                    bw.newLine();
                }
            }
            bw.close();
        }
        catch (Exception e){
            System.out.println("Error, something went wrong.");
        }
    }

    public void readTransportList(){
        transportList.clear();
        try {
            FileReader fr = new FileReader(transportFile);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String[] tempArr;
            String[] tempArr1;
            String delimiterTransport = ",";
            String delimiterOrderList = "@";
            //Variables for transport
            int idTransport;
            String driver;
            String registrationNumber;
            Boolean isTransported;
            ArrayList<Order> orderList = new ArrayList<>();
            //1,Urban,XYZ321,6@5,true
            while (line != null) {
                //Divide into transport variables
                tempArr = line.split(delimiterTransport);
                //Id
                idTransport = Integer.parseInt(tempArr[0]);
                //Driver
                driver = tempArr[1];
                //Registration number
                registrationNumber = tempArr[2];
                //Status for transport
                isTransported = Boolean.getBoolean(tempArr[4]);
                //Divide into orderIds
                tempArr1 = tempArr[3].split(delimiterOrderList);
                //Find order by orderId and add to orderList
                for(int i = 0; i < tempArr1.length; i++){
                    for(int j = 0; j < allOrders.size(); j++){
                        if(Integer.parseInt(tempArr1[i]) == allOrders.get(j).getId()){
                            orderList.add(allOrders.get(j));
                        }
                    }
                }
                //Create transport
                Transport transport = new Transport(idTransport, driver, registrationNumber, orderList, isTransported);
                //Add transport to list
                transportList.add(transport);
                //Read next line
                line = br.readLine();
                //Reset orderList for next transport object
                orderList.clear();
            }
            br.close();
        }
        catch (Exception e){
            System.out.println("Error, something went wrong!");
        }
    }
}
