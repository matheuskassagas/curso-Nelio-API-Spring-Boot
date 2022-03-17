package com.cursonelio.javaspringboot.cursoNelio.dto.Request;

public class UserRequest {

    private String email;
    private String senha;

    public UserRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
