package com.paola.carrito_compras.repository;

import com.paola.carrito_compras.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarritoRepository extends JpaRepository<Carrito, Long> {
}
