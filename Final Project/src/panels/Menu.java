package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Menu extends JPanel {
    private Runnable gameStartListener; // Called when Start Button is pressed
    private int height;
    private int width;

    public Menu(int width, int height) {
        // Use double buffered, which uses additional memory
        // space to achieve fast, flicker-free updates
        super(true);
        setFocusable(true); // A component needs to be focusable to use a KeyListener

        this.height = height;
        this.width = width;

        // Add KeyListener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    gameStartListener.run();
            }
        });
    }

    public void setSize(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public void setGameStartListener(Runnable listener) {
        gameStartListener = listener;
    }

    @Override
    public Dimension getPreferredSize() {
        // When `Window.pack()` is called it resizes itself to the size of its child
        // component's (this) size. Which is weirdly more accurate than `Window.setSize(w,h)`,
        // preventing the weird out of bounds draw issues from occurring.
        return new Dimension(width, height);
    }


    // Graphics Rendering because the normal swing UI sucks

    @Override
    public void paint(Graphics g) {
        // g is actually an instance of Graphics2D since you cannot
        // create an instance of Graphics (it's an abstract class)
        Graphics2D graphics = (Graphics2D) g;

        // Draw Background
        graphics.setColor(new Color(54, 57, 63));
        graphics.fillRect(0, 0, width, height);

        // Set Foreground Color
        graphics.setColor(new Color(187, 187, 187));
        paintMenu(graphics);
    }

    private void paintMenu(Graphics2D g) {
        // Draw Title
        Font titleFont = new Font("Monocraft", Font.BOLD, 50);
        drawCenteredText(g, "BLOCKS", titleFont, 70);
    }

    /** Utility function designed to center text */
    private void drawCenteredText(Graphics2D g, String text, Font font, int y) {
        FontMetrics metrics = getFontMetrics(font);
        int x = (width - metrics.stringWidth(text)) / 2;
        g.setFont(font);
        g.drawString(text, x, y);
    }
}
