/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo;

/**
 *
 * @author felipe
 */
public class Exame {
    private int codigoExame;
    private String dataExame;
    private String horarioExame;
    private Veiculo veiculo;
    private Instrutor instrutor;

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
     * @return the horarioExame
     */
    public String getHorarioExame() {
        return horarioExame;
    }

    /**
     * @param horarioExame the horarioExame to set
     */
    public void setHorarioExame(String horarioExame) {
        this.horarioExame = horarioExame;
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
    public Instrutor getInstrutor() {
        return instrutor;
    }

    /**
     * @param instrutor the instrutor to set
     */
    public void setInstrutor(Instrutor instrutor) {
        this.instrutor = instrutor;
    }
    
    
    
    
    
}
