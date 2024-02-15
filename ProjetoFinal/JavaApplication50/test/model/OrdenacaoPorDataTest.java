/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucas
 */
public class OrdenacaoPorDataTest {
    
    public OrdenacaoPorDataTest() {
    }

    @Test
    public void testOrdenacaoPorData() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Transacoes transacao1 = new Transacoes(100.0, "Receita1", "01/01/2023", "Descrição Receita");
        Transacoes transacao2 = new Transacoes(150.0, "Receita2", "02/01/2023", "Descrição Receita");
        Transacoes transacao3 = new Transacoes(50.0, "Receita3", "31/12/2022", "Descrição Receita");

        ArrayList<Transacoes> transacoes = new ArrayList<>();
        transacoes.add(transacao1);
        transacoes.add(transacao2);
        transacoes.add(transacao3);

        Collections.sort(transacoes, new OrdenacaoPorData());

        
        assertEquals(transacao3, transacoes.get(0)); 
        assertEquals(transacao1, transacoes.get(1));
        assertEquals(transacao2, transacoes.get(2)); 
    }
    
}
