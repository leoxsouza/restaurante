package com.restaurante.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name="TB_EMPRESA")
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_EMPRESA")
    @SequenceGenerator(name = "SQ_EMPRESA", sequenceName = "SQ_EMPRESA", allocationSize = 1, initialValue = 3)
    @Column(name = "ID_EMPRESA")
    private Long id;

    @Column(name = "NU_CNPJ", nullable = false)
    private String cnpj;

    @Column(name = "DS_NOME", nullable = false)
    private String nome;

}
