package com.restaurante.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoPessoaEnum {

    PF("Cliente"), PJ("Empresa");

    String descricao;
}
