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
public class Cliente {
    private int codCliente;
    private String nome;
    private String rg;
    private String cpf;
    private String datanasc;
    private String telefone;
    private String celular;
    private String endereco;
    private String numLADV;
    private String categoria;
    private String status;
    private ArrayList<AulasClientes> aulas;
    
    public Cliente (){
    
    }
    public Cliente (String nome, String rg, String cpf, String datanasc, String telefone, String celular, String endereco, String numLADV, String categoria, String status){
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.datanasc = datanasc;
        this.telefone = telefone;
        this.celular = celular;
        this.endereco = endereco;
        this.numLADV = numLADV;
        this.categoria = categoria;
        this.status = status;
    }

    /**
     * @return the codCliente
     */
    public int getCodCliente() {
        return codCliente;
    }

    /**
     * @param codCliente the codCliente to set
     */
    protected void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the rg
     */
    public String getRg() {
        return rg;
    }

    /**
     * @param rg the rg to set
     */
    public void setRg(String rg) {
        this.rg = rg;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the datanasc
     */
    public String getDatanasc() {
        return datanasc;
    }

    /**
     * @param datanasc the datanasc to set
     */
    public void setDatanasc(String datanasc) {
        this.datanasc = datanasc;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the numLADV
     */
    public String getNumLADV() {
        return numLADV;
    }

    /**
     * @param numLADV the numLADV to set
     */
    public void setNumLADV(String numLADV) {
        this.numLADV = numLADV;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
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

        
        
    
}
