/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.controle;

import autoescola.modelo.arquivo.FuncionarioArquivo;
import autoescola.modelo.bean.Endereco;
import autoescola.modelo.bean.Funcionario;
import autoescola.modelo.bean.Usuario;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Random;
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
public class ControleFuncionario {

    private Usuario usuario;
    private Funcionario funcionario;
    private Endereco endereco;

    public TableModel consultarFuncionarios() {

        FuncionarioArquivo arqFunc = new FuncionarioArquivo();
        ArrayList<Funcionario> funcionarios = arqFunc.consultarFuncionario();
        DefaultTableModel jTable1 = new DefaultTableModel();

        if (funcionarios != null) {
            jTable1.addColumn("Codigo Funcionário");
            jTable1.addColumn("Nome");
            jTable1.addColumn("Rg");
            jTable1.addColumn("Cpf");
            jTable1.addColumn("Telefone");
            jTable1.addColumn("Celular");
            jTable1.addColumn("Ativo");
            int i = 0;
            for (Funcionario funcionario : funcionarios) {
                jTable1.addRow(new Object[]{String.valueOf(funcionario.getCodigoFuncionario()), funcionario.getNome(), funcionario.getRg(), funcionario.getCpf(), funcionario.getTelefone(), funcionario.getCelular(), funcionario.getStatus()});

                if (funcionario.getStatus()) {

                }
                i++;
            }
            return jTable1;
        } else {
            JTable table = new JTable();
            table.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{
                        {null, null, null, null, null, null},},
                    new String[]{
                        "Codigo Funcionário", "Nome", "Rg", "Cpf", "Telefone", "Celular", "Ativo"
                    }
            ));
            return table.getModel();
        }

    }

    protected Funcionario funcionario(JTextField id) {
        FuncionarioArquivo funcionario = new FuncionarioArquivo();
        return funcionario.consultar(parseInt(id.getText()));

    }

    boolean isDigit(String s) {
        char ch = s.charAt(0);
        return (ch >= 48 && ch <= 57);
    }

    public TableModel consultaFuncionario(JTextField id, JComboBox tipo) {
        FuncionarioArquivo arqFunc = new FuncionarioArquivo();
        DefaultTableModel jTable1 = new DefaultTableModel();

        if (tipo.getSelectedItem().toString().equals("codigo") && !"".equals(id.getText()) && isDigit(id.getText())) {
            Funcionario funcionario = arqFunc.consultar(parseInt(id.getText()));

            if (funcionario != null) {
                jTable1.addColumn("Codigo Funcionário");
                jTable1.addColumn("Nome");
                jTable1.addColumn("Rg");
                jTable1.addColumn("Cpf");
                jTable1.addColumn("Telefone");
                jTable1.addColumn("Celular");
                jTable1.addColumn("Ativo");

                jTable1.addRow(new Object[]{String.valueOf(funcionario.getCodigoFuncionario()), funcionario.getNome(), funcionario.getRg(), funcionario.getCpf(), funcionario.getTelefone(), funcionario.getCelular(), funcionario.getStatus()});
                return jTable1;

            } else {
                return consultarFuncionarios();

            }

        } else {
            if (!"".equals(id.getText()) && !"".equals(tipo.getSelectedItem().toString())) {
                ArrayList<Funcionario> funcionarios = arqFunc.consultarfuncionariosLike(tipo.getSelectedItem().toString(), id.getText());

                if (funcionarios != null) {
                    jTable1.addColumn("Codigo Funcionário");
                    jTable1.addColumn("Nome");
                    jTable1.addColumn("Rg");
                    jTable1.addColumn("Cpf");
                    jTable1.addColumn("Telefone");
                    jTable1.addColumn("Celular");
                    jTable1.addColumn("Ativo");

                    for (Funcionario funcionario : funcionarios) {
                        jTable1.addRow(new Object[]{String.valueOf(funcionario.getCodigoFuncionario()), funcionario.getNome(), funcionario.getRg(), funcionario.getCpf(), funcionario.getTelefone(), funcionario.getCelular(), funcionario.getStatus()});
                    }

                    return jTable1;

                } else {

                    return consultarFuncionarios();

                }
            } else {

                return consultarFuncionarios();
            }
        }

    }

    public boolean alterarStatus(boolean anterior, String idStr) {

        FuncionarioArquivo arq = new FuncionarioArquivo();
        int id = parseInt(idStr);
        Funcionario funcionario = arq.consultar(id);
        if (isDigit(idStr)) {
            if (anterior != funcionario.getStatus()) {
                if (funcionario.getStatus()) {
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

    private String getRandomPass(int len) {
        char[] chart = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char[] senha = new char[len];
        int chartLenght = chart.length;
        Random rdm = new Random();
        for (int x = 0; x < len; x++) {

            senha[x] = chart[rdm.nextInt(chartLenght)];
        }
        return new String(senha);
    }

    public String gerarSenha() {

        return getRandomPass(8);
    }

    public String valorUsuario(int tipo) {

        switch (tipo) {
            case 0:
                if (usuario != null) {
                    return usuario.getLogin();
                } else {
                    return "";

                }
            case 1:

                if (usuario != null) {
                    return usuario.getSenha();
                } else {
                    return getRandomPass(8);

                }
            default:
                return "";
        }

    }

}
