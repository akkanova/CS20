import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class TCP {
    public final Socket socket;
    public final String host;
    public final int port;

    private final OutputStream output;

    public TCP(int port, OutputStream output) throws IOException {
        this.port = port;
        this.output = output;
        this.host = InetAddress
            .getLocalHost()
            .getHostAddress();

        this.socket = new Socket(host, port);
//        socket.getOutputStream();
    }
}
