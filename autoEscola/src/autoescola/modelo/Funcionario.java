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
public abstract class  Funcionario {
  
    private int codigoFuncionario;
    private String nome;
    private String rg;
    private String cpf;
    private String datanasc;
    private String telefone;
    private String celular;
    private String endereco;
    private String hora_entra;
    private String hora_sai;
    private String login;
    private String senha;
    private String tipo;
    private boolean status;

    public Funcionario(){
    }
    
    public Funcionario (String nome, String rg, String cpf, String datanasc, String telefone, String celular, String endereco, String hora_entra, String hora_sai, String login, String senha, String tipo, boolean status){
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.datanasc = datanasc;
        this.telefone = telefone;
        this.celular = celular;
        this.endereco = endereco;
        this.hora_entra = hora_entra;
        this.hora_sai = hora_sai;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
        this.status = status;
       
    }
    
    /**
     * @return the codigoFuncionario
     */
    public int getCodigoFuncionario() {
        return codigoFuncionario;
    }

    /**
     * @param codigoFuncionario the codigoFuncionario to set
     */
    protected void setCodigoFuncionario(int codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
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
     * @return the hora_entra
     */
    public String getHora_entra() {
        return hora_entra;
    }

    /**
     * @param hora_entra the hora_entra to set
     */
    public void setHora_entra(String hora_entra) {
        this.hora_entra = hora_entra;
    }

    /**
     * @return the hora_sai
     */
    public String getHora_sai() {
        return hora_sai;
    }

    /**
     * @param hora_sai the hora_sai to set
     */
    public void setHora_sai(String hora_sai) {
        this.hora_sai = hora_sai;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
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
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
