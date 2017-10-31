/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.connection.ConnectionFactory;
import autoescola.modelo.bean.Exame;
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
public class ExameDao {
    public boolean cadastrarExame(Exame exame) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO exame (dataExame, horaExame, codVeiculo, numCarteira, status) VALUES(?, ?, ?, ?, ?)");
            stmt.setString(1, exame.getDataExame());
            stmt.setString(2, exame.getHorarioInicio());
            stmt.setInt(3, exame.getVeiculo().getCodVeiculo());
            stmt.setString(4, exame.getInstrutor().getNumCarteira());
            stmt.setInt(5, exame.getStatus());

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
    
    public List<Exame> consultarExame() {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Exame> exames = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM exame");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Exame exame = new Exame();
                Veiculo veiculo = new Veiculo();
                Funcionario instrutor = new Funcionario();

                exame.setCodigoExame(rs.getInt("codExame"));
                exame.setDataExame(rs.getString("dataExame"));
                exame.setHorarioInicio(rs.getString("horaExame"));
                veiculo.setCodVeiculo(rs.getInt("codVeiculo"));
                instrutor.setNumCarteira("numCarteira");
                exame.setStatus(rs.getInt("status"));

                exames.add(exame);
            }
            JOptionPane.showMessageDialog(null, "Consulta concluida!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar! " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return exames;
    }
    
    public boolean consutarExameExiste(int codExame) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM exame WHERE codExame = ?");
            stmt.setInt(1, codExame);
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
    
    public boolean alterarExame(Exame exame) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE exame SET dataExame = ?, horaExame = ?, codVeiculo = ?, numCarteira = ?, status = ? WHERE codExame = ?");
            stmt.setString(1, exame.getDataExame());
            stmt.setString(2, exame.getHorarioInicio());
            stmt.setInt(3, exame.getVeiculo().getCodVeiculo());
            stmt.setString(4, exame.getInstrutor().getNumCarteira());
            stmt.setInt(5, exame.getStatus());
            stmt.setInt(6, exame.getCodigoExame());

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
    
    public boolean excluirExame(Exame exame) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE exame SET status = ? WHERE codExame = ?");
            stmt.setInt(1, exame.getStatus());
            stmt.setInt(2, exame.getCodigoExame());

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
