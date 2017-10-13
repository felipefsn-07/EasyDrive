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
public class Usuario {

    private int codLogin;
    private String login;
    private String senha;

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the codLogin
     */
    public int getCodLogin() {
        return codLogin;
    }

    /**
     * @param codLogin the codLogin to set
     */
    public void setCodLogin(int codLogin) {
        this.codLogin = codLogin;
    }
}
