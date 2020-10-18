package com.restaurante.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name="TB_USUARIO")
public class Usuario implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USUARIO")
    @SequenceGenerator(name = "SQ_USUARIO", sequenceName = "SQ_USUARIO", allocationSize = 1, initialValue = 2)
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "DS_LOGIN", nullable = false, unique = true, updatable = false)
    private String login;

    @Column(name = "DS_SENHA", nullable = false)
    private String senha;

    @OneToMany(mappedBy = "id.usuario", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UsuariosRole> roles;

    @Column(name = "DS_TOKEN")
    private String token;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "ID_PESSOA", name = "ID_PESSOA")
    private Pessoa pessoa;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
