/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.bean;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Turato
 */
public class AulasClientesTest {
    AulasClientes teste;
    
    public AulasClientesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        teste = new AulasClientes();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAluno method, of class AulasClientes.
     */
    @Test
    public void testGetAluno() {
        Cliente aluno;
        aluno = new Cliente();
        aluno.setNome("João");
        aluno.setCelular("(11)22222-3333");
        teste.setAluno(aluno);
        assertEquals("Test Get and Set Aluno, succeed",aluno,teste.getAluno());
    }

    /**
     * Test of setAluno method, of class AulasClientes.
     */
    @Test
    public void testSetAluno() {
    }

    /**
     * Test of getAulas and setAulas method, of class AulasClientes.
     */
    @Test
    public void testGetAulas() {
        Aula aula;
        aula = new Aula();
        aula.setDataAula("17/10/2017");
        aula.setHorarioAula("7:00");
        teste.setAulas(aula);
        assertEquals("Test Get and Set Aulas, succeed",aula,teste.getAulas());
    }
    
}
