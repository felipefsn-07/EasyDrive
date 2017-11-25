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
public class InstrutorTest {
    
    Instrutor teste;
    
    @Before
    public void setUp() {
        Endereco testEndereco = new Endereco("199","Limeira","SP","Rua","Morro Azul","11212121");
        teste = new Instrutor("João","30.193.298-1","309.483.284-32","19/6/1977","(19)3561-3921","(19)92831-3293",testEndereco,"7:00","18:00","testlogin","123testando",1,"Instrutor","312321");
    }
    
    public InstrutorTest() {
    }

    /**
     * Test of getCategoria and setCategoria method, of class Instrutor.
     */
    @Test
    public void testGetCategoria() {
        teste.setCategoria("ABC");
        assertEquals("Test getCategoria succeed","ABC",teste.getCategoria());
    }

    /**
     * Test of getNumCarteira and setNumCarteira method, of class Instrutor.
     */
    @Test
    public void testGetNumCarteira() {
        teste.setNumCarteira("19982");
        assertEquals("Test getNumCarteira, succeed","19982",teste.getNumCarteira());
    }
}
