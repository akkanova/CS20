import javax.swing.*;

public class GameMenu extends JFrame {
    // Using the IntelliJ Idea GUI Designer, most of the Component (panel, title, startButton,
    // etc.) setup are automatically pre-generated during compile time. Except for event listeners.
    private JLabel title;
    private JPanel panel;
    private JButton startButton;

    public GameMenu() {
        // JFrame Setup
        super("Tetris Menu");
        setSize(400, 300);
        setResizable(false);
        setLocationRelativeTo(null); // Null = Center of the screen
        setDefaultCloseOperation(EXIT_ON_CLOSE); // So it would close and not just go in the background
        setIconImage(Resources.loadImage("icon.png"));

        setContentPane(panel);
        setVisible(true);
    }
}
