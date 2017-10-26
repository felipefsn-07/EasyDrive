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
        if (columnIndex == 6) {
            return Boolean.class; // assim gera uma Checkbox.
        }
        return String.class;
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component Component =  super.prepareRenderer(renderer, row, column);
        
        if (isRowSelected(row)){
            Component.setBackground(getBackground());
            int linha = convertRowIndexToModel(row);
            boolean ativo = (boolean) getModel().getValueAt(linha, 6);
            if (ativo == false){
               Component.setBackground(Color.RED);
            }
        }

        return Component;
    }
}
