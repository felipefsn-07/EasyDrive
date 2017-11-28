/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.view.tabelas;

import autoescola.controleDao.ControleCalendario;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.ListSelectionModel;

/**
 *
 * @author felipe
 */
public class TabelaCalendario extends JTable {

    private int linha;
    private int coluna;
    private ControleCalendario controle;

    public TabelaCalendario(ControleCalendario controle) {
        this.linha = 0;
        this.coluna = 0;
        this.controle = controle;
        this.getSelectionModel().setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);
        this.getColumnModel().getSelectionModel().setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);
        this.setCellSelectionEnabled(true);

        this.setRowHeight(70);

    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    /**
     * 
     * @param renderer
     * @param row
     * @param column
     * @return 
     */

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {

        Component component = super.prepareRenderer(renderer, row, column);
        component.setFont(new java.awt.Font("Ubuntu", 0, 19)); // NOI18N
        int linhaCol = (int) getModel().getValueAt(row, column);
        int hoje[] = controle.getHoje();
        
        switch (controle.getCalendarioAnteriorAtualProximo()[row][column]) {
            case 1:
                component.setBackground(Color.LIGHT_GRAY);
                component.setEnabled(false);
                this.setCellSelectionEnabled(false);
                component.setForeground(Color.WHITE);
                break;
            case 2:
                this.setCellSelectionEnabled(true);

                if (linhaCol == hoje[0] && controle.getAno() == hoje[2] && controle.getMesControle() == hoje[1]) {
                    Color color = new Color(112, 226, 209);
                    component.setBackground(color);
                    component.setForeground(Color.WHITE);

                } else {
                    Color color = new Color(144, 180, 242);
                    component.setForeground(Color.black);

                    component.setBackground(Color.white);

                }

                if (isCellSelected(row, column)) {
                    controle.setDia(linhaCol);
                    Color color = new Color(61, 180, 162);
                    component.setBackground(color);
                    component.setForeground(Color.WHITE);
                } else {
                    if (linhaCol == hoje[0] && controle.getAno() == hoje[2] && controle.getMesControle() == hoje[1]) {
                        Color color = new Color(112, 226, 209);
                        component.setBackground(color);
                        component.setForeground(Color.WHITE);
                    } else {
                        Color color = new Color(144, 180, 242);
                        component.setForeground(Color.black);

                        component.setBackground(Color.white);

                    }
                }
                break;
            case 3:
                component.setBackground(Color.LIGHT_GRAY);
                component.setEnabled(false);
                this.setCellSelectionEnabled(false);
                component.setForeground(Color.WHITE);
                break;

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
