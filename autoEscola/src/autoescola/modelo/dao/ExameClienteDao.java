/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.connection.ConnectionFactory;
import autoescola.modelo.bean.Cliente;
import autoescola.modelo.bean.Exame;
import autoescola.modelo.bean.ExameClientes;
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
public class ExameClienteDao {

    public boolean cadastrarExameCliente(ExameClientes exameCli) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO clienteexame (codExame, codCliente) VALUES(?, ?)");
            stmt.setInt(1, exameCli.getExame().getCodigoExame());
            stmt.setInt(2, exameCli.getCliente().getCodCliente());

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public ArrayList<ExameClientes> consultarExameClientes() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<ExameClientes> exameClientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM clienteexame");
            rs = stmt.executeQuery();

            while (rs.next()) {
                ExameClientes exameCliente = new ExameClientes();
                Exame exame = new Exame();
                exame.setCodigoExame(rs.getInt("codExame"));
                exameCliente.setExame(exame);
                Cliente cliente = new Cliente();
                cliente.setCodCliente(rs.getInt("codCliente"));
                exameCliente.setCliente(cliente);
                exameClientes.add(exameCliente);
            }
            return exameClientes;
        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public ArrayList<ExameClientes> consultarExamePorClientes(int codCliente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<ExameClientes> exames = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * FROM clienteexame WHERE codCliente = ?");
            stmt.setInt(1, codCliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ExameClientes exameCliente = new ExameClientes();
                ExameDao ed = new ExameDao();
                Exame exame = ed.consutarExameExiste(rs.getInt("codExame"));
                exameCliente.setExame(exame);
                Cliente cliente = new Cliente();
                cliente.setCodCliente(rs.getInt("codCliente"));
                exameCliente.setCliente(cliente);
                exames.add(exameCliente);

            }
            return exames;
        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public ArrayList<ExameClientes> consultarClientesPorExame(int codExame) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<ExameClientes> clientes = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * FROM clienteexame WHERE codExame = ?");
            stmt.setInt(1, codExame);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ExameClientes exameCliente = new ExameClientes();
                Exame exame = new Exame();
                exame.setCodigoExame(rs.getInt("codExame"));

                exameCliente.setExame(exame);

                CliDao cli = new CliDao();
                Cliente cliente = cli.consutarClienteExiste(rs.getInt("codCliente"));
                exameCliente.setCliente(cliente);

                clientes.add(exameCliente);
            }
            return clientes;
        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public boolean alterarExameClientes(ExameClientes exameCliente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE clienteaula SET codExame = ?, codCliente = ? WHERE codExame = ? && codCliente = ?");
            stmt.setInt(1, exameCliente.getExame().getCodigoExame());
            stmt.setInt(2, exameCliente.getCliente().getCodCliente());
            stmt.setInt(3, exameCliente.getExame().getCodigoExame());
            stmt.setInt(4, exameCliente.getCliente().getCodCliente());
            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public ArrayList<ExameClientes> consultarExameClientesLike(String campo, String valor) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<ExameClientes> exameClientes = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * FROM clienteexame WHERE ? LIKE ?");
            stmt.setString(1, campo);
            stmt.setString(2, "%" + valor + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                ExameClientes exameCliente = new ExameClientes();
                Exame exame = new Exame();
                exame.setCodigoExame(rs.getInt("codExame"));
                exameCliente.setExame(exame);
                Cliente cliente = new Cliente();
                cliente.setCodCliente(rs.getInt("codCliente"));
                exameCliente.setCliente(cliente);
                exameClientes.add(exameCliente);
            }
            return exameClientes;

        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public boolean apagarAlunosExame(ExameClientes cliente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM clienteaula WHERE codExame = ? && codCliente = ?");
            stmt.setInt(1, cliente.getExame().getCodigoExame());
            stmt.setInt(2, cliente.getCliente().getCodCliente());
            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean apagarExame(int codigoExame){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM clienteaula WHERE codExame = ?");
            stmt.setInt(1, codigoExame);
            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<Cliente> trazerClientes(int codExame){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Cliente> clientes = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * FROM clienteexame WHERE codExame = ?");
            stmt.setInt(1, codExame);
            rs = stmt.executeQuery();

            while (rs.next()) {
                CliDao c = new CliDao();
                Cliente cliente = c.consutarClienteExiste(codExame);

                clientes.add(cliente);
            }
            return clientes;

        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
