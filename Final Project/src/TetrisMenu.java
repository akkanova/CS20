import javax.swing.*;
import java.awt.*;

/** The Menu and maybe settings */
public class TetrisMenu extends JFrame {
    public TetrisMenu() {
        super("Tetris Menu");

        // JFrame Setup
        setSize(400, 300);
        setLocationRelativeTo(null); // Null = Center of the screen
        setDefaultCloseOperation(EXIT_ON_CLOSE); // So it would close and not just go in the background
        setIconImage(Resources.loadImage("icon.png"));

        // Components setup
        JLabel titleLabel = new JLabel("Tetris");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton startButton = new JButton("Start");

        // Add everything to the panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(titleLabel);
        panel.add(startButton);
        getContentPane().add(panel); // Then add the panel to the JFrame

        setVisible(true);
    }
}
