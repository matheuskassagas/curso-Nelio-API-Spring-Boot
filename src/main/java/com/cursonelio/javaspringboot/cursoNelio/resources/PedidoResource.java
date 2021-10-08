package com.cursonelio.javaspringboot.cursoNelio.resources;

import com.cursonelio.javaspringboot.cursoNelio.domain.Cliente;
import com.cursonelio.javaspringboot.cursoNelio.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cursonelio.javaspringboot.cursoNelio.service.PedidoService;

import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){
        Pedido obj = pedidoService.buscar(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Pedido> findAll(){
        List<Pedido> pedidos=  pedidoService.findAll();
        return pedidos;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Pedido create(@RequestBody Pedido pedido){
        return pedidoService.create(pedido);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Pedido update(@PathVariable Integer id, @RequestBody Pedido pedido){
        return pedidoService.update(id, pedido);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete (@PathVariable Integer id){
        pedidoService.delete(id);
    }
}
