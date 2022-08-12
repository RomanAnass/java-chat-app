
package rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;


public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        
        Registry reg = LocateRegistry.getRegistry("127.0.0.1",9999);
        Interface stub = (Interface) reg.lookup("MyServer");
        User user = new User("anass","roman","123456");
        ArrayList<User> list = stub.LoGin(user);
        for (User user1 : list) {
            System.out.println(user1.getFirstname());
        }
       //stub.LogOut(2);
        
        
    }
          
}
