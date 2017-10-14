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
    private String horarioAula;
    private Instrutor instrutor;
    private Veiculo codVeiculo;
    private String statusAula;//Ativa, cancelada, realizada, adiada, desativada
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
     * @return the horarioAula
     */
    public String getHorarioAula() {
        return horarioAula;
    }

    /**
     * @param horarioAula the horarioAula to set
     */
    public void setHorarioAula(String horarioAula) {
        this.horarioAula = horarioAula;
    }

    /**
     * @return the instrutor
     */
    public Instrutor getInstrutor() {
        return instrutor;
    }

    /**
     * @param instrutor the instrutor to set
     */
    public void setInstrutor(Instrutor instrutor) {
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
     * @return the statusAula
     */
    public String getStatusAula() {
        return statusAula;
    }

    /**
     * @param statusAula the statusAula to set
     */
    public void setStatusAula(String statusAula) {
        this.statusAula = statusAula;
    }

    /**
     * @return the codVeiculo
     */
    public Veiculo getCodVeiculo() {
        return codVeiculo;
    }

    /**
     * @param codVeiculo the codVeiculo to set
     */
    public void setCodVeiculo(Veiculo codVeiculo) {
        this.codVeiculo = codVeiculo;
    }
}
