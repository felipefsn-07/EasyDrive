/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.controle;

import static java.lang.Integer.parseInt;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author felipe
 */
public class ControleCalendario {

    private int[][] calendario;
    private int[] hoje;

    public String weekDayString(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return new DateFormatSymbols().getWeekdays()[cal.get(Calendar.DAY_OF_WEEK)];

    }

    private int weekDayInt(Date d) {
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        int dia = c.get(c.DAY_OF_WEEK);
        switch (dia) {
            case Calendar.SUNDAY:
                return 0;
            case Calendar.MONDAY:
                return 1;
            case Calendar.TUESDAY:
                return 2;
            case Calendar.WEDNESDAY:
                return 3;
            case Calendar.THURSDAY:
                return 4;
            case Calendar.FRIDAY:
                return 5;
            case Calendar.SATURDAY:
                return 6;
            default:
                return -1;
        }
    }

    private String mudarFormato(Date data) {

        DateFormat df2 = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("pt", "BR"));
        return df2.format(data);
    }

    private Date getFirstDayOfMonth(int year, int month, int day) {

        GregorianCalendar gc = new GregorianCalendar();
        gc.set(year, month, day);
        gc.add(Calendar.MONTH, -1);
        gc.set(Calendar.DAY_OF_MONTH, 1);
        return gc.getTime();
    }

    private Date getLastDayOfMonth(int year, int month, int day) {

        GregorianCalendar gc = new GregorianCalendar();
        gc.set(year, month, day);
        gc.add(Calendar.MONTH, -1);
        gc.set(Calendar.DAY_OF_MONTH, gc.getActualMaximum(Calendar.DAY_OF_MONTH));
        return gc.getTime();
    }

    private int[] separarMesDiaAno(String data) {
        String[] valoresEntreBarras = data.split("/");
        int[] i = new int[3];
        i[0] = parseInt(valoresEntreBarras[0]);
        i[1] = parseInt(valoresEntreBarras[1]);
        i[2] = parseInt(valoresEntreBarras[2]);
        return i;

    }

    private void setMes(int fim, int diaSemanaInicio, int diaFimMesAnterior) {
        int dia = 1;
        int diaProxMex = 1;
        calendario = new int[6][7];
        if (diaSemanaInicio == 0) {
            diaFimMesAnterior -= 7;
        } else {
            diaFimMesAnterior -= diaSemanaInicio;

        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                calendario[i][j] = diaFimMesAnterior;
                diaFimMesAnterior++;
                if (i == 0 && 0 == diaSemanaInicio) {

                } else if (i == 0 && (j == diaSemanaInicio || j > diaSemanaInicio) && diaSemanaInicio != 0) {
                    calendario[i][j] = dia;
                    dia++;
                } else if (i != 0) {
                    if (dia <= fim) {
                        calendario[i][j] = dia;
                        dia++;
                    } else {
                        calendario[i][j] = diaProxMex;

                        diaProxMex++;
                    }
                }

            }
        }
    }

    protected int[][] getMesCalendario() {
        return this.calendario;
    }

    protected void setMesPreviousNext(int day, int month, int year) {
        Date start = getFirstDayOfMonth(year, month, day);
        Date end = getLastDayOfMonth(year, month, day);
        Date previousMonth = getLastDayOfMonth(year, month, 1);
        String previousMonthString = mudarFormato(previousMonth);
        String endString = mudarFormato(end);
        int[] DayMonthYearFim = separarMesDiaAno(endString);
        int[] DayMonthYearPrevious = separarMesDiaAno(previousMonthString);
        int diaSemana = weekDayInt(start);
        setMes(DayMonthYearFim[0], diaSemana, DayMonthYearPrevious[0]);

    }

    public int[][] getMesCalendarioAtual() {
        Calendar rightNow = Calendar.getInstance();
        Date d = rightNow.getTime();
        String dia = mudarFormato(d);
        int[] DayMonthYear = separarMesDiaAno(dia);
        setMesPreviousNext(DayMonthYear[0], DayMonthYear[1], DayMonthYear[2]);
        return calendario;

    }

    /**
     * @return the diaAtual
     */
    public int[] getHoje() {
        Calendar rightNow = Calendar.getInstance();
        Date d = rightNow.getTime();
        String dia = mudarFormato(d);
        int[] DayMonthYear = separarMesDiaAno(dia);
        return DayMonthYear;
    }

    public int tamanhoMesAnteriorNoCalendario() {
        int tamanho = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {        
                if (calendario[i][j] == 1){
                    break;
                
                }
                tamanho++;
            }
        }
        return tamanho;
    }

}
