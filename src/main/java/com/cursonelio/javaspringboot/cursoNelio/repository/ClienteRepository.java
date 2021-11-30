package com.cursonelio.javaspringboot.cursoNelio.repository;

import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
