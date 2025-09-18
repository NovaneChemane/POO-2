/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConnectionFactory;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ConnectionDatabase {
    
     private static String driver = "com.mysql.cj.jdbc.Driver";
     private static String URL = "jdbc:mysql://localhost:3306/HouseHolder2_bd";
     private static String user = "root";
     private static String password = "dababyzingg2";
     
     
     
     
    public static Connection getConnection() {
        try {
            Class.forName(driver);
            
            try {
                return DriverManager.getConnection(URL, user, password);
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
          
    }
    
    public static void closeConnection(Connection con) {
        
        if(con!=null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    public static void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);
        if(stmt!=null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
         closeConnection(con,stmt);
         if(rs!=null) {
             try {
                 rs.close();
             } catch (SQLException ex) {
                 Logger.getLogger(ConnectionDatabase.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         
     }
}
