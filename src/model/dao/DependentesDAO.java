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
import model.bean.Dependentes;

import model.bean.User;

public class DependentesDAO {

    Connection con = ConnectionDatabase.getConnection();
    PreparedStatement stmt = null;

    public void create(Dependentes dep) {

        try {
            stmt = con.prepareStatement("insert into dependentes(IdDependente,nome,tipo,emailUsuario) values (?,?,?,?)");

            stmt.setInt(1, dep.getIdDependente());
            stmt.setString(2, dep.getNome());
            stmt.setString(3, dep.getTipo());
            stmt.setString(4, dep.getEmailUsuario());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DependentesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }

    }

    public void atualizar(Dependentes dep) {

        try {
            stmt = con.prepareStatement("update dependentes set nome=?, tipo=? where IdDependente=?");
            stmt.setString(1, dep.getNome());
            stmt.setString(2, dep.getTipo());
            stmt.setInt(3, dep.getIdDependente());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DependentesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public boolean delete(int id, String email) {

        try {
            stmt = con.prepareStatement("delete from dependentes where IdDependente=? and emailUsuario=?");
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

    public List<Dependentes> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;
        List<Dependentes> dependentes = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select *from user");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Dependentes dep = new Dependentes();

                dep.setIdDependente(rs.getInt("IdDependente"));
                dep.setNome(rs.getString("Nome"));
                dep.setTipo(rs.getString("Tipo"));

                dependentes.add(dep);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DependentesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return dependentes;
    }

    public boolean apagarDadosUser(User user) {
        try {
            stmt = con.prepareStatement("DELETE FROM dependentes WHERE emailUsuario=?");
            stmt.setString(1, user.getEmail());

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0; // Retorna true se alguma linha foi afetada
        } catch (SQLException ex) {
           // JOptionPane.showMessageDialog(null, "Erro ao Apagar conta: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false; // Retorna false em caso de erro
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
      public List<Dependentes> readDependenteUsuario(String emailUsuario) {
    Connection con = ConnectionDatabase.getConnection();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Dependentes> dependen = new ArrayList<>();
    try {
        stmt = con.prepareStatement("SELECT * FROM dependentes WHERE emailUsuario = ?");
        stmt.setString(1, emailUsuario);  // Filtra pelo email do usuário
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            Dependentes dep = new Dependentes();
            dep.setIdDependente(rs.getInt("IdDependente"));
            dep.setNome(rs.getString("nome"));
            dep.setTipo(rs.getString("tipo"));
      
            dependen.add(dep);
        }
    } catch (SQLException ex) {
        Logger.getLogger(DependentesDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        ConnectionDatabase.closeConnection(con, stmt, rs);
    }
    return dependen;
}
}
