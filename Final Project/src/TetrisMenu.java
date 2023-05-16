import javax.swing.*;
import java.awt.*;

/** The Menu and maybe settings */
public class TetrisMenu extends JFrame {
    public TetrisMenu() {
        // JFrame Setup
        super("Tetris Menu");
        setSize(400, 300);
        setResizable(false);
        setLocationRelativeTo(null); // Null = Center of the screen
        setDefaultCloseOperation(EXIT_ON_CLOSE); // So it would close and not just go in the background
        setIconImage(Resources.loadImage("icon.png"));

        // Components setup
        JLabel titleLabel = new JLabel("Tetris");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Monocraft", Font.BOLD, 60));

        JButton startButton = createButton("Start");

        // Add everything to the panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(25));
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(75));
        panel.add(startButton);
        getContentPane().add(panel); // Then add the panel to the JFrame

        setVisible(true);
    }

    /** Creates a JButton with all the required settings */
    private JButton createButton(String title) {
        JButton button = new JButton(title);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false); // The little blue border around the text.
//        button.setSize(100, 100);

        return button;
    }
}
