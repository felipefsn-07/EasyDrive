/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.controleDao;

import autoescola.modelo.arquivo.AulasArquivo;
import autoescola.modelo.arquivo.AulasClientesArquivo;
import autoescola.modelo.bean.Aula;
import autoescola.modelo.bean.AulasClientes;
import autoescola.modelo.bean.Cliente;
import autoescola.modelo.dao.AulaDao;
import static java.lang.Integer.parseInt;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author felipe
 */
public class ControleChamada extends Controle {

    private Aula aula;
    private Aula aulaDadosSimples = new Aula();

    public ControleChamada(Aula aula) {
        AulaDao ea = new AulaDao();
        this.aula = ea.consultarChamada(aula.getCodAulas());
        aulaDadosSimples.setCodAulas(this.aula.getCodAulas());
        aulaDadosSimples.setInstrutor(this.aula.getInstrutor());
        aulaDadosSimples.setDataAula(this.aula.getDataAula());
        aulaDadosSimples.setHorarioAulaFim(this.aula.getHorarioAulaFim());
        aulaDadosSimples.setHorarioAulaInicio(this.aula.getHorarioAulaInicio());
    }

    public Aula getAulaDadosSimples() {
        return this.aulaDadosSimples;
    }

    public TableModel chamada() {

        DefaultTableModel jTable1 = new DefaultTableModel();

        if (aula.getAulas() != null) {
            jTable1.addColumn("Codigo do aluno");
            jTable1.addColumn("Nome");
            jTable1.addColumn("Presente");
            for (AulasClientes aulasClientes : aula.getAulas()) {
                Cliente cliente = aulasClientes.getAluno();
                jTable1.addRow(new Object[]{String.valueOf(cliente.getCodCliente()), cliente.getNome(), aulasClientes.isPresenca()});

            }
            return jTable1;
        } else {
            JTable table = new JTable();
            table.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "Codigo do aluno", "Nome", "Codigo do aluno"
                    }
            ));
            return table.getModel();
        }

    }

    @Override
    public boolean alterarStatus(boolean anterior, String idStr) {
        if (isDigit(idStr)) {
            AulasClientesArquivo aca = new AulasClientesArquivo();
            int id = parseInt(idStr);
            int i = 0;
            for (AulasClientes aulasClientes : aula.getAulas()) {
                if (aulasClientes.getAluno().getCodCliente() == id) {
                    if (aulasClientes.isPresenca()) {
                        aulasClientes.setPresenca(false);

                    } else {
                        aulasClientes.setPresenca(true);
                    }
                    if (aca.presenca(aulasClientes)) {
                        aula.getAulas().get(i).setPresenca(aulasClientes.isPresenca());
                        return true;
                    } else {
                        return false;
                    }

                }
                i++;

            }
        }
        return false;

    }

}
