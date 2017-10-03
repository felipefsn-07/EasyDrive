/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.modelo.bean.Cliente;
import java.util.List;

/**
 *
 * @author felipe
 */

public class ClienteDao {
    //private Cliente cli;
    public boolean cadastrarCliente(Cliente cli){
       
        return false;
    }
    public List<Cliente> consultarClientes(){
        List<Cliente> cli = null;
        return cli;
    }
    public Cliente consultarCliente(){
         Cliente cli = null;
        return cli;
    }
    
    /**
     *
     * @param pesquisar
     * @return
     */
    public List<Cliente> consultarClientesLike(String pesquisar){
        List<Cliente> cli = null;
        return cli;
    }
    public boolean consutarClienteExiste (){
        return false;
    }
    
    public boolean alterarCliente(Cliente cli){
       
        return false;
    }
      public boolean excluirCliente(Cliente cli){
       
        return false;
    }
    
}
