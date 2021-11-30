package com.cursonelio.javaspringboot.cursoNelio.service;


import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Pedido;
import com.cursonelio.javaspringboot.cursoNelio.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import com.cursonelio.javaspringboot.cursoNelio.service.exception.ObjectNotFounfException;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private PedidoRepository pedidoRepository;

    public Pedido buscar (Integer id){
        Optional<Pedido> obj = pedidoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFounfException(("" +
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())));
    }

    public List<Pedido> findAll (){
        List <Pedido> obj = pedidoRepository.findAll();
        return obj;
    }

    public Pedido create (Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    public Pedido update(Integer id, Pedido pedido){
        pedido.setId(id);
        pedido = pedidoRepository.save(pedido);
        return pedido;
    }

    public void delete(Integer id){
        pedidoRepository.deleteById(id);
    }
}
