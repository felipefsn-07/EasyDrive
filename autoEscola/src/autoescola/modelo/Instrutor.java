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

public class Instrutor extends Funcionario{
    
    private String categoria;
    private String numCarteira;


    public Instrutor(String nome, String rg, String cpf, String datanasc, String telefone, String celular, String endereco, String hora_entra, String hora_sai, String login, String senha, boolean status, String categoria, String numCarteira){
        super (nome, rg, cpf, datanasc, telefone, celular, endereco, hora_entra, hora_sai, login, senha, "Instrutor", status);
        this.categoria = categoria;
        this.numCarteira = numCarteira;
    
    }
    
    
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
     * @return the numCarteira
     */
    public String getNumCarteira() {
        return numCarteira;
    }

    /**
     * @param numCarteira the numCarteira to set
     */
    public void setNumCarteira(String numCarteira) {
        this.numCarteira = numCarteira;
    }
    
    
    
    
}
