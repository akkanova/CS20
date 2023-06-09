package tcp;

import java.io.*;
import java.net.Socket;

public abstract class BaseTCP implements AutoCloseable {
    protected Socket clientSocket;
    protected BufferedReader in;
    protected PrintWriter out;

    protected void bindClientSocket(Socket socket) throws IOException {
        clientSocket = socket;
        out = new PrintWriter(clientSocket.getOutputStream());
        in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream())
        );
    }

    protected void closeStreams() throws IOException {
        out.close();
        in.close();
    }
}
