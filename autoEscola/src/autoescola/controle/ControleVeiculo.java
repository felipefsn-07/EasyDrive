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
import javax.swing.JOptionPane;
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
    private boolean editar = false;

    /**
     * @return the editar
     */
    public boolean isEditar() {
        return editar;
    }

    /**
     * @param editar the editar to set
     */
    private void setEditar(boolean editar) {
        this.editar = editar;
    }

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
                    break;
                }
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

    public boolean cadastrarVeiculo(Veiculo veiculo) {

        VeiculoArquivo arqVeiculo = new VeiculoArquivo();
        if (!"".equals(veiculo.getAno()) && !"   -    ".equals(veiculo.getPlaca()) && !"".equals(veiculo.getModelo()) && !"0".equals(veiculo.getCapacidade())) {

            if (!arqVeiculo.consultarPlaca(veiculo.getPlaca())) {
                veiculo.setStatus(true);
                int res = arqVeiculo.cadastrarVeiculo(veiculo);
                if (res != 0) {
                    veiculo = new Veiculo();
                    this.veiculo = veiculo;
                    this.veiculo.setCodVeiculo(res);

                    JOptionPane.showMessageDialog(null, "Veiculo cadastrado com sucesso!");
                    setEditar(true);
                    return true;

                } else {
                    JOptionPane.showMessageDialog(null, "Erro!");

                }

            } else {
                JOptionPane.showMessageDialog(null, "Veiculo já cadastrado!");
            }

        } else {

            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

        }
        return false;
    }

    public boolean editarVeiculo(Veiculo veiculo) {

        VeiculoArquivo arqVeiculo = new VeiculoArquivo();
        if (!"".equals(veiculo.getAno()) && !"   -    ".equals(veiculo.getPlaca()) && !"".equals(veiculo.getModelo()) && !"0".equals(veiculo.getCapacidade())) {

            if (!arqVeiculo.consultarPlaca(veiculo.getPlaca()) || this.veiculo.getPlaca().equals(veiculo.getPlaca())) {
                veiculo.setStatus(this.veiculo.getStatus());
                veiculo.setCodVeiculo(this.veiculo.getCodVeiculo());
                if (arqVeiculo.alterarVeiculo(veiculo)) {
                    this.veiculo = veiculo;
                    JOptionPane.showMessageDialog(null, "Veiculo editado com sucesso!");
                    return true;

                } else {
                    JOptionPane.showMessageDialog(null, "Erro!");

                }

            } else {
                JOptionPane.showMessageDialog(null, "Veiculo já cadastrado!");
            }

        } else {

            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

        }
        return false;

    }

    public Veiculo getVeiculo(int id) {
        VeiculoArquivo arq = new VeiculoArquivo();
        this.veiculo = arq.consultar(id);
        setEditar(true);
        return this.veiculo;
    }

}
