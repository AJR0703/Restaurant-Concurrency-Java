package Cafe.GUI;

import Cafe.Cafe;

import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for Creating the GUI and displaying once the code in being run.
 */
public class StartSimulation {

    private JSplitPane splitPane;
    private List<JComponent> startMenuComponents = new ArrayList<>();
    private List<JComponent> runtimeMenuComponents = new ArrayList<>();
    private Font titleFont = new Font("Arial", Font.BOLD, 24);
    private Font subtitleFont = new Font("Arial", Font.BOLD, 14);
    public static Cafe CAFE = new Cafe();

    private StartSimulationMenu startMenu = new StartSimulationMenu();
    private RuntimeModificationMenu runtimeMenu = new RuntimeModificationMenu();

    private JTextArea logArea;

    /**
     * Constructor for the Class.
     * Creates and displays the starting menu with the log panel.
     * Sets a split ratio between the menu and the log panel.
     */
    public StartSimulation() {
        // Create the main frame
        JFrame frame = new JFrame("Cafe Management System");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.getContentPane().setBackground(new Color(80, 52, 31));

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = createLogPanel();

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        startMenuComponents = startMenu.createMenuPanel(this);
        addItems(startMenuComponents, leftPanel);
        formatLabels(startMenuComponents, subtitleFont);
        leftPanel.setBackground(new Color(198, 156, 109));

        // SplitPane to divide menu and log window
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(300); // Set initial size for menu panel
        splitPane.setResizeWeight(0.3); // Left panel takes 30% space

        frame.add(splitPane);
        frame.setVisible(true);
    }

    /**
     * Creates the log panel where actions will be shown.
     * All System.out logs will be added to the log area instead of the console.
     * @return the panel for the text area
     */
    private JPanel createLogPanel() {
        JPanel logPanel = new JPanel(new BorderLayout());
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        logArea.setBackground(new Color(198, 156, 109));
        logArea.setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        logPanel.add(scrollPane, BorderLayout.CENTER);

        // Redirect System.out to log window
        PrintStream printStream = new PrintStream(new Logs(logArea));
        System.setOut(printStream);
        System.setErr(printStream);

        return logPanel;
    }

    /**
     * Adds formatting such as Font size and alignment to all components provided.
     * @param components A list of JComponents that require formatting
     * @param style the style of font, including size and type to set to the Components.
     */
    private void formatLabels(List<JComponent> components, Font style) {
        components.get(0).setAlignmentX(Component.CENTER_ALIGNMENT);
        components.get(0).setFont(titleFont);
        components.get(0).setForeground(Color.BLACK);
        int i = 1;
        while (i < components.size()) {
            if (components.get(i) instanceof JButton) {
                components.get(i).setBackground(new Color(139, 69, 19));
                components.get(i).setForeground(new Color(255, 248, 220));
            } else if (components.get(i) instanceof JLabel) {
                components.get(i).setForeground(Color.BLACK);
            }
            else if (components.get(i) instanceof JSlider){
                components.get(i).setBackground(new Color(198, 156, 109));
                components.get(i).setForeground(Color.BLACK);
            }
            components.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
            components.get(i).setFont(style);
            i++;
        }
    }

    /**
     * Responsible for adding all Components from the list to the menu.
     * @param components List of JComponents.
     * @param menu The JPanel to add the JComponents to.
     */
    private void addItems(List<JComponent> components, JPanel menu) {
        Dimension space = new Dimension(0, 5);
        for (JComponent component : components) {
            menu.add(component);
            menu.add(Box.createRigidArea(space));
        }
    }

    /**
     * Adds the runtime Components from the list of JComponents to the JPanel to be displayed.
     */
    public void addRuntimeMenuPanel() {
        JPanel blankPanel = new JPanel(); // Create a new blank panel
        blankPanel.setLayout(new BoxLayout(blankPanel, BoxLayout.Y_AXIS));

        runtimeMenuComponents= runtimeMenu.createRuntimeMenu();
        addItems(runtimeMenuComponents, blankPanel);
        formatLabels(runtimeMenuComponents, subtitleFont);

        blankPanel.setBackground(new Color(198, 156, 109));
        splitPane.setLeftComponent(blankPanel);

        splitPane.setDividerLocation(300); // Set initial size for menu panel
        splitPane.setResizeWeight(0.3); // Left panel takes 30% space

        // Refresh UI
        splitPane.revalidate();
        splitPane.repaint();
    }

}
