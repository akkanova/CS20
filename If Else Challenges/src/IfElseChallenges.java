import java.util.Random;
import java.util.Scanner;

public class IfElseChallenges {
    public static final Random randomizer = new Random();
    public static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        // Question 2
        int randomInt = randomizer.nextInt(1, 10);
        if (randomInt > 5) {
            System.out.println("The number generated is greater than 5");
        } else {
            System.out.println("The number generated is lower than / equal to 5");
        }

        // Question 4
        String userName = askQuestion("Who is Shopping for Groceries?");
        String productName = askQuestion("What product do you want to buy (milk / ice cream)?");
        int productPrice = askNumericQuestion("What is the price of " + productName + "?" );
        if (productName.equalsIgnoreCase("ice cream")) {
            productPrice += productPrice * 0.05;
        }

        System.out.println(
            "You bought " + productName +
            " with the final price costing you $" +
            productPrice
        );

        // Question 6
        boolean hasAppropriateAge = askNumericQuestion("What is your age?") >= 16;
        boolean hasPassedDriverTest = askQuestion("Did you pass your driver's test (yes/no)?")
            .equalsIgnoreCase("yes");

        if (!hasAppropriateAge && !hasPassedDriverTest) {
            System.out.println(
                "You need to pass your driver's test and be at least the " +
                "age of 16 to be eligible for a driver's license."
            );
        } else if (!hasAppropriateAge) {
            System.out.println("You need to be at least 16 to be eligible for a driver's license.");
        } else if (!hasPassedDriverTest) {
            System.out.println("You need to pass your driver's test to be eligible for a driver's license.");
        } else {
            System.out.println("You are completely eligible for a driver's license.");
        }

        // Question 8
        char markLetter;
        int csMark = askNumericQuestion("What is your Computer Science Mark?");
        if (isInBetween(csMark, 80, 100)) { markLetter = 'A'; }
        else if (isInBetween(csMark, 70, 79)) { markLetter = 'B'; }
        else if (isInBetween(csMark, 60, 69)) { markLetter = 'C'; }
        else if (isInBetween(csMark, 50, 59)) { markLetter = 'D'; }
        else { markLetter = 'F'; }

        System.out.println(
            "Your " + csMark + "% in Computer Science " +
            "converted into letter grade is " + markLetter
        );
    }

    public static String askQuestion(String question) {
        System.out.println(question);
        return scanner.nextLine().trim();
    }

    public static Integer askNumericQuestion(String question) {
        return Integer.parseInt(askQuestion(question));
    }

    public static boolean isInBetween(Integer value, Integer lowest, Integer highest) {
        return lowest <= value && value <= highest;
    }
}