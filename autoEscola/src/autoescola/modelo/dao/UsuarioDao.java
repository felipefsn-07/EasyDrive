/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.connection.ConnectionFactory;
import autoescola.modelo.bean.Funcionario;
import autoescola.modelo.bean.Usuario;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucca
 */
public class UsuarioDao {

    public int cadastrarUsuario(Usuario user) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "INSERT INTO login (login, senha, status, codFunc) VALUES(?, ?, ?, ?)";
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getSenha());
            stmt.setInt(3, 0);
            stmt.setInt(4, user.getFucionario().getCodigoFuncionario());

            stmt.executeUpdate();

            final ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar! " + ex);
            return 0;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Usuario> consultarUsuarios() {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Usuario> users = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM login");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario user = new Usuario();

                user.setCodLogin(rs.getInt("codLogin"));
                user.setLogin(rs.getString("login"));
                user.setSenha(rs.getString("senha"));
                user.setStatus(rs.getInt("status"));

                users.add(user);
            }
            return users;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar! " + ex);
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public Usuario consultarUsuario(int codUsuario) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM login WHERE codLogin = ?");
            stmt.setInt(1, codUsuario);
            rs = stmt.executeQuery();
            Usuario user = new Usuario();

            while (rs.next()) {
                user.setCodLogin(rs.getInt("codLogin"));
                user.setLogin(rs.getString("login"));
                user.setSenha(rs.getString("senha"));
                user.setStatus(rs.getInt("status"));
                Funcionario funcionario = new Funcionario();
                funcionario.setCodigoFuncionario(rs.getInt("codFunc"));
                user.setFucionario(funcionario);
            }
            return user;

        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public Usuario consultarFuncionarioUsuario (int codigoFuncionario){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM login WHERE codFunc = ?");
            stmt.setInt(1, codigoFuncionario);
            rs = stmt.executeQuery();
            Usuario user = new Usuario();

            while (rs.next()) {
                user.setCodLogin(rs.getInt("codLogin"));
                user.setLogin(rs.getString("login"));
                user.setSenha(rs.getString("senha"));
                user.setStatus(rs.getInt("status"));
                
                Funcionario funcionario = new Funcionario();
                funcionario.setCodigoFuncionario(rs.getInt("codFunc"));
                user.setFucionario(funcionario);
            }
            return user;

        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public boolean alterarUsuario(Usuario user) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE login SET login = ?, senha = ? WHERE codLogin = ?");
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getSenha());
            stmt.setInt(3, user.getCodLogin());

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean desativar(int codUsuario) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE login SET status = 0 WHERE codLogin = ?");
            stmt.setInt(1, codUsuario);

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean ativar(int codUsuario) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE login SET status = 1 WHERE codLogin = ?");
            stmt.setInt(1, codUsuario);

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public int consultarUsuarioExiste(String login, String senha) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM login WHERE login = ? && senha = ?");
            stmt.setString(1, login);
            stmt.setString(2, senha);
            Usuario user = new Usuario();
            rs = stmt.executeQuery();

            while (rs.next()) {
                user.setCodLogin(rs.getInt("codLogin"));
                user.setLogin(rs.getString("login"));
                user.setSenha(rs.getString("senha"));
                user.setStatus(rs.getInt("status"));
            }
            return user.getCodLogin();
            //return 0;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos! " + ex);
            return 0;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public ArrayList<Usuario> consultarUsuariosLike(String campo, String valor){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Usuario> users = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * FROM login WHERE ? LIKE ?");
            stmt.setString(1, campo);
            stmt.setString(2, "%" + valor + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario user = new Usuario();

                user.setCodLogin(rs.getInt("codLogin"));
                user.setLogin(rs.getString("login"));
                user.setSenha(rs.getString("senha"));
                user.setStatus(rs.getInt("status"));
                
                Funcionario funcionario = new Funcionario();
                funcionario.setCodigoFuncionario(rs.getInt("codFunc"));
                user.setFucionario(funcionario);

                users.add(user);
            }
            return users;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar! " + ex);
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
