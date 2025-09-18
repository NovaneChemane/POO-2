/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import Connection.ConnectionDatabase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Especificacao;
public class EspecificacaoDAO {
    
    Connection con = ConnectionDatabase.getConnection();
     PreparedStatement stmt= null;
      
      public void create(Especificacao esp){
    
    
    
        try {
            stmt=con.prepareStatement("insert into especificação(IdEspecificacao,nome,quantia,dataespecificacao) values (?,?,?,?)");
            
            stmt.setInt(1, esp.getIdespecificacao());
            stmt.setString(2,esp.getNome());
            stmt.setDouble(3,esp.getQuantia());
            stmt.setInt(4, esp.getDataespecificacao());
           
            
            
            stmt.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(EspecificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            ConnectionDatabase.closeConnection(con, stmt);
    }
}
      
      public void atualizar(Especificacao esp){
          
        try {
            stmt=con.prepareStatement("update Especificacao set nome=?, quantia=?, dataespecificacao=? where IdEspecificacao=?");
            stmt.setString(1, esp.getNome());
            stmt.setDouble(2,esp.getQuantia());
            stmt.setInt(3,esp.getDataespecificacao());
            stmt.setInt(4,esp.getIdespecificacao());
           
            
            
            stmt.executeUpdate();
           
            
        } catch (SQLException ex) {
            Logger.getLogger(EspecificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            ConnectionDatabase.closeConnection(con, stmt);
        }
}
      
        public void delete(Especificacao esp) {
          
        try {
            stmt=con.prepareStatement("delete from Especificacao where IdEspecificacao=?");
            stmt.setInt(1, esp.getIdespecificacao());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EspecificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
             ConnectionDatabase.closeConnection(con, stmt);
        }
      } 
          public List<Especificacao> read() {
          Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
      
        ResultSet rs= null;
        List<Especificacao> especifica= new ArrayList<>();
        try {
            stmt=con.prepareStatement("select *from especificacao");
           rs=  stmt.executeQuery();
            
           while(rs.next()) {
               
               Especificacao espi= new Especificacao();
               
               espi.setIdespecificacao(rs.getInt("Idespecificação"));
               espi.setQuantia(rs.getDouble("Quantia"));
               espi.setNome(rs.getString("nome"));
               espi.setDataespecificacao(rs.getInt("dataEspecificação"));
               
               
               especifica.add(espi);
           }
           
        } catch (SQLException ex) {
            Logger.getLogger(EspecificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
               ConnectionDatabase.closeConnection(con, stmt, rs);
     }
         return especifica;
}
}
