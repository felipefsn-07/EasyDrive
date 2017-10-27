/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.controle;

import autoescola.modelo.arquivo.FuncionarioArquivo;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import static java.lang.Integer.parseInt;

/**
 *
 * @author felipe
 */
public class Tabela extends JTable {

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 6;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {

        Component Component = super.prepareRenderer(renderer, row, column);
        int linha = convertRowIndexToModel(row);
        boolean ativo = (boolean) getModel().getValueAt(linha, 6);

        if (isCellSelected(linha, 6)) {

            if (ativo == true) {

                Component.setForeground(Color.RED);
            } else {
                Component.setForeground(Color.BLACK);

            }

        }
        if (ativo == false) {
            Component.setForeground(Color.RED);
        } else {

            Component.setForeground(Color.BLACK);

        }
        if (isRowSelected(row)) {
            Color color = new Color(61, 180, 162);
            Component.setBackground(color);
            Component.setForeground(Color.WHITE);
            Component.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N

        } else {
            Component.setBackground(Color.white);
            Component.setForeground(Color.BLACK);
            Component.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        }

        return Component;
    }

    public void setCellSelectionEnabled(boolean cellSelectionEnabled, int row) {
        setRowSelectionAllowed(cellSelectionEnabled);
        setColumnSelectionAllowed(cellSelectionEnabled);
        boolean old = this.cellSelectionEnabled;
        this.cellSelectionEnabled = cellSelectionEnabled;
        firePropertyChange("cellSelectionEnabled", old, cellSelectionEnabled);
    }
}
