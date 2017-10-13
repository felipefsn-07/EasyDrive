/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.bean;

/**
 *
 * @author felipe
 */
public class AulasClientes {
   
   private Cliente aluno;   
   private Aula aulas;

    /**
     * @return the aluno
     */
    public Cliente getAluno() {
        return aluno;
    }

    /**
     * @param aluno the aluno to set
     */
    public void setAluno(Cliente aluno) {
        this.aluno = aluno;
    }

    /**
     * @return the aulas
     */
    public Aula getAulas() {
        return aulas;
    }

    /**
     * @param aulas the aulas to set
     */
    public void setAulas(Aula aulas) {
        this.aulas = aulas;
    }
   
 }
