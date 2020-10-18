package com.restaurante.service.mapper;

import com.restaurante.domain.Produto;
import com.restaurante.service.dto.ProdutoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    Produto toEntity(ProdutoDTO dto);

    ProdutoDTO toDto(Produto entity);

}
