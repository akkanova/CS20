package game;

import java.util.ArrayList;
import java.util.Collections;

/** Board holding tetromino and blocks positions */
public class Board {
    private ArrayList<Tetromino.Shape> bag; // Next pieces
    private Tetromino.Shape[][] well; // Immovable blocks at the bottom
    private Tetromino currentPiece;

    public final int boardWidth;
    public final int boardHeight;

    public Board() { this(10, 22); }

    public Board(int width, int height) {
        boardHeight = height;
        boardWidth = width;
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

    public Tetromino.Shape getNextPiece() {
        if (bag.size() < 1) generateNewPiece();
        return bag.get(0);
    }


}
