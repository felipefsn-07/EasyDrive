/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.controle;

import autoescola.modelo.arquivo.ClienteArquivo;
import autoescola.modelo.bean.Cliente;
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
public class ControleAluno extends Controle {

    private Cliente cliente;

    public TableModel consultarAlunos() {

        ClienteArquivo arqFunc = new ClienteArquivo();
        ArrayList<Cliente> clientes = arqFunc.consultarClientes();
        DefaultTableModel jTable1 = new DefaultTableModel();

        if (clientes != null) {
            jTable1.addColumn("Codigo aluno");
            jTable1.addColumn("Nome");
            jTable1.addColumn("Rg");
            jTable1.addColumn("Cpf");
            jTable1.addColumn("Telefone");
            jTable1.addColumn("Celular");
            jTable1.addColumn("numero do Ladv");
            jTable1.addColumn("Ativo");
            for (Cliente cliente : clientes) {
                jTable1.addRow(new Object[]{String.valueOf(cliente.getCodCliente()), cliente.getNome(), cliente.getRg(), cliente.getCpf(), cliente.getTelefone(), cliente.getCelular(), cliente.getNumLADV(), cliente.getStatus()});

            }
            return jTable1;
        } else {
            JTable table = new JTable();
            table.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{
                        {null, null, null, null, null, null, null},
                    },
                    new String[]{
                        "Codigo Funcionário", "Nome", "Rg", "Cpf", "Telefone", "Celular", "numero do Ladv", "Ativo"
                    }
            ));
            return table.getModel();
        }

    }

    @Override
    public boolean alterarStatus(boolean anterior, String idStr) {

        ClienteArquivo arq = new ClienteArquivo();
        int id = parseInt(idStr);
        cliente = arq.consultar(id);
        if (isDigit(idStr)) {
            if (anterior != cliente.getStatus()) {
                if (cliente.getStatus()) {
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

    public TableModel consultaAlunoLike(JTextField id, JComboBox tipo) {
        ClienteArquivo arqClie = new ClienteArquivo();
        DefaultTableModel jTable1 = new DefaultTableModel();
        if (tipo.getSelectedItem().toString().equals("codigo") && !"".equals(id.getText()) && isDigit(id.getText())) {
            cliente = arqClie.consultar(parseInt(id.getText()));

            if (cliente != null) {
                jTable1.addColumn("Codigo aluno");
                jTable1.addColumn("Nome");
                jTable1.addColumn("Rg");
                jTable1.addColumn("Cpf");
                jTable1.addColumn("Telefone");
                jTable1.addColumn("Celular");
                jTable1.addColumn("numero do Ladv");
                jTable1.addColumn("Ativo");
                jTable1.addRow(new Object[]{String.valueOf(cliente.getCodCliente()), cliente.getNome(), cliente.getRg(), cliente.getCpf(), cliente.getTelefone(), cliente.getCelular(), cliente.getNumLADV(), cliente.getStatus()});

                return jTable1;
            } else {
                return consultarAlunos();

            }

        } else {
            if (!"".equals(id.getText()) && !"".equals(tipo.getSelectedItem().toString())) {
                ArrayList<Cliente> clientes = arqClie.consultarClientesLike(tipo.getSelectedItem().toString(), id.getText());

                if (clientes != null) {
                    jTable1.addColumn("Codigo aluno");
                    jTable1.addColumn("Nome");
                    jTable1.addColumn("Rg");
                    jTable1.addColumn("Cpf");
                    jTable1.addColumn("Telefone");
                    jTable1.addColumn("Celular");
                    jTable1.addColumn("numero do Ladv");
                    jTable1.addColumn("Ativo");
                    for (Cliente clienteCons : clientes) {
                        jTable1.addRow(new Object[]{String.valueOf(clienteCons.getCodCliente()), clienteCons.getNome(), clienteCons.getRg(), clienteCons.getCpf(), clienteCons.getTelefone(), clienteCons.getCelular(), clienteCons.getNumLADV(), clienteCons.getStatus()});

                    }
                    return jTable1;

                } else {

                    return consultarAlunos();

                }
            } else {

                return consultarAlunos();
            }
        }

    }

}
