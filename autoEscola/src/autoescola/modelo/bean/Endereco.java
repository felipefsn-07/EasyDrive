/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.bean;

/**
 *
 * @author Lucca
 */
public class Endereco {
    private int codEndereco;
    private String num;
    private String cidade;
    private String estado;
    private String logradouro;
    private String bairro;
    private String cep;
    
    public Endereco(String num, String cidade, String estado, String logradouro, String bairro, String cep){
        this.num = num;
        this.cidade = cidade;
        this.estado = estado;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cep = cep;
    }
    
    public int getCodEndereco(){
        return this.codEndereco;
    }
    
    public void setNum(String num){
        this.num = num;
    }
    
    public String getNum(){
        return this.num;
    }
    
    public void setCidade(String cidade){
        this.cidade = cidade;
    }
    
    public String getCidade(){
        return this.cidade;
    }
    
    public void setEstado(String estado){
        this.estado = estado;
    }
    
    public String getEstado(){
        return this.estado;
    }
    
    public void setLogradouro(String logradouro){
        this.logradouro = logradouro;
    }
    
    public String getLogradouro(){
        return this.logradouro;
    }
    
    public void setBairro(String bairro){
        this.bairro = bairro;
    }
    
    public String getBairro(){
        return this.bairro;
    }
    
    public void setCep(String cep){
        this.cep = cep;
    }
    
    public String getCep(){
        return this.cep;
    }
}

