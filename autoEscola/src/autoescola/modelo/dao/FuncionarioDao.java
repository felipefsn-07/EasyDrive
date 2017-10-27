/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.dao;

import autoescola.connection.ConnectionFactory;
import autoescola.modelo.bean.Endereco;
import autoescola.modelo.bean.Funcionario;
import autoescola.modelo.bean.Usuario;
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
public class FuncionarioDao {
    public boolean cadastrarFuncionario(Funcionario func) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO funcionario (codLogin, codEndereco, rg, nome, cpf, dataNasc, tel, cel, horaEntra, horaSai, status, tipo) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, func.getUsuario().getCodLogin());
            stmt.setInt(2, func.getEndereco().getCodEndereco());
            stmt.setString(3, func.getRg());
            stmt.setString(4, func.getNome());
            stmt.setString(5, func.getCpf());
            stmt.setString(6, func.getDatanasc());
            stmt.setString(7, func.getTelefone());
            stmt.setString(8, func.getCelular());
            stmt.setString(9, func.getHora_entra());
            stmt.setString(10, func.getHora_sai());
            stmt.setBoolean(11, func.getStatus());
            stmt.setString(12, func.getTipo());

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
    
    public List<Funcionario> consultarFuncionario() {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Funcionario> funcs = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario func = new Funcionario();
                Usuario login = new Usuario();
                Endereco endereco = new Endereco();

                func.setCodigoFuncionario(rs.getInt("codFunc"));
                login.setCodLogin(rs.getInt("codLogin"));
                endereco.setCodEndereco(rs.getInt("codEndereco"));
                func.setRg(rs.getString("rg"));
                func.setNome(rs.getString("nome"));
                func.setCpf(rs.getString("cpf"));
                func.setDatanasc(rs.getString("dataNasc"));
                func.setTelefone(rs.getString("tel"));
                func.setCelular(rs.getString("cel"));
                func.setHora_entra(rs.getString("horaEntra"));
                func.setHora_sai(rs.getString("horaSai"));
                func.setStatus(rs.getBoolean("status"));
                func.setTipo(rs.getString("tipo"));
                      
                funcs.add(func);
            }
            JOptionPane.showMessageDialog(null, "Consulta concluida!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar! " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return funcs;
    }
    
    public boolean consutarFuncExiste(int codFunc) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario WHERE codFunc = ?");
            stmt.setInt(1, codFunc);
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
    
    public boolean alterarFunc(Funcionario func) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE funcionario SET rg = ?, nome = ?, cpf = ?, dataNasc = ?, tel = ?, cel =  ?, horaEntra = ?, horaSai = ?, status = ?, tipo = ? WHERE codFunc = ?");
            stmt.setString(1, func.getRg());
            stmt.setString(2, func.getNome());
            stmt.setString(3, func.getCpf());
            stmt.setString(4, func.getDatanasc());
            stmt.setString(5, func.getTelefone());
            stmt.setString(6, func.getCelular());
            stmt.setString(7, func.getHora_entra());
            stmt.setString(8, func.getHora_sai());
            stmt.setBoolean(9, func.getStatus());
            stmt.setString(10, func.getTipo());
            stmt.setInt(11, func.getCodigoFuncionario());

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
    
     public boolean excluirFunc(Funcionario func) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE funcionario SET status = ? WHERE codFunc = ?");
            stmt.setBoolean(1, func.getStatus());
            stmt.setInt(2, func.getCodigoFuncionario());

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
