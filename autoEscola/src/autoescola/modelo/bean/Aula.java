/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.bean;

import java.util.ArrayList;

/**
 *
 * @author felipe
 */
public class Aula {
    private int codAulas;
    private String dataAula;
    private String horarioAulaInicio;
    private String horarioAulaFim;
    private Funcionario instrutor;
    private Veiculo veiculo;
    private ArrayList<AulasClientes> aulas;
    
    
    public Aula (){
    
    }
    
    
    /**
     * @return the codAulas
     */
    public int getCodAulas() {
        return codAulas;
    }

    /**
     * @param codAulas the codAulas to set
     */
    public void setCodAulas(int codAulas) {
        this.codAulas = codAulas;
    }

    /**
     * @return the dataAula
     */
    public String getDataAula() {
        return dataAula;
    }

    /**
     * @param dataAula the dataAula to set
     */
    public void setDataAula(String dataAula) {
        this.dataAula = dataAula;
    }
    /**
     * @return the instrutor
     */
    public Funcionario getInstrutor() {
        return instrutor;
    }

    /**
     * @param instrutor the instrutor to set
     */
    public void setInstrutor(Funcionario instrutor) {
        this.instrutor = instrutor;
    }

    /**
     * @return the aulas
     */
    public ArrayList<AulasClientes> getAulas() {
        return aulas;
    }

    /**
     * @param aulas the aulas to set
     */
    public void setAulas(ArrayList<AulasClientes> aulas) {
        this.aulas = aulas;
    }

  
    /**
     * @return the veiculo
     */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * @param codVeiculo the veiculo to set
     */
    public void setVeiculo(Veiculo codVeiculo) {
        this.veiculo = codVeiculo;
    }

    /**
     * @return the horarioAulaInicio
     */
    public String getHorarioAulaInicio() {
        return horarioAulaInicio;
    }

    /**
     * @param horarioAulaInicio the horarioAulaInicio to set
     */
    public void setHorarioAulaInicio(String horarioAulaInicio) {
        this.horarioAulaInicio = horarioAulaInicio;
    }

    /**
     * @return the horarioAulaFim
     */
    public String getHorarioAulaFim() {
        return horarioAulaFim;
    }

    /**
     * @param horarioAulaFim the horarioAulaFim to set
     */
    public void setHorarioAulaFim(String horarioAulaFim) {
        this.horarioAulaFim = horarioAulaFim;
    }
}
