package server.management;
import server.player.PlayerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionManager implements Runnable {
    @Override
    public void run() {
        startListening();
    }

    private final int port = 5050;
    private final ServerController serverController;

    public ConnectionManager(ServerController serverController) {
        this.serverController = serverController;
    }

    public void startListening() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connection Manager: Accepted connection from " + clientSocket.getRemoteSocketAddress());
                //Create a new virtual thread for handling the new client connection.
                Thread.startVirtualThread(() -> handleNewConnection(client));
            }
        } catch (IOException e) {
            System.err.println("Connection Manager encountered an error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void handleNewConnection(Socket clientSocket) {
        //Create a new player handler for the cient.
        PlayerHandler handler = new PlayerHandler(clientSocket);
        //Delegate the new PlayerHandler to the server controller for tracking and session management.
        //serverController.registerPlayerHanlder(handler);
        //Start the process of the handler listening for client messages.
        //handler.listen();
    }
}

