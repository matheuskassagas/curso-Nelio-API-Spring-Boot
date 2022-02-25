package com.cursonelio.javaspringboot.cursoNelio.dto.Response;

import com.cursonelio.javaspringboot.cursoNelio.repository.entity.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PedidoResponse {

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private Date instante;
    private Pagamento pagamento;
    private Cliente cliente;
    private Endereco enderecoDeEntrega;
    private Set<ItemPedido> itens = new HashSet<>();

    public PedidoResponse() {
    }

    public PedidoResponse(Integer id, Date instante, Pagamento pagamento, Cliente cliente, Endereco enderecoDeEntrega, Set<ItemPedido> itens) {
        this.id = id;
        this.instante = instante;
        this.pagamento = pagamento;
        this.cliente = cliente;
        this.enderecoDeEntrega = enderecoDeEntrega;
        this.itens = itens;
    }

    public PedidoResponse(Pedido pedido) {
        id = pedido.getId();
        instante = pedido.getInstante();
        pagamento = pedido.getPagamento();
        cliente = pedido.getCliente();
        enderecoDeEntrega = pedido.getEnderecoDeEntrega();
        itens = pedido.getItens();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstante() {
        return instante;
    }

    public void setInstante(Date instante) {
        this.instante = instante;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEnderecoDeEntrega() {
        return enderecoDeEntrega;
    }

    public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public PedidoResponse toResponse (Pedido pedido){
        return new PedidoResponse(pedido.getId(), pedido.getInstante(), pedido.getPagamento(), pedido.getCliente(), pedido.getEnderecoDeEntrega(), pedido.getItens());
    }
}
