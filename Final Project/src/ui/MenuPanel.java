package ui;

import common.GraphicsUtils;
import common.ResourceManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MenuPanel extends JPanel {
    private final GameWindow gameWindow;

    public MenuPanel(GameWindow GameWindow) {
        setFocusable(true); // A component needs to be focusable to use a KeyListener
        this.gameWindow = GameWindow;

        // Add KeyListener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_ENTER) return;

                GameWindow.setCurrentPanel("PlayAreaPanel");
                GameWindow.playAreaPanel.start(GameWindow.getScreenWidth(), GameWindow.getScreenHeight());
                GameWindow.playAreaPanel.grabFocus();
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        // When `ui.Window.pack()` is called it resizes itself accurately according to the size of
        // its child component's (this) size. Which is weirdly more accurate than `ui.Window.setSize(w,h)`.
        // This hacky solution prevents the weird out of bounds draw issues from occurring.
        return new Dimension(gameWindow.getScreenWidth(), gameWindow.getScreenHeight());
    }


    // Graphics Rendering because the normal swing UI sucks

    @Override
    public void paintComponent(Graphics graphics) {
        // `graphics` is actually an instance of Graphics2D since you cannot
        // create an instance of Graphics (it's an abstract class)
        Graphics2D g = (Graphics2D) graphics;
        GraphicsUtils utils = new GraphicsUtils(g);

        int width = gameWindow.getScreenWidth();
        int height = gameWindow.getScreenHeight();

        // Draw Background
        utils.drawBackground(width, height);

        // Draw Foreground
        Font titleFont = new Font("Monocraft", Font.BOLD, (int) (50 * gameWindow.guiScale));
        Font subTitleFont = new Font("Monocraft", Font.PLAIN, (int) (15 * gameWindow.guiScale));

        g.setColor(utils.FG_TEXT_COLOR);
        utils.drawCenteredText("BLOCKS", titleFont, width, titleFont.getSize() + 20); // Title
        utils.drawCenteredText("STACKING GAME", subTitleFont, width, titleFont.getSize() + subTitleFont.getSize() + 25); // Sub-Title

        int imageSize = (int) (100 * gameWindow.guiScale);
        int imageOffset = imageSize / 2;
        g.drawImage(
            ResourceManager.loadImage("enter-key.png"),
            width / 2 - imageOffset, height / 2 - imageOffset, // X and Y Position
            imageSize, imageSize, // X and Y Size
            null
        );

        utils.drawCenteredText(
            "Press ENTER to Start.",
            new Font("Monocraft", Font.BOLD, (int) (15 * gameWindow.guiScale)),
            width, height / 2 + imageOffset + (int) (20 * gameWindow.guiScale)
        );
    }
}
