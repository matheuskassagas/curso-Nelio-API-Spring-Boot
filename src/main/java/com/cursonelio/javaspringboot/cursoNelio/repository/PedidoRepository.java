package com.cursonelio.javaspringboot.cursoNelio.repository;

import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
