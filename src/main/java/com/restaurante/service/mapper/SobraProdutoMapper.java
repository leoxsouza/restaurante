package com.restaurante.service.mapper;

import com.restaurante.domain.SobraProduto;
import com.restaurante.service.dto.SobraProdutoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SobraProdutoMapper {

    @Mapping(source = "idProduto", target = "produto.id")
    SobraProduto toEntity(SobraProdutoDTO dto);

    @Mapping(target = "idProduto", source = "produto.id")
    SobraProdutoDTO toDto(SobraProduto entity);

}
