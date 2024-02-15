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
public class Receitas extends Transacoes {
    private TpReceita tipoReceita;

    public Receitas(TpReceita tipoReceita, double valor, String nome, String data, String descricao) {
        super(valor, nome, data, descricao);
        this.tipoReceita = tipoReceita;
    }
    public Receitas(){
        
    }


    public TpReceita getTipoReceita() {
        return tipoReceita;
    }

    public void setTipoReceita(TpReceita tipoReceita) {
        this.tipoReceita = tipoReceita;
    }
    
    
}
