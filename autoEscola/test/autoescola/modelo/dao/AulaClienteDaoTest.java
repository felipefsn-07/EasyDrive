///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package autoescola.modelo.dao;
//
//import autoescola.modelo.bean.Aula;
//import autoescola.modelo.bean.AulasClientes;
//import autoescola.modelo.bean.Cliente;
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
//public class AulaClienteDaoTest {
//    AulaClienteDao teste;
//    public AulaClienteDaoTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//        teste = new AulaClienteDao();
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of cadastrarAulaCliente method, of class AulaClienteDao.
//     */
//    @Test
//    public void testCadastrarAulaCliente() {
//        Cliente clienteTest=new Cliente();
//        Aula aulaTest=new Aula();
//        AulasClientes aulasClientesTeste = new AulasClientes();
//        clienteTest.setCodCliente(5);
//        aulaTest.setCodAulas(2);
//        aulasClientesTeste.setAluno(clienteTest);
//        aulasClientesTeste.setAulas(aulaTest);
//        assertEquals("Test cadastrarAulaCliente, fail",true,teste.cadastrarAulaCliente(aulasClientesTeste));
//        
//    }
//    
//}
