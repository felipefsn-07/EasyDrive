/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.controleDao;
import autoescola.modelo.bean.Funcionario;
import autoescola.modelo.bean.Usuario;
import autoescola.modelo.dao.FuncionarioDao;
import autoescola.modelo.dao.UsuarioDao;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Lucca
 */
public class ControleLogin {
    private static Usuario usuario;

    /**
     *
     * @param usuario
     */
    protected void setUsuario(Usuario usuario) {

        autoescola.controleDao.ControleLogin.usuario = usuario;

    }
    
    public boolean isGerente(){
        FuncionarioDao dao = new FuncionarioDao();
        Funcionario funcionario = dao.consutarFuncExiste(usuario.getFucionario().getCodigoFuncionario());
        
        return "Gerente".equals(funcionario.getTipo()) || usuario.getFucionario().getCodigoFuncionario()==0;

        
    }

    /**
     * @return the Usuario
     */
    protected Usuario getLogin() {
        return usuario;
    }

    public int verificarLogin(JTextField login, JPasswordField senha) {
        UsuarioDao dao = new UsuarioDao();
        char[] chars = senha.getPassword();
        String password = String.valueOf(chars);
        Usuario user;
        int numUser = dao.consultarUsuarioExiste(login.getText(), password);
        user = dao.consultarUsuario(numUser);
        user.setCodLogin(numUser);
        this.setUsuario(user);
        return numUser;
    }

    public boolean verificarLogado() {
        return autoescola.controleDao.ControleLogin.usuario != null;
    }

    public void sair() {
        autoescola.controleDao.ControleLogin.usuario = null;
    }

    public String nome() {
        Funcionario funcionario;
        if (usuario.getCodLogin() == 1) {
            FuncionarioDao daoFun = new FuncionarioDao();
            funcionario = daoFun.consutarFuncExiste(usuario.getCodLogin());
            if (funcionario != null) {
                return funcionario.getNome();
            } else {
                return ("Primeiro usuário");

            }
        } else {
            FuncionarioDao daoFun = new FuncionarioDao();
            funcionario = daoFun.consutarFuncExiste(usuario.getCodLogin());
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
                    UsuarioDao dao = new UsuarioDao();
                    usuario.setSenha(passwordNew);
                    dao.alterarUsuario(usuario);
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
