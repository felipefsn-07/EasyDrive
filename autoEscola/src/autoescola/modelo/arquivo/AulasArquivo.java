/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.arquivo;

import autoescola.modelo.bean.Aula;
import autoescola.modelo.bean.Aula;
import autoescola.modelo.bean.AulasClientes;
import autoescola.modelo.bean.Cliente;
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
public class AulasArquivo extends Arquivo {

    private final String tabela = "tabelas/aula.csv";

    /**
     * @return the ArrayList of Aula
     */
    public ArrayList<Aula> consultarAulas() {
        File arquivoCSV = new File(tabela);
        ArrayList<Aula> aulas = new ArrayList();
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
                    Aula aula = new Aula();
                    aula.setCodAulas(parseInt(valoresEntreVirgulas[0]));
                    aula.setDataAula(valoresEntreVirgulas[1]);
                    aula.setHorarioAulaInicio(valoresEntreVirgulas[2]);
                    aula.setHorarioAulaFim(valoresEntreVirgulas[3]);
                    Veiculo veiculo = new Veiculo();
                    veiculo.setCodVeiculo(parseInt(valoresEntreVirgulas[4]));
                    aula.setVeiculo(veiculo);
                    Funcionario funcionario = new Funcionario();
                    funcionario.setCodigoFuncionario(parseInt(valoresEntreVirgulas[5]));
                    aula.setInstrutor(funcionario);

                    aulas.add(aula);

                }
            }
            return aulas;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    @Override
    /**
     * @param codAulas
     * @return the Aula
     */
    public Aula consultar(int codAulas) {
        File arquivoCSV = new File(tabela);
        Aula aula = new Aula();
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
                if (parseInt(valoresEntreVirgulas[0]) == codAulas) {
                    aula.setCodAulas(parseInt(valoresEntreVirgulas[0]));
                    aula.setDataAula(valoresEntreVirgulas[1]);
                    aula.setHorarioAulaInicio(valoresEntreVirgulas[2]);
                    aula.setHorarioAulaFim(valoresEntreVirgulas[3]);
                    VeiculoArquivo va = new VeiculoArquivo();       
                    Veiculo veiculo =va.consultar(parseInt(valoresEntreVirgulas[4]));
                    aula.setVeiculo(veiculo);
                    FuncionarioArquivo fa = new FuncionarioArquivo();             
                    Funcionario funcionario = fa.consultar(parseInt(valoresEntreVirgulas[5]));
                    aula.setInstrutor(funcionario);
                }
            }
            return aula;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    
      public Aula consultarChamada(int codAulas) {
        File arquivoCSV = new File(tabela);
        Aula aula = new Aula();
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
                if (parseInt(valoresEntreVirgulas[0]) == codAulas) {
                    aula.setCodAulas(parseInt(valoresEntreVirgulas[0]));
                    aula.setDataAula(valoresEntreVirgulas[1]);
                    aula.setHorarioAulaInicio(valoresEntreVirgulas[2]);
                    aula.setHorarioAulaFim(valoresEntreVirgulas[3]);
                    VeiculoArquivo va = new VeiculoArquivo();       
                    Veiculo veiculo =va.consultar(parseInt(valoresEntreVirgulas[4]));
                    aula.setVeiculo(veiculo);
                    FuncionarioArquivo fa = new FuncionarioArquivo();             
                    Funcionario funcionario = fa.consultar(parseInt(valoresEntreVirgulas[5]));
                    AulasClientesArquivo aca = new AulasClientesArquivo();
                    ArrayList<AulasClientes> alunos = aca.consultarAulasClientes(codAulas);
                    aula.setAulas(alunos);
                    aula.setInstrutor(funcionario);
                   
                    break;
                }
            }
            return aula;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    
    /**
     *
     * @param codigoAula
     * @param codAula
     * @return
     */
    public boolean apagar(int codigoAula) {
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
                    AulasClientesArquivo arqEC = new AulasClientesArquivo();
                    arqEC.apagarAula(codigoAula);

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
     * @param aula
     * @return false or true
     */
    public boolean alterar(Aula aula) {

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
                if (parseInt(valoresEntreVirgulas[0]) == aula.getCodAulas()) {
                    linhasDoArquivo = String.valueOf(aula.getCodAulas()) + ",";
                    linhasDoArquivo += aula.getDataAula() + ",";
                    linhasDoArquivo += aula.getHorarioAulaInicio() + ",";
                    linhasDoArquivo += aula.getHorarioAulaFim() + ",";
                    linhasDoArquivo += String.valueOf(aula.getVeiculo().getCodVeiculo()) + ",";
                    linhasDoArquivo += String.valueOf(aula.getInstrutor().getCodigoFuncionario());

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
     * @param aula
     * @return false or true
     */
    public int cadastrarAulas(Aula aula) {

        int idAulas = autoIncremento(tabela);
        try {
            // O parametro é que indica se deve sobrescrever ou continua no
            // arquivo.
            FileWriter fw = new FileWriter(tabela, true);
            BufferedWriter conexao = new BufferedWriter(fw);
            if (idAulas != 0) {
                conexao.write(String.valueOf(idAulas));
                conexao.write(',');
                conexao.write(aula.getDataAula());
                conexao.write(',');
                conexao.write(aula.getHorarioAulaInicio());
                conexao.write(',');
                conexao.write(aula.getHorarioAulaFim());
                conexao.write(',');
                conexao.write(String.valueOf(aula.getVeiculo().getCodVeiculo()));
                conexao.write(',');
                conexao.write(String.valueOf(aula.getInstrutor().getCodigoFuncionario()));
                conexao.newLine();
                conexao.close();

                return idAulas;
            } else {
                return 0;
            }

        } catch (IOException e) {
            //criar arquivo para salvar os erros 
            return 0;
        }
    }

    /**
     *
     * @param campo
     * @param valor
     * @return the ArrayList of Aula
     */
    public ArrayList<Aula> consultarAulassLike(String campo, String valor) {
        File arquivoCSV = new File(tabela);
        ArrayList<Aula> aulas = new ArrayList();
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
                    Aula aula = new Aula();
                    aula.setCodAulas(parseInt(valoresEntreVirgulas[0]));
                    aula.setDataAula(valoresEntreVirgulas[1]);
                    aula.setHorarioAulaInicio(valoresEntreVirgulas[2]);
                    aula.setHorarioAulaFim(valoresEntreVirgulas[3]);
                    Veiculo veiculo = new Veiculo();
                    veiculo.setCodVeiculo(parseInt(valoresEntreVirgulas[4]));
                    aula.setVeiculo(veiculo);
                    Funcionario funcionario = new Funcionario();
                    funcionario.setCodigoFuncionario(parseInt(valoresEntreVirgulas[5]));
                    aula.setInstrutor(funcionario);
                    aulas.add(aula);

                }
            }
            return aulas;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

     public ArrayList<Aula> consultarData(String data) {
        File arquivoCSV = new File(tabela);
        ArrayList<Aula> aulas = new ArrayList();
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
                    Aula aula = new Aula();
                    aula.setCodAulas(parseInt(valoresEntreVirgulas[0]));
                    aula.setDataAula(valoresEntreVirgulas[1]);
                    aula.setHorarioAulaInicio(valoresEntreVirgulas[2]);
                    aula.setHorarioAulaFim(valoresEntreVirgulas[3]);
                    aulas.add(aula);

                }
            }
            return aulas;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

}
