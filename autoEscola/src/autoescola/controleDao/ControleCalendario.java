/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.controleDao;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author felipe
 */
public class ControleCalendario extends Calendario {

    private int mes;
    private int ano;
    private int dia;

    /**
     *
     * @return the mes
     */
    public int getMesControle() {

        return this.mes;
    }

    /**
     *
     * @param ano
     */
    private void setAno(int ano) {
        this.ano = ano;
    }

    /**
     *
     * @return the ano
     */
    public int getAno() {

        return this.ano;
    }

    /**
     * @return the dia
     */
    public int getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     *
     * @return
     */
    public TableModel calendarioAtual() {
        this.ano = this.getHoje()[2];
        this.mes = this.getHoje()[1];
        this.dia = this.getHoje()[0];
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
     * Colocar na tabela o mes anterior
     *
     * @return
     */
    public TableModel calendarioMesAnterior() {
        // JOptionPane.showMessageDialog(null, mes);
        mes--;

        if (mes >= 1) {

            setMesPreviousNext(mes, 2017);
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
        } else {
            mes = 11;
            return calendarioProximoMes();

        }

    }

    /**
     * Colocar na tabela o proximo mês
     *
     * @return
     */
    public TableModel calendarioProximoMes() {
        mes++;

        if (mes <= 12) {

            setMesPreviousNext(mes, getAno());
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
        } else {
            mes = 2;
            return calendarioMesAnterior();

        }

    }

    /**
     * Colocar texto do mês anterior
     *
     * @return
     */
    public String mesAnteriorProximo() {

        switch (mes) {
            case 1:
                return "Janeiro  ";
            case 2:
                return "Fevereiro";
            case 3:
                return "Março    ";
            case 4:
                return "Abril    ";
            case 5:
                return "Maio     ";
            case 6:
                return "Junho    ";

            case 7:
                return "Julho    ";

            case 8:
                return "Agosto   ";
            case 9:
                return "Setembro ";

            case 10:
                return "Outubro  ";
            case 11:
                return "Novembro ";
            case 12:
                return "Dezembro ";

        }
        return "Janeiro";
    }

    public TableModel calendarioAnoAnterior() {
        if (ano > 1999) {
            setMesPreviousNext(getMesControle(), getAno() - 1);
            ano--;
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
        } else {
            setMesPreviousNext(1, getAno());
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
     * Colocar na tabela o proximo mês
     *
     * @return
     */
    public TableModel calendarioProximoAno() {
        ano++;
        setMesPreviousNext(getMesControle(), getAno());

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

    /**
     * @return the diaMesAno
     */
    public String getDiaMesAno() {
        String diaS = dia < 10 ? "0" : "";
        String mesS = mes < 10 ? "0" : "";

        return diaS+String.valueOf(dia) + "/" + mesS+String.valueOf(mes) + "/" + String.valueOf(ano);

    }
}
