import java.io.*;
import java.net.*;

public class server {
    public static void main(String[] args) {
        int port = 5000; // Port number
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Waiting for client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            // Input stream to receive data from client
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Output stream to send data to client
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            String message;
            while ((message = input.readLine()) != null) {
                System.out.println("Client says: " + message);
                output.println("Server received: " + message);
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            socket.close();
            System.out.println("Connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
