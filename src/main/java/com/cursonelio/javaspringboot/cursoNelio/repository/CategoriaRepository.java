package com.cursonelio.javaspringboot.cursoNelio.repository;

import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
