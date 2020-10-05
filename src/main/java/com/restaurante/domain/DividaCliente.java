package com.restaurante.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name="TB_DIVIDA_CLIENTE")
public class DividaCliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DIVIDA_CLIENTE")
    @SequenceGenerator(name = "SQ_DIVIDA_CLIENTE", sequenceName = "SQ_DIVIDA_CLIENTE", allocationSize = 1)
    @Column(name = "ID_DIVIDA_CLIENTE")
    private Long id;

    @Column(name = "VL_TOTAL", nullable = false)
    private Double total;

    @OneToMany(mappedBy = "dividaCliente", fetch = FetchType.EAGER)
    private Set<Divida> dividas;

    @OneToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

}
