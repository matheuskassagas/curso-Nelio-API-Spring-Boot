package com.cursonelio.javaspringboot.cursoNelio.service;


import com.cursonelio.javaspringboot.cursoNelio.dto.Response.CategoriaResponse;
import com.cursonelio.javaspringboot.cursoNelio.dto.Response.PedidoResponse;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Pedido;
import com.cursonelio.javaspringboot.cursoNelio.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import com.cursonelio.javaspringboot.cursoNelio.service.exception.ObjectNotFounfException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private PedidoRepository repository;

    public Pedido find (Integer id){
        Optional<Pedido> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFounfException(("" +
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())));
    }

    public List<PedidoResponse> findAll(){
        return repository.findAll().stream().map(pedido -> new PedidoResponse().toResponse(pedido)).collect(Collectors.toList());
    }

}
