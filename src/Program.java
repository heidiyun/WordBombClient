import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

class Client {
    private Socket socket;
    Client() throws IOException {
        Socket socket = new Socket();

        InetSocketAddress endPoint =
                new InetSocketAddress("localhost", 5000);

        socket.connect(endPoint);
        this.socket = socket;

        // os = socket.getOutputStream();
        // is = socket.getInputStream();
    }

    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    public InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }
}

public class Program {
    public static void main(String[] args) {
        Window2.main("Window2");
    }
}
