import java.util.Scanner;

public class TriviaGame {
    public static final Scanner scanner = new Scanner(System.in);
    public static boolean hasUsedFreePass;

    public static TriviaQuestion[] questions = {
            new TriviaQuestion("What is the Capital of Assyria?")
                    .setAnswerLetter('C')
                    .setOptions(new String[] {
                        "Araden",
                        "Sabbata",
                        "Assur",
                        "Nimrud"
                    }),
            new TriviaQuestion("What was the first message sent by morse code?")
                    .setAnswerLetter('B')
                    .setOptions(new String[] {
                        "Hello and Good Morning",
                        "What hath God wrought?",
                        "T-E-S-T",
                        "We hath made God weep"
                    }),
            new TriviaQuestion("What year was the very first model of the iPhone released?")
                    .setAnswerLetter('D')
                    .setOptions(new String[] {
                        "2006",
                        "2009",
                        "2008",
                        "2007"
                    }),
            new TriviaQuestion("What does \"HTTP\" stand for?")
                    .setAnswerLetter('B')
                    .setOptions(new String[] {
                        "Hyper-connected Text Transfer Protocol",
                        "HyperText Transfer Protocol",
                        "Hyper-connected Transmitted Terminal Packet",
                        "Hypertext Technology Packet"
                    }),
            new TriviaQuestion("What was Twitter’s original name?")
                    .setAnswerLetter('C')
                    .setOptions(new String[] {
                        "SnapText",
                        "BlueJay",
                        "Twttr",
                        "Tweett"
                    })
    };

    public static void main(String[] args) {
        double userScore = 100.00;
        int correctAnswers = 0;

        for (TriviaQuestion triviaQuestion: questions) {
            String answer;
            do {
                // Keep asking until the answer is valid
                answer = askQuestion(triviaQuestion.toString());
            } while (!validateAnswer(answer));

            if (answer.equalsIgnoreCase("pass") && !hasUsedFreePass) {
                hasUsedFreePass = true;
            } else if (triviaQuestion.checkAnswer(answer)) {
                System.out.println("That answer was Correct.\n");
                userScore *= 2.00;
                correctAnswers++;
            } else {
                System.out.println("That answer was Incorrect.\n");
                userScore *= 0.50;
            }
        }

        System.out.println(
            "You earned : $" + userScore +
            "\nYou managed to answer " + correctAnswers + "/" + questions.length +
            " questions."
        );

        String answer = askQuestion("Play again? (yes/no)");
        if (answer.equalsIgnoreCase("yes")) {
            hasUsedFreePass = false;
            main(args);
            return;
        }

        scanner.close();
    }

    public static String askQuestion(String question) {
        System.out.println(question);
        return scanner
            .nextLine()
            .trim()
            .toUpperCase();
    }

    public static boolean validateAnswer(String answer) {
        // An Enhanced Switch
        return switch (answer) {
            // If any of these matches with the answer return true
            case "A", "B", "C", "D": yield true;
            case "PASS": yield !hasUsedFreePass;
            default: yield false;
        };
    }



    public static class TriviaQuestion {
        public String[] options;
        public String question;
        public char answerLetter;

        public TriviaQuestion(String question) {
            this.question = question;
        }

        public TriviaQuestion setOptions(String[] options) {
            this.options = options;
            return this;
        }

        public TriviaQuestion setAnswerLetter(char letter) {
            this.answerLetter = letter;
            return this;
        }

        public boolean checkAnswer(String answer) {
            return answer.charAt(0) == this.answerLetter;
        }

        public String toString() {
            StringBuilder output = new StringBuilder(question + " :\n");
            Character[] characters = { 'A', 'B', 'C', 'D' };

            for (int i = 0; i < options.length; i++) {
                output
                    .append(characters[i])
                    .append(") ")
                    .append(options[i])
                    .append("\n");
            }

            return output.toString();
        }
    }
}