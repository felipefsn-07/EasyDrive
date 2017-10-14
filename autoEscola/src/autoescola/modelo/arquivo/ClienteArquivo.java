/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.arquivo;

import autoescola.modelo.bean.Cliente;
import autoescola.modelo.bean.Endereco;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author felipe
 */
public class ClienteArquivo extends Arquivo {

    private final String tabela = "tabelas/cliente.csv";

    public ArrayList<Cliente> consultarClientes() {
        File arquivoCSV = new File(tabela);
        ArrayList<Cliente> clientes = new ArrayList();
        try {

            //cria um scanner para ler o arquivo
            Scanner leitor = new Scanner(arquivoCSV);

            //variavel que armazenara as linhas do arquivo
            String linhasDoArquivo = null;

            //ignora a primeira linha do arquivo
            leitor.nextLine();
            //percorre todo o arquivo
            while (leitor.hasNext()) {
                //recebe cada linha do arquivo
                linhasDoArquivo = leitor.nextLine();

                //separa os campos entre as virgulas de cada linha
                //imprime a coluna que quiser
                String[] valoresEntreVirgulas = linhasDoArquivo.split(",");
                if (valoresEntreVirgulas[0] != null) {
                    Cliente cliente = new Cliente();
                    cliente.setCodCliente(parseInt(valoresEntreVirgulas[0]));
                    cliente.setNome(valoresEntreVirgulas[1]);
                    cliente.setTelefone(valoresEntreVirgulas[2]);
                    cliente.setCelular(valoresEntreVirgulas[3]);
                    cliente.setDatanasc(valoresEntreVirgulas[4]);
                    cliente.setRg(valoresEntreVirgulas[5]);
                    cliente.setCpf(valoresEntreVirgulas[6]);
                    cliente.setNumLADV(valoresEntreVirgulas[7]);
                    cliente.setStatus(parseInt(valoresEntreVirgulas[8]));
                    cliente.setCategoria(valoresEntreVirgulas[9]);
                    Endereco endereco;
                    EnderecoArquivo endArq = new EnderecoArquivo();
                    endereco = endArq.consultarEndereco(parseInt(valoresEntreVirgulas[10]));
                    cliente.setEndereco(endereco);
                    clientes.add(cliente);

                }
            }
            return clientes;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    public Cliente consultarCliente(int codigoCliente) {
        File arquivoCSV = new File(tabela);
        Cliente cliente = new Cliente();
        try {

            //cria um scanner para ler o arquivo
            Scanner leitor = new Scanner(arquivoCSV);

            //variavel que armazenara as linhas do arquivo
            String linhasDoArquivo = null;

            //ignora a primeira linha do arquivo
            leitor.nextLine();
            //percorre todo o arquivo
            while (leitor.hasNext()) {
                //recebe cada linha do arquivo
                linhasDoArquivo = leitor.nextLine();

                //separa os campos entre as virgulas de cada linha
                //imprime a coluna que quiser
                String[] valoresEntreVirgulas = linhasDoArquivo.split(",");
                if (parseInt(valoresEntreVirgulas[0]) == codigoCliente) {
                    cliente.setCodCliente(parseInt(valoresEntreVirgulas[0]));
                    cliente.setNome(valoresEntreVirgulas[1]);
                    cliente.setTelefone(valoresEntreVirgulas[2]);
                    cliente.setCelular(valoresEntreVirgulas[3]);
                    cliente.setDatanasc(valoresEntreVirgulas[4]);
                    cliente.setRg(valoresEntreVirgulas[5]);
                    cliente.setCpf(valoresEntreVirgulas[6]);
                    cliente.setNumLADV(valoresEntreVirgulas[7]);
                    cliente.setStatus(parseInt(valoresEntreVirgulas[8]));
                    cliente.setCategoria(valoresEntreVirgulas[9]);
                    Endereco endereco;
                    EnderecoArquivo endArq = new EnderecoArquivo();
                    endereco = endArq.consultarEndereco(parseInt(valoresEntreVirgulas[10]));
                    cliente.setEndereco(endereco);
                }
            }
            return cliente;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    public boolean desativarCliente(int codigoCliente) {
        File arquivoCSV = new File(tabela);
        try {

            //cria um scanner para ler o arquivo
            Scanner leitor = new Scanner(arquivoCSV);

            //variavel que armazenara as linhas do arquivo
            String linhasDoArquivo = null;
            String todo = "";
            //ignora a primeira linha do arquivo
            todo += leitor.nextLine() + "\n";
            //percorre todo o arquivo
            while (leitor.hasNext()) {
                //recebe cada linha do arquivo
                linhasDoArquivo = leitor.nextLine();

                //separa os campos entre as virgulas de cada linha
                //imprime a coluna que quiser
                String[] valoresEntreVirgulas = linhasDoArquivo.split(",");
                if (parseInt(valoresEntreVirgulas[0]) == codigoCliente) {
                    linhasDoArquivo = valoresEntreVirgulas[0] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[1] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[2] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[3] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[4] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[5] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[6] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[7] + ",";
                    linhasDoArquivo += "0,";
                    linhasDoArquivo += valoresEntreVirgulas[9] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[10];

                }
                todo += linhasDoArquivo + "\n";

            }
            try {
                FileWriter fw = new FileWriter(tabela);
                BufferedWriter conexao = new BufferedWriter(fw);
                conexao.write(todo);
                conexao.close();
                return true;
            } catch (IOException e) {
                return false;
            }
            //return true;
        } catch (FileNotFoundException e) {
            //log de erro
            return false;

        }
    }

    public boolean alterarCliente(Cliente cliente) {
        File arquivoCSV = new File(tabela);
        try {

            //cria um scanner para ler o arquivo
            Scanner leitor = new Scanner(arquivoCSV);

            //variavel que armazenara as linhas do arquivo
            String linhasDoArquivo = null;
            String todo = "";
            //ignora a primeira linha do arquivo
            todo += leitor.nextLine() + "\n";
            //percorre todo o arquivo
            while (leitor.hasNext()) {
                //recebe cada linha do arquivo
                linhasDoArquivo = leitor.nextLine();

                //separa os campos entre as virgulas de cada linha
                //imprime a coluna que quiser
                String[] valoresEntreVirgulas = linhasDoArquivo.split(",");
                if (parseInt(valoresEntreVirgulas[0]) == cliente.getCodCliente()) {
                    linhasDoArquivo = String.valueOf(cliente.getCodCliente()) + ",";
                    linhasDoArquivo += cliente.getNome() + ",";
                    linhasDoArquivo += cliente.getTelefone() + ",";
                    linhasDoArquivo += cliente.getCelular() + ",";
                    linhasDoArquivo += cliente.getDatanasc() + ",";
                    linhasDoArquivo += cliente.getRg() + ",";
                    linhasDoArquivo += cliente.getCpf() + ",";
                    linhasDoArquivo += cliente.getNumLADV() + ",";
                    linhasDoArquivo += String.valueOf(cliente.getStatus()) + ",";
                    linhasDoArquivo += cliente.getCategoria() + ",";
                    linhasDoArquivo += String.valueOf(cliente.getEndereco().getCodEndereco());

                }
                todo += linhasDoArquivo + "\n";

            }
            try {
                FileWriter fw = new FileWriter(tabela);
                BufferedWriter conexao = new BufferedWriter(fw);
                conexao.write(todo);
                conexao.close();
                return true;
            } catch (IOException e) {
                return false;
            }
            //return true;
        } catch (FileNotFoundException e) {
            //log de erro
            return false;

        }
    }

    public boolean cadastrarCliente(Cliente cliente) {

        int idCliente = autoIncremento(tabela);
        try {
            // O parametro é que indica se deve sobrescrever ou continua no
            // arquivo.
            FileWriter fw = new FileWriter(tabela, true);
            BufferedWriter conexao = new BufferedWriter(fw);
            if (idCliente != 0) {
                conexao.write(String.valueOf(idCliente));
                conexao.write(',');
                conexao.write(cliente.getNome());
                conexao.write(',');
                conexao.write(cliente.getTelefone());
                conexao.write(',');
                conexao.write(cliente.getCelular());
                conexao.write(',');
                conexao.write(cliente.getDatanasc());
                conexao.write(',');
                conexao.write(cliente.getRg());
                conexao.write(',');
                conexao.write(cliente.getCpf());
                conexao.write(',');
                conexao.write(cliente.getNumLADV());
                conexao.write(',');
                conexao.write(String.valueOf(cliente.getStatus()));
                conexao.write(',');
                conexao.write(cliente.getCategoria());
                conexao.write(',');
                conexao.write(String.valueOf(cliente.getEndereco().getCodEndereco()));
                conexao.newLine();
                conexao.close();

                return true;
            } else {
                return false;
            }

        } catch (IOException e) {
            //criar arquivo para salvar os erros 
            return false;
        }
    }

    public ArrayList<Cliente> consultarClientesLike(String campo, String valor) {
        File arquivoCSV = new File(tabela);
        ArrayList<Cliente> clientes = new ArrayList();
        try {
            //cria um scanner para ler o arquivo
            Scanner leitor = new Scanner(arquivoCSV);

            //variavel que armazenara as linhas do arquivo
            String linhasDoArquivo = null;

            //ignora a primeira linha do arquivo
            String[] valoresEntreVirgulasCampos = leitor.nextLine().split(",");
            int numCamp;
            for (numCamp = 0; numCamp <= 10; numCamp++) {
                if (valoresEntreVirgulasCampos[numCamp].equals(campo)) {
                    break;
                }
            }

            //leitor.nextLine();
            //percorre todo o arquivo
            while (leitor.hasNext()) {
                //recebe cada linha do arquivo
                linhasDoArquivo = leitor.nextLine();

                //separa os campos entre as virgulas de cada linha
                //imprime a coluna que quiser
                String[] valoresEntreVirgulas = linhasDoArquivo.split(",");
                if (valoresEntreVirgulas[0] != null && valoresEntreVirgulas[numCamp].contains(valor)) {
                    Cliente cliente = new Cliente();
                    cliente.setCodCliente(parseInt(valoresEntreVirgulas[0]));
                    cliente.setNome(valoresEntreVirgulas[1]);
                    cliente.setTelefone(valoresEntreVirgulas[2]);
                    cliente.setCelular(valoresEntreVirgulas[3]);
                    cliente.setDatanasc(valoresEntreVirgulas[4]);
                    cliente.setRg(valoresEntreVirgulas[5]);
                    cliente.setCpf(valoresEntreVirgulas[6]);
                    cliente.setNumLADV(valoresEntreVirgulas[7]);
                    cliente.setStatus(parseInt(valoresEntreVirgulas[8]));
                    cliente.setCategoria(valoresEntreVirgulas[9]);
                    Endereco endereco;
                    EnderecoArquivo endArq = new EnderecoArquivo();
                    endereco = endArq.consultarEndereco(parseInt(valoresEntreVirgulas[10]));
                    cliente.setEndereco(endereco);
                    clientes.add(cliente);

                }
            }
            return clientes;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }
}
