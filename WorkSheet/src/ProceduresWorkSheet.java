import java.util.Random;
import java.util.Scanner;

public class ProceduresWorkSheet {
    public static final Scanner scanner = new Scanner(System.in);
    public static final Random random = new Random();

    public static void main(String[] args) {
        // Question 2
        getThenPrintUserInput();

        // Question 4
        printNumberSquared(5);

        // Question 6
        getTwoNumbersThenPrintProduct();

        // Question 8
        printUserIntTimesTable();

        // Question 10
        generateRandomIntsWithUserRestriction();

        // Question 12
        generateRandomIntWithUserMaxAndMin();

        // Question 14
        getThreeNumbersThenPrintSum();

        // Question 16
        getTwoNumbersThenPrintMinimum();

        // Question 18
        printTwoRaisedToThePowerOfUserInput();

        // Question 20
        printUserInputInUppercase();
    }

    public static String askQuestion(String question) {
        System.out.println(question);
        return scanner.nextLine().trim();
    }

    public static int askNumericQuestion(String question) {
        return Integer.parseInt(askQuestion(question));
    }

    public static String toUppercase(String input) {
        StringBuilder output = new StringBuilder();
        for (char charCode : input.toLowerCase().toCharArray()) {
            // If in the range of char codes of a - z
            if (96 < charCode && charCode < 123)
                charCode -= 32;

            output.append(Character.toChars(charCode));
        }

        return output.toString();
    }

    public static void getThenPrintUserInput() {
        String suffix = askQuestion("Please provide your name :");
        System.out.printf("Hello %s\n", suffix);
    }

    public static void printNumberSquared(int number) {
        double product = Math.pow(number, 2);
        System.out.printf(
            "%d squared is %f\n",
            number, product
        );
    }

    public static void getTwoNumbersThenPrintProduct() {
        int a = askNumericQuestion("Please provide an integer input :");
        int b = askNumericQuestion("Please provide another integer input :");

        System.out.printf(
            "The product of %d and %d is %d\n",
            a, b, a * b
        );
    }

    public static void printUserIntTimesTable() {
        int input = askNumericQuestion("Please provide an integer input :");
        System.out.printf(
            "The times table of %d are as follows :\n",
            input
        );

        for (int i = 0; i <= 10; i++)
            System.out.println(input * i);
    }

    public static void generateRandomIntsWithUserRestriction() {
        int amountToGenerate = askNumericQuestion("How many random numbers to generate?");
        for (int i = 0; i < amountToGenerate; i++)
            System.out.println(random.nextInt());
    }

    public static void generateRandomIntWithUserMaxAndMin() {
        int min = askNumericQuestion("What is the minimum number the Random number generator should generate?");
        int max = askNumericQuestion("What is the maximum number the Random number generator should generate?");
        System.out.println(random.nextInt(max + 1 - min) + min);
    }

    public static void getThreeNumbersThenPrintSum() {
        int totalSum = 0;
        for (int i = 1; i < 4; i++) {
            int input = askNumericQuestion(String.format("Please provide %d of 3 input integers :", i));
            totalSum += input;
        }

        System.out.printf(
            "The total sum of the numbers you've provided is %s\n",
            totalSum
        );
    }

    public static void getTwoNumbersThenPrintMinimum() {
        int a = askNumericQuestion("Please provide an integer input :");
        int b = askNumericQuestion("Please provide another integer input :");

        System.out.printf(
            "The minimum between %d and %d is %d\n",
            a, b, Math.min(a, b)
        );
    }

    public static void printTwoRaisedToThePowerOfUserInput() {
        int exponent = askNumericQuestion("Please provide an integer input :");
        System.out.printf(
            "The number 2 raised to the power of %d is %f\n",
            exponent, Math.pow(2, exponent)
        );
    }

    public static void printUserInputInUppercase() {
        String input = askQuestion("Please provide a string input :");
        System.out.printf(
            "Your input converted to uppercase is %s\n",
            toUppercase(input)
        );
    }
}