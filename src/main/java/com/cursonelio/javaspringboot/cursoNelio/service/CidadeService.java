package com.cursonelio.javaspringboot.cursoNelio.service;

import com.cursonelio.javaspringboot.cursoNelio.dto.Response.CidadeResponse;
import com.cursonelio.javaspringboot.cursoNelio.dto.Response.EstadoResponse;
import com.cursonelio.javaspringboot.cursoNelio.repository.CidadeRepository;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Cidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    public List<CidadeResponse> findEstado(Integer estadoId){
        return repository.findCidades(estadoId).stream().map(obj -> new CidadeResponse().toResponse(obj)).collect(Collectors.toList());
    }

}
