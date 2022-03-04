package com.cursonelio.javaspringboot.cursoNelio.controller;

import com.cursonelio.javaspringboot.cursoNelio.dto.Response.ClienteResponse;
import com.cursonelio.javaspringboot.cursoNelio.dto.Response.PedidoResponse;
import com.cursonelio.javaspringboot.cursoNelio.dto.Response.ProdutoResponse;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Categoria;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cursonelio.javaspringboot.cursoNelio.service.PedidoService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@Valid @RequestBody Pedido pedido){
        pedido = service.create(pedido);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){
        Pedido obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/pedido/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findPedido (@PathVariable Integer id){
        PedidoResponse obj = service.findClienteId(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PedidoResponse>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }
}
