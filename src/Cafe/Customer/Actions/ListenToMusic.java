package Cafe.Customer.Actions;

import SettingsData.Data;

/**
 * This class implements the Customer action interface.
 * One of three actions required for the customer to choose from.
 */
public class ListenToMusic implements CustomerAction {

    /**
     * Overrides the CustomerAction class.
     * Thread will sleep for a certain time to simulate listening to music.
     */
    @Override
    public void executeAction() {
        try {
            System.out.println(Thread.currentThread().getName() + " is now Listening to Music.");
            Thread.sleep(Data.getWaitingTime());
            System.out.println(Thread.currentThread().getName() + " has finished Listening to Music.\n");
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
