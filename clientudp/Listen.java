/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclienttoclient;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrei
 */
public class Listen implements Runnable{
    private UDPClientSocket client;

    public Listen(UDPClientSocket client) {
    this.client = client;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                client.listen();
            }
        } catch (IOException ex) {
            Logger.getLogger(Listen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
