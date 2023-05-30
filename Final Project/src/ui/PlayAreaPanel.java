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
    private boolean isPaused;
    private double guiScale;
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
                 if (e.getKeyCode() == KeyEvent.VK_P)
                    isPaused = !isPaused;

                 repaint();
            }
        });
    }

    /** Called after user presses the Enter key in the Menu */
    public void start(int width, int height, double guiScale) {
        blockSize = width / GameWindow.BLOCKS_WIDTH;
        board = new Board();

        this.guiScale = guiScale;
        this.height = height;
        this.width = width;
        repaint();
    }


    // Graphics Rendering

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        GraphicsUtils utils = new GraphicsUtils(g, guiScale);

        // Draw Background
        utils.drawBackground(width, height);

        // Draw Paused Screen
        if (isPaused) {
            g.setColor(utils.FG_TEXT_COLOR);
            utils.drawCenteredText("PAUSED", utils.HEADER_FONT, width, height / 2);
            utils.drawCenteredText(
                    "Press P to Unpause.",
                    utils.PLAIN_FONT, width,
                    height / 2 + utils.HEADER_FONT.getSize() + 10
            );

            return;
        }

        // Draw Foreground Blocks
        int columns = GameWindow.BLOCKS_WIDTH;
        int rows = GameWindow.BLOCKS_HEIGHT;

        for (int col = 0; col < columns; col++) {
            for (int row = 0; row < rows; row++) {

                // Draw the Border
                if (row == 0 || row == board.boardHeight + 1 ||
                    col == 0 || col == board.boardWidth + 1 ||
                    col == columns - 1) {

                    int xPos = col * blockSize;
                    int yPos = row * blockSize;

                    g.drawImage(
                            ResourceManager.loadBlockTexture("border"),
                            xPos, yPos, blockSize, blockSize, null
                    );
                }
            }
        }
    }
}
