package common;

import java.util.Random;
import java.util.function.Function;

/** Various miscellaneous (mostly generic) static methods */
public class Misc {
    public static Random random = new Random();

    /** you can get the enum class by doing `Enum.class` */
    public static <T extends Enum<?>> T getRandomEnumValue(Class<T> enumClass) {
        T[] values = enumClass.getEnumConstants();
        return values[random.nextInt(values.length)];
    }

    public static <T> int getArrayMinValue(T[] array, Function<T, Integer> extractor) {
        int min = 0;
        for (T elem : array)
            min = Math.min(min, extractor.apply(elem));

        return min;
    }

    public static <T> int getArrayMaxValue(T[] array, Function<T, Integer> extractor) {
        int max = 0;
        for (T elem : array)
            max = Math.max(max, extractor.apply(elem));

        return max;
    }
}
