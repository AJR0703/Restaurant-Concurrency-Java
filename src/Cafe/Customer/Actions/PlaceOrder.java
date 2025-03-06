package Cafe.Customer.Actions;

import Cafe.Buffet.Order;
import SettingsData.Data;

import java.util.*;

/**
 * Implements the CustomerAction interface.
 * The customer requested items are randomly generated and an order object is initialised.
 */
public class PlaceOrder implements CustomerAction {

    private int wantedCoffees;
    private int wantedTeas;
    private int wantedCakes;
    private  Random random = new Random();

    /**
     * Overrides the CustomerAction method.
     * Generates a random quantity of stock and places the order.
     */
    @Override
    public void executeAction() {
        generateQuantity();
        placeOrder();
    }

    /**
     * Order object is created.
     * Calls the goToRequiredQueues() method.
     */
    private void placeOrder() {
        try {
            Order order = new Order(wantedCoffees, wantedTeas, wantedCakes);

            System.out.println(Thread.currentThread().getName() + " is thinking about what they want to eat.");
            Thread.sleep(Data.getWaitingTime());
            System.out.println(Thread.currentThread().getName() + " wants " + wantedTeas + " teas, " + wantedCoffees + " coffees and " + wantedCakes + " cakes.");

            goToRequiredQueues(order);

            System.out.println(Thread.currentThread().getName() + " has received their full order and is Enjoying their order.");
            Thread.sleep(Data.getWaitingTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * If requested food type is higher than zero, the customer will call the waitAtQueue() method.
     * @param order the order created in the placeOrder method.
     */
    private void goToRequiredQueues(Order order) {
        if (wantedCakes > 0) {
            order.waitAtCakeQueue();
        }
        if (wantedTeas > 0) {
            order.waitAtTeaQueue();
        }
        if (wantedCoffees > 0) {
            order.waitAtCoffeeQueue();
        }
    }

    /**
     * A random number of stock items will be generated the condition returns true.
     */
    private void generateQuantity() {
        do {
            this.wantedCakes = random.nextInt(2);
            this.wantedTeas = random.nextInt(2);
            this.wantedCoffees = random.nextInt(2);
        } while (!checkOrder());
    }

    /**
     * Generates an order of the following:
     * Only Coffee,
     * Only Tea,
     * Only Cake,
     * Cake and Tea,
     * Cake and Coffee,
     * @return boolean true if the order meets the requirements.
     */
    private boolean checkOrder() {
        int itemCount = wantedCakes + wantedTeas + wantedCoffees;

        return itemCount == 1 || (itemCount == 2 && !(wantedTeas == 1 && wantedCoffees == 1));
    }

}
