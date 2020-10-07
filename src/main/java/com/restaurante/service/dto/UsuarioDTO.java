package com.restaurante.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String login;
    private String senha;

    private RolesDTO role;
    private PessoaDTO pessoa;

}
