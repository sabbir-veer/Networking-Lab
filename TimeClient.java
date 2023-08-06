import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TimeClient {
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("Client started");
        Socket socket = new Socket("127.0.0.1", 22222);
        System.out.println("Client connected..");

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        try {
            Object fromServer = ois.readObject();
            System.out.println("From Server: " + (String)fromServer);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
    }
}
