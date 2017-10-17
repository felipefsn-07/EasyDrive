/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.arquivo;

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
public class EnderecoArquivo extends Arquivo implements Tabela{

    private final String tabela = "tabelas/endereco.csv";

    public ArrayList<Endereco> consultarEnderecos() {
        File arquivoCSV = new File(tabela);
        ArrayList<Endereco> enderecos = new ArrayList();
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
                    Endereco endereco = new Endereco();
                    endereco.setCodEndereco(parseInt(valoresEntreVirgulas[0]));
                    endereco.setNum(valoresEntreVirgulas[1]);
                    endereco.setCidade(valoresEntreVirgulas[2]);
                    endereco.setEstado(valoresEntreVirgulas[3]);
                    endereco.setLogradouro(valoresEntreVirgulas[4]);
                    endereco.setBairro(valoresEntreVirgulas[5]);
                    endereco.setCep(valoresEntreVirgulas[6]);
                    endereco.setStatus(parseInt(valoresEntreVirgulas[7]));

                    enderecos.add(endereco);

                }
            }
            return enderecos;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    @Override
    public Endereco consultar(int codEndereco) {
        File arquivoCSV = new File(tabela);
        Endereco endereco = new Endereco();
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
                if (parseInt(valoresEntreVirgulas[0]) == codEndereco) {
                    endereco.setCodEndereco(parseInt(valoresEntreVirgulas[0]));
                    endereco.setNum(valoresEntreVirgulas[1]);
                    endereco.setCidade(valoresEntreVirgulas[2]);
                    endereco.setEstado(valoresEntreVirgulas[3]);
                    endereco.setLogradouro(valoresEntreVirgulas[4]);
                    endereco.setBairro(valoresEntreVirgulas[5]);
                    endereco.setCep(valoresEntreVirgulas[6]);
                    endereco.setStatus(parseInt(valoresEntreVirgulas[7]));

                }
            }
            return endereco;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    @Override
    public boolean desativar(int codEndereco) {
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
                if (parseInt(valoresEntreVirgulas[0]) == codEndereco) {
                    linhasDoArquivo = valoresEntreVirgulas[0] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[1] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[2] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[3] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[4] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[5] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[6] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[7] + ",";
                    linhasDoArquivo += "0";

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

    public boolean alterarEndereco(Endereco endereco) {
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
                if (parseInt(valoresEntreVirgulas[0]) == endereco.getCodEndereco()) {
                    linhasDoArquivo = String.valueOf(endereco.getCodEndereco()) + ",";
                    linhasDoArquivo += endereco.getNum() + ",";
                    linhasDoArquivo += endereco.getCidade() + ",";
                    linhasDoArquivo += endereco.getEstado() + ",";
                    linhasDoArquivo += endereco.getLogradouro()+ ",";
                    linhasDoArquivo += endereco.getBairro() + ",";
                    linhasDoArquivo += endereco.getCep() + ",";
                    linhasDoArquivo += String.valueOf(endereco.getStatus());

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

    public int cadastrarEndereco(Endereco endereco) {

        int codEndereco = autoIncremento(tabela);
        try {
            // O parametro é que indica se deve sobrescrever ou continua no
            // arquivo.
            FileWriter fw = new FileWriter(tabela, true);
            BufferedWriter conexao = new BufferedWriter(fw);
            if (codEndereco != 0) {
                conexao.write(String.valueOf(codEndereco));
                conexao.write(',');
                conexao.write(endereco.getNum());
                conexao.write(',');
                conexao.write(endereco.getCidade());
                conexao.write(',');
                conexao.write(endereco.getEstado());
                conexao.write(',');
                conexao.write(endereco.getLogradouro());
                conexao.write(',');
                conexao.write(endereco.getBairro());
                conexao.write(',');
                conexao.write(endereco.getCep());
                conexao.write(',');
                conexao.write(String.valueOf(endereco.getStatus()));
                conexao.newLine();
                conexao.close();

                return codEndereco;
            } else {
                //msg erro no incremento codEndereco == 0
                return 0;
            }

        } catch (IOException e) {
            //criar arquivo para salvar os erros 
            return 0;
        }
    }

    public ArrayList<Endereco> consultarEnderecosLike(String campo, String valor) {
        File arquivoCSV = new File(tabela);
        ArrayList<Endereco> enderecos = new ArrayList();
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
                    Endereco endereco = new Endereco();
                    endereco.setCodEndereco(parseInt(valoresEntreVirgulas[0]));
                    endereco.setNum(valoresEntreVirgulas[1]);
                    endereco.setCidade(valoresEntreVirgulas[2]);
                    endereco.setEstado(valoresEntreVirgulas[3]);
                    endereco.setLogradouro(valoresEntreVirgulas[4]);
                    endereco.setBairro(valoresEntreVirgulas[5]);
                    endereco.setCep(valoresEntreVirgulas[6]);
                    endereco.setStatus(parseInt(valoresEntreVirgulas[7]));

                    enderecos.add(endereco);

                }
            }
            return enderecos;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

}
