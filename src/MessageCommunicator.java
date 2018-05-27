import com.google.protobuf.ByteString;
import com.google.protobuf.DynamicMessage;
import ex1.protobuf.Chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

public class MessageCommunicator {
    private String host;
    private int portNumber;
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private OnCreateReceiveListener createWordReceiver;
    private OnRemoveReceiveListener removeWordReceiver;
    private OnColorCreateReceiveListener createColorWordReceiver;


    public MessageCommunicator(String host, int portNumber) {
        this.host = host;
        this.portNumber = portNumber;
        this.removeWordReceiver = removeWord -> { };
    }

    public void connect() {
        socket = new Socket();
        InetSocketAddress endPoint = new InetSocketAddress(host, portNumber);
        try {
            socket.connect(endPoint);
        } catch (IOException e) {
            socket = null;
        }

        if (socket != null) {
            try {
                is = socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        new Thread(() -> {

            try (InputStream is = socket.getInputStream();
                 DataInputStream dis = new DataInputStream(is)){
                int readBytes;
//                byte[] lengthBuf = new byte[2];
                // byte[] buf = new byte[4096];

                while (true) {
                    int len = dis.readUnsignedShort();

                    byte[] buf = new byte[len];
                    readBytes = dis.read(buf, 0, len);
                    if (readBytes == -1)
                        break;

                    // String str = new String(buf, 0, len);
                    Chat.Protocol protocol = Chat.Protocol.parseFrom(buf);
                    switch (protocol.getProto()) {
                        case "a":
                            createWordReceiver.onReceive(protocol.getWord());
                            break;
                        case "r":
                            removeWordReceiver.onReceive(protocol.getWord());
                            break;
                        default:
                            createColorWordReceiver.onColor(protocol.getWord());
                            break;
                    }
                }
//                while ((len = is.read(bytes)) != -1) {
//                    String s = new String(bytes, 0, len);
//                    Chat.Protocol protocol = Chat.Protocol.parseFrom(s.getBytes());
//                    System.out.println(protocol.getWord());
//                    if (protocol.getProto().equals("a"))
//                        createWordReceiver.onReceive(protocol.getWord());
//                    else if (protocol.getProto().equals("r"))
//                        removeWordReceiver.onReceive(protocol.getWord());
//                    else createColorWordReceiver.onColor(protocol.getWord());
//                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void send(String word) {
        try {
            os.write(word.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    interface OnCreateReceiveListener {
        void onReceive(String word);
    }

    public void setCreateReceiver(OnCreateReceiveListener receiver) {
        this.createWordReceiver = receiver;
    }

    interface OnRemoveReceiveListener {
        void onReceive(String removeWord);
    }


    public void setRemoveReceiver(OnRemoveReceiveListener sender) {
        this.removeWordReceiver = sender;
    }

    interface OnColorCreateReceiveListener {
        void onColor(String word);
    }

    public void setCreateColorReceiver(OnColorCreateReceiveListener receiver) {
        this.createColorWordReceiver = receiver;
    }
}