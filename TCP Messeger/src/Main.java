import all_purpose.AnsiCharacters;
import all_purpose.Questioner;
import cli.Border;
import cli.Formatters;

import java.util.Scanner;


public class Main {
    public final static Questioner questioner = new Questioner(new Scanner(System.in));
    public final static Border DEFAULT_BORDER = Border.from(Border.BOLD_BORDER);
    public final static Border ERROR_BORDER = Border.from(Border.DOUBLE_BORDER);

    public static void main(String[] args) {
        DEFAULT_BORDER.setColour(AnsiCharacters.GREEN_FG);
        ERROR_BORDER.setColour(AnsiCharacters.RED_FG);

        char answer = questioner.askYesOrNoQuestion(format(
            " Do you want to connect to a server? \n" +
            "y / n "
        )).charAt(0);

        if (answer == 'n') {
            startServerSocket();
        } else {
            startClientSocket();
        }
    }

    /** The server socket is used to listen for incoming connections */
    public static void startServerSocket() {

    }

    /** Client socket is used to initiate a connection with a remote server. */
    public static void startClientSocket() {
        String hostname = questioner.askQuestion(format(
            " What is the hostname of the server? \n" +
            "E.g. 'Google.com', '192.168.1.1'"
        ));

        int port = questioner.askNumericQuestion(format(
            " What is the port of the server? \n" +
            "Any integers (0 - 65535)"
        ));
    }


    public static String format(String input) {
        return Formatters.addBorder(input, DEFAULT_BORDER);
    }

    public static void printError(String input) {
        System.out.println(Formatters.addBorder(
            " Error:\n" + input,
            ERROR_BORDER,
            Formatters.TextAlign.LEFT
        ));
    }
}