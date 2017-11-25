/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.modelo.bean.Endereco;
import autoescola.modelo.bean.Funcionario;
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
public class FuncionarioDaoTest {
    FuncionarioDao teste;
    Funcionario testeFuncionario1;
    Funcionario testeFuncionario2;
    public FuncionarioDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        teste = new FuncionarioDao();
        Endereco testeEndereco1 = new Endereco("199","Limeira","SP","Rua","Joaquim Procópio","12308.201");
        Endereco testeEndereco2 = new Endereco("1100","Pirassununga","SP","Rua","Vila Nova","13632.201");
        testeFuncionario1 = new Funcionario("Gabriel","52.406.715-6","421.517.858-40","16/09/1998","(19)3562-4885","(19)99682-8615",testeEndereco1,"7:00","16:00","gabrielturato","3213","Gerente",true,"321321","AB");
        testeFuncionario2 = new Funcionario("Lucas","12.345.678-9","123.456.789-00","25/01/1996","(19)3562-4885","(12)99082-3910",testeEndereco2,"7:00","18:00","lucaskep","dka@3213","Recepcionista",true,"543543","ABC");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of cadastrarFuncionario method, of class FuncionarioDao.
     */
    @Test
    public void testCadastrarFuncionario() {
        assertEquals("Test cadastrarFuncionario, fail",true,teste.cadastrarFuncionario(testeFuncionario1));
    }

    /**
     * Test of consultarFuncionario method, of class FuncionarioDao.
     */
    @Test
    public void testConsultarFuncionario() {
        List<Funcionario> testeLista = null;
        teste.cadastrarFuncionario(testeFuncionario2);
        testeLista.add(testeFuncionario1);
        testeLista.add(testeFuncionario2);
        assertEquals("Test consultaExame, fail",testeLista,teste.consultarFuncionario());
    }

    /**
     * Test of consutarFuncExiste method, of class FuncionarioDao.
     */
    @Test
    public void testConsutarFuncExiste() {
        assertEquals("Test consultaExameExiste",true,teste.consutarFuncExiste(testeFuncionario1.getCodigoFuncionario()));
    }

    /**
     * Test of alterarFunc method, of class FuncionarioDao.
     */
    @Test
    public void testAlterarFunc() {
        testeFuncionario2.setCodigoFuncionario(1);
        assertEquals("Test alterarFuncionario, fail",true,teste.alterarFunc(testeFuncionario2));
    }

    /**
     * Test of excluirFunc method, of class FuncionarioDao.
     */
    @Test
    public void testExcluirFunc() {
        assertEquals("Test excluirExame, fail",true,teste.excluirFunc(testeFuncionario1));
    }
    
}
