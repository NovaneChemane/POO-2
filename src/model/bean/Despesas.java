/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author yumin
 */
public class Despesas {
    private int idDespesas;
    private String designacao;

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }
    private double quantiatotal;
    private java.sql.Date Prazopagamento;
    private String emailUsuario;

    public int getIdDespesas() {
        return idDespesas;
    }

    public void setIdDespesas(int idDespesas) {
       this.idDespesas=idDespesas;
        
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public double getQuantiatotal() {
        return quantiatotal;
    }

    public void setQuantiatotal(double quantiatotal) {
        this.quantiatotal = quantiatotal;
    }

    public java.sql.Date getPrazopagamento() {
        return Prazopagamento;
    }

    public void setPrazopagamento(java.sql.Date Prazopagamento) {
        this.Prazopagamento = Prazopagamento;
    }
}
