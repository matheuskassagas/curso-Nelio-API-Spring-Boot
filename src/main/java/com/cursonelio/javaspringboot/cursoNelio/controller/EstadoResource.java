package com.cursonelio.javaspringboot.cursoNelio.controller;

import com.cursonelio.javaspringboot.cursoNelio.dto.Response.CidadeResponse;
import com.cursonelio.javaspringboot.cursoNelio.dto.Response.EstadoResponse;
import com.cursonelio.javaspringboot.cursoNelio.service.CidadeService;
import com.cursonelio.javaspringboot.cursoNelio.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CidadeService cidadeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll (){
        List<EstadoResponse> obj = estadoService.findAll();
        return ResponseEntity.ok().body(obj);
    }
    @RequestMapping(value = "/{estadoId}/cidades", method = RequestMethod.GET)
    public ResponseEntity<?> findCidades (@PathVariable Integer estadoId){
        List<CidadeResponse> obj = cidadeService.findEstado(estadoId);
        return ResponseEntity.ok().body(obj);
    }
}
