/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.dao.UserDAO;
import model.dao.UsuarioDAO;

/**
 *
 * @author yumin
 */


/**
 *
 * @author LENOVO
 */
public class Validacao {
     public static int calcularIdade(String dataNascimento) {
        if (dataNascimento == null || dataNascimento.contains("_")) {
            return -1;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataNasc = LocalDate.parse(dataNascimento, formatter);
            LocalDate dataAtual = LocalDate.now();
            return Period.between(dataNasc, dataAtual).getYears();
        } catch (DateTimeParseException e) {
            // JOptionPane.showMessageDialog(null, "Formato de data inválido. Use o formato dd/MM/yyyy.");
            return -1;
        }
    }

    public static boolean validarEmail(String email) {
        // Expressão regular para validar e-mails
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean verificarSeEmailExiste(String email) {
        UserDAO user = new UserDAO();
        UsuarioDAO ud = new UsuarioDAO();
        boolean verificaUser = user.emailExistsInTable(email, "User");
        return verificaUser;
    }
    
    public static boolean validarSenha(String senha, String senhaconf) {
        
        for(char c: senha.toCharArray()){
            
            if(Character.isDigit(c) || senha.equals(senhaconf)){
                return true;
            }
        }
        return false;
    }
    
    
    public static boolean validarDependentes(int dependentes){
        if(dependentes >= 0 ){
            return true;
        }
        return false;
    }
}
