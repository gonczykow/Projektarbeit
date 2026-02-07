import gui.MainFrame;
import javax.swing.*;

/**
 * Startpunkt der Anwendung
 */
public class Main {


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
