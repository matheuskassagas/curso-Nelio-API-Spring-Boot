package com.cursonelio.javaspringboot.cursoNelio.service;

import com.cursonelio.javaspringboot.cursoNelio.dto.Response.ClienteResponse;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Cliente;
import com.cursonelio.javaspringboot.cursoNelio.repository.ClienteRepository;
import com.cursonelio.javaspringboot.cursoNelio.service.exception.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.cursonelio.javaspringboot.cursoNelio.service.exception.ObjectNotFounfException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente find(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFounfException(("" +
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())));
    }

    public List<ClienteResponse> findAll(){
        return repository.findAll().stream().map(cliente -> new ClienteResponse().toResponse(cliente)).collect(Collectors.toList());
    }

    public Cliente create (Cliente cliente){
        return repository.save(cliente);

    }

    public Cliente update(Cliente cliente){
        Cliente clienteFind = find(cliente.getId());
        clienteFind.setId(cliente.getId());
        clienteFind.setNome(cliente.getNome());
        cliente = repository.save(clienteFind);
        return cliente;
    }

    public void delete(Integer id){
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Nao é possivel excluir uma categoria que possui produtos");
        }
    }
}
