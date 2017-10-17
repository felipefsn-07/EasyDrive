/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.connection.ConnectionFactory;
import autoescola.modelo.bean.Instrutor;
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
public class InstrutorDao {
    public boolean cadastrarInstrutor(Instrutor instrutor) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO instrutor (codFunc, numCarteira, categoria) VALUES(?, ?, ?)");
            stmt.setInt(1, instrutor.getCodigoFuncionario());
            stmt.setString(2, instrutor.getNumCarteira());
            stmt.setString(3, instrutor.getCategoria());

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
    
    public List<Instrutor> consultarInstrutor() {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Instrutor> instrutores = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM instrutor");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Instrutor instrutor = new Instrutor();

                instrutor.setCodigoFuncionario(rs.getInt("codFunc"));
                instrutor.setNumCarteira(rs.getString("numCarteira"));
                instrutor.setCategoria(rs.getString("categoria"));
                      
                instrutores.add(instrutor);
            }
            JOptionPane.showMessageDialog(null, "Consulta concluida!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar! " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return instrutores;
    }
}
