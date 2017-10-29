/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.arquivo;

import autoescola.modelo.bean.Veiculo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author felipe
 */
public class VeiculoArquivo extends Arquivo {

    private final String tabela = "tabelas/veiculo.csv";

    /**
     * Consulta e retorna todos os veiculos cadastrados
     *
     * @return the ArrayList of Veiculo
     */
    public ArrayList<Veiculo> consultarVeiculos() {
        File arquivoCSV = new File(tabela);
        ArrayList<Veiculo> veiculos = new ArrayList();
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
                    Veiculo veiculo = new Veiculo();
                    veiculo.setCodVeiculo(parseInt(valoresEntreVirgulas[0]));
                    veiculo.setPlaca(valoresEntreVirgulas[1]);
                    veiculo.setAno(valoresEntreVirgulas[2]);
                    veiculo.setModelo(valoresEntreVirgulas[3]);
                    veiculo.setCapacidade(parseFloat(valoresEntreVirgulas[4]));
                    boolean status = "true".equals(valoresEntreVirgulas[5]);
                    veiculo.setStatus(status);
                    veiculos.add(veiculo);

                }
            }
            return veiculos;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    /**
     * Consultar e retorn veiculo a partir do código do veiculo
     *
     * @param codVeiculo
     * @return the Veiculo
     */
    @Override
    public Veiculo consultar(int codVeiculo) {
        File arquivoCSV = new File(tabela);
        Veiculo veiculo = new Veiculo();
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
                if (parseInt(valoresEntreVirgulas[0]) == codVeiculo) {
                    veiculo.setCodVeiculo(parseInt(valoresEntreVirgulas[0]));
                    veiculo.setPlaca(valoresEntreVirgulas[1]);
                    veiculo.setAno(valoresEntreVirgulas[2]);
                    veiculo.setModelo(valoresEntreVirgulas[3]);
                    veiculo.setCapacidade(parseFloat(valoresEntreVirgulas[4]));
                    boolean status = "true".equals(valoresEntreVirgulas[5]);
                    veiculo.setStatus(status);

                }
            }
            return veiculo;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    /**
     * Desativar veiculo a partir do código Veiculo
     *
     * @param codVeiculo
     * @return false or true
     */
    @Override
    public boolean desativar(int codVeiculo) {
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
                if (parseInt(valoresEntreVirgulas[0]) == codVeiculo) {
                    linhasDoArquivo = valoresEntreVirgulas[0] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[1] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[2] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[3] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[4] + ",";
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

    public boolean ativar(int codVeiculo) {
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
                if (parseInt(valoresEntreVirgulas[0]) == codVeiculo) {
                    linhasDoArquivo = valoresEntreVirgulas[0] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[1] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[2] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[3] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[4] + ",";
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
     * Alterar veiculo
     *
     * @param veiculo
     * @return false or true
     */
    public boolean alterarVeiculo(Veiculo veiculo) {
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
                if (parseInt(valoresEntreVirgulas[0]) == veiculo.getCodVeiculo()) {
                    linhasDoArquivo = String.valueOf(veiculo.getCodVeiculo()) + ",";
                    linhasDoArquivo += veiculo.getPlaca() + ",";
                    linhasDoArquivo += veiculo.getAno() + ",";
                    linhasDoArquivo += veiculo.getModelo() + ",";
                    linhasDoArquivo += String.valueOf(veiculo.getCapacidade()) + ",";
                    linhasDoArquivo += String.valueOf(veiculo.getStatus());

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
     * Cadastrar veiculo
     *
     * @param veiculo
     * @return false or true
     */
    public boolean cadastrarVeiculo(Veiculo veiculo) {

        int codVeiculo = autoIncremento(tabela);
        try {
            // O parametro é que indica se deve sobrescrever ou continua no
            // arquivo.
            FileWriter fw = new FileWriter(tabela, true);
            BufferedWriter conexao = new BufferedWriter(fw);
            if (codVeiculo != 0) {
                conexao.write(String.valueOf(veiculo.getCodVeiculo()));
                conexao.write(',');
                conexao.write(veiculo.getPlaca());
                conexao.write(',');
                conexao.write(veiculo.getAno());
                conexao.write(',');
                conexao.write(veiculo.getModelo());
                conexao.write(',');
                conexao.write(String.valueOf(veiculo.getCapacidade()));
                conexao.write(',');
                conexao.write(String.valueOf(veiculo.getStatus()));
                conexao.newLine();
                conexao.close();

                return true;
            } else {
                //msg erro no incremento codVeiculo == 0
                return false;
            }

        } catch (IOException e) {
            //criar arquivo para salvar os erros 
            return false;
        }
    }

    /**
     * Função para consultar veiculos a partir do campo e um valor que pode ser
     * encontrado nessa coluna
     *
     * @param campo
     * @param valor
     * @return ArrayList of Veiculo
     */
    public ArrayList<Veiculo> consultarVeiculosLike(String campo, String valor) {
        File arquivoCSV = new File(tabela);
        ArrayList<Veiculo> veiculos = new ArrayList();
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
                    Veiculo veiculo = new Veiculo();
                    veiculo.setCodVeiculo(parseInt(valoresEntreVirgulas[0]));
                    veiculo.setPlaca(valoresEntreVirgulas[1]);
                    veiculo.setAno(valoresEntreVirgulas[2]);
                    veiculo.setModelo(valoresEntreVirgulas[3]);
                    veiculo.setCapacidade(parseFloat(valoresEntreVirgulas[4]));
                    boolean status = "true".equals(valoresEntreVirgulas[5]);
                    veiculo.setStatus(status);

                    veiculos.add(veiculo);

                }
            }
            return veiculos;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

}
