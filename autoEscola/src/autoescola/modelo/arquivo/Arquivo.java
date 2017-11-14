/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.arquivo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.Scanner;

/**
 *
 * @author felipe
 */
public abstract class Arquivo {

    /**
     * Implementado nas outras classes de arquivo
     *
     * @param codigo
     * @return
     */
    protected boolean desativar(int codigo) {
        return false;
    }

    /**
     * Implementado nas outras classes de arquivo
     *
     * @param codigo
     * @return
     */
    protected Object consultar(int codigo) {
        return false;
    }

    protected final int autoIncremento(String tabela) {
        int numLinhas = 1;
        //abre um arquivo e cria um file
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
            }
            if (linhasDoArquivo != null) {
                String[] valoresEntreVirgulas = linhasDoArquivo.split(",");
                if (valoresEntreVirgulas[0] != null) {
                    numLinhas += parseInt(valoresEntreVirgulas[0]);
                }
            }
        } catch (FileNotFoundException e) {
            return 0;
        }
        return numLinhas;
    }

    private void criarUsuario() {

        try {
            // O parametro é que indica se deve sobrescrever ou continua no
            // arquivo.
            FileWriter fw = new FileWriter("tabelas/usuario.csv");
            BufferedWriter conexao = new BufferedWriter(fw);
            conexao.write("codLogin,login,senha,codFuncionario,status");
            conexao.newLine();
            conexao.write("1,admin,admin,0,1");
            conexao.newLine();
            conexao.close();

        } catch (IOException e) {
            //criar arquivo para salvar os erros            
        }

    }

    private void criarAula() {

        try {
            // O parametro é que indica se deve sobrescrever ou continua no
            // arquivo.
            FileWriter fw = new FileWriter("tabelas/aula.csv");
            BufferedWriter conexao = new BufferedWriter(fw);
            conexao.write("codAula,dataAula,horarioAulaInico,horarioAulaFim,codVeiculo,codInstrutor");
            conexao.newLine();
            conexao.close();

        } catch (IOException e) {
            //criar arquivo para salvar os erros 
        }

    }

    private void criarCliente() {
        try {
            // O parametro é que indica se deve sobrescrever ou continua no
            // arquivo.
            FileWriter fw = new FileWriter("tabelas/cliente.csv");
            BufferedWriter conexao = new BufferedWriter(fw);
            conexao.write("codigo,nome,telefone,celular,data de nascimento,rg,cpf,numero do Ladv,status,categoria,codEndereco");
            conexao.newLine();
            conexao.close();

        } catch (IOException e) {
            //criar arquivo para salvar os erros 
        }

    }

    private void criarClienteAula() {
        try {
            // O parametro é que indica se deve sobrescrever ou continua no
            // arquivo.
            FileWriter fw = new FileWriter("tabelas/clienteaula.csv");
            BufferedWriter conexao = new BufferedWriter(fw);
            conexao.write("codAula,codCliente,presenca");
            conexao.newLine();
            conexao.close();

        } catch (IOException e) {
            //criar arquivo para salvar os erros 
        }

    }

    private void criarClienteExame() {
        try {
            // O parametro é que indica se deve sobrescrever ou continua no
            // arquivo.
            FileWriter fw = new FileWriter("tabelas/clienteexame.csv");
            BufferedWriter conexao = new BufferedWriter(fw);
            conexao.write("codEndereco,num,cidade,estado,logradouro,bairro,cep,status");
            conexao.newLine();
            conexao.close();

        } catch (IOException e) {
            //criar arquivo para salvar os erros 
        }
    }

    private void criarEndereco() {
        try {
            // O parametro é que indica se deve sobrescrever ou continua no
            // arquivo.
            FileWriter fw = new FileWriter("tabelas/endereco.csv");
            BufferedWriter conexao = new BufferedWriter(fw);
            conexao.write("codEndereco,num,cidade,estado,logradouro,bairro,cep,status");
            conexao.newLine();
            conexao.close();

        } catch (IOException e) {
            //criar arquivo para salvar os erros 
        }

    }

    private void exame() {

        try {
            // O parametro é que indica se deve sobrescrever ou continua no
            // arquivo.
            FileWriter fw = new FileWriter("tabelas/exame.csv");
            BufferedWriter conexao = new BufferedWriter(fw);
            conexao.write("codExame,dataExame,horaInicio,horaFim,codVeiculo,codInstrutor");
            conexao.newLine();
            conexao.close();

        } catch (IOException e) {
            //criar arquivo para salvar os erros 
        }
    }

    private void criarFuncionario() {

        try {
            // O parametro é que indica se deve sobrescrever ou continua no
            // arquivo.
            FileWriter fw = new FileWriter("tabelas/funcionario.csv");
            BufferedWriter conexao = new BufferedWriter(fw);
            conexao.write("codigo,codEndereco,rg,nome,cpf,dataNasc,telefone,celular,horaEntra,horaSai,tipo,numero da carteira,categoria,status");
            conexao.newLine();
            conexao.close();

        } catch (IOException e) {
            //criar arquivo para salvar os erros 
        }
    }

    private void criarVeiculo() {

        try {
            // O parametro é que indica se deve sobrescrever ou continua no
            // arquivo.
            FileWriter fw = new FileWriter("tabelas/veiculo.csv");
            BufferedWriter conexao = new BufferedWriter(fw);
            conexao.write("codigo do veiculo,placa,ano,modelo,capacidade,status");
            conexao.newLine();
            conexao.close();

        } catch (IOException e) {
            //criar arquivo para salvar os erros 
        }

    }

    private void criarPasta() {

        File theDir = new File("tabelas");

// if the directory does not exist, create it
        if (!theDir.exists()) {
            try {
                theDir.mkdirs();
            } catch (SecurityException se) {
                //handle it
            }
           
        }

    }

    protected void inicializarArquivos() {
        criarPasta();
        criarUsuario();
        criarAula();
        criarCliente();
        criarClienteAula();
        criarClienteExame();
        criarEndereco();
        exame();
        criarFuncionario();
        criarVeiculo();
    }
}
