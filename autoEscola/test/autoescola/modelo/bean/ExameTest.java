/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.bean;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Turato
 */
public class ExameTest {
    Exame teste;
    
    @Before
    public void setUp() {
        teste = new Exame();
    }
    public ExameTest() {
    }

    /**
     * Test of getCodigoExame and setCodigoExame method, of class Exame.
     */
    @Test
    public void testGetCodigoExame() {
        teste.setCodigoExame(52);
        assertEquals("Test getCodigoCliente, succeed",52,teste.getCodigoExame());
    }
    
    /**
     * Test of getDataExame and setDataExame method, of class Exame.
     */
    @Test
    public void testGetDataExame() {
        teste.setDataExame("13/01/2017");
        assertEquals("Test getDataExame, succeed","13/01/2017",teste.getDataExame());
    }

    /**
     * Test of getHorarioExame and setHorarioExame method, of class Exame.
     */
    @Test
    public void testGetHorarioExame() {
        teste.setHorarioExame("7:00");
        assertEquals("Test getHorarioExame, succeed","7:00",teste.getHorarioExame());
    }

    /**
     * Test of getVeiculo and setVeiculo method, of class Exame.
     */
    @Test
    public void testGetVeiculo() {
        Veiculo testVeiculo = new Veiculo("DBV-0086","Gol","2015",15,"Ret",1);
        teste.setVeiculo(testVeiculo);
        assertEquals("Test getVeiculo, succeed",testVeiculo,teste.getVeiculo());
    }

    /**
     * Test of getInstrutor and setInstrutor method, of class Exame.
     */
    @Test
    public void testGetInstrutor() {
        Endereco testEndereco = new Endereco("199","Limeira","SP","Rua","Morro Azul","11212121");
        Instrutor testInstrutor = new Instrutor("Ramon","19.392.392-29","192.583.293-23","4/5/1986","(19)3542-3910","(19)98022-1923",testEndereco,"7:00","18:00","ramonlcs","193824",1,"Instrutor","39218");
        assertEquals("Test getInstrutor, succeed",testInstrutor,teste.getInstrutor());
    }

    /**
     * Test of getExames and setExames method, of class Exame.
     */
    @Test
    public void testGetExames() {
        ExameClientes test1 = new ExameClientes();
        ExameClientes test2 = new ExameClientes();
        ArrayList<ExameClientes> testExames = null;
        testExames.add(test1);
        testExames.add(test2);
        teste.setExames(testExames);
        assertEquals("Test getExames, succeed",testExames,teste.getExames());
    }

    /**
     * Test of getStatus and setStatus method, of class Exame.
     */
    @Test
    public void testGetStatus() {
        teste.setStatus(1);
        assertEquals("Test getStatus, succeed",1,teste.getStatus());
    }
    
}
