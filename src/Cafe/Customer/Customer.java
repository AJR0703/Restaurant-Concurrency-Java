package Cafe.Customer;

import Cafe.Customer.Actions.CustomerAction;
import SettingsData.Data;

import java.util.*;

/**
 * Customer Class simulates a customer and the actions they will perform.
 * Extends the thread class to allow customers to run concurrently, simulating a real world environment.
 */
public class Customer extends Thread {

    private List<String> possibleActions = new ArrayList<>();
    private Queue<CustomerAction> actionQueue = new LinkedList<>();
    private Random random = new Random();

    /**
     * Constructor for the customer class.
     * Calls methods createActionList and assignActions
     */
    public Customer() {
        createActionList();
        assignActions();
    }

    /**
     * Adds the required Strings for the Customer Actions.
     */
    private void createActionList() {
        possibleActions.add("PlayPiano");
        possibleActions.add("ListenToMusic");
        possibleActions.add("PlaceOrder");
    }

    /**
     * Calls the Action factory class to create three random actions selected from the possibleActions queue.
     */
    private void assignActions() {
        for (int i = 0; i < 3; i++) {
            actionQueue.add(ActionFactory.getAction(possibleActions.get(random.nextInt(possibleActions.size()))));
        }
    }

    /**
     * Removes the action at the head of the queue.
     * @return the newly removed customer action.
     */
    public CustomerAction getNextAction() {
        return actionQueue.poll();
    }

    /**
     * Executes the actions in the Action queue.
     */
    @Override
    public void run() {
        while (!actionQueue.isEmpty()) {
            CustomerAction action = getNextAction();
            if (action != null) {
                action.executeAction();
            }
            try {
                System.out.println(Thread.currentThread().getName() + " is thinking about what they want to do next.");
                Thread.sleep(Data.getWaitingTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " has finished all actions.");
    }

}
