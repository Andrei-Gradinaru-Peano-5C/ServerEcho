/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclienttoclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Andrei
 */
public class UDPClientSocket {
    private DatagramSocket datagramSocket;
    private int port;
    private String targetIP;

    public UDPClientSocket(int port, String targetIP) throws SocketException {
        this.port = port;
        this.targetIP = targetIP;
        this.datagramSocket = new DatagramSocket(port);
    }
    
    public void send(String text) throws UnknownHostException, IOException {
        // Creo il DatagramPacket che devo inviare
        byte[] buffer = new byte[8192];
        buffer = text.getBytes(StandardCharsets.UTF_8); // Trasformo stringa in byte
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(targetIP), port);
        // Mando il Pachetto tramite il Socket
        this.datagramSocket.send(dp);
        System.out.println(" - Mandato: " + text);
    }
    
    public void listen() throws IOException {
        // Creo il DatagramPacket che devo ricevere
        byte[] buffer = new byte[8192];
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(targetIP), port);
        // Aspetto e accetto i pachetti in arrivo
        this.datagramSocket.receive(dp);
        // Converto il buffer in una Stringa
        String text = new String(dp.getData(), 0, dp.getLength(), StandardCharsets.ISO_8859_1);
        System.out.println(" - Ricevuto: " + text);
    }

}
