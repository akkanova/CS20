package cli;

import all_purpose.Utils;

// This a utility class designed for formatting
// CLI elements and designs.
public class Formatters {
    public enum TextAlign {
        LEFT,
        CENTER,
        RIGHT
    }

    public static String addBorder(String input, Border border) {
        return addBorder(input, border, TextAlign.CENTER);
    }

    public static String addBorder(String input, Border border, TextAlign align) {
        StringBuilder builder = new StringBuilder();
        String[] lines = input.split("\n");

        int width = Utils.getMaxLength(lines, String::length);
        builder.append(border.getTop(width));

        for (String line : lines) builder
            .append("\n")
            .append(border.getVerticalSide())
            .append(alignText(line, width, align))
            .append(border.getVerticalSide());

        return builder
            .append("\n")
            .append(border.getBottom(width))
            .toString();
    }


    public static String alignText(String input, int width) {
        return alignText(input, width, TextAlign.CENTER);
    }

    public static String alignText(String input, int width, TextAlign align) {
        int padding = width - input.length();
        return switch (align) {
            case LEFT -> input + " ".repeat(padding);
            case RIGHT -> " ".repeat(padding) + input;
            case CENTER -> {
                int left = Math.floorDiv(padding, 2);
                int right = left + (padding % 2);
                yield " ".repeat(left) + input + " ".repeat(right);
            }
        };
    }
}
