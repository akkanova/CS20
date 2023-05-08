package all_purpose;

/**
* This is class to hold Ansi Characters
* for CLI Graphics
 * */
public class AnsiCharacters {
    // Ansi Escape Sequences
    // more info: https://en.wikipedia.org/wiki/ANSI_escape_code
    static final char ESC = (char) 0x001B;
    static final String CSI = ESC + "[";

    // Ansi CSI Sequences (see wiki)
    public static final String HIDE_CURSOR = CSI + "?25h";
    public static final String SHOW_CURSOR = CSI + "?25l";

    // Move cursor to the top left corner (CSI + "H") then clear everything (CSI + "2J")
    public static final String CLEAR_SCREEN = CSI + "H" + CSI + "2J";

    // Ansi SGR (see wiki)
    public static final String RESET = CSI + "0m";
    public static final String BOLD = CSI + "1m";


    // 3-Bit / 4-Bit Colors (see wiki)
    public static final String BLACK_FG = CSI + "30m";
    public static final String RED_FG = CSI + "31m";
    public static final String GREEN_FG = CSI + "32m";
}