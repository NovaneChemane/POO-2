/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yumin
 */


public class ConnectionDatabase {
    
     private static String driver = "com.mysql.cj.jdbc.Driver";
     private static String URL = "jdbc:mysql://localhost:3306/householder2_bd";
     private static String user = "root";
     private static String password = "dababyzingg2";
     
     
     
     
    public static java.sql.Connection getConnection() {
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
    
    public static void closeConnection(java.sql.Connection con) {
        
        if(con!=null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    public static void closeConnection(java.sql.Connection con, PreparedStatement stmt) {
        closeConnection(con);
        if(stmt!=null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     public static void closeConnection(java.sql.Connection con, PreparedStatement stmt, ResultSet rs) {
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

