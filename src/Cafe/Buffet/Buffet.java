package Cafe.Buffet;

import SettingsData.Data;

public class Buffet {

    public static Station COFFEE_STATION;
    public static Station TEA_STATION;
    public static Station CAKE_STATION;

    /**
     * Creates object for the class station and assigns names to each of them.
     */
    public static void InitializeStations() {
        COFFEE_STATION = new Station(Data.getInitialCoffeeStock(), Data.getStaffForSpecifiedStation("coffee"), "coffee");
        TEA_STATION = new Station(Data.getInitialTeaStock(), Data.getStaffForSpecifiedStation("tea"), "tea");
        CAKE_STATION = new Station(Data.getInitialCakeStock(), Data.getStaffForSpecifiedStation("cake"), "cake");

        COFFEE_STATION.setName("Coffee Station");
        TEA_STATION.setName("Tea Station");
        CAKE_STATION.setName("Cake Station");

        COFFEE_STATION.start();
        TEA_STATION.start();
        CAKE_STATION.start();
    }
}
