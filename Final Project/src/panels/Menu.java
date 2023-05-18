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

    @Override
    public void paint(Graphics g) {
        g.drawRect(0, 0, width - 10, height - 10);
//        g.setColor(new Color());
//        g.fillRect(0, 0, );
    }
}
