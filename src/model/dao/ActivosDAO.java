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
import javax.swing.JOptionPane;
import model.bean.Activos;
import model.bean.User;
import model.bean.Usuario;
public class ActivosDAO {
    
    Connection con = ConnectionDatabase.getConnection();
    PreparedStatement stmt= null;
    public void create(Activos ac){
    
    
    
        try {
            stmt=con.prepareStatement("insert into activos(IdActivos,TipoActivos,ValorActivos,DataActivos, emailUsuario) values (?,?,?,?,?)");
            
            stmt.setInt(1, ac.getIdActivos());
            stmt.setString(2,ac.getTipoActivos());
            stmt.setDouble(3,ac.getValorActivos());
            stmt.setDate(4,ac.getDataActivos());
            stmt.setString(5, ac.getEmailUsuario());
            
            stmt.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(ActivosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            ConnectionDatabase.closeConnection(con, stmt);
    }
    }
     public void atualizar(Activos ac){
          
        try {
            stmt=con.prepareStatement("update activos set TipoActivos=?, ValorActivos=?, DataActivos=? where IdActivos=?");
            stmt.setString(1, ac.getTipoActivos());
            stmt.setDouble(2,ac.getValorActivos());
            stmt.setDate(3,ac.getDataActivos());
            stmt.setInt(4,ac.getIdActivos());
            
            stmt.executeUpdate();
           
            
        } catch (SQLException ex) {
            Logger.getLogger(ActivosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            ConnectionDatabase.closeConnection(con, stmt);
        }
     }
    public boolean delete(int id, String email) {
        try {
            stmt = con.prepareStatement("DELETE FROM activos WHERE IdActivos=? and emailUsuario=?");
            stmt.setInt(1, id);
            stmt.setString(2,email);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0; // Retorna true se alguma linha foi afetada
        } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao remover cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        return false; // Retorna false em caso de erro
        } finally {
        ConnectionDatabase.closeConnection(con, stmt);
        }
    }
      public List<Activos> read(String email) {
          Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
      
        ResultSet rs= null;
        List<Activos> ativos= new ArrayList<>();
        try {
            stmt=con.prepareStatement("select *from activos");
           rs=  stmt.executeQuery();
            
           while(rs.next()) {
               
               Activos ativo= new Activos();
               
               ativo.setIdActivos(rs.getInt("IdActivos"));
               ativo.setTipoActivos(rs.getString("TipoActivos"));
               ativo.setValorActivos(rs.getDouble("ValorActivos"));
               ativo.setDataActivos(rs.getDate("DataActivos"));
               
               ativos.add(ativo);
           }
           
        } catch (SQLException ex) {
            Logger.getLogger(ActivosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
               ConnectionDatabase.closeConnection(con, stmt, rs);
     }
         return ativos;
}
      
      
      public boolean apagarDadosUser(User user){
        try {
            stmt = con.prepareStatement("DELETE FROM activos WHERE emailUsuario=?");
            stmt.setString(1, user.getEmail());
           
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0; // Retorna true se alguma linha foi afetada
        } catch (SQLException ex) {
        //JOptionPane.showMessageDialog(null, "Erro ao Apagar conta: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        return false; // Retorna false em caso de erro
        } finally {
        ConnectionDatabase.closeConnection(con, stmt);
        }  
      }
        public List<Activos> readActivosUsuario(String emailUsuario) {
    Connection con = ConnectionDatabase.getConnection();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Activos> ativos = new ArrayList<>();
    try {
        stmt = con.prepareStatement("SELECT * FROM activos WHERE emailUsuario = ?");
        stmt.setString(1, emailUsuario);  // Filtra pelo email do usuário
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            Activos ativo = new Activos();
            ativo.setIdActivos(rs.getInt("IdActivos"));
            ativo.setTipoActivos(rs.getString("TipoActivos"));
            ativo.setValorActivos(rs.getDouble("ValorActivos"));
            ativo.setDataActivos(rs.getDate("DataActivos"));
            ativos.add(ativo);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ActivosDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        ConnectionDatabase.closeConnection(con, stmt, rs);
    }
    return ativos;
}
        
        public Double getTotalActivos() throws SQLException{
           Double totalActivos = 0.0;
        try {
            stmt=con.prepareStatement("Select SUM(ValorActivos) as totalActivos from Activos");
            ResultSet rs = stmt.executeQuery();
                     
                while(rs.next()){
                    totalActivos=rs.getDouble("totalActivos");
        } 
                
                        
        }catch (SQLException ex) {
            Logger.getLogger(ActivosDAO.class.getName()).log(Level.SEVERE, null, ex);
        

      }
      return totalActivos;
    }
}

