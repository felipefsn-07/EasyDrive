/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.connection.ConnectionFactory;
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
public class VeicDao {

    public boolean cadastrarVeiculo(Veiculo veic) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO automovel (placa, ano, modelo, capacidade, status, tipo) VALUES(?, ?, ?, ?, ?, ?)");
            stmt.setString(1, veic.getPlaca());
            stmt.setString(2, veic.getAno());
            stmt.setString(3, veic.getModelo());
            stmt.setFloat(4, veic.getCapacidade());
            stmt.setInt(5, veic.getStatus());
            stmt.setString(6, veic.getTipo());

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

    public List<Veiculo> consultarVeiculos() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Veiculo> veiculos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM automovel");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Veiculo veic = new Veiculo();
                
                veic.setCodVeiculo(rs.getInt("codVeiculo"));
                veic.setPlaca(rs.getString("placa"));
                veic.setAno(rs.getString("ano"));
                veic.setModelo(rs.getString("modelo"));
                veic.setCapacidade(rs.getInt("capacidade"));
                veic.setStatus(rs.getInt("status"));
                veic.setCodVeiculo(rs.getInt("codVeiculo"));
                veic.setTipo(rs.getString("tipo"));

                veiculos.add(veic);
            }
            JOptionPane.showMessageDialog(null, "Consulta concluida!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar! " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return veiculos;
    }
    
    public boolean consutarVeiculoExiste(int codVeiculo) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM automavel WHERE codVeiculo = ?");
            stmt.setInt(1, codVeiculo);
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
    
    public boolean alterarVeiculo(Veiculo veic) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE automovel SET placa = ?, ano = ?, modelo = ?, capacidade = ?, status = ?, tipo = ? WHERE codVeiculo = ?");
            
            stmt.setString(1, veic.getPlaca());
            stmt.setString(2, veic.getAno());
            stmt.setString(3, veic.getModelo());
            stmt.setFloat(3, veic.getCapacidade());
            stmt.setInt(4, veic.getStatus());
            stmt.setString(5, veic.getTipo());
            stmt.setInt(6, veic.getCodVeiculo());

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
    
    public boolean excluirVeiculo(Veiculo veic) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE automovel SET status = ? WHERE codVeiculo = ?");
            stmt.setInt(1, veic.getCodVeiculo());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
