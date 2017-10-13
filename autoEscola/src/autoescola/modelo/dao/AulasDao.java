/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.connection.ConnectionFactory;
import autoescola.modelo.bean.Aula;
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
public class AulasDao {
    public boolean cadastrarAulas(Aula aula) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO aula (dataAula, horarioAula, codVeiculo, numCarteira) VALUES(?, ?, ?, ?)");
            stmt.setDate(1, aula.getDataAula());
            stmt.setTime(2, aula.getHorarioAula());
            stmt.setInt(3, aula.getVeiculo().getCodVeiculo());
            stmt.setString(4, aula.getInstrutor().getNumCarteira());

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
    
    public List<Aula> consultarAula() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Aula> aulas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM aula");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Aula aula = new Aula();

                aula.getCodAulas();
                aula.setDataAula(rs.getDate("dataAula").toLocalDate());
                aula.setHorarioAula(rs.getTime("horarioAula").toLocalTime());
                aula.setVeiculo(veiculo);
                
                
                Instrutor instrutor = new Instrutor();
                
                instrutor.setNumCarteira(rs.getString("numCarteira"));           
                aula.setInstrutor(instrutor);

                aulas.add(aula);
            }
            JOptionPane.showMessageDialog(null, "Consulta concluida!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar! " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return aulas;
    }
}
