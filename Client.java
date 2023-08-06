import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("Client started");
        Socket socket = new Socket("127.0.0.1", 22222);
        System.out.println("Client connected..");

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        Scanner sc = new Scanner(System.in);

        String message = sc.nextLine();

        // sent to server
        oos.writeObject(message);

        try {
            Object fromServer = ois.readObject();
            System.out.println("From Server: " + (String)fromServer);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
