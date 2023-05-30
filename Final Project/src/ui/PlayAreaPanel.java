package ui;

import common.GraphicsUtils;
import common.ResourceManager;
import game.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayAreaPanel extends JPanel {
    private Board board;
    private int blockSize;
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
        this.blockSize = width / GameWindow.BLOCKS_WIDTH;
        this.board = new Board();
        this.height = height;
        this.width = width;
        repaint();
    }


    // Graphics Rendering

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        GraphicsUtils utils = new GraphicsUtils(g);

        // Draw Background
        utils.drawBackground(width, height);

        // Draw the Border
        int columns = board.boardWidth + 1;
        int rows = board.boardHeight + 1;

        for (int col = -1; col < columns; col++) {
            for (int row = -1; row < rows; row++) {
                if (row == -1 || row == rows - 1 || col == -1 || col == columns - 1) {
                    int xPos = col * blockSize;
                    int yPos = row * blockSize;
                    g.drawImage(
                            ResourceManager.loadBlockTexture("border"),
                            xPos, yPos, blockSize, blockSize,
                            null
                    );
                }
            }
        }
    }
}
