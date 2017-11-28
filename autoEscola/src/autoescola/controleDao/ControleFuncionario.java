/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.controleDao;

import autoescola.modelo.bean.Endereco;
import autoescola.modelo.bean.Funcionario;
import autoescola.modelo.bean.Usuario;
import autoescola.modelo.dao.EnderecoDao;
import autoescola.modelo.dao.FuncionarioDao;
import autoescola.modelo.dao.UsuarioDao;
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
public class ControleFuncionario extends Controle {

    private Usuario usuario;
    private Funcionario funcionario;
    private boolean botao = false;

    public TableModel consultarFuncionarios() {

        FuncionarioDao daoFunc = new FuncionarioDao();
        ArrayList<Funcionario> funcionarios = daoFunc.consultarFuncionarios();
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
                    new Object[][]{},
                    new String[]{
                        "Codigo Funcionário", "Nome", "Rg", "Cpf", "Telefone", "Celular", "Ativo"
                    }
            ));
            return table.getModel();
        }

    }

    public boolean isFuncionarioInstrutor() {
        return true;
    }

    public void consultar(int id) {
        //JOptionPane.showMessageDialog(null, id);
        if (id != 0) {
            FuncionarioDao daoFuncionario = new FuncionarioDao();
            UsuarioDao daoUsuario = new UsuarioDao();
            this.funcionario = daoFuncionario.consutarFuncExiste(id);
            this.usuario = daoUsuario.consultarFuncionarioUsuario(id);            
        }

    }

    public Funcionario getFuncionario() {
        this.setBotao(true);
        return funcionario;
    }

    public TableModel consultaFuncionarioLike(JTextField id, JComboBox tipo) {
        FuncionarioDao daoFunc = new FuncionarioDao();
        DefaultTableModel jTable1 = new DefaultTableModel();

        if (tipo.getSelectedItem().toString().equals("codFunc") && !"".equals(id.getText()) && isDigit(id.getText())) {
            ArrayList<Funcionario> funcionarios = daoFunc.consultarfuncionariosLike("codFunc", id.getText());
            if (funcionarios != null) {
                jTable1.addColumn("Codigo Funcionário");
                jTable1.addColumn("Nome");
                jTable1.addColumn("Rg");
                jTable1.addColumn("Cpf");
                jTable1.addColumn("Telefone");
                jTable1.addColumn("Celular");
                jTable1.addColumn("Ativo");

                for (Funcionario funcionarioCons : funcionarios) {
                    jTable1.addRow(new Object[]{String.valueOf(funcionarioCons.getCodigoFuncionario()), funcionarioCons.getNome(), funcionarioCons.getRg(), funcionarioCons.getCpf(), funcionarioCons.getTelefone(), funcionarioCons.getCelular(), funcionarioCons.getStatus()});
                    break;
                }
                return jTable1;

            }

        } else if (!"".equals(id.getText()) && !"".equals(tipo.getSelectedItem().toString())) {
            ArrayList<Funcionario> funcionarios = daoFunc.consultarfuncionariosLike(tipo.getSelectedItem().toString(), id.getText());

            if (funcionarios != null) {
                jTable1.addColumn("Codigo Funcionário");
                jTable1.addColumn("Nome");
                jTable1.addColumn("Rg");
                jTable1.addColumn("Cpf");
                jTable1.addColumn("Telefone");
                jTable1.addColumn("Celular");
                jTable1.addColumn("Ativo");

                for (Funcionario funcionarioCons : funcionarios) {
                    jTable1.addRow(new Object[]{String.valueOf(funcionarioCons.getCodigoFuncionario()), funcionarioCons.getNome(), funcionarioCons.getRg(), funcionarioCons.getCpf(), funcionarioCons.getTelefone(), funcionarioCons.getCelular(), funcionarioCons.getStatus()});
                }

                return jTable1;

            }
        } else if ("".equals(id.getText())) {
            return consultarFuncionarios();
        }

        JTable table = new JTable();
        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Codigo Funcionário", "Nome", "Rg", "Cpf", "Telefone", "Celular", "Ativo"
                }
        ));
        return table.getModel();
    }

    /**
     *
     * @param anterior
     * @param idStr
     * @return
     */
    @Override
    public boolean alterarStatus(boolean anterior, String idStr) {

        FuncionarioDao dao = new FuncionarioDao();
        int id = parseInt(idStr);
        funcionario = dao.consutarFuncExiste(id);
        if (isDigit(idStr)) {
            if (anterior != funcionario.getStatus()) {
                if (funcionario.getStatus()) {
                    return dao.desativar(id);
                } else {
                    return dao.ativar(id);

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

    public boolean cadastrarFuncionario(String nome, String rg, String cpf, String dataNasc, String telefone, String celular, String horaEntra, String horaSai, String tipo, String carteira, String categoria, String senha) {
        FuncionarioDao dao = new FuncionarioDao();

        if (!dao.consultarRg(rg)) {
            UsuarioDao daoUser = new UsuarioDao();
            usuario = new Usuario();
            funcionario = new Funcionario();
            Endereco novoEndereco = new Endereco();
            if (!"".equals(nome) && !"  .   .   - ".equals(rg) && !"   .   .   -  ".equals(cpf) && !"  /  /    ".equals(dataNasc) && !"(  )     -    ".equals(telefone) && !"".equals(celular) && !"  :  ".equals(horaEntra) && !"  :  ".equals(horaSai) && !"".equals(senha)) {
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
                funcionario.setNumCarteira("null");
                funcionario.setCategoria("null");
                if (tipo.equals("Instrutor")) {
                    if (!"".equals(carteira) && !"".equals(categoria)) {

                        funcionario.setNumCarteira(carteira);
                        funcionario.setCategoria(categoria);

                    } else {
                        JOptionPane.showMessageDialog(null, "Preeencha todos os campos!");

                        return false;
                    }

                }
                usuario.setLogin(pegarNumero(rg));
                usuario.setSenha(senha);
                novoEndereco.setCodEndereco(0);
                novoEndereco.setStatus(1);
                funcionario.setEndereco(novoEndereco);
                int res = dao.cadastrarFuncionario(funcionario);
                if (res != 0) {
                    funcionario.setEndereco(null);
                    funcionario.setCodigoFuncionario(res);
                    usuario.setFucionario(funcionario);
                    usuario.setStatus(1);
                    daoUser.cadastrarUsuario(usuario);
                    setBotao(true);

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
        } else {
            JOptionPane.showMessageDialog(null, "Funcionário já cadastrado!");
            return false;

        }

    }

    public void alterarSenha(String senha) {
        //setBotao(true);
        if (!"".equals(senha)) {
            UsuarioDao dao = new UsuarioDao();
            usuario.setSenha(senha);
            if (dao.alterarUsuario(usuario)) {
                JOptionPane.showMessageDialog(null, "Alterado com sucesso!");

            } else {
                JOptionPane.showMessageDialog(null, "Erro ao alterar senha!");

            }

        } else {
            JOptionPane.showMessageDialog(null, "Cadastre o funcinário primeiro!");
        }

    }

    public boolean editarFuncionario(String nome, String rg, String cpf, String dataNasc, String telefone, String celular, String horaEntra, String horaSai, String tipo, String carteira, String categoria, String senha) {
        FuncionarioDao dao = new FuncionarioDao();
        if (!dao.consultarRg(rg) || funcionario.getRg().equals(rg)) {

            UsuarioDao daoUser = new UsuarioDao();
            usuario = new Usuario();
            if (!"".equals(nome) && !"  .   .   - ".equals(rg) && !"   .   .   -  ".equals(cpf) && !"  /  /    ".equals(dataNasc) && !"(  )     -    ".equals(telefone) && !"".equals(celular) && !"  :  ".equals(horaEntra) && !"  :  ".equals(horaSai) && !"".equals(senha)) {
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
                funcionario.setNumCarteira("null");
                funcionario.setCategoria("null");
                if (tipo.equals("Instrutor")) {
                    if (!"".equals(carteira) && !"".equals(categoria)) {

                        funcionario.setNumCarteira(carteira);
                        funcionario.setCategoria(categoria);

                    } else {

                        JOptionPane.showMessageDialog(null, "Preeencha todos os campos!");

                        return false;
                    }

                }
                usuario.setLogin(pegarNumero(rg));
                usuario.setSenha(senha);
                if (dao.alterarFunc(funcionario)) {
                    usuario.setFucionario(funcionario);
                    usuario.setStatus(1);
                    daoUser.alterarUsuario(usuario);
                    setBotao(true);

                    JOptionPane.showMessageDialog(null, "Editado com sucesso!");
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi Editado!");
                    return false;
                }
            } else {

                JOptionPane.showMessageDialog(null, "Preeencha todos os campos!");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Funcionário já cadastrado!");
            return false;

        }

    }

    public boolean verificarSeFuncinario() {

        return funcionario != null;
    }

    public boolean verificarSeEndereco() {

        return funcionario.getEndereco() != null;
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

    public boolean cadastrarEndereco(Endereco endereco) {
        if (!endereco.getBairro().equals("") && !endereco.getCep().equals("     -   ") && !endereco.getCidade().equals("") && !endereco.getEstado().equals("") && !endereco.getLogradouro().equals("") && !endereco.getNum().equals("")) {
            if (verificarSeFuncinario()) {
                EnderecoDao daoEnd = new EnderecoDao();
                FuncionarioDao daoFunc = new FuncionarioDao();
                int res = daoEnd.cadastrarEndereco(endereco);
                if (res != 0) {
                    endereco.setCodEndereco(res);
                    funcionario.setEndereco(endereco);
                    daoFunc.alterarFunc(funcionario);
                    JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cadastre o funcionário primeiro", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;

        }
    }

    public void editarEndereco(Endereco endereco) {
        if (!endereco.getBairro().equals("") && !endereco.getCep().equals("     -   ") && !endereco.getCidade().equals("") && !endereco.getEstado().equals("") && !endereco.getLogradouro().equals("") && !endereco.getNum().equals("")) {

            if (verificarSeFuncinario()) {
                if (verificarSeEndereco()) {
                    EnderecoDao daoEnd = new EnderecoDao();
                    endereco.setCodEndereco(this.funcionario.getEndereco().getCodEndereco());

                    if (daoEnd.alterarEndereco(endereco)) {
                       
                        funcionario.setEndereco(daoEnd.consultar(endereco.getCodEndereco()));
                        JOptionPane.showMessageDialog(null, "Editado com sucesso");

                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao editar", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao editar", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } else {
                JOptionPane.showMessageDialog(null, "Cadastre o funcionário primeiro", "Error", JOptionPane.ERROR_MESSAGE);

            }

        } else {

            JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

}
