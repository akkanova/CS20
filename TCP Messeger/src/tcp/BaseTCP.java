package tcp;

import java.io.*;
import java.net.Socket;

public abstract class BaseTCP {
    protected Socket clientSocket;
    protected BufferedReader in;
    protected PrintWriter out;

    public abstract void stop() throws IOException;

    protected void bindClientSocket(Socket socket) throws IOException {
        clientSocket = socket;
        out = new PrintWriter(clientSocket.getOutputStream());
        in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream())
        );
    }
}
