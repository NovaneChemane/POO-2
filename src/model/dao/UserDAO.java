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
import model.bean.User;

public class UserDAO {

    Connection con = ConnectionDatabase.getConnection();
    PreparedStatement stmt = null;

    public void create(User user) {

        try {
            stmt = con.prepareStatement("insert into user(Email,pass) values (?,?)");

            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPass());
            stmt.executeUpdate();
            //JOptionPane.showMessageDialog(null, "User Registado com sucesso");

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public void atualizar(User user) {

        try {
            stmt = con.prepareStatement("update Especificacao set email=?, pass=?");
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPass());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public boolean delete(User user) {

        try {
            stmt = con.prepareStatement("delete from User where email=?");
            stmt.setString(1, user.getEmail());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
        return false;
    }

    public List<User> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;
        List<User> users = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select *from user");
            rs = stmt.executeQuery();

            while (rs.next()) {

                User user = new User();

                user.setEmail(rs.getString("email"));
                user.setPass(rs.getString("pass"));

                users.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return users;
    }

    public boolean selectUser(User user) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM user WHERE email = ? AND pass = ?";
        try {
            stmt = con.prepareStatement(sql); // Definindo o SQL para stmt
            stmt.setString(1, user.getEmail()); // Configurando o email
            stmt.setString(2, user.getPass()); // Configurando a senha

            rs = stmt.executeQuery(); // Executa a consulta
            return rs.next(); // Retorna true se encontrou o usuário
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Fecha a conexão e recursos para evitar vazamento
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return false;
    }

    public boolean emailExistsInTable(String email, String tableName) {
        Connection con = ConnectionDatabase.getConnection();

        ResultSet rs = null;
        PreparedStatement stmt = null;
        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE email = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean apagarDadosUser(User user) {
        try {
            stmt = con.prepareStatement("DELETE FROM User WHERE email=?");
            stmt.setString(1, user.getEmail());

            int rowsAffected = stmt.executeUpdate();

            // Retorna true se alguma linha foi afetada
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Apagar conta: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false; // Retorna false em caso de erro
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
        return true;
    }
}
