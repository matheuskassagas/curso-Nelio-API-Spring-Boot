package com.cursonelio.javaspringboot.cursoNelio.dto.Response;

import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Cliente;


public class ClienteResponse {

    private Integer id;
    private String nome;
    private String email;

    public ClienteResponse() {
    }
    public ClienteResponse(Cliente cliente) {
        id = cliente.getId();
        nome = cliente.getNome();
        email = cliente.getEmail();
    }

    public ClienteResponse(Integer id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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


    public ClienteResponse toResponse (Cliente cliente){
        return new ClienteResponse(cliente.getId(), cliente.getNome(), cliente.getEmail());
    }
}
