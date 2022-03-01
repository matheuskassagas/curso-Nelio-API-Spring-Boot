package com.cursonelio.javaspringboot.cursoNelio.controller;

import com.cursonelio.javaspringboot.cursoNelio.dto.Request.ClienteRequest;
import com.cursonelio.javaspringboot.cursoNelio.dto.Response.CategoriaResponse;
import com.cursonelio.javaspringboot.cursoNelio.dto.Response.ClienteResponse;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Categoria;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cursonelio.javaspringboot.cursoNelio.service.ClienteService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find (@PathVariable Integer id){
        Cliente obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findCliente (@PathVariable Integer id){
        ClienteResponse obj = service.findClienteId(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteResponse>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@Valid @RequestBody ClienteRequest clienteRequest){
        clienteRequest = service.create(clienteRequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteRequest.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@Valid @PathVariable Integer id, @RequestBody ClienteRequest clienteRequest) throws Exception {
        clienteRequest.setId(id);
        service.update(clienteRequest);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public  ResponseEntity<?> delete (@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public  ResponseEntity<Page<ClienteResponse>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue ="nome") String orderBy,
            @RequestParam(value="direction", defaultValue ="ASC") String direction){
        Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<ClienteResponse> listDTO = list.map(obj -> new ClienteResponse(obj));
        return ResponseEntity.ok().body(listDTO);
    }
}
