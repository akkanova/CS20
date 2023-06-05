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

    // The x and y location of this piece
    // relative to the grid
    private final Point currentPosition;

    // The x and y offsets of each block of a tetromino
    // (4 blocks in total) relative to `currentPosition`,
    // used for rotation calculation
    private Point[] blocksOffsets;


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

    public Tetromino(Shape shape) {
        this.shape = shape;
        this.currentPosition = new Point();
        this.blocksOffsets = getBlockOffsets(shape);
    }

    /** get initial block offsets based on Tetromino shape */
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

    public static Point[] getEmptyPointArray() {
        return new Point[] { new Point(), new Point(), new Point(), new Point() };
    }

    /** Equally add the x & y value of a Point to an array of Points */
    public static Point[] addOffsetsAndPosition(Point[] offsets, Point position) {
        Point[] coordinates = getEmptyPointArray();

        for (int blockIndex = 0; blockIndex < 4; blockIndex++) {
            coordinates[blockIndex].x = offsets[blockIndex].x + position.x;
            coordinates[blockIndex].y = offsets[blockIndex].y + position.y;
        }

        return coordinates;
    }

    /** Add delta (Axis) to current (Axis) position */
    public Point[] translate(int deltaX, int deltaY) {
        Point newPosition = new Point(
            currentPosition.x + deltaX,
            currentPosition.y + deltaY
        );

        return addOffsetsAndPosition(blocksOffsets, newPosition);
    }

    /**
     * Explanation for how this all works:
     * (Visualize in Desmos for more clarity)
     * Take for example point { -5, -5 } to rotate it right (Clockwise),
     * we set the new value of X to the value of it's Y. So now it's { -5, _ }.
     * Then we set the new value of Y to the value it's old X times negative.
     * So now it's pos is { -5, 5 }. To rotate to the left (Counter-Clockwise)
     * we don't multiply the new value of Y with a negative, instead we do it for
     * the new value of X instead.
     */
    public Point[] rotate(int xDirection, int yDirection) {
        Point[] oldOffset = blocksOffsets;
        Point[] newOffset = getEmptyPointArray();

        for (int blockIndex = 0; blockIndex < 4; blockIndex++) {
            newOffset[blockIndex].x = oldOffset[blockIndex].y * xDirection;
            newOffset[blockIndex].y = oldOffset[blockIndex].x * yDirection;
        }

        return newOffset;
    }

    /** Creates an exact duplicate of this */
    public Tetromino duplicate() {
        Tetromino copy = new Tetromino(shape);
        copy.setCurrentPosition(currentPosition.x, currentPosition.y);
        copy.setBlocksOffsets(blocksOffsets);
        return copy;
    }

    // Setters

    public void setBlocksOffsets(Point[] blocksOffsets) {
        this.blocksOffsets = blocksOffsets;
    }

    public void setCurrentPosition(int x, int y) {
        currentPosition.x = x;
        currentPosition.y = y;
    }


    // Getters

    public Point[] getBlockCoordinates() {
        return addOffsetsAndPosition(blocksOffsets, currentPosition);
    }

    public Point getCurrentPosition() {
        return currentPosition;
    }

    public Tetromino.Shape getShape() {
        return shape;
    }
}
