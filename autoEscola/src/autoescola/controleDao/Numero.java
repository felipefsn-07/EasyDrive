/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.controleDao;

/**
 *
 * @author felipe
 */
public interface Numero {
    /**
     * Verificar se a string é um digito
     * @param s
     * @return 
     */
    boolean isDigit(String s);
    
    /**
     * Pegar numero
     * @param variavel
     * @return 
     */
    String pegarNumero(String variavel);
    
}
