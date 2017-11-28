/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.arquivo;

import autoescola.modelo.bean.Aula;
import autoescola.modelo.bean.AulasClientes;
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
public class AulasClientesArquivo extends Arquivo {

    private final String tabela = "tabelas/clienteaula.csv";

    /**
     *
     * @param codigoAula
     * @return the ArrayList of AulasClientes
     */
    public ArrayList<AulasClientes> consultarAulasClientes(int codigoAula) {
        File arquivoCSV = new File(tabela);
        ArrayList<AulasClientes> aulaClientes = new ArrayList();
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
                if (parseInt(valoresEntreVirgulas[0]) == codigoAula) {
                    AulasClientes aulaCliente = new AulasClientes();
                    Aula aula = new Aula();
                    
                    aula.setCodAulas(parseInt(valoresEntreVirgulas[0]));
                    aulaCliente.setAulas(aula);
                    
                    
                    ClienteArquivo c = new ClienteArquivo();
                    aulaCliente.setAluno(c.consultar(parseInt(valoresEntreVirgulas[1])));
                    
                    
                    boolean status = valoresEntreVirgulas[2].equals("true");
                    aulaCliente.setPresenca(status);
                    aulaClientes.add(aulaCliente);

                }
            }
            return aulaClientes;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    /**
     *
     * @param codCliente
     * @return the AulasClientes
     */
    public ArrayList<AulasClientes> consultarClientesAula(int codCliente) {
        File arquivoCSV = new File(tabela);
        ArrayList<AulasClientes> aulaClientes = new ArrayList();
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
                if (parseInt(valoresEntreVirgulas[1]) == codCliente) {
                    AulasClientes aulaCliente = new AulasClientes();
                    AulasArquivo aa = new AulasArquivo();

                    Aula aula = aa.consultar(parseInt(valoresEntreVirgulas[0]));
                    aulaCliente.setAulas(aula);
                    Cliente cliente = new Cliente();
                    cliente.setCodCliente(parseInt(valoresEntreVirgulas[1]));
                    boolean status = valoresEntreVirgulas[2].equals("true");
                    aulaCliente.setPresenca(status);
                    aulaCliente.setAluno(cliente);
                    aulaClientes.add(aulaCliente);

                }
            }
            return aulaClientes;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    /**
     *
     * @param aulaCliente
     * @return false or true
     */
    public boolean alterarAulasClientes(AulasClientes aulaCliente) {
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
                if (parseInt(valoresEntreVirgulas[0]) == aulaCliente.getAulas().getCodAulas() && parseInt(valoresEntreVirgulas[1]) == aulaCliente.getAluno().getCodCliente()) {
                    linhasDoArquivo = String.valueOf(aulaCliente.getAulas().getCodAulas()) + ",";
                    linhasDoArquivo += String.valueOf(aulaCliente.getAluno().getCodCliente());
                    linhasDoArquivo += String.valueOf(aulaCliente.isPresenca());

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
     * @param aulaCliente
     * @return false or true
     */
    public boolean cadastrarAulasClientes(AulasClientes aulaCliente) {

        int codAulasClientes = autoIncremento(tabela);
        try {
            // O parametro é que indica se deve sobrescrever ou continua no
            // arquivo.
            FileWriter fw = new FileWriter(tabela, true);
            BufferedWriter conexao = new BufferedWriter(fw);
            if (codAulasClientes != 0) {
                conexao.write(String.valueOf(aulaCliente.getAulas().getCodAulas()));
                conexao.write(',');
                conexao.write(String.valueOf(aulaCliente.getAluno().getCodCliente()));
                conexao.write(',');
                conexao.write(String.valueOf(aulaCliente.isPresenca()));
                conexao.newLine();
                conexao.close();

                return true;
            } else {
                //msg erro no incremento codAulasClientes == 0
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
     * @return the ArrayList of AulasClientes
     */
    public ArrayList<AulasClientes> consultarAulasClientesLike(String campo, String valor) {
        File arquivoCSV = new File(tabela);
        ArrayList<AulasClientes> aulaClientes = new ArrayList();
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
                    AulasClientes aulaCliente = new AulasClientes();
                    Aula aula = new Aula();
                    aula.setCodAulas(parseInt(valoresEntreVirgulas[0]));
                    aulaCliente.setAulas(aula);
                    Cliente cliente = new Cliente();
                    cliente.setCodCliente(parseInt(valoresEntreVirgulas[1]));
                    aulaCliente.setAluno(cliente);
                    boolean status = valoresEntreVirgulas[1].equals("true");
                    aulaCliente.setPresenca(status);
                    aulaClientes.add(aulaCliente);

                }
            }
            return aulaClientes;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    /**
     *
     * @param codigoAula
     * @return
     */
    public boolean apagarAula(int codigoAula) {
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
                if (parseInt(valoresEntreVirgulas[0]) == codigoAula) {
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

    public ArrayList<Cliente> trazerClientes(int codigoAula) {
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
                if (valoresEntreVirgulas != null & parseInt(valoresEntreVirgulas[0]) == codigoAula) {
                    ClienteArquivo c = new ClienteArquivo();
                    Cliente cliente = c.consultar(parseInt(valoresEntreVirgulas[1]));

                    clientes.add(cliente);

                }
            }
            return clientes;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    public boolean presenca(AulasClientes aulasClientes) {
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
                if (parseInt(valoresEntreVirgulas[0]) == aulasClientes.getAulas().getCodAulas() && parseInt(valoresEntreVirgulas[1]) == aulasClientes.getAluno().getCodCliente()) {
                    linhasDoArquivo = String.valueOf(aulasClientes.getAulas().getCodAulas()) + ",";
                    linhasDoArquivo += String.valueOf(aulasClientes.getAluno().getCodCliente()) + ",";
                    linhasDoArquivo += String.valueOf(aulasClientes.isPresenca());

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
}
