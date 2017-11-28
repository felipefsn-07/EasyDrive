/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.connection.ConnectionFactory;
import autoescola.modelo.bean.Aula;
import autoescola.modelo.bean.AulasClientes;
import autoescola.modelo.bean.Cliente;
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
public class AulaClienteDao {

    public boolean cadastrarAulaCliente(AulasClientes aulaCli) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO clienteaula (codAula, codCliente, presenca) VALUES(?, ?, ?)");
            stmt.setInt(1, aulaCli.getAulas().getCodAulas());
            stmt.setInt(2, aulaCli.getAluno().getCodCliente());
            stmt.setBoolean(3, aulaCli.isPresenca());

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public ArrayList<AulasClientes> consultarAulasClientes(int codigoAula) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<AulasClientes> aulaClientes = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * FROM clienteaula WHERE codAula = ?");
            stmt.setInt(1, codigoAula);
            rs = stmt.executeQuery();

            while (rs.next()) {
                AulasClientes aulaCliente = new AulasClientes();
                Aula aula = new Aula();

                aula.setCodAulas(rs.getInt("codAula"));
                aulaCliente.setAulas(aula);

                CliDao c = new CliDao();
                aulaCliente.setAluno(c.consutarClienteExiste(rs.getInt("codCliente")));

                aulaCliente.setPresenca(rs.getBoolean("presenca"));
                aulaClientes.add(aulaCliente);
            }

            return aulaClientes;

        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public ArrayList<AulasClientes> consultarClientesAula(int codCliente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<AulasClientes> aulaClientes = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * FROM clienteaula WHERE codCliente = ?");
            stmt.setInt(1, codCliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                AulasClientes aulaCliente = new AulasClientes();
                AulaDao ad = new AulaDao();

                Aula aula = ad.consutarAulaExiste(rs.getInt("codAula"));
                aulaCliente.setAulas(aula);

                Cliente cliente = new Cliente();
                cliente.setCodCliente(rs.getInt("codCliente"));

                aulaCliente.setPresenca(rs.getBoolean("presenca"));
                aulaCliente.setAluno(cliente);
                aulaClientes.add(aulaCliente);
            }

            return aulaClientes;

        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public boolean alterarAulasClientes(AulasClientes aulaCliente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE clienteaula SET codAula = ?, codCliente = ?, presenca = ? WHERE codAula = ? && codCliente = ?");
            stmt.setInt(1, aulaCliente.getAulas().getCodAulas());
            stmt.setInt(2, aulaCliente.getAluno().getCodCliente());
            stmt.setBoolean(3, aulaCliente.isPresenca());
            stmt.setInt(4, aulaCliente.getAulas().getCodAulas());
            stmt.setInt(5, aulaCliente.getAluno().getCodCliente());

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public ArrayList<AulasClientes> consultarAulasClientesLike(String campo, String valor) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<AulasClientes> aulas = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * FROM clienteaula WHERE ? LIKE ?");
            stmt.setString(1, campo);
            stmt.setString(2, "%" + valor + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                AulasClientes aulaCliente = new AulasClientes();
                Aula aula = new Aula();

                aula.setCodAulas(rs.getInt("codAula"));
                aulaCliente.setAulas(aula);

                Cliente cliente = new Cliente();
                cliente.setCodCliente(rs.getInt("codCliente"));
                aulaCliente.setAluno(cliente);

                aulaCliente.setPresenca(rs.getBoolean("presenca"));
                aulas.add(aulaCliente);
            }
            return aulas;

        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public boolean apagarAula(int codigoAula) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM clienteaula WHERE codAula = ?");
            stmt.setInt(1, codigoAula);

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public ArrayList<Cliente> trazerClientes(int codigoAula) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Cliente> clientes = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * FROM clienteaula WHERE codAula = ?");
            stmt.setInt(1, codigoAula);
            rs = stmt.executeQuery();

            while (rs.next()) {
                CliDao c = new CliDao();
                Cliente cliente = c.consutarClienteExiste(codigoAula);

                clientes.add(cliente);
            }
            return clientes;

        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public boolean presenca(AulasClientes aulasClientes){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE clienteaula SET codAula = ?, codCliente = ?, presenca = ? WHERE codAula = ? && codCliente = ?");
            stmt.setInt(1, aulasClientes.getAulas().getCodAulas());
            stmt.setInt(2, aulasClientes.getAluno().getCodCliente());
            stmt.setBoolean(3, aulasClientes.isPresenca());
            stmt.setInt(4, aulasClientes.getAulas().getCodAulas());
            stmt.setInt(5, aulasClientes.getAluno().getCodCliente());

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
