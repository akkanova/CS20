import java.util.Scanner;

public class InputChallenges {
    public static final Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        // Scenario 1
        String firstName = askQuestion("What is your firstname?");
        String lastName = askQuestion("What is your lastname?");
        System.out.println(
            "Your name is : " +
            lastName + ", " + firstName
        );

        // Scenario 2
        Integer userAge = askNumericQuestion("What is your current age?");
        System.out.println(
            "Your age in Dog years is : " +
            (userAge * 7)
        );

        // Scenario 3
        int sumOfThreeNumbers = 0;
        String[] labels = { "First", "Second", "Third" };

        for (String label: labels) {
            String question = "Please provide the " + label + " number";
            sumOfThreeNumbers += askNumericQuestion(question);
        }

        System.out.println(
            "The sum of the three numbers you provided are : " +
            sumOfThreeNumbers
        );

        // Scenario 4
        int areaCode = askNumericQuestion("What is your Phone's Area Code?  Ex: (this) 000-0000 ");
        int telephonePrefix = askNumericQuestion("What is your Phone's Telephone Prefix?  Ex: (000) this-000");
        int lineNumber = askNumericQuestion("What is your Phone's Line Number?  Ex: (000) 000-this");
        System.out.println(
            "Your Phone Number is : (" +
            areaCode + ") " +
            telephonePrefix + "-" +
            lineNumber
        );

        // Scenario 6
        int userGrade = askNumericQuestion("What is your grade?");
        int userAverageMark = askNumericQuestion("What is your average mark?");
        String userName = askQuestion("What is your name?");
        System.out.println(
            userName + " (" +
            userGrade + "): " +
            userAverageMark + "%"
        );
    }

    public static String askQuestion(String question) {
        System.out.println(question);
        return keyboard.nextLine();
    }

    public static Integer askNumericQuestion(String question) {
        return Integer.parseInt(askQuestion(question));
    }
}