package com.restaurante.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {

    EMPRESA(2L, "EMPRESA"),
    CLIENTE(3L, "CLIENTE");

    Long id;
    String descricaoRole;

}
