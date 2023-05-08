package tcp;

import java.io.*;
import java.net.Socket;


public class TCPClient extends BaseTCP {
    public void connect(String hostname, int port) throws IOException {
        bindClientSocket(new Socket(hostname, port));
    }


    public void close() throws IOException {
        clientSocket.close();
        closeStreams();
    }
}