package com.restaurante.repository;

import com.restaurante.domain.DividaCliente;
import com.restaurante.service.dto.ClienteDividaDTO;
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
            "WHERE d.usuarioEmpresa.pessoa.empresa.id = :idEmpresa")
    List<DividaClienteListDTO> listarDividas(@Param("idEmpresa") Long idEmpresa);

    @Query("SELECT new com.restaurante.service.dto.ClienteDividaDTO(d.id, d.total, d.usuarioEmpresa.pessoa.empresa.nome, d.usuarioEmpresa.pessoa.empresa.id) " +
            "FROM DividaCliente d WHERE d.usuarioCliente.login = :login")
    List<ClienteDividaDTO> listarDividaPorCliente(@Param("login") String login);
}
