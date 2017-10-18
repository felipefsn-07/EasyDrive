/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.connection.ConnectionFactory;
import autoescola.modelo.bean.AulasClientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}
