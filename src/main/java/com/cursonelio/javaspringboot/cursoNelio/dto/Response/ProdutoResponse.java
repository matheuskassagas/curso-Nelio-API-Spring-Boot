package com.cursonelio.javaspringboot.cursoNelio.dto.Response;

import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Produto;

public class ProdutoResponse {

    private Integer id;
    private String nome;
    private Double preco;

    public ProdutoResponse() {
    }

    public ProdutoResponse(Produto obj) {
        id = obj.getId();
        nome = obj.getNome();
        preco = obj.getPreco();
    }


    public ProdutoResponse(Integer id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public ProdutoResponse toResponse (Produto produto){
        return new ProdutoResponse(produto.getId(), produto.getNome(), produto.getPreco());
    }
}
