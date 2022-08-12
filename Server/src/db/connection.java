
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class connection {
    
    public static Connection getConnection() throws SQLException{
     
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/serverchat","root","");
        if(con != null){
            return con;  
        }
          
        return null;
    }
 
}
