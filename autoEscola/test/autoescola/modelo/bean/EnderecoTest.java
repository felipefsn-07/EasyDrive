/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.bean;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Turato
 */
public class EnderecoTest {
    Endereco teste = new Endereco();
    
    public EnderecoTest() {
    }

    /**
     * Test of setCodEndereco and getCodEndereco method, of class Endereco.
     */
    @Test
    public void testSetCodEndereco() {
        teste.setCodEndereco(1);
        assertEquals("Teste Get and Set Endereco, succeed",1,teste.getCodEndereco());
    }

    /**
     * Test of setNum and getNum method, of class Endereco.
     */
    @Test
    public void testSetNum() {
        teste.setNum("1999");
        assertEquals("Teste Get and Set Num, succeed","1999",teste.getNum());
    }

    /**
     * Test of setCidade and getCidade method, of class Endereco.
     */
    @Test
    public void testSetCidade() {
        teste.setCidade("Limeira");
        assertEquals("Teste Get and Set Cudade, succeed","Limeira",teste.getCidade());
    }

    /**
     * Test of setEstado and getEstado method, of class Endereco.
     */
    @Test
    public void testSetEstado() {
        teste.setEstado("SP");
        assertEquals("Teste Get and Set Estado, succeed","SP",teste.getEstado());
    }

    /**
     * Test of setLogradouro and getLogradouro method, of class Endereco.
     */
    @Test
    public void testSetLogradouro() {
        teste.setLogradouro("Rua");
        assertEquals("Teste Get and Set Logradouro, succeed","Rua",teste.getLogradouro());
    }

    /**
     * Test of setBairro and getBairro method, of class Endereco.
     */
    @Test
    public void testSetBairro() {
        teste.setBairro("Morro Azul");
        assertEquals("Teste Get and Set Bairro, succeed","Morro Azul",teste.getBairro());
    }

    /**
     * Test of setCep and getCep method, of class Endereco.
     */
    @Test
    public void testSetCep() {
        teste.setCep("31231-312");
        assertEquals("Teste Set and Get Cep, succeed","31231-312",teste.getCep());    
    }
    /**
     * Test of getStatus and setStatus method, of class Endereco.
     */
    @Test
    public void testGetStatus() {
        teste.setStatus(2);
        assertEquals("Teste Set and Get Status, succeed",2,teste.getStatus());
    }
    
}
