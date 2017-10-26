/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.controle;

import autoescola.modelo.arquivo.FuncionarioArquivo;
import autoescola.modelo.bean.Funcionario;
import java.awt.Color;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author felipe
 */
public class ControleFuncionario {

    public TableModel consultaUsuarios() {

        FuncionarioArquivo arqFunc = new FuncionarioArquivo();
        ArrayList<Funcionario> funcionarios = arqFunc.consultarFuncionario();
        DefaultTableModel jTable1 = new DefaultTableModel();

        if (funcionarios != null) {
            jTable1.addColumn("Codigo Funcionário");
            jTable1.addColumn("Nome");
            jTable1.addColumn("Rg");
            jTable1.addColumn("Cpf");
            jTable1.addColumn("Telefone");
            jTable1.addColumn("Celular");
            jTable1.addColumn("Ativo");
            int i = 0;
            for (Funcionario funcionario : funcionarios) {
                Color background;
                jTable1.addRow(new Object[]{String.valueOf(funcionario.getCodigoFuncionario()), funcionario.getNome(), funcionario.getRg(), funcionario.getCpf(), funcionario.getTelefone(), funcionario.getCelular(), funcionario.getStatus()==1});

                if (funcionario.getStatus() == 0) {

                }
                i++;
            }
            return jTable1;
        } else {
            JTable table = new JTable();
            table.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{
                        {null, null, null, null, null, null},
                    },
                    new String[]{
                        "Codigo Funcionário", "Nome", "Rg", "Cpf", "Telefone", "Celular", "Ativo"
                    }
            ));
            return table.getModel();
        }

    }

    protected Funcionario funcionario(JTextField id) {
        FuncionarioArquivo funcionario = new FuncionarioArquivo();
        return funcionario.consultar(parseInt(id.getText()));

    }

    boolean isDigit(String s) {
        char ch = s.charAt(0);
        return (ch >= 48 && ch <= 57);
    }

    public TableModel consultaUsuario(JTextField id, JComboBox tipo) {
        FuncionarioArquivo arqFunc = new FuncionarioArquivo();
        DefaultTableModel jTable1 = new DefaultTableModel();

        if (tipo.getSelectedItem().toString().equals("codigo") && !"".equals(id.getText()) && isDigit(id.getText())) {
            Funcionario funcionario = arqFunc.consultar(parseInt(id.getText()));

            if (funcionario != null) {
                jTable1.addColumn("Codigo Funcionário");
                jTable1.addColumn("Nome");
                jTable1.addColumn("Rg");
                jTable1.addColumn("Cpf");
                jTable1.addColumn("Telefone");
                jTable1.addColumn("Celular");
                jTable1.addColumn("Ativo");

                jTable1.addRow(new Object[]{String.valueOf(funcionario.getCodigoFuncionario()), funcionario.getNome(), funcionario.getRg(), funcionario.getCpf(), funcionario.getTelefone(), funcionario.getCelular(), funcionario.getStatus()==1});
                return jTable1;

            } else {
                return consultaUsuarios();

            }

        } else {
            if (!"".equals(id.getText()) && !"".equals(tipo.getSelectedItem().toString())) {
                ArrayList<Funcionario> funcionarios = arqFunc.consultarfuncionariosLike(tipo.getSelectedItem().toString(), id.getText());

                if (funcionarios != null) {
                    jTable1.addColumn("Codigo Funcionário");
                    jTable1.addColumn("Nome");
                    jTable1.addColumn("Rg");
                    jTable1.addColumn("Cpf");
                    jTable1.addColumn("Telefone");
                    jTable1.addColumn("Celular");
                    jTable1.addColumn("Ativo");

                    for (Funcionario funcionario : funcionarios) {
                        jTable1.addRow(new Object[]{String.valueOf(funcionario.getCodigoFuncionario()), funcionario.getNome(), funcionario.getRg(), funcionario.getCpf(), funcionario.getTelefone(), funcionario.getCelular(), funcionario.getStatus()==1});
                    }

                    return jTable1;

                } else {

                    return consultaUsuarios();

                }
            } else {

                return consultaUsuarios();
            }
        }

    }
}
