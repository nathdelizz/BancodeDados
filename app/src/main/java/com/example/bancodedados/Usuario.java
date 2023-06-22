package com.example.bancodedados;

public class Usuario {
    private String nomeUsuario;
    private String emailUsuario;
    private int idUsuario;

    public Usuario(int i, String s, String toString) {}
    public Usuario(String nomeUsuario, String emailUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.idUsuario = idUsuario;
    }

    protected String getEmailUsuario() {
        return emailUsuario;
    }
    protected void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    protected String getNomeUsuario() {
        return nomeUsuario;
    }

    protected void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    protected int getidUsuario() {
        return idUsuario;
    }

    protected void setIdUsuario(String nomeUsuario) {
        this.idUsuario = idUsuario;
    }
}
