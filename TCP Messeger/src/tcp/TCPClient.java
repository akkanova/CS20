package tcp;

import java.io.*;
import java.net.Socket;

public class TCPClient extends BaseTCP {
    public TCPClient(String hostname, int port) throws IOException {
        bindClientSocket(new Socket(hostname, port));
    }

    public void stop() throws IOException {
        clientSocket.close();
    }
}