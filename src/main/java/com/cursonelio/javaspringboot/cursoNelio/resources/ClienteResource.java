package com.cursonelio.javaspringboot.cursoNelio.resources;

import com.cursonelio.javaspringboot.cursoNelio.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cursonelio.javaspringboot.cursoNelio.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find (@PathVariable Integer id){
        Cliente obj = clienteService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Cliente> findAll(){
        List<Cliente> clientes=  clienteService.findAll();
        return clientes;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Cliente create(@RequestBody Cliente cliente){
        return clienteService.create(cliente);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Cliente update(@PathVariable Integer id, @RequestBody Cliente cliente){
        return clienteService.update(id, cliente);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete (@PathVariable Integer id){
        clienteService.delete(id);
    }
}
