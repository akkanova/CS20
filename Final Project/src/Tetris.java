import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Tetris {
    private final Renderer renderer;
    private final Scanner scanner;

    public Tetris(
        InputStream input,
        PrintStream output
    ) {
        this.renderer = new Renderer(output);
        this.scanner = new Scanner(input);
    }

    public void start() {
        renderer.clear();
        renderer.renderWithBorder(
            " Welcome to Tetris \n" +
            " Press A to Continue "
        );
    }
}
