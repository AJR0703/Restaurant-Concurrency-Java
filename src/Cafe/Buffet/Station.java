package Cafe.Buffet;

import SettingsData.Data;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Station class extends the Thread class as objects will need to run simultaneously.
 * The station will be responsible for storing orders related to the food type.
 * When stock is low, the staff will be notified to restock.
 * The station will also contain shared resources, areas of the code will have mutual exclusion enforced to prevent interference.
 */
public class Station extends Thread {
    private int stock;
    private ReentrantLock lock = new ReentrantLock(true);
    private Condition staffCondition = lock.newCondition();
    private Condition orderCondition = lock.newCondition();
    private String foodType;
    private LinkedBlockingQueue<Order> ORDER_QUEUE;
    private boolean stockLow = false;

    /**
     * Constructor for the Station class.
     * @param startingStock the stock the station will start with.
     * @param numOfStaffAssigned the number of staff objects that should be initialized for the Station.
     * @param foodType the type of food that the station will take orders for.
     */
    public Station(int startingStock, int numOfStaffAssigned, String foodType) {
        this.stock = startingStock;
        this.foodType = foodType;
        this.ORDER_QUEUE = new LinkedBlockingQueue<>();
        assignStaff(numOfStaffAssigned, foodType);
    }

    /**
     * Checks the current stock of the station.
     * If stock is low, the boolean stock low will be set to true and the staff Thread will be notified.
     * @param requestedStock the stock requested by the customer.
     * @return boolean to show if there is enough stock.
     */
    public boolean checkStock(int requestedStock) {
        lock.lock();
        try {
            if (stock - requestedStock <= 0) {
                stockLow = true;
                System.out.println(foodType + " stock is low. A Staff member at the " + Thread.currentThread().getName() + " will be notified.");
                staffCondition.signal();
                return false;
            }
            return true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Sets the current thread to wait while the staff is restocking.
     */
    public void waitForRestock() {
        lock.lock();
        try {
            if (stockLow) {
                orderCondition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Staff thread will pass through this method to increment the stock.
     * boolean stockLow will be set to false after stock has been incremented.
     * @param newStock the stock to increment the current stock by.
     */
    public void restock(int newStock) {
        lock.lock();
        try {
            this.stock += newStock;
            stockLow = false;
            System.out.println(foodType + " stock after restocking: " + stock);
            orderCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Decrements the current stock by the requested stock.
     * @param requestedStock the amount to decrement by.
     */
    public void takeFood(int requestedStock) {
        lock.lock();
        try {
            stock -= requestedStock;
            System.out.println("Took " + requestedStock + " " + foodType + "(s), remaining stock: " + stock);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Initialises staff objects by the amount specified.
     * @param numOfStaff The number of staff to initialise.
     * @param stafftype the type of staff wanted, the thread name is set to the food type.
     */
    private void assignStaff(int numOfStaff, String stafftype) {
        int i = 0;
        while (i < numOfStaff) {
            Staff staff = new Staff(this);
            staff.setName(stafftype + " Staff " + (i + 1));
            staff.start();
            i++;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Staff thread will pass through this method.
     * If the stock low boolean is false, the staff thread will wait.
     */
    public void waitForStockLow() {
        lock.lock();
        try {
            if (!stockLow) {
                staffCondition.await();
            }
            System.out.println(Thread.currentThread().getName() + " has been notified, they will begin restocking.");
            Thread.sleep(Data.getWaitingTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * @return the order queue for the station.
     */
    public LinkedBlockingQueue<Order> getOrderQueue() {
        lock.lock();
        try {
            return ORDER_QUEUE;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Checks the stock.
     * If stock is low, this thread will wait until stock has been replenished.
     * Food is then taken and the food type boolean for the order is set to false.
     * The order is the removed from the queue.
     */
    @Override
    public void run() {
        while (true) {
            try {
                if (!ORDER_QUEUE.isEmpty()) {
                    Order currentOrder = ORDER_QUEUE.peek();
                    if (!checkStock(currentOrder.getStock(foodType))) {
                        waitForRestock();
                    }
                    takeFood(currentOrder.getStock(foodType));
                    currentOrder.setStockRequired(foodType, false);
                    ORDER_QUEUE.take();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
