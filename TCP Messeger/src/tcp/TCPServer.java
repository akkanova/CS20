package tcp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class TCPServer extends BaseTCP {
    private final ServerSocket serverSocket;

    /** Default port is 0, forcing the ServerSocket to choose it for us */
    public TCPServer() throws IOException { this(0); }

    public TCPServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }


    public void start() throws IOException {
        bindClientSocket(serverSocket.accept());
    }

    public void close() throws IOException {
        clientSocket.close();
        serverSocket.close();
        closeStreams();
    }


    // Getters
    public String getHostname() throws UnknownHostException {
        return InetAddress
            .getLocalHost()
            .getHostAddress();
    }

    public int getPort() {
        return serverSocket.getLocalPort();
    }
}
