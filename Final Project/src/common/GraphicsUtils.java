package common;

import java.awt.*;

/** Wraps around Graphics2D and provides common tools for rendering */
public class GraphicsUtils {
    public final Color BG_COLOR = new Color(54, 57, 63); // Background Color
    public final Color FG_TEXT_COLOR = new Color(187, 187, 187); // Foreground Color
    public final Font SUB_HEADER_FONT;
    public final Font HEADER_FONT;
    public final Font PLAIN_FONT;

    private final Graphics2D graphics;

    public GraphicsUtils(Graphics2D g, double guiScale) {
        SUB_HEADER_FONT = new Font("Monocraft", Font.PLAIN, (int) (15 * guiScale));
        HEADER_FONT = new Font("Monocraft", Font.BOLD, (int) (50 * guiScale));
        PLAIN_FONT = new Font("Monocraft", Font.BOLD, (int) (15 * guiScale));

        graphics = g;
    }

    public void drawBackground(int screenWidth, int screenHeight) {
        graphics.setColor(BG_COLOR);
        graphics.fillRect(0, 0, screenWidth, screenHeight);
    }

    public void drawCenteredText(String text, Font font, int screenWidth, int yPos) {
        FontMetrics metrics = graphics.getFontMetrics(font);
        int xPos = (screenWidth - metrics.stringWidth(text)) / 2;
        graphics.setFont(font);
        graphics.drawString(text, xPos, yPos);
    }
}
