import all_purpose.AnsiCharacters;
import cli.Border;
import cli.Formatters;

import java.io.PrintStream;

// This is a class designed to handle
// the rendering of Graphics
public class Renderer {
    private final Border defaultBorder = Border.from(Border.BOLD_BORDER);
    private final Border errorBorder = Border.from(Border.BOLD_BORDER);
    private final PrintStream output;

    public Renderer(PrintStream output) {
        defaultBorder.setColour(AnsiCharacters.GREEN_FG);
        errorBorder.setColour(AnsiCharacters.RED_FG);
        this.output = output;
    }

    public void print(String content) {
        output.flush();
        output.print(content);
    }

    public void printError(String error) {
        print(Formatters.addBorder(error, errorBorder));
    }

    public void printWithBorder(String content) {
        print(Formatters.addBorder(content, defaultBorder));
    }
}
