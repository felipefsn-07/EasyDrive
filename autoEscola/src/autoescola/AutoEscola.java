/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola;

import autoescola.modelo.bean.Cliente;
import autoescola.modelo.dao.ClienteDao;
import java.sql.Date;
import java.lang.Iterable;

/**
 *
 * @author felipe
 */
public class AutoEscola {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


                 // TODO code application logic here
                 
                 ClienteDao dao = new ClienteDao();
                 
                 
                 Cliente cli = new Cliente();
                
                 /*
                 cli.setNome("fernando");
                 cli.setTelefone("34973818");
                 cli.setCelular("981773296");
                 cli.setDatanasc(Date.valueOf("1999-01-25"));
                 cli.setRg("526686868");
                 cli.setCpf("43108186841");
                 cli.setNumLADV("123456789");
                 cli.setStatus(1);
                 cli.setCategoria("A");
                 
                 dao.cadastrarCliente(cli);
                 */
                 
                 
                 System.out.println(dao.consutarClienteExiste("43108186840"));
                 
                 /*
                 for(Cliente c: dao.consultarClientesLike("Lucca")){
                     System.out.println(c.getNome());
                 }
                */
    }
}
