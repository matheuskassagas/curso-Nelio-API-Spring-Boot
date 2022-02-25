package com.cursonelio.javaspringboot.cursoNelio.service;

import com.cursonelio.javaspringboot.cursoNelio.dto.CategoriaResponse;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Categoria;
import com.cursonelio.javaspringboot.cursoNelio.repository.CategoriaRepository;
import com.cursonelio.javaspringboot.cursoNelio.service.exception.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.cursonelio.javaspringboot.cursoNelio.service.exception.ObjectNotFounfException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria find(Integer id) {
        Optional<Categoria> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFounfException(("" +
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())));
    }

    public List<CategoriaResponse> findAll(){
        return repository.findAll().stream().map(categoria -> new CategoriaResponse().toResponse(categoria)).collect(Collectors.toList());
    }

    public Categoria create (Categoria categoria){
        categoria = repository.save(categoria);
        return categoria;
    }

    public Categoria update (Categoria categoria){
        Categoria categoriaFind = find(categoria.getId());
        categoriaFind.setId(categoria.getId());
        categoriaFind.setNome(categoria.getNome());
        categoria = repository.save(categoriaFind);
        return categoria;
    }

    public void delete (Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Nao é possivel excluir uma categoria que possui produtos");
        }
    }
}



