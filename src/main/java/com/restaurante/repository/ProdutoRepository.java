package com.restaurante.repository;

import com.restaurante.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Boolean existsByDescricaoIgnoreCaseAndAndEmpresaId(String descricao, Long empresaId);

    List<Produto> getAllByEmpresaId(Long empresaId);

}
