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
public class Veiculo {
        
    private int codVeiculo;
    private String modelo;
    private String ano;
    private String capacidade;
    private String tipo; 
    private boolean status;
    
    public Veiculo(){
    
    }
    
    public Veiculo(String modelo, String ano, String capacidade, String tipo, boolean status){
        this.modelo = modelo;
        this.ano = ano;
        this.capacidade = capacidade;
        this.tipo = tipo;
        this.status = status;
    }

    /**
     * @return the codVeiculo
     */
    public int getCodVeiculo() {
        return codVeiculo;
    }

    /**
     * @param codVeiculo the codVeiculo to set
     */
    protected void setCodVeiculo(int codVeiculo) {
        this.codVeiculo = codVeiculo;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the ano
     */
    public String getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(String ano) {
        this.ano = ano;
    }

    /**
     * @return the capacidade
     */
    public String getCapacidade() {
        return capacidade;
    }

    /**
     * @param capacidade the capacidade to set
     */
    public void setCapacidade(String capacidade) {
        this.capacidade = capacidade;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the status
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
