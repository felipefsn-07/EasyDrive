/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.arquivo;

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
                    Usuario usuario = new Usuario();
                    usuario.setCodLogin(parseInt(valoresEntreVirgulas[1]));
                    funcionario.setUsuario(usuario);
                    funcionario.set(valoresEntreVirgulas[2]);
                    funcionario.setCelular(valoresEntreVirgulas[3]);
                    funcionario.setDatanasc(valoresEntreVirgulas[4]);
                    funcionario.setRg(valoresEntreVirgulas[5]);
                    funcionario.setCpf(valoresEntreVirgulas[6]);
                    funcionario.setNumLADV(valoresEntreVirgulas[7]);
                    funcionario.setStatus(parseInt(valoresEntreVirgulas[8]));
                    funcionario.setCategoria(valoresEntreVirgulas[9]);
                    funcionario.setCodEndereco(parseInt(valoresEntreVirgulas[10]));
                    funcionarios.add(funcionario);

                }
            }
            return funcionarios;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    public funcionario consultarfuncionario(int codigofuncionario) {
        File arquivoCSV = new File(tabela);
        funcionario funcionario = new funcionario();
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
                    funcionario.setCodfuncionario(parseInt(valoresEntreVirgulas[0]));
                    funcionario.setNome(valoresEntreVirgulas[1]);
                    funcionario.setTelefone(valoresEntreVirgulas[2]);
                    funcionario.setCelular(valoresEntreVirgulas[3]);
                    funcionario.setDatanasc(valoresEntreVirgulas[4]);
                    funcionario.setRg(valoresEntreVirgulas[5]);
                    funcionario.setCpf(valoresEntreVirgulas[6]);
                    funcionario.setNumLADV(valoresEntreVirgulas[7]);
                    funcionario.setStatus(parseInt(valoresEntreVirgulas[8]));
                    funcionario.setCategoria(valoresEntreVirgulas[9]);
                    funcionario.setCodEndereco(parseInt(valoresEntreVirgulas[10]));
                }
            }
            return funcionario;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    public boolean desativarfuncionario(int codigofuncionario) {
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

    public boolean alterarfuncionario(int codigofuncionario, funcionario funcionario) {
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
                    linhasDoArquivo = String.valueOf(codigofuncionario) + ",";
                    linhasDoArquivo +=funcionario.getNome() + ",";
                    linhasDoArquivo += funcionario.getTelefone() + ",";
                    linhasDoArquivo += funcionario.getCelular() + ",";
                    linhasDoArquivo += funcionario.getDatanasc()+ ",";
                    linhasDoArquivo += funcionario.getRg()+ ",";
                    linhasDoArquivo += funcionario.getCpf() + ",";
                    linhasDoArquivo += funcionario.getNumLADV() + ",";
                    linhasDoArquivo += String.valueOf(funcionario.getStatus())+",";
                    linhasDoArquivo += funcionario.getCategoria() + ",";
                    linhasDoArquivo += String.valueOf(funcionario.getCodEndereco());

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

    public boolean cadastrarfuncionario(Funcionario funcionario) {

        int idfuncionario = autoIncremento(tabela);
        try {
            // O parametro é que indica se deve sobrescrever ou continua no
            // arquivo.
            FileWriter fw = new FileWriter(tabela, true);
            BufferedWriter conexao = new BufferedWriter(fw);
            if (idfuncionario != 0) {
                conexao.write(String.valueOf(idfuncionario));
                conexao.write(',');
                conexao.write(funcionario.getNome());
                conexao.write(',');
                conexao.write(funcionario.getTelefone());
                conexao.write(',');
                conexao.write(funcionario.getCelular());
                conexao.write(',');
                conexao.write(funcionario.getDatanasc());
                conexao.write(',');
                conexao.write(funcionario.getRg());
                conexao.write(',');
                conexao.write(funcionario.getCpf());
                conexao.write(',');
                conexao.write(funcionario.getNumLADV());
                conexao.write(',');
                conexao.write(String.valueOf(funcionario.getStatus()));
                conexao.write(',');
                conexao.write(funcionario.getCategoria());
                conexao.write(',');
                conexao.write(String.valueOf(funcionario.getCodEndereco()));
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

    public ArrayList<funcionario> consultarfuncionariosLike(String campo, String valor) {
        File arquivoCSV = new File(tabela);
        ArrayList<funcionario> funcionarios = new ArrayList();
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
                    funcionario funcionario = new funcionario();
                    funcionario.setCodfuncionario(parseInt(valoresEntreVirgulas[0]));
                    funcionario.setNome(valoresEntreVirgulas[1]);
                    funcionario.setTelefone(valoresEntreVirgulas[2]);
                    funcionario.setCelular(valoresEntreVirgulas[3]);
                    funcionario.setDatanasc(valoresEntreVirgulas[4]);
                    funcionario.setRg(valoresEntreVirgulas[5]);
                    funcionario.setCpf(valoresEntreVirgulas[6]);
                    funcionario.setNumLADV(valoresEntreVirgulas[7]);
                    funcionario.setStatus(parseInt(valoresEntreVirgulas[8]));
                    funcionario.setCategoria(valoresEntreVirgulas[9]);
                    funcionario.setCodEndereco(parseInt(valoresEntreVirgulas[10]));
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
