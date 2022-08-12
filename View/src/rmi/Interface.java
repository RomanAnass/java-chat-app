
package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface Interface extends Remote{
    
    public boolean SignUp(User user)throws RemoteException;
    public ArrayList<User> LoGin(User user)throws RemoteException;
    public void LogOut(int id)throws RemoteException;
    public ArrayList<Message> getMessagesById(int id) throws RemoteException;
    public void SendFile(int idEmit,int idRece,String nameFile,int len,byte[] b)throws RemoteException;
    public Dfile Downloadfile(int id)throws RemoteException;
}
