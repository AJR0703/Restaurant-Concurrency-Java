package Cafe.Customer;

import Cafe.Customer.Actions.CustomerAction;
import Cafe.Customer.Actions.ListenToMusic;
import Cafe.Customer.Actions.PlaceOrder;
import Cafe.Customer.Actions.PlayPiano;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for creating CustomerActions.
 * Implements the Factory pattern to do so.
 */
public class ActionFactory {

    private static final Map<String, CustomerAction> customerActions = new HashMap<>();

    /**
     * If the action already exists in the hashmap, the existing action will be returned.
     * @param action string matching the key of the hashmap.
     * @return Returns either a newly created customer action or an existing customer action.
     */
    public static CustomerAction getAction(String action) {
        String actionName = action.toLowerCase();
        if (!customerActions.containsKey(actionName)) {
            customerActions.put(actionName, assignCustomerAction(actionName));
        }
        return customerActions.get(actionName);
    }

    /**
     * creates a new Customer action based on the string provided.
     * @param actionName the string for the customer action wanted.
     * @return
     */
    private static CustomerAction assignCustomerAction(String actionName) {
        switch (actionName.toLowerCase()) {
            case "placeorder":
                return new PlaceOrder();
            case "playpiano":
                return new PlayPiano();
            case "listentomusic":
                return new ListenToMusic();
            default:
                throw new IllegalArgumentException("This food type does not exist");
        }
    }
}
