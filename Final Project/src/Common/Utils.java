package Common;

// A misc class for misc tools
public class Utils {
    public static int getMaxLength(String[] array) {
        int max = 0;
        for (String elem : array)
            max = Math.max(max, elem.length());

        return max;
    }
}
