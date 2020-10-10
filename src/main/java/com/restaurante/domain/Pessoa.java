package com.restaurante.domain;

import com.restaurante.enumeration.TipoPessoaEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name="TB_PESSOA")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PESSOA")
    @SequenceGenerator(name = "SQ_PESSOA", sequenceName = "SQ_PESSOA", allocationSize = 1)
    @Column(name = "ID_PESSOA")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TP_PESSOA", nullable = false)
    private TipoPessoaEnum tipoPessoa;

    @Column(name = "NU_CNPJ")
    private String cnpj;

    @Column(name = "NU_CPF")
    private String cpf;

    @Column(name = "DS_NOME", nullable = false)
    private String nome;

}
