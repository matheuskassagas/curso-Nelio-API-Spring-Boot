package com.cursonelio.javaspringboot.cursoNelio.dto.Request;

import com.cursonelio.javaspringboot.cursoNelio.service.validation.ClienteInsert;
import com.cursonelio.javaspringboot.cursoNelio.service.validation.CpfouCnpjValido;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@ClienteInsert
public class ClienteRequestNew {

    @NotEmpty(message = "Preenchimento obrigatorio")
    private String nome;
    @NotEmpty(message = "Preenchimento obrigatorio")
    @Email
    private String email;
    @NotEmpty
    private String cpfOuCnpj;
    private Integer tipoCliente;
    @NotEmpty
    private String logradouro;
    @NotEmpty
    private String numero;
    private String complemento;
    private String bairro;
    @NotEmpty
    private String cep;

    @NotEmpty
    private String telefones1;
    private String telefones2;
    private String telefones3;

    private Integer cidadeId;

    public ClienteRequestNew(){
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

    public Integer getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(Integer tipoCliente) {
        this.tipoCliente = tipoCliente;
    }


    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefones1() {
        return telefones1;
    }

    public void setTelefones1(String telefones1) {
        this.telefones1 = telefones1;
    }

    public String getTelefones2() {
        return telefones2;
    }

    public void setTelefones2(String telefones2) {
        this.telefones2 = telefones2;
    }

    public String getTelefones3() {
        return telefones3;
    }

    public void setTelefones3(String telefones3) {
        this.telefones3 = telefones3;
    }

    public Integer getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }

}
