/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.controle;

import autoescola.modelo.arquivo.ClienteArquivo;
import autoescola.modelo.arquivo.ClienteExameArquivo;
import autoescola.modelo.arquivo.ExameArquivo;
import autoescola.modelo.arquivo.FuncionarioArquivo;
import autoescola.modelo.arquivo.VeiculoArquivo;
import autoescola.modelo.bean.Cliente;
import autoescola.modelo.bean.Exame;
import autoescola.modelo.bean.ExameClientes;
import autoescola.modelo.bean.Funcionario;
import autoescola.modelo.bean.Veiculo;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class ControleExame extends Controle {

    private ControleCalendario calendario;
    private Exame exame = new Exame();
    private ArrayList<Cliente> clientes = null;
    private ArrayList<Cliente> clientesExames = null;
    private ArrayList<Funcionario> instrutores = null;
    private ArrayList<Veiculo> veiculos = null;

    public ControleExame(ControleCalendario calendario) {
        this.calendario = calendario;

    }

    public ControleExame(Exame exame) {
        ExameArquivo ea = new ExameArquivo();
        this.exame = ea.consultar(exame.getCodigoExame());
        inicializarClientesCadastradoExame();
        inicializarClientesNaoCadastradoExame();
        inicializarInstrutoresAtivos();
        incializarVeiculoAtivo();
    }

    private void inicializarClientesCadastradoExame() {
        ClienteExameArquivo cea = new ClienteExameArquivo();
        this.clientesExames = cea.trazerClientes(exame.getCodigoExame());
    }

    private void inicializarClientesNaoCadastradoExame() {
        ClienteArquivo cea = new ClienteArquivo();
        this.clientes = cea.consultarClientesAtivos();
        for (Cliente cliente : clientesExames) {

            for (int i = 0; i < clientes.size(); i++) {
                if (cliente.getCodCliente() == clientes.get(i).getCodCliente()) {
                    this.clientes.remove(i);
                }
            }
        }
    }

    private void inicializarInstrutoresAtivos() {
        FuncionarioArquivo arqFunc = new FuncionarioArquivo();
        instrutores = arqFunc.consultarInstrutoresAtivos();

    }

    private void incializarVeiculoAtivo() {
        VeiculoArquivo arqVeic = new VeiculoArquivo();
        veiculos = arqVeic.consultarVeiculos();
    }

    public TableModel consultarInstrutoresAtivos() {

        DefaultTableModel jTable1 = new DefaultTableModel();

        if (instrutores != null) {
            jTable1.addColumn("Codigo Funcionário");
            jTable1.addColumn("Nome");
            jTable1.addColumn("Rg");
            jTable1.addColumn("Cpf");
            jTable1.addColumn("Telefone");
            jTable1.addColumn("Celular");
            int i = 0;
            for (Funcionario funcionario : instrutores) {
                jTable1.addRow(new Object[]{String.valueOf(funcionario.getCodigoFuncionario()), funcionario.getNome(), funcionario.getRg(), funcionario.getCpf(), funcionario.getTelefone(), funcionario.getCelular()});

            }
            return jTable1;
        } else {
            JTable table = new JTable();
            table.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "Codigo Funcionário", "Nome", "Rg", "Cpf", "Telefone", "Celular"
                    }
            ));
            return table.getModel();
        }

    }

    public TableModel consultarVeiculosAtivos() {

        DefaultTableModel jTable1 = new DefaultTableModel();

        if (veiculos != null) {
            jTable1.addColumn("Codigo veiculo");
            jTable1.addColumn("placa");
            jTable1.addColumn("ano");
            jTable1.addColumn("modelo");
            jTable1.addColumn("capacidade");
            for (Veiculo veiculoCons : veiculos) {
                jTable1.addRow(new Object[]{String.valueOf(veiculoCons.getCodVeiculo()), veiculoCons.getPlaca(), veiculoCons.getAno(), veiculoCons.getModelo(), veiculoCons.getCapacidade()});

            }
            return jTable1;
        } else {
            JTable table = new JTable();
            table.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "Codigo veiculo", "placa", "ano", "modelo", "capacidade"
                    }
            ));
            return table.getModel();
        }

    }

    public TableModel consultaVeiculoLike(JTextField id, JComboBox tipo) {

        DefaultTableModel jTable1 = new DefaultTableModel();
        jTable1.addColumn("Codigo veiculo");
        jTable1.addColumn("placa");
        jTable1.addColumn("ano");
        jTable1.addColumn("modelo");
        jTable1.addColumn("capacidade");

        if (!id.getText().equals("") && veiculos != null) {
            switch (tipo.getSelectedItem().toString()) {

                case "codigo do veiculo":

                    for (Veiculo veiculoCons : veiculos) {
                        if (veiculoCons.getCodVeiculo() == parseInt(id.getText())) {
                            jTable1.addRow(new Object[]{String.valueOf(veiculoCons.getCodVeiculo()), veiculoCons.getPlaca(), veiculoCons.getAno(), veiculoCons.getModelo(), veiculoCons.getCapacidade()});
                            return jTable1;
                        }
                    }
                    return jTable1;
                case "placa":
                    for (Veiculo veiculoCons : veiculos) {
                        if (veiculoCons.getPlaca().contains(id.getText())) {
                            jTable1.addRow(new Object[]{String.valueOf(veiculoCons.getCodVeiculo()), veiculoCons.getPlaca(), veiculoCons.getAno(), veiculoCons.getModelo(), veiculoCons.getCapacidade()});
                        }
                    }
                    return jTable1;

                case "ano":
                    for (Veiculo veiculoCons : veiculos) {
                        if (veiculoCons.getAno().contains(id.getText())) {
                            jTable1.addRow(new Object[]{String.valueOf(veiculoCons.getCodVeiculo()), veiculoCons.getPlaca(), veiculoCons.getAno(), veiculoCons.getModelo(), veiculoCons.getCapacidade()});
                        }
                    }
                    return jTable1;
                case "modelo":
                    for (Veiculo veiculoCons : veiculos) {
                        if (veiculoCons.getModelo().contains(id.getText())) {
                            jTable1.addRow(new Object[]{String.valueOf(veiculoCons.getCodVeiculo()), veiculoCons.getPlaca(), veiculoCons.getAno(), veiculoCons.getModelo(), veiculoCons.getCapacidade()});
                        }
                    }
                    return jTable1;
                case "capacidade":
                    for (Veiculo veiculoCons : veiculos) {
                        if (veiculoCons.getCapacidade() == (parseFloat(id.getText()))) {
                            jTable1.addRow(new Object[]{String.valueOf(veiculoCons.getCodVeiculo()), veiculoCons.getPlaca(), veiculoCons.getAno(), veiculoCons.getModelo(), veiculoCons.getCapacidade()});
                        }
                    }
                    return jTable1;

            }
        }
        return consultarVeiculosAtivos();

    }

    public TableModel consultaFuncionarioLike(JTextField id, JComboBox tipo) {

        DefaultTableModel jTable1 = new DefaultTableModel();
        jTable1.addColumn("Codigo Funcionário");
        jTable1.addColumn("Nome");
        jTable1.addColumn("Rg");
        jTable1.addColumn("Cpf");
        jTable1.addColumn("Telefone");
        jTable1.addColumn("Celular");
        if (!id.getText().equals("") && instrutores != null) {
            switch (tipo.getSelectedItem().toString()) {

                case "codigo":
                    for (Funcionario funcionario : instrutores) {
                        if (funcionario.getCodigoFuncionario() == parseInt(id.getText())) {
                            jTable1.addRow(new Object[]{String.valueOf(funcionario.getCodigoFuncionario()), funcionario.getNome(), funcionario.getRg(), funcionario.getCpf(), funcionario.getTelefone(), funcionario.getCelular()});
                            return jTable1;
                        }
                    }
                    return jTable1;
                case "nome":
                    for (Funcionario funcionario : instrutores) {
                        if (funcionario.getNome().contains(id.getText())) {
                            jTable1.addRow(new Object[]{String.valueOf(funcionario.getCodigoFuncionario()), funcionario.getNome(), funcionario.getRg(), funcionario.getCpf(), funcionario.getTelefone(), funcionario.getCelular()});
                        }

                    }
                    return jTable1;

                case "rg":
                    for (Funcionario funcionario : instrutores) {
                        if (funcionario.getRg().contains(id.getText())) {
                            jTable1.addRow(new Object[]{String.valueOf(funcionario.getCodigoFuncionario()), funcionario.getNome(), funcionario.getRg(), funcionario.getCpf(), funcionario.getTelefone(), funcionario.getCelular()});
                        }
                    }
                    return jTable1;
                case "cpf":
                    for (Funcionario funcionario : instrutores) {
                        if (funcionario.getCpf().contains(id.getText())) {
                            jTable1.addRow(new Object[]{String.valueOf(funcionario.getCodigoFuncionario()), funcionario.getNome(), funcionario.getRg(), funcionario.getCpf(), funcionario.getTelefone(), funcionario.getCelular()});
                        }
                    }
                    return jTable1;

            }
        }
        return consultarInstrutoresAtivos();

    }

    public boolean adicionarVeiculo(int codigoVeiculo) {
        if (temExame()) {
            ExameArquivo ea = new ExameArquivo();
            VeiculoArquivo va = new VeiculoArquivo();
            exame.setVeiculo(va.consultar(codigoVeiculo));
            if (ea.alterarExame(exame)) {
                JOptionPane.showMessageDialog(null, "Veiculo inserido com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro verifique o log de erro!");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Agende o exame primeiro!");

            return false;
        }
    }

    public boolean adicionarInstrutor(int codigoFuncionario) {
        if (temExame()) {
            ExameArquivo ea = new ExameArquivo();
            FuncionarioArquivo fa = new FuncionarioArquivo();
            exame.setInstrutor(fa.consultar(codigoFuncionario));
            if (ea.alterarExame(exame)) {
                JOptionPane.showMessageDialog(null, "Instrutor inserido com sucesso!");

                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro verifique o log de erro!");
                return false;

            }

        } else {
            JOptionPane.showMessageDialog(null, "Agende o exame primeiro!");

            return false;
        }
    }

    public Funcionario getInstrutor() {
        return exame.getInstrutor();
    }

    public Veiculo getVeiculo() {
        return exame.getVeiculo();
    }

    /**
     *
     * @return
     */
    public TableModel consultarExames() {

        ExameArquivo arqExame = new ExameArquivo();
        ArrayList<Exame> exames = arqExame.consultarData(calendario.getDiaMesAno());
        DefaultTableModel jTable1 = new DefaultTableModel();

        if (exames != null) {
            jTable1.addColumn("Codigo Exame");
            jTable1.addColumn("Horario Inicio");
            jTable1.addColumn("Horario fim");

            for (Exame exame : exames) {
                jTable1.addRow(new Object[]{exame.getCodigoExame(), exame.getHorarioInicio(), exame.getHorarioFim()});
            }
            return jTable1;
        } else {
            JTable table = new JTable();
            table.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "Codigo Exame", "Horario Inicio", "Horario fim"
                    }
            ));
            return table.getModel();
        }

    }

    /**
     *
     * @param exame
     * @return
     */
    public boolean agendarExame(Exame exame) {
        ExameArquivo ea = new ExameArquivo();
        exame.setInstrutor(new Funcionario());
        exame.setVeiculo(new Veiculo());
        if (!exame.getDataExame().equals("") && !("").equals(exame.getHorarioFim()) && !("").equals(exame.getHorarioInicio())) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                Date horaInicio = sdf.parse(exame.getHorarioInicio());
                Date horaFim = sdf.parse(exame.getHorarioFim());
                if (horaFim.getTime() > horaInicio.getTime()) {
                    int res = ea.cadastrarExame(exame);
                    if (res != 0) {
                        exame.setCodigoExame(res);
                        this.exame = exame;

                        JOptionPane.showMessageDialog(null, "Agendado com sucesso!");
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro verifque o log de erro!");

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Horário fora do limite!");

                }

            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, e);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Preeencha todos os campos!");
        }
        return false;

    }

    public boolean alterarExame(Exame exame) {

        ExameArquivo ea = new ExameArquivo();
        exame.setInstrutor(this.exame.getInstrutor());
        exame.setVeiculo(this.exame.getVeiculo());
        exame.setCodigoExame(this.exame.getCodigoExame());

        if (!exame.getDataExame().equals("") && !("").equals(exame.getHorarioFim()) && !("").equals(exame.getHorarioInicio())) {
            try {

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                Date horaInicio = sdf.parse(exame.getHorarioInicio());
                Date horaFim = sdf.parse(exame.getHorarioFim());
                if (horaFim.getTime() > horaInicio.getTime()) {
                    if (ea.alterarExame(exame)) {
                        this.exame = exame;

                        JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro verifque o log de erro!");

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Horário fora do limite!");

                }

            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, e);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Preeencha todos os campos!");
        }
        return false;

    }

    /**
     *
     * @param aluno
     * @return
     */
    public boolean deletarAluno(Cliente aluno) {
        ClienteExameArquivo eca = new ClienteExameArquivo();

        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente remover o aluno do exame?", "Remover o aluno do exame", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            ExameClientes ec = new ExameClientes();
            ec.setCliente(aluno);
            ec.setExame(exame);
            //Usuário clicou em Sim. Executar o código correspondente.
            if (eca.apagarAlunosExame(ec)) {
                JOptionPane.showMessageDialog(null, "Removido do exame com sucesso");
                tirarAlunoMemoria(clientesExames, aluno);
                ClienteArquivo ca = new ClienteArquivo();
                clientes.add(ca.consultar(aluno.getCodCliente()));
                return true;
            }

        } else if (resposta == JOptionPane.NO_OPTION) {
            //Usuário clicou em não. Executar o código correspondente.
            return false;
        }
        return false;

    }

    public void tirarAlunoMemoria(ArrayList<Cliente> c, Cliente cliente) {
        for (int i = 0; i < c.size(); i++) {
            if (c.get(i).getCodCliente() == cliente.getCodCliente()) {
                c.remove(i);
            }
        }
    }

    /**
     *
     * @param codigoAluno
     * @return
     */
    public boolean adicionarAlunoExame(int codigoAluno) {
        if (temExame()) {
            ClienteArquivo arqAluno = new ClienteArquivo();
            Cliente aluno = arqAluno.consultar(codigoAluno);
            ExameClientes ec = new ExameClientes();
            ec.setExame(exame);
            ec.setCliente(aluno);
            ClienteExameArquivo arq = new ClienteExameArquivo();

            if (arq.cadastrarExameClientes(ec)) {
                clientesExames.add(aluno);
                tirarAlunoMemoria(clientes, aluno);
                return true;
            } else {
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Agende o exame primeiro!");

            return false;
        }

    }

    public TableModel consultarAlunos() {
        DefaultTableModel jTable1 = new DefaultTableModel();
        if (clientes != null) {
            jTable1.addColumn("Codigo aluno");
            jTable1.addColumn("Nome");
            jTable1.addColumn("Rg");
            jTable1.addColumn("Cpf");
            jTable1.addColumn("Telefone");
            jTable1.addColumn("Celular");
            jTable1.addColumn("Numero do Ladv");
            for (Cliente cliente : clientes) {

                jTable1.addRow(new Object[]{String.valueOf(cliente.getCodCliente()), cliente.getNome(), cliente.getRg(), cliente.getCpf(), cliente.getTelefone(), cliente.getCelular(), cliente.getNumLADV()});

            }
            return jTable1;

        }
        JTable table = new JTable();
        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Codigo aluno", "Nome", "Rg", "Cpf", "Telefone", "Celular", "Numero do Ladv"
                }
        ));
        return table.getModel();

    }

    public TableModel consultarAlunosExame() {
        DefaultTableModel jTable1 = new DefaultTableModel();
        if (clientesExames != null) {
            jTable1.addColumn("Codigo aluno");
            jTable1.addColumn("Nome");;
            for (Cliente cliente : clientesExames) {

                jTable1.addRow(new Object[]{String.valueOf(cliente.getCodCliente()), cliente.getNome()});

            }
            return jTable1;

        }
        JTable table = new JTable();
        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Codigo aluno", "Nome"
                }
        ));
        return table.getModel();

    }

    public TableModel consultaAlunoLikeExame(JTextField id, JComboBox tipo) {
        DefaultTableModel jTable1 = new DefaultTableModel();

        jTable1.addColumn("Codigo aluno");
        jTable1.addColumn("Nome");
        jTable1.addColumn("Rg");
        jTable1.addColumn("Cpf");
        jTable1.addColumn("Telefone");
        jTable1.addColumn("Celular");
        jTable1.addColumn("numero do Ladv");

        if (tipo.getSelectedItem().toString().equals("codigo") && !"".equals(id.getText()) && isDigit(id.getText())) {
            for (Cliente cliente : clientes) {

                if (cliente.getCodCliente() == parseInt(id.getText())) {

                    jTable1.addRow(new Object[]{String.valueOf(cliente.getCodCliente()), cliente.getNome(), cliente.getRg(), cliente.getCpf(), cliente.getTelefone(), cliente.getCelular(), cliente.getNumLADV()});

                }
            }
            return jTable1;

        } else if (tipo.getSelectedItem().toString().equals("nome") && !"".equals(id.getText())) {
            for (Cliente cliente : clientes) {
                if (cliente.getNome().contains(id.getText())) {

                    jTable1.addRow(new Object[]{String.valueOf(cliente.getCodCliente()), cliente.getNome(), cliente.getRg(), cliente.getCpf(), cliente.getTelefone(), cliente.getCelular(), cliente.getNumLADV()});

                }
            }
            return jTable1;

        } else if (tipo.getSelectedItem().toString().equals("rg") && !"".equals(id.getText())) {
            for (Cliente cliente : clientes) {
                if (cliente.getRg().contains(id.getText())) {

                    jTable1.addRow(new Object[]{String.valueOf(cliente.getCodCliente()), cliente.getNome(), cliente.getRg(), cliente.getCpf(), cliente.getTelefone(), cliente.getCelular(), cliente.getNumLADV()});

                }

            }
            return jTable1;

        } else if (tipo.getSelectedItem().toString().equals("cpf") && !"".equals(id.getText())) {
            for (Cliente cliente : clientes) {
                if (cliente.getCpf().contains(String.valueOf(id))) {

                    jTable1.addRow(new Object[]{String.valueOf(cliente.getCodCliente()), cliente.getNome(), cliente.getRg(), cliente.getCpf(), cliente.getTelefone(), cliente.getCelular(), cliente.getNumLADV()});

                }
            }
            return jTable1;

        } else if (tipo.getSelectedItem().toString().equals("numero do Ladv") && !"".equals(id.getText())) {
            for (Cliente cliente : clientes) {
                if (cliente.getNumLADV().contains(String.valueOf(id))) {

                    jTable1.addRow(new Object[]{String.valueOf(cliente.getCodCliente()), cliente.getNome(), cliente.getRg(), cliente.getCpf(), cliente.getTelefone(), cliente.getCelular(), cliente.getNumLADV()});

                }
            }
            return jTable1;

        } else if ("".equals(id.getText())) {
            return consultarAlunos();
        }
        JTable table = new JTable();
        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Codigo aluno", "Nome", "Rg", "Cpf", "Telefone", "Celular", "Numero do Ladv"
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param id
     * @return
     */
    public Exame exameEditar() {
        return exame;
    }

    /**
     *
     * @return
     */
    public boolean temExame() {

        return exame.getCodigoExame() != 0;
    }
}
