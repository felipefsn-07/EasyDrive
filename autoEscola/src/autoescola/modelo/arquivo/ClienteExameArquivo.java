/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.arquivo;

import autoescola.modelo.bean.Exame;
import autoescola.modelo.bean.ExameClientes;
import autoescola.modelo.bean.Cliente;
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
public class ClienteExameArquivo extends Arquivo {

    private final String tabela = "tabelas/clienteexame.csv";

    /**
     *
     * @return the ArrayList of ExameClientes
     */
    public ArrayList<ExameClientes> consultarExameClientes() {
        File arquivoCSV = new File(tabela);
        ArrayList<ExameClientes> exameClientes = new ArrayList();
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
                    ExameClientes exameCliente = new ExameClientes();
                    Exame exame = new Exame();
                    exame.setCodigoExame(parseInt(valoresEntreVirgulas[0]));
                    exameCliente.setExame(exame);
                    Cliente cliente = new Cliente();
                    cliente.setCodCliente(parseInt(valoresEntreVirgulas[1]));
                    exameCliente.setCliente(cliente);
                    exameClientes.add(exameCliente);

                }
            }
            return exameClientes;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    /**
     *
     * @param codCliente
     * @return ExameClientes
     */
    public ArrayList<ExameClientes> consultarExamePorClientes(int codCliente) {
        File arquivoCSV = new File(tabela);
        ArrayList<ExameClientes> exames = new ArrayList();
        try {

            //cria um scanner para ler o arquivo
            Scanner leitor = new Scanner(arquivoCSV);

            //variavel que armazenara as linhas do arquivo
            String linhasDoArquivo = null;

            //ignora a primeira linha do arquivo
            leitor.nextLine();
            //percorre todo o arquivo
            int aux = 0;
            while (leitor.hasNext()) {
                //recebe cada linha do arquivo
                linhasDoArquivo = leitor.nextLine();

                //separa os campos entre as virgulas de cada linha
                //imprime a coluna que quiser
                String[] valoresEntreVirgulas = linhasDoArquivo.split(",");
                if (parseInt(valoresEntreVirgulas[1]) == codCliente) {
                    ExameClientes exameCliente = new ExameClientes();
                    ExameArquivo ea = new ExameArquivo();
                    Exame exame = ea.consultar(parseInt(valoresEntreVirgulas[0]));
                    exameCliente.setExame(exame);
                    Cliente cliente = new Cliente();
                    cliente.setCodCliente(parseInt(valoresEntreVirgulas[1]));
                    exameCliente.setCliente(cliente);
                    exames.add(exameCliente);
                    aux++;

                }
            }
            if (aux > 0) {
                return exames;
            } else {
                return null;
            }
        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    /**
     *
     * @param codExame
     * @return ExameClientes
     */
    public ArrayList<ExameClientes> consultarClientesPorExame(int codExame) {
        File arquivoCSV = new File(tabela);
        ArrayList<ExameClientes> clientes = new ArrayList();
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
                if (parseInt(valoresEntreVirgulas[0]) == codExame) {
                    ExameClientes exameCliente = new ExameClientes();
                    Exame exame = new Exame();
                    exame.setCodigoExame(parseInt(valoresEntreVirgulas[0]));
                    exameCliente.setExame(exame);
                    ClienteArquivo cli = new ClienteArquivo();
                    Cliente cliente = cli.consultar(parseInt(valoresEntreVirgulas[1]));
                    exameCliente.setCliente(cliente);
                    clientes.add(exameCliente);

                }
            }
            return clientes;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    /**
     *
     * @param exameCliente
     * @return false or true
     */
    public boolean alterarExameClientes(ExameClientes exameCliente) {
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
                if (parseInt(valoresEntreVirgulas[0]) == exameCliente.getExame().getCodigoExame() && parseInt(valoresEntreVirgulas[1]) == exameCliente.getCliente().getCodCliente()) {
                    linhasDoArquivo = String.valueOf(exameCliente.getExame().getCodigoExame()) + ",";
                    linhasDoArquivo += String.valueOf(exameCliente.getCliente().getCodCliente());
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

    /**
     *
     * @param exameCliente
     * @return false or true
     */
    public boolean cadastrarExameClientes(ExameClientes exameCliente) {

        int codExameClientes = autoIncremento(tabela);
        try {
            // O parametro é que indica se deve sobrescrever ou continua no
            // arquivo.
            FileWriter fw = new FileWriter(tabela, true);
            BufferedWriter conexao = new BufferedWriter(fw);
            if (codExameClientes != 0) {
                conexao.write(String.valueOf(exameCliente.getExame().getCodigoExame()));
                conexao.write(',');
                conexao.write(String.valueOf(exameCliente.getCliente().getCodCliente()));
                conexao.newLine();
                conexao.close();

                return true;
            } else {
                //msg erro no incremento codExameClientes == 0
                return false;
            }

        } catch (IOException e) {
            //criar arquivo para salvar os erros 
            return false;
        }
    }

    /**
     *
     * @param campo
     * @param valor
     * @return ArrayList of ExameClientes
     */
    public ArrayList<ExameClientes> consultarExameClientesLike(String campo, String valor) {
        File arquivoCSV = new File(tabela);
        ArrayList<ExameClientes> exameClientes = new ArrayList();
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
                    ExameClientes exameCliente = new ExameClientes();
                    Exame exame = new Exame();
                    exame.setCodigoExame(parseInt(valoresEntreVirgulas[0]));
                    exameCliente.setExame(exame);
                    Cliente cliente = new Cliente();
                    cliente.setCodCliente(parseInt(valoresEntreVirgulas[1]));
                    exameCliente.setCliente(cliente);
                    exameClientes.add(exameCliente);

                }
            }
            return exameClientes;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    public boolean apagarAlunosExame(ExameClientes cliente) {
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
                if (parseInt(valoresEntreVirgulas[0]) == cliente.getExame().getCodigoExame() && parseInt(valoresEntreVirgulas[1]) == cliente.getCliente().getCodCliente()) {
                    linhasDoArquivo = "";

                }
                if (!linhasDoArquivo.equals("")) {
                    todo += linhasDoArquivo + "\n";
                }

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

    public boolean apagarExame(int codigoExame) {
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
                if (parseInt(valoresEntreVirgulas[0]) == codigoExame) {
                    linhasDoArquivo = "";

                }
                if (!linhasDoArquivo.equals("")) {
                    todo += linhasDoArquivo + "\n";
                }

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

    /**
     *
     * @param codExame
     * @return ExameClientes
     */
    public ArrayList<Cliente> trazerClientes(int codExame) {
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
                if (parseInt(valoresEntreVirgulas[0]) == codExame) {
                    ClienteArquivo c = new ClienteArquivo();
                    Cliente cliente = c.consultar(parseInt(valoresEntreVirgulas[1]) );
                  
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
