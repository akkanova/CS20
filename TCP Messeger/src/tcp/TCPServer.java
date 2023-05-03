package tcp;

import java.io.IOException;
import java.net.ServerSocket;

public class TCPServer extends BaseTCP {
    private final ServerSocket serverSocket;

    public TCPServer() throws IOException {
        // Set to port 0, to have the server socket choose the port
        this(0);
    }

    public TCPServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        bindClientSocket(serverSocket.accept());
    }

    public void stop() throws IOException {
        clientSocket.close();
        serverSocket.close();
    }
}
