
package runnable;

import db.dataBase;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi.Message;



public class ReadMessage implements Runnable{

    Socket socket;
    ObjectInputStream input;
    ObjectOutputStream out;
    Integer Int; 
   // addSockets add = new addSockets();
    public ReadMessage(Socket socket) throws IOException {
        this.socket = socket;
        input = new ObjectInputStream(this.socket.getInputStream());
    }
    
    
    
    @Override
    public void run() {
       while(true){
           try {
               Int = (Integer) input.readObject();
               addSockets.Sockets.put(Int, socket);
               System.out.println("size : "+addSockets.Sockets.size());
               while(true){
                this.readMessage();    
               }

           } catch (IOException ex) {
               Logger.getLogger(ReadMessage.class.getName()).log(Level.SEVERE, null, ex);
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(ReadMessage.class.getName()).log(Level.SEVERE, null, ex);
           } catch (SQLException ex) {
               Logger.getLogger(ReadMessage.class.getName()).log(Level.SEVERE, null, ex);
           }
            }
    }

    private void readMessage() throws IOException, ClassNotFoundException, SQLException {       
       Message message = (Message) input.readObject();
       dataBase.saveMessage(message.getIdEmit(),message.getIdRece(),message.getMessage());
       boolean b = dataBase.isLogin(message.getIdRece());
       System.out.println("isLogin :"+b);
       if(b){
           //out.writeObject(message);
            System.out.println("isLogin"+b);
            for(Integer key : addSockets.Sockets.keySet()){
                System.out.println("key :"+key);
                System.out.println("rece :"+message.getIdRece());
                Integer i = message.getIdRece();
                System.out.println("s : "+i);
                if(key.equals(i)){
                    System.out.println("equal");
                    if(out == null)
                        out = new ObjectOutputStream(addSockets.Sockets.get(key).getOutputStream()); 
                    
                    System.out.println("my message :"+message.getMessage());
                    out.writeObject(message);
                }
            }
       }
        
    }
    
}
