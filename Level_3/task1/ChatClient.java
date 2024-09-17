import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 12345;

        try (Socket socket = new Socket(hostname, port)) {
            System.out.println("Connected to chat server");

           
            new ReadMessageThread(socket).start();

            
            try (BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                String userInput;
                while ((userInput = consoleInput.readLine()) != null) {
                    out.println(userInput);
                }
            }

        } catch (IOException e) {
            System.out.println("Error connecting to the server: " + e.getMessage());
        }
    }
}


class ReadMessageThread extends Thread {
    private Socket socket;

    public ReadMessageThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            System.out.println("Error reading from server: " + e.getMessage());
        }
    }
}
