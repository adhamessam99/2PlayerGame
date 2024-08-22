package org.example;

import java.net.*;
import java.io.*;


public class PlayerServer {
    private int messageCounter;
    private ServerSocket serverSocket;

    public PlayerServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        messageCounter = 0;
    }

// listen for connection from client, read messages from client and send responses, increments counter
    public void start() {
        try (Socket clientSocket = serverSocket.accept();
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            String inputLine;
            while ((inputLine = in.readLine()) != null && messageCounter < 10) {
                messageCounter++;
                System.out.println("Server received: " + inputLine);
                String response = "Reply " + messageCounter + " (Message " + messageCounter + ")";
                out.println(response);
                System.out.println("Server sent: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        PlayerServer serverPlayer = new PlayerServer(8080);
        serverPlayer.start();
    }
}
