package all_purpose;

// A misc class for misc tools
public class Utils {
    public static <T> int getMaxLength(T[] array, Func<T, Integer> getLength) {
        int max = 0;
        for (T elem : array)
            max = Math.max(max, getLength.run(elem));

        return max;
    }

    // Similar to C# Func
    public interface Func<I, R> {
        R run(I e);
    }
}
    