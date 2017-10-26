/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.controle;

import autoescola.modelo.arquivo.FuncionarioArquivo;
import autoescola.modelo.arquivo.UsuarioArquivo;
import autoescola.modelo.bean.Funcionario;
import autoescola.modelo.bean.Usuario;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author felipe
 */
public class ControleLogin {

    private static Usuario usuario;

    /**
     *
     * @param usuario
     */
    protected void setUsuario(Usuario usuario) {

        ControleLogin.usuario = usuario;

    }

    /**
     * @return the Usuario
     */
    protected Usuario getLogin() {
        return usuario;
    }

    public int verificarLogin(JTextField login, JPasswordField senha) {
        UsuarioArquivo arq = new UsuarioArquivo();
        char[] chars = senha.getPassword();
        String password = String.valueOf(chars);
        Usuario user;
        int numUser = arq.consultarUsuario(login.getText(), password, 1);
        user = arq.consultar(numUser);
        user.setCodLogin(numUser);
        this.setUsuario(user);
        return numUser;
    }

    public boolean verificarLogado() {
        return ControleLogin.usuario != null;
    }

    public void sair() {
        ControleLogin.usuario = null;
    }

    public String nome() {
        Funcionario funcionario = new Funcionario();
        if (usuario.getCodLogin() == 1) {
            FuncionarioArquivo arqFun = new FuncionarioArquivo();
            funcionario = arqFun.consultar(usuario.getCodLogin());
            if (funcionario != null) {
                return funcionario.getNome();
            } else {
                return ("Primeiro usuário");

            }
        } else {
            FuncionarioArquivo arqFun = new FuncionarioArquivo();
            funcionario = arqFun.consultar(usuario.getCodLogin());
            return funcionario.getNome();

        }
    }

    /**
     *
     * @return the login
     */
    public String login() {

        return usuario.getLogin();

    }

    public void alterarSenhaUsuario(JPasswordField senhaAnterior, JPasswordField senhaNova, JPasswordField confirmarSenha) {
        char[] chars = senhaAnterior.getPassword();
        String password = String.valueOf(chars);
        char[] charsNova = senhaNova.getPassword();
        String passwordNew = String.valueOf(charsNova);
        char[] charsConfirmarSenha = confirmarSenha.getPassword();
        String passwordConfirmarSenha = String.valueOf(charsConfirmarSenha);

        if (password.equals(usuario.getSenha())) {
            if (!"".equals(passwordNew) && !"".equals(passwordConfirmarSenha)) {
                if (passwordNew.equals(passwordConfirmarSenha)) {
                    UsuarioArquivo arq = new UsuarioArquivo();
                    usuario.setSenha(passwordNew);
                    arq.alterarUsuario(usuario);
                    JOptionPane.showMessageDialog(null, "Alterado com sucesso!");

                } else {
                    JOptionPane.showMessageDialog(null, "Senhas não conferem!");

                }
            } else {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

            }
        } else {
            JOptionPane.showMessageDialog(null, "Senha anterior está incorreta!");
        }
    }
}
