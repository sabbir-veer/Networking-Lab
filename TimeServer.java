import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TimeServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(22222);
        System.out.println("Server started..");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client Connected..");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                String serverMsg = "Server Date: " +(new Date()).toString();
                oos.writeObject(serverMsg);
        }
    }

}
