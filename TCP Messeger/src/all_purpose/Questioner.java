package all_purpose;

import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Pattern;

public record Questioner(Scanner scanner) {

    public String askQuestion(String question) {
        System.out.println(question);
        return scanner.nextLine().trim();
    }

    public String askQuestionUntilValidAnswer(String question, Function<String, Boolean> isValid) {
        String answer;
        do {
            answer = askQuestion(question);
        } while (!isValid.apply(answer));
        return answer;
    }

    public String askYesOrNoQuestion(String question) {
        return askQuestionUntilValidAnswer(question, (answer) -> {
            char character = answer.toLowerCase().charAt(0);
            return character == 'y' || character == 'n';
        });
    }

    public int askNumericQuestion(String question) {
        return Integer.parseInt(askQuestionUntilValidAnswer(question, (answer) ->
            // Using regex I can test whether the input is a number or not
            // it matches any answers that is entirely made of number (no length limit)
            Pattern.matches("^\\d+$", answer)
        ));
    }
}
