package org.example;

import java.io.*;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        int portNumber = 12345; // Port number to listen on

        try (DatagramSocket socket = new DatagramSocket(portNumber)) {
            System.out.println("Server is listening on port " + portNumber);

            // Create a byte array for receiving data
            byte[] receiveData = new byte[1024];

            while (true) {
                // Create a DatagramPacket to receive data
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Receive data from the client
                socket.receive(receivePacket);

                // Extract the data and client information
                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                System.out.println("Received from " + clientAddress + ":" + clientPort + ": " + clientMessage);

                // Send a response back to the client
                String responseMessage = "Server: " + clientMessage;
                byte[] sendData = responseMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
