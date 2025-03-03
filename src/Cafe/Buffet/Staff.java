package Cafe.Buffet;

import java.util.Random;

/**
 * Staff Class, initialised as objects, the objects will be responsible for restocking.
 * Extends the Thread class as multiple staff need to run concurrently.
 */
public class Staff extends Thread {
    private Random random = new Random();
    private Station assignedStation;

    /**
     * Constructor for the staff class.
     * @param assignedStation the station the staff member will be responsible for restocking.
     */
    public Staff(Station assignedStation) {
        this.assignedStation = assignedStation;
    }

    /**
     * Calls the stations wait and restock methods.
     * A random number between 3 and 7 will be used to increment the stock for the assigned station.
     */
    @Override
    public void run() {
        while (true) {
            assignedStation.waitForStockLow();
            assignedStation.restock(random.nextInt(3, 7));
        }
    }
}
