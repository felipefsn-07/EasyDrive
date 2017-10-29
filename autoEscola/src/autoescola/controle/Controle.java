/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.controle;


/**
 *
 * @author felipe
 */
public abstract class Controle {
    
    protected boolean isDigit(String s) {
        char ch = s.charAt(0);
        return (ch >= 48 && ch <= 57);
    }

    protected String pegarNumero(String variavel) {
        String a = "";
        for (int i = 0; i < variavel.length(); i++) {
            if (variavel.charAt(i) >= 48 && variavel.charAt(i) <= 57) {
                a += variavel.charAt(i);
            }
        }
        return a;
    }
        public abstract boolean alterarStatus(boolean anterior, String idStr);

    
}
