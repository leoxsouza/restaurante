package com.restaurante.service.mapper;

import com.restaurante.domain.ComprasCliente;
import com.restaurante.service.dto.ComprasClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface ComprasClienteMapper {

    @Mapping(source = "idUsuarioCliente", target = "usuarioCliente.id")
    ComprasCliente toEntity(ComprasClienteDTO dto);

    @Mapping(target = "idUsuarioCliente", source = "usuarioCliente.id")
    ComprasClienteDTO toDto(ComprasCliente entity);

}
