package common;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/** A static methods class that loads the images and other graphics required */
public class ResourceManager {
    private static final String baseDir = "res/"; // Base Files Directory
    private static String texturePack;

    public static void setTexturePack(String pack) {
        texturePack = pack;
    }

    public static BufferedImage loadBlockTexture(String block) {
        return loadImage(texturePack + "/" + block + ".png");
    }

    public static BufferedImage loadImage(String filename) {
        String fullPath = baseDir + filename;
        try {
            return ImageIO.read(new File(fullPath));
        } catch (IOException e) {
            System.err.println("Failed to load image: " + fullPath);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Checks your system if you already installed
     * that font, then registers it for you if it isn't.
     * Do not include the extension (.tff) when specifying `name`
     * */
    public static void loadFont(String name) {
        // First check whether that font exists
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        for (String fontName : ge.getAvailableFontFamilyNames())
            if (fontName.equals(name)) return;

        String fullPath = baseDir + name + ".ttf";
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(fullPath));
            ge.registerFont(font);

        } catch (IOException e) {
            System.err.println("Failed to load font: " + fullPath);
            e.printStackTrace();

        } catch (FontFormatException e) {
            System.err.println("Font " + fullPath + " is invalid");
            e.printStackTrace();
        }
    }
}
