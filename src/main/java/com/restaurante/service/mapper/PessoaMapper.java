package com.restaurante.service.mapper;

import com.restaurante.domain.Pessoa;
import com.restaurante.service.dto.PessoaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PessoaMapper {

    @Mapping(source = "empresa", target = "empresa.id")
    Pessoa toEntity(PessoaDTO dto);

    @Mapping(target = "empresa", source = "empresa.id")
    PessoaDTO toDto(Pessoa entity);

}
