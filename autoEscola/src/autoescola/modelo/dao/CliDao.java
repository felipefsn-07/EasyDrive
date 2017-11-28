/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.connection.ConnectionFactory;
import autoescola.modelo.bean.Cliente;
import autoescola.modelo.bean.Endereco;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author felipe
 */
public class CliDao {

    //private Cliente cli;
    public int cadastrarCliente(Cliente cli) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "INSERT INTO cliente (nome, tel, cel, dataNasc, rg, cpf, numLadv, status, categoria) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, cli.getNome());
            stmt.setString(2, cli.getTelefone());
            stmt.setString(3, cli.getCelular());
            stmt.setString(4, cli.getDatanasc());
            stmt.setString(5, cli.getRg());
            stmt.setString(6, cli.getCpf());
            stmt.setString(7, cli.getNumLADV());
            stmt.setBoolean(8, cli.getStatus());
            stmt.setString(9, cli.getCategoria());

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

    public ArrayList<Cliente> consultarClientes() {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cli = new Cliente();

                cli.setCodCliente(rs.getInt("codCliente"));
                cli.setNome(rs.getString("nome"));
                cli.setTelefone(rs.getString("tel"));
                cli.setCelular(rs.getString("cel"));
                cli.setDatanasc(rs.getString("dataNasc"));
                cli.setRg(rs.getString("rg"));
                cli.setCpf(rs.getString("cpf"));
                cli.setNumLADV(rs.getString("numLadv"));
                cli.setStatus(rs.getBoolean("status"));
                cli.setCategoria(rs.getString("categoria"));

                EnderecoDao endDao = new EnderecoDao();
                Endereco endereco = endDao.consultar(rs.getInt("codEndereco"));
                cli.setEndereco(endereco);

                clientes.add(cli);
            }
            return clientes;
        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public ArrayList<Cliente> consultarClientesAtivos() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE status = 1");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cli = new Cliente();

                cli.setCodCliente(rs.getInt("codCliente"));
                cli.setNome(rs.getString("nome"));
                cli.setTelefone(rs.getString("tel"));
                cli.setCelular(rs.getString("cel"));
                cli.setDatanasc(rs.getString("dataNasc"));
                cli.setRg(rs.getString("rg"));
                cli.setCpf(rs.getString("cpf"));
                cli.setNumLADV(rs.getString("numLadv"));
                cli.setStatus(rs.getBoolean("status"));
                cli.setCategoria(rs.getString("categoria"));

                EnderecoDao endDao = new EnderecoDao();
                Endereco endereco = endDao.consultar(rs.getInt("codEndereco"));
                cli.setEndereco(endereco);

                clientes.add(cli);
            }
            return clientes;
        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    /**
     *
     * @param nome
     * @return
     */
    public ArrayList<Cliente> consultarClientesLike(String campo, String valor) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE " + campo + " LIKE ?");
            stmt.setString(1, "%" + valor + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cli = new Cliente();

                cli.setCodCliente(rs.getInt("codCliente"));
                cli.setNome(rs.getString("nome"));
                cli.setTelefone(rs.getString("tel"));
                cli.setCelular(rs.getString("cel"));
                cli.setDatanasc(rs.getString("dataNasc"));
                cli.setRg(rs.getString("rg"));
                cli.setCpf(rs.getString("cpf"));
                cli.setNumLADV(rs.getString("numLadv"));
                cli.setStatus(rs.getBoolean("status"));
                cli.setCategoria(rs.getString("categoria"));

                EnderecoDao endDao = new EnderecoDao();
                Endereco endereco = endDao.consultar(rs.getInt("codEndereco"));
                cli.setEndereco(endereco);

                clientes.add(cli);
            }
            return clientes;
        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public Cliente consutarClienteExiste(int codigoCliente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cli = new Cliente();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente LEFT JOIN endereco ON cliente.codEndereco=endereco.codEndereco WHERE codCliente = ?");
            stmt.setInt(1, codigoCliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                cli.setCodCliente(rs.getInt("codCliente"));
                cli.setNome(rs.getString("nome"));
                cli.setTelefone(rs.getString("tel"));
                cli.setCelular(rs.getString("cel"));
                cli.setDatanasc(rs.getString("dataNasc"));
                cli.setRg(rs.getString("rg"));
                cli.setCpf(rs.getString("cpf"));
                cli.setNumLADV(rs.getString("numLadv"));
                cli.setStatus(rs.getBoolean("status"));
                cli.setCategoria(rs.getString("categoria"));
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
                }else{
                    endereco = null;
                }
                cli.setEndereco(endereco);

            }
            return cli;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar! " + ex);
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
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE rg = ?");
            stmt.setString(1, rg);
            rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException ex) {
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public boolean alterarCliente(Cliente cli) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE cliente SET nome = ?, tel = ?, cel = ?, dataNasc = ?, rg = ?, cpf = ?, numLadv = ?, status = ?, categoria = ?, codEndereco= ? WHERE codCliente = ?");
            stmt.setString(1, cli.getNome());
            stmt.setString(2, cli.getTelefone());
            stmt.setString(3, cli.getCelular());
            stmt.setString(4, cli.getDatanasc());
            stmt.setString(5, cli.getRg());
            stmt.setString(6, cli.getCpf());
            stmt.setString(7, cli.getNumLADV());
            stmt.setBoolean(8, cli.getStatus());
            stmt.setString(9, cli.getCategoria());
            stmt.setInt(10, cli.getEndereco().getCodEndereco());
            stmt.setInt(11, cli.getCodCliente());
            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean desativar(int codigoCliente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE cliente SET status = 0 WHERE codCliente = ?");
            stmt.setInt(1, codigoCliente);

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean ativar(int codigoCliente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE cliente SET status = 1 WHERE codCliente = ?");
            stmt.setInt(1, codigoCliente);

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
