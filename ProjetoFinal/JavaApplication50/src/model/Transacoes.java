    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Lucas
 */
public class Transacoes {
    private double valor;
    private String nome;
    private String data;
    private String descricao;
   
    public Transacoes(){
        
    }
    
    public Transacoes(double valor, String nome, String data, String descricao) {
        this.valor = valor;
        this.nome = nome;
        this.data = data;
        this.descricao = descricao;
    }

    
    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
}
