package com.restaurante.service.mapper;

import com.restaurante.domain.DividaCliente;
import com.restaurante.service.dto.ComprasClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface DividaClienteMapper {

    @Mapping(source = "idUsuarioCliente", target = "usuarioCliente.id")
    @Mapping(source = "valorCompra", target = "total")
    DividaCliente toEntity(ComprasClienteDTO dto);

}
