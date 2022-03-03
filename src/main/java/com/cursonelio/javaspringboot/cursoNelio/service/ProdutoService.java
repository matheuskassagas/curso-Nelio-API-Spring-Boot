package com.cursonelio.javaspringboot.cursoNelio.service;
import com.cursonelio.javaspringboot.cursoNelio.dto.Response.PedidoResponse;
import com.cursonelio.javaspringboot.cursoNelio.dto.Response.ProdutoResponse;
import com.cursonelio.javaspringboot.cursoNelio.repository.CategoriaRepository;
import com.cursonelio.javaspringboot.cursoNelio.repository.ProdutoRepository;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Categoria;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Cliente;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Pedido;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Produto;
import com.cursonelio.javaspringboot.cursoNelio.service.exception.ObjectNotFounfException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public Produto find (Integer id){
        Optional<Produto> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFounfException(("" +
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())));
    }

    public Page<Produto> search (String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return repository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponse> findAll(){
        return repository.findAll().stream().map(produto -> new ProdutoResponse().toResponse(produto)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProdutoResponse findProdutoId (Integer id){
        Optional<Produto> produto = repository.findById(id);
        return new ProdutoResponse().toResponse(produto.get());
    }


}
