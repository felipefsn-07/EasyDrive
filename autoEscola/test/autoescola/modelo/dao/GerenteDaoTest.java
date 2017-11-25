/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.modelo.bean.Endereco;
import autoescola.modelo.bean.Gerente;
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
public class GerenteDaoTest {
    GerenteDao teste;
    Gerente testeGerente;
    public GerenteDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        teste = new GerenteDao();
        Endereco testeEndereco1 = new Endereco("199","Limeira","SP","Rua","Joaquim Procópio","12308.201");
        testeGerente = new Gerente("Gabriel","52.406.715-6","421.517.858-40","16/09/1998","(19)3562-4885","(19)99682-8615",testeEndereco1,"7:00","16:00","gabrielturato","1234","Gerente",1);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of cadastrarGerente method, of class GerenteDao.
     */
    @Test
    public void testCadastrarGerente() {
        assertEquals("Test cadastrarGerente, fail",true,teste.cadastrarGerente(testeGerente));
    }
    
}
