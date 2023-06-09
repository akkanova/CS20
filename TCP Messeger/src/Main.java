import all_purpose.*;
import cli.*;
import tcp.*;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Main {
    public final static Questioner questioner = new Questioner(new Scanner(System.in));
    public final static Border DEFAULT_BORDER = Border.from(Border.BOLD_BORDER);
    public final static Border ERROR_BORDER = Border.from(Border.DOUBLE_BORDER);

    public static void main(String[] args) {
        DEFAULT_BORDER.setColour(AnsiCharacters.GREEN_FG);
        ERROR_BORDER.setColour(AnsiCharacters.RED_FG);

        char answer = questioner.askYesOrNoQuestion(format(
            " Do you want to initiate the connection? \n" +
            "y / n "
        )).charAt(0);

        if (answer == 'n') {
            startClientSocket();
        } else {
            startServerSocket();
        }
    }


    /** The server socket is required to initiate the tcp communication */
    public static void startServerSocket() {
        try (TCPServer server = new TCPServer()) {
            questioner.clearConsole();
            System.out.println(format(
                " People can now connect to you, through \nHostname: " +
                server.getHostname() +
                "\nPort: " +
                server.getPort()
            ));

            server.start();

        } catch (IOException e) {
            printError(" Cannot Establish TCP Server ");
            throw new RuntimeException(e);
        }
    }


    /**
     * The client socket can only connect to an already initiated and
     * listening server tcp socket
     *  */
    public static void startClientSocket() {
        String hostname = questioner.askQuestion(format(
            " What is the Hostname of the target? \n" +
            "E.g. 'Google.com', '192.168.1.1'"
        ));

        int port = questioner.askNumericQuestion(format(
            " What is the Port of the target? \n" +
            "Any integers (0 - 65535)"
        ));

        try (TCPClient client = new TCPClient()) {
            questioner.clearConsole();
            client.connect(hostname, port);


        } catch (UnknownHostException e) {
            printError(" Invalid Hostname and  ");
            startClientSocket();
        } catch (IOException e) {
            printError(" Cannot Connect to Server ");
            throw new RuntimeException(e);
        }
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