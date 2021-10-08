package com.cursonelio.javaspringboot.cursoNelio.service;

import com.cursonelio.javaspringboot.cursoNelio.domain.Categoria;
import com.cursonelio.javaspringboot.cursoNelio.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cursonelio.javaspringboot.cursoNelio.service.exception.ObjectNotFounfException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFounfException(("" +
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())));
    }

    public List<Categoria> findAll(){
        List<Categoria> obj = categoriaRepository.findAll();
        return obj;
    }

    public Categoria create (Categoria categoria){
        categoria = categoriaRepository.save(categoria);
        return categoria;
    }

    public Categoria update (Integer id, Categoria categoria){
        categoria.setId(id);
        categoria = categoriaRepository.save(categoria);
        return categoria;
    }

    public void delete (Integer id){
        categoriaRepository.deleteById(id);
    }
}



