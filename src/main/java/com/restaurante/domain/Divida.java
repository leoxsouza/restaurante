package com.restaurante.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name="TB_DIVIDA")
public class Divida implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DIVIDA")
    @SequenceGenerator(name = "SQ_DIVIDA", sequenceName = "SQ_DIVIDA", allocationSize = 1)
    @Column(name = "ID_DIVIDA")
    private Long id;

    @Column(name = "DH_DIVIDA", updatable = false, nullable = false)
    private LocalDateTime dataDivida;

    @Column(name = "VL_DIVIDA", nullable = false)
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "ID_DIVIDA_CLIENTE")
    private DividaCliente dividaCliente;

}
