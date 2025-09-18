/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author yumin
 */
public class Especificacao {
     private int idespecificacao;
    private String nome;
    private double quantia;
    private int dataespecificacao;

    public int getIdespecificacao() {
        return idespecificacao;
    }

    public void setIdespecificacao(int idespecificacao) {
        this.idespecificacao = idespecificacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String Nome) {
        this.nome = nome;
    }

    public double getQuantia() {
        return quantia;
    }

    public void setQuantia(double quantia) {
        this.quantia = quantia;
    }

    public int getDataespecificacao() {
        return dataespecificacao;
    }

    public void setDataespecificacao(int dataespecificacao) {
        this.dataespecificacao = dataespecificacao;
    }
    
}
