package com.restaurante.repository;

import com.restaurante.domain.ComprasCliente;
import com.restaurante.service.dto.ComprasClienteListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComprasClienteRepository extends JpaRepository<ComprasCliente, Long> {

    @Query("SELECT new com.restaurante.service.dto.ComprasClienteListDTO(c.valorCompra, c.dataCompra) FROM ComprasCliente c " +
            "WHERE c.usuarioEmpresa.pessoa.empresa.id = :idEmpresa AND c.usuarioCliente.login = :login")
    List<ComprasClienteListDTO> getComprasPorUsuario(@Param("login") String login, @Param("idEmpresa") Long idEmpresa);
}
