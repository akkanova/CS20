package Common;

// This a utility class designed for formatting
// CLI elements and designs.
public class CliGraphics {
    enum TextAlign {
        LEFT,
        CENTER,
        RIGHT
    }

    public static String addBorder(String input) {
        return addBorder(input, TextAlign.CENTER);
    }

    public static String addBorder(String input, TextAlign align) {
        return addBorder(input, align, Characters.ARROW_BORDER);
    }

    public static String addBorder(String input, TextAlign align, Characters.Border border) {
        StringBuilder builder = new StringBuilder();
        String[] lines = input.split("\n");

        int horizontalLength = Utils.getMaxLength(lines, String::length);
        String horizontalSide = String.valueOf(border.horizontalSide())
            .repeat(horizontalLength);

        builder
            .append(border.topLeft())
            .append(horizontalSide)
            .append(border.topRight());

        for (String line : lines) {
            int padding = horizontalLength - line.length();
            String padded = switch (align) {
                case LEFT -> line + " ".repeat(padding);
                case RIGHT -> " ".repeat(padding) + line;
                case CENTER -> {
                    int left = Math.floorDiv(padding, 2);
                    int right = left + (padding % 2);
                    yield " ".repeat(left) + line + " ".repeat(right);
                }
            };

            builder
                .append("\n")
                .append(border.verticalSide())
                .append(padded)
                .append(border.verticalSide());
        }

        return builder
            .append("\n")
            .append(border.bottomLeft())
            .append(horizontalSide)
            .append(border.bottomRight())
            .toString();
    }
}
