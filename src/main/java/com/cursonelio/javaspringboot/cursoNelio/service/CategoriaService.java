package com.cursonelio.javaspringboot.cursoNelio.service;

import com.cursonelio.javaspringboot.cursoNelio.dto.Response.CategoriaResponse;
import com.cursonelio.javaspringboot.cursoNelio.dto.Response.ClienteResponse;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Categoria;
import com.cursonelio.javaspringboot.cursoNelio.repository.CategoriaRepository;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Cliente;
import com.cursonelio.javaspringboot.cursoNelio.service.exception.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.cursonelio.javaspringboot.cursoNelio.service.exception.ObjectNotFounfException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Transactional(readOnly = true)
    public Categoria find(Integer id) {
        Optional<Categoria> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFounfException(("" +
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())));
    }

    @Transactional(readOnly = true)
    public CategoriaResponse findCategoriaId (Integer id){
        Optional<Categoria> cat = repository.findById(id);
        return new CategoriaResponse().toResponse(cat.get());
    }

    @Transactional(readOnly = true)
    public List<CategoriaResponse> findAll(){
        return repository.findAll().stream().map(categoria -> new CategoriaResponse().toResponse(categoria)).collect(Collectors.toList());
    }

    @Transactional
    public Categoria create (Categoria categoria){
        categoria = repository.save(categoria);
        return categoria;
    }

    @Transactional
    public Categoria update (Categoria categoria){
        Categoria categoriaFind = find(categoria.getId());
        categoriaFind.setId(categoria.getId());
        categoriaFind.setNome(categoria.getNome());
        categoria = repository.save(categoriaFind);
        return categoria;
    }

    @Transactional
    public void delete (Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Nao é possivel excluir uma categoria que possui produtos");
        }
    }

    public Page<Categoria> findPage (Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }
}



