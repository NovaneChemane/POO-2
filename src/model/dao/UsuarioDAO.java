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
import model.bean.Usuario;

public class UsuarioDAO {

        public void create(Usuario us) {

        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("insert into usuário (numerodependentes,nome,apelido,idade,email,pass,confirmarpass) values (?,?,?,?,?,?,?)");

            stmt.setInt(1, us.getNumerodependentes());
            stmt.setString(2, us.getNome());
            stmt.setString(3, us.getApelido());
            stmt.setInt(4, us.getIdade());
            stmt.setString(5, us.getEmail());
            stmt.setString(6, us.getPass());
            stmt.setString(7, us.getConfirmarpass());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registado com sucesso");

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    
    

    public void atualizar(Usuario us) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("update usuário set nome =?,apelido=?, idade=?,pass=?, numerodependentes=?,confirmarpass=? where email=?");
            stmt.setInt(5, us.getNumerodependentes());
            stmt.setString(1, us.getNome());
            stmt.setInt(3, us.getIdade());
            stmt.setString(7, us.getEmail());
            stmt.setString(6, us.getConfirmarpass());
            stmt.setString(4, us.getPass());
            stmt.setString(2, us.getApelido());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public void delete(Usuario us) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("delete from usuário where email=?");
            stmt.setInt(1, us.getIdusuario());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public List<Usuario> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            stmt = con.prepareStatement("select *from usuário");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Usuario usuario = new Usuario();

                usuario.setNumerodependentes(rs.getInt("Numero_Dependentes"));
                usuario.setNome(rs.getString("nome"));
                usuario.setIdade(rs.getInt("idade"));
                usuario.setEmail(rs.getString("Email"));

                usuario.setPass(rs.getString("pass"));
                usuario.setApelido(rs.getString("apelido"));

                usuarios.add(usuario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return usuarios;
    }
    
        public boolean selectEmail(String email, String nome){
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "Select * From usuário WHERE nome = ? and email = ?";

            try {
                stmt = con.prepareCall(sql);
                stmt.setString(1, nome);
                stmt.setString(2, email);
                rs = stmt.executeQuery();
                return rs.next();
                
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                 ConnectionDatabase.closeConnection(con, stmt, rs);
            }
            return false;

    } 
        
        public String selectEmail(String email){
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String nome=" ", apelido=" ";
        
        

            try {
                stmt = con.prepareStatement("Select Nome, apelido From usuário WHERE email = ?"); 
                stmt.setString(1, email);
                
                rs = stmt.executeQuery();
               
              while (rs.next()) {

              nome=rs.getString("Nome");
              apelido=rs.getString("apelido");
                
            
            }
                
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                 ConnectionDatabase.closeConnection(con, stmt, rs);
            }
            
            return nome+" "+apelido;
    } 
        
        public void updateSenha(String email, String senha , String confsenha){
             Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("update usuário set pass=? , confirmarpass = ? where email=?");
            
            stmt.setString(1, senha);
            stmt.setString(2, confsenha);
            stmt.setString(3, email);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
        }
        
        public boolean apagarDadosUser(User us) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("delete from usuário where email=?");
            stmt.setString(1, us.getEmail());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
        return false;
    }
        
        
        public double atualizarActivos(double valor){
            
            
            
            
            
            return 0;
        }
        
}
