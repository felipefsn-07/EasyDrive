/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.controleDao;

import autoescola.modelo.bean.Aula;
import autoescola.modelo.bean.AulasClientes;
import autoescola.modelo.bean.Cliente;
import autoescola.modelo.bean.Endereco;
import autoescola.modelo.bean.Exame;
import autoescola.modelo.bean.ExameClientes;
import autoescola.modelo.dao.AulaClienteDao;
import autoescola.modelo.dao.CliDao;
import autoescola.modelo.dao.EnderecoDao;
import autoescola.modelo.dao.ExameClienteDao;
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
public class ControleAluno extends Controle {

    private Cliente cliente;
    private boolean editar = false;

    public boolean verificarSeCliente() {

        return cliente != null;
    }

    public boolean verificarSeEndereco() {

        return cliente.getEndereco() != null;
    }

    /**
     *
     * @return
     */
    public TableModel consultarAlunos() {

        CliDao arqCli = new CliDao();
        ArrayList<Cliente> clientes = arqCli.consultarClientes();
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
                    new Object[][]{},
                    new String[]{
                        "Codigo Funcionário", "Nome", "Rg", "Cpf", "Telefone", "Celular", "numero do Ladv", "Ativo"
                    }
            ));
            return table.getModel();
        }

    }

    @Override
    public boolean alterarStatus(boolean anterior, String idStr) {

        CliDao arq = new CliDao();
        int id = parseInt(idStr);
        cliente = arq.consutarClienteExiste(id);
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
        CliDao arqClie = new CliDao();
        DefaultTableModel jTable1 = new DefaultTableModel();

        if (tipo.getSelectedItem().toString().equals("codigo") && !"".equals(id.getText()) && isDigit(id.getText())) {
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
                    break;
                }
                return jTable1;
            }
        } else if (!"".equals(id.getText()) && !"".equals(tipo.getSelectedItem().toString())) {
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

            }
        } else if ("".equals(id.getText())) {
            return consultarAlunos();

        }
        JTable table = new JTable();
        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Codigo Funcionário", "Nome", "Rg", "Cpf", "Telefone", "Celular", "numero do Ladv", "Ativo"
                }
        ));
        return table.getModel();

    }

    public boolean cadastrarEndereco(Endereco endereco) {
        if (!endereco.getBairro().equals("") && !endereco.getCep().equals("     -   ") && !endereco.getCidade().equals("") && !endereco.getEstado().equals("") && !endereco.getLogradouro().equals("") && !endereco.getNum().equals("")) {
            if (verificarSeCliente()) {
                endereco.setStatus(cliente.getEndereco().getStatus());
                EnderecoDao arqEnd = new EnderecoDao();
                CliDao arqCli = new CliDao();
                int res = arqEnd.cadastrarEndereco(endereco);
                if (res != 0) {
                    endereco.setCodEndereco(res);
                    cliente.setEndereco(endereco);
                    arqCli.alterarCliente(cliente);
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

            if (verificarSeCliente()) {
                if (verificarSeEndereco()) {
                    EnderecoDao arqEnd = new EnderecoDao();
                    endereco.setCodEndereco(this.cliente.getEndereco().getCodEndereco());

                    if (arqEnd.alterarEndereco(endereco)) {
                        cliente.setEndereco(endereco);
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

    public boolean cadastrarAluno(Cliente aluno) {

        CliDao arqCliente = new CliDao();
        if (!"".equals(aluno.getNome()) && !"  .   .   - ".equals(aluno.getRg()) && !"   .   .   -  ".equals(aluno.getCpf()) && !"  /  /    ".equals(aluno.getDatanasc()) && !"(  )     -    ".equals(aluno.getTelefone()) && !"(  )      -    ".equals(aluno.getCelular()) && !"".equals(aluno.getCategoria()) && !"".equals(aluno.getNumLADV())) {

            if (!arqCliente.consultarRg(aluno.getRg())) {
                Endereco enderecoNovo = new Endereco();
                enderecoNovo.setCodEndereco(0);
                aluno.setEndereco(enderecoNovo);
                int res = arqCliente.cadastrarCliente(aluno);
                if (res != 0) {
                    cliente = new Cliente();
                    this.cliente = aluno;
                    this.cliente.setCodCliente(res);
                    JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
                    setEditar(true);
                    return true;

                } else {
                    JOptionPane.showMessageDialog(null, "Erro!");

                }

            } else {
                JOptionPane.showMessageDialog(null, "Aluno já cadastrado!");
            }

        } else {

            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

        }
        return false;
    }

    public boolean editarAluno(Cliente aluno) {

        CliDao arqCliente = new CliDao();
        if (!"".equals(aluno.getNome()) && !"  .   .   - ".equals(aluno.getRg()) && !"   .   .   -  ".equals(aluno.getCpf()) && !"  /  /    ".equals(aluno.getDatanasc()) && !"(  )     -    ".equals(aluno.getTelefone()) && !"(  )      -    ".equals(aluno.getCelular()) && !"".equals(aluno.getCategoria()) && !"".equals(aluno.getNumLADV())) {

            if (!arqCliente.consultarRg(aluno.getRg()) || cliente.getRg().equals(aluno.getRg())) {
                Endereco enderecoNovo = new Endereco();
                enderecoNovo.setCodEndereco(0);
                aluno.setEndereco(enderecoNovo);
                aluno.setStatus(cliente.getStatus());
                aluno.setCodCliente(this.cliente.getCodCliente());
                if (arqCliente.alterarCliente(aluno)) {
                    this.cliente = aluno;

                    JOptionPane.showMessageDialog(null, "Aluno editado com sucesso!");
                    return true;

                } else {
                    JOptionPane.showMessageDialog(null, "Erro!");

                }

            } else {
                JOptionPane.showMessageDialog(null, "Aluno já cadastrado!");
            }

        } else {

            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

        }
        return false;

    }

    public Cliente cliente(int id) {
        CliDao arq = new CliDao();
        setEditar(true);
        this.cliente = arq.consutarClienteExiste(id);
        return this.cliente;
    }


    public TableModel consultarExamesCliente() {

        DefaultTableModel jTable1 = new DefaultTableModel();
        ArrayList<ExameClientes> ec = new ArrayList();
        ExameClienteDao cea = new ExameClienteDao();
        ec = null;
        if (cliente != null) {
            ec = cea.consultarExamePorClientes(cliente.getCodCliente());
        }
        if (ec != null) {
            jTable1.addColumn("Codigo do exame");
            jTable1.addColumn("Data");
            jTable1.addColumn("Hora inicio");
            jTable1.addColumn("Hora fim");
            for (ExameClientes exameClientes : ec) {
                Exame exame = exameClientes.getExame();
                jTable1.addRow(new Object[]{String.valueOf(exame.getCodigoExame()), exame.getDataExame(), exame.getHorarioInicio(), exame.getHorarioFim()});

            }
            return jTable1;
        } else {
            JTable table = new JTable();
            table.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "Codigo do exame", "Data", "Hora inicio", "Hora fim"
                    }
            ));
            return table.getModel();
        }

    }

    public TableModel consultarAulaCliente() {

        DefaultTableModel jTable1 = new DefaultTableModel();
        ArrayList<AulasClientes> ac = null;
        AulaClienteDao cea = new AulaClienteDao();
        if (cliente != null) {
            ac = cea.consultarClientesAula(cliente.getCodCliente());
        }
        if (ac != null) {
            jTable1.addColumn("Codigo da aula");
            jTable1.addColumn("Data");
            jTable1.addColumn("Hora inicio");
            jTable1.addColumn("Hora fim");
            jTable1.addColumn("Presença");

            for (AulasClientes aulasClientes : ac) {
                Aula aula = aulasClientes.getAulas();
                if (aulasClientes.isPresenca()) {
                    jTable1.addRow(new Object[]{String.valueOf(aula.getCodAulas()), aula.getDataAula(), aula.getHorarioAulaInicio(), aula.getHorarioAulaFim(), "Presente"});
                } else {
                    jTable1.addRow(new Object[]{String.valueOf(aula.getCodAulas()), aula.getDataAula(), aula.getHorarioAulaInicio(), aula.getHorarioAulaFim(), ""});

                }

            }
            return jTable1;
        } else {
            JTable table = new JTable();
            table.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "Codigo do aula", "Data", "Hora inicio", "Hora fim", "Presença"
                    }
            ));
            return table.getModel();
        }

    }

}
