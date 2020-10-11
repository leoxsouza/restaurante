package com.restaurante.enumeration;

import com.restaurante.service.dto.UsuarioDTO;

public interface TipoPessoaStrategy {

    default void setRole(UsuarioDTO usuarioDTO) {}
}
