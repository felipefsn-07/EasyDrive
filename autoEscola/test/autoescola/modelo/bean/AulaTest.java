///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package autoescola.modelo.bean;
//
//import java.util.ArrayList;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import java.util.ArrayList;
//
//
///**
// *
// * @author Turato
// */
//public class AulaTest {
//    Aula aula1;
//    
//    public AulaTest() {
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
//        aula1 = new Aula();
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of getCodAulas and setCodAulas method, of class Aula.
//     */
//    @Test
//    public void testGetCodAulas() {
//        aula1.setCodAulas(5);
//        assertEquals("Test GetCodAulas, fail",5,aula1.getCodAulas());
//    }
//
//    /**
//     * Test of setCodAulas and getCodAulas method, of class Aula.
//     */
//    @Test
//    public void testSetCodAulas() {
//        aula1.setCodAulas(20);
//        assertTrue("Test SetCodAulas, fail",aula1.getCodAulas()==20);
//    }
//
//    /**
//     * Test of getDataAula and setDataAula method, of class Aula.
//     */
//    @Test
//    public void testGetDataAula() {
//        aula1.setDataAula("20/10/2015");
//        assertEquals("Test GetDataAula, fail","20/10/2015",aula1.getDataAula());
//    }
//
//    /**
//     * Test of setDataAula and getDataAula method, of class Aula.
//     */
//    @Test
//    public void testSetDataAula() {
//        aula1.setDataAula("15/09/2015");
//        assertTrue("Test SetDataAula 1, fail",aula1.getDataAula().equals("15/09/2015"));
//    }
//
//    /**
//     * Test of getHorarioAula and setHorarioAula method, of class Aula.
//     */
//    @Test
//    public void testGetHorarioAulaInicio() {
//        aula1.setHorarioAulaInicio("00:00");
//        assertEquals("Test GetHorarioAula, fail","00:00",aula1.getHorarioAulaInicio());
//    }
//    
//    /**
//     * Test of getHorarioAulaFim and setHorarioAulaFim method, of class Aula.
//     */
//    @Test
//    public void testGetHorarioAulaFim() {
//        aula1.setHorarioAulaFim("07:40");
//        assertEquals("Test GetHorarioAula, fail","07:40",aula1.getHorarioAulaFim());
//    }
//
//    /**
//     * Test of getInstrutor and setInstrutor method, of class Aula.
//     */
//    @Test
//    public void testGetInstrutor() {
//        Funcionario instrutor;
//        instrutor= new Funcionario();
//        instrutor.setNome("Gabriel");
//        instrutor.setCelular("(19)11111-2222");
//        aula1.setInstrutor(instrutor);
//        assertEquals("Test Get and Set Instrutor, fail",instrutor,aula1.getInstrutor());
//    }
//    
//    /**
//     * Test of getAulas and setAulas method, of class Aula.
//     */
//    @Test
//    public void testGetAulas() {
//        ArrayList<AulasClientes> teste;
//        teste = null;
//        Funcionario instrutor;
//        instrutor= new Funcionario();
//        instrutor.setNome("Gabriel");
//        instrutor.setCelular("(19)11111-2222");
//        Veiculo veiculo;
//        veiculo = new Veiculo("EFL-9291","Fiesta","2007",25,"RET",true);
//        aula1.setCodAulas(1);
//        aula1.setDataAula("25/01/2017");
//        aula1.setHorarioAulaFim("7:40");
//        aula1.setHorarioAulaInicio("6:00");
//        aula1.setInstrutor(instrutor);
//        aula1.setVeiculo(veiculo);
//        aula1.setAulas(teste);
//        assertTrue("Test Get and Set Aulas, fail",aula1.getAulas()==teste);
//    }
//
//    /**
//     * Test of getCodVeiculo and setCodVeiculo method, of class Aula.
//     */
//    @Test
//    public void testsetVeiculo() {
//        Veiculo teste;
//        teste = new Veiculo("EFL-9291","Fiesta","2007",25,"RET",true);
//        aula1.setVeiculo(teste);
//        assertEquals("Test Set and Get CodVeiculo, fail",teste,aula1.getVeiculo());
//    }
//    
//}
