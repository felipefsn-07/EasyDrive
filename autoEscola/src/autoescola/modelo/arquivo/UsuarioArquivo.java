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
public class UsuarioArquivo extends Arquivo {

    private final String tabela = "tabelas/usuario.csv";

    /**
     * Consulta e retorna todos os usuarios cadastrados
     *
     * @return ArrayList of Usuario
     */
    public ArrayList<Usuario> consultarUsuarios() {
        File arquivoCSV = new File(tabela);
        ArrayList<Usuario> usuarios = new ArrayList();
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
                    Usuario usuario = new Usuario();
                    usuario.setCodLogin(parseInt(valoresEntreVirgulas[0]));
                    usuario.setLogin(valoresEntreVirgulas[1]);
                    usuario.setSenha(valoresEntreVirgulas[2]);
                    Funcionario funcionario = new Funcionario();
                    funcionario.setCodigoFuncionario(parseInt(valoresEntreVirgulas[3]));
                    usuario.setFucionario(funcionario);
                    usuario.setStatus(parseInt(valoresEntreVirgulas[4]));

                    usuarios.add(usuario);

                }
            }
            return usuarios;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    /**
     * Consultar usuario a partir do código do usuario
     *
     * @param codUsuario
     * @return the Usuario
     */
    @Override
    public Usuario consultar(int codUsuario) {
        File arquivoCSV = new File(tabela);
        Usuario usuario = new Usuario();
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
                if (parseInt(valoresEntreVirgulas[0]) == codUsuario) {
                    usuario.setCodLogin(parseInt(valoresEntreVirgulas[0]));
                    usuario.setLogin(valoresEntreVirgulas[1]);
                    usuario.setSenha(valoresEntreVirgulas[2]);
                    Funcionario funcionario = new Funcionario();
                    funcionario.setCodigoFuncionario(parseInt(valoresEntreVirgulas[3]));
                    usuario.setFucionario(funcionario);
                    usuario.setStatus(parseInt(valoresEntreVirgulas[4]));

                }
            }
            return usuario;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    public Usuario consultarFuncionarioUsuario (int codigoFuncionario) {
        File arquivoCSV = new File(tabela);
        Usuario usuario = new Usuario();
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
                if (parseInt(valoresEntreVirgulas[3]) == codigoFuncionario) {
                    usuario.setCodLogin(parseInt(valoresEntreVirgulas[0]));
                    usuario.setLogin(valoresEntreVirgulas[1]);
                    usuario.setSenha(valoresEntreVirgulas[2]);
                    Funcionario funcionario = new Funcionario();
                    funcionario.setCodigoFuncionario(parseInt(valoresEntreVirgulas[3]));
                    usuario.setFucionario(funcionario);
                    usuario.setStatus(parseInt(valoresEntreVirgulas[4]));

                }
            }
            return usuario;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }
    
    public int consultarUsuario(String login, String senha, int tipo) {

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
                if (tipo == 1) {
                    if (valoresEntreVirgulas[1].equals(login) && valoresEntreVirgulas[2].equals(senha)) {
                        return parseInt(valoresEntreVirgulas[0]);

                    }
                } else if (tipo == 2 && valoresEntreVirgulas[1].equals(login)) {
                    return 0;

                }
            }
            return 0;

        } catch (FileNotFoundException e) {
            //log de erro
            return 0;

        }
    }

    /**
     * Desativar usuario a partir do código do usuario
     *
     * @param codUsuario
     * @return false or true
     */
    @Override
    public boolean desativar(int codUsuario) {
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
                if (parseInt(valoresEntreVirgulas[0]) == codUsuario) {
                    linhasDoArquivo = valoresEntreVirgulas[0] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[1] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[2] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[3] + ",";
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

       public boolean ativar(int codUsuario) {
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
                if (parseInt(valoresEntreVirgulas[0]) == codUsuario) {
                    linhasDoArquivo = valoresEntreVirgulas[0] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[1] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[2] + ",";
                    linhasDoArquivo += valoresEntreVirgulas[3] + ",";
                    linhasDoArquivo += "1";

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
     * Alterar o usuario
     *
     * @param usuario
     * @return false or true
     */
    public boolean alterarUsuario(Usuario usuario) {
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
                if (parseInt(valoresEntreVirgulas[0]) == usuario.getCodLogin()) {
                    linhasDoArquivo = String.valueOf(usuario.getCodLogin()) + ",";
                    linhasDoArquivo += usuario.getLogin() + ",";
                    linhasDoArquivo += usuario.getSenha() + ",";
                    linhasDoArquivo += String.valueOf(usuario.getFucionario().getCodigoFuncionario()) + ",";

                    linhasDoArquivo += String.valueOf(usuario.getStatus());

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
     * Cadastrar usuario
     *
     * @param usuario
     * @return false or true
     */
    public int cadastrarUsuario(Usuario usuario) {

        int codUsuario = autoIncremento(tabela);
        try {
            // O parametro é que indica se deve sobrescrever ou continua no
            // arquivo.
            FileWriter fw = new FileWriter(tabela, true);
            BufferedWriter conexao = new BufferedWriter(fw);
            if (codUsuario != 0) {
                conexao.write(String.valueOf(codUsuario));
                conexao.write(',');
                conexao.write(usuario.getLogin());
                conexao.write(',');
                conexao.write(usuario.getSenha());
                conexao.write(',');
                conexao.write(usuario.getFucionario().getCodigoFuncionario());
                conexao.write(',');
                conexao.write(String.valueOf(usuario.getStatus()));
                conexao.newLine();
                conexao.close();

                return codUsuario;
            } else {
                //msg erro no incremento codUsuario == 0

                return 0;
            }

        } catch (IOException e) {
            //criar arquivo para salvar os erros 
            return 0;
        }
    }

    /**
     * Função para consultar usuario a partir do campo e um valor que pode ser
     * encontrado nessa coluna
     *
     * @param campo
     * @param valor
     * @return the ArrayList of Usuario
     */
    public ArrayList<Usuario> consultarUsuariosLike(String campo, String valor) {
        File arquivoCSV = new File(tabela);
        ArrayList<Usuario> usuarios = new ArrayList();
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
                    Usuario usuario = new Usuario();
                    usuario.setCodLogin(parseInt(valoresEntreVirgulas[0]));
                    usuario.setLogin(valoresEntreVirgulas[1]);
                    usuario.setSenha(valoresEntreVirgulas[2]);
                    Funcionario funcionario = new Funcionario();
                    funcionario.setCodigoFuncionario(parseInt(valoresEntreVirgulas[3]));
                    usuario.setFucionario(funcionario);
                    usuario.setStatus(parseInt(valoresEntreVirgulas[4]));

                    usuarios.add(usuario);

                }
            }
            return usuarios;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

}
