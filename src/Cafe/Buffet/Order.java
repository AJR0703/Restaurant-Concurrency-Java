package Cafe.Buffet;

import SettingsData.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static Cafe.Buffet.Buffet.*;

/**
 * The order class represents the food the customer wants to take from the Buffet Stations.
 * Once initialized as an object, the food will be added to a station queue.
 * Mutual Exclusion will be enforced to prevent interference between threads.
 */
public class Order {

    private ReentrantLock orderLock = new ReentrantLock(true);
    private Condition coffeeCondition = orderLock.newCondition();
    private Condition teaCondition = orderLock.newCondition();
    private Condition cakeCondition = orderLock.newCondition();
    private Map<String, Integer> stockMap;
    private Map<String, Boolean> stockRequirementMap;

    /**
     * Constructor for the Order class.
     *
     * @param numOfCoffees The number of Coffees for the order.
     * @param numOfTeas The number of Teas for the order.
     * @param numOfCakes The number of Cakes for the order.
     */
    public Order(int numOfCoffees, int numOfTeas, int numOfCakes) {
        stockMap = new HashMap<>();
        stockRequirementMap = new HashMap<>();
        stockMap.put("coffee", numOfCoffees);
        stockMap.put("tea", numOfTeas);
        stockMap.put("cake", numOfCakes);
    }

    /**
     * Checks to see if the current order requires coffees,
     * If the current Order has coffees, the order is added to the Coffee station queue.
     * The thread is set to wait until the Coffee has been retrieved.
     */
    public void waitAtCoffeeQueue() {
        orderLock.lock();
        try {
            if (stockMap.get("coffee") > 0) {
                System.out.println(Thread.currentThread().getName() + " is waiting in Coffee Station queue.");
                Thread.sleep(Data.getWaitingTime());
                stockRequirementMap.put("coffee", true);
                if (!COFFEE_STATION.getOrderQueue().isEmpty()) {
                    System.out.println(Thread.currentThread().getName() + " is waiting in line for their Coffee.");
                }
                COFFEE_STATION.getOrderQueue().put(this);
                coffeeCondition.await();
            } else {
                stockRequirementMap.put("coffee", false);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            orderLock.unlock();
        }
    }

    /**
     * Checks to see if the current order requires cakes,
     * If the current Order has cakes, the order is added to the Cake station queue.
     * The thread is set to wait until the Cake has been retrieved.
     */
    public void waitAtCakeQueue() {
        orderLock.lock();
        try {
            if (stockMap.get("cake") > 0) {
                System.out.println(Thread.currentThread().getName() + " is heading to the Cake Station.");
                Thread.sleep(Data.getWaitingTime());
                stockRequirementMap.put("cake", true);
                if (!CAKE_STATION.getOrderQueue().isEmpty()) {
                    System.out.println(Thread.currentThread().getName() + " is waiting in line for their Cake.");
                }
                CAKE_STATION.getOrderQueue().put(this);
                cakeCondition.await();
            } else {
                stockRequirementMap.put("cake", false);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            orderLock.unlock();
        }
    }

    /**
     * Checks to see if the current order requires teas,
     * If the current Order has teas, the order is added to the Tea station queue.
     * The thread is set to wait until the Tea has been retrieved.
     */
    public void waitAtTeaQueue() {
        orderLock.lock();
        try {
            if (stockMap.get("tea") > 0) {
                System.out.println(Thread.currentThread().getName() + " is heading to the Tea Station.");
                Thread.sleep(Data.getWaitingTime());
                stockRequirementMap.put("tea", true);
                if (!TEA_STATION.getOrderQueue().isEmpty()) {
                    System.out.println(Thread.currentThread().getName() + " is waiting in line for their Tea.");
                }
                TEA_STATION.getOrderQueue().put(this);
                teaCondition.await();
            } else {
                stockRequirementMap.put("tea", false);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            orderLock.unlock();
        }
    }

    /**
     * Checks to see if specified food is required for the order.
     * @param foodType the food type to check in the order if required.
     * @return true or false value
     */
    public Boolean isFoodRequired(String foodType) {
        orderLock.lock();
        try {
            return stockRequirementMap.get(foodType);
        } finally {
            orderLock.unlock();
        }
    }

    /**
     * Sets the stock required for a food type to either false.
     * Signals the waiting customer thread to continue based on the foodType the customer is waiting for.
     * @param foodType the food type to set to false.
     * @param bool the boolean used to set the stock map to false.
     */
    public void setStockRequired(String foodType, boolean bool) {
        orderLock.lock();
        try {
            if (foodType.equals("coffee")) {
                stockRequirementMap.replace(foodType, bool);
                coffeeCondition.signal();
            } else if (foodType.equals("tea")) {
                stockRequirementMap.replace(foodType, bool);
                teaCondition.signal();
            } else if (foodType.equals("cake")) {
                stockRequirementMap.replace(foodType, bool);
                cakeCondition.signal();
            }
        } finally {
            orderLock.unlock();
        }
    }

    /**
     * Retrieves the amount of stock required for the order.
     * @param foodType the food type being requested.
     * @return
     */
    public int getStock(String foodType) {
        orderLock.lock();
        try {
            return stockMap.get(foodType);
        } finally {
            orderLock.unlock();
        }
    }
}
