///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package autoescola.modelo.dao;
//
//import autoescola.modelo.bean.Cliente;
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author Turato
// */
//public class CliDaoTest {
//    Cliente testeCliente;
//    Cliente testeCliente2;
//    CliDao teste;
//    public CliDaoTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass(){
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//        testeCliente = new Cliente("Gabriel","59.203.193-6","192.371.238-84","16/09/1998","(19)3562-4885","(19)99682-8615","321431","AB");
//        testeCliente2 = new Cliente("Lucas","12.203.155-7","698.391.320-32","25/01/1996","(19)3567-4938","(19)99837-2910","321321","AB");
//        teste = new CliDao();
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of cadastrarCliente method, of class CliDao.
//     */
//    @Test
//    public void testCadastrarCliente() {
//        boolean sucesso;
//        sucesso=teste.cadastrarCliente(testeCliente);
//        assertEquals("Test cadastrarCliente, fail",1,sucesso);
//        testeCliente = new Cliente();
//        sucesso=teste.cadastrarCliente(testeCliente);
//        assertEquals("Test cadastrarCliente, fail",0,sucesso);
//    }
//
//    /**
//     * Test of consultarClientes method, of class CliDao.
//     */
//    @Test
//    public void testConsultarClientes() {
//        List<Cliente> testeLista = null;
//        boolean sucesso;
//        sucesso=teste.cadastrarCliente(testeCliente2);
//        testeLista.add(testeCliente);
//        testeLista.add(testeCliente2);
//        assertEquals("Test ConsultarClientes, fail",testeLista,teste.consultarClientes());
//    }
//
//    /**
//     * Test of consultarClientesLike method, of class CliDao.
//     */
//    @Test
//    public void testConsultarClientesLike() {
//        List<Cliente> testeLista = null;
//        String nome="Gab";
//        testeLista.add(testeCliente);
//        assertEquals("Test consultarClientesLike, fail",testeLista,teste.consultarClientesLike(nome));
//    }
//
//    /**
//     * Test of consutarClienteExiste method, of class CliDao.
//     */
//    @Test
//    public void testConsutarClienteExiste() {
//        String cpf="192.371.238-84";
//        boolean sucesso;
//        assertEquals("Test consultarClientesLike, fail",true,teste.consutarClienteExiste(cpf));
//    }
//
//    /**
//     * Test of alterarCliente method, of class CliDao.
//     */
//    @Test
//    public void testAlterarCliente() {
//        testeCliente2.setCodCliente(1);
//        assertEquals("Test alterarCliente, fail",true,teste.alterarCliente(testeCliente2));
//    }
//
//    /**
//     * Test of excluirCliente method, of class CliDao.
//     */
//    @Test
//    public void testExcluirCliente() {
//        assertEquals("Test excluirCliente, fail",1,teste.excluirCliente(testeCliente));
//        }
//}
