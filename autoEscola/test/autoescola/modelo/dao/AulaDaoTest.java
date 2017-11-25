/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.modelo.bean.Aula;
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
public class AulaDaoTest {
    AulaDao teste;
    Aula aulateste1;
    Aula aulateste2;
    public AulaDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        teste = new AulaDao();
        aulateste1=new Aula();
        aulateste2=new Aula();
        aulateste1.setDataAula("16/11/2017");
        aulateste1.setHorarioAulaInicio("7:00");
        aulateste1.setHorarioAulaFim("8:40");
        aulateste2.setDataAula("17/11/2017");
        aulateste2.setCodAulas(2);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of cadastrarAula method, of class AulaDao.
     */
    @Test
    public void testCadastrarAula() {
        assertEquals("Test cadastrarAula, fail",true,teste.cadastrarAula(aulateste1));
    }

    /**
     * Test of consultarAulas method, of class AulaDao.
     */
    @Test
    public void testConsultarAulas() {
        teste.cadastrarAula(aulateste2);
        List<Aula> testeLista = null;
        testeLista.add(aulateste1);
        testeLista.add(aulateste2);
        assertEquals("Test consultarAulas, fail",testeLista,teste.consultarAulas());
    }

    /**
     * Test of consutarAulaExiste method, of class AulaDao.
     */
    @Test
    public void testConsutarAulaExiste() {
        assertEquals("Test consutarAulaExiste, fail",true,teste.consutarAulaExiste(aulateste1.getCodAulas()));
    }

    /**
     * Test of alterarAula method, of class AulaDao.
     */
    @Test
    public void testAlterarAula() {
        aulateste2.setCodAulas(1);
        assertEquals("Test alterarAula, fail",true,teste.alterarAula(aulateste2));
    }

    /**
     * Test of excluirAula method, of class AulaDao.
     */
    @Test
    public void testExcluirAula() {
        assertEquals("Teste excluirAula, fail",true,teste.excluirAula(aulateste1));
    }
    
}
