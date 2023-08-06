import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(22222);
        System.out.println("Server started..");
        int cnt = 1;
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client Connected..");
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
    
            Object cMsg;
            try {
                cMsg = ois.readObject();
                System.out.println("From Client: " + (String)cMsg);
    
                String serverMsg = (String) cMsg;
                serverMsg = "Message " + cnt + " " + serverMsg;
                oos.writeObject(serverMsg);
                cnt++;
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
