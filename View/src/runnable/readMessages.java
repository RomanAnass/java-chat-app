/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runnable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import rmi.Message;

/**
 *
 * @author ACH
 */
public class readMessages implements Runnable{

    Socket socket;
    ObjectInputStream input;
    JTextArea msg;

    public readMessages(Socket socket,JTextArea msg) {
        this.socket = socket;
        this.msg = msg;
    }
    
    
    
    @Override
    public void run() {
        
      
    }
    
}
