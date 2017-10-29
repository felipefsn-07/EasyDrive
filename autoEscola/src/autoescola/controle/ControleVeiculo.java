/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.controle;

import autoescola.modelo.arquivo.VeiculoArquivo;
import autoescola.modelo.bean.Veiculo;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author felipe
 */
public class ControleVeiculo extends Controle {

    private Veiculo veiculo;

    public TableModel consultarVeiculos() {

        VeiculoArquivo arqFunc = new VeiculoArquivo();
        ArrayList<Veiculo> veiculos = arqFunc.consultarVeiculos();
        DefaultTableModel jTable1 = new DefaultTableModel();

        if (veiculos != null) {
            jTable1.addColumn("Codigo veiculo");
            jTable1.addColumn("placa");
            jTable1.addColumn("ano");
            jTable1.addColumn("modelo");
            jTable1.addColumn("capacidade");
            jTable1.addColumn("Ativo");
            for (Veiculo veiculoCons : veiculos) {
                jTable1.addRow(new Object[]{String.valueOf(veiculoCons.getCodVeiculo()), veiculoCons.getPlaca(), veiculoCons.getAno(), veiculoCons.getModelo(), veiculoCons.getCapacidade(), veiculoCons.getStatus()});

            }
            return jTable1;
        } else {
            JTable table = new JTable();
            table.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{
                        {null, null, null, null, null, null},},
                    new String[]{
                        "Codigo veiculo", "placa", "ano", "modelo", "capacidade", "Ativo"
                    }
            ));
            return table.getModel();
        }

    }

    @Override
    public boolean alterarStatus(boolean anterior, String idStr) {

        VeiculoArquivo arq = new VeiculoArquivo();
        int id = parseInt(idStr);
        veiculo = arq.consultar(id);
        if (isDigit(idStr)) {
            if (anterior != veiculo.getStatus()) {
                if (veiculo.getStatus()) {
                    return arq.desativar(id);
                } else {
                    return arq.ativar(id);

                }
            } else {
                return false;
            }
        }
        return false;
    }

    public TableModel consultaVeiculoLike(JTextField id, JComboBox tipo) {
        VeiculoArquivo arqVeic = new VeiculoArquivo();
        DefaultTableModel jTable1 = new DefaultTableModel();
        if (tipo.getSelectedItem().toString().equals("codigo do veiculo") && !"".equals(id.getText()) && isDigit(id.getText())) {
            veiculo = arqVeic.consultar(parseInt(id.getText()));

            if (veiculo != null) {
                jTable1.addColumn("Codigo veiculo");
                jTable1.addColumn("placa");
                jTable1.addColumn("ano");
                jTable1.addColumn("modelo");
                jTable1.addColumn("capacidade");
                jTable1.addColumn("Ativo");
                jTable1.addRow(new Object[]{String.valueOf(veiculo.getCodVeiculo()), veiculo.getPlaca(), veiculo.getAno(), veiculo.getModelo(), veiculo.getCapacidade(), veiculo.getStatus()});

                return jTable1;
            } else {

                return consultarVeiculos();
            }
        } else {
            if (!"".equals(id.getText()) && !"".equals(tipo.getSelectedItem().toString())) {
                ArrayList<Veiculo> veiculos = arqVeic.consultarVeiculosLike(tipo.getSelectedItem().toString(), id.getText());

                if (veiculos != null) {
                    jTable1.addColumn("Codigo veiculo");
                    jTable1.addColumn("placa");
                    jTable1.addColumn("ano");
                    jTable1.addColumn("modelo");
                    jTable1.addColumn("capacidade");
                    jTable1.addColumn("Ativo");
                    for (Veiculo veiculoCons : veiculos) {
                        jTable1.addRow(new Object[]{String.valueOf(veiculoCons.getCodVeiculo()), veiculoCons.getPlaca(), veiculoCons.getAno(), veiculoCons.getModelo(), veiculoCons.getCapacidade(), veiculoCons.getStatus()});

                    }
                    return jTable1;

                } else {

                    return consultarVeiculos();

                }
            } else {

                return consultarVeiculos();
            }
        }

    }

}
