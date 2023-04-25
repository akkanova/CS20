package Common;

import java.util.Arrays;

// This a utility class designed for formatting
// CLI elements and designs.
public class CliGraphics {
    public String wrapBorder(String input) {
        StringBuilder builder = new StringBuilder();
        String[] lines = input.split("\n");

        int max = Utils.getMaxLength(lines);

        builder.append(Characters.THICK_TOP_LEFT_BORDER);
        builder.append(String.valueOf(Characters.THICK_TOP_BORDER).repeat(max));
//        builder.append(Characters.)

        return builder.toString();
    }
}
