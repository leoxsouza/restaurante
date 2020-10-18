package com.restaurante.repository;

import com.restaurante.domain.SobraProduto;
import com.restaurante.service.dto.SobraProdutoListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SobraProdutoRepository extends JpaRepository<SobraProduto, Long> {

    @Query("SELECT new com.restaurante.service.dto.SobraProdutoListDTO(s.produto.descricao, s.qtdPeso, s.qtdUnidade, s.dataSobra) " +
            "FROM SobraProduto s " +
            "WHERE s.produto.empresa.id = :idEmpresa")
    List<SobraProdutoListDTO> listarPorEmpresa(@Param("idEmpresa") Long idEmpresa);
}
