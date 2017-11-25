/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.modelo.bean.Veiculo;
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
public class VeicDaoTest {
    VeicDao teste;
    Veiculo testeVeiculo;
    Veiculo testeVeiculo2;
    public VeicDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        teste = new VeicDao();
        testeVeiculo = new Veiculo("DBV-0086","Gol","2005",20,"Ret",true);
        testeVeiculo2 = new Veiculo("FLL-1209","Fox","2010",30,"Ret",true);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of cadastrarVeiculo method, of class VeicDao.
     */
    @Test
    public void testCadastrarVeiculo() {
        assertEquals("Test first cadastrarVeiculo, fail",true,teste.cadastrarVeiculo(testeVeiculo));
        assertEquals("Test second cadastrarVeiculo, fail",true,teste.cadastrarVeiculo(testeVeiculo2));
    }

    /**
     * Test of consultarVeiculos method, of class VeicDao.
     */
    @Test
    public void testConsultarVeiculos() {
        ArrayList<Veiculo> testeLista = new ArrayList();
        testeLista.add(testeVeiculo);
        testeLista.add(testeVeiculo2);
        assertEquals("Test consultarVeiculos, fail",testeLista,teste.consultarVeiculos());
    }

    /**
     * Test of consutarVeiculoExiste method, of class VeicDao.
     */
    @Test
    public void testConsutarVeiculoExiste() {
        assertEquals("Test consultarVeiculoExiste, fail",true,teste.consutarVeiculoExiste(testeVeiculo.getCodVeiculo()));
        assertEquals("Test consultarVeiculoExiste, fail",true,teste.consutarVeiculoExiste(testeVeiculo2.getCodVeiculo()));
    }

    /**
     * Test of alterarVeiculo method, of class VeicDao.
     */
    @Test
    public void testAlterarVeiculo() {
        testeVeiculo2.setCodVeiculo(1);
        assertEquals("Test alterarVeiculo, fail",true,teste.alterarVeiculo(testeVeiculo2));
    }

    /**
     * Test of excluirVeiculo method, of class VeicDao.
     */
    @Test
    public void testExcluirVeiculo() {
        assertEquals("Test excluirVeiculo, fail",true,teste.excluirVeiculo(testeVeiculo));
    }
    
}
