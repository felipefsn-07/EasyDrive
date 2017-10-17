/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.arquivo;

/**
 *
 * @author felipe
 */
public interface Tabela {
    boolean desativar(int codigo);
    Object consultar(int codigo);
}
