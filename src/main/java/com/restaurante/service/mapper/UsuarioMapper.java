package com.restaurante.service.mapper;

import com.restaurante.domain.Usuario;
import com.restaurante.service.dto.UsuarioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);

    UsuarioDTO toDto(Usuario entity);

}
