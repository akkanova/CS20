package Common;

// A misc class for misc tools
public class Utils {
    public static int getMaxLength(Object<Measurable>[] array) {
        int max = 0;
        for (T elem : array)
            max = Math.max(max, elem.length());

        return max;
    }

    interface Measurable {
        int length();
    }
}
