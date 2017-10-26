/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.controle;

import autoescola.modelo.arquivo.FuncionarioArquivo;
import autoescola.modelo.bean.Funcionario;
import java.util.ArrayList;
import javax.swing.JTable;
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
            jTable1.addColumn("Endereço");
            jTable1.addColumn("Editar");
            jTable1.addColumn("Desativar");

            for (Funcionario funcionario : funcionarios) {
                jTable1.addRow(new Object[]{String.valueOf(funcionario.getCodigoFuncionario()), funcionario.getNome(), funcionario.getRg(), funcionario.getCpf(), funcionario.getTelefone(), funcionario.getTelefone(), null, null, null});
            }
            return jTable1;
        } else {
            JTable table = new JTable();
            table.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{
                        {null, null, null, null, null, null, null, null, null}
                    },
                    new String[]{
                        "Codigo Funcionário", "Nome", "Rg", "Cpf", "Telefone", "Celular", "Endereço", "Editar", "Desativar"
                    }
            ));
            return table.getModel();
        }
        
    }

}
