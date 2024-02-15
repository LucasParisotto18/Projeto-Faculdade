/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import model.OrdenacaoPorData;

/**
 *
 * @author Lucas
 */
public class Movimentos {

    private ArrayList<Transacoes> contas;   
    //private double saldo = 0;

    public Movimentos() {
        this.contas = new ArrayList<>();
    }

    public void incluirMovimentos(Transacoes conta) {
        contas.add(conta);
    }

    public ArrayList<Transacoes> getContas() {
        return contas;
    }


    /**
     * Consulta e soma os valores de todos os Objetos do Array
     *
     * @return Retorna uma variavel double com o resultado da soma
     */
    public double consultarSaldoSemPeriodo() {
        double saldo = 0;
        for (Transacoes t : contas) {
            if (t instanceof Receitas) {
                saldo += t.getValor();
            } else {
                saldo -= t.getValor();
            }

        }
        return saldo;
    }

    /**
     * Consulta e soma os valores dos Objetos do Array, os quais tem a data
     * menor ou igual a atual.
     *
     * @return Retorna uma variavel double com o resultado da soma
     */
    public double consultarSaldoAteHoje() {
        double saldo = 0;
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Transacoes t : contas) {
            LocalDate dataAjustada = LocalDate.parse(t.getData(), dtf);
            if (dataAjustada.isBefore(dataAtual) || dataAjustada.isEqual(dataAtual)) {
                if (t instanceof Receitas) {
                    saldo += t.getValor();
                } else {
                    saldo -= t.getValor();
                }
            }

        }
        return saldo;

    }

    /**
     * Consulta e soma os valores dos Objetos do Array, os quais tem a data
     * dentro dos parâmetros de entrada do método
     *
     * @param de Espera uma data de inicio (String), que sará convertida em
     * LocalDate.
     * @param ate Espera uma data de termino (String), que sará convertida em
     * LocalDate.
     * @return Retorna uma variavel double com o resultado da soma dos objetos
     * nesse período
     */
    public double consultarSaldoPeriodo(String de, String ate) {
        double saldo = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //LocalDate dataAtual = LocalDate.now();
        try {
            LocalDate dataAte = LocalDate.parse(ate, dtf);
            LocalDate dataDe = LocalDate.parse(de, dtf);

            if (dataAte.isBefore(dataDe)) {
                throw new IllegalArgumentException("A data de término não pode ser anterior à data de início.");
            }
            
            for (Transacoes t : contas) {
                //saldo = consultarSaldoAteHoje();

                LocalDate dataAjustada = LocalDate.parse(t.getData(), dtf);

                if ((dataAjustada.isBefore(dataAte) || dataAjustada.isEqual(dataAte)) && (dataAjustada.isAfter(dataDe) || dataAjustada.isEqual(dataDe))) {
                    if (t instanceof Receitas) {
                        saldo += t.getValor();
                    } else {
                        saldo -= t.getValor();
                    }
                }

            }
        } catch (DateTimeParseException e) {
        throw new IllegalArgumentException("Formato de data inválido. Use o formato dd/MM/yyyy.", e);
        }

        return saldo;

    }

    /**
     * Faz uma cópia de um Array e ordena o mesmo de acordo com a classe interna
     * que foi passada(OrdenacaoPorData())
     *
     * @param listaTransacoes Array que será copiado e ordenado (Transacoes)
     * @return Retorna um ArrayList(Transacoes) ordenado por Data
     */
    public ArrayList<Transacoes> ordenarDataCrescente(ArrayList<Transacoes> listaTransacoes) {

        ArrayList<Transacoes> copiaLista = new ArrayList<>(listaTransacoes);

        Collections.sort(copiaLista, new OrdenacaoPorData());

        return copiaLista;

    }

}
