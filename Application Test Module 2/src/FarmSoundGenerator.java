import java.util.Scanner;

public class FarmSoundGenerator {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        print("***Welcome to the Farm***");

        // Declared here as it never changes within the loop
        String actionQuery = "Do you want to hear a (d)og, a (c)ow, or (q)uit?";
        String answer;

        do {
            String animalNoise;
            answer = askQuestion(actionQuery);

            // Determine animal noise from user input.
            if (answer.equalsIgnoreCase("d")) {
                animalNoise = "bark";

            } else if (answer.equalsIgnoreCase("c")) {
                animalNoise = "moo";

            } else continue;

            int numOfRepetition = askNumericQuestion("Enter the number of times to " + animalNoise + ":");
            String repeatedString = repeatString(animalNoise, numOfRepetition);
            print(repeatedString);

        } while(!answer.equalsIgnoreCase("q"));
    }

    // The methods written below have been designed to be
    // multipurpose, as shown by my usage of them in
    // multiple Java projects.

    // Just a shortcut of `system.out.println()`.
    public static void print(String output) {
        System.out.println(output);
    }

    public static String askQuestion(String question) {
        print(question);
        return scanner.nextLine().trim();
    }

    // Always expects and returns an integer.
    public static int askNumericQuestion(String question) {
        return Integer.parseInt(askQuestion(question));
    }

    // Although `String.repeat()` already existed, this method
    // adds padding between each repeated input.
    public static String repeatString(String input, int numOfRepetition) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < numOfRepetition; i++)
            output.append(input).append(" ");

        return  output.toString();
    }
}