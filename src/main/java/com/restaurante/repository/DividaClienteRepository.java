package com.restaurante.repository;

import com.restaurante.domain.DividaCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DividaClienteRepository extends JpaRepository<DividaCliente, Long> {

    DividaCliente findByUsuarioClienteId(Long usuarioClienteId);

}
