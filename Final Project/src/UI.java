import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class UI extends JFrame implements ActionListener {
    public int blockSize;

    public UI() {
        // JFrame Setup
        super("Block Stacking Game");
        setLocationRelativeTo(null); // null = Center of the screen
        setDefaultCloseOperation(EXIT_ON_CLOSE); // So it would close, instead of going in the background
        setIconImage(Resources.loadImage("icon.png"));

        // Set Initial Screen Size
        Dimension size = new Dimension(300, 400);
        setBlockSize(size);
        setSize(size);

        // Listen to the UI being resized
        addComponentListener(new ComponentListener() {
            // Useless requirement for ComponentListener
            @Override public void componentMoved(ComponentEvent e) {}
            @Override public void componentShown(ComponentEvent e) {}
            @Override public void componentHidden(ComponentEvent e) {}

            @Override
            public void componentResized(ComponentEvent e) {
                Component component = e.getComponent();
                setBlockSize(new Dimension(
                    component.getWidth(),
                    component.getHeight()
                ));
            }
        });

        setVisible(true);
    }

    /** Sets the block width & height from given screen size */
    private void setBlockSize(Dimension screenSize) {
        int gameWidth = 10;
        int gameHeight = 24;
        blockSize = (screenSize.width * screenSize.height) / (gameWidth * gameHeight);
    }

    // GRAPHICS

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        renderMenu(g);
    }

    public void renderMenu(Graphics g) {
        // Test
        for (int i = 0; i < 10; i++)
            g.drawLine(blockSize * i, 0, blockSize * i, blockSize * 24);
    }


    // LOOP

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
