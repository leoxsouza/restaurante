package com.restaurante.service.dto;

import com.restaurante.enumeration.TipoPessoaEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PessoaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private TipoPessoaEnum tipoPessoa;

    private String cnpj;

    private String cpf;

    private String nome;

    private String celular;

    private Long empresa;
}
