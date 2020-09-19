package com.restaurante.service.dto;

import com.restaurante.domain.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userLogin;
    private String userNome;

    public UsuarioDTO(Usuario usuario) {
        this.userLogin = usuario.getLogin();
        this.userNome = usuario.getNome();
    }

}
