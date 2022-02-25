package com.cursonelio.javaspringboot.cursoNelio.dto;

import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Categoria;

public class CategoriaResponse {
    private Integer id;
    private String nome;

    public CategoriaResponse() {
    }

    public CategoriaResponse(Categoria obj) {
        id = obj.getId();
        nome = getNome();
    }

    public CategoriaResponse(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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

    public CategoriaResponse toResponse(Categoria categoria){
        return new CategoriaResponse(categoria.getId(), categoria.getNome());
    }
}
