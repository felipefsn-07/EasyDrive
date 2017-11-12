/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.controle;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author felipe
 */
public class Tabela extends JTable {

    public Tabela() {
        
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    //Bloquear para que o usuário não edite a cécula da tabela
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {

        Component Component = super.prepareRenderer(renderer, row, column);

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

}
