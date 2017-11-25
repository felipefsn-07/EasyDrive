/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.modelo.bean.Endereco;
import autoescola.modelo.bean.Instrutor;
import java.util.ArrayList;
import java.util.List;
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
public class InstrutorDaoTest {
    InstrutorDao teste;
    Instrutor testeInstrutor;
    public InstrutorDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        teste = new InstrutorDao();
        Endereco testeEndereco2 = new Endereco("1100","Pirassununga","SP","Rua","Vila Nova","13632.201");
        testeInstrutor = new Instrutor("Felipe","11.222.333-4","111.222.333-00","04/07/1996","(19)3565-7290","(12)97932-1392",testeEndereco2,"6:00","18:00","felipesilva","123926",1,"ABC","10921021");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of cadastrarInstrutor method, of class InstrutorDao.
     */
    @Test
    public void testCadastrarInstrutor() {
        assertEquals("Test cadastrarInstrutor, fail",true,teste.cadastrarInstrutor(testeInstrutor));
    }

    /**
     * Test of consultarInstrutor method, of class InstrutorDao.
     */
    @Test
    public void testConsultarInstrutor() {
        ArrayList<Instrutor> testeLista = new ArrayList();
        testeLista.add(testeInstrutor);
        assertEquals("Test consultarInstrutor, fail",testeLista,teste.consultarInstrutor());
    }
    
}
