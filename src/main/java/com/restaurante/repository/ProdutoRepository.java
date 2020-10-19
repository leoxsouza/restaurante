package com.restaurante.repository;

import com.restaurante.domain.Produto;
import com.restaurante.service.dto.DropDownDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Boolean existsByDescricaoIgnoreCaseAndAndEmpresaId(String descricao, Long empresaId);

    List<Produto> getAllByEmpresaId(Long empresaId);

    @Query("SELECT new com.restaurante.service.dto.DropDownDTO(p.id, p.descricao) FROM Produto p WHERE p.empresa.id = :idEmpresa")
    List<DropDownDTO> getProdutosDropdown(@Param("idEmpresa") Long idEmpresa);
}
