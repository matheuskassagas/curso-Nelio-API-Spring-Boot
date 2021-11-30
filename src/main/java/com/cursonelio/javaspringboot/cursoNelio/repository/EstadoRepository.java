package com.cursonelio.javaspringboot.cursoNelio.repository;

import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}
