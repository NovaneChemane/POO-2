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

import model.bean.Despesas;
import model.bean.User;

public class DespesasDAO {

    Connection con = ConnectionDatabase.getConnection();
    PreparedStatement stmt = null;

    public void create(Despesas de) {

        try {
            stmt = con.prepareStatement("insert into despesas(IdDespesas,designação,quantiatotal,PrazoDePagamento, emailUsuario) values (?,?,?,?,?)");

            stmt.setInt(1, de.getIdDespesas());
            stmt.setString(2, de.getDesignacao());
            stmt.setDouble(3, de.getQuantiatotal());
            stmt.setDate(4, de.getPrazopagamento());
            stmt.setString(5, de.getEmailUsuario());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DespesasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public void atualizar(Despesas de) {

        try {
            stmt = con.prepareStatement("update despesas set designacao=?, quantiatotal=?, PrazoDePagamento=? where IdDespesas=?");
            stmt.setString(1, de.getDesignacao());
            stmt.setDouble(2, de.getQuantiatotal());
            stmt.setDate(3, de.getPrazopagamento());
            stmt.setInt(4, de.getIdDespesas());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DespesasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public boolean delete(int id, String email) {

        try {
            stmt = con.prepareStatement("delete from despesas where IdDespesas=? and emailUsuario=?");
            stmt.setInt(1, id);
            stmt.setString(2, email);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0; // Retorna true se alguma linha foi afetada
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao remover Dependente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false; // Retorna false em caso de erro
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public List<Despesas> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;
        List<Despesas> despesas = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select *from despesas");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Despesas despesa = new Despesas();

                despesa.setIdDespesas(rs.getInt("IdDespesas"));
                despesa.setDesignacao(rs.getString("Designação"));
                despesa.setQuantiatotal(rs.getDouble("QuantiaTotal"));
                despesa.setPrazopagamento(rs.getDate("PrazoDePagamento"));

                despesas.add(despesa);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DespesasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return despesas;
    }

    public boolean apagarDadosUser(User user) {
        try {
            stmt = con.prepareStatement("DELETE FROM despesas WHERE emailUsuario=?");
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
      public List<Despesas> readDespesaUsuario(String emailUsuario) {
    Connection con = ConnectionDatabase.getConnection();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Despesas> desp = new ArrayList<>();
    try {
        stmt = con.prepareStatement("SELECT * FROM despesas WHERE emailUsuario = ?");
        stmt.setString(1, emailUsuario);  // Filtra pelo email do usuário
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            Despesas despesas = new Despesas();
            despesas.setIdDespesas(rs.getInt("IdDespesas"));
            despesas.setDesignacao(rs.getString("Designação"));
            despesas.setQuantiatotal(rs.getDouble("QuantiaTotal"));
            despesas.setPrazopagamento(rs.getDate("PrazoDePagamento"));
            desp.add(despesas);
        }
    } catch (SQLException ex) {
        Logger.getLogger(DespesasDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        ConnectionDatabase.closeConnection(con, stmt, rs);
    }
    return desp;
}
      
      public Double getTotalDespesas() throws SQLException{
           Double totalDespesas = 0.0;
        try {
            stmt=con.prepareStatement("Select SUM(QuantiaTotal) as totalDespesas from Despesas");
            ResultSet rs = stmt.executeQuery();
                     
                while(rs.next()){
                    totalDespesas=rs.getDouble("totalDespesas");
        } 
                
                        
        }catch (SQLException ex) {
            Logger.getLogger(ActivosDAO.class.getName()).log(Level.SEVERE, null, ex);
        

      }
      return totalDespesas;
    }
}
