package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/** Board holding tetrominoes and main game logic */
public class Board {
    private final ArrayList<Tetromino.Shape> bag; // Contains the Next pieces
    private final Tetromino.Shape[][] grid; // Static Blocks location (For collision and rendering)

    private Tetromino.Shape heldPiece;
    private Tetromino currentPiece;
    private GameState gameState;
    private boolean heldPieceLock; // You can only switch with the held piece once per block.
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
        // Keep the bag full
        if (bag.size() < 5) {
            // List out all possible Tetromino shapes
            ArrayList<Tetromino.Shape> allShapes = new ArrayList<>();
            Collections.addAll(allShapes, Tetromino.Shape.values());
            // The bag should contain all possible shapes
            // With a completely random sequence they appear in.
            Collections.shuffle(allShapes);
            Collections.addAll(bag, allShapes.toArray(Tetromino.Shape[]::new));
        }

        // Replace the currentPiece with a new shape from the bag
        initializeWithPiece(bag.get(0));
        bag.remove(0);
    }

    public void switchWithHeldPiece() {
        // Can only be used once per block.
        // Unlocked when currentPiece is attached to the grid.
        if (heldPieceLock) return;
        heldPieceLock = true;

        Tetromino.Shape currentPieceShape = currentPiece.getShape();
        if (heldPiece == null) {
            // At the start of the game heldPiece is null
            // so, we can't really switch it with the currentPiece.
            heldPiece = currentPieceShape;
            generateNewPiece();
            return;
        }

        initializeWithPiece(heldPiece);
        heldPiece = currentPieceShape;
    }
    
    /** Initialize the currentPiece with a new Shape */
    private void initializeWithPiece(Tetromino.Shape shape) {
        currentPiece = null;
        currentPiece = new Tetromino(shape);

        int yPos = 1;
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
        ArrayList<Integer> rowsToClear = new ArrayList<>();
        for (int row = boardHeight - 1; row > 0; row--) {
            // Count how many columns of that row is filled
            int colFilled = 0;
            for (Tetromino.Shape col : grid[row])
                if (col != null) colFilled++;

            // If the Row is full add it to the rowsToClear queue
            if (colFilled >= boardWidth)
                rowsToClear.add(row);
        }

        // Starting from the top-most full row
        Collections.reverse(rowsToClear);
        for (int fullRowIndex : rowsToClear) {
            score += 100 + (50 * rowsToClear.size());

            // Go up from the full row and move all the subsequent row down,
            // Which will overwrite the full row.
            for (int row = fullRowIndex - 1; row > 0; row--)
                System.arraycopy(grid[row], 0, grid[row + 1], 0, boardWidth);
        }
    }

    /** Check whether the new coordinates overlap with other blocks or out of bounds */
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


    // Movements
    
    public void dropPiece() {
        // The default value should be the currentPiece
        dropPiece(currentPiece, true);
    }

    private void dropPiece(Tetromino target, boolean addScore) {
        // Keep Dropping the Piece
        // until it collides with something
        while (true)
            if (movePiece(target, 0, 1, addScore)) break;
    }

    /**
     * If forced is true, the invocation of the function is considered a
     * user input and a point is added to the user's score.
     * */
    public void movePieceDown(boolean forced) { movePiece(currentPiece, 0, 1, forced); }
    public void movePieceRight() { movePiece(currentPiece, 1, 0, false); }
    public void movePieceLeft() { movePiece(currentPiece, -1, 0, false); }

    /** Returns true if it collided with something */
    private boolean movePiece(Tetromino target, int deltaX, int deltaY, boolean addScore) {
        Point[] newCoordinates = target.translate(deltaX, deltaY);
        boolean doesCollideWithGrid = doesCollide(newCoordinates);

        if (!doesCollideWithGrid) {
            if (addScore) score += 1;
            Point currentPosition = target.getCurrentPosition();
            target.setCurrentPosition(
                currentPosition.x + deltaX,
                currentPosition.y + deltaY
            );
        }

        // If the provided tetromino is the currentPiece and not the
        // Shadow tetromino, attach it to the static block grid.
        if (target.equals(currentPiece) && doesCollideWithGrid && deltaY > 0) {
            // Convert the current piece blocks into a static block within the grid
            for (Point block : target.getBlockCoordinates())
                grid[block.y][block.x] = currentPiece.getShape();
            
            cleanupRows();
            generateNewPiece();
            heldPieceLock = false; // Unlock Held Piece Lock
        }

        return doesCollideWithGrid;
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
    
    public Tetromino.Shape getHeldPiece() {
        return heldPiece;
    }

    public Tetromino.Shape getBlockShapeAt(int x, int y) {
        return grid[y][x];
    }

    public Tetromino.Shape[] getNextPieces() {
        return bag.toArray(Tetromino.Shape[]::new);
    }

    public GameState getGameState() {
        return gameState;
    }

    public Tetromino getCurrentPiece() {
        return currentPiece;
    }

    /** Shadow refers to the predicted landing place of the currentPiece */
    public Tetromino getCurrentPieceShadow() {
        Tetromino shadow = currentPiece.duplicate();
        dropPiece(shadow, false);
        return shadow;
    }

    public int getScore() {
        return score;
    }
}
