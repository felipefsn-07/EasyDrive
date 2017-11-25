/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.modelo.bean.Usuario;
import java.util.ArrayList;
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
public class UsuarioDaoTest {
    UsuarioDao teste;
    Usuario testeUsuario;
    Usuario testeUsuario2;
    public UsuarioDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        teste = new UsuarioDao();
        testeUsuario = new Usuario();
        testeUsuario2 = new Usuario();
        testeUsuario.setCodLogin(1);
        testeUsuario.setLogin("gabrielturato");
        testeUsuario.setSenha("1234");
        testeUsuario.setStatus(1);
        testeUsuario2.setCodLogin(2);
        testeUsuario2.setLogin("lucasturato");
        testeUsuario2.setSenha("124323432");
        testeUsuario2.setStatus(1);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of cadastrarUsuario method, of class UsuarioDao.
     */
    @Test
    public void testCadastrarUsuario() {
        assertEquals("Test cadastrarFuncionario, fail",true,teste.cadastrarUsuario(testeUsuario));
    }

    /**
     * Test of consultarUsuario method, of class UsuarioDao.
     */
    @Test
    public void testConsultarUsuario() {
        teste.cadastrarUsuario(testeUsuario2);
        ArrayList<Usuario> testeLista = new ArrayList();
        testeLista.add(testeUsuario);
        testeLista.add(testeUsuario2);
        assertEquals("Test consultarFuncionario, fail",testeLista,teste.consultarUsuario());
    }

    /**
     * Test of alterarUsuario method, of class UsuarioDao.
     */
    @Test
    public void testAlterarUsuario() {
        testeUsuario2.setCodLogin(1);
        assertEquals("Test alterarFuncionario, fail",true,teste.alterarUsuario(testeUsuario2));
    }

    /**
     * Test of excluirUsuario method, of class UsuarioDao.
     */
    @Test
    public void testExcluirUsuario() {
        testeUsuario2.setCodLogin(1);
        assertEquals("Test excluirFuncionario, fail",true,teste.alterarUsuario(testeUsuario2));
    }
    
}
