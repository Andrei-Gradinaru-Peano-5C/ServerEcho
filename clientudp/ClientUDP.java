/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclienttoclient;

import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrei
 */
public class ClientUDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        try {
            // TODO code application logic here
            String ip = JOptionPane.showInputDialog("Inserire IP destinatario ((127.0.0.1) Blank for loopback)");
            if(ip.equals("")) { ip = "127.0.0.1"; }
            UDPClientSocket udpc = new UDPClientSocket(2000,ip);
            new Listen(udpc);
            Scanner sc = new Scanner(System.in);
            String mess = "";
            
            while(!mess.equals("quit")) {
                System.out.println("Inserire Messaggio:");
                mess = sc.nextLine();
                new Send(udpc,mess);
                Thread.sleep(10);
            }
        } catch (SocketException ex) {
            Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
