package com.restaurante.service.mapper;

import com.restaurante.domain.Role;
import com.restaurante.domain.UsuariosRole;
import com.restaurante.service.dto.RolesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RolesMapper {

    @Mapping(source = "id", target = "id.role.id")
    @Mapping(source = "descricaoRole", target = "id.role.descricaoRole")
    UsuariosRole toEntity(RolesDTO dto);

    @Mapping(target = "id", source = "id.role.id")
    @Mapping(target = "descricaoRole", source = "id.role.descricaoRole")
    RolesDTO toDto(UsuariosRole entity);

    Role toEntityRoles(RolesDTO dto);

}
