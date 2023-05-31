package game;

import java.awt.*;

/**
 * <a href="https://en.wikipedia.org/wiki/Tetromino">
 * Tetromino Definition
 * </a>
 * P.S most Java IDEs support HTML notations (including IntelliJ)
 * */
public class Tetromino {
    private final Shape shape;
    // The x and y positions of each block of a tetromino (4 blocks in total)
    private Point[] blocksCoordinates;

    /**
     * The Distinct shapes of Tetrominoes.
     * <a href="https://en.wikipedia.org/wiki/Tetromino#One-sided_tetrominoes">
     *     More Info
     * </a>
     */
    public enum Shape {
        Straight, // Line
        Square, // Cube
        TShape,
        JShape, // L Shape facing Left
        LShape, // L Shape facing Right (basically a mirror of J)
        SSkew, // Stairs going up to the right
        ZSkew // Stairs going up to the left (mirror of S)
    }

    enum RotationDirection {
        CounterClockwise,
        Clockwise
    }

    public Tetromino(Shape shape) {
        this.shape = shape;
        this.blocksCoordinates = getBlockOffsets(shape);
    }

    /** get block offsets based on Tetromino shape */
    public static Point[] getBlockOffsets(Shape shape) {
        return switch (shape) {
            // I suggest visualizing the points in https://www.desmos.com/calculator, for it to make some sense
            case Straight -> new Point[] { new Point(0,  -1), new Point(0,  0), new Point(0,  1), new Point(0,  2) }; // Vertical Line
            case Square   -> new Point[] { new Point(0,   0), new Point(1,  0), new Point(0,  1), new Point(1,  1) }; // Cube
            case TShape   -> new Point[] { new Point(-1,  0), new Point(0,  0), new Point(1,  0), new Point(0,  1) }; // Upside Down T
            case JShape   -> new Point[] { new Point(-1, -1), new Point(0, -1), new Point(0,  0), new Point(0,  1) }; // J
            case LShape   -> new Point[] { new Point(1,  -1), new Point(0, -1), new Point(0,  0), new Point(0,  1) }; // L
            case SSkew    -> new Point[] { new Point(0,  -1), new Point(0,  0), new Point(1,  0), new Point(1,  1) }; // Stairs going up to the right
            case ZSkew    -> new Point[] { new Point(0, - 1), new Point(0,  0), new Point(-1, 0), new Point(-1, 1) }; // Stairs going up to the left
        };
    }

    // Setters

    public void setBlocksCoordinates(Point[] coordinates) {
        this.blocksCoordinates = coordinates;
    }

    // Getters

    public Point[] getBlocksCoordinates() {
        return this.blocksCoordinates;
    }

    public Tetromino.Shape getShape() {
        return this.shape;
    }
}
