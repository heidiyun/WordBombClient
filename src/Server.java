
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

// Thread per connection model
//  : 하나의 연결에 대해서 하나의 스레드를 할당해서 데이터를 주고 받는다.

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);

        while (true) {
            Socket socket = serverSocket.accept();
            // Main Thread



            System.out.println(socket.getRemoteSocketAddress());

            // Connection Thread
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            byte[] buf = new byte[128];
            int len;
            try {
                while ((len = is.read(buf)) != -1) {
                    // len: 수신된 바이트의 크기
                    // len == -1: EOF, 연결이 종료되었다.
                    os.write(buf, 0, len);
                }

                is.close();
                os.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("종료되었습니다.");
            // Connection Thread
        }
    }
}

/*
public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);

        // 클라이언트가 접속을 요청하면, 수락한다.
        // : 세션이 형성된다.
        Socket socket = serverSocket.accept();
        System.out.println(socket.getRemoteSocketAddress());
    }
}
*/














