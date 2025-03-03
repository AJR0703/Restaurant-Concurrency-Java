package Cafe;

import Cafe.Buffet.Buffet;
import Cafe.Customer.Customer;
import SettingsData.Data;

public class Cafe {
    private int numOfCustomers;
    private int customerCount = 1;

    public void open() {
        Buffet.InitializeStations();
        this.numOfCustomers = Data.getCustomerCount();
        for (int i = 1; i <= numOfCustomers; i++) {
            try{
                Customer customer = new Customer();
                customer.setName("Customer-" + customerCount);
                customer.start();
                customerCount++;
                Thread.sleep(5);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addNewCustomers() {
        this.numOfCustomers = Data.getCustomerCount();
        for (int i = 1; i <= numOfCustomers; i++) {
            try{
                Customer customer = new Customer();
                customer.setName("Customer-" + customerCount);
                customer.start();
                Thread.sleep(5);
                customerCount++;
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
