/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.connection.ConnectionFactory;
import autoescola.modelo.bean.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucca
 */
public class EnderecoDao {
    public int cadastrarEndereco(Endereco endereco) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO endereco (num, cidade, estado, logradouro, bairro, cep, status) VALUES(?, ?, ?, ?, ?, ?, 1)");
            stmt.setString(1, endereco.getNum());
            stmt.setString(2, endereco.getCidade());
            stmt.setString(3, endereco.getEstado());
            stmt.setString(4, endereco.getLogradouro());
            stmt.setString(5, endereco.getBairro());
            stmt.setString(6, endereco.getCep());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
            return endereco.getCodEndereco();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar! " + ex);
            return 0;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<Endereco> consultarEnderecos() {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Endereco> enderecos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM endereco");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Endereco endereco = new Endereco();

                endereco.setCodEndereco(rs.getInt("codEdereco"));
                endereco.setNum(rs.getString("num"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCep(rs.getString("cep"));
                endereco.setStatus(rs.getInt("status"));

                enderecos.add(endereco);
            }
            return enderecos;
        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public boolean alterarEndereco(Endereco endereco) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE endereco SET num = ?, cidade = ?, estado = ?, logradouro = ?, bairro = ?, cep = ? WHERE codEndereco = ?");
            stmt.setString(1, endereco.getNum());
            stmt.setString(2, endereco.getCidade());
            stmt.setString(3, endereco.getEstado());
            stmt.setString(4, endereco.getLogradouro());
            stmt.setString(5, endereco.getBairro());
            stmt.setString(6, endereco.getCep());
            stmt.setInt(7, endereco.getCodEndereco());

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
    
    public Endereco consultar(int codEndereco){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Endereco endereco = new Endereco();

        try {
            stmt = con.prepareStatement("SELECT * FROM endereco WHERE codEndereco = ?");
            stmt.setInt(1, codEndereco);
            rs = stmt.executeQuery();

            while (rs.next()) {endereco.setCodEndereco(rs.getInt("codEdereco"));
                endereco.setNum(rs.getString("num"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCep(rs.getString("cep"));
                endereco.setStatus(rs.getInt("status"));
            }
            return endereco;
        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public boolean desativar(int codEndereco){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE endereco SET status = 0 WHERE codEndereco = ?");
            stmt.setInt(1, codEndereco);

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Desativado com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao desativar! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<Endereco> consultarEnderecosLike(String campo, String valor){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Endereco> enderecos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM endereco WHERE ? LIKE ?");
            stmt.setString(1, campo);
            stmt.setString(1, "%" + valor + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Endereco endereco = new Endereco();

                endereco.setCodEndereco(rs.getInt("codEdereco"));
                endereco.setNum(rs.getString("num"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCep(rs.getString("cep"));
                endereco.setStatus(rs.getInt("status"));

                enderecos.add(endereco);
            }
            return enderecos;
        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
