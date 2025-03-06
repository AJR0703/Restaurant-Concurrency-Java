package Cafe.GUI;

import SettingsData.Data;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static Cafe.GUI.Simulation.CAFE;

/**
 * Returns a list for all items that should be added to the start menu for the simulation.
 */
public class StartSimulationMenu {

    private Font subtitleFont = new Font("Arial", Font.BOLD, 20);

    /**
     * Adds and returns a List of JComponents that should be added to the startMenu.
     * @param menu the class in which the Menu is created.
     * @return a list of JComponents
     */
    public List<JComponent> createMenuPanel(Simulation menu) {
        JLabel menuTitle = new JLabel("Cafe Settings");
        menuTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // Center title

        JPanel customerPanel = createLabeledSlider("Number of Customers:", 5, 50, 5);
        JPanel coffeePanel = createLabeledSlider("Number of Coffees:", 1, 300, 5);
        JPanel cakePanel = createLabeledSlider("Number of Cakes:", 1, 300, 5);
        JPanel teaPanel = createLabeledSlider("Number of Teas:", 1, 300, 5);
        JPanel numOfCoffeeStaffPanel = createLabeledSlider("Number of Coffee Staff:", 1, 10, 5);
        JPanel numOfTeaStaffPanel = createLabeledSlider("Number of Tea Staff:", 1, 10, 5);
        JPanel numOfCakeStaffPanel = createLabeledSlider("Number of Cake Staff:", 1, 10, 5);

        customerPanel.setBackground(new Color(198, 156, 109));

        JButton submitButton = new JButton("Start Simulation");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        submitButton.addActionListener(e -> {
                Data.setCustomerCount(retrieveJSlider(customerPanel).getValue());
                Data.setInitialCoffeeStock(retrieveJSlider(coffeePanel).getValue());
                Data.setInitialCakeStock(retrieveJSlider(cakePanel).getValue());
                Data.setInitialTeaStock(retrieveJSlider(teaPanel).getValue());
                Data.addStaff("coffee",retrieveJSlider(numOfCoffeeStaffPanel).getValue());
                Data.addStaff("tea", retrieveJSlider(numOfTeaStaffPanel).getValue());
                Data.addStaff("cake", retrieveJSlider(numOfCakeStaffPanel).getValue());

                menu.addRuntimeMenuPanel();
                CAFE.open();
        });

        return Arrays.asList(menuTitle, customerPanel, coffeePanel, cakePanel, teaPanel, numOfCoffeeStaffPanel,
                numOfTeaStaffPanel, numOfCakeStaffPanel,submitButton);
    }

    /**
     * Helper method to create a JPanel with a label, slider, and value display.
     */
    private JPanel createLabeledSlider(String text, int min, int max, int defaultValue) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(198, 156, 109));

        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        JSlider slider = new JSlider(min, max, defaultValue);
        JLabel valueLabel = new JLabel(String.valueOf(slider.getValue()));

        label.setFont(subtitleFont);
        valueLabel.setFont(subtitleFont);

        slider.addChangeListener(e -> valueLabel.setText(String.valueOf(slider.getValue())));

        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        labelPanel.setBackground(new Color(198, 156, 109));
        slider.setBackground(new Color(198, 156, 109));
        labelPanel.add(label);
        labelPanel.add(valueLabel);

        panel.add(labelPanel);
        panel.add(slider);

        panel.add(Box.createVerticalStrut(10));

        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, panel.getPreferredSize().height));

        return panel;
    }

    private JSlider retrieveJSlider(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JSlider) {
                return (JSlider) component;
            }
        }
        return null;
    }


}
