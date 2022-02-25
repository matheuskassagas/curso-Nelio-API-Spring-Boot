package com.cursonelio.javaspringboot.cursoNelio.service;

import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Categoria;
import com.cursonelio.javaspringboot.cursoNelio.repository.CategoriaRepository;
import com.cursonelio.javaspringboot.cursoNelio.service.exception.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.cursonelio.javaspringboot.cursoNelio.service.exception.ObjectNotFounfException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria find(Integer id) {
        Optional<Categoria> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFounfException(("" +
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())));
    }

    public List<Categoria> findAll(){
        List<Categoria> obj = repository.findAll();
        return obj;
    }

    public Categoria create (Categoria categoria){
            categoria = repository.save(categoria);
            return categoria;

    }

    public Categoria update (Integer id, Categoria categoria){
        categoria.setId(id);
        categoria = repository.save(categoria);
        return categoria;
    }

    public void delete (Integer id){
        try{
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Nao é possivel excluir uma categoria que possui produtos");
        }
    }
}



