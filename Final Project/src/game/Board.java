package game;

import common.Misc;

/** Board holding tetromino and blocks positions */
public class Board {
    private Tetromino currentPiece;
    private Tetromino.Shape nextPieceShape;

    private final int boardWidth;
    private final int boardHeight;

    public Board() {
        this(10, 22);
    }

    public Board(int width, int height) {
        boardHeight = height;
        boardWidth = width;
    }

    public Tetromino getCurrentPiece() { return currentPiece; }
    public Tetromino.Shape getNextPieceShape() { return nextPieceShape; }

    public void generateNextPiece() {
        currentPiece = new Tetromino(nextPieceShape);
        nextPieceShape = Misc.getRandomEnumValue(Tetromino.Shape.class);
    }

    public boolean isValidMove(int newX, int newY) {
        return false;
    }

    public void moveCurrentPiece(int newX, int newY) {

    }
}
