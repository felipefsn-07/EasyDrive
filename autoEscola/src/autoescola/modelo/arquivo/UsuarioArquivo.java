/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.arquivo;

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
                    usuario.setStatus(parseInt(valoresEntreVirgulas[3]));
                    usuarios.add(usuario);

                }
            }
            return usuarios;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    public Usuario consultarUsuario(int codUsuario) {
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
                    usuario.setStatus(parseInt(valoresEntreVirgulas[3]));

                }
            }
            return usuario;

        } catch (FileNotFoundException e) {
            //log de erro
            return null;

        }
    }

    public boolean desativarUsuario(int codUsuario) {
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
                    linhasDoArquivo += usuario.getLogin()+ ",";
                    linhasDoArquivo += usuario.getSenha()+ ",";
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

    public boolean cadastrarUsuario(Usuario usuario) {

        int codUsuario = autoIncremento(tabela);
        try {
            // O parametro é que indica se deve sobrescrever ou continua no
            // arquivo.
            FileWriter fw = new FileWriter(tabela, true);
            BufferedWriter conexao = new BufferedWriter(fw);
            if (codUsuario != 0) {
                conexao.write(String.valueOf(codUsuario));
                conexao.write(',');
                conexao.write(usuario.getCodLogin());
                conexao.write(',');
                conexao.write(usuario.getLogin());
                conexao.write(',');
                conexao.write(usuario.getSenha());
                conexao.write(',');                
                conexao.write(String.valueOf(usuario.getStatus()));
                conexao.newLine();
                conexao.close();

                return true;
            } else {
                //msg erro no incremento codUsuario == 0
                return false;
            }

        } catch (IOException e) {
            //criar arquivo para salvar os erros 
            return false;
        }
    }

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
                    usuario.setStatus(parseInt(valoresEntreVirgulas[3]));

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
