package SettingsData;

import java.util.HashMap;
import java.util.Map;

public class Data {
    private static int initialCoffeeStock;
    private static int initialTeaStock;
    private static int initialCakeStock;
    private static int customerCount;
    private static Map<String, Integer> staffAvailable = new HashMap<>();

    private static int waitingTime = 500;

    public static int getInitialCakeStock() {
        return initialCakeStock;
    }

    public static void setInitialCakeStock(int initialCakeStock) {
        Data.initialCakeStock = initialCakeStock;
    }

    public static int getInitialCoffeeStock() {
        return initialCoffeeStock;
    }

    public static void setInitialCoffeeStock(int initialCoffeeStock) {
        Data.initialCoffeeStock = initialCoffeeStock;
    }

    public static int getInitialTeaStock() {
        return initialTeaStock;
    }

    public static void setInitialTeaStock(int initialTeaStock) {
        Data.initialTeaStock = initialTeaStock;
    }

    public static int getCustomerCount() {
        return customerCount;
    }

    public static void setCustomerCount(int customerCount) {
        Data.customerCount = customerCount;
    }

    public static Integer getStaffForSpecifiedStation(String staffType) {
        return staffAvailable.get(staffType);
    }

    public static void addStaff(String staffType, Integer numOfStaff) {
        staffAvailable.put(staffType, numOfStaff);
    }

    public static void outputStartingValue() {
        System.out.println("Starting Simulation with " + customerCount + " customers, " +
                initialTeaStock + " teas, " +
                initialCakeStock + " cakes and " +
                initialCoffeeStock + " coffees.");
    }

    public static int getWaitingTime() {
        return waitingTime;
    }

    public static void setWaitingTime(int waitingTime) {
        Data.waitingTime = waitingTime;
    }
}
