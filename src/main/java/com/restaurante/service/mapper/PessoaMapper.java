package com.restaurante.service.mapper;

import com.restaurante.domain.Pessoa;
import com.restaurante.service.dto.PessoaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PessoaMapper {

    Pessoa toEntity(PessoaDTO dto);

    PessoaDTO toDto(Pessoa entity);

}
