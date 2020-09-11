package com.restaurante.dominio;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "RL_USUARIO_ROLE")
public class UsuariosRole implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UsuariosRolePK id;

    @Override
    public String getAuthority() {
        return this.id.getRole().getDescricaoRole();
    }
}
