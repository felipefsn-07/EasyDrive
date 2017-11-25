/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.modelo.bean.Endereco;
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
public class EnderecoDaoTest {
    EnderecoDao teste;
    Endereco testeEndereco1;
    Endereco testeEndereco2;
    public EnderecoDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        teste = new EnderecoDao();
        testeEndereco1 = new Endereco("199","Limeira","SP","Rua","Joaquim Procópio","12308.201");
        testeEndereco2 = new Endereco("1100","Pirassununga","SP","Rua","Vila Nova","13632.201");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of cadastrarEndereco method, of class EnderecoDao.
     */
    @Test
    public void testCadastrarEndereco() {
        assertEquals("Test cadastrarEndereco, fail",true,teste.cadastrarEndereco(testeEndereco1));
    }

    /**
     * Test of consultarEndereco method, of class EnderecoDao.
     */
    @Test
    public void testConsultarEndereco() {
        List<Endereco> testeLista = null;
        testeLista.add(testeEndereco1);
        testeLista.add(testeEndereco2);
        teste.cadastrarEndereco(testeEndereco2);
        assertEquals("Test consultarEndereco, fail",testeLista,teste.consultarEndereco());
        
    }

    /**
     * Test of alterarEndereco method, of class EnderecoDao.
     */
    @Test
    public void testAlterarEndereco() {
        testeEndereco2.setCodEndereco(1);
        assertEquals("Test alterarEndereco, fail",true,teste.alterarEndereco(testeEndereco2));
    }
    
}
