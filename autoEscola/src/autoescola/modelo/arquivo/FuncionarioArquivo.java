/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.arquivo;

import autoescola.modelo.bean.Endereco;
import autoescola.modelo.bean.Funcionario;
import autoescola.modelo.bean.Usuario;
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
public class FuncionarioArquivo extends Arquivo {

    private final String tabela = "tabelas/funcionario.csv";

    /**
     * Consulta e retorna todos funcionarios cadastrados
     *
     * @return the ArrayList of Funcionario
     */
    public ArrayList<Funcionario> consultarFuncionario() {
        File arquivoCSV = new File(tabela);
        ArrayList<Funcionario> funcionarios = new ArrayList();
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
                    Funcionario funcionario = new Funcionario();
                    funcionario.setCodigoFuncionario(parseInt(valoresEntreVirgulas[0]));
                    EnderecoArquivo endArq = new EnderecoArquivo();
                    Endereco endereco = endArq.consultar(parseInt(valoresEntreVirgulas[1]));
                    funcionario.setEndereco(endereco);
                    funcionario.setRg(valoresEntreVirgulas[2]);
                    funcionario.setNome(valoresEntreVirgulas[3]);
                    funcionario.setCpf(valoresEntreVirgulas[4]);
                    funcionario.setDatanasc(valoresEntreVirgulas[5]);
                    funcionario.setTelefone(valoresEntreVirgulas[6]);
                    funcionario.setCelular(valoresEntreVirgulas[7]);
                    funcionario.setHora_entra(valoresEntreVirgulas[8]);
                    funcionario.setHora_sai(valoresEntreVirgulas[9]);
                    funcionario.setTipo(valoresEntreVirgulas[10]);
                    funcionario.setNumCarteira(valoresEntreVirgulas[11]);
                    funcionario.setCategoria(valoresEntreVirgulas[12]);
                    boolean status;
                    status = valoresEntreVirgulas[13].equals("true");
                    funcionario.setStatus(status);
                    funcionarios.add(funcionario);

                }
            }
            return funcionarios;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    /**
     * Função para consultar o funcionário a partir do código
     *
     * @param codigofuncionario
     * @return
     */
    @Override
    public Funcionario consultar(int codigofuncionario) {
        File arquivoCSV = new File(tabela);
        Funcionario funcionario = new Funcionario();
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
                if (parseInt(valoresEntreVirgulas[0]) == codigofuncionario) {
                    funcionario.setCodigoFuncionario(parseInt(valoresEntreVirgulas[0]));
                    EnderecoArquivo endArq = new EnderecoArquivo();
                    Endereco endereco = endArq.consultar(parseInt(valoresEntreVirgulas[1]));
                    funcionario.setEndereco(endereco);
                    funcionario.setRg(valoresEntreVirgulas[2]);
                    funcionario.setNome(valoresEntreVirgulas[3]);
                    funcionario.setCpf(valoresEntreVirgulas[4]);
                    funcionario.setDatanasc(valoresEntreVirgulas[5]);
                    funcionario.setTelefone(valoresEntreVirgulas[6]);
                    funcionario.setCelular(valoresEntreVirgulas[7]);
                    funcionario.setHora_entra(valoresEntreVirgulas[8]);
                    funcionario.setHora_sai(valoresEntreVirgulas[9]);
                    funcionario.setTipo(valoresEntreVirgulas[10]);
                    funcionario.setNumCarteira(valoresEntreVirgulas[11]);
                    funcionario.setCategoria(valoresEntreVirgulas[12]);

                    boolean status;
                    status = valoresEntreVirgulas[13].equals("true");
                    funcionario.setStatus(status);
                }
            }
            return funcionario;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    /**
     * Desativar funcionário a partir do código do funcionario
     *
     * @param codigofuncionario
     * @return false or true
     */
    @Override
    public boolean desativar(int codigofuncionario) {
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
                if (parseInt(valoresEntreVirgulas[0]) == codigofuncionario) {
                    linhasDoArquivo = valoresEntreVirgulas[0] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[1] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[2] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[3] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[4] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[5] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[6] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[7] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[8] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[9] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[10] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[11] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[12] + ",";
                    linhasDoArquivo += "false";

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
    
       public boolean consultarRg(String rg) {
        File arquivoCSV = new File(tabela);
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
                if (valoresEntreVirgulas[2].equals(rg)) {
                    return true;
                }
            }
            return false;

        } catch (FileNotFoundException e) {
            //log de erro
            return false;

        }
    }


    /**
     *
     * @param codigofuncionario
     * @return
     */
    public boolean ativar(int codigofuncionario) {
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
                if (parseInt(valoresEntreVirgulas[0]) == codigofuncionario) {
                    linhasDoArquivo = valoresEntreVirgulas[0] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[1] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[2] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[3] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[4] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[5] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[6] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[7] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[8] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[9] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[10] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[11] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[12] + ",";
                    linhasDoArquivo += "true";

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
     * Alterar funcionario
     *
     * @param funcionario
     * @return false or true
     */
    public boolean alterarfuncionario(Funcionario funcionario) {
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
                if (parseInt(valoresEntreVirgulas[0]) == funcionario.getCodigoFuncionario()) {
                    linhasDoArquivo = String.valueOf(funcionario.getCodigoFuncionario()) + ",";
                    linhasDoArquivo += String.valueOf(funcionario.getEndereco().getCodEndereco()) + ",";
                    linhasDoArquivo += funcionario.getRg() + ",";
                    linhasDoArquivo += funcionario.getNome() + ",";
                    linhasDoArquivo += funcionario.getCpf() + ",";
                    linhasDoArquivo += funcionario.getDatanasc() + ",";
                    linhasDoArquivo += funcionario.getTelefone() + ",";
                    linhasDoArquivo += funcionario.getCelular() + ",";
                    linhasDoArquivo += funcionario.getHora_entra() + ",";
                    linhasDoArquivo += funcionario.getHora_sai() + ",";
                    linhasDoArquivo += funcionario.getTipo() + ",";
                    linhasDoArquivo += funcionario.getNumCarteira() + ",";
                    linhasDoArquivo += funcionario.getCategoria() + ",";
                    linhasDoArquivo += String.valueOf(funcionario.getStatus());

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
     * Cadastrar funcionário
     *
     * @param funcionario
     * @return false or true
     */
    public int cadastrarfuncionario(Funcionario funcionario) {

        int idFuncionario = autoIncremento(tabela);
        try {
            // O parametro é que indica se deve sobrescrever ou continua no
            // arquivo.
            FileWriter fw = new FileWriter(tabela, true);
            BufferedWriter conexao = new BufferedWriter(fw);
            if (idFuncionario != 0) {
                conexao.write(String.valueOf(idFuncionario));
                conexao.write(',');
                conexao.write(String.valueOf(funcionario.getEndereco().getCodEndereco()));
                conexao.write(',');
                conexao.write(funcionario.getRg());
                conexao.write(',');
                conexao.write(funcionario.getNome());
                conexao.write(',');
                conexao.write(funcionario.getCpf());
                conexao.write(',');
                conexao.write(funcionario.getDatanasc());
                conexao.write(',');
                conexao.write(funcionario.getTelefone());
                conexao.write(',');
                conexao.write(funcionario.getCelular());
                conexao.write(',');
                conexao.write(funcionario.getHora_entra());
                conexao.write(',');
                conexao.write(funcionario.getHora_sai());
                conexao.write(',');
                conexao.write(funcionario.getTipo());
                conexao.write(',');
                conexao.write(funcionario.getNumCarteira());
                conexao.write(',');
                conexao.write(funcionario.getCategoria());
                conexao.write(',');
                conexao.write(String.valueOf(funcionario.getStatus()));
                conexao.newLine();
                conexao.close();

                return idFuncionario;
            } else {
                return 0;
            }

        } catch (IOException e) {
            //criar arquivo para salvar os erros 
            return 0;
        }
    }

    /**
     * Função para consultar Funcionario a partir do campo e um valor que pode
     * ser encontrado nessa coluna
     *
     * @param campo
     * @param valor
     * @return ArrayList of Funcionario
     */
    public ArrayList<Funcionario> consultarfuncionariosLike(String campo, String valor) {
        File arquivoCSV = new File(tabela);
        ArrayList<Funcionario> funcionarios = new ArrayList();
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
                    Funcionario funcionario = new Funcionario();
                    funcionario.setCodigoFuncionario(parseInt(valoresEntreVirgulas[0]));
                    EnderecoArquivo endArq = new EnderecoArquivo();
                    Endereco endereco = endArq.consultar(parseInt(valoresEntreVirgulas[1]));
                    funcionario.setEndereco(endereco);
                    funcionario.setRg(valoresEntreVirgulas[2]);
                    funcionario.setNome(valoresEntreVirgulas[3]);
                    funcionario.setCpf(valoresEntreVirgulas[4]);
                    funcionario.setDatanasc(valoresEntreVirgulas[5]);
                    funcionario.setTelefone(valoresEntreVirgulas[6]);
                    funcionario.setCelular(valoresEntreVirgulas[7]);
                    funcionario.setHora_entra(valoresEntreVirgulas[8]);
                    funcionario.setHora_sai(valoresEntreVirgulas[9]);
                    funcionario.setTipo(valoresEntreVirgulas[10]);
                    funcionario.setNumCarteira(valoresEntreVirgulas[11]);
                    funcionario.setCategoria(valoresEntreVirgulas[12]);
                    boolean status;
                    status = valoresEntreVirgulas[13].equals("true");
                    funcionario.setStatus(status);
                    funcionarios.add(funcionario);

                }
            }
            return funcionarios;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }
}
