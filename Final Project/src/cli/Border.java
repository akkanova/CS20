package cli;

import all_purpose.AnsiCharacters;

public class Border {
    // Border Presets
    public static final Border SINGLE_DOUBLE_BORDER = new Border('╓', '╖', '─', '║', '╙', '╜');
    public static final Border DOUBLE_SINGLE_BORDER = new Border('╒', '╕', '═', '│', '╘', '╛');
    public static final Border ROUNDED_BORDER = new Border('╭', '╮', '─', '│', '╰', '╯');
    public static final Border SINGLE_BORDER = new Border('┌', '┐', '─', '│', '└', '┘');
    public static final Border DOUBLE_BORDER = new Border('╔', '╗', '═', '║', '╚', '╝');
    public static final Border ARROW_BORDER = new Border('↘', '↙', '-', '|', '↗', '↖');
    public static final Border BOLD_BORDER = new Border('┏', '┓', '━', '┃', '┗', '┛');

    private final char horizontalSide;
    private final char verticalSide;
    private final char bottomRight;
    private final char bottomLeft;
    private final char topRight;
    private final char topLeft;
    private String colour;

    public Border(
        char topLeft,
        char topRight,
        char horizontalSide,
        char verticalSide,
        char bottomLeft,
        char bottomRight
    ) {
        this.horizontalSide = horizontalSide;
        this.verticalSide = verticalSide;
        this.bottomRight = bottomRight;
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
        this.topLeft = topLeft;
    }

    // Extending Object.clone is not possible
    // as it's broken. So this static method
    // will serve as a way to clone Borders
    public static Border from(Border border) {
        return new Border(
            border.topLeft,
            border.topRight,
            border.horizontalSide,
            border.verticalSide,
            border.bottomLeft,
            border.bottomRight
        );
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getTop(int width) {
        return this.colour + topLeft +
            getHorizontalSide(width) +
            topRight + AnsiCharacters.RESET;
    }

    public String getVerticalSide() {
        return this.colour +
            verticalSide +
            AnsiCharacters.RESET;
    }

    public String getBottom(int width) {
        return this.colour + bottomLeft +
            getHorizontalSide(width) +
            bottomRight + AnsiCharacters.RESET;
    }

    private String getHorizontalSide(int width) {
        return String.valueOf(horizontalSide).repeat(width);
    }
}
