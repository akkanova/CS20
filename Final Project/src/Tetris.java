import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Tetris {
    private final InputStream input;
    private final PrintStream output;
    private final Scanner scanner;

    public Tetris(
        InputStream input,
        PrintStream output
    ) {
        this.input = input;
        this.output = output;
        this.scanner = new Scanner(this.input);
    }

    public void start() {
        output.flush();
        output.println();
    }

    public void stop() {
        scanner.close();
    }
}
