package Cafe.GUI;

import SettingsData.Data;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static Cafe.GUI.StartSimulation.CAFE;

/**
 * Creates a list for all items that should be added to the start menu for the simulation.
 */
public class StartSimulationMenu {

    private List<JComponent> cafeComponents = new ArrayList<>();

    /**
     * Adds and returns a List of JComponents that should be added to the startMenu.
     * @param menu the class in which the Menu is created.
     * @return a list of JComponents
     */
    public List<JComponent> createMenuPanel(StartSimulation menu) {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        JLabel menuTitle = new JLabel("Cafe Settings");

        JLabel customersLabel = new JLabel("Number of Customers:");
        JSlider customersSlider = new JSlider(1, 50, 5);
        JLabel numOfCustomersLabel = new JLabel(String.valueOf(customersSlider.getValue()));
        customersSlider.addChangeListener(e -> numOfCustomersLabel.setText(String.valueOf(customersSlider.getValue())));

        JLabel coffeeLabel = new JLabel("Number of Coffees");
        JSlider coffeeStockSlider = new JSlider(5, 30, 5);
        JLabel numOfCoffeesLabel = new JLabel(String.valueOf(coffeeStockSlider.getValue()));
        coffeeStockSlider.addChangeListener(e -> numOfCoffeesLabel.setText(String.valueOf(coffeeStockSlider.getValue())));

        JLabel cakeLabel = new JLabel("Number of Cakes");
        JSlider cakeStockSlider = new JSlider(5, 30, 5);
        JLabel numOfCakesLabel = new JLabel(String.valueOf(cakeStockSlider.getValue()));
        cakeStockSlider.addChangeListener(e -> numOfCakesLabel.setText(String.valueOf(cakeStockSlider.getValue())));

        JLabel teaLabel = new JLabel("Number of Teas:");
        JSlider teaStockSlider = new JSlider(5, 30, 5);
        JLabel numOfTeasLabel = new JLabel(String.valueOf(teaStockSlider.getValue()));
        teaStockSlider.addChangeListener(e -> numOfTeasLabel.setText(String.valueOf(teaStockSlider.getValue())));

        JLabel coffeeStaffLabel = new JLabel("Number of Staff assigned at Coffee Station:");
        JSlider coffeeStaffSlider = new JSlider(1, 5, 1);
        JLabel numOfCoffeeStaffLabel = new JLabel(String.valueOf(coffeeStaffSlider.getValue()));
        coffeeStaffSlider.addChangeListener(e -> numOfCoffeeStaffLabel.setText(String.valueOf(coffeeStaffSlider.getValue())));

        JLabel teaStaffLabel = new JLabel("Number of Staff assigned at Tea Station:");
        JSlider teaStaffSlider = new JSlider(1, 5, 1);
        JLabel numOfTeaStaffLabel = new JLabel(String.valueOf(teaStaffSlider.getValue()));
        teaStaffSlider.addChangeListener(e -> numOfTeaStaffLabel.setText(String.valueOf(teaStaffSlider.getValue())));

        JLabel cakeStaffLabel = new JLabel("Number of Staff assigned at Cake Station:");
        JSlider cakeStaffSlider = new JSlider(1, 5, 1);
        JLabel numOfCakeStaffLabel = new JLabel(String.valueOf(cakeStaffSlider.getValue()));
        cakeStaffSlider.addChangeListener(e -> numOfCakeStaffLabel.setText(String.valueOf(cakeStaffSlider.getValue())));

        JButton submitButton = new JButton("Start Simulation");

        cafeComponents.add(menuTitle);

        cafeComponents.add(customersLabel);
        cafeComponents.add(customersSlider);
        cafeComponents.add(numOfCustomersLabel);

        cafeComponents.add(coffeeLabel);
        cafeComponents.add(coffeeStockSlider);
        cafeComponents.add(numOfCoffeesLabel);

        cafeComponents.add(cakeLabel);
        cafeComponents.add(cakeStockSlider);
        cafeComponents.add(numOfCakesLabel);

        cafeComponents.add(teaLabel);
        cafeComponents.add(teaStockSlider);
        cafeComponents.add(numOfTeasLabel);

        cafeComponents.add(coffeeStaffLabel);
        cafeComponents.add(coffeeStaffSlider);
        cafeComponents.add(numOfCoffeeStaffLabel);

        cafeComponents.add(teaStaffLabel);
        cafeComponents.add(teaStaffSlider);
        cafeComponents.add(numOfTeaStaffLabel);

        cafeComponents.add(cakeStaffLabel);
        cafeComponents.add(cakeStaffSlider);
        cafeComponents.add(numOfCakeStaffLabel);

        cafeComponents.add(submitButton);

        submitButton.addActionListener(e -> {
            Data.setCustomerCount(customersSlider.getValue());
            Data.setInitialCoffeeStock(coffeeStockSlider.getValue());
            Data.setInitialCakeStock(cakeStockSlider.getValue());
            Data.setInitialTeaStock(teaStockSlider.getValue());
            Data.addStaff("coffee", coffeeStaffSlider.getValue());
            Data.addStaff("tea", teaStaffSlider.getValue());
            Data.addStaff("cake", cakeStaffSlider.getValue());

            submitButton.setEnabled(false);

            Data.outputStartingValue();
            CAFE.open();
            menu.addRuntimeMenuPanel();
        });

        return cafeComponents;
    }
}
