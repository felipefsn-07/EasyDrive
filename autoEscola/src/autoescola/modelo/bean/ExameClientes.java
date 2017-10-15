/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.bean;

/**
 *
 * @author felipe
 */
public class ExameClientes {
    private Cliente cliente;
    private Exame exame;

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the exame
     */
    public Exame getExame() {
        return exame;
    }

    /**
     * @param exame the exame to set
     */
    public void setExame(Exame exame) {
        this.exame = exame;
    }
}
