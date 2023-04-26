package Common;

// This a utility class designed for formatting
// CLI elements and designs.
public class CliGraphics {
    public String wrapBorder(String input) {
        return wrapBorder(input, Characters.ThickBorder);
    }

    public String wrapBorder(String input, Characters.Border border) {
        StringBuilder builder = new StringBuilder();
        String[] lines = input.split("\n");

        int max = Utils.getMaxLength(lines);

        builder.append(Characters.ThickBorder.TOP_LEFT);
        builder.append(String.valueOf(Characters.ThickBorder.TOP).repeat(max));
        builder.append(Characters.ThickBorder.TOP_RIGHT);

        for (String line : lines) {
//            builder.append(Characters.ThickBorder.)
        }

        return builder.toString();
    }
}
