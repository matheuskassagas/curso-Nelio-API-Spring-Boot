package com.cursonelio.javaspringboot.cursoNelio.repository;

import com.cursonelio.javaspringboot.cursoNelio.dto.Response.EstadoResponse;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

    @Transactional(readOnly = true)
    public List<Estado> findAllByOrderByNome();
}
