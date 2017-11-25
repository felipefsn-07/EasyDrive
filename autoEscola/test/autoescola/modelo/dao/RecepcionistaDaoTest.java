/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.modelo.bean.Endereco;
import autoescola.modelo.bean.Recepcionista;
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
public class RecepcionistaDaoTest {
    RecepcionistaDao teste;
    Recepcionista testeRecepcionista;
    public RecepcionistaDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        teste = new RecepcionistaDao();
        Endereco testeEndereco2 = new Endereco("1100","Pirassununga","SP","Rua","Vila Nova","13632.201");
        testeRecepcionista = new Recepcionista("Lucas","12.345.678-9","123.456.789-00","25/01/1996","(19)3562-4885","(12)99082-3910",testeEndereco2,"7:00","18:00","lucasturato","1234456","Recepcionista",1);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of cadastrarRecepcionista method, of class RecepcionistaDao.
     */
    @Test
    public void testCadastrarRecepcionista() {
        assertEquals("Test cadastrarFuncionario, fail",true,teste.cadastrarRecepcionista(testeRecepcionista));
    }
    
}
