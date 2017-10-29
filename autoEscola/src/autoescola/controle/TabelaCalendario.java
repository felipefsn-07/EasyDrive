/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.controle;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import autoescola.controle.ControleCalendario;
import javax.swing.ListSelectionModel;

/**
 *
 * @author felipe
 */
public class TabelaCalendario extends JTable {

    private int linha;
    private int coluna;

    public TabelaCalendario() {
        this.linha = 0;
        this.coluna = 0;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        this.getSelectionModel().setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);
        this.getColumnModel().getSelectionModel().setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);
        this.setCellSelectionEnabled(true);
        Component component = super.prepareRenderer(renderer, row, column);
        this.setRowHeight(70);
        ControleCalendario calendario = new ControleCalendario();
        component.setFont(new java.awt.Font("Ubuntu", 0, 19)); // NOI18N

        int linhaCol = (int) getModel().getValueAt(row, column);

        if (row == 0 && column <= 7 && linhaCol > 1) {
            component.setBackground(Color.LIGHT_GRAY);
            component.setForeground(Color.WHITE);
            component.setEnabled(false);
            this.setCellSelectionEnabled(false);
        } else if (row >= 4 && column <= 7 && linhaCol == 1) {
            linha = row;
            coluna = column;
            component.setBackground(Color.LIGHT_GRAY);
            component.setForeground(Color.WHITE);
            component.setEnabled(false);
            this.setCellSelectionEnabled(false);

        } else if (row >= linha && coluna <= 7 && linhaCol >= 1 && linhaCol <= 20) {
            component.setBackground(Color.LIGHT_GRAY);
            component.setForeground(Color.WHITE);
            component.setEnabled(false);
            this.setCellSelectionEnabled(false);

        } else {
            component.setEnabled(true);
            this.setCellSelectionEnabled(true);

            if (isCellSelected(row, column)) {
                Color color = new Color(61, 180, 162);
                component.setBackground(color);
                component.setForeground(Color.WHITE);
            } else {
                if (linhaCol == calendario.getHoje()[0]) {
                    Color color = new Color(61, 180, 162);
                    component.setBackground(color);
                    component.setForeground(Color.WHITE);
                } else {
                    Color color = new Color(144, 180, 242);

                    component.setBackground(color);

                }
            }

        }
        return component;
    }

    public void setCellSelectionEnabled(boolean cellSelectionEnabled, int row) {
        setRowSelectionAllowed(cellSelectionEnabled);
        setColumnSelectionAllowed(cellSelectionEnabled);
        boolean old = this.cellSelectionEnabled;
        this.cellSelectionEnabled = cellSelectionEnabled;
        firePropertyChange("cellSelectionEnabled", old, cellSelectionEnabled);
    }

}
