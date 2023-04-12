import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static final PieceOfArt[] piecesOfArt = {
        new PieceOfArt(
            "Leonardo da Vinci",
            "Mona Lisa",
            1512,
            9_999_999.99,
            new String[]{ "canvas", "oil" }
        ),

        // Simpsons Reference
        new PieceOfArt(
            "Bartholomew Jojo \"Bart\" Simpson",
            "El Barto",
            1987,
            100_000_000,
            new String[]{ "watercolor", "paper" }
        ),

        // Banana Duct-Taped to a blank canvas
        new PieceOfArt(
            "Maurizio Cattelan",
            "Comedian",
            2019,
            120_000,
            new String[]{ "canvas" }
        )
    };

    public static void main(String[] args) {
        System.out.println("***Welcome to the Art Gallery***");
        StringBuilder question = new StringBuilder()
            .append("Please enter your selection:\n");

        int action;
        for (int i = 0; i < piecesOfArt.length; i++) question
            .append(i + 1)
            .append(". ")
            .append(piecesOfArt[i].getTitle())
            .append("\n");

        question.append("0. Quit");

        do {
            action = askNumericQuestion(question.toString());

            if (0 < action && action <= piecesOfArt.length)  {
                PieceOfArt chosenArt = piecesOfArt[action - 1];
                System.out.println(chosenArt + "\n");
            }
        } while (action != 0);
    }

    public static Integer askNumericQuestion(String question) {
        System.out.println(question);
        return Integer.parseInt(scanner.nextLine());
    }
}