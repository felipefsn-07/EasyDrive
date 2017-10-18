/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.connection.ConnectionFactory;
import autoescola.modelo.bean.Recepcionista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucca
 */
public class RecepcionistaDao {
    public boolean cadastrarRecepcionista(Recepcionista recep) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO recepcionista (codFunc) VALUES(?)");
            stmt.setInt(1, recep.getCodigoFuncionario());

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
