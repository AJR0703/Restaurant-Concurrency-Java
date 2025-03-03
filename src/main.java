import Cafe.Cafe;
import Cafe.GUI.StartSimulation;

import javax.swing.*;

public class main {

    private static final int numOfCustomers = 7;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StartSimulation::new);
    }

}
