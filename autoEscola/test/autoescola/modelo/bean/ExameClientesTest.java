/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.bean;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Turato
 */
public class ExameClientesTest {
    ExameClientes teste;
    
    @Before
    public void setUp() {
        teste = new ExameClientes();
    }
    
    public ExameClientesTest() {
    }

    /**
     * Test of getCliente and setCliente method, of class ExameClientes.
     */
    @Test
    public void testGetCliente() {
        Cliente clienteTest = new Cliente("João","29.584.392-6","392.309.293-49","25/01/1996","(19)3565-7899","(19)99872-3923","2931","A");
        teste.setCliente(clienteTest);
        assertEquals("Test getCliente, succeed",clienteTest,teste.getCliente());
    }
    
    /**
     * Test of getExame method, of class ExameClientes.
     */
    @Test
    public void testGetExame() {
        Exame exameTest = new Exame();
        teste.setExame(exameTest);
        assertEquals("Test getExame, succeed",exameTest,teste.getExame());
    }
    
}
