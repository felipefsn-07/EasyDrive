/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.arquivo;

import autoescola.modelo.bean.Instrutor;
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
public class InstrutorArquivo extends Arquivo {

    private final String tabela = "tabelas/instrutor.csv";

    /**
     * Consulta e retorna todos os Instrutores cadatrados
     *
     * @return the ArrayList of Instrutor
     */
    public ArrayList<Instrutor> consultarInstrutors() {
        File arquivoCSV = new File(tabela);
        ArrayList<Instrutor> instrutores = new ArrayList();
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
                    Instrutor instrutor = new Instrutor();
                    instrutor.setCodigoFuncionario(parseInt(valoresEntreVirgulas[0]));
                    instrutor.setNumCarteira(valoresEntreVirgulas[1]);
                    instrutor.setCategoria(valoresEntreVirgulas[2]);
                    boolean status;
                    status = valoresEntreVirgulas[3].equals("true");
                    instrutor.setStatus(status);
                    instrutores.add(instrutor);

                }
            }
            return instrutores;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    /**
     * Consulta e reterona o Instrutor a partir do código do Instrutor
     *
     * @param codigoInstrutor
     * @return the Instrutor
     */
    @Override
    public Instrutor consultar(int codigoInstrutor) {
        File arquivoCSV = new File(tabela);
        Instrutor instrutor = new Instrutor();
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
                if (parseInt(valoresEntreVirgulas[0]) == codigoInstrutor) {
                    instrutor.setCodigoFuncionario(parseInt(valoresEntreVirgulas[0]));
                    instrutor.setNumCarteira(valoresEntreVirgulas[1]);
                    instrutor.setCategoria(valoresEntreVirgulas[2]);
                    boolean status;
                    status = valoresEntreVirgulas[3].equals("true");
                    instrutor.setStatus(status);
                }
            }
            return instrutor;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    /**
     * Desativar Instrutor a partir do codigo Instrutor
     *
     * @param codigoInstrutor
     * @return false or true
     */
    @Override
    public boolean desativar(int codigoInstrutor) {
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
                if (parseInt(valoresEntreVirgulas[0]) == codigoInstrutor) {
                    linhasDoArquivo = valoresEntreVirgulas[0] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[1] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[2] + ",";
                    linhasDoArquivo += "0";
                }
                todo += linhasDoArquivo + "\n";
                FuncionarioArquivo arq = new FuncionarioArquivo();
                arq.desativar(codigoInstrutor);
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
     * Alterar Instrutor
     *
     * @param instrutor
     * @return false or true
     */
    public boolean alterarInstrutor(Instrutor instrutor) {
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
                if (parseInt(valoresEntreVirgulas[0]) == instrutor.getCodigoFuncionario()) {
                    linhasDoArquivo = String.valueOf(instrutor.getCodigoFuncionario()) + ",";
                    linhasDoArquivo += instrutor.getNumCarteira() + ",";
                    linhasDoArquivo += instrutor.getCategoria() + ",";
                    linhasDoArquivo += String.valueOf(instrutor.getStatus());
                    FuncionarioArquivo arq = new FuncionarioArquivo();
                    arq.alterarfuncionario(instrutor);
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
     * Cadastra funcionario
     *
     * @param instrutor
     * @return false or true
     */
    public int cadastrarInstrutor(Instrutor instrutor) {
        FuncionarioArquivo arq = new FuncionarioArquivo();
        int idInstrutor = arq.cadastrarfuncionario(instrutor);
        try {
            // O parametro é que indica se deve sobrescrever ou continua no
            // arquivo.
            FileWriter fw = new FileWriter(tabela, true);
            BufferedWriter conexao = new BufferedWriter(fw);
            if (idInstrutor != 0) {
                conexao.write(String.valueOf(idInstrutor));
                conexao.write(',');
                conexao.write(instrutor.getNumCarteira());
                conexao.write(',');
                conexao.write(instrutor.getCategoria());
                conexao.write(',');
                conexao.write(String.valueOf(instrutor.getStatus()));
                conexao.newLine();
                conexao.close();

                return idInstrutor;
            } else {
                return 0;
            }

        } catch (IOException e) {
            //criar arquivo para salvar os erros 
            return 0;
        }
    }

    /**
     * Função para consultar Instrutor a partir do campo e um valor que pode ser
     * encontrado nessa coluna
     *
     * @param campo
     * @param valor
     * @return the ArrayList of Instrutor
     */
    public ArrayList<Instrutor> consultarInstrutorsLike(String campo, String valor) {
        File arquivoCSV = new File(tabela);
        ArrayList<Instrutor> instrutores = new ArrayList();
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
                    Instrutor instrutor = new Instrutor();
                    instrutor.setCodigoFuncionario(parseInt(valoresEntreVirgulas[0]));
                    instrutor.setNumCarteira(valoresEntreVirgulas[1]);
                    instrutor.setCategoria(valoresEntreVirgulas[2]);
                    boolean status;
                    status = valoresEntreVirgulas[3].equals("true");
                    instrutor.setStatus(status);
                    instrutores.add(instrutor);

                }
            }
            return instrutores;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }
}
