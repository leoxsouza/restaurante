package com.restaurante.dominio;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ROLE")
@Getter
@Setter
public class Role implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ROLE")
    @SequenceGenerator(name = "SQ_ROLE")
    @Column(name = "ID_ROLE")
    private Long id;

    @Column(name = "DS_ROLE")
    private String descricaoRole;

    @Override
    public String getAuthority() {
        return this.descricaoRole;
    }
}