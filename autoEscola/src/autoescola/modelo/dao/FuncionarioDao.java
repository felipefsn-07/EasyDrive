/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.connection.ConnectionFactory;
import autoescola.modelo.bean.Endereco;
import autoescola.modelo.bean.Funcionario;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucca
 */
public class FuncionarioDao {

    public int cadastrarFuncionario(Funcionario func) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "INSERT INTO funcionario (rg, nome, cpf, dataNasc, tel, cel, horaEntra, horaSai, status, tipo, carteira, categoria) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, func.getRg());
            stmt.setString(2, func.getNome());
            stmt.setString(3, func.getCpf());
            stmt.setString(4, func.getDatanasc());
            stmt.setString(5, func.getTelefone());
            stmt.setString(6, func.getCelular());
            stmt.setString(7, func.getHora_entra());
            stmt.setString(8, func.getHora_sai());
            stmt.setBoolean(9, true);
            stmt.setString(10, func.getTipo());
            stmt.setString(11, func.getNumCarteira());
            stmt.setString(12, func.getCategoria());

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

    public ArrayList<Funcionario> consultarFuncionarios() {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Funcionario> funcs = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario func = new Funcionario();

                func.setCodigoFuncionario(rs.getInt("codFunc"));
                func.setRg(rs.getString("rg"));
                func.setNome(rs.getString("nome"));
                func.setCpf(rs.getString("cpf"));
                func.setDatanasc(rs.getString("dataNasc"));
                func.setTelefone(rs.getString("tel"));
                func.setCelular(rs.getString("cel"));
                func.setHora_entra(rs.getString("horaEntra"));
                func.setHora_sai(rs.getString("horaSai"));
                func.setStatus(rs.getBoolean("status"));
                func.setTipo(rs.getString("tipo"));
                func.setNumCarteira(rs.getString("carteira"));
                func.setCategoria(rs.getString("categoria"));

                EnderecoDao endDao = new EnderecoDao();
                Endereco endereco = endDao.consultar(rs.getInt("codEndereco"));
                func.setEndereco(endereco);

                funcs.add(func);
            }

            return funcs;

        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public Funcionario consutarFuncExiste(int codFunc) {
        Connection con = ConnectionFactory.getConnection();
        Funcionario func = new Funcionario();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario LEFT JOIN endereco ON funcionario.codEndereco = endereco.codEndereco WHERE codFunc = ?");
            stmt.setInt(1, codFunc);
            rs = stmt.executeQuery();

            while (rs.next()) {
                func.setCodigoFuncionario(rs.getInt("codFunc"));
                func.setRg(rs.getString("rg"));
                func.setNome(rs.getString("nome"));
                func.setCpf(rs.getString("cpf"));
                func.setDatanasc(rs.getString("dataNasc"));
                func.setTelefone(rs.getString("tel"));
                func.setCelular(rs.getString("cel"));
                func.setHora_entra(rs.getString("horaEntra"));
                func.setHora_sai(rs.getString("horaSai"));
                func.setStatus(rs.getBoolean("status"));
                func.setTipo(rs.getString("tipo"));
                func.setNumCarteira(rs.getString("carteira"));
                func.setCategoria(rs.getString("categoria"));
                Endereco endereco = new Endereco();
                if (rs.getString("num") != null) {
                    endereco.setCodEndereco(rs.getInt("codEndereco"));
                    endereco.setNum(rs.getString("num"));
                    endereco.setCidade(rs.getString("cidade"));
                    endereco.setEstado(rs.getString("estado"));
                    endereco.setLogradouro(rs.getString("logradouro"));
                    endereco.setBairro(rs.getString("bairro"));
                    endereco.setCep(rs.getString("cep"));
                    endereco.setStatus(rs.getInt("status"));
                } else {
                    endereco = null;
                }
                func.setEndereco(endereco);

            }
            return func;
        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public boolean alterarFunc(Funcionario func) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE funcionario SET rg = ?, nome = ?, cpf = ?, dataNasc = ?, tel = ?, cel =  ?, horaEntra = ?, horaSai = ?, status = ?, tipo = ?, carteira = ?, categoria = ?, codEndereco = ? WHERE codFunc = ?");
            stmt.setString(1, func.getRg());
            stmt.setString(2, func.getNome());
            stmt.setString(3, func.getCpf());
            stmt.setString(4, func.getDatanasc());
            stmt.setString(5, func.getTelefone());
            stmt.setString(6, func.getCelular());
            stmt.setString(7, func.getHora_entra());
            stmt.setString(8, func.getHora_sai());
            stmt.setBoolean(9, func.getStatus());
            stmt.setString(10, func.getTipo());
            stmt.setString(11, func.getNumCarteira());
            stmt.setString(12, func.getCategoria());
            stmt.setInt(13, func.getEndereco().getCodEndereco());
            stmt.setInt(14, func.getCodigoFuncionario());

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean desativar(int codFunc) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE funcionario SET status = 0 WHERE codFunc = ?");
            stmt.setInt(1, codFunc);

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean ativar(int codFunc) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE funcionario SET status = 1 WHERE codFunc = ?");
            stmt.setInt(1, codFunc);

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ativar! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public ArrayList<Funcionario> consultarInstrutoresAtivos() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Funcionario> funcs = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario WHERE tipo = Instrutor && status = 1");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario func = new Funcionario();

                func.setCodigoFuncionario(rs.getInt("codFunc"));
                func.setRg(rs.getString("rg"));
                func.setNome(rs.getString("nome"));
                func.setCpf(rs.getString("cpf"));
                func.setDatanasc(rs.getString("dataNasc"));
                func.setTelefone(rs.getString("tel"));
                func.setCelular(rs.getString("cel"));
                func.setHora_entra(rs.getString("horaEntra"));
                func.setHora_sai(rs.getString("horaSai"));
                func.setStatus(rs.getBoolean("status"));
                func.setTipo(rs.getString("tipo"));
                func.setNumCarteira(rs.getString("carteira"));
                func.setCategoria(rs.getString("categoria"));

                EnderecoDao endDao = new EnderecoDao();
                Endereco endereco = endDao.consultar(rs.getInt("codEndereco"));
                func.setEndereco(endereco);

                funcs.add(func);
            }
            return funcs;

        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public boolean consultarRg(String rg) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario WHERE rg = ?");
            stmt.setString(1, rg);
            rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException ex) {
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public ArrayList<Funcionario> consultarfuncionariosLike(String campo, String valor) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Funcionario> funcs = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario WHERE " + campo + " LIKE ?");
            stmt.setString(1, "%" + valor + "%");
            rs = stmt.executeQuery();

            //System.out.println(stmt.)
            while (rs.next()) {

                Funcionario func = new Funcionario();
                func.setCodigoFuncionario(rs.getInt("codFunc"));
                func.setRg(rs.getString("rg"));
                func.setNome(rs.getString("nome"));
                func.setCpf(rs.getString("cpf"));
                func.setDatanasc(rs.getString("dataNasc"));
                func.setTelefone(rs.getString("tel"));
                func.setCelular(rs.getString("cel"));
                func.setHora_entra(rs.getString("horaEntra"));
                func.setHora_sai(rs.getString("horaSai"));
                func.setStatus(rs.getBoolean("status"));
                func.setTipo(rs.getString("tipo"));
                func.setNumCarteira(rs.getString("carteira"));
                func.setCategoria(rs.getString("categoria"));

                funcs.add(func);
            }

            return funcs;

        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
