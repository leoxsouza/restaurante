package com.restaurante.repository;

import com.restaurante.domain.DividaCliente;
import com.restaurante.service.dto.DividaClienteListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DividaClienteRepository extends JpaRepository<DividaCliente, Long> {

    DividaCliente findByUsuarioClienteIdAndUsuarioEmpresaPessoaEmpresaId(Long usuarioClienteId, Long empresaId);

    @Query("SELECT new com.restaurante.service.dto.DividaClienteListDTO(d.id, d.total, d.usuarioCliente.pessoa.nome, d.usuarioCliente.id) " +
            "FROM DividaCliente d " +
            "WHERE d.usuarioEmpresa.id = :usuarioEmpresaId")
    List<DividaClienteListDTO> listarDividas(@Param("usuarioEmpresaId") Long usuarioEmpresaId);
}
