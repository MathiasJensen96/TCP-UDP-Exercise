package org.example;

import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Server address
        int serverPort = 12345; // Server port number

        try (DatagramSocket socket = new DatagramSocket();
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to the server. Type messages to send (Ctrl+C to exit).");

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                // Convert the input message to bytes
                byte[] sendData = userInput.getBytes();

                // Create a DatagramPacket to send data to the server
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(serverAddress), serverPort);

                // Send the packet to the server
                socket.send(sendPacket);

                // Create a byte array for receiving data
                byte[] receiveData = new byte[1024];

                // Create a DatagramPacket to receive data from the server
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Receive data from the server
                socket.receive(receivePacket);

                // Extract and print the server's response
                String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println(serverResponse);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
