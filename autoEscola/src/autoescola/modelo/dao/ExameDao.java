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
    public int cadastrarExame(Exame exame) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
//dataExame, horaInicia, horaFim, codVeiculo, codInstrutor
        try {
            stmt = con.prepareStatement("INSERT INTO exame (dataExame, horaInicio, horaFim, codVeiculo, codInstrutor) VALUES(?, ?, ?, ?, ?)");
            stmt.setString(1, exame.getDataExame());
            stmt.setString(2, exame.getHorarioInicio());
            stmt.setString(3, exame.getHorarioFim());
            stmt.setInt(4, exame.getVeiculo().getCodVeiculo());
            stmt.setInt(5, exame.getInstrutor().getCodigoFuncionario());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
            return 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar! " + ex);
            return 0;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<Exame> consultarExame() {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Exame> exames = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM exame");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Exame exame = new Exame();
                Veiculo veiculo = new Veiculo();
                Funcionario instrutor = new Funcionario();

                exame.setCodigoExame(rs.getInt("codExame"));
                exame.setDataExame(rs.getString("dataExame"));
                exame.setHorarioInicio(rs.getString("horaInicio"));
                exame.setHorarioFim(rs.getString("horaFim"));
                veiculo.setCodVeiculo(rs.getInt("codVeiculo"));
                instrutor.setCodigoFuncionario(rs.getInt("codInstrutor"));

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
    
    public Exame consutarExameExiste(int codExame) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Exame exame = new Exame();
        
          
        try {
            stmt = con.prepareStatement("SELECT * FROM exame WHERE codExame = ?");
            stmt.setInt(1, codExame);
            rs = stmt.executeQuery();

            while (rs.next()) {
                exame.setCodigoExame(rs.getInt("codExame"));
                exame.setDataExame(rs.getString("dataExame"));
                exame.setHorarioInicio(rs.getString("horarioInicio"));
                exame.setHorarioInicio(rs.getString("horarioFim"));
                
                VeicDao vd = new VeicDao();
                Veiculo veiculo = vd.consutarVeiculoExiste(rs.getInt("codVeiculo"));
                exame.setVeiculo(veiculo);

                FuncionarioDao fd = new FuncionarioDao();
                Funcionario funcionario = fd.consutarFuncExiste(rs.getInt("codInstrutor"));
                exame.setInstrutor(funcionario);

            }
            return exame;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar! " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return null;
    }
    
    public boolean alterarExame(Exame exame) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE exame SET dataExame = ?, horaInicio = ?, horaFim = ?,  codVeiculo = ?, codInstrutor = ? WHERE codExame = ?");
            stmt.setString(1, exame.getDataExame());
            stmt.setString(2, exame.getHorarioInicio());
            stmt.setString(3, exame.getHorarioFim());
            stmt.setInt(4, exame.getVeiculo().getCodVeiculo());
            stmt.setInt(5, exame.getInstrutor().getCodigoFuncionario());
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
    
    
    public ArrayList<Exame> consultarData(String data) {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Exame exame = new Exame();

        ArrayList<Exame> exames = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM exame WHERE dataAula = ?");
            stmt.setString(1, data);
            rs = stmt.executeQuery();

            while (rs.next()) {
                exame.setCodigoExame(rs.getInt("codExame"));
                exame.setDataExame(rs.getString("dataExame"));
                exame.setHorarioInicio(rs.getString("horarioInicio"));
                exame.setHorarioInicio(rs.getString("horarioFim"));
                
                VeicDao vd = new VeicDao();
                Veiculo veiculo = vd.consutarVeiculoExiste(rs.getInt("codVeiculo"));
                exame.setVeiculo(veiculo);

                FuncionarioDao fd = new FuncionarioDao();
                Funcionario funcionario = fd.consutarFuncExiste(rs.getInt("codInstrutor"));
                exame.setInstrutor(funcionario);

                
                exames.add(exame);
            }
            return exames;
        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public boolean apagar(int codExame) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM exame WHERE codExame = ?");
            stmt.setInt(1, codExame);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ExameClienteDao daoEC = new ExameClienteDao();
                daoEC.apagarExame(codExame);
            }
            return true;

        } catch (SQLException ex) {
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public ArrayList<Exame> consultarExamesLike(String campo, String valor){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Exame> exames = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * FROM exame WHERE ? LIKE ?");
            stmt.setString(1, campo);
            stmt.setString(2, "%" + valor + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Exame exame = new Exame();

               exame.setCodigoExame(rs.getInt("codExame"));
                exame.setDataExame(rs.getString("dataExame"));
                exame.setHorarioInicio(rs.getString("horarioInicio"));
                exame.setHorarioInicio(rs.getString("horarioFim"));
                
                VeicDao vd = new VeicDao();
                Veiculo veiculo = vd.consutarVeiculoExiste(rs.getInt("codVeiculo"));
                exame.setVeiculo(veiculo);

                FuncionarioDao fd = new FuncionarioDao();
                Funcionario funcionario = fd.consutarFuncExiste(rs.getInt("codInstrutor"));
                exame.setInstrutor(funcionario);

                
                exames.add(exame);
            }
            return exames;

        } catch (SQLException ex) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    }
    
   /* public boolean excluirExame(Exame exame) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE exame WHERE codExame = ?");
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
    }*/

