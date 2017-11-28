/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.controleDao;

import autoescola.modelo.bean.Cliente;
import autoescola.modelo.bean.Aula;
import autoescola.modelo.bean.AulasClientes;
import autoescola.modelo.bean.Funcionario;
import autoescola.modelo.bean.Veiculo;
import autoescola.modelo.dao.AulaClienteDao;
import autoescola.modelo.dao.AulaDao;
import autoescola.modelo.dao.CliDao;
import autoescola.modelo.dao.FuncionarioDao;
import autoescola.modelo.dao.VeicDao;
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
public class ControleAula extends Controle {
    private ControleCalendario calendario;
    private Aula aula = new Aula();
    private ArrayList<Cliente> clientes = null;
    private ArrayList<Cliente> clientesAulas = null;
    private ArrayList<Funcionario> instrutores = null;
    private ArrayList<Veiculo> veiculos = null;
    
    public ControleAula(ControleCalendario calendario) {
        this.calendario = calendario;
    }
    
       public ControleAula(Aula aula) {
        AulaDao ea = new AulaDao();
        this.aula = ea.consutarAulaExiste(aula.getCodAulas());
        inicializarClientesCadastradoAula();
        inicializarClientesNaoCadastradoAula();
        inicializarInstrutoresAtivos();
        incializarVeiculoAtivo();
    }

    private void inicializarClientesCadastradoAula() {
        AulaClienteDao cea = new AulaClienteDao();
        this.clientesAulas = cea.trazerClientes(aula.getCodAulas());
    }

    private void inicializarClientesNaoCadastradoAula() {
        CliDao cea = new CliDao();
        this.clientes = cea.consultarClientesAtivos();
        for (Cliente cliente : clientesAulas) {

            for (int i = 0; i < clientes.size(); i++) {
                if (cliente.getCodCliente() == clientes.get(i).getCodCliente()) {
                    this.clientes.remove(i);
                }
            }
        }
    }

    private void inicializarInstrutoresAtivos() {
        FuncionarioDao arqFunc = new FuncionarioDao();
        instrutores = arqFunc.consultarInstrutoresAtivos();

    }

    private void incializarVeiculoAtivo() {
        VeicDao arqVeic = new VeicDao();
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
        if (temAula()) {
            AulaDao ea = new AulaDao();
            VeicDao va = new VeicDao();
            aula.setVeiculo(va.consutarVeiculoExiste(codigoVeiculo));
            if (ea.alterarAula(aula)) {
                JOptionPane.showMessageDialog(null, "Veiculo inserido com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro verifique o log de erro!");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Agende a aula primeiro!");

            return false;
        }
    }

    public boolean adicionarInstrutor(int codigoFuncionario) {
        if (temAula()) {
            AulaDao ea = new AulaDao();
            FuncionarioDao fa = new FuncionarioDao();
            aula.setInstrutor(fa.consutarFuncExiste(codigoFuncionario));
            if (ea.alterarAula(aula)) {
                JOptionPane.showMessageDialog(null, "Instrutor inserido com sucesso!");

                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro verifique o log de erro!");
                return false;

            }

        } else {
            JOptionPane.showMessageDialog(null, "Agende a aula primeiro!");

            return false;
        }
    }

    public Funcionario getInstrutor() {
        return aula.getInstrutor();
    }

    public Veiculo getVeiculo() {
        return aula.getVeiculo();
    }
    
    
    public TableModel consultarAulas() {

        AulaDao arqAula = new AulaDao();
        ArrayList<Aula> aulas = arqAula.consultarData(calendario.getDiaMesAno());
        DefaultTableModel jTable1 = new DefaultTableModel();

        if (aulas != null) {
            jTable1.addColumn("Codigo Aula");
            jTable1.addColumn("Horario Inicio");
            jTable1.addColumn("Horario fim");

            for (Aula aula : aulas) {
                jTable1.addRow(new Object[]{aula.getCodAulas(), aula.getHorarioAulaInicio(), aula.getHorarioAulaFim()});
            }
            return jTable1;
        } else {
            JTable table = new JTable();
            table.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "Codigo Aula", "Horario Inicio", "Horario fim"
                    }
            ));
            return table.getModel();
        }

    }
    
    
    /**
     *
     * @return
     */
    public boolean temAula() {
        return aula.getCodAulas()!= 0;
    }
    
    
    
    
    
    @Override
    public boolean alterarStatus(boolean anterior, String idStr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        /**
     *
     * @param aula
     * @return
     */
    public boolean agendarAula(Aula aula) {
        AulaDao ea = new AulaDao();
        aula.setInstrutor(new Funcionario());
        aula.setVeiculo(new Veiculo());
        if (!aula.getDataAula().equals("") && !("").equals(aula.getHorarioAulaFim()) && !("").equals(aula.getHorarioAulaInicio())) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                Date horaInicio = sdf.parse(aula.getHorarioAulaInicio());
                Date horaFim = sdf.parse(aula.getHorarioAulaFim());
                if (horaFim.getTime() > horaInicio.getTime()) {
                    int res = ea.cadastrarAula(aula);
                    if (res != 0) {
                        aula.setCodAulas(res);
                        this.aula = aula;

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

    public boolean alterarAula(Aula aula) {

        AulaDao ea = new AulaDao();
        aula.setInstrutor(this.aula.getInstrutor());
        aula.setVeiculo(this.aula.getVeiculo());
        aula.setCodAulas(this.aula.getCodAulas());

        if (!aula.getDataAula().equals("") && !("").equals(aula.getHorarioAulaInicio()) && !("").equals(aula.getHorarioAulaFim())) {
            try {

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                Date horaInicio = sdf.parse(aula.getHorarioAulaInicio());
                Date horaFim = sdf.parse(aula.getHorarioAulaFim());
                if (horaFim.getTime() > horaInicio.getTime()) {
                    if (ea.alterarAula(aula)) {
                        this.aula = aula;

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
        AulaClienteDao eca = new AulaClienteDao();

        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente remover o aluno da aula?", "Remover o aluno da aula", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            AulasClientes ec = new AulasClientes();
            ec.setAluno(aluno);
            ec.setAulas(aula);
            //Usuário clicou em Sim. Executar o código correspondente.
            if (eca.apagarAula(ec.getAulas().getCodAulas())) {
                JOptionPane.showMessageDialog(null, "Removido da aula com sucesso");
                tirarAlunoMemoria(clientesAulas, aluno);
                CliDao ca = new CliDao();
                clientes.add(ca.consutarClienteExiste(aluno.getCodCliente()));
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
    public boolean adicionarAlunoAula(int codigoAluno) {
        if (temAula()) {
            CliDao arqAluno = new CliDao();
            Cliente aluno = arqAluno.consutarClienteExiste(codigoAluno);
            AulasClientes ec = new AulasClientes();
            ec.setAulas(aula);
            ec.setAluno(aluno);
            AulaClienteDao arq = new AulaClienteDao();

            if (arq.cadastrarAulaCliente(ec)) {
                clientesAulas.add(aluno);
                tirarAlunoMemoria(clientes, aluno);
                return true;
            } else {
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Agende a aula primeiro!");

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

    public TableModel consultarAlunosAula() {
        DefaultTableModel jTable1 = new DefaultTableModel();
        if (clientesAulas != null) {
            jTable1.addColumn("Codigo aluno");
            jTable1.addColumn("Nome");;
            for (Cliente cliente : clientesAulas) {

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

    public TableModel consultaAlunoLikeAula(JTextField id, JComboBox tipo) {
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
     * @param id
     * @return
     */
    public Aula aulaEditar() {
        return aula;
    }
}
