package com.cursonelio.javaspringboot.cursoNelio.service;

import com.cursonelio.javaspringboot.cursoNelio.dto.Request.ClienteRequest;
import com.cursonelio.javaspringboot.cursoNelio.dto.Request.ClienteRequestNew;
import com.cursonelio.javaspringboot.cursoNelio.dto.Response.ClienteResponse;
import com.cursonelio.javaspringboot.cursoNelio.repository.CidadeRepository;
import com.cursonelio.javaspringboot.cursoNelio.repository.EnderecoRepository;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Categoria;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Cidade;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Cliente;
import com.cursonelio.javaspringboot.cursoNelio.repository.ClienteRepository;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Endereco;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.enuns.TipoCliente;
import com.cursonelio.javaspringboot.cursoNelio.service.exception.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.cursonelio.javaspringboot.cursoNelio.service.exception.ObjectNotFounfException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    public Cliente find(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFounfException(("" +
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())));
    }

    @Transactional(readOnly = true)
    public ClienteResponse findClienteId (Integer id){
        Optional<Cliente> cliente = repository.findById(id);
        return new ClienteResponse().toResponse(cliente.get());
    }

    @Transactional(readOnly = true)
    public List<ClienteResponse> findAll(){
        return repository.findAll().stream().map(cliente -> new ClienteResponse().toResponse(cliente)).collect(Collectors.toList());
    }

    @Transactional
    public Cliente create (Cliente cliente){
        repository.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;
    }

    public Cliente fromDTO (ClienteRequestNew clienteRequestNew){
        Cliente cli = new Cliente(null, clienteRequestNew.getNome(), clienteRequestNew.getEmail(), clienteRequestNew.getCpfOuCnpj(), TipoCliente.toEnum(clienteRequestNew.getTipoCliente()), bCryptPasswordEncoder.encode(clienteRequestNew.getSenha()));
        Endereco end = new Endereco(null, clienteRequestNew.getLogradouro(), clienteRequestNew.getNumero(), clienteRequestNew.getComplemento(), clienteRequestNew.getBairro(), clienteRequestNew.getCep(), cli, null);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(clienteRequestNew.getTelefones1());
        if (clienteRequestNew.getTelefones2()!=null){
            cli.getTelefones().add(clienteRequestNew.getTelefones2());
        }
        if (clienteRequestNew.getTelefones3()!=null){
            cli.getTelefones().add(clienteRequestNew.getTelefones3());
        }
        return cli;
    }

    @Transactional
    public Cliente update (ClienteRequest clienteRequest) throws Exception{
        Optional<Cliente> clienteFind = repository.findById(clienteRequest.getId());
        if (clienteFind.isEmpty()){
            throw new Exception("Id not found");
        }
        Cliente cliente = clienteRequest.toModel(clienteRequest);
        // Cliente cliente = new Cliente(clienteRequest.getId(), clienteRequest.getNome(), clienteRequest.getEmail());
        return repository.save(cliente);
    }


    @Transactional
    public void delete(Integer id){
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Nao é possivel excluir por que há entidades relacionadas");
        }
    }

    public Page<Cliente> findPage (Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }
}
