package com.cursonelio.javaspringboot.cursoNelio.service;

import com.cursonelio.javaspringboot.cursoNelio.domain.Cliente;
import com.cursonelio.javaspringboot.cursoNelio.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cursonelio.javaspringboot.cursoNelio.service.exception.ObjectNotFounfException;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente find (Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFounfException(("" +
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())));
    }

    public List<Cliente> findAll (){
        List <Cliente> obj = clienteRepository.findAll();
        return obj;
    }

    public Cliente create (Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente update(Integer id, Cliente cliente){
        cliente.setId(id);
        cliente = clienteRepository.save(cliente);
        return cliente;
    }

    public void delete(Integer id){
        clienteRepository.deleteById(id);
    }
}
