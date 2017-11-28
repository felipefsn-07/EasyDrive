/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.connection.ConnectionFactory;
import autoescola.modelo.bean.Aula;
import autoescola.modelo.bean.AulasClientes;
import autoescola.modelo.bean.Funcionario;
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
public class AulaDao {

    public int cadastrarAula(Aula aula) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "INSERT INTO aula (dataAula, horaInicio, horaFim) VALUES(?, ?, ?)";
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, aula.getDataAula());
            stmt.setString(2, aula.getHorarioAulaInicio());
            stmt.setString(3, aula.getHorarioAulaFim());

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

    public ArrayList<Aula> consultarAulas() {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Aula aula = new Aula();

        ArrayList<Aula> aulas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM aula");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                Funcionario funcionario = new Funcionario();

                aula.setCodAulas(rs.getInt("codAula"));
                aula.setDataAula(rs.getString("dataAula"));
                aula.setHorarioAulaInicio(rs.getString("horaInicio"));
                aula.setHorarioAulaFim(rs.getString("horaFim"));
                veiculo.setCodVeiculo(rs.getInt("codVeiculo"));
                aula.setVeiculo(veiculo);
                funcionario.setCodigoFuncionario(rs.getInt("codInstrutor"));
                aula.setInstrutor(funcionario);

                aulas.add(aula);
            }
            return aulas;
        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public Aula consutarAulaExiste(int codAula) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Aula aula = new Aula();

        try {
            stmt = con.prepareStatement("SELECT * FROM aula WHERE codAula = ?");
            stmt.setInt(1, codAula);
            rs = stmt.executeQuery();

            while (rs.next()) {
                aula.setCodAulas(rs.getInt("codAula"));
                aula.setDataAula(rs.getString("dataAula"));
                aula.setHorarioAulaInicio(rs.getString("horaInicio"));
                aula.setHorarioAulaFim(rs.getString("horaFim"));

                VeicDao vd = new VeicDao();
                Veiculo veiculo = vd.consutarVeiculoExiste(rs.getInt("codVeiculo"));
                aula.setVeiculo(veiculo);

                FuncionarioDao fd = new FuncionarioDao();
                Funcionario funcionario = fd.consutarFuncExiste(rs.getInt("codInstrutor"));
                aula.setInstrutor(funcionario);
            }
            return aula;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar! " + ex);
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public boolean alterarAula(Aula aula) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE aula SET dataAula = ?, horaInicio = ?, horaFim = ?, codVeiculo = ?, codInstrutor = ? WHERE codAula = ?");
            stmt.setString(1, aula.getDataAula());
            stmt.setString(2, aula.getHorarioAulaInicio());
            stmt.setString(3, aula.getHorarioAulaFim());
            stmt.setInt(4, aula.getVeiculo().getCodVeiculo());
            stmt.setInt(5, aula.getInstrutor().getCodigoFuncionario());
            stmt.setInt(6, aula.getCodAulas());

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean apagar(int codAula) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM aula WHERE codAula = ?");
            stmt.setInt(1, codAula);
            rs = stmt.executeQuery();

            while (rs.next()) {
                AulaClienteDao daoEC = new AulaClienteDao();
                daoEC.apagarAula(codAula);
            }
            return true;

        } catch (SQLException ex) {
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public Aula consultarChamada(int codAula) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Aula aula = new Aula();

        try {
            stmt = con.prepareStatement("SELECT * FROM aula WHERE codAula = ?");
            stmt.setInt(1, codAula);
            rs = stmt.executeQuery();

            while (rs.next()) {
                aula.setCodAulas(rs.getInt("codAula"));
                aula.setDataAula(rs.getString("dataAula"));
                aula.setHorarioAulaInicio(rs.getString("horaInicio"));
                aula.setHorarioAulaFim(rs.getString("horaFim"));

                VeicDao vd = new VeicDao();
                Veiculo veiculo = vd.consutarVeiculoExiste(rs.getInt("codVeiculo"));
                aula.setVeiculo(veiculo);

                FuncionarioDao fd = new FuncionarioDao();
                Funcionario funcionario = fd.consutarFuncExiste(rs.getInt("codInstrutor"));
                aula.setInstrutor(funcionario);

                AulaClienteDao acd = new AulaClienteDao();
                ArrayList<AulasClientes> alunos = acd.consultarAulasClientes(codAula);
                aula.setAulas(alunos);
            }
            return aula;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar! " + ex);
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public ArrayList<Aula> consultarAulassLike(String campo, String valor) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Aula> aulas = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * FROM aula WHERE ? LIKE ?");
            stmt.setString(1, campo);
            stmt.setString(2, "%" + valor + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Aula aula = new Aula();

                aula.setCodAulas(rs.getInt("codAula"));
                aula.setDataAula(rs.getString("dataAula"));
                aula.setHorarioAulaInicio(rs.getString("horaInicio"));
                aula.setHorarioAulaFim(rs.getString("horaFim"));

                VeicDao vd = new VeicDao();
                Veiculo veiculo = vd.consutarVeiculoExiste(rs.getInt("codVeiculo"));
                aula.setVeiculo(veiculo);

                FuncionarioDao fd = new FuncionarioDao();
                Funcionario funcionario = fd.consutarFuncExiste(rs.getInt("codInstrutor"));
                aula.setInstrutor(funcionario);

                aulas.add(aula);
            }
            return aulas;

        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public ArrayList<Aula> consultarData(String data) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Aula aula = new Aula();

        ArrayList<Aula> aulas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM aula WHERE dataAula = ?");
            stmt.setString(1, data);
            rs = stmt.executeQuery();

            while (rs.next()) {
                aula.setCodAulas(rs.getInt("codAula"));
                aula.setDataAula(rs.getString("dataAula"));
                aula.setHorarioAulaInicio(rs.getString("horaInicio"));
                aula.setHorarioAulaFim(rs.getString("horaFim"));

                aulas.add(aula);
            }
            return aulas;
        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
