import Common.CliGraphics;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Tetris {
    private final PrintStream output;
    private final Scanner scanner;

    public Tetris(
        InputStream input,
        PrintStream output
    ) {
        this.output = output;
        this.scanner = new Scanner(input);
    }

    public void start() {
        output.flush();
        output.println(
            CliGraphics.addBorder("Welcome to Tetris\nWritten in Java\nBy: Alfred F.")
        );

        output.println();
    }

    public void stop() {
        scanner.close();
    }
}
