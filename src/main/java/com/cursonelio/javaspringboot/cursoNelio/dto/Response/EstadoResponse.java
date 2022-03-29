package com.cursonelio.javaspringboot.cursoNelio.dto.Response;

import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Estado;

public class EstadoResponse {

    private Integer id;
    private String nome;

    public EstadoResponse(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public EstadoResponse() {
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

    public EstadoResponse toResponse(Estado estado){
        return new EstadoResponse(estado.getId(), estado.getNome());
    }
}
