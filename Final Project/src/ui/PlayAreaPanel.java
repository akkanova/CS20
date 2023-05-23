package ui;

import common.GraphicsUtils;
import game.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayAreaPanel extends JPanel {
    private Board board;
    private int height;
    private int width;

    public PlayAreaPanel() {
        // Use double buffered, which uses additional memory
        // space to achieve fast, flicker-free updates
        super(true);
        setFocusable(true); // A component needs to be focusable to use a KeyListener

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // if (e.getKeyCode() == KeyEvent.VK_P)
                    // pause game
            }
        });
    }

    /** Called after user presses the Enter key in the Menu */
    public void start(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new Board();
    }


    // Graphics Rendering

    @Override
    public void paint(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        GraphicsUtils utils = new GraphicsUtils(g);

        // Draw Background
        utils.drawBackground(width, height);
    }
}
