/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.controle;

import autoescola.modelo.arquivo.FuncionarioArquivo;
import autoescola.modelo.bean.Cliente;
import autoescola.modelo.bean.Funcionario;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author felipe
 */
public class ControleExame extends ControleCalendario {

    
    public TableModel calendario() {
        int[][] calendario = getMesCalendarioAtual();
        DefaultTableModel jTable1 = new DefaultTableModel();
        jTable1.addColumn("Dom");
        jTable1.addColumn("Seg");
        jTable1.addColumn("Ter");
        jTable1.addColumn("Qua");
        jTable1.addColumn("Qui");
        jTable1.addColumn("Sex");
        jTable1.addColumn("Sab");
       //jTable1.setRowHeight(height); 
        for (int i = 0; i < 6; i++) {
            jTable1.addRow(new Object[]{calendario[i][0], calendario[i][1], calendario[i][2], calendario[i][3], calendario[i][4], calendario[i][5], calendario[i][6]});

        }

        return jTable1;

    }

}
