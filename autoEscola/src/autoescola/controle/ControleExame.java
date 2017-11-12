/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.controle;

import autoescola.modelo.arquivo.ClienteArquivo;
import autoescola.modelo.arquivo.ClienteExameArquivo;
import autoescola.modelo.arquivo.ExameArquivo;
import autoescola.modelo.bean.Cliente;
import autoescola.modelo.bean.Exame;
import autoescola.modelo.bean.ExameClientes;
import autoescola.modelo.bean.Funcionario;
import autoescola.modelo.bean.Veiculo;
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
    private ArrayList<Cliente> clientes;
    private ArrayList<Cliente> clientesExames = new ArrayList();

    public ControleExame(ControleCalendario calendario) {
        this.calendario = calendario;
        clientes = new ArrayList();

    }
    /**
     * 
     */
    public ControleExame() {
        ClienteArquivo arqCli = new ClienteArquivo();
        clientes = arqCli.consultarClientesAtivos();

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

    /**
     * 
     * @param aluno
     * @return 
     */
    public boolean deletarAluno(Cliente aluno) {
        ClienteExameArquivo eca = new ClienteExameArquivo();

        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "Excluir aluno", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            ExameClientes ec = new ExameClientes();
            ec.setCliente(aluno);
            ec.setExame(exame);
            //Usuário clicou em Sim. Executar o código correspondente.
            if (eca.apagarAlunosExame(ec)) {
                JOptionPane.showMessageDialog(null, "Deletado com sucesso");
                removerAluno(aluno);
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

    /**
     * 
     * @param aluno 
     */
    private void removerAlunoJaCadastrado(Cliente aluno) {
        if (aluno != null) {
            for (int i = 0; i < clientes.size(); i++) {
                if (clientes.get(i).getCodCliente() == aluno.getCodCliente()) {
                    clientes.remove(i);

                }
            }
        }

    }

    /**
     * 
     * @param aluno 
     */
    private void removerAluno(Cliente aluno) {
        if (aluno != null) {
            for (int i = 0; i < clientesExames.size(); i++) {
                if (clientesExames.get(i).getCodCliente() == aluno.getCodCliente()) {
                    clientesExames.remove(i);

                }
            }
        }

    }

    /**
     * 
     * @param codigoAluno
     * @return 
     */
    public boolean adicionarAlunoExame(int codigoAluno) {
        ClienteArquivo arqAluno = new ClienteArquivo();
        Cliente aluno = arqAluno.consultar(codigoAluno);
        ExameClientes ec = new ExameClientes();
        ec.setExame(exame);
        ec.setCliente(aluno);
        ClienteExameArquivo arq = new ClienteExameArquivo();

        if (arq.cadastrarExameClientes(ec)) {
            removerAlunoJaCadastrado(aluno);
            clientesExames.add(aluno);
            exame.getAlunos().add(ec);
            return true;
        }

        return false;

    }

    /**
     * 
     * @return 
     */
    public TableModel consultarAlunos() {
        // ClienteArquivo arqCli = new ClienteArquivo();

        //clientes = arqCli.consultarClientesAtivos();
        DefaultTableModel jTable1 = new DefaultTableModel();

        if (clientes != null) {
            jTable1.addColumn("Codigo aluno");
            jTable1.addColumn("Nome");
            jTable1.addColumn("Rg");
            jTable1.addColumn("Cpf");
            jTable1.addColumn("Telefone");
            jTable1.addColumn("Celular");
            jTable1.addColumn("numero do Ladv");

            for (Cliente cliente : clientes) {

                if (exame.getAlunos() != null) {
                    for (ExameClientes clienteExame : exame.getAlunos()) {
                        if (cliente.getCodCliente() != clienteExame.getCliente().getCodCliente()) {
                            jTable1.addRow(new Object[]{String.valueOf(cliente.getCodCliente()), cliente.getNome(), cliente.getRg(), cliente.getCpf(), cliente.getTelefone(), cliente.getCelular(), cliente.getNumLADV()});
                        }

                    }
                } else {
                    jTable1.addRow(new Object[]{String.valueOf(cliente.getCodCliente()), cliente.getNome(), cliente.getRg(), cliente.getCpf(), cliente.getTelefone(), cliente.getCelular(), cliente.getNumLADV()});
                }

            }
            return jTable1;

        }
        JTable table = new JTable();
        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Codigo do aluno", "Nome", "Rg", "Cpf", "Telefone", "Celular", "numero do Ladv"
                }
        ));
        return table.getModel();

    }

    /**
     * 
     * @param id
     * @param tipo
     * @return 
     */
    public TableModel consultaAlunoLikeExame(JTextField id, JComboBox tipo) {
        DefaultTableModel jTable1 = new DefaultTableModel();

        if (tipo.getSelectedItem().toString().equals("codigo") && !"".equals(id.getText()) && isDigit(id.getText())) {
            // cliente = arqClie.consultar(parseInt(id.getText()));
            for (Cliente cliente : clientes) {
                if (cliente.getCodCliente() == parseInt(id.getText())) {
                    jTable1.addColumn("Codigo aluno");
                    jTable1.addColumn("Nome");
                    jTable1.addColumn("Rg");
                    jTable1.addColumn("Cpf");
                    jTable1.addColumn("Telefone");
                    jTable1.addColumn("Celular");
                    jTable1.addColumn("numero do Ladv");
                    jTable1.addRow(new Object[]{String.valueOf(cliente.getCodCliente()), cliente.getNome(), cliente.getRg(), cliente.getCpf(), cliente.getTelefone(), cliente.getCelular(), cliente.getNumLADV()});

                    return jTable1;
                }
            }
        } else if (tipo.getSelectedItem().toString().equals("nome") && !"".equals(id.getText()) && isDigit(id.getText())) {
            for (Cliente cliente : clientes) {
                if (cliente.getNome().contains(String.valueOf(id))) {
                    jTable1.addColumn("Codigo aluno");
                    jTable1.addColumn("Nome");
                    jTable1.addColumn("Rg");
                    jTable1.addColumn("Cpf");
                    jTable1.addColumn("Telefone");
                    jTable1.addColumn("Celular");
                    jTable1.addColumn("numero do Ladv");
                    jTable1.addRow(new Object[]{String.valueOf(cliente.getCodCliente()), cliente.getNome(), cliente.getRg(), cliente.getCpf(), cliente.getTelefone(), cliente.getCelular(), cliente.getNumLADV()});

                    return jTable1;
                }
            }

        } else if (tipo.getSelectedItem().toString().equals("rg") && !"".equals(id.getText()) && isDigit(id.getText())) {
            for (Cliente cliente : clientes) {
                if (cliente.getRg().contains(String.valueOf(id))) {
                    jTable1.addColumn("Codigo aluno");
                    jTable1.addColumn("Nome");
                    jTable1.addColumn("Rg");
                    jTable1.addColumn("Cpf");
                    jTable1.addColumn("Telefone");
                    jTable1.addColumn("Celular");
                    jTable1.addColumn("numero do Ladv");
                    jTable1.addRow(new Object[]{String.valueOf(cliente.getCodCliente()), cliente.getNome(), cliente.getRg(), cliente.getCpf(), cliente.getTelefone(), cliente.getCelular(), cliente.getNumLADV()});

                    return jTable1;
                }
            }

        } else if (tipo.getSelectedItem().toString().equals("cpf") && !"".equals(id.getText()) && isDigit(id.getText())) {
            for (Cliente cliente : clientes) {
                if (cliente.getCpf().contains(String.valueOf(id))) {
                    jTable1.addColumn("Codigo aluno");
                    jTable1.addColumn("Nome");
                    jTable1.addColumn("Rg");
                    jTable1.addColumn("Cpf");
                    jTable1.addColumn("Telefone");
                    jTable1.addColumn("Celular");
                    jTable1.addColumn("numero do Ladv");
                    jTable1.addRow(new Object[]{String.valueOf(cliente.getCodCliente()), cliente.getNome(), cliente.getRg(), cliente.getCpf(), cliente.getTelefone(), cliente.getCelular(), cliente.getNumLADV()});

                    return jTable1;
                }
            }

        } else if (tipo.getSelectedItem().toString().equals("numero do Ladv") && !"".equals(id.getText()) && isDigit(id.getText())) {
            for (Cliente cliente : clientes) {
                if (cliente.getNumLADV().contains(String.valueOf(id))) {
                    jTable1.addColumn("Codigo aluno");
                    jTable1.addColumn("Nome");
                    jTable1.addColumn("Rg");
                    jTable1.addColumn("Cpf");
                    jTable1.addColumn("Telefone");
                    jTable1.addColumn("Celular");
                    jTable1.addColumn("numero do Ladv");
                    jTable1.addRow(new Object[]{String.valueOf(cliente.getCodCliente()), cliente.getNome(), cliente.getRg(), cliente.getCpf(), cliente.getTelefone(), cliente.getCelular(), cliente.getNumLADV()});

                    return jTable1;
                }
            }

        }
        return consultarAlunos();
    }

    /**
     *
     * @return
     */
    public TableModel consultarAlunosExame() {

        DefaultTableModel jTable1 = new DefaultTableModel();
      
        if (exame.getAlunos() != null) {
            jTable1.addColumn("Codigo aluno");
            jTable1.addColumn("Nome");

            for (Cliente cliente : clientesExames) {
                //Cliente cliente = exameClientes.getCliente();
                jTable1.addRow(new Object[]{String.valueOf(cliente.getCodCliente()), cliente.getNome()});
            }
            return jTable1;
        } else {
            JTable table = new JTable();
            table.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "Codigo do aluno", "Nome"
                    }
            ));
            return table.getModel();
        }

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
    public Exame exameEditar(int id) {
        ExameArquivo ea = new ExameArquivo();
        exame = ea.consultar(id);
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
