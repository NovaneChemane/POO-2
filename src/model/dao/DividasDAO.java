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
import model.bean.Dividas;
import model.bean.User;

public class DividasDAO {

    Connection con = ConnectionDatabase.getConnection();
    PreparedStatement stmt = null;

    public void create(Dividas div) {

        try {
            stmt = con.prepareStatement("insert into dívidas(IdDívidas,estado,designação,valor,dataDívidas,emailUsuario) values (?,?,?,?,?,?)");

            stmt.setInt(1, div.getIdDividas());
            stmt.setString(2, div.getEstado());
            stmt.setString(3, div.getDesigna());
            stmt.setDouble(4, div.getValor());
            stmt.setDate(5, div.getDatadividas());
            stmt.setString(6, div.getEmailUsuario());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DividasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public void atualizar(Dividas div) {

        try {
            stmt = con.prepareStatement("update dividas set estado=?, designação=?, valor=?, datadividas=? where IdDívidas=?");
            stmt.setString(1, div.getEstado());
            stmt.setString(2, div.getDesigna());
            stmt.setDouble(3, div.getValor());
            stmt.setDate(4, div.getDatadividas());
            stmt.setInt(5, div.getIdDividas());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DividasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public boolean delete(int id, String email) {

        try {
            stmt = con.prepareStatement("delete from dividas where IdDívidas=? and emailUsuario?");
            stmt.setInt(1, id);
            stmt.setString(2, email);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0; // Retorna true se alguma linha foi afetada
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao remover Dívida: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false; // Retorna false em caso de erro
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public List<Dividas> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;
        List<Dividas> dividas = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select *from dividas");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Dividas divida = new Dividas();

                divida.setIdDividas(rs.getInt("IdDívidas"));
                divida.setEstado(rs.getString("Estado"));
                divida.setDesigna(rs.getString("designação"));
                divida.setValor(rs.getDouble("Valor"));
                divida.setDatadividas(rs.getDate("datadividas"));

                dividas.add(divida);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ActivosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return dividas;
    }

    public boolean apagarDadosUser(User user) {
        try {
            stmt = con.prepareStatement("DELETE FROM dividas WHERE emailUsuario=?");
            stmt.setString(1, user.getEmail());

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0; // Retorna true se alguma linha foi afetada
        } catch (SQLException ex) {
          //  JOptionPane.showMessageDialog(null, "Erro ao Apagar conta: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false; // Retorna false em caso de erro
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
      public List<Dividas> readDividasUsuario(String emailUsuario) {
    Connection con = ConnectionDatabase.getConnection();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Dividas> divid = new ArrayList<>();
    try {
        stmt = con.prepareStatement("SELECT * FROM  dívidas WHERE emailUsuario = ?");
        stmt.setString(1, emailUsuario);  
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            Dividas divida = new Dividas();
            divida.setIdDividas(rs.getInt("IdDívidas"));
            divida.setEstado(rs.getString("estado"));
            divida.setDesigna(rs.getString("designação"));
            divida.setValor(rs.getDouble("Valor"));
            divida.setDatadividas(rs.getDate("datadívidas"));
            divid.add(divida);
        }
    } catch (SQLException ex) {
        Logger.getLogger(DividasDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        ConnectionDatabase.closeConnection(con, stmt, rs);
    }
    return divid;
}
      
      
      public Double getTotalDividas() throws SQLException{
           Double totalDividas = 0.0;
        try {
            stmt=con.prepareStatement("Select SUM(Valor) as totalDividas from Dívidas");
            ResultSet rs = stmt.executeQuery();
                     
                while(rs.next()){
                    totalDividas=rs.getDouble("totalDividas");
        } 
                
                        
        }catch (SQLException ex) {
            Logger.getLogger(ActivosDAO.class.getName()).log(Level.SEVERE, null, ex);
        

      }
      return totalDividas;
    }
}
