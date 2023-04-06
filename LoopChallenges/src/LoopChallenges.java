import java.util.Scanner;

public class LoopChallenges {
    public static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        // Do/While Loop Practice Activities
        // Question 2
        int index = 0;
        do {
            System.out.println(index);
            index += 3;
        } while (index <= 21);

        // Question 4
        int userNumber = askNumericQuestion("Please provide a random integer :");
        double displayNumber = 1;
        double index2 = 1;

        while (displayNumber <= userNumber) {
            System.out.println(displayNumber);
            displayNumber = Math.pow(2, index2);
            index2++;
        }

        // Question 6
        String answer;
        do {
            answer = askQuestion("Is C++ better in performance than Rust? (yes/no)");
        } while (
            !answer.equalsIgnoreCase("yes") &&
            !answer.equalsIgnoreCase("no")
        );

        // Question 7 & 8
        int userProvidedNumber;
        int totalSum = 0;
        int index3 = 0;
        do {
            userProvidedNumber = askNumericQuestion("Please provide a random integer : (type 0 to stop.)");
            totalSum += userProvidedNumber;
            index3++;
        } while (userProvidedNumber != 0);
        System.out.println("The total sum of the numbers provided are : " + totalSum);
        System.out.println("The average of the numbers provided are : " + (totalSum / (index3 - 1.0)) );

        // For Loop Practice Activities
        // Question 2 (using a for loop)
        for (int i = 0; i <= 21; i += 3) {
            System.out.println(i);
        }

        // Question 4 (using a for loop)
        int userInt = askNumericQuestion("Please provide a random integer :");
        double displayNum = 0;
        for (int i = 0; displayNum <= userInt; i++) {
            System.out.println(displayNum);
            displayNum = Math.pow(2, i);
        }

        scanner.close();
    }

    public static String askQuestion(String question) {
        System.out.println(question);
        return scanner.nextLine().trim();
    }

    public static Integer askNumericQuestion(String question) {
        return Integer.parseInt(askQuestion(question));
    }
}