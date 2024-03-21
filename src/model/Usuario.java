package model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String login;
    private String senha;
    private String nome;
    private String email;

    private boolean funcionario;

    public Usuario(String login, String senha, String nome, String email, boolean funcionario) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.email = email;
        this.funcionario = funcionario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isFuncionario() {
        return funcionario;
    }

    public void setFuncionario(boolean funcionario) {
        this.funcionario = funcionario;
    }
}
