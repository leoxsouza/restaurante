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
@Table(name="TB_COMPRAS_CLIENTE")
public class ComprasCliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COMPRAS_CLIENTE")
    @SequenceGenerator(name = "SQ_COMPRAS_CLIENTE", sequenceName = "SQ_COMPRAS_CLIENTE", allocationSize = 1)
    @Column(name = "ID_COMPRAS_CLIENTE")
    private Long id;

    @Column(name = "DH_COMPRA", updatable = false, nullable = false)
    private LocalDateTime dataCompra;

    @Column(name = "VL_COMPRA", nullable = false)
    private Double valorCompra;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_CLIENTE", nullable = false)
    private Usuario usuarioCliente;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_EMPRESA", nullable = false)
    private Usuario usuarioEmpresa;

}
