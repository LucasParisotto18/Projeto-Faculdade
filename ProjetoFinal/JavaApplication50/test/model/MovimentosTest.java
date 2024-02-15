/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucas
 */
public class MovimentosTest {

    public MovimentosTest() {
    }

    @Test
    public void testConsultarSaldoSemPeriodo() {
        Movimentos movimentos = new Movimentos();
        TpDespesa td = TpDespesa.ALIMENTACAO;
        TpReceita tr = TpReceita.DECIMOTERCEIRO;

        Receitas receita1 = new Receitas(tr, 100, "Receita1", "21/10/2023", "Descrição Receita1");
        Despesas despesa1 = new Despesas(td, 40, "Despesa1", "20/10/2023", "Descrição Despesa1");

        movimentos.incluirMovimentos(receita1);
        movimentos.incluirMovimentos(despesa1);

        double saldoAtual = movimentos.consultarSaldoSemPeriodo();

        double saldoEsperado = 60;

        assertEquals(saldoEsperado, saldoAtual, 0.01);
     
    }

    @Test
    public void testConsultarSaldoAteHoje() {
        Movimentos movimentos = new Movimentos();

        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Receitas receita1 = new Receitas(TpReceita.DECIMOTERCEIRO, 100, "Receita1", dataAtual.format(dtf), "Descrição Receita1");
        Despesas despesa1 = new Despesas(TpDespesa.ALIMENTACAO, 40, "Despesa1", dataAtual.minusDays(1).format(dtf), "Descrição Despesa1");
        Despesas despesa2 = new Despesas(TpDespesa.ENTRETERIMENTO, 30, "Despesa2", dataAtual.plusDays(1).format(dtf), "Descrição Despesa2");

        movimentos.incluirMovimentos(receita1);
        movimentos.incluirMovimentos(despesa1);
        movimentos.incluirMovimentos(despesa2);

        double saldoAtual = movimentos.consultarSaldoAteHoje();

        double saldoEsperado = 60;

        assertEquals(saldoEsperado, saldoAtual, 0.01);
    }

    @Test
    public void testConsultarSaldoPeriodo() {
        Movimentos movimentos = new Movimentos();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Receitas receita1 = new Receitas(TpReceita.DECIMOTERCEIRO, 100, "Receita1", "01/01/2023", "Descrição Receita1");
        Despesas despesa1 = new Despesas(TpDespesa.ALIMENTACAO, 50, "Despesa1", "15/01/2023", "Descrição Despesa1");
        Despesas despesa2 = new Despesas(TpDespesa.ALIMENTACAO, 30, "Despesa2", "01/02/2023", "Descrição Despesa2");

        movimentos.incluirMovimentos(receita1);
        movimentos.incluirMovimentos(despesa1);
        movimentos.incluirMovimentos(despesa2);

        double saldoAtual = movimentos.consultarSaldoPeriodo("01/01/2023", "28/02/2023");

        double saldoEsperado = 100 - 50 - 30;

        assertEquals(saldoEsperado, saldoAtual, 0.01);

        saldoAtual = movimentos.consultarSaldoPeriodo("01/01/2023", "15/01/2023");

        saldoEsperado = 100 - 50;

        assertEquals(saldoEsperado, saldoAtual, 0.01);
    }

    @Test
    public void testOrdenarDataCrescente() {
        Movimentos movimentos = new Movimentos();

        Transacoes transacao1 = new Transacoes(100, "Transacao1", "01/01/2023", "Descrição Transacao1");
        Transacoes transacao2 = new Transacoes(50, "Transacao2", "15/01/2023", "Descrição Transacao2");
        Transacoes transacao3 = new Transacoes(30, "Transacao3", "14/01/2023", "Descrição Transacao3");

        movimentos.incluirMovimentos(transacao1);
        movimentos.incluirMovimentos(transacao2);
        movimentos.incluirMovimentos(transacao3);

        ArrayList<Transacoes> transacoesOrdenadas = movimentos.ordenarDataCrescente(movimentos.getContas());

        List<Transacoes> transacoesEsperadas = Arrays.asList(transacao1, transacao3, transacao2);

        assertEquals(transacoesEsperadas, transacoesOrdenadas);
    }

}
