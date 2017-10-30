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
public class Exame {

    private int codigoExame;
    private String dataExame;
    private String horarioInicio;
    private String horarioFim;

    private Veiculo veiculo;
    private Funcionario instrutor;
    private int status;
    private ArrayList<ExameClientes> exames;

    /**
     * @return the codigoExame
     */
    public int getCodigoExame() {
        return codigoExame;
    }

    /**
     * @param codigoExame the codigoExame to set
     */
    public void setCodigoExame(int codigoExame) {
        this.codigoExame = codigoExame;
    }

    /**
     * @return the dataExame
     */
    public String getDataExame() {
        return dataExame;
    }

    /**
     * @param dataExame the dataExame to set
     */
    public void setDataExame(String dataExame) {
        this.dataExame = dataExame;
    }

    /**
     * @return the veiculo
     */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * @param veiculo the veiculo to set
     */
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
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
     * @return the exames
     */
    public ArrayList<ExameClientes> getExames() {
        return exames;
    }

    /**
     * @param exames the exames to set
     */
    public void setExames(ArrayList<ExameClientes> exames) {
        this.exames = exames;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the horarioInicio
     */
    public String getHorarioInicio() {
        return horarioInicio;
    }

    /**
     * @param horarioInicio the horarioInicio to set
     */
    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    /**
     * @return the horarioFim
     */
    public String getHorarioFim() {
        return horarioFim;
    }

    /**
     * @param horarioFim the horarioFim to set
     */
    public void setHorarioFim(String horarioFim) {
        this.horarioFim = horarioFim;
    }

}
