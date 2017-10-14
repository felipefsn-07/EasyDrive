/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.bean;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author felipe
 */
public class Aula {
    private int codAulas;
    private Date dataAula;
    private Time horarioAula;
    private Instrutor instrutor;
    private Veiculo veiculo;
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
    public Date getDataAula() {
        return dataAula;
    }

    /**
     * @param dataAula the dataAula to set
     */
    public void setDataAula(LocalDate dataAula) {
        this.dataAula = Date.valueOf(dataAula);
    }

    /**
     * @return the horarioAula
     */
    public Time getHorarioAula() {
        return horarioAula;
    }

    /**
     * @param horarioAula the horarioAula to set
     */
    public void setHorarioAula(LocalTime horarioAula) {
        this.horarioAula = Time.valueOf​(horarioAula);
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
    
    public void setVeiculo(Veiculo veiculo){
        this.veiculo = veiculo;
    }
    
    public Veiculo getVeiculo(){
        return veiculo;
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