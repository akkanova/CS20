package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/** Board holding tetromino and blocks positions */
public class Board {
    private ArrayList<Tetromino.Shape> bag; // Next pieces
    private Tetromino.Shape[][] grid; // Blocks location (For collision and rendering)
    private Tetromino currentPiece;

    public final int boardWidth;
    public final int boardHeight;

    public Board() { this(10, 22); }

    public Board(int width, int height) {
        boardHeight = height;
        boardWidth = width;

        grid = new Tetromino.Shape[width][height];
        bag = new ArrayList<>();
    }

    public void generateNewPiece() {
        if (bag.isEmpty()) {
            // The bag should contain all possible shapes
            // With the sequence they appear in completely random.
            Collections.addAll(bag, Tetromino.Shape.values());
            Collections.shuffle(bag);
        }

        currentPiece = new Tetromino(bag.get(0));
        bag.remove(0);
    }


    // Movement

    // `rotateLeft` seems like a more straight forward and easier
    // to understand name than `rotateCounterClockwise`
    public void rotateLeft() { rotate(-1, 1); }
    public void rotateRight() { rotate(1, -1); }

    private void rotate(int xDirection, int yDirection) {
        // Explanation for how this all works:
        // (visualize in Desmos for more clarity)
        // Take for example point { -5, -5 } to rotate it right (Clockwise),
        // we set the new value of X to the value of it's Y. So now it's { -5, _ }.
        // Then we set the new value of Y to the value it's old X times negative.
        // So now it's pos is { -5, 5 }. To rotate to the left (Counter-Clockwise)
        // we don't multiply the new value of Y with a negative, instead we do it for
        // the new value of X instead.

        Point[] oldCoordinates = currentPiece.getBlocksCoordinates();
        Point[] newCoordinates = new Point[4];

        for (int blockIndex = 0; blockIndex < 4; blockIndex++) {
            newCoordinates[blockIndex].x = oldCoordinates[blockIndex].y * xDirection;
            newCoordinates[blockIndex].y = oldCoordinates[blockIndex].x * yDirection;
        }

        // Collision Detection Here

        currentPiece.setBlocksCoordinates(newCoordinates);
    }


    // Getters

    public Tetromino.Shape getBlock(int x, int y) {
        return grid[x][y];
    }

    public Tetromino.Shape getNextPiece() {
        if (bag.size() < 1) generateNewPiece();
        return bag.get(0);
    }
}
