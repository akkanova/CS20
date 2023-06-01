package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;

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

        grid = new Tetromino.Shape[height][width];
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

        int yPos = 2;
        int xPos = boardWidth / 2;
        Point[] initialPosition = currentPiece.translate(xPos, yPos);

        // If It can't put the tetromino in the initial position it
        // usually means Game Over.
        if (!doesCollide(initialPosition))
            currentPiece.setCurrentPosition(xPos, yPos);

        else gameState = GameState.Stopped;
    }

    /** Remove Full rows */
    public void cleanupRows() {
        Consumer<Integer> collapseRow = (Integer rowIndex) -> {
            // Going up the grid to move everything down;
            for (int row = rowIndex - 1; row > 0; row--) {
                System.arraycopy(grid[row], 0, grid[row + 1], 0, boardWidth);
            }
        };

        for (int row = boardHeight - 1; row > 0; row--) {
            // Count how many columns of that row is filled
            int colFilled = 0;
            for (Tetromino.Shape col : grid[row])
                if (col != null) colFilled++;

            // If the Row is full collapse all the row above it
            if (colFilled >= boardWidth) {

                // To do make a queue of rows to clear, and do that instead;

                collapseRow.accept(row);
                score += 100;
            }
        }
    }

    /** Convert the current piece into a static block within the grid */
    public void attachCurrentPieceToGrid() {
        for (Point block : currentPiece.getBlockCoordinates())
            grid[block.y][block.x] = currentPiece.getShape();

        currentPiece = null;
        cleanupRows();
        generateNewPiece();
    }

    /** Check whether the new coordinates overlap with other blocks */
    public boolean doesCollide(Point[] newCoordinates) {
        for (Point block : newCoordinates) {

            if (block.x < 0 || block.x > boardWidth - 1 ||
                block.y < 0 || block.y > boardHeight - 1)
                return true;

            if (grid[block.y][block.x] != null)
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
        Point currentPosition = currentPiece.getCurrentPosition();

        boolean doesCollideWithGrid = doesCollide(newCoordinates);

        if (!doesCollideWithGrid)
            currentPiece.setCurrentPosition(
                currentPosition.x + deltaX,
                currentPosition.y + deltaY
            );

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

        Point currentPosition = currentPiece.getCurrentPosition();
        Point[] newOffsets = currentPiece.rotate(xDirection, yDirection);
        Point[] newCoordinates = Tetromino.addOffsetsAndPosition(newOffsets, currentPosition);

        if (!doesCollide(newCoordinates))
            currentPiece.setBlocksOffsets(newOffsets);
    }


    // Getters

    public Tetromino.Shape getBlock(int x, int y) {
        return grid[y][x];
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
