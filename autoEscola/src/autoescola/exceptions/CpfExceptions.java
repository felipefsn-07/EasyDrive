/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.exceptions;

/**
 *
 * @author felipe
 */
public class CpfExceptions extends Exception {
    public CpfExceptions(String mensagem){
        //mensagem = "CPF invalido";
        super(mensagem);
    }
}
