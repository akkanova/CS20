import javax.swing.*;
import java.awt.*;

public class TetrisWindow extends JFrame {

    public TetrisWindow() {
        this(600, 500, "Java Tetris");
    }

    public TetrisWindow(int width, int height, String title) {
        super(title);
        super.setSize(width, height);
        super.setLocationByPlatform(true);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Load Images
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image icon = toolkit.createImage("sample.jfif");
        super.setIconImage(icon);
    }

    public void run() {
        super.setVisible(true);
    }
}
