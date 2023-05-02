import javax.xml.crypto.Data;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.net.*;
import java.io.*;


public class Main {

    // Scanner class for user input
    static Scanner scan = new Scanner(System.in);

    // Maximum amount of bytes to read from a single message
    static final int MAX_BYTES = 60_000;
    static boolean finished = false;
    public static void main(String[] args) {
        try {
            int port = Integer.parseInt("8888");
            String name = "default";


            // Address to connect to
            InetSocketAddress group = new InetSocketAddress("10.20.4.202", port);

            // Network interface to use for connection
            NetworkInterface intf = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());

            // Local socket to create connection from
            MulticastSocket socket = new MulticastSocket(port);

            // Connect to the specified address
            socket.joinGroup(group, intf);

            // Inform user of a successful connection
            System.out.println("Connection established.");

            // Create another thread for the listener to simultaneously receive and send messages
            Thread listener = new Thread(() -> {
                while (!finished) {
                    // Buffer to hold the received bytes in
                    byte[] buffer = new byte[MAX_BYTES];

                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                    try {
                        socket.receive(packet);

                        // Output the received packet by using the buffer to create a string
                        System.out.println(new String(buffer, 0, packet.getLength(), StandardCharsets.UTF_8));
                    }
                    // Handle if the program cannot read the message
                    catch (IOException e) {
                        System.out.println("Could not read data.");
                    }
                }
            });

            // Start the above thread
            listener.start();

            System.out.println("You can now send messages.");

            while (true) {
                System.out.print("> ");
                String message = scan.nextLine();

                // Handle shutdown
                if (message.equals("EXIT")) {
                    // Make sure the other thread closes as well
                    finished = true;

                    // Properly leave the multicast group
                    socket.leaveGroup(group, intf);

                    // Properly close the socket
                    socket.close();

                    // Exit the loop
                    break;
                }

                // TODO: Ask user for their name
                // message = String.format("%s: %s", name, message);

                // Store the bytes to send to user
                byte[] buffer = message.getBytes();

                // Create the packet to send the data in
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                // Send the above packet
                socket.send(packet);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Illegal arguments provided. You must provide the following: <address> <port>");
        } catch (Exception e) {
            System.err.println("Fatal error has occurred. Program cannot continue.");
            e.printStackTrace();
        }
    }
}