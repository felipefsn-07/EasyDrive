/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.modelo.bean.Exame;
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
public class ExameDaoTest {
    ExameDao teste;
    Exame testeExame;
    Exame testeExame2;
    public ExameDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        teste = new ExameDao();
        testeExame = new Exame();
        testeExame2 = new Exame();
        testeExame.setCodigoExame(1);
        testeExame.setDataExame("16/09/2017");
        testeExame.setHorarioInicio("8:00");
        testeExame2.setCodigoExame(2);
        testeExame2.setDataExame("25/03/2018");
        testeExame2.setHorarioFim("10:00");
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of cadastrarExame method, of class ExameDao.
     */
    @Test
    public void testCadastrarExame() {
        assertEquals("Test cadastrarExame, fail",true,teste.cadastrarExame(testeExame));
    }

    /**
     * Test of consultarExame method, of class ExameDao.
     */
    @Test
    public void testConsultarExame() {
        List<Exame> testeLista = null;
        teste.cadastrarExame(testeExame2);
        testeLista.add(testeExame2);
        testeLista.add(testeExame);
        assertEquals("Test consultaExame, fail",testeLista,teste.consultarExame());
    }

    /**
     * Test of consutarExameExiste method, of class ExameDao.
     */
    @Test
    public void testConsutarExameExiste() {
        assertEquals("Test consultaExameExiste, fail",true,teste.consutarExameExiste(testeExame.getCodigoExame()));
        assertEquals("Test consultaExameExiste, fail",true,teste.consutarExameExiste(testeExame2.getCodigoExame()));
    }

    /**
     * Test of alterarExame method, of class ExameDao.
     */
    @Test
    public void testAlterarExame() {
        testeExame2.setCodigoExame(1);
        assertEquals("Test alterarExame, fail",true,teste.alterarExame(testeExame2));
    }

    /**
     * Test of excluirExame method, of class ExameDao.
     */
    /*@Test
    public void testExcluirExame() {
        assertEquals("Test excluirExame, fail",true,teste.excluirExame(testeExame));
    }*/
    
}
