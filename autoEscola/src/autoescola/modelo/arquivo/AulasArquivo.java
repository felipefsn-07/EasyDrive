/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.arquivo;

import autoescola.modelo.bean.Aula;
import autoescola.modelo.bean.Instrutor;
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
public class AulasArquivo extends Arquivo implements Tabela{
    
    private final String tabela = "tabelas/aula.csv";

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
                    aula.setHorarioAula(valoresEntreVirgulas[2]);
                    Veiculo veiculo = new Veiculo();
                    veiculo.setCodVeiculo(parseInt(valoresEntreVirgulas[3]));
                    aula.setCodVeiculo(veiculo);
                    Instrutor instrutor = new Instrutor();
                    instrutor.setCodigoFuncionario(parseInt(valoresEntreVirgulas[4]));
                    aula.setInstrutor(instrutor);
                    aula.setStatusAula(valoresEntreVirgulas[5]);
                    
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
                    aula.setHorarioAula(valoresEntreVirgulas[2]);
                    Veiculo veiculo = new Veiculo();
                    veiculo.setCodVeiculo(parseInt(valoresEntreVirgulas[3]));
                    aula.setCodVeiculo(veiculo);
                    Instrutor instrutor = new Instrutor();
                    instrutor.setCodigoFuncionario(parseInt(valoresEntreVirgulas[4]));
                    aula.setInstrutor(instrutor);
                    aula.setStatusAula(valoresEntreVirgulas[5]);
                }
            }
            return aula;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    @Override
    public boolean desativar(int codigoAulas) {
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
                if (parseInt(valoresEntreVirgulas[0]) == codigoAulas) {
                    linhasDoArquivo = valoresEntreVirgulas[0] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[1] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[2] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[3] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[4]+ ",";
                    linhasDoArquivo += "desativada";
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
                    linhasDoArquivo +=aula.getDataAula()+ ",";
                    linhasDoArquivo += aula.getHorarioAula()+ ",";
                    linhasDoArquivo += String.valueOf(aula.getCodVeiculo().getCodVeiculo())+ ",";
                    linhasDoArquivo += String.valueOf(aula.getInstrutor().getCodigoFuncionario())+ ",";
                    linhasDoArquivo += String.valueOf(aula.getStatusAula());

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

    public boolean cadastrarAulas(Aula aula) {

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
                conexao.write(aula.getHorarioAula());
                conexao.write(',');
                conexao.write(String.valueOf(aula.getCodVeiculo().getCodVeiculo()));
                conexao.write(',');
                conexao.write(String.valueOf(aula.getInstrutor().getCodigoFuncionario()));
                conexao.write(',');
                conexao.write(aula.getStatusAula());
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
                    aula.setHorarioAula(valoresEntreVirgulas[2]);
                    Veiculo veiculo = new Veiculo();
                    veiculo.setCodVeiculo(parseInt(valoresEntreVirgulas[3]));
                    aula.setCodVeiculo(veiculo);
                    Instrutor instrutor = new Instrutor();
                    instrutor.setCodigoFuncionario(parseInt(valoresEntreVirgulas[4]));
                    aula.setInstrutor(instrutor);
                    aula.setStatusAula(valoresEntreVirgulas[5]);
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
