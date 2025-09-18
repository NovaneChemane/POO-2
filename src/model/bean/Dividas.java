/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author yumin
 */
public class Dividas {
    private int idDividas;
    private String estado;
    private String designa;
    private double valor;
    private java.sql.Date datadividas;
    private String emailUsuario;

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public int getIdDividas() {
        return idDividas;
    }

    public void setIdDividas(int idDividas) {
        this.idDividas=idDividas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDesigna() {
        return designa;
    }

    public void setDesigna(String designa) {
        this.designa = designa;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public java.sql.Date getDatadividas() {
        return datadividas;
    }

    public void setDatadividas(java.sql.Date datadividas) {
        this.datadividas = datadividas;
    }

}
