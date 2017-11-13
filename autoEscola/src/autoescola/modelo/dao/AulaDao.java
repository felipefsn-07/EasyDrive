/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.connection.ConnectionFactory;
import autoescola.modelo.bean.Aula;
import autoescola.modelo.bean.Funcionario;
import autoescola.modelo.bean.Veiculo;
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
public class AulaDao {
    public boolean cadastrarAula(Aula aula) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO aula (dataAula, horarioAula, codVeiculo, numCarteira, status) VALUES(?, ?, ?, ?, ?)");
            stmt.setString(1, aula.getDataAula());
            stmt.setString(2, aula.getHorarioAulaInicio());
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
    
    public List<Aula> consultarAulas() {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Aula> aulas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM aula");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Aula aula = new Aula();
                Veiculo veiculo = new Veiculo();
                Funcionario instrutor = new Funcionario();

                aula.setCodAulas(rs.getInt("codAula"));
                aula.setDataAula(rs.getString("dataAula"));
                aula.setHorarioAulaInicio(rs.getString("horarioAula"));
                veiculo.setCodVeiculo(rs.getInt("codVeiculo"));
                instrutor.setNumCarteira(rs.getString("numCarteira"));
                      
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
    
    public boolean consutarAulaExiste(int codAula) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM aula WHERE codAula = ?");
            stmt.setInt(1, codAula);
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
    
    public boolean alterarAula(Aula aula) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE aula SET dataAula = ?, horarioAula = ?, codVeiculo = ?, numCarteira = ? WHERE codAula = ?");
            stmt.setString(1, aula.getDataAula());
            stmt.setString(2, aula.getHorarioAulaInicio());
            stmt.setInt(3, aula.getVeiculo().getCodVeiculo());
            stmt.setString(4, aula.getInstrutor().getNumCarteira());
            stmt.setInt(6, aula.getCodAulas());

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
    
    public boolean excluirAula(Aula aula) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE aula  WHERE codAula = ?");
            stmt.setInt(2, aula.getCodAulas());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
