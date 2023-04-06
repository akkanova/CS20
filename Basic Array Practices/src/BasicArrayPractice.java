import java.util.*;

public class BasicArrayPractice {
    public static final Random random = new Random();
    public enum ArraySortStatus {
        Descending,
        Ascending,
        Unknown,
        Random,
    }

    public static void main(String[] args) {
        // Challenge 2
        double[] doubleArray = { 1.0, 2.0, 3.0, 4.0, 5.0, 6.9, 7.0 };
        double[] reversedArray = reverseArray(doubleArray);
        System.out.printf(
            "This array : %s \nreversed is : %s.\n\n",
            Arrays.toString(doubleArray),
            Arrays.toString(reversedArray)
        );

        // Challenge 4
        int arraySize = getRandomInt(5, 25);
        int[] randomArray = generateRandomIntArray(arraySize, 0, 100);
        System.out.printf(
            "Look at this random Integer array : %s.\n\n",
            Arrays.toString(randomArray)
        );

        // Challenge 6
        String[] arrayA = generateRandomStrArray(getRandomInt(4, 8));
        String[] arrayB = generateRandomStrArray(getRandomInt(4, 8));
        String[] arrayC = combineStringArrays(arrayA, arrayB);
        System.out.printf(
            "Array A : %s\nand array B : %s\ncombined becomes array C : %s.\n\n",
            Arrays.toString(arrayA),
            Arrays.toString(arrayB),
            Arrays.toString(arrayC)
        );

        // Challenge 8
        int[] randomIntArray = generateRandomIntArray(10, 0, 10);
        ArraySortStatus sortStatus = getArraySortStatus(randomIntArray);
        System.out.printf(
            "This array %s\nis sorted in a %s order. Thus, the array %s sorted in ascending order.\n\n",
            Arrays.toString(randomIntArray),
            sortStatus.name(),
            // The challenge asked "whether it is sorted in ascending order or not"
            sortStatus != ArraySortStatus.Ascending
                ? "is not"
                : "is"
        );

        // Challenge 10
        int[] intArrayA = generateRandomIntArray(10, 0, 10);
        int[] intArrayB = generateRandomIntArray(10, 0, 10);
        int common = getAmountOfCommonInts(intArrayA, intArrayB);
        System.out.printf(
            "Array A : %s\nand array B : %s\nhas %d common elements between them.",
            Arrays.toString(intArrayA),
            Arrays.toString(intArrayB),
            common
        );
    }


    // Since Random.nextInt(min, max) doesn't work everywhere
    // this will compensate for it.
    public static int getRandomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    // Returns a reversed copy of the array
    public static double[] reverseArray(double[] original) {
        double[] copy = new double[original.length];
        // Index of where to put the copied element from the original
        int index = original.length - 1;
        for (double element : original) {
            copy[index] = element;
            index--;
        }

        return copy;
    }

    // Min and max refers to the minimum and maximum
    // constraints when generating the elements of the array.
    public static int[] generateRandomIntArray(int size, int min, int max) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
            array[i] = getRandomInt(min, max);

        return array;
    }

    // Returns random string array, using randomly generated integers
    // converted into string as its array content.
    public static String[] generateRandomStrArray(int size) {
        String[] array = new String[size];
        for (int i = 0; i < size; i++)
            array[i] = Integer.toString(getRandomInt(6, 920));

        return  array;
    }

    public static String[] combineStringArrays(String[] arrayA, String[] arrayB) {
        String[] combinedArray = new String[arrayA.length + arrayB.length];
        // Copy the contents of array A to the combined array;
        System.arraycopy(arrayA, 0, combinedArray, 0, arrayA.length);
        // Copy the contents of array B to the combined array with the offset of array A length;
        System.arraycopy(arrayB, 0, combinedArray, arrayA.length, arrayB.length);

        return combinedArray;
    }

    // Returns whether the given int array is sorted in "Ascending",
    // "Descending" order or just completely "Random";
    // If array length is < 2 or the entire array consists of the
    // same number it will return "Unknown".
    public static ArraySortStatus getArraySortStatus(int[] array) {
        ArraySortStatus status = ArraySortStatus.Unknown;
        ArraySortStatus prevStatus = status;

        for (int i = 0; i < array.length; i++) {
            // [... a, b ...] Visual representation of their pos
            int b = array[Math.min(i + 1, array.length - 1)];
            int a = array[i];

            if (a == b) continue;
            if (a < b) status = ArraySortStatus.Ascending;
            if (a > b) status = ArraySortStatus.Descending;

            // If the status changes after the third iteration
            // and the current status is no longer unknown,
            // then that means it's just a random mess
            if (i > 1 && prevStatus != ArraySortStatus.Unknown && prevStatus != status) {
                status = ArraySortStatus.Random;
                break;
            }

            prevStatus = status;
        }

        return status;
    }

    // Returns the amount elements that both exists in array and in array B.
    public static int getAmountOfCommonInts(int[] arrayA, int[] arrayB) {
        Set<Integer> hashSet = new HashSet<>();

        for (int a : arrayA) {
            for (int b : arrayB) {
                if (a == b) {
                    hashSet.add(a);
                    break;
                }
            }
        }
        
        return hashSet.size();
    }
}