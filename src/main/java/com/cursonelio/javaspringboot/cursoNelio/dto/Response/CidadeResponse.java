package com.cursonelio.javaspringboot.cursoNelio.dto.Response;

import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Cidade;


public class CidadeResponse {

    private Integer id;
    private String nome;

    public CidadeResponse() {
    }

    public CidadeResponse(Integer id, String nome) {
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

    public CidadeResponse toResponse (Cidade cidade){
        return new CidadeResponse(cidade.getId(), cidade.getNome());
    }
}
