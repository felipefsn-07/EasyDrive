/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.connection.ConnectionFactory;
import autoescola.modelo.bean.Aula;
import autoescola.modelo.bean.AulasClientes;
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
            stmt = con.prepareStatement("INSERT INTO clienteaula (codAula, codCliente) VALUES(?, ?)");
            stmt.setInt(1, aulaCli.getAulas().getCodAulas());
            stmt.setInt(2, aulaCli.getAluno().getCodCliente());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
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

                aulaCliente.setPresenca(true);
                aulaClientes.add(aulaCliente);
            }

            return aulaClientes;

        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public boolean apagarAula(int codigoAula) {
        return true;
    }
}
