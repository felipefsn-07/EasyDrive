/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.arquivo;

import autoescola.modelo.bean.Aulas;
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

    public ArrayList<AulasClientes> consultarAulasClientes() {
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
                if (valoresEntreVirgulas[0] != null) {
                    AulasClientes aulaCliente = new AulasClientes();
                    Aulas aula = new Aulas();
                    aula.setCodAulas(parseInt(valoresEntreVirgulas[0]));
                    aulaCliente.setAulas(aula);
                    Cliente cliente = new Cliente();
                    cliente.setCodCliente(parseInt(valoresEntreVirgulas[1]));
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

    public AulasClientes consultarAulasPorClientes(int codCliente) {
        File arquivoCSV = new File(tabela);
        AulasClientes aulacliente = new AulasClientes();
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
                    Aulas aula = new Aulas();
                    aula.setCodAulas(parseInt(valoresEntreVirgulas[0]));
                    aulaCliente.setAulas(aula);
                    Cliente cliente = new Cliente();
                    cliente.setCodCliente(parseInt(valoresEntreVirgulas[1]));
                    aulaCliente.setAluno(cliente);

                }
            }
            return aulacliente;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    public AulasClientes consultarClientesPorAula(int codAula) {
        File arquivoCSV = new File(tabela);
        AulasClientes aulacliente = new AulasClientes();
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
                if (parseInt(valoresEntreVirgulas[0]) == codAula) {
                    AulasClientes aulaCliente = new AulasClientes();
                    Aulas aula = new Aulas();
                    aula.setCodAulas(parseInt(valoresEntreVirgulas[0]));
                    aulaCliente.setAulas(aula);
                    Cliente cliente = new Cliente();
                    cliente.setCodCliente(parseInt(valoresEntreVirgulas[1]));
                    aulaCliente.setAluno(cliente);

                }
            }
            return aulacliente;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }
     
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
                    linhasDoArquivo += String.valueOf(aulaCliente.getAluno().getCodCliente()) ;
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
                    Aulas aula = new Aulas();
                    aula.setCodAulas(parseInt(valoresEntreVirgulas[0]));
                    aulaCliente.setAulas(aula);
                    Cliente cliente = new Cliente();
                    cliente.setCodCliente(parseInt(valoresEntreVirgulas[1]));
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

    
}
