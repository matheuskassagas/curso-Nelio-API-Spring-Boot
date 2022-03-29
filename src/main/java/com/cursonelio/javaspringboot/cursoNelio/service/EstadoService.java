package com.cursonelio.javaspringboot.cursoNelio.service;

import com.cursonelio.javaspringboot.cursoNelio.dto.Response.EstadoResponse;
import com.cursonelio.javaspringboot.cursoNelio.repository.EstadoRepository;

import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    public List<EstadoResponse> findAll(){
        return repository.findAllByOrderByNome().stream().map(obj -> new EstadoResponse().toResponse(obj)).collect(Collectors.toList());
    }
}
