package CLI;

import AllPurpose.AnsiCharacters;

public class Border {
    // Border Presets
    public static final Border SINGLE_DOUBLE_BORDER = new Border('╓', '╖', '─', '║', '╙', '╜');
    public static final Border DOUBLE_SINGLE_BORDER = new Border('╒', '╕', '═', '│', '╘', '╛');
    public static final Border ROUNDED_BORDER = new Border('╭', '╮', '─', '│', '╰', '╯');
    public static final Border SINGLE_BORDER = new Border('┌', '┐', '─', '│', '└', '┘');
    public static final Border DOUBLE_BORDER = new Border('╔', '╗', '═', '║', '╚', '╝');
    public static final Border ARROW_BORDER = new Border('↘', '↙', '-', '|', '↗', '↖');
    public static final Border BOLD_BORDER = new Border('┏', '┓', '━', '┃', '┗', '┛');

    private final char topLeft;
    private final char topRight;
    private final char horizontalSide;
    private final char verticalSide;
    private final char bottomLeft;
    private final char bottomRight;
    private String colour;

    public Border(
        Border border
    ) {
        this.horizontalSide = border.horizontalSide;
        this.verticalSide = border.verticalSide;
        this.bottomRight = border.bottomRight;
        this.bottomLeft = border.bottomLeft;
        this.topRight = border.topRight;
        this.topLeft = border.topLeft;
        this.colour = border.colour;
    }

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
