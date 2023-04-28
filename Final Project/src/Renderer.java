import AllPurpose.AnsiCharacters;
import CLI.Border;
import CLI.Formatters;
import java.io.PrintStream;

// This is a class designed to handle
// the rendering of Graphics
public class Renderer {
    private final PrintStream output;
    private final Border defaultBorder;

    public Renderer(PrintStream output) {
        this.output = output;
        defaultBorder = new Border(Border.BOLD_BORDER);
        defaultBorder.setColour(AnsiCharacters.GREEN_FG);
    }

    public void clear() {
        output.flush();
    }

    public void render(String graphics) {
        output.flush();
        output.print(graphics);
    }

    public void renderWithBorder(String graphics) {
        render(Formatters.addBorder(graphics, Formatters.TextAlign.CENTER, defaultBorder));
    }
}
