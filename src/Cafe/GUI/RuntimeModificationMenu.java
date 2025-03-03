package Cafe.GUI;

import SettingsData.Data;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static Cafe.GUI.StartSimulation.CAFE;

/**
 * Creates a Runtime Menu, used modifying/ adding objects during runtime.
 */
public class RuntimeModificationMenu {

    private List<JComponent> runtimeMenuComponents = new ArrayList<>();

    /**
     * Adds Components to the List of JComponents
     * Functionality such as adding customers and modifying execution time.
     * @return A list of JComponents.
     */
    public List<JComponent> createRuntimeMenu() {

        JLabel menuTitle = new JLabel("Runtime Settings");

        JLabel customersLabel = new JLabel("Add new Customers:");
        JSlider customersSlider = new JSlider(1, 30, 5);
        JLabel numOfCustomersLabel = new JLabel(String.valueOf(customersSlider.getValue()));
        customersSlider.addChangeListener(e -> numOfCustomersLabel.setText(String.valueOf(customersSlider.getValue())));

        JButton submitButton = new JButton("Add Customers");
        submitButton.addActionListener(e -> {
            Data.setCustomerCount(customersSlider.getValue());

            CAFE.addNewCustomers();
        });

        JLabel executionTimeLabel = new JLabel("Modify Execution Time (Fast to Slow):");
        JSlider executionTimeSlider = new JSlider(1, 5000, Data.getWaitingTime());
        executionTimeSlider.addChangeListener(e -> Data.setWaitingTime(executionTimeSlider.getValue()));

        runtimeMenuComponents.add(menuTitle);
        runtimeMenuComponents.add(customersLabel);
        runtimeMenuComponents.add(customersSlider);
        runtimeMenuComponents.add(numOfCustomersLabel);
        runtimeMenuComponents.add(submitButton);
        runtimeMenuComponents.add(executionTimeLabel);
        runtimeMenuComponents.add(executionTimeSlider);


        return runtimeMenuComponents;
    }

}
