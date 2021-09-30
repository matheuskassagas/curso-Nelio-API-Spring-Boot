package com.cursonelio.javaspringboot.cursoNelio.service;


import com.cursonelio.javaspringboot.cursoNelio.domain.Pedido;
import com.cursonelio.javaspringboot.cursoNelio.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import com.cursonelio.javaspringboot.cursoNelio.service.exception.ObjectNotFounfException;

import java.util.Optional;

@Service
public class PedidoService {

    private PedidoRepository pedidoRepository;

    public Pedido buscar (Integer id){
        Optional<Pedido> obj = pedidoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFounfException(("" +
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())));


    }
}
