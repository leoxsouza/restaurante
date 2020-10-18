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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name="TB_SOBRA_PRODUTO")
public class SobraProduto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SOBRA_PRODUTO")
    @SequenceGenerator(name = "SQ_SOBRA_PRODUTO", sequenceName = "SQ_SOBRA_PRODUTO", allocationSize = 1)
    @Column(name = "ID_SOBRA_PRODUTO")
    private Long id;

    @Column(name = "QT_PESO")
    private Double qtdPeso;

    @Column(name = "QT_UNIDADE")
    private Long qtdUnidade;

    @Column(name = "DT_SOBRA", nullable = false)
    private LocalDate dataSobra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PRODUTO", nullable = false)
    private Produto produto;

}
