package ui;

import common.ResourceManager;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    // The panel used for switching between MenuPanel and PlayAreaPanel
    private final JPanel cards = new JPanel(new CardLayout());
    public final PlayAreaPanel playAreaPanel;
    public final MenuPanel menuPanel;

    public static final int BLOCKS_HEIGHT = 24;
    public static final int BLOCKS_WIDTH = 17;
    // Dictates the size of the UI while retaining UI component size ratios
    public double guiScale;

    public GameWindow() {
        // Set GUI scaling based on your screen height
        this((int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 24 / 20));
    }

    public GameWindow(int guiScale) {
        // JFrame Setup
        super("Blocks: Stacking Game");
        setResizable(false);
        setLocationRelativeTo(null); // null = Center of the screen
        setDefaultCloseOperation(EXIT_ON_CLOSE); // So it would close, instead of going in the background
        setIconImage(ResourceManager.loadImage("icon.png"));

        // Panels Setup
        this.menuPanel = new MenuPanel(this);
        this.playAreaPanel = new PlayAreaPanel();
        this.guiScale = guiScale;

        // Add Panels
        cards.add(menuPanel, "MenuPanel");
        cards.add(playAreaPanel, "PlayAreaPanel");

        // Set the initial viewable panel to menu
        setCurrentPanel("MenuPanel");
        menuPanel.grabFocus(); // Allow for key listener

        getContentPane().add(cards);
        pack();
        setVisible(true);
    }

    /** Switch the current visible panel */
    public void setCurrentPanel(String panelName) {
        CardLayout layout = (CardLayout) cards.getLayout();
        layout.show(cards, panelName);
    }

    // Screen width and heights calculated based on GUI scaling modifier
    public int getScreenWidth() { return (int) (BLOCKS_WIDTH * (guiScale * 20)); }
    public int getScreenHeight() { return (int) (BLOCKS_HEIGHT * (guiScale * 20)); }
}
