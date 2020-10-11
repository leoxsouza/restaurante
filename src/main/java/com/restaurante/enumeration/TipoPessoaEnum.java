package com.restaurante.enumeration;

import com.restaurante.service.dto.RolesDTO;
import com.restaurante.service.dto.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoPessoaEnum implements TipoPessoaStrategy {

    PF("Cliente") {
        @Override
        public void setRole(UsuarioDTO usuarioDTO) {
            RolesDTO roleCliente = new RolesDTO();
            roleCliente.setDescricaoRole(RoleEnum.CLIENTE.getDescricaoRole());
            roleCliente.setId(RoleEnum.CLIENTE.getId());
            usuarioDTO.setRole(roleCliente);
        }
    },
    PJ("Empresa") {
        @Override
        public void setRole(UsuarioDTO usuarioDTO) {
            RolesDTO roleEmpresa = new RolesDTO();
            roleEmpresa.setDescricaoRole(RoleEnum.EMPRESA.getDescricaoRole());
            roleEmpresa.setId(RoleEnum.EMPRESA.getId());
            usuarioDTO.setRole(roleEmpresa);
        }
    };

    String descricao;
}
