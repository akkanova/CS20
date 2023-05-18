import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/** The class that loads the images and other graphics required */
public class Resources {
    private static final Map<String, BufferedImage> imageCache = new HashMap<>();
    private static final String baseDir = "res/"; // Base Directory

    /** Load Everything Sequentially */
    public static void load() {
        loadImage("icon.png");
        loadFont("Monocraft"); // Credit to https://github.com/IdreesInc/Monocraft
    }

    /**
     * Loads the image and stores it into the image Cache, so it can be
     * fetched again in the future without having to load it again.
     */
    public static BufferedImage loadImage(String filename) {
        if (imageCache.containsKey(filename))
            return imageCache.get(filename);

        String fullPath = baseDir + filename;
        try {
            BufferedImage image = ImageIO.read(new File(fullPath));
            imageCache.put(filename, image);
            return image;

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
