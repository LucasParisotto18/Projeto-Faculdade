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
public class Despesas extends Transacoes{
    private TpDespesa tipoDespesa;

    
    public Despesas(TpDespesa tipoDespesa, double valor, String nome,String data, String descricao) {
        super(valor, nome, data, descricao);
        this.tipoDespesa = tipoDespesa;
    }
    
    public Despesas(){
        
    }
    
    public TpDespesa getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(TpDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

   
    
    
}
