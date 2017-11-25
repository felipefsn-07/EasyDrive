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
     * Test of getHorarioInicio and setHorarioInicio method, of class Exame.
     */
    @Test
    public void testGetHorarioInicio() {
        teste.setHorarioInicio("7:00");
        assertEquals("Test getHorarioExame, succeed","7:00",teste.getHorarioInicio());
    }
    
    /**
     * Test of getHorarioFim and setHorarioFim method, of class Exame.
     */
    @Test
    public void testGetHorarioFim() {
        teste.setHorarioFim("8:40");
        assertEquals("Test getHorarioExame, succeed","8:40",teste.getHorarioFim());
    }

    /**
     * Test of getVeiculo and setVeiculo method, of class Exame.
     */
    @Test
    public void testGetVeiculo() {
        Veiculo testVeiculo = new Veiculo("DBV-0086","Gol","2015",15,"Ret",true);
        teste.setVeiculo(testVeiculo);
        assertEquals("Test getVeiculo, succeed",testVeiculo,teste.getVeiculo());
    }

    /**
     * Test of getInstrutor and setInstrutor method, of class Exame.
     */
    @Test
    public void testGetInstrutor() {
        Endereco testEndereco = new Endereco("199","Limeira","SP","Rua","Morro Azul","11212121");
        Funcionario testInstrutor = new Funcionario("Ramon","19.392.392-29","192.583.293-23","4/5/1986","(19)3542-3910","(19)98022-1923",testEndereco,"7:00","18:00","ramonlcs","193824","AB",true,"Instrutor","39218");
        teste.setInstrutor(testInstrutor);
        assertEquals("Test getInstrutor, succeed",testInstrutor,teste.getInstrutor());
    }

    /**
     * Test of getAlunos and setAlunos method, of class Exame.
     */
    @Test
    public void testGetAlunos() {
        ExameClientes test1 = new ExameClientes();
        Cliente testCliente = new Cliente("João da Silva","40.230.321-2","312.121.321-22","25/09/1998","(12)3568-9018","(12)99820-1213","321321","AB");
        Exame testExame = new Exame();
        testExame.setCodigoExame(1);
        testExame.setDataExame("12/08/2018");
        testExame.setHorarioFim("9:00");
        testExame.setHorarioInicio("8:00");
        test1.setExame(testExame);
        test1.setCliente(testCliente);
        ArrayList<ExameClientes> testExames = new ArrayList();
        testExames.add(test1);
        teste.setAlunos(testExames);
        assertEquals("Test getExames, succeed",testExames,teste.getAlunos());
    }
    
}
