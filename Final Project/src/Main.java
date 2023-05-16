import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // To ensure that TetrisMenu is created on the "Swing UI" Thread, (Which might be
        // different from this one, running main) we use this function, and to also avoid
        // abnormal issues that can occur with Java's Multi-threaded asynchronicity
        SwingUtilities.invokeLater(() -> {
            // Load all the resources first.
            Resources.load();
            new TetrisMenu();
        });
    }
}