
package db;

import static db.connection.getConnection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi.Dfile;
import rmi.Message;
import rmi.User;



public class dataBase extends connection{
    
   
    // creer un compte 
     synchronized public static boolean SignUp(String firstName,String lastName,String password) throws SQLException{
 
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        boolean bool = true;
        int b = 0;
        try {
            con = getConnection();
            String Query1 = "SELECT * FROM users";
            st = con.createStatement();
            res = st.executeQuery(Query1);
              
            while(res.next()){
               if(res.getString("firstName").equals(firstName) && res.getString("lastName").equals(lastName)){
                        bool = false;
                        break;
               }    
            }
            if(bool){
                System.out.println("1");
                System.out.println(firstName);
                System.out.println(lastName);
                System.out.println(password);
               String Query2 = "INSERT INTO users(firstName,lastName,password,isLogin,isAdmin) VALUES('"+firstName+"','"+lastName+"','"+password+"',0,0)";
               st.execute(Query2);
               System.out.println("2");
            }
            
        } catch (Exception e) {
        }finally{
            res.close();
            st.close();
            con.close();
             
        }
        return bool;
    }
    
     // verifier un compte existe ou non
     synchronized public static boolean Login(String firstName,String lastName,String password) throws SQLException{
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        boolean b = false; 
        String Query;
        User user ;
        int Id = 0;
          try {
            con = getConnection();
            
            Query = "SELECT * FROM users";
            st = con.createStatement();
            res = st.executeQuery(Query);
            while(res.next()){
               if(res.getString("firstName").equals(firstName) && res.getString("lastName").equals(lastName)&& res.getString("password").equals(password)){
                        b = true;
                        Id = res.getInt("id");
                        System.out.println(Id);
                        System.out.println(b);
                        break;
               }    
            }
            if(b){
                System.out.println("update");
                 st = con.createStatement();
                Query = "UPDATE users SET isLogin = 1 WHERE id = "+Id; 
                st.executeUpdate(Query);
            }
        } catch (Exception e) {
        }finally{
            System.out.println(b);
            res.close();
            st.close();
            con.close();
             
        }
       return b;
    }
     
     // retourne tous les utilisateurs qui existent dans le systeme
     synchronized public static ArrayList<User> getAllUsers() throws SQLException{
        ArrayList<User> users = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null; 
        User user ;
         
        try {
            con = getConnection();
            String Query = "SELECT * FROM users";
            st = con.createStatement();
            res = st.executeQuery(Query);
            while(res.next()){
               user = new User(res.getInt("id"),res.getString("firstName"),res.getString("lastName"),res.getString("password"),res.getInt("isLogin"),res.getInt("isAdmin"));
               users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dataBase.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            res.close();
            st.close();
            con.close(); 
        }
            
       return users;
    }
     
     synchronized public static void LogOut(int id) throws SQLException{
        Connection con = null;
        Statement st = null;
      try {
        con = getConnection();
        String Query = "UPDATE users SET isLogin = 0 WHERE id = "+id; ;
        st = con.createStatement();
        st.executeUpdate(Query);
      } catch (SQLException ex) {
          Logger.getLogger(dataBase.class.getName()).log(Level.SEVERE, null, ex);
      }finally{
            st.close();
            con.close(); 
        }
       
    }
     
      synchronized public static void saveMessage(int emetter_Id,int receptor_Id,String Message) throws SQLException{
        Connection con = null;
        Statement st = null;
      try {
        con = getConnection();
        st = con.createStatement();
        String Query = "INSERT INTO chat(emetter_Id,receptor_Id,message) VALUES('"+emetter_Id+"','"+receptor_Id+"','"+Message+"')";
        st.execute(Query);
      } catch (SQLException ex) {
          Logger.getLogger(dataBase.class.getName()).log(Level.SEVERE, null, ex);
      }finally{
          st.close();
          con.close();
      }
   }
      
      synchronized public static boolean isLogin(int id){
       Connection con = null;
       Statement st = null;
       ResultSet res = null;
       boolean b = false;
      try {
          con = getConnection();
          String Query = "SELECT isLogin FROM users WHERE id = '"+id+"'";
          st = con.createStatement();
          res = st.executeQuery(Query);
         
           while(res.next()){
              int connect = res.getInt("isLogin");
              if(connect == 1){
                 b = true; 
                 break;
              }
               
            }
           
      } catch (SQLException ex) {
          Logger.getLogger(dataBase.class.getName()).log(Level.SEVERE, null, ex);
      }
           
      return b;
   }
      
      synchronized public static void saveFile(int idEmit, int idRece,String nameFile,int len ,byte[] b) throws IOException, SQLException{
         Connection con = null;
             PreparedStatement pst = null;
             Statement st = null;
             String Query1 = null ;//"UPDATE users set firstname = ? where id = ?";
             String Query2 = null; //
             OutputStream oos = null ;
             File file ;
             FileInputStream input ;
          try {
             
             String fileName = nameFile;
             System.out.println(fileName);
             long l = len;
             System.out.println(l);
             byte[] buf = new byte[(int)l];
             oos = new FileOutputStream(fileName);
             DataOutputStream dos = new DataOutputStream(oos);
             dos.write(b, 0, b.length);
             dos.flush();
             file = new File(fileName);
             input = new FileInputStream(file);
             con = getConnection();
             st  = con.createStatement();
             Query2 = "INSERT INTO savefile VALUES('"+idEmit+"','"+idRece+"','"+nameFile+"','"+len+"','"+null+"')";
             st.execute(Query2);
       
             Query1 = "UPDATE savefile SET file = ? WHERE idEmit = '"+idEmit+"' && idRece = '"+idRece+"'";
             pst = con.prepareStatement(Query1);
             pst.setBinaryStream(1, input);
             pst.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(dataBase.class.getName()).log(Level.SEVERE, null, ex);
         }finally{
             st.close();
             con.close();
         }
           
           
      }
      
     synchronized public static String getFilename(int id){
         Connection con = null;
         Statement st = null;
         ResultSet res = null;
         String Query;
         String filename = null;
         try {
            con = getConnection();
            Query = "SELECT namefile FROM savefile";
            st = con.createStatement();
            res = st.executeQuery(Query);
             while(res.next()){
              filename = res.getString("namefile");
             }
         } catch (SQLException ex) {
             Logger.getLogger(dataBase.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         return filename;
     }
                
      
      synchronized public static ArrayList<Message> getMessagesById(int id){
       ArrayList<Message> messages = new ArrayList<>();
       Connection con = null;
       Statement st = null;
       ResultSet res = null;
       Message message ;
       
      try {
          con = getConnection();
          String Query = "SELECT * FROM chat WHERE emetter_Id = '"+id+"' OR receptor_Id = '"+id+"'";
          st = con.createStatement();
          res = st.executeQuery(Query);
          while(res.next()){
             message = new Message(res.getInt("emetter_Id"),res.getInt("receptor_Id"),res.getString("message"));
             messages.add(message);
          }
      } catch (SQLException ex) {
          Logger.getLogger(dataBase.class.getName()).log(Level.SEVERE, null, ex);
      }
       return messages; 
   }
   
}
