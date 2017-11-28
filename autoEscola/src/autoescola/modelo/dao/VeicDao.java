/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.connection.ConnectionFactory;
import autoescola.modelo.bean.Veiculo;
import com.mysql.jdbc.Statement;
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
public class VeicDao {

    public int cadastrarVeiculo(Veiculo veic) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "INSERT INTO automovel (placa, ano, modelo, capacidade, status) VALUES(?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, veic.getPlaca());
            stmt.setString(2, veic.getAno());
            stmt.setString(3, veic.getModelo());
            stmt.setFloat(4, veic.getCapacidade());
            stmt.setBoolean(5, true);

            stmt.executeUpdate();

            final ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar! " + ex);
            return 0;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public ArrayList<Veiculo> consultarVeiculos() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Veiculo> veiculos = new ArrayList<>();

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
                veic.setStatus(rs.getBoolean("status"));

                veiculos.add(veic);
            }
            return veiculos;
        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public Veiculo consutarVeiculoExiste(int codVeiculo) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Veiculo veic = new Veiculo();

        try {
            stmt = con.prepareStatement("SELECT * FROM automavel WHERE codVeiculo = ?");
            stmt.setInt(1, codVeiculo);
            rs = stmt.executeQuery();

            while (rs.next()) {
                veic.setCodVeiculo(rs.getInt("codVeiculo"));
                veic.setPlaca(rs.getString("placa"));
                veic.setAno(rs.getString("ano"));
                veic.setModelo(rs.getString("modelo"));
                veic.setCapacidade(rs.getInt("capacidade"));
                veic.setStatus(rs.getBoolean("status"));
            }
            return veic;
        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public boolean alterarVeiculo(Veiculo veic) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE automovel SET placa = ?, ano = ?, modelo = ?, capacidade = ?, status = ? WHERE codVeiculo = ?");

            stmt.setString(1, veic.getPlaca());
            stmt.setString(2, veic.getAno());
            stmt.setString(3, veic.getModelo());
            stmt.setFloat(3, veic.getCapacidade());
            stmt.setBoolean(4, veic.getStatus());
            stmt.setInt(6, veic.getCodVeiculo());

            stmt.executeUpdate();

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

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public ArrayList<Veiculo> consultarVeiculosAtivo() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Veiculo> veiculos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM automovel WHERE status = true");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Veiculo veic = new Veiculo();

                veic.setCodVeiculo(rs.getInt("codVeiculo"));
                veic.setPlaca(rs.getString("placa"));
                veic.setAno(rs.getString("ano"));
                veic.setModelo(rs.getString("modelo"));
                veic.setCapacidade(rs.getInt("capacidade"));
                veic.setStatus(rs.getBoolean("status"));

                veiculos.add(veic);
            }
            return veiculos;
        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public boolean consultarPlaca(String placa) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Veiculo veic = new Veiculo();

        try {
            stmt = con.prepareStatement("SELECT * FROM automavel WHERE placa = ?");
            stmt.setString(1, placa);
            rs = stmt.executeQuery();

            while (rs.next()) {
                veic.setCodVeiculo(rs.getInt("codVeiculo"));
                veic.setPlaca(rs.getString("placa"));
                veic.setAno(rs.getString("ano"));
                veic.setModelo(rs.getString("modelo"));
                veic.setCapacidade(rs.getInt("capacidade"));
                veic.setStatus(rs.getBoolean("status"));
            }
            return true;
        } catch (SQLException ex) {
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public boolean desativar(int codVeiculo) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE automovel SET status = 0 WHERE codVeiculo = ?");
            stmt.setInt(1, codVeiculo);

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao desativar! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean ativar(int codVeiculo) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE automovel SET status = 1 WHERE codVeiculo = ?");
            stmt.setInt(1, codVeiculo);

            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao desativar! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public ArrayList<Veiculo> consultarVeiculosLike(String campo, String valor) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Veiculo> veiculos = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * FROM automovel WHERE ? LIKE ?");
            stmt.setString(1, campo);
            stmt.setString(2, "%" + valor + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Veiculo veic = new Veiculo();

                veic.setCodVeiculo(rs.getInt("codVeiculo"));
                veic.setPlaca(rs.getString("placa"));
                veic.setAno(rs.getString("ano"));
                veic.setModelo(rs.getString("modelo"));
                veic.setCapacidade(rs.getInt("capacidade"));
                veic.setStatus(rs.getBoolean("status"));

                veiculos.add(veic);
            }
            return veiculos;
        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

}
