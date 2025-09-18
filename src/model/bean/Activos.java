/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author yumin
 */
public class Activos {
    private int idActivos;
    private String tipoActivos;
    private double ValorActivos;
    private java.sql.Date dataActivos;

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }
    private String emailUsuario;



    public int getIdActivos() {
        return idActivos;
    }

    public void setIdActivos(int idActivos) {
        this.idActivos=idActivos;
    }

    public String getTipoActivos() {
        return tipoActivos;
    }

    public void setTipoActivos(String tipoActivos) {
        this.tipoActivos = tipoActivos;
    }

    public double getValorActivos() {
        return ValorActivos;
    }

    public void setValorActivos(double ValorActivos) {
        this.ValorActivos = ValorActivos;
    }

    public java.sql.Date getDataActivos() {
        return dataActivos;
    }

    public void setDataActivos(java.sql.Date dataActivos) {
        this.dataActivos = dataActivos;
    }

    
 
}
