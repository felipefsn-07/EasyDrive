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
public class Gerente extends Funcionario{
    public Gerente(String nome, String rg, String cpf, String datanasc, String telefone, String celular, String endereco, String hora_entra, String hora_sai, String login, String senha, String tipo, boolean status){
        super (nome, rg, cpf, datanasc, telefone, celular, endereco, hora_entra, hora_sai, login, senha, "Gerente", status);
    }
}
