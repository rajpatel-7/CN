import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(host, port)) {
            System.out.println("Connected to server.");

            // Input and output streams
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            String userInput;
            while (true) {
                System.out.print("Enter message (type 'exit' to quit): ");
                userInput = console.readLine();
                output.println(userInput);
                if (userInput.equalsIgnoreCase("exit")) {
                    break;
                }
                String response = input.readLine();
                System.out.println("Server says: " + response);
            }

            socket.close();
            System.out.println("Disconnected from server.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
