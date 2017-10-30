/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.controle;

import autoescola.modelo.arquivo.ExameArquivo;
import autoescola.modelo.bean.Exame;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author felipe
 */
public class ControleExame {

    private ControleCalendario calendario;

    public ControleExame(ControleCalendario calendario) {
        this.calendario = calendario;
    }


    public TableModel consultarExames() {

        ExameArquivo arqExame = new ExameArquivo();
        ArrayList<Exame> exames = arqExame.consultarData(calendario.getDiaMesAno());
        DefaultTableModel jTable1 = new DefaultTableModel();

        if (exames != null) {
            jTable1.addColumn("Codigo Exame");
            jTable1.addColumn("Hora");
            for (Exame exame : exames) {
                jTable1.addRow(new Object[]{exame.getCodigoExame(), exame.getHorarioExame()});
            }
            return jTable1;
        } else {
            JTable table = new JTable();
            table.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{
                        {null, null, null, null, null, null},},
                    new String[]{
                        "Codigo Exame", "Hora"
                    }
            ));
            return table.getModel();
        }

    }

}
