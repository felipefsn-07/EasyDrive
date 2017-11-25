/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.bean;

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
public class ClienteTest {
    Cliente teste;
    
    public ClienteTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        teste = new Cliente();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCodCliente and setCodCliente method, of class Cliente.
     */
    @Test
    public void testGetCodCliente() {
        teste.setCodCliente(2);
        assertEquals("Teste Set e Get CodCliente, fail",2,teste.getCodCliente());
    }

    /**
     * Test of setEndereco and getEndereco method, of class Cliente.
     */
    @Test
    public void testSetEndereco() {
        Endereco endereco;
        endereco = new Endereco("199","Limeira","SP","Rua","Morro Azul","11212121");
        teste.setEndereco(endereco);
        assertEquals("Teste Set and Get endereco, succeed",endereco,teste.getEndereco());
    }

    /**
     * Test of getNome and setNome method, of class Cliente.
     */
    @Test
    public void testGetNome() {
        teste.setNome("Gabriel");
        assertEquals("Teste Get and Set nome","Gabriel",teste.getNome());
    }

    /**
     * Test of getRg and setRg method, of class Cliente.
     */
    @Test
    public void testGetRg() {
        teste.setRg("121321312");
        assertEquals("Teste Get and Set RG, succeed","121321312",teste.getRg());
    }

    /**
     * Test of getCpf and setCpf method, of class Cliente.
     */
    @Test
    public void testGetCpf() {
        teste.setCpf("20359304241");
        assertEquals("Teste Get and Set CPF","20359304241",teste.getCpf());
    }

    /**
     * Test of getDatanasc and setDatanasc method, of class Cliente.
     */
    @Test
    public void testGetDatanasc() {
        teste.setDatanasc("16/09/1998");
        assertEquals("Teste Get and Set DataNasc, succeed","16/09/1998",teste.getDatanasc());
    }

    /**
     * Test of getTelefone and setTelefone method, of class Cliente.
     */
    @Test
    public void testGetTelefone() {
        teste.setTelefone("(11)2222-3333");
        assertEquals("Teste Get and Set Telefone, succeed","(11)2222-3333",teste.getTelefone());
    }

    /**
     * Test of getCelular and setCelular method, of class Cliente.
     */
    @Test
    public void testGetCelular() {
        teste.setCelular("(11)22222-3333");
        assertEquals("Teste Set and Get Celular, succeed","(11)22222-3333",teste.getCelular());
    }

    /**
     * Test of getNumLADV and setNumLADV method, of class Cliente.
     */
    @Test
    public void testGetNumLADV() {
        teste.setNumLADV("313153243");
        assertEquals("Teste Get and Set NumLADV, succeed","313153243",teste.getNumLADV());
    }

    /**
     * Test of getCategoria and setCategoria method, of class Cliente.
     */
    @Test
    public void testGetCategoria() {
        teste.setCategoria("AB");
        assertEquals("Teste Get and Set Categoria, succeed","AB",teste.getCategoria());
    }

    /**
     * Test of getStatus and setStatus method, of class Cliente.
     */
    @Test
    public void testGetStatus() {
        teste.setStatus(1);
        assertEquals("Teste Get and Set Status, succeed",1,teste.getStatus());
    }

    /**
     * Test of getAulas and setAulas method, of class Cliente.
     */
    @Test
    public void testGetAulas() {
        ArrayList<AulasClientes> aula;
        aula = null;
        teste.setAulas(aula);
        assertTrue("Test Get and Set Aulas, succeed",teste.getAulas()==aula);
    }

    /**
     * Test of getExames and setExames method, of class Cliente.
     */
    @Test
    public void testGetExames() {
        ArrayList<ExameClientes> exames;
        exames=null;
        teste.setExames(exames);
        assertEquals("Teste Get and Set Exames, succeed",exames,teste.getExames());
    }
    
}
