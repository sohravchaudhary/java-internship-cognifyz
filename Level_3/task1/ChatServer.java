import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static Set<Socket> clientSockets = new HashSet<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is listening on port 1234");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                synchronized (clientSockets) {
                    clientSockets.add(socket);
                }


                new ClientHandler(socket).start();
            }
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }


    public static void broadcastMessage(String message, Socket senderSocket) {
        synchronized (clientSockets) {
            for (Socket socket : clientSockets) {
                if (socket != senderSocket) {
                    try {
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        out.println(message);
                    } catch (IOException e) {
                        System.out.println("Error sending message to client: " + e.getMessage());
                    }
                }
            }
        }
    }


    public static void removeClient(Socket socket) {
        synchronized (clientSockets) {
            clientSockets.remove(socket);
        }
        System.out.println("Client disconnected");
    }
}

class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Received: " + message);
                ChatServer.broadcastMessage(message, socket);
            }
        } catch (IOException e) {
            System.out.println("Error handling client: " + e.getMessage());
        } finally {
            ChatServer.removeClient(socket);
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Error closing client socket: " + e.getMessage());
            }
        }
    }
}
