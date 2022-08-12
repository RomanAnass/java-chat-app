package rmi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import runnable.ReadMessage;

public class Server {
   
    
    
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
       
        Remote stub = UnicastRemoteObject.exportObject(new Implementation(), 0);
        Registry reg = LocateRegistry.createRegistry(9999);
        reg.bind("MyServer", stub);

        System.out.println("Server Runnig ...");
        System.out.println("Waitin for clinet invocation ....");
       

        ServerSocket ss;
       
        try {
            ss = new ServerSocket(8888);
           
            while (true) {            
            Socket socket = ss.accept();
           
            ReadMessage runnable = new ReadMessage(socket);
            Thread t = new Thread(runnable);
            t.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       //Implementation i = new Implementation();
    }

}
