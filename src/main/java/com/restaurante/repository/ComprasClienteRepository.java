package com.restaurante.repository;

import com.restaurante.domain.ComprasCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComprasClienteRepository extends JpaRepository<ComprasCliente, Long> {

}
