import java.util.Scanner;

public class PopMachineChallenge {
    public static Scanner scanner = new Scanner(System.in);
    public static final double costOfPop = 1.50;

    public static void main(String[] args) {
        double userCredit = 0;
        int popsPurchased = 0;
        String answer;

        // Game / Application loop
        do {
            // Using floating point string format specifier with 2 decimal places ('%.2f')
            // Format specifiers are replaced by the value in the second provided arguments in the formatter method

            String question =
                "You have $" + String.format("%.2f", userCredit) + " credit. " +
                "What would you like to do?\n" +
                "0. Quit\n" +
                "1. Add $0.25\n" +
                "2. Add $1.00\n" +
                "3. Add $2.00\n" +
                "4. Refund";

            if (userCredit >= costOfPop)
                question += "\n5. Buy a pop";

            answer = askQuestion(question);
            switch (answer) {
                case "1" -> userCredit += 0.25;
                case "2" -> userCredit += 1.00;
                case "3" -> userCredit += 2.00;
                case "4" -> {
                    if (userCredit == 0) continue;
                    //System.out.printf method can print and format at the same time
                    System.out.printf("You have been refunded $%.2f.", userCredit);
                    userCredit = 0;
                }
                case "5" -> {
                    if (userCredit < costOfPop) continue;
                    userCredit -= costOfPop;
                    popsPurchased++;
                }
            }

        } while(!answer.equalsIgnoreCase("0"));

        // Using integer format specifier ('%d')
        System.out.printf("You purchased %d pop(s)", popsPurchased);
    }

    public static String askQuestion(String question) {
        System.out.println(question);
        return scanner.nextLine().trim();
    }
}