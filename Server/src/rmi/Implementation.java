
package rmi;

import db.dataBase;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import runnable.addSockets;


public class Implementation implements Interface{

    @Override
    public boolean SignUp(User user) throws RemoteException {
        boolean bool = false;
        try {
            bool = dataBase.SignUp(user.getFirstname(),user.getLastname(),user.getPassword());
            System.out.println("bool : "+bool);
            
        } catch (SQLException ex) {
            Logger.getLogger(Implementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
       
    }

    @Override
    public ArrayList<User> LoGin(User user) throws RemoteException {
        try {
            if(dataBase.Login(user.getFirstname(),user.getLastname(),user.getPassword())){
                System.out.println("1");
                return dataBase.getAllUsers(); 
            }
               
        } catch (SQLException ex) {
            Logger.getLogger(Implementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public void LogOut(int id) throws RemoteException {
        try {
            dataBase.LogOut(id);
        } catch (SQLException ex) {
            Logger.getLogger(Implementation.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    

    @Override
    public ArrayList<Message> getMessagesById(int id) throws RemoteException {
         return dataBase.getMessagesById(id); 
    }

    @Override
    public void SendFile(int idEmit, int idRece,String nameFile,int len ,byte[] b) throws RemoteException { 
        try {
            // System.out.println(idEmit+""+idRece+" "+nameFile+" "+len+" "+b.length);
            dataBase.saveFile(idEmit, idRece, nameFile, len, b);
             
            
        } catch (IOException ex) {
            Logger.getLogger(Implementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Implementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public Dfile Downloadfile(int id) throws RemoteException {
        
        Dfile file = null ;
        String filename = dataBase.getFilename(id);
        File f = new File(filename);
        InputStream  is;
        try {
            is = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(is);  
            DataInputStream dis = new DataInputStream(bis); 
            byte[] buf = new byte[(int) f.length()];
            dis.readFully(buf, 0, buf.length);
            file =new Dfile(0,id, filename,0,buf);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Implementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Implementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return file;
    }
    
}
