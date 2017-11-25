/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.bean;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Turato
 */
public class FuncionarioTest {
    
    Funcionario teste;
    
    @Before
    public void setUp() {
        teste = new Funcionario();
    }
    
    public FuncionarioTest() {
    }

    /**
     * Test of getCodigoFuncionario and setCodigoFuncionario method, of class Funcionario.
     */
    @Test
    public void testGetCodigoFuncionario() {
        teste.setCodigoFuncionario(10);
        assertEquals("Test getCodigoFuncionario, fail",10,teste.getCodigoFuncionario());
    }

    /**
     * Test of getNome and setNome method, of class Funcionario.
     */
    @Test
    public void testGetNome() {
        teste.setNome("Luis");
        assertEquals("Test getNome, fail","Luis",teste.getNome());
    }

    /**
     * Test of getRg and setRg method, of class Funcionario.
     */
    @Test
    public void testGetRg() {
        teste.setRg("23.321.483-2");
        assertEquals("Test getRg, fail","23.321.483-2",teste.getRg());
    }
    
    /**
     * Test of getCpf and setCpf method, of class Funcionario.
     */
    @Test
    public void testGetCpf() {
        teste.setCpf("293.592.391-10");
        assertEquals("Test getCpf, fail","293.592.391-10",teste.getCpf());
    }

    /**
     * Test of getDatanasc and setDatanasc method, of class Funcionario.
     */
    @Test
    public void testGetDatanasc() {
        teste.setDatanasc("19/09/1993");
        assertEquals("Test getDatanasc, fail","19/09/1993",teste.getDatanasc());
    }
    
    /**
     * Test of getTelefone and setTelefone method, of class Funcionario.
     */
    @Test
    public void testGetTelefone() {
        teste.setTelefone("(19)3562-4888");
        assertEquals("Test getTelefone, fail","(19)3562-4888",teste.getTelefone());
    }

    /**
     * Test of getCelular and setCelular method, of class Funcionario.
     */
    @Test
    public void testGetCelular() {
        teste.setCelular("(19)99812-3918");
        assertEquals("Test getCelular, fail","(19)99812-3918",teste.getCelular());
    }

    /**
     * Test of getEndereco and setEndereco method, of class Funcionario.
     */
    @Test
    public void testGetEndereco() {
        Endereco testEndereco = new Endereco("199","Limeira","SP","Rua","Morro Azul","11212121");
        teste.setEndereco(testEndereco);
        assertEquals("Test getEndereco, fail",testEndereco,teste.getEndereco());
    }

    /**
     * Test of getHora_entra and setHora_entra method, of class Funcionario.
     */
    @Test
    public void testGetHora_entra() {
        teste.setHora_entra("7:00");
        assertEquals("Test getHora_entra, fail","7:00",teste.getHora_entra());
    }

    /**
     * Test of getHora_sai method, of class Funcionario.
     */
    @Test
    public void testGetHora_sai() {
        teste.setHora_sai("18:00");
        assertEquals("Test getHora_sai, fail","18:00",teste.getHora_sai());
    }

    /**
     * Test of getTipo and setTipo method, of class Funcionario.
     */
    @Test
    public void testGetTipo() {
        teste.setTipo("Gerente");
        assertEquals("Test getTipo, fail","Gerente",teste.getTipo());
    }

    /**
     * Test of getStatus and setStatus method, of class Funcionario.
     */
    @Test
    public void testGetStatus() {
        teste.setStatus(1);
        assertEquals("Test getStatus, fail",1,teste.getStatus());
    }

    /**
     * Test of getUsuario and setUsuario method, of class Funcionario.
     */
    @Test
    public void testGetUsuario() {
        Usuario testUsuario = new Usuario();
        testUsuario.setLogin("loginteste");
        testUsuario.setSenha("12344");
        teste.setUsuario(testUsuario);
        assertEquals("Test getUsuario, fail",testUsuario,teste.getUsuario());
    }
    
}
