/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.arquivo;

import autoescola.modelo.bean.Exame;
import autoescola.modelo.bean.ExameClientes;
import autoescola.modelo.bean.Funcionario;
import autoescola.modelo.bean.Veiculo;
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
public class ExameArquivo extends Arquivo {

    private final String tabela = "tabelas/exame.csv";

    /**
     * Consulta e retorna todos os Exames cadastrados
     *
     * @return the ArrayList of Exame
     */
    public ArrayList<Exame> consultarExames() {
        File arquivoCSV = new File(tabela);
        ArrayList<Exame> exames = new ArrayList();
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
                    Exame exame = new Exame();
                    exame.setCodigoExame(parseInt(valoresEntreVirgulas[0]));
                    exame.setDataExame(valoresEntreVirgulas[1]);
                    exame.setHorarioInicio(valoresEntreVirgulas[2]);
                    exame.setHorarioFim(valoresEntreVirgulas[3]);

                    Veiculo veiculo = new Veiculo();
                    veiculo.setCodVeiculo(parseInt(valoresEntreVirgulas[4]));
                    exame.setVeiculo(veiculo);
                    Funcionario instrutor = new Funcionario();
                    instrutor.setCodigoFuncionario(parseInt(valoresEntreVirgulas[5]));
                    exame.setInstrutor(instrutor);

                    exames.add(exame);

                }
            }
            return exames;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    /**
     * Consulta e retorna o exame a partir do código do Exame
     *
     * @param codExame
     * @return tje Exame
     */
    @Override
    public Exame consultar(int codExame) {
        File arquivoCSV = new File(tabela);
        Exame exame = new Exame();
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
                    exame.setCodigoExame(parseInt(valoresEntreVirgulas[0]));
                    exame.setDataExame(valoresEntreVirgulas[1]);
                    exame.setHorarioInicio(valoresEntreVirgulas[2]);
                    exame.setHorarioFim(valoresEntreVirgulas[3]);
                    VeiculoArquivo va = new VeiculoArquivo();

                    Veiculo veiculo = va.consultar(parseInt(valoresEntreVirgulas[4]));
                    exame.setVeiculo(veiculo);

                    FuncionarioArquivo fa = new FuncionarioArquivo();
                    Funcionario instrutor = fa.consultar(parseInt(valoresEntreVirgulas[5]));
                    ClienteExameArquivo arqEc = new ClienteExameArquivo();
                    ArrayList<ExameClientes> ec = arqEc.consultarClientesPorExame(codExame);
                    exame.setAlunos(ec);
                    exame.setInstrutor(instrutor);

                }
            }
            return exame;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    public ArrayList<Exame> consultarData(String data) {
        File arquivoCSV = new File(tabela);
        ArrayList<Exame> exames = new ArrayList();
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
                if (valoresEntreVirgulas[1].equals(data)) {
                    Exame exame = new Exame();
                    exame.setCodigoExame(parseInt(valoresEntreVirgulas[0]));
                    exame.setDataExame(valoresEntreVirgulas[1]);
                    exame.setHorarioInicio(valoresEntreVirgulas[2]);
                    exame.setHorarioFim(valoresEntreVirgulas[3]);
                    Veiculo veiculo = new Veiculo();
                    veiculo.setCodVeiculo(parseInt(valoresEntreVirgulas[4]));
                    exame.setVeiculo(veiculo);
                    Funcionario instrutor = new Funcionario();
                    instrutor.setCodigoFuncionario(parseInt(valoresEntreVirgulas[5]));
                    exame.setInstrutor(instrutor);
                    exames.add(exame);

                }
            }
            return exames;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    /**
     * Desativar o exame cadastrado a partir do codigo do Exame
     *
     * @param codExame
     * @return false or true
     */
    public boolean apagar(int codExame) {
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
                if (parseInt(valoresEntreVirgulas[0]) == codExame) {
                    linhasDoArquivo = "";
                    ClienteExameArquivo arqEC = new ClienteExameArquivo();
                    arqEC.apagarExame(codExame);

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
     * Alterar dados do Exame
     *
     * @param exame
     * @return false or true
     */
    public boolean alterarExame(Exame exame) {
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
                if (parseInt(valoresEntreVirgulas[0]) == exame.getCodigoExame()) {
                    linhasDoArquivo = String.valueOf(exame.getCodigoExame()) + ",";
                    linhasDoArquivo += exame.getDataExame() + ",";
                    linhasDoArquivo += exame.getHorarioInicio() + ",";
                    linhasDoArquivo += exame.getHorarioFim() + ",";
                    linhasDoArquivo += String.valueOf(exame.getVeiculo().getCodVeiculo()) + ",";
                    linhasDoArquivo += String.valueOf(exame.getInstrutor().getCodigoFuncionario());

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
     * Função para cadastrar exame
     *
     * @param exame
     * @return false or true
     */
    public int cadastrarExame(Exame exame) {

        int codExame = autoIncremento(tabela);
        try {
            // O parametro é que indica se deve sobrescrever ou continua no
            // arquivo.
            FileWriter fw = new FileWriter(tabela, true);
            BufferedWriter conexao = new BufferedWriter(fw);
            if (codExame != 0) {
                conexao.write(String.valueOf(codExame));
                conexao.write(',');
                conexao.write(exame.getDataExame());
                conexao.write(',');
                conexao.write(exame.getHorarioInicio());
                conexao.write(',');
                conexao.write(exame.getHorarioFim());
                conexao.write(',');
                conexao.write(String.valueOf(exame.getVeiculo().getCodVeiculo()));
                conexao.write(',');
                conexao.write(String.valueOf(exame.getInstrutor().getCodigoFuncionario()));
                conexao.newLine();
                conexao.close();

                return codExame;
            } else {
                //msg erro no incremento codExame == 0
                return 0;
            }

        } catch (IOException e) {
            //criar arquivo para salvar os erros 
            return 0;
        }
    }

    /**
     * Função para consultar Exame a partir do campo e um valor que pode ser
     * encontrado nessa coluna
     *
     * @param campo
     * @param valor
     * @return the ArrayList of Exame
     */
    public ArrayList<Exame> consultarExamesLike(String campo, String valor) {
        File arquivoCSV = new File(tabela);
        ArrayList<Exame> exames = new ArrayList();
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
                    Exame exame = new Exame();
                    exame.setCodigoExame(parseInt(valoresEntreVirgulas[0]));
                    exame.setDataExame(valoresEntreVirgulas[1]);
                    exame.setHorarioInicio(valoresEntreVirgulas[2]);
                    exame.setHorarioFim(valoresEntreVirgulas[3]);

                    Veiculo veiculo = new Veiculo();
                    veiculo.setCodVeiculo(parseInt(valoresEntreVirgulas[4]));
                    exame.setVeiculo(veiculo);
                    Funcionario instrutor = new Funcionario();
                    instrutor.setCodigoFuncionario(parseInt(valoresEntreVirgulas[5]));
                    exame.setInstrutor(instrutor);

                    exames.add(exame);

                }
            }
            return exames;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }
}
