/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola;

import autoescola.modelo.arquivo.*;
import autoescola.modelo.bean.*;
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
        EnderecoArquivo enderecoArq = new EnderecoArquivo();
        Cliente cli = new Cliente();
        
        cli.setNome("Felipe Silva do Nascimento");
        cli.setTelefone("349738181");
        cli.setCelular("9817732961");
        cli.setDatanasc("1999-01-251");
        cli.setRg("5266868681");
        cli.setCpf("431081868411");
        cli.setNumLADV("1234567891");
        cli.setStatus(1);
        cli.setCategoria("A1");
        Endereco endereco = enderecoArq.consultarEndereco(1);
        endereco.setCodEndereco(1);
        cli.setEndereco(endereco);

        //arq.cadastrarCliente(cli);
        // System.out.println(arq.consultarCliente(1).getNome());
       /*ArrayList<Cliente> clientes = arq.consultarClientesLike("nome", "S");
        System.out.println(clientes.size());
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(clientes.get(i).getCodCliente());
            System.out.println(clientes.get(i).getNome());
        }
        */
        cli.setCodCliente(1);
        arq.alterarCliente(cli);
        
       ArrayList<Cliente> clientes = arq.consultarClientes();
        for (int i = 0; i < clientes.size(); i++) {
            System.out.print(clientes.get(i).getCodCliente()+" ");
            System.out.print(clientes.get(i).getTelefone()+" ");
            System.out.print(clientes.get(i).getCelular()+" ");
            System.out.print(clientes.get(i).getDatanasc()+" ");
            System.out.print(clientes.get(i).getCelular()+" ");
            System.out.print(clientes.get(i).getRg()+" ");
            System.out.print(clientes.get(i).getCpf()+" ");
            System.out.print(clientes.get(i).getNumLADV()+" ");
            System.out.print(clientes.get(i).getStatus()+" ");
            System.out.print(clientes.get(i).getEndereco().getCodEndereco()+" ");
            System.out.println(clientes.get(i).getNome());
        }
        
    }
}