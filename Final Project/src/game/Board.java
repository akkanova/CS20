package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/** Board holding tetrominoes and main game logic */
public class Board {
    private final ArrayList<Tetromino.Shape> bag; // Next pieces
    private final Tetromino.Shape[][] grid; // Static Blocks location (For collision and rendering)

    private Tetromino currentPiece;
    private GameState gameState;
    private int score;

    public final int boardWidth;
    public final int boardHeight;

    public enum GameState {
        Playing,
        Paused,
        Stopped
    }

    public Board(int width, int height) {
        gameState = GameState.Playing;
        boardHeight = height;
        boardWidth = width;
        score = 0;

        grid = new Tetromino.Shape[width][height];
        bag = new ArrayList<>();
        generateNewPiece();
    }

    /** Pause and Unpause the game */
    public void pause() {
        gameState = gameState == GameState.Playing
            ? GameState.Paused
            : GameState.Playing;
    }

    public void generateNewPiece() {
        if (bag.isEmpty()) {
            // The bag should contain all possible shapes
            // With a completely random sequence they appear in.
            Collections.addAll(bag, Tetromino.Shape.values());
            Collections.shuffle(bag);
        }

        currentPiece = new Tetromino(bag.get(0));
        bag.remove(0);

        Point[] initialPosition = currentPiece.translate(boardWidth / 2, 2);
        // If It can't put the tetromino in the initial position it
        // usually means Game Over.
        if (!doesCollide(initialPosition))
            currentPiece.setBlockCoordinates(initialPosition);

        else gameState = GameState.Stopped;
    }

    /** Convert the current piece into a static block within the grid */
    public void attachCurrentPieceToGrid() {
        for (Point block : currentPiece.getBlocksCoordinates())
            grid[block.x][block.y] = currentPiece.getShape();

        currentPiece = null;
        generateNewPiece();
    }

    /** Check whether the new coordinates overlap with other blocks */
    public boolean doesCollide(Point[] newCoordinates) {
        for (Point block : newCoordinates) {

            if (block.x < 0 || block.x > boardWidth - 1 ||
                block.y < 0 || block.y > boardHeight - 1)
                return true;

            if (grid[block.x][block.y] != null)
                return true;
        }

        return false;
    }


    // Movement

    /**
     * If forced is true, the invocation of the function is considered a
     * user input and a point is added to the user's score.
     * */
    public void movePieceDown(boolean forced) { movePiece(0, 1, forced); }
    public void movePieceRight() { movePiece(1, 0, false); }
    public void movePieceLeft() { movePiece(-1, 0, false); }

    /** Returns true if the move was successful */
    private void movePiece(int deltaX, int deltaY, boolean addScore) {
        Point[] newCoordinates = currentPiece.translate(deltaX, deltaY);
        boolean doesCollideWithGrid = doesCollide(newCoordinates);

        if (!doesCollideWithGrid)
            currentPiece.setBlockCoordinates(newCoordinates);

        if (doesCollideWithGrid && deltaY > 0) {
            attachCurrentPieceToGrid();
            if (addScore) score += 1;
        }
    }

    public void rotatePieceCounterClockwise() { rotatePiece(-1, 1); }
    public void rotatePieceClockWise() { rotatePiece(1, -1); }

    private void rotatePiece(int xDirection, int yDirection) {
        if (currentPiece == null) return;
        // Rotation doesn't make sense for a square.
        if (currentPiece.getShape() == Tetromino.Shape.Square) return;

        Point[] newOffsets = currentPiece.rotate(xDirection, yDirection);
        Point[] oldCoordinates = currentPiece.getBlocksCoordinates();
        Point[] newCoordinates = Tetromino.getEmptyPointArray();

        for (int blockIndex = 0; blockIndex < 4; blockIndex++) {
            newCoordinates[blockIndex].x = newOffsets[blockIndex].x + oldCoordinates[blockIndex].x;
            newCoordinates[blockIndex].y = newOffsets[blockIndex].y + oldCoordinates[blockIndex].y;
        }

        if (!doesCollide(newCoordinates)) {
            currentPiece.setBlocksOffsets(newOffsets);
            currentPiece.setBlockCoordinates(newCoordinates);
        }
    }


    // Getters

    public Tetromino.Shape getBlock(int x, int y) {
        return grid[x][y];
    }

    public GameState getGameState() {
        return gameState;
    }

    public Tetromino getCurrentPiece() {
        return currentPiece;
    }

    public Tetromino.Shape getNextPiece() {
        if (bag.size() < 1) generateNewPiece();
        return bag.get(0);
    }

    public int getScore() {
        return score;
    }
}
