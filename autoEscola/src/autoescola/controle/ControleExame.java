/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.controle;

import autoescola.modelo.arquivo.FuncionarioArquivo;
import autoescola.modelo.bean.Cliente;
import autoescola.modelo.bean.Funcionario;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JOptionPane;

/**
 *
 * @author felipe
 */
public class ControleExame extends ControleCalendario {

    private int mes;
    private int ano;

    private void setMes(int mes) {
        this.mes = mes;
    }

    private int getMes() {

        return this.mes;
    }

    private void setAno(int ano) {
        this.ano = ano;
    }

    private int getAno() {

        return this.ano;
    }

    /**
     *
     * @return
     */
    public TableModel calendarioAtual() {
        setMes(getHoje()[1]);
        setAno(getHoje()[2]);

        int[][] calendario = getMesCalendarioAtual();
        DefaultTableModel jTable1 = new DefaultTableModel();
        jTable1.addColumn("Dom");
        jTable1.addColumn("Seg");
        jTable1.addColumn("Ter");
        jTable1.addColumn("Qua");
        jTable1.addColumn("Qui");
        jTable1.addColumn("Sex");
        jTable1.addColumn("Sab");
        //jTable1.setRowHeight(height); 
        for (int i = 0; i < 6; i++) {
            jTable1.addRow(new Object[]{calendario[i][0], calendario[i][1], calendario[i][2], calendario[i][3], calendario[i][4], calendario[i][5], calendario[i][6]});

        }

        return jTable1;

    }

    /**
     *
     * @return
     */
    public TableModel calendarioMesAnterior() {
        if (mes > 1) {
            setMesPreviousNext(getHoje()[0], getMes() - 1, getAno());
            mes--;
            int[][] calendario = getMesCalendario();

            DefaultTableModel jTable1 = new DefaultTableModel();
            jTable1.addColumn("Dom");
            jTable1.addColumn("Seg");
            jTable1.addColumn("Ter");
            jTable1.addColumn("Qua");
            jTable1.addColumn("Qui");
            jTable1.addColumn("Sex");
            jTable1.addColumn("Sab");
            //jTable1.setRowHeight(height); 
            for (int i = 0; i < 6; i++) {
                jTable1.addRow(new Object[]{calendario[i][0], calendario[i][1], calendario[i][2], calendario[i][3], calendario[i][4], calendario[i][5], calendario[i][6]});

            }

            return jTable1;
        }else{
        setMesPreviousNext(getHoje()[0], 1, getAno());
            int[][] calendario = getMesCalendario();

            DefaultTableModel jTable1 = new DefaultTableModel();
            jTable1.addColumn("Dom");
            jTable1.addColumn("Seg");
            jTable1.addColumn("Ter");
            jTable1.addColumn("Qua");
            jTable1.addColumn("Qui");
            jTable1.addColumn("Sex");
            jTable1.addColumn("Sab");
            //jTable1.setRowHeight(height); 
            for (int i = 0; i < 6; i++) {
                jTable1.addRow(new Object[]{calendario[i][0], calendario[i][1], calendario[i][2], calendario[i][3], calendario[i][4], calendario[i][5], calendario[i][6]});

            }

            return jTable1;

        
        
        }

    }

    /**
     *
     * @return
     */
    
    public TableModel calendarioProximoMes() {
        if (mes <12) {
            setMesPreviousNext(getHoje()[0], getMes() - 1, getAno());
            mes++;
            int[][] calendario = getMesCalendario();

            DefaultTableModel jTable1 = new DefaultTableModel();
            jTable1.addColumn("Dom");
            jTable1.addColumn("Seg");
            jTable1.addColumn("Ter");
            jTable1.addColumn("Qua");
            jTable1.addColumn("Qui");
            jTable1.addColumn("Sex");
            jTable1.addColumn("Sab");
            //jTable1.setRowHeight(height); 
            for (int i = 0; i < 6; i++) {
                jTable1.addRow(new Object[]{calendario[i][0], calendario[i][1], calendario[i][2], calendario[i][3], calendario[i][4], calendario[i][5], calendario[i][6]});

            }

            return jTable1;
        }else{
        setMesPreviousNext(getHoje()[0], 12, getAno());
            int[][] calendario = getMesCalendario();

            DefaultTableModel jTable1 = new DefaultTableModel();
            jTable1.addColumn("Dom");
            jTable1.addColumn("Seg");
            jTable1.addColumn("Ter");
            jTable1.addColumn("Qua");
            jTable1.addColumn("Qui");
            jTable1.addColumn("Sex");
            jTable1.addColumn("Sab");
            //jTable1.setRowHeight(height); 
            for (int i = 0; i < 6; i++) {
                jTable1.addRow(new Object[]{calendario[i][0], calendario[i][1], calendario[i][2], calendario[i][3], calendario[i][4], calendario[i][5], calendario[i][6]});

            }

            return jTable1;

        
        
        }

    }
    public String mesAnteriorProximo() {

        switch (mes) {
            case 1:
                return "Janeiro";
            case 2:
                return "Fevereiro";
            case 3:
                return "Março";
            case 4:
                return "Abril";
            case 5:
                return "Maio";
            case 6:
                return "Junho";

            case 7:
                return "Julho";

            case 8:
                return "Agosto";
            case 9:
                return "Setembro";

            case 10:
                return "Outubro";
            case 11:
                return "Novembro";
            case 12:
                return "Dezembro";

        }
        return "Janeiro";
    }

}
