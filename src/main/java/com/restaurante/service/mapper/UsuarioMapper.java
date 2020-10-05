package com.restaurante.service.mapper;

import com.restaurante.domain.Usuario;
import com.restaurante.service.dto.CredenciaisDTO;
import com.restaurante.service.dto.UsuarioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RolesMapper.class})
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);

    Usuario toEntity(CredenciaisDTO dto);

    UsuarioDTO toDto(Usuario entity);

}
