package org.example;

import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Server address
        int serverPort = 12345; // Server port number

        try (Socket socket = new Socket(serverAddress, serverPort);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to the server.");

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput); // Send user input to the server

                // Receive and print the server's response
                String serverResponse = in.readLine();
                System.out.println(serverResponse);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
