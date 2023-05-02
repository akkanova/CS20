import javax.swing.*;

public class TetrisWindow extends JFrame {

    public TetrisWindow() {
        this(500, 600, "Java Tetris");
    }

    public TetrisWindow(int width, int height, String title) {
        super(title);
        super.setSize(width, height);
        super.setLocationByPlatform(true);

    }

}
