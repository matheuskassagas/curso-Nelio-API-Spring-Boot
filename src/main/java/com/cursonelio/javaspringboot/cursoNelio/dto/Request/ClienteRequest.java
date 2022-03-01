package com.cursonelio.javaspringboot.cursoNelio.dto.Request;

import com.cursonelio.javaspringboot.cursoNelio.annotations.CpfouCnpjValido;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Cliente;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.enuns.TipoCliente;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class ClienteRequest {
    private Integer id;
    @NotEmpty(message = "Preenchimento obrigatorio")
    private String nome;
    @NotEmpty(message = "Preenchimento obrigatorio")
    @Email
    private String email;
    @CpfouCnpjValido
    @NotEmpty
    private String cpfOuCnpj;
    private TipoCliente tipoCliente;

    public ClienteRequest() {
    }

    public ClienteRequest(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipoCliente) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipoCliente = tipoCliente;
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

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Cliente toModel (ClienteRequest clienteRequest){
        return new Cliente(clienteRequest.getId(), clienteRequest.getNome(), clienteRequest.getEmail(), clienteRequest.getCpfOuCnpj(), clienteRequest.getTipoCliente());
    }
}
