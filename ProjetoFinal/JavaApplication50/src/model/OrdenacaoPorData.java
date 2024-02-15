/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

/**
 *
 * @author Lucas
 */
public class OrdenacaoPorData implements Comparator<Transacoes> {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Compara as datas e retorna a data mais antiga para a mais recente
     * @param t1 Obejto 1 (Transacoes)
     * @param t2 Obejto 2 (Transacoes)
     * @return Retorna a ordenação dos objetos por data (crescente)
     */
    @Override
    public int compare(Transacoes t1, Transacoes t2) {

        try {
           LocalDate data1 = LocalDate.parse(t1.getData(), dtf);
           LocalDate data2 = LocalDate.parse(t2.getData(), dtf);
           return data1.compareTo(data2);  
        } catch (Exception e) {
            throw new RuntimeException("Erro ao comparar datas.");
            
        }
        

    }

}
