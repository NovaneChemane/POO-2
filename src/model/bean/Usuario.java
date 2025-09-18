/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author yumin
 */
public class Usuario {
    private int idusuario;
    private int numerodependentes;
    private String nome;
    private int idade;
    private String email;
    private String confirmarpass;
   
    private String pass;
    private String apelido;

    public String getConfirmarpass() {
        return confirmarpass;
    }

    public void setConfirmarpass(String confirmarpass) {
        this.confirmarpass = confirmarpass;
    }
   

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }
    

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getNumerodependentes() {
        return numerodependentes;
    }

    public void setNumerodependentes(int numerodependentes) {
        this.numerodependentes = numerodependentes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
