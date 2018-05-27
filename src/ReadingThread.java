import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReadingThread implements Runnable {
    private Consumer consumer = new Consumer();
    private Client client;
    {
        try {
            client = new Client();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int len;
        byte[] bytes = new byte[128];

        try {
            InputStream is = client.getInputStream();
            while ((len = is.read(bytes)) != -1) {
                String s = new String(bytes, 0, len);
                consumer.getWords().add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class Consumer {
    private static List<String> words = Collections.synchronizedList(new ArrayList<>());
    private static List<String> sendingWords = Collections.synchronizedList(new ArrayList<>());

    public List<String> getWords() {
        return words;
    }

    public static List<String> getSendingWords() {
        return sendingWords;
    }
}