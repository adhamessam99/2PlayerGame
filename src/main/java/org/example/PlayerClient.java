package org.example;

import java.net.*;
import java.io.*;


public class PlayerClient {
    private PrintWriter out;
    private BufferedReader in;
    private Socket clientSocket;

    // establish connection with server with this specified ip address and port number
    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    // send message to the server and read the response
    public void sendMessage(String msg) throws IOException {
        out.println(msg);
        String response = in.readLine();
        System.out.println("Client received: " + response);
    }

    // close the input(reading) and output(writing) and socket connection
    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }


    public static void main(String[] args) throws IOException {
        PlayerClient client = new PlayerClient();
        client.startConnection("127.0.0.1", 8080);
        for (int i = 0; i < 10; i++) {
            client.sendMessage("Message " + (i + 1));
        }
        client.stopConnection();
    }
}
