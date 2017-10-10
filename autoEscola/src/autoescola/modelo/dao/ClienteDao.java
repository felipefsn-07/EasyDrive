/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.connection.ConnectionFactory;
import autoescola.modelo.bean.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author felipe
 */
public class ClienteDao {

    //private Cliente cli;
    public boolean cadastrarCliente(Cliente cli) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO cliente (nome, tel, cel, dataNasc, rg, cpf, numLadv, status, categoria) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, cli.getNome());
            stmt.setString(2, cli.getTelefone());
            stmt.setString(3, cli.getCelular());
            stmt.setString(4, cli.getDatanasc());
            stmt.setString(5, cli.getRg());
            stmt.setString(6, cli.getCpf());
            stmt.setString(7, cli.getNumLADV());
            stmt.setInt(8, cli.getStatus());
            stmt.setString(9, cli.getCategoria());

            stmt.executeUpdate();

            //stmt = con.prepareStatement("INSERT INTO endereco (num, cidade, estado, logradouro, bairro, cep, codCliente) VALUES(?, ?, ?, ?, ?, ?, ?)");
            //stmt = con.prepareStatement("INSERT INTO endereco (num, cidade, estado, logradouro, bairro, cep, codCliente) VALUES('480', 'carapicuiba', 'SP', 'Avenida tamara', 'centro')");
            //stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Cliente> consultarClientes() {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cli = new Cliente();

                cli.getCodCliente();
                cli.setNome(rs.getString("nome"));
                cli.setTelefone(rs.getString("tel"));
                cli.setCelular(rs.getString("cel"));
                cli.setDatanasc(rs.getString("dataNasc"));
                cli.setRg(rs.getString("rg"));
                cli.setCpf(rs.getString("cpf"));
                cli.setNumLADV(rs.getString("numLadv"));
                cli.setStatus(rs.getInt("status"));
                cli.setCategoria(rs.getString("categoria"));

                clientes.add(cli);
            }
            JOptionPane.showMessageDialog(null, "Consulta concluida!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar! " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return clientes;
    }

    /**
     *
     * @param nome
     * @return
     */
    public List<Cliente> consultarClientesLike(String nome) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE nome LIKE ?");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cli = new Cliente();

                cli.getCodCliente();
                cli.setNome(rs.getString("nome"));
                cli.setTelefone(rs.getString("tel"));
                cli.setCelular(rs.getString("cel"));
                cli.setDatanasc(rs.getString("dataNasc"));
                cli.setRg(rs.getString("rg"));
                cli.setCpf(rs.getString("cpf"));
                cli.setNumLADV(rs.getString("numLadv"));
                cli.setStatus(rs.getInt("status"));
                cli.setCategoria(rs.getString("categoria"));

                clientes.add(cli);
            }
            JOptionPane.showMessageDialog(null, "Consulta concluida!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar! " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return clientes;
    }

    public boolean consutarClienteExiste(String cpf) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE cpf = ?");
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();

            while (rs.next()) {
                JOptionPane.showMessageDialog(null, "Consulta concluida!");
                return true;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar! " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return false;
    }

    public boolean alterarCliente(Cliente cli) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE cliente SET nome = ?, tel = ?, cel = ?, dataNasc = ?, rg = ?, cpf = ?, numLadv = ?, status = ?, categoria = ? WHERE codCliente = ?");
            stmt.setString(1, cli.getNome());
            stmt.setString(2, cli.getTelefone());
            stmt.setString(3, cli.getCelular());
            stmt.setString(4, cli.getDatanasc());
            stmt.setString(5, cli.getRg());
            stmt.setString(6, cli.getCpf());
            stmt.setString(7, cli.getNumLADV());
            stmt.setInt(8, cli.getStatus());
            stmt.setString(9, cli.getCategoria());
            stmt.setInt(10, cli.getCodCliente());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean excluirCliente(Cliente cli) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM cliente WHERE codCliente = ?");
            stmt.setInt(1, cli.getCodCliente());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
