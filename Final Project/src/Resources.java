import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/** The class that loads the images and other graphics required */
public class Resources {
    private static final Map<String, BufferedImage> imageCache = new HashMap<>();
    private static final String baseDir = "res/"; // Base Directory

    public static void load() {
        loadImage("icon.png");
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
            System.err.print("Failed to load image: " + fullPath);
            e.printStackTrace();
            return null;
        }
    }
}
