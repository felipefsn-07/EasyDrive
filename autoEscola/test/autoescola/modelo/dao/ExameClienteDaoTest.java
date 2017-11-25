/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.modelo.bean.Cliente;
import autoescola.modelo.bean.Exame;
import autoescola.modelo.bean.ExameClientes;
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
public class ExameClienteDaoTest {
    ExameClienteDao teste;
    public ExameClienteDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        teste = new ExameClienteDao();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of cadastrarExameCliente method, of class ExameClienteDao.
     */
    @Test
    public void testCadastrarExameCliente() {
        Cliente testeCliente = new Cliente();
        Exame testeExame = new Exame();
        testeCliente.setCodCliente(1);
        testeCliente.setNome("José");
        testeCliente.setRg("30.231.492-4");
        testeExame.setCodigoExame(1);
        testeExame.setDataExame("05/01/2018");
        testeExame.setHorarioInicio("9:00");
        testeExame.setHorarioFim("10:00");
        ExameClientes testeExameClientes = new ExameClientes();
        testeExameClientes.setCliente(testeCliente);
        testeExameClientes.setExame(testeExame);
        assertEquals("Teste cadastrarExameCliente",true,teste.cadastrarExameCliente(testeExameClientes));
    }
    
}
