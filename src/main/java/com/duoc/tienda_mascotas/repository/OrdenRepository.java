package com.duoc.tienda_mascotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.tienda_mascotas.model.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
}
