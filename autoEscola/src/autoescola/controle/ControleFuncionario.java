/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.controle;

import autoescola.modelo.arquivo.FuncionarioArquivo;
import autoescola.modelo.arquivo.InstrutorArquivo;
import autoescola.modelo.arquivo.UsuarioArquivo;
import autoescola.modelo.bean.Endereco;
import autoescola.modelo.bean.Funcionario;
import autoescola.modelo.bean.Instrutor;
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
    private boolean botao;

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

    protected boolean isDigit(String s) {
        char ch = s.charAt(0);
        return (ch >= 48 && ch <= 57);
    }

    protected String pegarNumero(String variavel) {
        String a = "";
        for (int i = 0; i < variavel.length(); i++) {
            if (variavel.charAt(i) >= 48 && variavel.charAt(i) <= 57) {
                a += variavel.charAt(i);
            }
        }
        return a;
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
        String senha = getRandomPass(8);
        usuario.setSenha(senha);

        return senha;

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

    public boolean cadastrarFuncionario(String nome, String rg, String cpf, String dataNasc, String telefone, String celular, String horaEntra, String horaSai, String tipo, String carteira, String categoria) {
        FuncionarioArquivo arq = new FuncionarioArquivo();
        UsuarioArquivo arqUser = new UsuarioArquivo();

        funcionario = new Funcionario();
        usuario = new Usuario();
        endereco = new Endereco();
        if (!"".equals(nome) && !"  .   .   - ".equals(rg) && !"   .   .   -  ".equals(cpf) && !"  /  /    ".equals(dataNasc) && !"(  )     -    ".equals(telefone) && !"".equals(celular) && !"  :  ".equals(horaEntra) && !"  :  ".equals(horaSai)) {
            switch (tipo) {
                case "Gerente":
                case "Recepcionista":
                    funcionario.setNome(nome);
                    funcionario.setRg(rg);
                    funcionario.setCpf(cpf);
                    funcionario.setDatanasc(dataNasc);
                    funcionario.setTelefone(telefone);
                    funcionario.setCelular(celular);
                    funcionario.setHora_entra(horaEntra);
                    funcionario.setHora_sai(horaSai);
                    funcionario.setTipo(tipo);
                    funcionario.setStatus(true);
                    usuario.setCodLogin(0);
                    usuario.setLogin(pegarNumero(rg));
                    funcionario.setUsuario(usuario);
                    endereco.setCodEndereco(0);
                    funcionario.setEndereco(endereco);

                    if (arq.cadastrarfuncionario(funcionario)) {
                        int user = arqUser.cadastrarUsuario(usuario);
                        usuario.setCodLogin(user);
                        funcionario.setUsuario(usuario);
                        arq.alterarfuncionario(funcionario);
                        JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Não foi cadastrado!");
                        return false;

                    }
                case "Instrutor":
                    if (!"".equals(carteira) && !"".equals(categoria)) {

                        Instrutor instrutor = new Instrutor();
                        instrutor.setNome(nome);
                        instrutor.setRg(rg);
                        instrutor.setCpf(cpf);
                        instrutor.setDatanasc(dataNasc);
                        instrutor.setTelefone(telefone);
                        instrutor.setCelular(celular);
                        instrutor.setHora_entra(horaEntra);
                        instrutor.setHora_sai(horaSai);
                        instrutor.setTipo(tipo);
                        instrutor.setNumCarteira(carteira);
                        instrutor.setCategoria(categoria);
                        instrutor.setStatus(true);
                        usuario.setCodLogin(0);
                        usuario.setLogin(pegarNumero(rg));
                        instrutor.setUsuario(usuario);
                        endereco.setCodEndereco(0);
                        instrutor.setEndereco(endereco);

                        InstrutorArquivo arqInstrutor = new InstrutorArquivo();
                        if (arqInstrutor.cadastrarInstrutor(instrutor)) {
                            int user = arqUser.cadastrarUsuario(usuario);
                            usuario.setCodLogin(user);
                            instrutor.setUsuario(usuario);
                            arq.alterarfuncionario(instrutor);
                            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
                            return true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Não foi cadastrado!");
                            return false;

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Preeencha todos os campos!");
                        return false;

                    }
                default:
                    JOptionPane.showMessageDialog(null, "Opção invalida!");
                    return false;
            }
        } else {

            JOptionPane.showMessageDialog(null, "Preeencha todos os campos!");
            return false;
        }
    }

    public void alterarSenha() {
        //setBotao(true);
        if (!"".equals(usuario.getSenha())) {
            UsuarioArquivo arq = new UsuarioArquivo();
            if (arq.alterarUsuario(usuario)) {
                JOptionPane.showMessageDialog(null, "Alterado com sucesso!");


            } else {
                JOptionPane.showMessageDialog(null, "Erro ao alterar senha!");

            }

        } else {
            JOptionPane.showMessageDialog(null, "Cadastre o funcinário primeiro!");
        }

    }

    public boolean alterarFuncionario() {
        return false;
    }

    public boolean verificarSeFuncinario() {

        return funcionario != null;
    }

    public boolean verificarSeEndereco() {

        return endereco != null;
    }

    public boolean verificarSeUsuario() {

        return usuario != null;
    }

    /**
     * @return the botao
     */
    public boolean isBotao() {
        return botao;
    }

    /**
     * @param botao the botao to set
     */
    private void setBotao(boolean botao) {
        this.botao = botao;
    }

}
