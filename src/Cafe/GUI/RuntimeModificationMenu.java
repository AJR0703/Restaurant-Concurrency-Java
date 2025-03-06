package Cafe.GUI;

import SettingsData.Data;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Cafe.GUI.Simulation.CAFE;

/**
 * Creates a Runtime Menu, used modifying/ adding objects during runtime.
 */
public class RuntimeModificationMenu {

    private List<JComponent> runtimeMenuComponents = new ArrayList<>();
    private Font subtitleFont = new Font("Arial", Font.BOLD, 20);

    /**
     * Returns a list for all items that should be added to the runtime menu for the simulation.
     */
    public List<JComponent> createRuntimeMenu() {

        JLabel menuTitle = new JLabel("Runtime Settings");

        JPanel customerPanel = createLabeledSlider("Number Of Customers to add:", 5, 50, 5);
        JButton submitButton = new JButton("Add Customers");
        submitButton.addActionListener(e -> {
            Data.setCustomerCount(retrieveJSlider(customerPanel).getValue());

            CAFE.addNewCustomers();
        });

        JPanel executionSpeedPanel = createLabeledSlider("Modify Delay (Milliseconds):", 1, 5000, Data.getWaitingTime());
        retrieveJSlider(executionSpeedPanel).addChangeListener(e -> Data.setWaitingTime(retrieveJSlider(executionSpeedPanel).getValue()));

        return Arrays.asList(menuTitle, customerPanel,submitButton, executionSpeedPanel);
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

    /**
     * Retrieves the Slider from each JPanel
     */
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
