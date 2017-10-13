/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola;

import autoescola.modelo.arquivo.ClienteArquivo;
import autoescola.modelo.bean.Cliente;
import java.sql.Date;
import java.util.ArrayList;

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
        ClienteArquivo arq = new ClienteArquivo();

        Cliente cli = new Cliente();

        cli.setNome("Felipe Silva do Nascimento");
        cli.setTelefone("34973818");
        cli.setCelular("981773296");
        cli.setDatanasc(Date.valueOf("1999-01-25"));
        cli.setRg("526686868");
        cli.setCpf("43108186841");
        cli.setNumLADV("123456789");
        cli.setStatus(1);
        cli.setCategoria("A");
        cli.setCodEndereco(1);

        //arq.cadastrarCliente(cli);
        // System.out.println(arq.consultarCliente(1).getNome());
       /*ArrayList<Cliente> clientes = arq.consultarClientesLike("nome", "S");
        System.out.println(clientes.size());
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(clientes.get(i).getCodCliente());
            System.out.println(clientes.get(i).getNome());
        }
        */
        arq.alterarCliente(3, cli);
        
       ArrayList<Cliente> clientes = arq.consultarClientes();
        for (int i = 0; i < clientes.size(); i++) {
            System.out.print(clientes.get(i).getCodCliente());
            System.out.println(clientes.get(i).getNome());
        }
        
    }
}
