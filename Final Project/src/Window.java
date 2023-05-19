import panels.PlayArea;
import panels.Menu;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public final CardLayout layout = new CardLayout();
    public final PlayArea playAreaPanel;
    public final Menu menuPanel;

    public Window() {
        this(300, 400);
    }

    public Window(int width, int height) {
        // JFrame Setup
        super("Blocks: Stacking Game");
        setLayout(layout); // CardLayout allows for switching of panels
        setResizable(false);
        setLocationRelativeTo(null); // null = Center of the screen
        setDefaultCloseOperation(EXIT_ON_CLOSE); // So it would close, instead of going in the background
        setIconImage(Resources.loadImage("icon.png"));

        // Panels Setup
        menuPanel = new Menu(width, height);
        playAreaPanel = new PlayArea();

        // Listen to Game Start Event
        menuPanel.grabFocus();
        menuPanel.setGameStartListener(() -> {
            // Change panel, and start the other one.
            layout.next(this);
            playAreaPanel.grabFocus();
        });

        // Add Panels
        add(menuPanel);
        add(playAreaPanel);
        pack(); // Set Size to the panel size

        setVisible(true);
    }
}
