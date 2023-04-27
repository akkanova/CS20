package Common;

public class Characters {
    public record Border(
        char topLeft,
        char topRight,
        char horizontalSide,
        char verticalSide,
        char bottomLeft,
        char bottomRight
    ) {}

    // Border Presets
    public static Border SINGLE_DOUBLE_BORDER = new Border('╓', '╖', '─', '║', '╙', '╜');
    public static Border DOUBLE_SINGLE_BORDER = new Border('╒', '╕', '═', '│', '╘', '╛');
    public static Border ROUNDED_BORDER = new Border('╭', '╮', '─', '│', '╰', '╯');
    public static Border SINGLE_BORDER = new Border('┌', '┐', '─', '│', '└', '┘');
    public static Border DOUBLE_BORDER = new Border('╔', '╗', '═', '║', '╚', '╝');
    public static Border ARROW_BORDER = new Border('↘', '↙', '-', '|', '↗', '↖');
    public static Border BOLD_BORDER = new Border('┏', '┓', '━', '┃', '┗', '┛');
}