package game;

/**
 * <a href="https://en.wikipedia.org/wiki/Tetromino">
 * Tetromino Definition
 * </a>
 * P.S most Java IDEs support HTML notations (including IntelliJ)
 * */
public class Tetromino {
    private final Shape shape;
    private int[][] blocksCoordinates; // The x and y positions of each block of a tetromino (4 blocks in total)

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
        this.blocksCoordinates = getBlockOffsets(shape);
    }

    /** get block offsets based on Tetromino shape */
    public static int[][] getBlockOffsets(Shape shape) {
        return switch (shape) {
            // I suggest visualizing the points in https://www.desmos.com/calculator, for it to make some sense
            case Straight -> new int[][] { {0,  -1}, {0,  0}, {0,  1}, {0,  2} }; // Vertical Line
            case Square   -> new int[][] { {0,   0}, {1,  0}, {0,  1}, {1,  1} }; // Cube
            case TShape   -> new int[][] { {-1,  0}, {0,  0}, {1,  0}, {0,  1} }; // Upside Down T
            case JShape   -> new int[][] { {-1, -1}, {0, -1}, {0,  0}, {0,  1} }; // J
            case LShape   -> new int[][] { {1,  -1}, {0, -1}, {0,  0}, {0,  1} }; // L
            case SSkew    -> new int[][] { {0,  -1}, {0,  0}, {1,  0}, {1,  1} }; // Stairs going up to the right
            case ZSkew    -> new int[][] { {0, - 1}, {0,  0}, {-1, 0}, {-1, 1} }; // Stairs going up to the left
        };
    }

    public void setBlocksCoordinates(int[][] coordinates) { this.blocksCoordinates = coordinates; }
    public int[][] getBlocksCoordinates() { return this.blocksCoordinates; }

    public void rotateLeft() {
        // Rotation doesn't make sense for a square
        if (shape == Shape.Square) return;
        int[][] oldCoordinates = this.blocksCoordinates;
        int[][] newCoordinates = new int[4][2];

        for (int blockIndex = 0; blockIndex < 4; blockIndex++) {
            newCoordinates[blockIndex][0] = -oldCoordinates[blockIndex][1];
            newCoordinates[blockIndex][1] = oldCoordinates[blockIndex][0];
        }

        this.blocksCoordinates = newCoordinates;
    }

    public void rotateRight() {
        if (shape == Shape.Square) return;
        int[][] oldCoordinates = this.blocksCoordinates;
        int[][] newCoordinates = new int[4][2];

        for (int blockIndex = 0; blockIndex < 4; blockIndex++) {
            newCoordinates[blockIndex][0] = oldCoordinates[blockIndex][1];
            newCoordinates[blockIndex][1] = -oldCoordinates[blockIndex][0];
        }

        this.blocksCoordinates = newCoordinates;
    }

}
