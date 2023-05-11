import javax.swing.*;
import java.awt.*;

public class TetrisWindow extends JFrame {

    public TetrisWindow() {
        this(600, 500, "Java Tetris");
    }

    public TetrisWindow(int width, int height, String title) {
        super(title);
        super.setResizable(false);
        super.setSize(width, height);
        super.setLayout(new CardLayout());
        super.setLocationByPlatform(true);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Load Images
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image icon = toolkit.createImage("res/icon.png");
        super.setIconImage(icon);

        super.add(new TetrisMenu());
        super.add(new TetrisPanel());
    }

    public void run() {
        super.setVisible(true);
    }
}
